package io.github.avcherkasov.proxy.nbrb.model.soap;


import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="DailyExRatesOnDate">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Cur_QuotName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Cur_Scale" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="Cur_OfficialRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="Cur_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Cur_Abbreviation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "dailyExRatesOnDate"
})
@XmlRootElement(name = "NewDataSet")
public class NewDataSet {

    @XmlElement(name = "DailyExRatesOnDate")
    protected List<NewDataSet.DailyExRatesOnDate> dailyExRatesOnDate;

    /**
     * Gets the value of the dailyExRatesOnDate property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dailyExRatesOnDate property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDailyExRatesOnDate().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NewDataSet.DailyExRatesOnDate }
     *
     *
     */
    public List<NewDataSet.DailyExRatesOnDate> getDailyExRatesOnDate() {
        if (dailyExRatesOnDate == null) {
            dailyExRatesOnDate = new ArrayList<DailyExRatesOnDate>();
        }
        return this.dailyExRatesOnDate;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Cur_QuotName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Cur_Scale" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="Cur_OfficialRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="Cur_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Cur_Abbreviation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "curQuotName",
            "curScale",
            "curOfficialRate",
            "curCode",
            "curAbbreviation"
    })
    public static class DailyExRatesOnDate {

        @XmlElement(name = "Cur_QuotName")
        protected String curQuotName;
        @XmlElement(name = "Cur_Scale")
        protected Integer curScale;
        @XmlElement(name = "Cur_OfficialRate")
        protected BigDecimal curOfficialRate;
        @XmlElement(name = "Cur_Code")
        protected String curCode;
        @XmlElement(name = "Cur_Abbreviation")
        protected String curAbbreviation;

        /**
         * Gets the value of the curQuotName property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCurQuotName() {
            return curQuotName;
        }

        /**
         * Sets the value of the curQuotName property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCurQuotName(String value) {
            this.curQuotName = value;
        }

        /**
         * Gets the value of the curScale property.
         *
         * @return
         *     possible object is
         *     {@link Integer }
         *
         */
        public Integer getCurScale() {
            return curScale;
        }

        /**
         * Sets the value of the curScale property.
         *
         * @param value
         *     allowed object is
         *     {@link Integer }
         *
         */
        public void setCurScale(Integer value) {
            this.curScale = value;
        }

        /**
         * Gets the value of the curOfficialRate property.
         *
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *
         */
        public BigDecimal getCurOfficialRate() {
            return curOfficialRate;
        }

        /**
         * Sets the value of the curOfficialRate property.
         *
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *
         */
        public void setCurOfficialRate(BigDecimal value) {
            this.curOfficialRate = value;
        }

        /**
         * Gets the value of the curCode property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCurCode() {
            return curCode;
        }

        /**
         * Sets the value of the curCode property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCurCode(String value) {
            this.curCode = value;
        }

        /**
         * Gets the value of the curAbbreviation property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCurAbbreviation() {
            return curAbbreviation;
        }

        /**
         * Sets the value of the curAbbreviation property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCurAbbreviation(String value) {
            this.curAbbreviation = value;
        }

    }

}
