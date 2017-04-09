package io.github.avcherkasov.currency.exchanger.service;

import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import io.github.avcherkasov.currency.exchanger.entity.VendorCurrency;
import io.github.avcherkasov.currency.exchanger.repository.VendorCurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The wrapper service for the interface {@link VendorCurrencyRepository VendorCurrencyRepository}
 *
 * @author Anatoly Cherkasov
 * @see VendorCurrency
 * @see VendorCurrencyRepository
 */
@Slf4j
@Service
public class VendorCurrencyService {

    @Autowired
    private VendorCurrencyRepository vendorCurrencyRepository;

    /**
     * Get list of vendor with currency where is active vendor and currency
     *
     * @param isActiveVendor         {@link Vendor#isActive}
     * @param isActiveVendorCurrency {@link VendorCurrency#isActive}
     * @return list of {@link VendorCurrency VendorCurrency}
     */
    public List<VendorCurrency> findByVendorIsActiveAndIsActive(boolean isActiveVendor, boolean isActiveVendorCurrency) {
        LOGGER.info("Trying to get VendorCurrency list from the DB");
        List<VendorCurrency> list = vendorCurrencyRepository.findByVendorIsActiveAndIsActive(isActiveVendor, isActiveVendorCurrency);

        LOGGER.info("Finish getCurrencyList method. DB returned {} list", list.size());
        return list;
    }

    /**
     * Get list of vendor with currency where all is active equals true
     *
     * @return list of {@link VendorCurrency VendorCurrency}
     */
    public List<VendorCurrency> findAllWhereIsActiveTrue() {
        LOGGER.info("Trying to get VendorCurrency list from the DB");
        List<VendorCurrency> list = findByVendorIsActiveAndIsActive(true, true);

        LOGGER.info("Finish getCurrencyList method. DB returned {} list", list.size());
        return list;
    }

}
