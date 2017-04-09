package io.github.avcherkasov.currency.exchanger.controller;


import io.github.avcherkasov.currency.exchanger.AbstractIntegrationTest;
import io.github.avcherkasov.currency.exchanger.ConverterRequest;
import io.github.avcherkasov.currency.exchanger.utils.ConverterRequestUtils;
import io.github.avcherkasov.currency.exchanger.utils.ErrorMapping;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Anatoly Cherkasov
 * @see io.github.avcherkasov.currency.exchanger.service.RateService
 * @see io.github.avcherkasov.currency.exchanger.service.VendorService
 * @see io.github.avcherkasov.currency.exchanger.model.ConverterResult
 */
public class ConverterControllerTest extends AbstractIntegrationTest {

    private final static String BASE_PATH = "/converter/v1/";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private ConverterController converterController;

    private MockMvc mockMvc;

    private Resource fixtureConverterRequest;

    @Before
    public void setUp() {
        // We have to reset our mock between tests because the mock objects
        // are managed by the Spring container. If we would not reset them,
        // stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(converterController);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        fixtureConverterRequest = new ClassPathResource("fixture/converter_request.csv");
    }

    /**
     * Convert
     * <p>
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/converter/v1/cbr/usd/eur/2017-03-21/2
     * <p>
     * Output variants:
     * <p>
     * ==================================
     * <pre>
     * <code> {
     *      vendor: "Центральный Банк России",
     *      from: "USD",
     *      to: "RUB",
     *      rate: 116.7552,
     *      date: 1016-01-21,
     *      count: 2
     * } <code>
     * </pre>
     * ==================================
     * <pre>
     * <code> {
     *      vendor: "Центральный Банк России",
     *      from: "RUB",
     *      to: "RUB",
     *      rate: 4,
     *      date: 1016-01-21,
     *      count: 2
     * } <code>
     * </pre>
     * ==================================
     * <pre>
     * <code> {
     *      vendor: "Центральный Банк России",
     *      from: "RUB",
     *      to: "USD",
     *      rate: 0.03425971605547333,
     *      date: 1016-01-21,
     *      count: 2
     * } <code>
     * </pre>
     * ==================================
     * <pre>
     * <code> {
     *      vendor: "Центральный Банк России",
     *      from: "EUR",
     *      to: "USD",
     *      rate: 2.108401167571123,
     *      date: 1016-01-21,
     *      count: 2
     * } <code>
     * </pre>
     * ==================================
     * <pre>
     * <code> {
     *      vendor: "Центральный Банк России",
     *      from: "USD",
     *      to: "EUR",
     *      rate: 1.8971721613150108,
     *      date: 1016-01-21,
     *      count: 2
     * } <code>
     * </pre>
     */
    @Test
    @Sql("classpath:data/sql/rates_by_code_currency.sql")
    public void convert() throws Exception {

        List<ConverterRequest> results = ConverterRequestUtils
                .getConverterRequestFromFile(
                        fixtureConverterRequest.getInputStream()
                );

        results.stream().forEach(result -> {
            try {
                RequestBuilder requestBuilder = get(BASE_PATH + "{vendor}/{from}/{to}/{date_request}/{count}",
                        result.getVendor(),
                        result.getFrom(),
                        result.getTo(),
                        result.getDateReq(),
                        result.getCount()
                );

                System.out.println(requestBuilder);
                mockMvc.perform(requestBuilder)
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.from", is(result.getFrom())))
                        .andExpect(jsonPath("$.to", is(result.getTo())))
                        .andExpect(jsonPath("$.count", is(result.getCount())))
                        .andExpect(jsonPath("$.date", is(result.getDateReq())))
                        .andExpect(jsonPath("$.rate").value(result.getRate()))
                        .andDo(MockMvcResultHandlers.print())
                ;
            } catch (Exception e) {
                Assert.assertTrue(false);
            }
        });

    }


    /**
     * Get error while converting
     * <p>
     * Input:
     * <p>
     * e.g. http://{host}:{port}/{path}/{version}/cbr/wtf/bad/2017-03-21/100
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
     */
    @Test
    public void convertWithError() throws Exception {
        mockMvc.perform(get(BASE_PATH + "cbr/wtf/bad/2017-03-21/100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error.code", Matchers.is(ErrorMapping.RECORD_NOT_FOUND)))
                .andExpect(jsonPath("$.error.message", Matchers.is(ErrorMapping.RECORD_NOT_FOUND)))
                .andDo(MockMvcResultHandlers.print())
        ;
    }

}
