package io.github.avcherkasov.proxy.nbrb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * @author Anatoly Cherkasov
 * @see ApplicationContext
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProxyNbrbApplicationTest {

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void testContextLoads() throws Exception {
        assertNotNull(this.ctx);
        assertTrue(this.ctx.containsBean("proxyNbrbApplication"));
    }

}
