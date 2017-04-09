package io.github.avcherkasov.protocol.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * The result of accessing the vendor proxy within the session.
 *
 * @author Anatoly Cherkasov
 * @see Currency
 * @see Error
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProxyResult {

    /**
     * List of {@link Currency Currency} (Can by {@code null})
     */
    private List<Currency> currencies;

    /**
     * Given {@link Error Error} instance (Can by {@code null})
     */
    private Error error;

}
