package io.github.avcherkasov.currency.exchanger.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A helper class for working with date
 *
 * @author Anatoly Cherkasov
 */
public class CustomDateUtils {

    // --------------------------------------------------------------
    // Constants
    // --------------------------------------------------------------

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String REQUEST_DATE_PATTERN = "dd/MM/yyyy";

    public static String getCurrentDate(String pattern) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatters);
    }

    public static String getCurrentDateForRequest() {
        return getCurrentDate(REQUEST_DATE_PATTERN);
    }

}
