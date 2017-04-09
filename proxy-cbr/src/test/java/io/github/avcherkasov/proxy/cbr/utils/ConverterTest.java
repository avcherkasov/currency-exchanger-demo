package io.github.avcherkasov.proxy.cbr.utils;

import io.github.avcherkasov.protocol.model.Currency;
import io.github.avcherkasov.proxy.cbr.model.Valute;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Anatoly Cherkasov
 * @see Converter
 */
public class ConverterTest {

    @Test
    public void mapToCurrency() {
        Long nominal = 10L;
        BigDecimal rate = new BigDecimal(10);
        String numCode = "643";
        String charCode = "RUB";
        String name = "Rubles";

        Currency currency = new Currency();
        currency.setNumCode(numCode);
        currency.setCharCode(charCode);
        currency.setName(name);
        currency.setNominal(nominal);
        currency.setRate(rate);

        Valute valute = new Valute();
        valute.setCharCode(charCode);
        valute.setNumCode(numCode);
        valute.setName(name);
        valute.setNominal(nominal);
        valute.setValue(rate);

        assertEquals(currency, Converter.mapToCurrency.apply(valute));
    }

}
