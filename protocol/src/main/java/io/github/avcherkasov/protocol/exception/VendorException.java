package io.github.avcherkasov.protocol.exception;

/**
 * Handy class for wrapping runtime {@code Exceptions} with a root cause.
 *
 * @author Anatoly Cherkasov
 * @see #getMessage
 * @see #printStackTrace
 */
public class VendorException extends RuntimeException {

    /**
     * Constructs a new {@code VendorException} with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public VendorException() {
        super();
    }

    /**
     * Construct a new {@code VendorException} with the specified detail message.
     *
     * @param message the detail message
     */
    public VendorException(String message) {
        super(message);
    }

    /**
     * Construct a new {@code VendorException} with the cause.
     *
     * @param cause the root cause
     */
    public VendorException(Throwable cause) {
        super(cause);
    }

    /**
     * Construct a new {@code VendorException} with the
     * specified detail message and root cause.
     *
     * @param message the detail message
     * @param cause   the root cause
     */
    public VendorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Return the detail message, including the message from the nested exception
     * if there is one.
     */
    @Override
    public String getMessage() {
        return String.format(
                "Unknown error during request to vendor execution.%s%s",
                System.getProperty("line.separator"),
                getCause().getMessage()
        );
    }

}
