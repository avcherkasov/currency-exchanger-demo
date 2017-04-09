package io.github.avcherkasov.protocol;

import io.github.avcherkasov.protocol.exception.VendorException;
import io.github.avcherkasov.protocol.model.Context;
import io.github.avcherkasov.protocol.model.ProxyResult;

/**
 * The interface defining the general interaction with the vendor proxy
 *
 * @author Anatoly Cherkasov
 */
public interface Proxy {

    /**
     * Currency Rates
     *
     * @param context the field's context {@link Context}
     * @return ProxyResult Return the {@link ProxyResult}
     * @throws VendorException unless otherwise provided
     */
    ProxyResult processed(Context context) throws VendorException;
}
