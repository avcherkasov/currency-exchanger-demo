package io.github.avcherkasov.currency.exchanger.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * Entity Vendor. Contains information about the vendor object.
 *
 * @author Anatoly Cherkasov
 */
@Data
@Entity(name = "vendor")
@Table(name = "vendor")
public class Vendor {

    /**
     * The unique identifier
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_vendor_id_gen"
    )
    @SequenceGenerator(
            name = "seq_vendor_id_gen",
            sequenceName = "seq_vendor_id",
            allocationSize = 1
    )
    private Long id;

    /**
     * Vendor name
     */
    private String name;

    /**
     * Url vendor proxy
     */
    private String url;

    /**
     * Vendor full name
     */
    private String fullName;

    /**
     * Options for vendor proxy
     */
    private String additional;

    /**
     * Is active
     */
    private boolean isActive;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructs a new {@link Vendor Vendor} instance.
     */
    public Vendor() {
        // Constructs default a new {@link Vendor Vendor} instance.
    }

    /**
     * Constructs a new {@link Vendor Vendor} instance with the given
     * initial parameters to be constructed.
     *
     * @param id the field's id (see {@link #id}).
     */
    public Vendor(Long id) {
        this.id = id;
    }

    /**
     * Constructs a new {@link Vendor Vendor} instance with the given
     * initial parameters to be constructed.
     *
     * @param id         the field's id (see {@link #id}).
     * @param name       the field's name (see {@link #name}).
     * @param url        the field's url (see {@link #url}).
     * @param fullName   the field's fullName (see {@link #fullName}).
     * @param additional the field's additional (see {@link #additional}).
     */
    public Vendor(Long id, String name, String url, String fullName, String additional) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.fullName = fullName;
        this.additional = additional;
    }

    /**
     * Constructs a new {@link Vendor Vendor} instance with the given
     * initial parameters to be constructed.
     *
     * @param name       the field's name (see {@link #name}).
     * @param url        the field's url (see {@link #url}).
     * @param fullName   the field's fullName (see {@link #fullName}).
     * @param additional the field's additional (see {@link #additional}).
     */
    public Vendor(String name, String url, String fullName, String additional) {
        this.name = name;
        this.url = url;
        this.fullName = fullName;
        this.additional = additional;
    }

    /**
     * Constructs a new {@link Vendor Vendor} instance with the given
     * initial parameters to be constructed.
     *
     * @param name       the field's name (see {@link #name}).
     * @param url        the field's url (see {@link #url}).
     * @param fullName   the field's fullName (see {@link #fullName}).
     * @param additional the field's additional (see {@link #additional}).
     * @param isActive   the field's isActive (see {@link #isActive}).
     */
    public Vendor(String name, String url, String fullName, String additional, boolean isActive) {
        this.name = name;
        this.url = url;
        this.fullName = fullName;
        this.additional = additional;
        this.isActive = isActive;
    }

    /**
     * Constructs a new {@link Vendor Vendor} instance with the given
     * initial parameters to be constructed.
     *
     * @param id         the field's id (see {@link #id}).
     * @param name       the field's name (see {@link #name}).
     * @param url        the field's url (see {@link #url}).
     * @param fullName   the field's fullName (see {@link #fullName}).
     * @param additional the field's additional (see {@link #additional}).
     * @param isActive   the field's isActive (see {@link #isActive}).
     */
    public Vendor(Long id, String name, String url, String fullName, String additional, boolean isActive) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.fullName = fullName;
        this.additional = additional;
        this.isActive = isActive;
    }

}
