package io.github.avcherkasov.proxy.nbrb.handler;

import io.github.avcherkasov.protocol.Proxy;
import io.github.avcherkasov.protocol.exception.VendorException;
import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.Currency;
import io.github.avcherkasov.protocol.model.ProxyResult;
import io.github.avcherkasov.protocol.wrapper.ProxyResultWrapper;
import io.github.avcherkasov.proxy.nbrb.model.json.Currencies;
import io.github.avcherkasov.proxy.nbrb.model.json.Rates;
import io.github.avcherkasov.proxy.nbrb.utils.Converter;
import io.github.avcherkasov.proxy.nbrb.utils.CustomDateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Proxy NBRB Handler
 *
 * @author Anatoly Cherkasov
 * @see <a href="https://www.nbrb.by/APIHelp/ExRates">Technical resources</a>
 * @see <a href="http://www.nbrb.by/API/ExRates/Rates?onDate=2017-04-05&Periodicity=0">Json API</a>
 * @see Proxy
 * @see ProxyResult
 */
@Component
public class NbrbJsonHandler implements Proxy, NbrbHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbrbHandler.class);

    @Value("${nbrb.url}")
    private String url;

    @Value("#{'${nbrb.fields.required}'}")
    private String[] requiredFields;

    private RestTemplate restTemplate = new RestTemplate();

    /** Map with String keys and String values */
    private Map<String, String> options = Collections.emptyMap();

    /** List of {@link Currencies} */
    private List<Currencies> currencyCodes = Collections.emptyList();

    // ------------------------------------------------------------------------
    // Overridden interface methods
    // ------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean filter(String format) {
        return "json".equalsIgnoreCase(format);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxyResult accept(Context context) {
        return processed(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxyResult processed(Context context) throws VendorException {
        LOGGER.info("CurrencyRates. start with input params: context {}", context);
        ResponseEntity<Rates[]> responseEntity;
        options = context.getOptions();

        if (!checkRequiredFields(options)) {
            String msg = String.format("Not found some required fields %s in options %s", Arrays.toString(requiredFields), options.toString());
            LOGGER.error(msg);
            return ProxyResultWrapper.makeError("Empty required fields", msg);
        }

        try {
            currencyCodes = getCodesInAllCurrencies();
            String dateReq = CustomDateUtils.convert(context.getDateReq());

            String prepareUrl = UriComponentsBuilder
                    .fromUriString(options.getOrDefault("url", url))
                    .path("/Rates")
                    .queryParam("onDate", dateReq)
                    .queryParam("Periodicity", options.getOrDefault("Periodicity", "0"))
                    .build()
                    .toUriString();
            LOGGER.info("CurrencyRates. prepareUrl {}", prepareUrl);

            responseEntity = restTemplate.getForEntity(prepareUrl, Rates[].class);
            LOGGER.info("CurrencyRates. response {}", responseEntity);
        } catch (Exception e) {
            LOGGER.info("CurrencyRates. Exception ", e);
            return ProxyResultWrapper.makeError("error", e.getMessage());
        }

        ProxyResult proxyResult = new ProxyResult();
        if (responseEntity != null) {
            Rates[] rates = responseEntity.getBody();
            List<Currency> currencies = Arrays.stream(rates)
                    .parallel().map(
                            ratesCurrency -> {
                                String numCode = findNumCodeByCharCode(ratesCurrency.getCurAbbreviation());
                                return Converter.mapToCurrencyWithNumCode.apply(ratesCurrency, numCode);
                            }
                    )
                    .collect(Collectors.toList());
            proxyResult.setCurrencies(currencies);
        }
        LOGGER.info("CurrencyRates. finish {}", proxyResult);
        return proxyResult;
    }

    // ------------------------------------------------------------------------
    // Other supporting methods
    // ------------------------------------------------------------------------

    /**
     * Find digital currency code by symbolic currency code
     *
     * @param charCode symbolic currency code
     * @return digital currency code
     */
    private String findNumCodeByCharCode(String charCode) {
        if (currencyCodes.stream().anyMatch(
                codes -> codes.getCurAbbreviation().equals(charCode)
        )) {
            return currencyCodes.stream().filter(
                    codes -> codes.getCurAbbreviation().equals(charCode)
            ).findFirst().orElse(new Currencies()).getCurCode();
        }
        return "";
    }

    /**
     * Get codes in all currencies
     *
     * @return list of {@link Currencies}
     */
    private List<Currencies> getCodesInAllCurrencies() {
        ResponseEntity<Currencies[]> responseEntity;
        try {
            String prepareUrl = UriComponentsBuilder
                    .fromUriString(options.getOrDefault("url", url))
                    .path("/Currencies")
                    .build()
                    .toUriString();
            LOGGER.info("CurrencyRates. prepareUrl {}", prepareUrl);

            responseEntity = restTemplate.getForEntity(prepareUrl, Currencies[].class);
            LOGGER.info("CurrencyRates. response {}", responseEntity);
            return Arrays.asList(responseEntity.getBody());
        } catch (Exception e) {
            LOGGER.info("CurrencyRates. Exception ", e);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Check required fields
     *
     * @param options {@link #requiredFields}
     * @return boolean
     */
    private boolean checkRequiredFields(Map options) {
        return Arrays.stream(requiredFields).allMatch(options::containsKey);
    }

}
