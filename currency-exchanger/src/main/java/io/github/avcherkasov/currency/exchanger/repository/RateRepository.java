package io.github.avcherkasov.currency.exchanger.repository;

import io.github.avcherkasov.currency.exchanger.entity.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * The interface for executing named queries for an entity {@link Rates}
 *
 * @author Anatoly Cherkasov
 */
@Repository
public interface RateRepository extends JpaRepository<Rates, Long> {

    String FIND_ALL_RATE_BY_CURRENCY_VENDOR_AND_DATE = "SELECT * " +
            " FROM rates r " +
            " JOIN vendor_currency vc ON r.vendor_currency_id = vc.id " +
            " JOIN vendor v ON vc.vendor_id = v.id " +
            " JOIN currency cf ON vc.source_currency_id = cf.id " +
            " JOIN currency ct ON vc.target_currency_id = ct.id " +
            " WHERE v.name = :vendorName " +
            " AND ((" +
            "(cf.char_code = :currency_from OR cf.num_code = :currency_from) " +
            " OR " +
            "(ct.char_code = :currency_from OR ct.num_code = :currency_from) " +
            ") " +
            " AND (" +
            " (cf.char_code = :currency_to OR cf.num_code = :currency_to) " +
            " OR " +
            " (ct.char_code = :currency_to OR ct.num_code = :currency_to) " +
            ") OR (" +
            "   ct.char_code IN(:currency_from,:currency_to) " +
            "   OR ct.num_code IN(:currency_from,:currency_to)" +
            " )) " +
            " AND r.rate_date = :inputDate ";

    @Query(value = FIND_ALL_RATE_BY_CURRENCY_VENDOR_AND_DATE, nativeQuery = true)
    List<Rates> findAllRateByCurrencyAndVendorNameAndDate(
            @Param("vendorName") String vendorName,
            @Param("currency_from") String currency_from,
            @Param("currency_to") String currency_to,
            @Param("inputDate") Date inputDate
    );

    Rates findByVendorCurrencyIdAndNominalAndRateAndRateDate(
            Long vendorCurrencyId,
            Long nominal,
            Double rate,
            Date rateDate
    );

    Rates findByVendorCurrencyVendorNameAndVendorCurrencyTargetCurrencyCharCodeAndRateDate(
            @Param("vendorName") String vendorName,
            @Param("target_currency") String targetCurrency,
            @Param("inputDate") Date inputDate
    );

}
