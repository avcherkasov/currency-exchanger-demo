package io.github.avcherkasov.protocol.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Data set for interaction with the vendor proxy.
 *
 * @author Anatoly Cherkasov
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Context {

    /**
     * Request for date (e.g. "02/03/2017")
     */
    @JsonProperty("date_req")
    private String dateReq;

    /**
     * The Map of options, with attribute names as keys and
     * corresponding attribute values as values (never {@code null})
     */
    @JsonProperty("options")
    private Map options;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link Context Context} object.
     */
    public Context() {
        // Constructs default a new {@link Context Context} instance.
    }

    /**
     * Constructs a new {@link Context Context} with the supplied settings.
     *
     * @param dateReq the field's dataReq (see {@link #dateReq}).
     * @param options the field's options (see {@link #options}).
     */
    public Context(String dateReq, Map options) {
        this.dateReq = dateReq;
        this.options = options;
    }

}
