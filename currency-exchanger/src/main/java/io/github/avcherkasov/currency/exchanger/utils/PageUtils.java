package io.github.avcherkasov.currency.exchanger.utils;


/**
 * A helper class for working with paged
 *
 * @author Anatoly Cherkasov
 */
public class PageUtils {

    // --------------------------------------------------------------
    // Constants
    // --------------------------------------------------------------

    public static final int MAX_PAGE_LIMIT = 60;
    public static final int DEFAULT_PAGE_LIMIT = 10;

    // --------------------------------------------------------------
    // Other public methods
    // --------------------------------------------------------------

    /**
     * Get page number
     * Page numbering starts with zero index, so you need to subtract the digit one
     *
     * @param page zero-based page index.
     * @return int
     */
    public static int getPageNumber(final int page) {
        return (page > 0) ? page - 1 : 0;
    }

    /**
     * Get page limit
     *
     * @param limit the size of the page to be returned.
     * @return int
     */
    public static int getPageLimit(final int limit) {
        return (limit < MAX_PAGE_LIMIT && limit > 0) ? limit : DEFAULT_PAGE_LIMIT;
    }

}
