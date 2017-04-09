package io.github.avcherkasov.proxy.nbrb.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Anatoly Cherkasov <shnurok14@yandex.ru>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    /**
     * The unique identifier (e.g. "191")
     */
    @JsonProperty("Cur_ID")
    private Long curID;

    /**
     * Date (e.g. "2017-04-06T00:00:00")
     */
    @JsonProperty("Date")
    private Date date;

    /**
     * Symbolic currency code (e.g. BGN)
     */
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;

    /**
     * Currency nominal (e.g. 1)
     */
    @JsonProperty("Cur_Scale")
    private Long curScale;

    /**
     * Currency name (e.g. Болгарский лев)
     */
    @JsonProperty("Cur_Name")
    private String curName;

    /**
     * Currency rate (e.g. 1.0259)
     */
    @JsonProperty("Cur_OfficialRate")
    private BigDecimal curOfficialRate;

}
