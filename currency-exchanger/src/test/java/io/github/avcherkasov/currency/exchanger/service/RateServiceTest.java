package io.github.avcherkasov.currency.exchanger.service;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author Anatoly Cherkasov
 * @see RateService
 */
public class RateServiceTest {

    @Test
    public void testExistenceService() {
        String simpleName = getClass().getSimpleName().replace("Test","");
        try {
            Class.forName(getClass().getPackage().getName() + "." + simpleName);
            assertTrue(true);
        } catch (final ClassNotFoundException e) {
            assertTrue(false);
        }
    }

}
