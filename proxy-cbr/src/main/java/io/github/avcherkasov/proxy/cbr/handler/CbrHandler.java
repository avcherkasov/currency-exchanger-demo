package io.github.avcherkasov.proxy.cbr.handler;

import io.github.avcherkasov.protocol.Proxy;
import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.Currency;
import io.github.avcherkasov.protocol.model.ProxyResult;
import io.github.avcherkasov.protocol.wrapper.ProxyResultWrapper;
import io.github.avcherkasov.proxy.cbr.model.ValCurs;
import io.github.avcherkasov.proxy.cbr.utils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Proxy CBR Handler
 *
 * @author Anatoly Cherkasov
 *
 * @see <a href="http://www.cbr.ru/scripts/Root.asp?PrtId=SXML">Technical resources</a>
 *
 * @see Proxy
 * @see ProxyResult
 */
@Component
public class CbrHandler implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(CbrHandler.class);

    @Value("${cbr.url}")
    private String url;

    @Value("#{'${cbr.fields.required}'}")
    private String[] requiredFields;

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxyResult processed(Context context) {
        LOGGER.info("CurrencyRates. start with input params: context {}", context);
        ResponseEntity<ValCurs> responseEntity;
        Map<String, String> options = context.getOptions();

        if (!checkRequiredFields(options)) {
            String msg = String.format("Not found some required fields %s in options %s", Arrays.toString(requiredFields), options.toString());
            LOGGER.error(msg);
            return ProxyResultWrapper.makeError("Empty required fields", msg);
        }

        try {
            String prepareUrl = UriComponentsBuilder
                    .fromUriString(options.getOrDefault("url", url))
                    .queryParam("date_req", context.getDateReq())
                    .build()
                    .toUriString();
            LOGGER.info("CurrencyRates. prepareUrl {}", prepareUrl);

            responseEntity = new RestTemplate().getForEntity(prepareUrl, ValCurs.class);
            LOGGER.info("CurrencyRates. response {}", responseEntity);
        } catch (Exception e) {
            LOGGER.info("CurrencyRates. Exception ", e);
            return ProxyResultWrapper.makeError("error", e.getMessage());
        }

        ProxyResult proxyResult = new ProxyResult();
        if (responseEntity != null) {
            ValCurs valCurs = responseEntity.getBody();

            List<Currency> currencies = valCurs
                    .getValutes()
                    .stream().parallel()
                    .map(Converter.mapToCurrency)
                    .collect(Collectors.toList());

            proxyResult.setCurrencies(currencies);
        }
        LOGGER.info("CurrencyRates. finish {}", proxyResult);
        return proxyResult;
    }

    private boolean checkRequiredFields(Map options) {
        return Arrays.stream(requiredFields).allMatch(options::containsKey);
    }

}
