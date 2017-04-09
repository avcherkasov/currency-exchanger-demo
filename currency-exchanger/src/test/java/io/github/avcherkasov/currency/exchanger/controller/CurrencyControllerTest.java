package io.github.avcherkasov.currency.exchanger.controller;

import io.github.avcherkasov.currency.exchanger.AbstractIntegrationTest;
import io.github.avcherkasov.currency.exchanger.entity.Currency;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Anatoly Cherkasov
 * @see Currency
 * @see CurrencyController
 * @see WebApplicationContext
 * @see MockMvc
 */
public class CurrencyControllerTest extends AbstractIntegrationTest {

    private final static String BASE_PATH = "/currency/v1/";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private CurrencyController currencyController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        // We have to reset our mock between tests because the mock objects
        // are managed by the Spring container. If we would not reset them,
        // stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(currencyController);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Handy class for test with {@link Currency}
     */
    private static class CurrencyHelper {

        final static Long RUB_ID = 1L;
        final static String RUB_CHAR_CODE = "RUB";
        final static String RUB_NUM_CODE = "643";
        final static String RUB_NAME = "Российский рубль";

        final static Long USD_ID = 2L;
        final static String USD_CHAR_CODE = "USD";
        final static String USD_NUM_CODE = "840";
        final static String USD_NAME = "Доллар США";

        static Currency makeCurrencyRUB() {
            return new Currency(RUB_ID, RUB_CHAR_CODE, RUB_NUM_CODE, RUB_NAME);
        }

        static Currency makeCurrencyUSD() {
            return new Currency(USD_ID, USD_CHAR_CODE, USD_NUM_CODE, USD_NAME);
        }

    }

    /**
     * Get currency by ID
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/currency/v1/id/1
     * <p>
     * Output:
     * <pre>
     * <code> {
     *      id: 1,
     *      charCode: "RUB",
     *      numCode: "643",
     *      name: "Российский рубль"
     * } <code>
     * </pre>
     * @throws Exception exception
     */
    @Test
    public void getById() throws Exception {
        mockMvc.perform(get(BASE_PATH + "id/{id}", CurrencyHelper.RUB_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.charCode", is(CurrencyHelper.RUB_CHAR_CODE)))
                .andExpect(jsonPath("$.numCode", is(CurrencyHelper.RUB_NUM_CODE)))
                .andExpect(jsonPath("$.name", is(CurrencyHelper.RUB_NAME)))
                .andDo(MockMvcResultHandlers.print())
        ;

        Currency currency = CurrencyHelper.makeCurrencyRUB();

        when(currencyController.getById(CurrencyHelper.RUB_ID)).thenReturn(currency);
        assertEquals(currency, currencyController.getById(CurrencyHelper.RUB_ID));

        Mockito.verify(currencyController, Mockito.times(1)).getById(CurrencyHelper.RUB_ID);
        Mockito.verifyNoMoreInteractions(currencyController);
    }

    /**
     * Get currency by char code
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/currency/v1/code/rub
     * <p>
     * Output:
     * <pre>
     * <code> {
     *      id: 1,
     *      charCode: "RUB",
     *      numCode: "643",
     *      name: "Российский рубль"
     * } <code>
     * </pre>
     * @throws Exception exception
     */
    @Test
    public void getByCharCode() throws Exception {
        mockMvc.perform(get(BASE_PATH + "code/{code}", CurrencyHelper.RUB_CHAR_CODE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.charCode", is(CurrencyHelper.RUB_CHAR_CODE)))
                .andExpect(jsonPath("$.numCode", is(CurrencyHelper.RUB_NUM_CODE)))
                .andExpect(jsonPath("$.name", is(CurrencyHelper.RUB_NAME)))
                .andDo(MockMvcResultHandlers.print())
        ;

        Currency currency = CurrencyHelper.makeCurrencyRUB();

        when(currencyController.getByCode(CurrencyHelper.RUB_CHAR_CODE)).thenReturn(currency);
        assertEquals(currency, currencyController.getByCode(CurrencyHelper.RUB_CHAR_CODE));

        Mockito.verify(currencyController, Mockito.times(1)).getByCode(CurrencyHelper.RUB_CHAR_CODE);
        Mockito.verifyNoMoreInteractions(currencyController);
    }

    /**
     * Get currency by num code
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/currency/v1/code/643
     * <p>
     * Output:
     * <pre>
     * <code> {
     *      id: 1,
     *      charCode: "RUB",
     *      numCode: "643",
     *      name: "Российский рубль"
     * } <code>
     * </pre>
     * @throws Exception exception
     */
    @Test
    public void getByNumCode() throws Exception {
        mockMvc.perform(get(BASE_PATH + "code/{code}", CurrencyHelper.USD_NUM_CODE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.charCode", is(CurrencyHelper.USD_CHAR_CODE)))
                .andExpect(jsonPath("$.numCode", is(CurrencyHelper.USD_NUM_CODE)))
                .andExpect(jsonPath("$.name", is(CurrencyHelper.USD_NAME)))
                .andDo(MockMvcResultHandlers.print())
        ;

        Currency currency = CurrencyHelper.makeCurrencyUSD();

        when(currencyController.getByCode(CurrencyHelper.USD_NUM_CODE)).thenReturn(currency);
        assertEquals(currency, currencyController.getByCode(CurrencyHelper.USD_NUM_CODE));

        Mockito.verify(currencyController, Mockito.times(1)).getByCode(CurrencyHelper.USD_NUM_CODE);
        Mockito.verifyNoMoreInteractions(currencyController);
    }

    /**
     * Get list of currencies
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/currency/v1/list
     * <p>
     * Output:
     * <pre>
     * <code> [
     *  {
     *      id: 1,
     *      charCode: "RUB",
     *      numCode: "643",
     *      name: "Российский рубль"
     *  },
     *  {
     *      id: 2,
     *      charCode: "USD",
     *      numCode: "840",
     *      name: "Доллар США"
     *  },
     *  {
     *      id: 3,
     *      charCode: "BYR",
     *      numCode: "974",
     *      name: "Белорусский рубль"
     *  },
     *  {
     *      id: 4,
     *      charCode: "EUR",
     *      numCode: "978",
     *      name: "Евро"
     *  },
     *  {
     *      id: 5,
     *      charCode: "BYN",
     *      numCode: "933",
     *      name: "Бело"
     *  }
     * ] <code>
     * </pre>
     * @throws Exception exception
     */
    @Test
    public void list() throws Exception {
        mockMvc.perform(get(BASE_PATH + "list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].id", Matchers.hasItem(1)))
                .andExpect(jsonPath("$[*].charCode", Matchers.hasItem(CurrencyHelper.RUB_CHAR_CODE)))
                .andExpect(jsonPath("$[*].numCode", Matchers.hasItem(CurrencyHelper.RUB_NUM_CODE)))
                .andDo(MockMvcResultHandlers.print())
        ;

        Currency rub = CurrencyHelper.makeCurrencyRUB();
        Currency usd = CurrencyHelper.makeCurrencyUSD();
        List<Currency> currencies = Arrays.asList(rub, usd);

        Mockito.when(currencyController.list()).thenReturn(currencies);
        assertEquals(currencies, currencyController.list());

        Mockito.verify(currencyController, Mockito.times(1)).list();
        Mockito.verifyNoMoreInteractions(currencyController);
    }

    /**
     * Get list of currencies with pagination
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/currency/v1/list/1/2
     * <p>
     * Output:
     * <pre>
     * <code> [
     *  {
     *      id: 1,
     *      charCode: "RUB",
     *      numCode: "643",
     *      name: "Российский рубль"
     *  },
     *  {
     *      id: 2,
     *      charCode: "USD",
     *      numCode: "840",
     *      name: "Доллар США"
     *  }
     * ] <code>
     * </pre>
     * @throws Exception exception
     */
    @Test
    public void listWithPagination() throws Exception {
        int page = 1;
        int limit = 2;

        mockMvc.perform(get(BASE_PATH + "list/{page}/{limit}", page, limit))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].id", Matchers.hasItem(1)))
                .andExpect(jsonPath("$[*].charCode", Matchers.hasItem(CurrencyHelper.RUB_CHAR_CODE)))
                .andExpect(jsonPath("$[*].numCode", Matchers.hasItem(CurrencyHelper.RUB_NUM_CODE)))
                .andDo(MockMvcResultHandlers.print())
        ;

        Currency rub = CurrencyHelper.makeCurrencyRUB();
        Currency usd = CurrencyHelper.makeCurrencyUSD();
        List<Currency> currencies = Arrays.asList(rub, usd);

        Mockito.when(currencyController.listWithPagination(page, limit)).thenReturn(currencies);
        assertEquals(currencies, currencyController.listWithPagination(page, limit));

        Mockito.verify(currencyController, Mockito.times(1)).listWithPagination(page, limit);
        Mockito.verifyNoMoreInteractions(currencyController);
    }

}
