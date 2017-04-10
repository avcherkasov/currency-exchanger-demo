package io.github.avcherkasov.proxy.nbrb.handler;

import by.nbrb.ExRates;
import by.nbrb.ExRatesDailyResponse;
import by.nbrb.ExRatesSoap;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import io.github.avcherkasov.protocol.Proxy;
import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.Currency;
import io.github.avcherkasov.protocol.model.ProxyResult;
import io.github.avcherkasov.protocol.wrapper.ProxyResultWrapper;
import io.github.avcherkasov.proxy.nbrb.model.soap.NewDataSet;
import io.github.avcherkasov.proxy.nbrb.utils.Converter;
import io.github.avcherkasov.proxy.nbrb.utils.CustomDateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Proxy NBRB Handler
 *
 * @author Anatoly Cherkasov
 * @see <a href="https://www.nbrb.by/APIHelp/ExRates">Technical resources</a>
 * @see <a href="https://www.nbrb.by/statistics/Rates/WebService/?what=1">SOAP API</a>
 * @see Proxy
 * @see ProxyResult
 */
@Component
public class NbrbSoapHandler implements Proxy, NbrbHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbrbSoapHandler.class);

    // ------------------------------------------------------------------------
    // Overridden interface methods
    // ------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean filter(String format) {
        return "soap".equalsIgnoreCase(format);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxyResult accept(Context context) {
        return processed(context);
    }

    /**
     * When debugging, use the date:
     * XMLGregorianCalendar calendar = exRatesSoap.lastDailyExRatesDate();
     *
     * {@inheritDoc}
     */
    @Override
    public ProxyResult processed(Context context) {
        ExRatesSoap exRatesSoap = new ExRates().getExRatesSoap();
        List<NewDataSet.DailyExRatesOnDate> dailyExRatesOnDateList = Collections.emptyList();

        try {
            XMLGregorianCalendar calendar = CustomDateUtils.stringToXMLGregorianCalendar("04/04/2017");
            ExRatesDailyResponse.ExRatesDailyResult result = exRatesSoap.exRatesDaily(calendar);
            Node newDataSet = ((ElementNSImpl) result.getAny()).getFirstChild();
            dailyExRatesOnDateList = NbrbSoapHandler.nodeToListObject(newDataSet);
        } catch (Exception e) {
            LOGGER.error("" + e.getMessage());
            ProxyResultWrapper.makeError("error", e.getMessage());
        }

        ProxyResult proxyResult = new ProxyResult();
        if (!dailyExRatesOnDateList.isEmpty()) {
            List<Currency> currencies = dailyExRatesOnDateList.stream()
                    .parallel().map(Converter.mapToCurrency)
                    .collect(Collectors.toList());
            proxyResult.setCurrencies(currencies);
        }
        LOGGER.info("CurrencyRates. finish {}", proxyResult);
        return proxyResult;
    }

    // ------------------------------------------------------------------------
    // Other supporting methods
    // ------------------------------------------------------------------------

    /**
     * Mapping node to list Object
     *
     * @param result {@link org.w3c.dom.Node}
     * @return list of {@link NewDataSet.DailyExRatesOnDate}
     * @throws JAXBException exception
     */
    private static List<NewDataSet.DailyExRatesOnDate> nodeToListObject(org.w3c.dom.Node result) throws JAXBException {
        JAXBContext context1 = JAXBContext.newInstance(NewDataSet.class);
        Unmarshaller unMarshaller = context1.createUnmarshaller();
        NewDataSet newDataSet = (NewDataSet) unMarshaller.unmarshal(result);
        return newDataSet.getDailyExRatesOnDate();
    }

}
