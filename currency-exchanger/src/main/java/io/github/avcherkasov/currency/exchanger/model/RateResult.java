package io.github.avcherkasov.currency.exchanger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import io.github.avcherkasov.protocol.model.Currency;
import io.github.avcherkasov.protocol.model.Error;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * Rate Result
 *
 * @author Anatoly Cherkasov
 * @see Currency
 * @see Error
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateResult {

    /**
     * Date
     */
    Date date;

    /**
     * Vendor name {@link Vendor#getName()}
     */
    String vendor;

    /**
     * List of the {@link Currency}
     */
    List<Currency> currencies;

    /**
     * {@link Error}
     */
    Error error;

}
