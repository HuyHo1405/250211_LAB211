package model;

import java.io.Serializable;

/**
 * Represents an order for a feast menu placed by a customer.
 * <p>
 * This class implements {@link Serializable} to allow object serialization.
 * Each order contains information about the customer, the feast menu selected,
 * the date of the event, and the number of tables required.
 * </p>
 */
public class FeastOrder implements Serializable {

    /** The unique code of the customer placing the order. */
    private String customerCode;

    /** The unique code of the feast menu selected in the order. */
    private String feastMenuCode;

    /** The date of the feast order. */
    private String date;

    /** The number of tables booked for the feast. */
    private int numberOfTable;

    /**
     * Constructs a new {@code FeastOrder} with the given details.
     *
     * @param customerCode   The unique code of the customer placing the order.
     * @param feastMenuCode  The unique code of the feast menu selected.
     * @param date           The date of the order in YYYY-MM-DD format.
     * @param numberOfTable  The number of tables booked for the feast.
     */
    public FeastOrder(String customerCode, String feastMenuCode, String date, int numberOfTable) {
        this.customerCode = customerCode;
        this.feastMenuCode = feastMenuCode;
        this.date = date;
        this.numberOfTable = numberOfTable;
    }

    // Getters

    /**
     * Gets the customer code associated with the order.
     *
     * @return The customer code.
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * Gets the feast menu code selected in the order.
     *
     * @return The feast menu code.
     */
    public String getFeastMenuCode() {
        return feastMenuCode;
    }

    /**
     * Gets the date of the feast order.
     *
     * @return The order date in YYYY-MM-DD format.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the number of tables booked for the feast.
     *
     * @return The number of tables.
     */
    public int getNumberOfTable() {
        return numberOfTable;
    }

    // Setters

    /**
     * Sets the customer code for the order.
     *
     * @param customerCode The new customer code.
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * Sets the feast menu code for the order.
     *
     * @param feastMenuCode The new feast menu code.
     */
    public void setFeastMenuCode(String feastMenuCode) {
        this.feastMenuCode = feastMenuCode;
    }

    /**
     * Sets the date of the feast order.
     *
     * @param date The new order date in YYYY-MM-DD format.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the number of tables booked for the feast.
     *
     * @param numberOfTable The new number of tables.
     */
    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    /**
     * Returns a formatted string representation of the order.
     *
     * @return A string containing the order details.
     */
    @Override
    public String toString() {
        return String.format("FeastOrder [Customer Code: %s, Feast Menu Code: %s, Date: %s, Tables: %d]",
                customerCode, feastMenuCode, date, numberOfTable);
    }
}
