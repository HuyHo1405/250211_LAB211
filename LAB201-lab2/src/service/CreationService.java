package service;

import dao.CustomerDAO;
import dao.DAOContainer;
import dao.FeastMenuDAO;
import dao.FeastOrderDAO;
import model.Customer;
import model.FeastOrder;
import utils.InputterUtils;
import utils.ResponseViewUtils;
import utils.rules.CustomerRules;
import utils.rules.FeastMenuRules;

/**
 * Service class responsible for handling the creation of customers and feast orders.
 */
public class CreationService {
    
    private CustomerDAO cdao = DAOContainer.CDAO;
    private FeastMenuDAO fmdao = DAOContainer.FMDAO;
    private FeastOrderDAO fodao = DAOContainer.FODAO;
    
    /**
     * Checks if a feast order is duplicated in the existing orders.
     * 
     * @param feastOrder The feast order to check for duplication.
     * @return True if the order is duplicated, false otherwise.
     */
    private boolean isDuplicated(FeastOrder feastOrder){
        for (FeastOrder o : fodao.getList()) {
            if(!o.getCustomerCode().equals(feastOrder.getCustomerCode())){
                continue;
            }
            if(!o.getFeastMenuCode().equals(feastOrder.getFeastMenuCode())){
                continue;
            }
            if(!o.getDate().equals(feastOrder.getDate())){
                continue;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Prompts the user to input customer details and creates a Customer object.
     * 
     * @return A new Customer object based on user input.
     */
    private Customer inputCustomer() {
        return new Customer(
                InputterUtils.inputWithPattern("customer code", CustomerRules.Acceptable.CODE_REGEX),
                InputterUtils.inputName(2, 25),
                InputterUtils.inputWithPattern("customer email", CustomerRules.Acceptable.EMAIL_REGEX),
                InputterUtils.inputWithPattern("customer phone", CustomerRules.Acceptable.VIETNAMESE_PHONE_REGEX)
        );
    }
    
    /**
     * Prompts the user to input feast order details and creates a FeastOrder object.
     * 
     * @return A new FeastOrder object based on user input.
     */
    private FeastOrder inputFeastOrder(){
        return new FeastOrder(
                InputterUtils.inputWithPattern("customer code", CustomerRules.Acceptable.CODE_REGEX),
                InputterUtils.inputWithPattern("code of set menu", FeastMenuRules.Acceptable.CODE_REGEX),
                InputterUtils.inputFutureDate("event"),
                InputterUtils.inputPossitiveInteger("number of the table")
        );
    }
    
    /**
     * Adds a new customer to the system.
     * 
     * @return True if the customer is added successfully, false otherwise.
     */
    public boolean addCustomer(){
        Customer customer = inputCustomer();
        
        if(cdao.isExist(customer.getCode())){
            ResponseViewUtils.displayError("create customer", "Customer code [" + customer.getCode() + "] already exist!");
            return false;
        }
        
        if(!cdao.create(customer)){
            ResponseViewUtils.displayError("create customer", "Internal error!");
            return false;
        }
        
        ResponseViewUtils.displayResponse("Create customer with code[" + customer.getCode()+"] successfully!");
        return true;
    }
    
    /**
     * Adds a new feast order to the system.
     * 
     * @return True if the feast order is added successfully, false otherwise.
     */
    public boolean addFeastOrder(){
        FeastOrder feastOrder = inputFeastOrder();
        String errorReason = null;
        
        if(!cdao.isExist(feastOrder.getCustomerCode())){
            errorReason = "The customer code does not exist in the data!";
        } else if(!fmdao.isExist(feastOrder.getFeastMenuCode())){
            errorReason = "The feast menu code does not exist in the data!";
        } else if(isDuplicated(feastOrder)){
            errorReason = "The feast order is duplicated!";
        }
        
        if(errorReason != null){
            ResponseViewUtils.displayError("create order", errorReason);
            return false;
        }
        
        if(!fodao.create(feastOrder)){
            ResponseViewUtils.displayError("create order", "Internal error!");
            return false;
        }
            
        ResponseViewUtils.displayResponse("Create order with the menu code [" + feastOrder.getFeastMenuCode() + "] successfully!");
        return true;
    }
}
