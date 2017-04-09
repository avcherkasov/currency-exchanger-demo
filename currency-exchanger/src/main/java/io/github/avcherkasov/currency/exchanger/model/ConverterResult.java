package io.github.avcherkasov.currency.exchanger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import io.github.avcherkasov.currency.exchanger.utils.serializer.CustomDateSerializer;
import io.github.avcherkasov.protocol.model.Error;
import lombok.Data;

import java.util.Date;


/**
 * Converter Result
 *
 * @author Anatoly Cherkasov
 * @see Vendor#getFullName()
 * @see Error
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConverterResult {

    /**
     * {@link Vendor#getFullName()}
     */
    private String vendor;

    /**
     * From currency
     */
    private String from;

    /**
     * To currency
     */
    private String to;

    /**
     * Currency rate
     */
    private Double rate;

    /**
     * Date
     */
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date date;

    /**
     * Count currency
     */
    private int count;

    /**
     * {@link io.github.avcherkasov.protocol.model.Error}
     */
    io.github.avcherkasov.protocol.model.Error error;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link ConverterResult ConverterResult} instance.
     */
    public ConverterResult() {
        // Constructs default a new {@link ConverterResult ConverterResult} instance.
    }

    /**
     * Constructs a new {@link ConverterResult ConverterResult} instance with the given
     * initial parameters to be constructed.
     *
     * @param vendor the field's vendor (see {@link #vendor}).
     * @param from   the field's from (see {@link #from}).
     * @param to     the field's to (see {@link #to}).
     * @param rate   the field's rate (see {@link #rate}).
     * @param date   the field's date (see {@link #date}).
     * @param count  the field's count (see {@link #count}).
     */
    public ConverterResult(String vendor, String from, String to, Double rate, Date date, int count) {
        this.vendor = vendor;
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.date = date;
        this.count = count;
    }

}
