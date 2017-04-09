package io.github.avcherkasov.currency.exchanger.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Anatoly Cherkasov
 * @see PageUtils
 */
public class PageUtilsTest {

    @Test
    public void pageNumber() throws Exception {
        int page = 0;
        assertEquals(0, PageUtils.getPageNumber(page));

        page = 1;
        assertEquals(0, PageUtils.getPageNumber(page));

        page = 2;
        assertEquals(1, PageUtils.getPageNumber(page));

        page = -1;
        assertEquals(0, PageUtils.getPageNumber(page));
    }

    @Test
    public void pageLimit() throws Exception {
        int limit = 1;
        assertEquals(1, PageUtils.getPageLimit(limit));

        limit = 0;
        assertEquals(PageUtils.DEFAULT_PAGE_LIMIT, PageUtils.getPageLimit(limit));
    }

    @Test
    public void maxPageLimit() throws Exception {
        int limit = PageUtils.MAX_PAGE_LIMIT - 1;
        assertEquals(limit, PageUtils.getPageLimit(limit));

        limit = PageUtils.MAX_PAGE_LIMIT + 1;
        assertEquals(PageUtils.DEFAULT_PAGE_LIMIT, PageUtils.getPageLimit(limit));
    }

    @Test
    public void defaultPageLimit() throws Exception {
        int limit = PageUtils.MAX_PAGE_LIMIT + 1;
        assertEquals(PageUtils.DEFAULT_PAGE_LIMIT, PageUtils.getPageLimit(limit));

        limit = -1;
        assertEquals(PageUtils.DEFAULT_PAGE_LIMIT, PageUtils.getPageLimit(limit));
    }

}
