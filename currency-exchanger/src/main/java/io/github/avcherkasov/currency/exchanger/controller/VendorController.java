package io.github.avcherkasov.currency.exchanger.controller;

import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import io.github.avcherkasov.currency.exchanger.service.VendorService;
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
 * Vendor data output
 *
 * @author Anatoly Cherkasov
 * @see VendorService
 * @see Vendor
 */
@Slf4j
@RestController
@RequestMapping(value = "vendor/v1")
public class VendorController {

    private VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        Assert.notNull(vendorService, "VendorService must not be null");

        this.vendorService = vendorService;
    }

    /**
     * Get list of vendors
     * <p>
     * Input:
     * e.g. http://{host}:{port}/{path}/{version}/list
     * <p>
     * Output:
     * <pre>
     * <code> [
     *  {
     *      id: 1,
     *      name: "cbr",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Центральный Банк России",
     *      additional: "{"param":"params-1","test":"test_2"}",
     *      active: true
     *  },
     *  {
     *      id: 2,
     *      name: "test vendor",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Test vendor",
     *      additional: "{"":""}",
     *      active: false
     *  }
     * ] <code>
     * </pre>
     *
     * @return List<Vendor>
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Vendor> list() {
        return vendorService.findAll();
    }

    /**
     * Get list of vendors with pagination
     * <p>
     * Input:
     * e.g. http://{host}:{port}/{path}/{version}/list/1/2
     * <p>
     * Output:
     * <pre>
     * <code> [
     *  {
     *      id: 1,
     *      name: "cbr",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Центральный Банк России",
     *      additional: "{"param":"params-1","test":"test_2"}",
     *      active: true
     *  },
     *  {
     *      id: 2,
     *      name: "test vendor",
     *      url: "http://localhost:8101/currency_rates",
     *      fullName: "Test vendor",
     *      additional: "{"":""}",
     *      active: false
     *  }
     * ] <code>
     * </pre>
     *
     * @return List<Vendor>
     */
    @RequestMapping(value = "list/{page}/{limit}", method = RequestMethod.GET)
    public List<Vendor> listWithPagination(@PathVariable("page") int page, @PathVariable("limit") int limit) {
        int pageNumber = PageUtils.getPageNumber(page);
        int pageLimit = PageUtils.getPageLimit(limit);

        Page<Vendor> vendorPage = vendorService.findAllPagination(pageNumber, pageLimit);

        return vendorPage.getContent();
    }

}
