package io.github.avcherkasov.protocol.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Currency
 *
 * @author Anatoly Cherkasov
 *         <p>
 *         See {<a href="https://en.wikipedia.org/wiki/ISO_4217">ISO 4217</a>}
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    /**
     * Digital currency code
     */
    @JsonProperty("numCode")
    private String numCode;

    /**
     * Symbolic currency code
     */
    @JsonProperty("charCode")
    private String charCode;

    /**
     * Currency nominal
     */
    @JsonProperty("nominal")
    private Long nominal;

    /**
     * Currency name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Currency rate
     */
    @JsonProperty("rate")
    private BigDecimal rate;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link Currency Currency} instance.
     */
    public Currency() {
        // Constructs default a new {@link Currency Currency} instance.
    }

    /**
     * Constructs a new {@link Currency Currency} instance with the given
     * initial parameters to be constructed..
     *
     * @param numCode  the field's numCode (see {@link #numCode}).
     * @param charCode the field's charCode (see {@link #charCode}).
     * @param nominal  the field's nominal (see {@link #nominal}).
     * @param name     the field's name (see {@link #name}).
     * @param rate     the field's rate (see {@link #rate}).
     */
    public Currency(String numCode, String charCode, Long nominal, String name, BigDecimal rate) {
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.rate = rate;
    }

}
