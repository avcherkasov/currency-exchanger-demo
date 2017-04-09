package io.github.avcherkasov.currency.exchanger.handler;

import io.github.avcherkasov.currency.exchanger.utils.ErrorMapping;
import io.github.avcherkasov.protocol.model.ProxyResult;
import io.github.avcherkasov.protocol.wrapper.ProxyResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

/**
 * Handles all exceptions for the controllers
 * <p>
 *
 * @author Anatoly Cherkasov
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    // --------------------------------------------------------------
    // Constants
    // --------------------------------------------------------------

    private static final String ERROR_CODE = "error";

    // ------------------------------------------------------------------------
    // Exception handlers
    // ------------------------------------------------------------------------

    /**
     * Convert a predefined exception to an HTTP Status code
     *
     * @param exception {@link IllegalArgumentException}
     * @return ProxyResult {@link ProxyResult} object with a http status
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ProxyResult conflict(Exception exception) {
        LOGGER.error(ErrorMapping.ILLEGAL_ARGUMENT, exception);
        return ProxyResultWrapper.makeError(
                ERROR_CODE, replaceQuotationMarks(exception.getMessage())
        );
    }

    /**
     * Convert a predefined exception to an HTTP Status code
     *
     * @param exception {@link ConnectException}
     * @return ProxyResult {@link ProxyResult} object with a http status
     */
    @ResponseStatus(value = HttpStatus.GONE)
    @ExceptionHandler(ConnectException.class)
    public ProxyResult connect(Exception exception) {
        LOGGER.error(ErrorMapping.CONNECT_REFUSED, exception);
        return ProxyResultWrapper.makeError(
                ERROR_CODE, replaceQuotationMarks(exception.getMessage())
        );
    }

    // ------------------------------------------------------------------------
    // Other methods
    // ------------------------------------------------------------------------

    /**
     * Replace quotation marks in string
     * e.g. The "some" text -> The 'some' text
     *
     * @param str input string
     * @return String
     */
    private static String replaceQuotationMarks(String str) {
        return str.replace("\"", "'");
    }

}
