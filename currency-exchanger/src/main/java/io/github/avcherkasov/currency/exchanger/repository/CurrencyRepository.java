package io.github.avcherkasov.currency.exchanger.repository;

import io.github.avcherkasov.currency.exchanger.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface for executing named queries for an entity {@link Currency}
 *
 * @author Anatoly Cherkasov
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    String GET_CURRENCY_BY_CODE = "SELECT * FROM #{#entityName} WHERE num_code = :code OR char_code = UPPER(:code)";

    @Query(value = GET_CURRENCY_BY_CODE, nativeQuery = true)
    Currency findCurrencyByCode(@Param("code") String code);

}
