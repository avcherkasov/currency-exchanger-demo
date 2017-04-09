package io.github.avcherkasov.currency.exchanger.controller;

import io.github.avcherkasov.currency.exchanger.AbstractIntegrationTest;
import io.github.avcherkasov.currency.exchanger.utils.ErrorMapping;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Anatoly Cherkasov
 * @see RatesController
 * @see WebApplicationContext
 * @see MockMvc
 */
public class RatesControllerTest extends AbstractIntegrationTest {

    private final static String BASE_PATH = "/rates/v1/";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private RatesController ratesController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        // We have to reset our mock between tests because the mock objects
        // are managed by the Spring container. If we would not reset them,
        // stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(ratesController);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Get error rates by code currency
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/rates/v1/bad/wht
     * <p>
     * Output:
     * <pre>
     * <code> {
     *      error: {
     *          code: "Record not found",
     *          message: "Record not found"
     *      }
     * } <code>
     * </pre>
     * @throws Exception exception
     */
    @Test
    public void getErrorRatesByCodeCurrency() throws Exception {
        mockMvc.perform(get(BASE_PATH + "{vendor}/{currency}", "bad", "wht"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error.code", is(ErrorMapping.RECORD_NOT_FOUND)))
                .andExpect(jsonPath("$.error.message", is(ErrorMapping.RECORD_NOT_FOUND)))
                .andDo(MockMvcResultHandlers.print())
        ;
    }

    /**
     * Get rates by code currency
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/rates/v1/cbr/usd?date=1016-01-21
     * <p>
     * Output:
     * <pre>
     * <code> {
     *      date: "2017-03-21",
     *      vendor: "Центральный Банк России",
     *      currencies: [
     *          {
     *              numCode: "840",
     *              charCode: "USD",
     *              nominal: 1,
     *              name: "Доллар США",
     *              rate: 58.3776
     *          }
     *      ]
     * } <code>
     * </pre>
     *
     * @throws Exception exception
     */
    @Test
    @Sql("classpath:data/sql/rates_by_code_currency.sql")
    public void getRatesByCodeCurrency() throws Exception {
        mockMvc.perform(get(BASE_PATH + "{vendor}/{currency}?date=1016-01-21", "cbr", "usd"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.currencies[*].charCode", Matchers.hasItem("USD")))
                .andExpect(jsonPath("$.currencies[*].numCode", Matchers.hasItem("840")))
                .andExpect(jsonPath("$.currencies[*].nominal", Matchers.hasItem(1)))
                .andDo(MockMvcResultHandlers.print())
        ;
    }

}
