package io.github.avcherkasov.proxy.cbr.model;

import io.github.avcherkasov.proxy.cbr.utils.adapter.BigDecimalXmlAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

/**
 * @author Anatoly Cherkasov
 */
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Valute {

    @XmlAttribute(name = "ID")
    private String id;

    @XmlElement(name = "NumCode")
    private String numCode;

    @XmlElement(name = "CharCode")
    private String charCode;

    @XmlElement(name = "Nominal")
    private Long nominal;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(BigDecimalXmlAdapter.class)
    private BigDecimal value;

    public String toString() {
        return String.format("{\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\"}",
                id,
                numCode,
                charCode,
                String.valueOf(nominal),
                name,
                String.valueOf(value)
        );
    }

    public Long getNominal() {
        return nominal;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCharCode() {
        return charCode;
    }

    public String getNumCode() {
        return numCode;
    }

    public String getName() {
        return name;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
