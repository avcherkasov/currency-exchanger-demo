package io.github.avcherkasov.currency.exchanger.utils;

import io.github.avcherkasov.currency.exchanger.entity.Rates;
import io.github.avcherkasov.protocol.model.Currency;

import java.math.BigDecimal;

/**
 * A helper class for working with currency
 *
 * @author Anatoly Cherkasov
 */
public class CurrencyUtils {

    /**
     * Make currency from rates
     *
     * @param rates {@link Rates Rates}
     * @return {@link Currency Currency}
     */
    public static Currency makeCurrencyFromRates(Rates rates) {
        Currency currency = new Currency();
        currency.setNominal(rates.getNominal());
        currency.setRate(new BigDecimal(rates.getRate()));
        currency.setNumCode(rates.getVendorCurrency().getTargetCurrency().getNumCode());
        currency.setCharCode(rates.getVendorCurrency().getTargetCurrency().getCharCode());
        currency.setName(rates.getVendorCurrency().getTargetCurrency().getName());
        return currency;
    }

}
