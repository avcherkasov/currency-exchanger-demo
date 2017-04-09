package io.github.avcherkasov.proxy.nbrb.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Anatoly Cherkasov
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currencies {

    /**
     * Digital currency code (e.g. 643)
     */
    @JsonProperty("Cur_Code")
    private String curCode;

    /**
     * Symbolic currency code (e.g. RUB)
     */
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;

}
