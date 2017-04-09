package io.github.avcherkasov.currency.exchanger;

import io.github.avcherkasov.currency.exchanger.entity.Currency;
import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import lombok.Data;

/**
 * @see Vendor
 * @see Currency
 */
@Data
public class ConverterRequest {

    /**
     * {@link Vendor#getName()}
     */
    String vendor;

    /**
     * From currency {@link Currency#getCharCode()} or {@link Currency#getNumCode()}
     */
    private String from;

    /**
     * To currency {@link Currency#getCharCode()} or {@link Currency#getNumCode()}
     */
    private String to;

    /**
     * Rate
     */
    Double rate;

    /**
     * Date request
     */
    String dateReq;

    /**
     * Count of convertible currency
     */
    int count;


    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link ConverterRequest ConverterRequest} instance.
     */
    public ConverterRequest() {
        // Constructs default a new {@link ConverterRequest ConverterRequest} instance.
    }

    /**
     * Constructs a new {@link ConverterRequest ConverterRequest} instance with the given
     * initial parameters to be constructed.
     *
     * @param vendor  the field's vendor (see {@link #vendor}).
     * @param from    the field's from (see {@link #from}).
     * @param to      the field's to (see {@link #to}).
     * @param rate    the field's rate (see {@link #rate}).
     * @param dateReq the field's dateReq (see {@link #dateReq}).
     * @param count   the field's count (see {@link #count}).
     */
    public ConverterRequest(String vendor, String from, String to, Double rate, String dateReq, int count) {
        this.vendor = vendor;
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.dateReq = dateReq;
        this.count = count;
    }

}
