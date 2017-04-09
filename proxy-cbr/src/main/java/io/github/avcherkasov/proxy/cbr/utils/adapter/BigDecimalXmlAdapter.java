package io.github.avcherkasov.proxy.cbr.utils.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

/**
 * XML converter for String -> BigDecimals
 *
 * @author Anatoly Cherkasov
 * @see XmlAdapter
 */
public class BigDecimalXmlAdapter extends XmlAdapter<String, BigDecimal> {

    /**
     * Convert a value type to a bound type.
     *
     * @param value The value to be converted. Can be null.
     * @return BigDecimal
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link javax.xml.bind.ValidationEventHandler}.
     * @see XmlAdapter#unmarshal(Object)
     */
    @Override
    public BigDecimal unmarshal(String value) throws Exception {
        return new BigDecimal(value.replace(",", "."));
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param value The value to be converted. Can be null.
     * @return String
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link javax.xml.bind.ValidationEventHandler}.
     * @see XmlAdapter#marshal(Object)
     */
    @Override
    public String marshal(BigDecimal value) throws Exception {
        return null;
    }

}
