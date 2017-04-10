package io.github.avcherkasov.proxy.nbrb.handler;

import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.ProxyResult;

/**
 * Interface for convenient interaction with handlers
 *
 * @author Anatoly Cherkasov
 */
public interface NbrbHandler {

    /**
     * The filter to select is a handler class
     *
     * @param format Type of message participating in the filter
     * @return boolean
     */
    boolean filter(String format);

    /**
     * The method called after successful filtering for working out the exchange rate
     *
     * @param context the field's context {@link Context}
     * @return ProxyResult Return the {@link ProxyResult}
     */
    ProxyResult accept(Context context);

}
