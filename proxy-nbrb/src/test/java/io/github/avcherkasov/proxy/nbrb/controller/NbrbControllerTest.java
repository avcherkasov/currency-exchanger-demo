package io.github.avcherkasov.proxy.nbrb.controller;

import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.ProxyResult;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore("Only for debugging integration")
public class NbrbControllerTest {

    @Autowired
    private NbrbController controller;

    private Context context;

    @Before
    public void setUp() {
        context = new Context();
        context.setDateReq("05/04/2017");
        context.setOptions(Collections.emptyMap());
    }

    @Test
    public void testCurrencyRates() {
        ProxyResult proxyResult = controller.currencyRates("json", context);
        assertTrue(!proxyResult.getCurrencies().isEmpty());

        proxyResult = controller.currencyRates("soap", context);
        assertTrue(!proxyResult.getCurrencies().isEmpty());
    }

    @Test
    public void testCurrencyRatesUnsupportedMethod() {
        ProxyResult proxyResult = controller.currencyRates("bad_format", context);
        assertEquals("error", proxyResult.getError().getCode());
        assertEquals("Unsupported method", proxyResult.getError().getMessage());
    }

}
