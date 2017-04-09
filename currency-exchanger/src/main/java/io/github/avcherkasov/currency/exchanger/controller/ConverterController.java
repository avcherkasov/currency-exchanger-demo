package io.github.avcherkasov.currency.exchanger.controller;

import io.github.avcherkasov.currency.exchanger.entity.Currency;
import io.github.avcherkasov.currency.exchanger.entity.Rates;
import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import io.github.avcherkasov.currency.exchanger.model.ConverterResult;
import io.github.avcherkasov.currency.exchanger.model.wrapper.ConverterResultWrapper;
import io.github.avcherkasov.currency.exchanger.service.RateService;
import io.github.avcherkasov.currency.exchanger.service.VendorService;
import io.github.avcherkasov.currency.exchanger.utils.CustomDateUtils;
import io.github.avcherkasov.currency.exchanger.utils.ErrorMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Currency conversion from one to another for a certain date
 *
 * @author Anatoly Cherkasov
 * @see RateService
 * @see VendorService
 * @see ConverterResult
 */
@Slf4j
@RestController
@RequestMapping(value = "converter/v1")
public class ConverterController {

    private static final Double DEFAULT_RATE = 1.0;

    private RateService rateService;

    private VendorService vendorService;

    @Autowired
    public ConverterController(RateService rateService, VendorService vendorService) {
        Assert.notNull(rateService, "RateService must not be null");
        Assert.notNull(vendorService, "VendorService must not be null");

        this.rateService = rateService;
        this.vendorService = vendorService;
    }

    /**
     * Convert currencies
     * <p>
     * Input:
     * e.g. http://{host}:{port}/{path}/{version}/cbr/rub/usd/2017-03-21/1
     * <p>
     * Output:
     * <pre>
     * <code> {
     *      vendor: "Центральный Банк России",
     *      from: "USD",
     *      to: "RUB",
     *      rate: 116.7552,
     *      date: 2017-03-21,
     *      count: 2
     * } <code>
     * </pre>
     *
     * Output with error:
     * <pre>
     * <code> {
     *      error: {
     *          code: "Record not found",
     *          message: "Record not found"
     *      }
     * } <code>
     * </pre>
     *
     * @param vendorName   {@link Vendor#getName()}
     * @param fromCurrency {@link Currency#getCharCode()} or  {@link Currency#getNumCode()}
     * @param toCurrency   {@link Currency#getCharCode()} or  {@link Currency#getNumCode()}
     * @param inputDate    Request for a specific date
     * @param count        The count of currency to convert
     * @return ConverterResult {@link ConverterResult}
     * @throws ParseException Signals that an error has been reached unexpectedly while parsing
     */
    @RequestMapping(value = "/{vendor}/{from_currency}/{to_currency}/{date}/{count}", method = RequestMethod.GET)
    public ConverterResult convert(
            @PathVariable("vendor") String vendorName,
            @PathVariable("from_currency") String fromCurrency,
            @PathVariable("to_currency") String toCurrency,
            @PathVariable("date") @DateTimeFormat(pattern = CustomDateUtils.DATE_PATTERN) Date inputDate,
            @PathVariable("count") int count
    ) throws ParseException {
        LOGGER.info("Start with vendor name {}, from currency {}, to currency {}, input date {}, count {}",
                vendorName, fromCurrency, toCurrency, inputDate, count);
        Double rateResponse;

        // One and the same currency 1 to 1 (e.g. USD -> USD)
        if (fromCurrency.equals(toCurrency)) {
            rateResponse = DEFAULT_RATE;
        } else {
            List<Rates> rates = rateService.findByVendorNameAndCurrencyAndDate(
                    vendorName,
                    fromCurrency.toUpperCase(),
                    toCurrency.toUpperCase(),
                    inputDate
            );

            if (rates.size() > 1) {
                //From one currency to another
                rateResponse = receiveRatesInSeveralCurrencies(toCurrency, rates);
            } else if (rates.size() == 1) {
                // Conventional conversion
                rateResponse = receiveRatesForOneCurrency(toCurrency, rates);
            } else {
                ConverterResult converterResult = ConverterResultWrapper.makeError(
                        ErrorMapping.RECORD_NOT_FOUND,
                        ErrorMapping.RECORD_NOT_FOUND
                );
                LOGGER.error("Error with converterResult {}", converterResult);
                return converterResult;
            }
        }

        Vendor vendor = vendorService.findByName(vendorName);
        ConverterResult converterResult = new ConverterResult(
                vendor.getFullName(),
                fromCurrency.toUpperCase(),
                toCurrency.toUpperCase(),
                (rateResponse * count),
                inputDate,
                count
        );

        LOGGER.info("Finish with converterResult {}", converterResult);
        return converterResult;
    }

    /**
     * Receive rates for one currency
     * (e.g. "RUB -> USD else USD -> RUB")
     *
     * @param toCurrency {@link Currency#getCharCode()} or  {@link Currency#getNumCode()}
     * @param rates      List of {@link Rates Rates} (Can by {@code null})
     * @return Double
     */
    private Double receiveRatesForOneCurrency(String toCurrency, List<Rates> rates) {
        Currency currency = rates.get(0).getVendorCurrency().getTargetCurrency();
        Double rateCurrency = rates.get(0).getCurrencyWithNominal();
        return currency.сurrencyIsEqualToCode(toCurrency)
                ? (DEFAULT_RATE / rateCurrency)
                : (rateCurrency * DEFAULT_RATE);
    }

    /**
     * Receive rates in several currencies
     * (e.g. "EUR -> USD else USD -> EUR")
     *
     * @param toCurrency {@link Currency#getCharCode()} or {@link Currency#getNumCode()}
     * @param rates      List of {@link Rates Rates} (Can by {@code null})
     * @return Double
     */
    private Double receiveRatesInSeveralCurrencies(String toCurrency, List<Rates> rates) {
        Currency currencyOne = rates.get(0).getVendorCurrency().getTargetCurrency();
        Double rateCurrencyOne = rates.get(0).getCurrencyWithNominal();
        Double rateCurrencyTwo = rates.get(1).getCurrencyWithNominal();
        return currencyOne.сurrencyIsEqualToCode(toCurrency)
                ? rateCurrencyTwo / rateCurrencyOne
                : rateCurrencyOne / rateCurrencyTwo;
    }

}
