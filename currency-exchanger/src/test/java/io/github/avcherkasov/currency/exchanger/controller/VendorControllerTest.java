package io.github.avcherkasov.currency.exchanger.controller;

import io.github.avcherkasov.currency.exchanger.AbstractIntegrationTest;
import io.github.avcherkasov.currency.exchanger.entity.Vendor;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Anatoly Cherkasov
 * @see Vendor
 * @see VendorController
 * @see WebApplicationContext
 * @see MockMvc
 */
public class VendorControllerTest extends AbstractIntegrationTest {

    private final static String BASE_PATH = "/vendor/v1/";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private VendorController vendorController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Throwable {
        // We have to reset our mock between tests because the mock objects
        // are managed by the Spring container. If we would not reset them,
        // stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(vendorController);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Handy class for test with {@link Vendor}
     */
    private static class VendorHelper {

        final static String NAME = "cbr";

        static Vendor makeVendor() {
            Vendor vendor = new Vendor();
            vendor.setName(NAME);
            return vendor;
        }

    }

    /**
     * Get list of vendor
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/vendor/v1/list
     * <p>
     * Output:
     * <pre>
     * <code> [
     *  {
     *      id: 1,
     *      name: "cbr",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Центральный Банк России",
     *      additional: "{"param":"params-1","test":"test_2"}",
     *      active: true
     *  },
     *  {
     *      id: 2,
     *      name: "test vendor",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Test vendor",
     *      additional: "{"":""}",
     *      active: false
     *  }
     * ] <code>
     * </pre>
     *
     * @throws Exception exception
     */
    @Test
    @Sql("classpath:data/sql/vendor_list.sql")
    public void listTest() throws Exception {
        mockMvc.perform(get(BASE_PATH + "list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].name", Matchers.hasItem("test")))
                .andDo(MockMvcResultHandlers.print())
        ;

        List<Vendor> vendors = Arrays.asList(VendorHelper.makeVendor());

        Mockito.when(vendorController.list()).thenReturn(vendors);
        assertEquals(vendors, vendorController.list());

        Mockito.verify(vendorController, Mockito.times(1)).list();
        Mockito.verifyNoMoreInteractions(vendorController);
    }

    /**
     * Get list of vendor
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/vendor/v1/list
     * <p>
     * Output:
     * <pre>
     * <code> [
     *  {
     *      id: 1,
     *      name: "cbr",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Центральный Банк России",
     *      additional: "{"param":"params-1","test":"test_2"}",
     *      active: true
     *  },
     *  {
     *      id: 2,
     *      name: "test vendor",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Test vendor",
     *      additional: "{"":""}",
     *      active: false
     *  }
     * ] <code>
     * </pre>
     *
     * @throws Exception exception
     */
    @Test
    public void list() throws Exception {
        mockMvc.perform(get(BASE_PATH + "list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].name", Matchers.hasItem(VendorHelper.NAME)))
                .andDo(MockMvcResultHandlers.print())
        ;

        List<Vendor> vendors = Arrays.asList(VendorHelper.makeVendor());

        Mockito.when(vendorController.list()).thenReturn(vendors);
        assertEquals(vendors, vendorController.list());

        Mockito.verify(vendorController, Mockito.times(1)).list();
        Mockito.verifyNoMoreInteractions(vendorController);
    }

    /**
     * Get list of vendor
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/vendor/v1/list/1/2
     * <p>
     * Output:
     * <pre>
     * <code> [
     *  {
     *      id: 1,
     *      name: "cbr",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Центральный Банк России",
     *      additional: "{"param":"params-1","test":"test_2"}",
     *      active: true
     *  },
     *  {
     *      id: 2,
     *      name: "test vendor",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Test vendor",
     *      additional: "{"":""}",
     *      active: false
     *  }
     * ] <code>
     * </pre>
     *
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
                .andExpect(jsonPath("$[*].name", Matchers.hasItem(VendorHelper.NAME)))
                .andDo(MockMvcResultHandlers.print())
        ;

        List<Vendor> vendors = Arrays.asList(VendorHelper.makeVendor());

        Mockito.when(vendorController.listWithPagination(page, limit)).thenReturn(vendors);
        assertEquals(vendors, vendorController.listWithPagination(page, limit));

        Mockito.verify(vendorController, Mockito.times(1)).listWithPagination(page, limit);
        Mockito.verifyNoMoreInteractions(vendorController);
    }

}
