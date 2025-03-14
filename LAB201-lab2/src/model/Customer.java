package model;

import java.io.Serializable;

/**
 * Represents a customer with basic information such as code, name, email, and phone number.
 * <p>
 * This class implements {@link Serializable} to allow customer objects to be serialized.
 * </p>
 */
public class Customer implements Serializable {

    /** Unique identifier for the customer. */
    private String code;
    
    /** Full name of the customer. */
    private String name;
    
    /** Email address of the customer. */
    private String email;
    
    /** Phone number of the customer. */
    private String phoneNumber;

    /**
     * Constructs a new {@code Customer} with the given details.
     *
     * @param code        Unique identifier of the customer.
     * @param name        Full name of the customer.
     * @param email       Email address of the customer.
     * @param phoneNumber Phone number of the customer.
     */
    public Customer(String code, String name, String email, String phoneNumber) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters

    /**
     * Gets the customer code.
     *
     * @return The customer code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the full name of the customer.
     *
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return The customer's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters

    /**
     * Sets the customer code.
     *
     * @param code The new customer code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Sets the full name of the customer.
     *
     * @param name The new name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email The new email of the customer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phoneNumber The new phone number of the customer.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
