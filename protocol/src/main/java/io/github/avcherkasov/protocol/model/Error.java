package io.github.avcherkasov.protocol.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Error
 *
 * @author Anatoly Cherkasov
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {

    /**
     * Code
     */
    @JsonProperty("code")
    private String code;

    /**
     * Detail message
     */
    @JsonProperty("message")
    private String message;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link Error Error} instance.
     */
    public Error() {
        // Constructs default a new {@link Error Error} instance.
    }

    /**
     * Constructs a new {@link Error Error} instance with the given initial
     * code and message to be constructed.
     *
     * @param code    the field's code (see {@link #code}).
     * @param message the field's message (see {@link #message}).
     */
    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
