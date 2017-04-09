package io.github.avcherkasov.currency.exchanger.repository;

import io.github.avcherkasov.currency.exchanger.entity.VendorCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface for executing named queries for an entity {@link VendorCurrency}
 *
 * @author Anatoly Cherkasov
 */
@Repository
public interface VendorCurrencyRepository extends JpaRepository<VendorCurrency, Long> {

    List<VendorCurrency> findByVendorName(String name);

    List<VendorCurrency> findByVendorIsActive(boolean isActive);

    List<VendorCurrency> findByVendorIsActiveAndIsActive(boolean isActiveVendor, boolean isActiveVendorCurrency);

}
