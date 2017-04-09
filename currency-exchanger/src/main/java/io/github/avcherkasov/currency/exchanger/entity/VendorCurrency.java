package io.github.avcherkasov.currency.exchanger.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * Entity VendorCurrency. Contains information about the vendor and currency.
 *
 * @author Anatoly Cherkasov
 */
@Data
@Entity(name = "vendor_currency")
@Table(name = "vendor_currency")
public class VendorCurrency {

    /**
     * The unique identifier
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_vendor_currency_id_gen"
    )
    @SequenceGenerator(
            name = "seq_vendor_currency_id_gen",
            sequenceName = "seq_vendor_currency_id",
            allocationSize = 1
    )
    private Long id;

    /**
     * {@link Vendor Vendor} object
     */
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    /**
     * Source {@link Currency Currency} object
     */
    @ManyToOne
    @JoinColumn(name = "source_currency_id")
    private Currency sourceCurrency;

    /**
     * Target {@link Currency Currency} object
     */
    @ManyToOne
    @JoinColumn(name = "target_currency_id")
    private Currency targetCurrency;

    /**
     * Is active
     */
    private Boolean isActive;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link VendorCurrency VendorCurrency} instance.
     */
    public VendorCurrency() {
        // Constructs default a new {@link VendorCurrency VendorCurrency} instance.
    }

    /**
     * Constructs a new {@link VendorCurrency VendorCurrency} instance with the given
     * initial parameters to be constructed.
     *
     * @param id the field's id (see {@link #id}).
     */
    public VendorCurrency(Long id) {
        this.id = id;
    }

    /**
     * Constructs a new {@link VendorCurrency VendorCurrency} instance with the given
     * initial parameters to be constructed.
     *
     * @param vendor         the field's vendor (see {@link #vendor}).
     * @param sourceCurrency the field's sourceCurrency (see {@link #sourceCurrency}).
     * @param targetCurrency the field's targetCurrency (see {@link #targetCurrency}).
     * @param isActive       the field's isActive (see {@link #isActive}).
     */
    public VendorCurrency(Vendor vendor, Currency sourceCurrency, Currency targetCurrency, Boolean isActive) {
        this.vendor = vendor;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.isActive = isActive;
    }

    /**
     * Constructs a new {@link VendorCurrency VendorCurrency} instance with the given
     * initial parameters to be constructed.
     *
     * @param id             the field's id (see {@link #id}).
     * @param vendor         the field's vendor (see {@link #vendor}).
     * @param sourceCurrency the field's sourceCurrency (see {@link #sourceCurrency}).
     * @param targetCurrency the field's targetCurrency (see {@link #targetCurrency}).
     * @param isActive       the field's isActive (see {@link #isActive}).
     */
    public VendorCurrency(Long id, Vendor vendor, Currency sourceCurrency, Currency targetCurrency, Boolean isActive) {
        this.id = id;
        this.vendor = vendor;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.isActive = isActive;
    }

}
