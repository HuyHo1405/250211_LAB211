package service;

import dao.DAOContainer;
import dao.CustomerDAO;
import dao.FeastMenuDAO;
import dao.FeastOrderDAO;
import model.Customer;
import model.FeastMenu;
import model.FeastOrder;
import utils.InputterUtils;
import utils.MapperUtils;
import utils.ResponseViewUtils;
import utils.rules.CustomerRules;

/**
 * Service class responsible for handling the deletion of customers and feast orders.
 */
public class DeletionService {
    
    private CustomerDAO cdao = DAOContainer.CDAO;
    private FeastOrderDAO fodao = DAOContainer.FODAO;
    private FeastMenuDAO fmdao = DAOContainer.FMDAO;
    
    /**
     * Deletes a customer based on the provided customer code.
     * 
     * @return True if the customer is deleted successfully, false otherwise.
     */
    public boolean deleteCustomer() {
        String code = InputterUtils.inputWithPattern("customer code", CustomerRules.Acceptable.CODE_REGEX);
        String action;
        
        Customer customerToDelete = cdao.delete(code);
        if (customerToDelete == null) {
            ResponseViewUtils.displayError("delete customer", "The customer code does not exist!");
            return false;
        }
        
        System.out.println(MapperUtils.toCustomerDTO(customerToDelete));
        
        if (!InputterUtils.inputConfirm("delete this customer")) {
            if (!cdao.create(customerToDelete)) {
                ResponseViewUtils.displayError("undo deletion of customer", "Internal error!");
                return false;
            }
            action = "Undo deletion of customer with id [" + customerToDelete.getCode() + "] successfully!";
        } else {
            action = "Delete customer with id [" + customerToDelete.getCode() + "] successfully!";
        }
        
        ResponseViewUtils.displayResponse(action);
        return true;
    }
    
    /**
     * Deletes a feast order based on the provided order ID.
     * 
     * @return True if the feast order is deleted successfully, false otherwise.
     */
    public boolean deleteFeastOrder() {
        int id = InputterUtils.inputInteger("feast order id");
        
        if (!fodao.isExist(id)) {
            ResponseViewUtils.displayError("delete order", "The order does not exist!");
            return false;
        }
        
        FeastOrder feastOrder = fodao.delete(id);
        Customer customer = cdao.getCustomerByCode(feastOrder.getCustomerCode());
        FeastMenu feastMenu = fmdao.getFeastMenuByCode(feastOrder.getFeastMenuCode());
        
        System.out.println(MapperUtils.toFeastOrderDTO(id, feastOrder, customer, feastMenu));
        
        if (!InputterUtils.inputConfirm("delete this feast order")) {
            if (fodao.create(feastOrder)) {
                ResponseViewUtils.displayResponse("Undo deletion of order with the feast menu code [" + feastOrder.getFeastMenuCode() + "] successfully!");
                return true;
            } else {
                ResponseViewUtils.displayError("undo deletion of order", "Internal error!");
                return false;
            }
        }
        
        ResponseViewUtils.displayResponse("Delete order with the feast menu code [" + feastOrder.getFeastMenuCode() + "] successfully!");
        return true;
    }
}