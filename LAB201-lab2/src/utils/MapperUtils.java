package utils;

import dto.CustomerDTO;
import dto.FeastOrderDTO;
import model.Customer;
import model.FeastMenu;
import model.FeastOrder;

/**
 * Utility class for mapping model objects to DTO objects.
 */
public class MapperUtils {

    /**
     * Converts a Customer model to a CustomerDTO.
     * 
     * @param customer The Customer object to be converted.
     * @return A CustomerDTO containing the mapped data.
     */
    public static CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getCode(), 
                customer.getName(), 
                customer.getEmail(), 
                customer.getPhoneNumber()
        );
    }

    /**
     * Converts a FeastOrder model to a FeastOrderDTO.
     * 
     * @param id The ID of the feast order.
     * @param feastOrder The FeastOrder object to be converted.
     * @param customer The Customer associated with the feast order.
     * @param feastMenu The FeastMenu associated with the feast order.
     * @return A FeastOrderDTO containing the mapped data.
     */
    public static FeastOrderDTO toFeastOrderDTO(int id, FeastOrder feastOrder, Customer customer, FeastMenu feastMenu) {
        return new FeastOrderDTO(
                id, 
                customer.getCode(), 
                customer.getName(), 
                customer.getEmail(), 
                customer.getPhoneNumber(), 
                feastMenu.getCode(), 
                feastMenu.getName(), 
                feastOrder.getDate(), 
                feastOrder.getNumberOfTable(), 
                feastMenu.getPrice(), 
                feastMenu.getIngredients()
        );
    }
}
