package io.github.avcherkasov.currency.exchanger.service;

import io.github.avcherkasov.currency.exchanger.entity.Rates;
import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import io.github.avcherkasov.currency.exchanger.entity.VendorCurrency;
import io.github.avcherkasov.currency.exchanger.repository.RateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * The wrapper service for the interface {@link RateRepository RateRepository}
 * TODO: Think about the names of methods
 *
 * @author Anatoly Cherkasov
 */
@Slf4j
@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    /**
     * Get list rates by vendor name, currency and date
     *
     * @param vendorName   {@link Vendor#getName()}
     * @param fromCurrency currency
     * @param toCurrency   currency
     * @param rateDate     {@link Rates#getRateDate()}
     * @return list of {@link Rates Rates}
     */
    public List<Rates> findByVendorNameAndCurrencyAndDate(String vendorName, String fromCurrency, String toCurrency, Date rateDate) {
        LOGGER.info("Trying to get Rates list from the DB");
        List<Rates> rates = rateRepository.findAllRateByCurrencyAndVendorNameAndDate(
                vendorName,
                fromCurrency.toUpperCase(),
                toCurrency.toUpperCase(),
                rateDate
        );
        LOGGER.info("Finish findByVendorNameAndCurrencyAndDate method. DB returned {} list", rates.size());
        return rates;
    }

    /**
     * Get rates by vendor name, currency and date
     *
     * @param vendorName {@link Vendor#getName()}
     * @param currency   currency
     * @param rateDate   {@link Rates#getRateDate()}
     * @return {@link Rates Rates}
     */
    public Rates findByVendorNameAndCurrencyAndDate(String vendorName, String currency, Date rateDate) {
        LOGGER.info("Trying to get Rates list from the DB");
        Rates rates = rateRepository.findByVendorCurrencyVendorNameAndVendorCurrencyTargetCurrencyCharCodeAndRateDate(
                vendorName,
                currency.toUpperCase(),
                rateDate
        );
        LOGGER.info("Finish findByVendorNameAndCurrencyAndDate method. DB returned {}", rates);
        return rates;
    }

    /**
     * Get rates by vendor currency, nominal, rate and date
     *
     * @param vendorCurrencyId {@link VendorCurrency#getId()}
     * @param nominal          {@link Rates#getNominal()}
     * @param rate             {@link Rates#getRate()}
     * @param rateDate         {@link Rates#getRateDate()}
     * @return {@link Rates Rates}
     */
    public Rates findByVendorCurrencyIdAndNominalAndRateAndRateDate(Long vendorCurrencyId, Long nominal, Double rate, Date rateDate) {
        LOGGER.info("Trying to get Rates from the DB");
        Rates rates = rateRepository.findByVendorCurrencyIdAndNominalAndRateAndRateDate(
                vendorCurrencyId, nominal, rate, rateDate
        );
        LOGGER.info("Finish findByVendorCurrencyIdAndNominalAndRateAndRateDate method. DB returned {}", rates);
        return rates;
    }

    /**
     * Save rates
     *
     * @param rate {@link Rates Rates}
     * @return {@link Rates Rates}
     */
    public Rates save(Rates rate) {
        LOGGER.info("Trying to save Rate from the DB");
        Rates rates = rateRepository.save(rate);
        LOGGER.info("Finish save method. DB returned {}", rates);
        return rates;
    }

    /**
     * Rates exists
     *
     * @param rate {@link Rates Rates}
     * @return boolean
     */
    public boolean exists(Rates rate) {
        Rates rates = rateRepository.findByVendorCurrencyIdAndNominalAndRateAndRateDate(
                rate.getVendorCurrency().getId(),
                rate.getNominal(),
                rate.getRate(),
                rate.getRateDate()
        );
        return rates != null;
    }

}
