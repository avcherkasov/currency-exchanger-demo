package io.github.avcherkasov.currency.exchanger.repository;

import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface for executing named queries for an entity {@link Vendor}
 *
 * @author Anatoly Cherkasov
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    List<Vendor> findByIsActive(boolean isActive);

    Vendor findByName(String name);

}
