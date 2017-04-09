package io.github.avcherkasov.proxy.nbrb.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A helper class for working with date
 *
 * @author Anatoly Cherkasov
 */
public class CustomDateUtils {

    // --------------------------------------------------------------
    // Constants
    // --------------------------------------------------------------

    public static final String PATTERN_FROM = "dd/MM/yyyy";
    public static final String PATTERN_TO = "yyyy-MM-dd";

    // --------------------------------------------------------------
    // Other public methods
    // --------------------------------------------------------------

    /**
     * Convert date
     *
     * @param dateStr date string
     * @param from    pattern from
     * @param to      pattern to
     * @return date in string
     * @throws ParseException exception
     */
    public static String convert(String dateStr, String from, String to) throws ParseException {
        DateFormat fromFormat = new SimpleDateFormat(from);
        DateFormat toFormat = new SimpleDateFormat(to);
        Date date = fromFormat.parse(dateStr);
        return toFormat.format(date);
    }

    /**
     * Convert date
     *
     * @param dateStr date string
     * @return date in string
     * @throws ParseException exception
     */
    public static String convert(String dateStr) throws ParseException {
        return CustomDateUtils.convert(dateStr, PATTERN_FROM, PATTERN_TO);
    }

    public static XMLGregorianCalendar stringToXMLGregorianCalendar(String dateStr, String pattern) throws DatatypeConfigurationException, ParseException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new SimpleDateFormat(pattern).parse(dateStr));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }

    public static XMLGregorianCalendar stringToXMLGregorianCalendar(String dateStr) throws DatatypeConfigurationException, ParseException {
        return stringToXMLGregorianCalendar(dateStr, CustomDateUtils.PATTERN_FROM);
    }

}
