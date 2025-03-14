package service;

import dao.DAOContainer;
import dao.CustomerDAO;
import dao.FeastMenuDAO;
import dao.FeastOrderDAO;
import dto.CustomerDTO;
import dto.FeastOrderDTO;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import model.Customer;
import model.FeastMenu;
import model.FeastOrder;
import utils.InputterUtils;
import utils.MapperUtils;
import utils.rules.CustomerRules;

/**
 * Service class responsible for retrieving customer and feast order data.
 */
public class RetrievalService {
    
    private CustomerDAO cdao = DAOContainer.CDAO;
    private FeastMenuDAO fmdao = DAOContainer.FMDAO;
    private FeastOrderDAO fodao = DAOContainer.FODAO;
    
    /**
     * Checks if the given input is a full name (contains a space).
     * 
     * @param input The input string to check.
     * @return True if the input is a full name, false otherwise.
     */
    private boolean isFullName(String input){
        return input.trim().contains(" ");
    }
    
    /**
     * Maps a FeastOrder object to a FeastOrderDTO object.
     * 
     * @param feastOrder The feast order to map.
     * @return The corresponding FeastOrderDTO object.
     */
    private FeastOrderDTO mapToDTO(FeastOrder feastOrder){
        Customer customer = cdao.getCustomerByCode(feastOrder.getCustomerCode());
        FeastMenu feastMenu = fmdao.getFeastMenuByCode(feastOrder.getFeastMenuCode());
        int id = fodao.getId(feastOrder) + 1;
        return MapperUtils.toFeastOrderDTO(id, feastOrder, customer, feastMenu);
    }
    
    /**
     * Maps a Customer object to a CustomerDTO object.
     * 
     * @param customer The customer to map.
     * @return The corresponding CustomerDTO object.
     */
    private CustomerDTO mapToDTO(Customer customer){
        return MapperUtils.toCustomerDTO(customer);
    }
    
    /**
     * Retrieves a filtered and mapped list of objects.
     * 
     * @param <E> The type of the elements in the source list.
     * @param <D> The type of the elements in the resulting list.
     * @param list The list of elements to process.
     * @param mapper The function to map elements.
     * @param condition The filter condition.
     * @return A sorted list of mapped and filtered elements.
     */
    private <E, D> List<D> retrieve(List<E> list, Function<E, D> mapper, Predicate<D> condition){
        return list.stream()
                .map(mapper)
                .filter(condition)
                .sorted()
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves all customers as CustomerDTO objects.
     * 
     * @return A list of all customers in DTO format.
     */
    public List<CustomerDTO> retrieveAllCustomer(){
        return retrieve(
                cdao.getList(), 
                c -> MapperUtils.toCustomerDTO(c),
                c -> true
        );
    }
    
    /**
     * Retrieves all feast menus.
     * 
     * @return A list of all feast menus.
     */
    public List<FeastMenu> retrieveAllFeastMenu(){
        return retrieve(
                fmdao.getList(), 
                c -> c,
                c -> true
        );
    }
    
    /**
     * Retrieves all feast orders as FeastOrderDTO objects.
     * 
     * @return A list of all feast orders in DTO format.
     */
    public List<FeastOrderDTO> retrieveAllFeastOrder(){
        return retrieve(
                fodao.getList(), 
                f -> mapToDTO(f),
                f -> true
        );
    }
    
    /**
     * Searches for customers by name.
     * 
     * @return A list of matching customers in DTO format.
     */
    public List<CustomerDTO> searchCustomerName(){
        String input = InputterUtils.inputName(2, 25);
        if(isFullName(input)){
            return retrieve(
                    cdao.getList(), 
                    c -> mapToDTO(c), 
                    c -> c.getCustomerName().contains(input)
            );
        }
        return retrieve(
                cdao.getList(), 
                c -> mapToDTO(c),
                c -> c.getFirstName().contains(input)
        );
    }
    
    /**
     * Searches for a customer by phone number.
     * 
     * @return A list containing the matching customer in DTO format.
     */
    public List<CustomerDTO> searchCustomerByPhone(){
        String phoneNum = InputterUtils.inputWithPattern("phone number", CustomerRules.Acceptable.PHONE_REGEX);
        
        return retrieve(
                cdao.getList(),
                c -> mapToDTO(c),
                c -> c.getPhoneNumber().equals(phoneNum)
        );
    } 
    
    /**
     * Searches for feast orders by customer name.
     * 
     * @return A list of matching feast orders in DTO format.
     */
    public List<FeastOrderDTO> searchFeastOrderByCustomer(){
        String customerName = InputterUtils.inputName(2, 25);
        
        return retrieve(
                fodao.getList(),
                f -> mapToDTO(f),
                f -> f.getCustomerName().equals(customerName)
        );
    }
}