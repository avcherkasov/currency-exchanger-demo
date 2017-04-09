package io.github.avcherkasov.proxy.cbr.utils.adapter;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Anatoly Cherkasov
 * @see BigDecimalXmlAdapter
 */
public class BigDecimalXmlAdapterTest {

    private BigDecimalXmlAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new BigDecimalXmlAdapter();
    }

    @Test
    public void testUnmarshallOK() throws Exception {
        BigDecimal value = BigDecimal.valueOf(10.10).setScale(2, BigDecimal.ROUND_HALF_UP);
        String number = "10.10";
        assertEquals(adapter.unmarshal(number), value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnmarshallNotOK() throws Exception {
        adapter.unmarshal("VALUE_STRING_WRONG");
    }

}