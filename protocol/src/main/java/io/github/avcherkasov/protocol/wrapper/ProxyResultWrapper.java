package io.github.avcherkasov.protocol.wrapper;

import io.github.avcherkasov.protocol.model.Currency;
import io.github.avcherkasov.protocol.model.Error;
import io.github.avcherkasov.protocol.model.ProxyResult;

import java.util.List;

/**
 * Handy class for wrapping {@link ProxyResult}
 *
 * @author Anatoly Cherkasov
 * @see ProxyResult
 */
public class ProxyResultWrapper {

    /**
     * Make error with code and detail message
     *
     * @param code    error code
     * @param message error detail message
     * @return ProxyResult {@link ProxyResult}
     */
    public static ProxyResult makeError(final String code, final String message) {
        ProxyResult proxyResult = new ProxyResult();
        proxyResult.setError(new Error(code, message));
        return proxyResult;
    }

    /**
     * Make list currency
     *
     * @param currencies list of {@link Currency} or {@code null} if not a present
     * @return ProxyResult {@link ProxyResult}
     */
    public static ProxyResult makeListCurrency(final List<Currency> currencies) {
        ProxyResult proxyResult = new ProxyResult();
        proxyResult.setCurrencies(currencies);
        return proxyResult;
    }

}
