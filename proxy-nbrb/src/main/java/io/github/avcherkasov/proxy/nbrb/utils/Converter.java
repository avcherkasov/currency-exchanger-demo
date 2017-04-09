package io.github.avcherkasov.proxy.nbrb.utils;

import io.github.avcherkasov.protocol.model.Currency;
import io.github.avcherkasov.proxy.nbrb.model.json.Rates;
import io.github.avcherkasov.proxy.nbrb.model.soap.NewDataSet;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Common converter
 *
 * @author Anatoly Cherkasov
 * @see Rates
 * @see Currency
 */
public class Converter {

    /**
     * Mapping rates and num code to currency
     */
    public static BiFunction<Rates, String, Currency> mapToCurrencyWithNumCode = (rates, numCode) -> {
        Currency currency = new Currency();
        currency.setNumCode(numCode);
        currency.setCharCode(rates.getCurAbbreviation());
        currency.setName(rates.getCurName());
        currency.setNominal(rates.getCurScale());
        currency.setRate(rates.getCurOfficialRate());
        return currency;
    };

    /**
     * Mapping rates to currency
     */
    public static Function<NewDataSet.DailyExRatesOnDate, Currency> mapToCurrency = (rates) -> {
        Currency currency = new Currency();
        currency.setNumCode(rates.getCurCode());
        currency.setCharCode(rates.getCurAbbreviation());
        currency.setName(rates.getCurQuotName());
        currency.setNominal(Long.valueOf(rates.getCurScale()));
        currency.setRate(rates.getCurOfficialRate());
        return currency;
    };

}
