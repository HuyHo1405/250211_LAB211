package dto;

import utils.rules.FeastOrderRules;
import model.IDisplayTable;
import utils.ResponseViewUtils;
import utils.rules.CustomerRules;

/**
 * Data Transfer Object (DTO) representing a feast order.
 * This class contains customer details, order details, and methods 
 * for displaying the order in table or list format.
 */
public class FeastOrderDTO implements IDisplayTable, Comparable<FeastOrderDTO> {
    
    /** Unique identifier for the order */
    private int id;

    /** Unique customer code associated with the order */
    private String customerCode;

    /** Full name of the customer who placed the order */
    private String customerName;

    /** Email address of the customer */
    private String customerEmail;

    /** Formatted phone number of the customer */
    private String customerPhone;

    /** Unique code for the ordered feast menu */
    private String feastMenuCode;

    /** Name of the feast menu */
    private String feastMenuName;

    /** Date of the feast order */
    private String date;

    /** Number of tables reserved for the feast */
    private int numberOfTable;

    /** Price per table */
    private double price;

    /** List of ingredients included in the feast menu */
    private String[] ingredients;

    /** Total cost calculated as price per table multiplied by number of tables */
    private double totalCost;

    /**
     * Constructs a FeastOrderDTO object with given details.
     * 
     * @param id Unique order ID
     * @param customerCode Customer's unique code
     * @param customerName Full name of the customer
     * @param customerEmail Email address of the customer
     * @param customerPhone Phone number of the customer
     * @param feastMenuCode Code of the feast menu
     * @param feastMenuName Name of the feast menu
     * @param date Date of the order
     * @param numberOfTable Number of tables reserved
     * @param price Price per table
     * @param ingredients List of ingredients for the feast
     */
    public FeastOrderDTO(int id, String customerCode, String customerName, String customerEmail, String customerPhone, 
                         String feastMenuCode, String feastMenuName, String date, int numberOfTable, double price, 
                         String[] ingredients) {
        this.id = id;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = toDisplayPhone(customerPhone);
        this.feastMenuCode = feastMenuCode;
        this.feastMenuName = feastMenuName;
        this.date = date;
        this.numberOfTable = numberOfTable;
        this.price = price;
        this.ingredients = ingredients;
        this.totalCost = price * numberOfTable;
    }

    /**
     * Gets the unique order ID.
     *
     * @return Order ID
     */
    public int getId() {
        return id;
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
     * Gets the email address of the customer.
     *
     * @return Customer email
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Gets the formatted phone number of the customer.
     *
     * @return Customer phone number
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Gets the feast menu code.
     *
     * @return Feast menu code
     */
    public String getFeastMenuCode() {
        return feastMenuCode;
    }

    /**
     * Gets the feast menu name.
     *
     * @return Feast menu name
     */
    public String getFeastMenuName() {
        return feastMenuName;
    }

    /**
     * Gets the order date.
     *
     * @return Order date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the number of tables reserved.
     *
     * @return Number of tables
     */
    public int getNumberOfTable() {
        return numberOfTable;
    }

    /**
     * Gets the price per table.
     *
     * @return Price per table
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the list of ingredients for the feast menu.
     *
     * @return Array of ingredients
     */
    public String[] getIngredients() {
        return ingredients;
    }

    /**
     * Gets the total cost of the order.
     *
     * @return Total cost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns a formatted string representing the feast order in a detailed list format.
     * 
     * @return Formatted string for detailed list display
     */
    @Override
    public String toString() {
        String line = ResponseViewUtils.line('-', ResponseViewUtils.SCREEN_LENGTH);
        return String.format(FeastOrderRules.Format.LIST, 
                id,
                line,
                customerCode,
                customerName,
                customerEmail,
                customerPhone,
                line,
                feastMenuCode,
                feastMenuName,
                date,
                numberOfTable,
                price,
                line,
                ingredients[0],
                ingredients[1],
                ingredients[2],
                line,
                totalCost,
                line
        );
    }

    /**
     * Returns a formatted string representing the feast order in a table format.
     * 
     * @return Formatted string for table display
     */
    @Override
    public String toTable() {
        return String.format(FeastOrderRules.Format.TABLE, 
                id,
                date,
                customerCode,
                feastMenuCode,
                String.format("%,.0f", price),
                numberOfTable,
                String.format("%,.0f", totalCost)
        );
    }

    /**
     * Compares this feast order with another based on the order date.
     * If dates are the same, comparison is done based on order ID.
     * 
     * @param o Other FeastOrderDTO to compare with
     * @return A negative, zero, or positive integer if this order is earlier than, 
     *         equal to, or later than the specified order.
     */
    @Override
    public int compareTo(FeastOrderDTO o) {
        int dateCompare = this.date.compareTo(o.date);
        if (dateCompare != 0) {
            return dateCompare;
        }
        return Integer.compare(this.id, o.id);
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
