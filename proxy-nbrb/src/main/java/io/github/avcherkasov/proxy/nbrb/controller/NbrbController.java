package io.github.avcherkasov.proxy.nbrb.controller;


import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.ProxyResult;
import io.github.avcherkasov.proxy.nbrb.handler.NbrbHandler;
import io.github.avcherkasov.proxy.nbrb.handler.NbrbUnsupportedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Nbrb Controller
 *
 * @author Anatoly Cherkasov
 * @see io.github.avcherkasov.proxy.nbrb.handler.NbrbHandler
 * @see io.github.avcherkasov.proxy.nbrb.handler.NbrbSoapHandler
 * @see ProxyResult
 */
@RestController
@RequestMapping(value = "currency_rates/v1")
public class NbrbController {

    private List<NbrbHandler> handlers;

    private NbrbUnsupportedHandler unsupportedHandler;

    @Autowired
    public NbrbController(List<NbrbHandler> handlers, NbrbUnsupportedHandler unsupportedHandler) {
        Assert.notNull(handlers, "handlers must not be null");
        Assert.notNull(unsupportedHandler, "unsupportedHandler must not be null");

        this.handlers = handlers;
        this.unsupportedHandler = unsupportedHandler;
    }

    /**
     * Get currency rates
     *
     * @param format  e.g. json, soap, xml
     * @param context {@link Context Context}
     * @return ProxyResult {@link ProxyResult ProxyResult}
     */
    @RequestMapping(value = "{format}", method = RequestMethod.POST)
    public ProxyResult currencyRates(
            @PathVariable("format") String format,
            @RequestBody Context context
    ) {
        return handlers.stream()
                .filter(handler -> handler.filter(format))
                .findFirst()
                .orElse(unsupportedHandler)
                .accept(context);
    }

}
