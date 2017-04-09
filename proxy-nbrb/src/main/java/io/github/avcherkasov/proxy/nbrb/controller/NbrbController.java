package io.github.avcherkasov.proxy.nbrb.controller;

import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.ProxyResult;
import io.github.avcherkasov.proxy.nbrb.handler.NbrbSoapHandler;
import io.github.avcherkasov.proxy.nbrb.handler.NbrbJsonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nbrb Controller
 *
 * @author Anatoly Cherkasov
 * @see io.github.avcherkasov.proxy.nbrb.handler.NbrbJsonHandler
 * @see io.github.avcherkasov.proxy.nbrb.handler.NbrbSoapHandler
 * @see ProxyResult
 */
@RestController
@RequestMapping(value = "currency_rates/v1")
public class NbrbController {

    private NbrbJsonHandler jsonHandler;
    private NbrbSoapHandler soapHandler;

    @Autowired
    public NbrbController(NbrbJsonHandler jsonHandler, NbrbSoapHandler soapHandler) {
        Assert.notNull(jsonHandler, "NbrbJsonHandler must not be null");
        Assert.notNull(soapHandler, "NbrbSoapHandler must not be null");

        this.jsonHandler = jsonHandler;
        this.soapHandler = soapHandler;
    }

    /**
     * Get currency rates (json)
     *
     * @param context {@link Context Context}
     * @return ProxyResult {@link ProxyResult ProxyResult}
     */
    @RequestMapping(value = "json", method = RequestMethod.POST)
    public ProxyResult currencyRatesXml(@RequestBody Context context) {
        return jsonHandler.processed(context);
    }

    /**
     * Get currency rates (soap)
     *
     * @param context {@link Context Context}
     * @return ProxyResult {@link ProxyResult ProxyResult}
     */
    @RequestMapping(value = "soap", method = RequestMethod.POST)
    public ProxyResult currencyRatesSoap(@RequestBody Context context) {
        return soapHandler.processed(context);
    }

}
