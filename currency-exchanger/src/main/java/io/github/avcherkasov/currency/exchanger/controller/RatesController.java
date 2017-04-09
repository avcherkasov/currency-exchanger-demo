package io.github.avcherkasov.currency.exchanger.controller;

import io.github.avcherkasov.currency.exchanger.entity.Rates;
import io.github.avcherkasov.currency.exchanger.model.RateResult;
import io.github.avcherkasov.currency.exchanger.model.wrapper.RateResultWrapper;
import io.github.avcherkasov.currency.exchanger.service.RateService;
import io.github.avcherkasov.currency.exchanger.utils.CurrencyUtils;
import io.github.avcherkasov.currency.exchanger.utils.CustomDateUtils;
import io.github.avcherkasov.currency.exchanger.utils.ErrorMapping;
import io.github.avcherkasov.protocol.model.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Currency Rates by date and currency type
 *
 * @author Anatoly Cherkasov
 * @see RateService
 * @see Rates
 * @see Currency
 * @see io.github.avcherkasov.currency.exchanger.entity.Vendor
 */
@Slf4j
@RestController
@RequestMapping(value = "rates/v1")
public class RatesController {

    private RateService rateService;

    @Autowired
    public RatesController(RateService rateService) {
        Assert.notNull(rateService, "RateService must not be null");

        this.rateService = rateService;
    }

    /**
     * Get rates by code currency
     * <p>
     * Input:
     * e.g. http://{host}:{port}/{path}/{version}/cbr/usd?date=1016-01-21
     * <p>
     * Output:
     * <pre>
     * <code> {
     *      date: "2017-03-21",
     *      vendor: "Центральный Банк России",
     *      currencies: [
     *          {
     *              numCode: "840",
     *              charCode: "USD",
     *              nominal: 1,
     *              name: "Доллар США",
     *              rate: 58.3776
     *          }
     *      ]
     * } <code>
     * </pre>
     *
     * @param vendor     {@link io.github.avcherkasov.currency.exchanger.entity.Vendor}
     * @param currency   {@link io.github.avcherkasov.currency.exchanger.entity.Currency}
     * @param inputeDate {@link java.util.Date}
     * @return {@link RateResult}
     * @throws ParseException {@link ParseException} exception
     */
    @RequestMapping(value = "/{vendor}/{currency}", method = RequestMethod.GET)
    public RateResult getRatesByCodeCurrency(
            @PathVariable("vendor") String vendor,
            @PathVariable(value = "currency") String currency,
            @RequestParam(value = "date", defaultValue = "today", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date inputeDate
    ) throws ParseException {
        LOGGER.info("vendor {}, currency {}, date {}", vendor, currency, inputeDate);
        Rates rates = rateService.findByVendorNameAndCurrencyAndDate(
                vendor,
                currency.toUpperCase(),
                inputeDate
        );

        if (rates == null) {
            return RateResultWrapper.makeError(
                    ErrorMapping.RECORD_NOT_FOUND,
                    ErrorMapping.RECORD_NOT_FOUND
            );
        }

        List<Currency> currencies = new ArrayList<>();
        currencies.add(CurrencyUtils.makeCurrencyFromRates(rates));

        return RateResultWrapper.makeCurrencyWithDateAndVendor(
                rates.getRateDate(),
                rates.getVendorCurrency().getVendor().getFullName(),
                currencies
        );
    }

    /**
     * Init Binder
     *
     * @param binder {@link WebDataBinder WebDataBinder}
     * @see <a href="http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/InitBinder.html">InitBinder</a>
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CustomDateUtils.DATE_PATTERN);
        final CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true) {
            @Override
            public void setAsText(String text) {
                if ("today".equals(text)) {
                    super.setValue(new Date());
                } else {
                    super.setAsText(text);
                }
            }
        };
        binder.registerCustomEditor(Date.class, dateEditor);
    }

}
