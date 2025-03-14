package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Customer;

/**
 * Data Access Object (DAO) class for managing Customer objects.
 * Handles loading, saving, and performing CRUD operations on customer data.
 */
public class CustomerDAO implements Serializable {
    
    private static final String FILE_NAME = "customer.dat"; // File to store customer data
    private Map<String, Customer> map; // Stores customers with their unique code as key

    /**
     * Constructor initializes the customer map by loading data from file.
     */
    public CustomerDAO() {
        this.map = load();
    }
    
    /**
     * Loads customer data from a file.
     * 
     * @return A map containing customer data or an empty map if an error occurs.
     */
    public Map<String, Customer> load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (HashMap<String, Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }
    
    /**
     * Saves the current customer data to a file.
     * 
     * @return true if saving is successful, false otherwise.
     */
    public boolean save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(map);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Retrieves the map of customers.
     * 
     * @return The customer map.
     */
    public Map<String, Customer> getMap() {
        return map;
    }
    
    /**
     * Retrieves a customer by their unique code.
     * 
     * @param code The unique code of the customer.
     * @return The Customer object if found, otherwise null.
     */
    public Customer getCustomerByCode(String code) {
        return map.getOrDefault(code, null);
    }
    
    /**
     * Adds a new customer to the data store.
     * 
     * @param customer The customer to be added.
     * @return true if the customer was added successfully.
     */
    public boolean create(Customer customer) {
        this.map.put(customer.getCode(), customer);
        return isExist(customer.getCode());
    }
    
    /**
     * Deletes a customer by their unique code.
     * 
     * @param code The unique code of the customer.
     * @return The removed Customer object, or null if not found.
     */
    public Customer delete(String code) {
        return this.map.remove(code);
    }
    
    /**
     * Updates a customer's information based on a given choice.
     * 
     * @param code The unique code of the customer.
     * @param choice The field to update (0: Name, 1: Phone Number, 2: Email).
     * @param value The new value to update.
     * @return true if the update was successful, false otherwise.
     */
    public boolean update(String code, int choice, String value) {
        Customer c = this.map.getOrDefault(code, null);
        if (c != null) {
            switch (choice) {
                case 0:
                    c.setName(value);
                    break;
                case 1:
                    c.setPhoneNumber(value);
                    break;
                case 2:
                    c.setEmail(value);
                    break;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Checks if a customer with the given code exists.
     * 
     * @param code The unique code of the customer.
     * @return true if the customer exists, false otherwise.
     */
    public boolean isExist(String code) {
        return this.map.containsKey(code);
    }
    
    /**
     * Retrieves a list of all customers.
     * 
     * @return A list of all customers.
     */
    public List<Customer> getList() {
        return new ArrayList<>(map.values());
    }
}
