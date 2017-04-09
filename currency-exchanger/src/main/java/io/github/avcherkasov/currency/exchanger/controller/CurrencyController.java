package io.github.avcherkasov.currency.exchanger.controller;

import io.github.avcherkasov.currency.exchanger.entity.Currency;
import io.github.avcherkasov.currency.exchanger.service.CurrencyService;
import io.github.avcherkasov.currency.exchanger.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Currency data output
 *
 * @author Anatoly Cherkasov
 * @see CurrencyService
 * @see Currency
 */
@Slf4j
@RestController
@RequestMapping(value = "currency/v1")
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        Assert.notNull(currencyService, "CurrencyService must not be null");

        this.currencyService = currencyService;
    }

    /**
     * Get currency by ID
     * <p>
     * Input:
     * e.g. http://{host}:{port}/{path}/{version}/id/1
     * <p>
     * Output:
     * <pre>
     * <code> {
     *      id: 1,
     *      charCode: "RUB",
     *      numCode: "643",
     *      name: "Российский рубль"
     * } <code>
     * </pre>
     *
     * @param id {@link Currency#id}
     * @return {@link Currency}
     */
    @RequestMapping(value = "id/{id}", method = RequestMethod.GET)
    public Currency getById(@PathVariable("id") long id) {
        return currencyService.findById(id);
    }

    /**
     * Get currency by code
     * <p>
     * Input:
     * e.g. http://{host}:{port}/{path}/{version}/code/643
     * <p>
     * Output:
     * <pre>
     * <code> {
     *      id: 1,
     *      charCode: "RUB",
     *      numCode: "643",
     *      name: "Российский рубль"
     * } <code>
     * </pre>
     *
     * @param code {@link Currency#charCode} or {@link Currency#numCode}
     * @return {@link Currency}
     */
    @RequestMapping(value = "code/{code}", method = RequestMethod.GET)
    public Currency getByCode(@PathVariable("code") String code) {
        return currencyService.findByCode(code);
    }

    /**
     * Get list of currency
     * <p>
     * Input:
     * e.g. http://{host}:{port}/{path}/{version}
     * <p>
     * Output:
     * <pre>
     * <code> [
     *  {
     *      id: 1,
     *      charCode: "RUB",
     *      numCode: "643",
     *      name: "Российский рубль"
     *  },
     *  {
     *      id: 2,
     *      charCode: "USD",
     *      numCode: "840",
     *      name: "Доллар США"
     *  }
     * ] <code>
     * </pre>
     *
     * @return List<Currency>
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Currency> list() {
        return currencyService.findAll();
    }

    /**
     * Get list of currencies with pagination
     * <p>
     * Input:
     * e.g. http://{host}:{port}/{path}/{version}/list/1/2
     * <p>
     * Output:
     * <pre>
     * <code> [
     *  {
     *      id: 1,
     *      charCode: "RUB",
     *      numCode: "643",
     *      name: "Российский рубль"
     *  },
     *  {
     *      id: 2,
     *      charCode: "USD",
     *      numCode: "840",
     *      name: "Доллар США"
     *  }
     * ] <code>
     * </pre>
     *
     * @return List<Currency>
     */
    @RequestMapping(value = "list/{page}/{limit}", method = RequestMethod.GET)
    public List<Currency> listWithPagination(
            @PathVariable("page") int page,
            @PathVariable("limit") int limit
    ) {
        int pageNumber = PageUtils.getPageNumber(page);
        int pageLimit = PageUtils.getPageLimit(limit);

        Page<Currency> currencyPage = currencyService.findAllPagination(pageNumber, pageLimit);

        return currencyPage.getContent();
    }

}
