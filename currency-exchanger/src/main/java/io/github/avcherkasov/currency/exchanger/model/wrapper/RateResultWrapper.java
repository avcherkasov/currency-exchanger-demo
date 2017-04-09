package io.github.avcherkasov.currency.exchanger.model.wrapper;

import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import io.github.avcherkasov.currency.exchanger.model.RateResult;
import io.github.avcherkasov.protocol.model.Currency;
import io.github.avcherkasov.protocol.model.Error;

import java.util.Date;
import java.util.List;

/**
 * Handy class for wrapping {@link RateResult}
 *
 * @author Anatoly Cherkasov
 * @see Currency
 * @see RateResult
 */
public class RateResultWrapper {

    /**
     * Make currency with date and vendor name
     *
     * @param date       date
     * @param vendor     {@link Vendor#getFullName()}
     * @param currencies list of {@link Currency Currency}
     * @return RateResult {@link RateResult}
     */
    public static RateResult makeCurrencyWithDateAndVendor(Date date, String vendor, List<Currency> currencies) {
        RateResult rateResult = new RateResult();
        rateResult.setDate(date);
        rateResult.setVendor(vendor);
        rateResult.setCurrencies(currencies);
        return rateResult;
    }

    /**
     * Make error with code and detail message
     *
     * @param code    error code
     * @param message error detail message
     * @return RateResult {@link RateResult}
     */
    public static RateResult makeError(final String code, final String message) {
        RateResult rateResult = new RateResult();
        rateResult.setError(new Error(code, message));
        return rateResult;
    }

}
