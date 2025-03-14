package dto;

import java.util.Arrays;
import utils.rules.CustomerRules;
import model.IDisplayTable;
import utils.ResponseViewUtils;

/**
 * Data Transfer Object (DTO) representing a customer.
 * This class provides methods for formatting customer details
 * and implementing display and comparison functionalities.
 */
public class CustomerDTO implements IDisplayTable, Comparable<CustomerDTO> {
    
    /** Unique code identifying the customer */
    private String customerCode;

    /** Full name of the customer */
    private String customerName;

    /** First name extracted from the customer's full name */
    private String firstName;

    /** Email address of the customer */
    private String email;

    /** Phone number of the customer, formatted for display */
    private String phoneNumber;

    /**
     * Constructs a CustomerDTO object with given details.
     * 
     * @param customerCode Unique customer code
     * @param customerName Full name of the customer
     * @param email Email address of the customer
     * @param phoneNumber Phone number of the customer
     */
    public CustomerDTO(String customerCode, String customerName, String email, String phoneNumber) {
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = toDisplayPhone(phoneNumber);
        this.firstName = toFirstName(customerName);
    }

    /**
     * Gets the unique customer code.
     *
     * @return Customer code
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * Gets the full name of the customer.
     *
     * @return Customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Gets the first name extracted from the customer's full name.
     *
     * @return First name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return Email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the formatted phone number of the customer.
     *
     * @return Formatted phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns a formatted string representing the customer in a table view.
     * 
     * @return Formatted string for table display
     */
    @Override
    public String toTable() {
        return String.format(CustomerRules.Format.TABLE, 
                customerCode, 
                toDisplayName(customerName), 
                toDisplayEmail(email), 
                phoneNumber);
    }

    /**
     * Returns a formatted string representing the customer in a detailed list view.
     * 
     * @return Formatted string for detailed list display
     */
    @Override
    public String toString() {
        String line = ResponseViewUtils.line('-', ResponseViewUtils.SCREEN_LENGTH);
        return String.format(CustomerRules.Format.LIST, 
                line,
                customerCode, 
                customerName, 
                email, 
                phoneNumber,
                line);
    }

    /**
     * Compares this customer with another customer based on their first names.
     * 
     * @param o Other CustomerDTO to compare with
     * @return A negative, zero, or positive integer as this customer's first name
     *         is less than, equal to, or greater than the specified customer's first name.
     */
    @Override
    public int compareTo(CustomerDTO o) {
        return this.firstName.compareTo(o.firstName);
    }

    /**
     * Formats the customer's name for display.
     * If the name has more than one word, it rearranges it as "FirstName, LastName".
     *
     * @param name Full name of the customer
     * @return Formatted display name
     */
    private String toDisplayName(String name) {
        String[] arr = name.split(" ");
        if (arr.length == 1) {
            return name;
        }
        return firstName + ", " + String.join(" ", Arrays.copyOf(arr, arr.length - 1));
    }

    /**
     * Formats the email address for display.
     * If the email is too long, it trims and appends "..".
     *
     * @param email Email address
     * @return Formatted email address
     */
    private String toDisplayEmail(String email) {
        return (email.length() > 25) ? email.substring(0, 22) + ".." : email;
    }

    /**
     * Extracts the first name from a full name.
     * Assumes the last word in the full name is the first name.
     *
     * @param name Full name of the customer
     * @return Extracted first name
     */
    private String toFirstName(String name) {
        String[] arr = name.split(" ");
        return arr[arr.length - 1];
    }

    /**
     * Formats the phone number based on the accepted format defined in CustomerRules.
     *
     * @param phone Raw phone number
     * @return Formatted phone number
     */
    private String toDisplayPhone(String phone) {
        return phone.replaceAll(CustomerRules.Acceptable.PHONE_REGEX, CustomerRules.Format.PHONE);
    }

}
