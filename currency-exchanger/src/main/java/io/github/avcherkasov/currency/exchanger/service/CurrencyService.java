package io.github.avcherkasov.currency.exchanger.service;

import io.github.avcherkasov.currency.exchanger.entity.Currency;
import io.github.avcherkasov.currency.exchanger.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The wrapper service for the interface {@link CurrencyRepository CurrencyRepository}
 *
 * @author Anatoly Cherkasov
 */
@Slf4j
@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Find currency by ID
     *
     * @param id {@link Currency#id}
     * @return {@link Currency}
     */
    public Currency findById(Long id) {
        LOGGER.info("Trying to find currency by id [{}] from the DB", id);
        Currency currency = currencyRepository.findOne(id);
        LOGGER.info("Finish findById method. DB returned [{}]", currency);
        return currency;
    }

    /**
     * Get currency by code
     *
     * @param code {@link Currency#charCode} or {@link Currency#numCode}
     * @return {@link Currency}
     */
    public Currency findByCode(String code) {
        LOGGER.info("Trying to find currency by code [{}] from the DB", code);
        Currency currency = currencyRepository.findCurrencyByCode(code);
        LOGGER.info("Finish findByCode method. DB returned [{}]", currency);
        return currency;
    }

    /**
     * Get list of currency
     *
     * @return list of {@link Currency Currency}
     */
    public List<Currency> findAll() {
        LOGGER.info("Trying to find all currency from the DB");
        List<Currency> list = currencyRepository.findAll();
        LOGGER.info("Finish getCurrencyList method. DB returned {} list", list.size());
        return list;
    }

    /**
     * Get list of currency with pagination
     *
     * @return Page<Currency>
     */
    public Page<Currency> findAllPagination(int page, int size) {
        LOGGER.info("Trying to find currency list from the DB");
        Page<Currency> list = currencyRepository.findAll(createPageRequest(page, size));
        LOGGER.info("Finish getCurrencyList method. DB returned {} list", list.getContent().size());
        return list;
    }

    /**
     * Create page request for pagination
     *
     * @param page zero-based page index.
     * @param size the size of the page to be returned.
     * @return {@link PageRequest}
     */
    private PageRequest createPageRequest(int page, int size) {
        return new PageRequest(page, size, Sort.Direction.ASC, "id");
    }

}
