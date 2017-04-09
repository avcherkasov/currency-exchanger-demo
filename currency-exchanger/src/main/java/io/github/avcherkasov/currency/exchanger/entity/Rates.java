package io.github.avcherkasov.currency.exchanger.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity Rates. Contains information about the rates object.
 *
 * @author Anatoly Cherkasov
 */
@Data
@Entity(name = "rates")
@Table(name = "rates")
public class Rates {

    /**
     * The unique identifier
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_rates_id_gen"
    )
    @SequenceGenerator(
            name = "seq_rates_id_gen",
            sequenceName = "seq_rates_id",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_currency_id")
    private VendorCurrency vendorCurrency;

    /**
     * Currency nominal
     */
    private Long nominal;

    /**
     * Currency rate
     */
    private Double rate;

    @Temporal(TemporalType.DATE)
    @Column(name = "rate_date", insertable = true, updatable = false)
    private Date rateDate;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link Rates Rates} instance.
     */
    public Rates() {
        // Constructs default a new {@link Rates Rates} instance.
    }

    /**
     * Constructs a new {@link Rates Rates} instance with the given
     * initial parameters to be constructed.
     * <p>
     * with the supplied settings
     *
     * @param vendorCurrency the field's vendorCurrency (see {@link #vendorCurrency}).
     * @param nominal        the field's nominal (see {@link #nominal}).
     * @param rate           the field's rate (see {@link #rate}).
     * @param rateDate       the field's rateDate (see {@link #rateDate}).
     */
    public Rates(VendorCurrency vendorCurrency, Long nominal, Double rate, Date rateDate) {
        this.vendorCurrency = vendorCurrency;
        this.nominal = nominal;
        this.rate = rate;
        this.rateDate = rateDate;
    }

    /**
     * Constructs a new {@link Rates Rates} instance with the given
     * initial parameters to be constructed.
     *
     * @param id             the field's id (see {@link #id}).
     * @param vendorCurrency the field's vendorCurrency (see {@link #vendorCurrency}).
     * @param nominal        the field's nominal (see {@link #nominal}).
     * @param rate           the field's rate (see {@link #rate}).
     * @param rateDate       the field's rateDate (see {@link #rateDate}).
     */
    public Rates(Long id, VendorCurrency vendorCurrency, Long nominal, Double rate, Date rateDate) {
        this.id = id;
        this.vendorCurrency = vendorCurrency;
        this.nominal = nominal;
        this.rate = rate;
        this.rateDate = rateDate;
    }

    // ------------------------------------------------------------------------
    // Other public methods
    // ------------------------------------------------------------------------

    /**
     * Get currency with nominal
     * e.g. 58.3776 * 2 -> 116.7552
     *
     * @return Double
     */
    public Double getCurrencyWithNominal() {
        return rate * nominal;
    }

}
