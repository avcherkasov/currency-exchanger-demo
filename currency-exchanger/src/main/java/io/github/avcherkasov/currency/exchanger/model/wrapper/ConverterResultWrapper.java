package io.github.avcherkasov.currency.exchanger.model.wrapper;

import io.github.avcherkasov.currency.exchanger.model.ConverterResult;

/**
 * Handy class for wrapping {@link ConverterResult}
 *
 * @author Anatoly Cherkasov
 * @see ConverterResult
 */
public class ConverterResultWrapper {

    /**
     * Make error with code and detail message
     *
     * @param code    error code
     * @param message error detail message
     * @return ConverterResult {@link ConverterResult}
     */
    public static ConverterResult makeError(final String code, final String message) {
        ConverterResult converterResult = new ConverterResult();
        converterResult.setError(new io.github.avcherkasov.protocol.model.Error(code, message));
        return converterResult;
    }

}
