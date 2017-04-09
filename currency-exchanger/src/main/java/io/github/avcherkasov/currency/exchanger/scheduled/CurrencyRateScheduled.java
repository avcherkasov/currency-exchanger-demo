package io.github.avcherkasov.currency.exchanger.scheduled;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.avcherkasov.currency.exchanger.entity.Rates;
import io.github.avcherkasov.currency.exchanger.entity.VendorCurrency;
import io.github.avcherkasov.currency.exchanger.service.RateService;
import io.github.avcherkasov.currency.exchanger.service.VendorCurrencyService;
import io.github.avcherkasov.currency.exchanger.utils.CustomDateUtils;
import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.Currency;
import io.github.avcherkasov.protocol.model.ProxyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;


/**
 * Currency rate scheduled
 *
 * @author Anatoly Cherkasov
 * @see VendorCurrencyService
 * @see RateService
 * @see ProxyResult
 * @see VendorCurrency
 * @see Currency
 * @see Rates
 * @see Context
 * @see ObjectMapper
 * @see RestTemplate
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "cron", name = "expression")
public class CurrencyRateScheduled {

    private VendorCurrencyService vendorCurrencyService;

    private RateService rateService;

    private ObjectMapper objectMapper;

    private RestTemplate restTemplate;

    private Date date_request;

    @Autowired
    public CurrencyRateScheduled(
            VendorCurrencyService vendorCurrencyService,
            RateService rateService,
            ObjectMapper objectMapper,
            RestTemplate restTemplate
    ) {
        Assert.notNull(vendorCurrencyService, "VendorCurrencyService must not be null");
        Assert.notNull(rateService, "RateService must not be null");
        Assert.notNull(objectMapper, "ObjectMapper must not be null");
        Assert.notNull(restTemplate, "RestTemplate must not be null");

        this.vendorCurrencyService = vendorCurrencyService;
        this.rateService = rateService;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    /**
     * To receive exchange rates from suppliers
     */
    @Scheduled(fixedRateString = "${cron.expression}")
    public void getCurrencyRates() {

        // dateReq e.g.: dd/MM/yyyy
        // TODO: only for demo
        String dateReq = "05/04/2017";
        LocalDate localDate = LocalDate.parse(dateReq, DateTimeFormatter.ofPattern(CustomDateUtils.REQUEST_DATE_PATTERN));
        date_request = java.sql.Date.valueOf(localDate);

        // TODO: Commented before the demonstration
        //String dateReq = CustomDateUtils.getCurrentDateForRequest();
        LOGGER.info("CurrencyRates. start with input params: date_req {}", dateReq);

        List<VendorCurrency> list = vendorCurrencyService.findAllWhereIsActiveTrue();

        // Check currency and error, other
        list.stream().parallel().forEach((VendorCurrency vc) -> {
            try {
                ResponseEntity<ProxyResult> responseEntity = send(
                        vc.getVendor().getUrl(),
                        makeContext(dateReq, vc.getVendor().getAdditional())
                );

                if (Optional.ofNullable(responseEntity.getBody().getCurrencies()).isPresent()) {
                    getRateAndSaveFromCurrencies(responseEntity.getBody().getCurrencies(), vc);
                }

                if(Optional.ofNullable(responseEntity.getBody().getError()).isPresent()) {
                    LOGGER.warn("CurrencyRates. finish with error {}", responseEntity.getBody().getError());
                }
            } catch (IOException e) {
                LOGGER.error("Exception ", e);
                throw new IllegalArgumentException(e);
            }
        });
        LOGGER.info("CurrencyRates. finish");
    }

    // ------------------------------------------------------------------------
    // Other supporting methods
    // ------------------------------------------------------------------------

    /**
     * Get rate and save from currencies
     *
     * @param currencies     list of {@link Currency}
     * @param vendorCurrency {@link VendorCurrency VendorCurrency}
     */
    private void getRateAndSaveFromCurrencies(List<Currency> currencies, VendorCurrency vendorCurrency) {
        currencies.stream().parallel()
                .filter(currencyIsEquals(vendorCurrency)).forEach(currency -> {
                    Rates rate = makeRates(vendorCurrency, currency);
                    if (!rateService.exists(rate)) {
                        LOGGER.info("Save rate {}", rate);
                        rateService.save(rate);
                    }
                }
        );
    }

    /**
     * Currency is equals
     *
     * @param vendorCurrency {@link VendorCurrency VendorCurrency}
     * @return Predicate<Currency>
     */
    private Predicate<Currency> currencyIsEquals(VendorCurrency vendorCurrency) {
        return currency ->
                currency.getCharCode().equals(vendorCurrency.getTargetCurrency().getCharCode()) &&
                        currency.getNumCode().equals(vendorCurrency.getTargetCurrency().getNumCode());
    }

    /**
     * Make rates
     *
     * @param vendorCurrency {@link VendorCurrency VendorCurrency}
     * @param currency       {@link Currency Currency}
     * @return {@link Rates Rates}
     */
    private Rates makeRates(VendorCurrency vendorCurrency, Currency currency) {
        return new Rates(vendorCurrency, currency.getNominal(), currency.getRate().doubleValue(), date_request);
    }

    /**
     * Send request
     *
     * @param url     proxy URL
     * @param context {@link Context context}
     * @return ResponseEntity<ProxyResult>
     */
    private ResponseEntity<ProxyResult> send(String url, Context context) {
        return restTemplate.postForEntity(url, context, ProxyResult.class);
    }

    /**
     * Make context
     *
     * @param dataReq date request
     * @param options proxy options
     * @return {@link Context context}
     * @throws IOException exception
     */
    private Context makeContext(String dataReq, String options) throws IOException {
        return Context.builder()
                .dateReq(dataReq)
                .options(objectMapper.readValue(options, Map.class))
                .build();
    }

}
