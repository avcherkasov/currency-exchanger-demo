package io.github.avcherkasov.proxy.cbr.controller;

import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.ProxyResult;
import io.github.avcherkasov.proxy.cbr.handler.CbrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cbr Controller
 *
 * @author Anatoly Cherkasov
 * @see CbrHandler
 * @see ProxyResult
 */
@RestController
public class CbrController {

    private CbrHandler handler;

    @Autowired
    public CbrController(CbrHandler handler) {
        Assert.notNull(handler, "CbrHandler must not be null");

        this.handler = handler;
    }

    /**
     * Get currency rates
     *
     * @param context {@link Context Context}
     * @return ProxyResult {@link ProxyResult ProxyResult}
     */
    @RequestMapping(value = "currency_rates", method = RequestMethod.POST)
    public synchronized ProxyResult currencyRates(@RequestBody Context context) {
        return handler.processed(context);
    }

}
