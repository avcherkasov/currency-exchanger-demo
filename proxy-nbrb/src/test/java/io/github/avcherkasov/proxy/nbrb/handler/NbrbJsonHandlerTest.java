package io.github.avcherkasov.proxy.nbrb.handler;

import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.ProxyResult;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertTrue;

/**
 * @author Anatoly Cherkasov
 * @see Context
 * @see ProxyResult
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore("Only for debugging integration")
public class NbrbJsonHandlerTest {

    @Autowired
    private NbrbJsonHandler handler;

    @Test
    public void testProcessed() {
        Context context = new Context();
        context.setDateReq("05/04/2017");
        context.setOptions(Collections.emptyMap());

        ProxyResult proxyResult = handler.processed(context);
        assertTrue(!proxyResult.getCurrencies().isEmpty());
    }

}
