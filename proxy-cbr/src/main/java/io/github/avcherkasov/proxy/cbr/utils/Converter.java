package io.github.avcherkasov.proxy.cbr.utils;

import io.github.avcherkasov.proxy.cbr.model.Valute;
import io.github.avcherkasov.protocol.model.Currency;

import java.util.function.Function;

/**
 * Common converter
 *
 * @author Anatoly Cherkasov
 * @see Valute
 * @see Currency
 */
public class Converter {

    /**
     * Mapping valute to currency
     */
    public static Function<Valute, Currency> mapToCurrency = (valute) -> {
        Currency currency = new Currency();
        currency.setNumCode(valute.getNumCode());
        currency.setCharCode(valute.getCharCode());
        currency.setName(valute.getName());
        currency.setNominal(valute.getNominal());
        currency.setRate(valute.getValue());
        return currency;
    };

}
