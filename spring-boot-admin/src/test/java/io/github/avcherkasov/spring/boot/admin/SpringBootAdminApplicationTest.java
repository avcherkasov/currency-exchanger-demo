package io.github.avcherkasov.spring.boot.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Anatoly Cherkasov
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringBootAdminApplicationTest {

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void testContextLoads() throws Exception {
        assertNotNull(this.ctx);
        assertTrue(this.ctx.containsBean("springBootAdminApplication"));
    }
}
