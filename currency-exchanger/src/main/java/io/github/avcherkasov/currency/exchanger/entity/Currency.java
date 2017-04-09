package io.github.avcherkasov.currency.exchanger.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Entity currency. Contains information about the currency object.
 *
 * @author Anatoly Cherkasov
 */
@Data
@Entity(name = "currency")
@Table(name = "currency")
public class Currency {

    /**
     * The unique identifier
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_currency_id_gen"
    )
    @SequenceGenerator(
            name = "seq_currency_id_gen",
            sequenceName = "seq_currency_id",
            allocationSize = 1
    )
    private Long id;

    /**
     * Symbolic currency code
     */
    private String charCode;

    /**
     * Digital currency code
     */
    private String numCode;

    /**
     * Currency name
     */
    private String name;


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
     * initial parameters to be constructed.
     *
     * @param id the field's id (see {@link #id}).
     */
    public Currency(Long id) {
        this.id = id;
    }

    /**
     * Constructs a new {@link Currency Currency} instance with the given
     * initial parameters to be constructed.
     *
     * @param id       the field's id (see {@link #id}).
     * @param charCode the field's charCode (see {@link #charCode}).
     * @param numCode  the field's numCode (see {@link #numCode}).
     * @param name     the field's name (see {@link #name}).
     */
    public Currency(Long id, String charCode, String numCode, String name) {
        this.id = id;
        this.charCode = charCode;
        this.numCode = numCode;
        this.name = name;
    }

    // ------------------------------------------------------------------------
    // Other public methods
    // ------------------------------------------------------------------------

    /**
     * Currency is equals to code
     *
     * @param code {@link Currency#getCharCode()} or {@link Currency#getNumCode()}
     * @return boolean
     */
    public boolean сurrencyIsEqualToCode(String code) {
        return getCharCode().equalsIgnoreCase(code) || getNumCode().equals(code);
    }

}
