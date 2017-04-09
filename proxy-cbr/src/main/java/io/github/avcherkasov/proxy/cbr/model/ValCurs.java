package io.github.avcherkasov.proxy.cbr.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Anatoly Cherkasov
 */
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValCurs {

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "Date")
    private String date;

    @XmlElement(name = "Valute")
    private List<Valute> valutes = new ArrayList<Valute>();

    public List<Valute> getValutes() {
        return valutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValutes(List<Valute> valutes) {
        this.valutes = valutes;
    }

    public String toString() {
        int len = valutes.size();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(String.format("\"%s\", \"%s\", \"%d\" ", name, date, len));
        for (Valute v : getValutes()) {
            sb.append(v.toString());
        }
        sb.append("}");
        return sb.toString();
    }

}
