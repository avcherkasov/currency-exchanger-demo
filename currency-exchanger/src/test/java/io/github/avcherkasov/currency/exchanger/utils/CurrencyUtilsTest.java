package io.github.avcherkasov.currency.exchanger.utils;

import io.github.avcherkasov.currency.exchanger.entity.Rates;
import io.github.avcherkasov.currency.exchanger.entity.VendorCurrency;
import io.github.avcherkasov.protocol.model.Currency;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Anatoly Cherkasov
 * @see Rates
 * @see VendorCurrency
 * @see io.github.avcherkasov.currency.exchanger.entity.Currency
 * @see io.github.avcherkasov.protocol.model.Currency
 * @see MockitoAnnotations
 */
public class CurrencyUtilsTest {

    /**
     * {@link Rates Rates}
     */
    @Spy
    private Rates rates;

    /**
     * {@link VendorCurrency VendorCurrency}
     */
    @Spy
    private VendorCurrency vendorCurrency;

    /**
     * {@link io.github.avcherkasov.currency.exchanger.entity.Currency Currency}
     */
    @Spy
    private io.github.avcherkasov.currency.exchanger.entity.Currency currency;

    /**
     * This must be called for the @Mock annotations above to be processed
     * and for the mock service to be injected into the controller under test.
     *
     * @throws Exception exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void makeCurrencyFromRates() {
        Long nominal = 10L;
        BigDecimal rate = new BigDecimal(10);
        String numCode = "643";
        String charCode = "RUB";
        String currencyName = "Rubles";

        rates.setNominal(nominal);
        rates.setRate(rate.doubleValue());

        currency.setNumCode(numCode);
        currency.setCharCode(charCode);
        currency.setName(currencyName);
        vendorCurrency.setTargetCurrency(currency);
        rates.setVendorCurrency(vendorCurrency);

        Currency currency = new Currency();
        currency.setNominal(nominal);
        currency.setRate(rate);
        currency.setNumCode(numCode);
        currency.setCharCode(charCode);
        currency.setName(currencyName);

        assertEquals(currency, CurrencyUtils.makeCurrencyFromRates(rates));
    }

}
