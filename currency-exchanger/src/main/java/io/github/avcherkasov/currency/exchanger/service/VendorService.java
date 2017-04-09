package io.github.avcherkasov.currency.exchanger.service;

import io.github.avcherkasov.currency.exchanger.entity.Vendor;
import io.github.avcherkasov.currency.exchanger.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The wrapper service for the interface {@link VendorRepository VendorRepository}
 *
 * @author Anatoly Cherkasov
 */
@Slf4j
@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    /**
     * Find vendor by ID
     *
     * @param id {@link Vendor#getId()}
     * @return {@link Vendor}
     */
    public Vendor findVendorById(Long id) {
        LOGGER.info("Trying to get vendor by id [{}] from the DB", id);
        Vendor vendor = vendorRepository.findOne(id);
        LOGGER.info("Finish findVendorById method. DB returned [{}]", vendor);
        return vendor;
    }

    /**
     * Find vendor by name
     *
     * @param name {@link Vendor#getName()}
     * @return {@link Vendor}
     */
    public Vendor findByName(String name) {
        LOGGER.info("Trying to get vendor by name [{}] from the DB", name);
        Vendor vendor = vendorRepository.findByName(name);
        LOGGER.info("Finish findByName method. DB returned [{}]", vendor);
        return vendor;
    }

    /**
     * Get list of vendors
     *
     * @return list of {@link Vendor Vendor}
     */
    public List<Vendor> findAll() {
        LOGGER.info("Trying to get vendor list from the DB");
        List<Vendor> list = vendorRepository.findAll();
        LOGGER.info("Finish getVendorList method. DB returned {} list", list.size());
        return list;
    }

    /**
     * Get list of vendor with pagination
     *
     * @return Page<Vendor>
     */
    public Page<Vendor> findAllPagination(int page, int size) {
        LOGGER.info("Trying to get vendor list from the DB");
        Page<Vendor> list = vendorRepository.findAll(createPageRequest(page, size));
        LOGGER.info("Finish getVendorList method. DB returned {} list", list.getContent().size());
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
