package io.github.avcherkasov.proxy.nbrb.handler;

import io.github.avcherkasov.protocol.Proxy;
import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.ProxyResult;
import io.github.avcherkasov.protocol.wrapper.ProxyResultWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Proxy NBRB Unsupported Handler
 *
 * @author Anatoly Cherkasov
 * @see <a href="https://www.nbrb.by/APIHelp/ExRates">Technical resources</a>
 * @see <a href="https://www.nbrb.by/statistics/Rates/WebService/?what=1">SOAP API</a>
 * @see Proxy
 * @see ProxyResult
 */
@Component
public class NbrbUnsupportedHandler implements NbrbHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbrbUnsupportedHandler.class);

    // ------------------------------------------------------------------------
    // Overridden interface methods
    // ------------------------------------------------------------------------

    @Override
    public boolean filter(String format) {
        return false;
    }

    @Override
    public ProxyResult accept(Context context) {
        ProxyResult proxyResult = ProxyResultWrapper.makeError("error", "Unsupported method");
        LOGGER.error("Error unsupported method: proxyResult {}", proxyResult);
        return proxyResult;
    }

}
