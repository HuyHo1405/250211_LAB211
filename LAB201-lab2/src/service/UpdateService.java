package service;

import dao.DAOContainer;
import dao.CustomerDAO;
import dao.FeastMenuDAO;
import dao.FeastOrderDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import model.Customer;
import model.FeastMenu;
import model.FeastOrder;
import utils.InputterUtils;
import utils.ResponseViewUtils;
import view.OptionsInfo;
import utils.MapperUtils;
import utils.rules.CustomerRules;
import utils.rules.FeastMenuRules;

/**
 * Service class responsible for updating customer and feast order information.
 */
public class UpdateService {

    private CustomerDAO cdao = DAOContainer.CDAO;
    private FeastOrderDAO fodao = DAOContainer.FODAO;
    private FeastMenuDAO fmdao = DAOContainer.FMDAO;

    /**
     * Checks if a given date is expired.
     * 
     * @param date The date string in format dd/MM/yyyy.
     * @return true if the date is before today, false otherwise.
     * @throws Exception if the date format is invalid.
     */
    private boolean isExpired(String date) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        LocalDate eventDate = LocalDate.parse(date, formatter);
        return eventDate.isBefore(LocalDate.now());
    }
    
    /**
     * Displays an options menu and retrieves the user’s choice.
     * 
     * @param optionsInfo The options menu information.
     * @param minChoice The minimum choice value.
     * @param maxChoice The maximum choice value.
     * @return The selected choice by the user.
     */
    private int getChoice(OptionsInfo optionsInfo, int minChoice, int maxChoice) {
        ResponseViewUtils.displayList(
                optionsInfo.getMenuName(),
                optionsInfo.getMenuOptions());
        return InputterUtils.inputChoice(optionsInfo.getAction(), minChoice, maxChoice);
    }

    /**
     * Retrieves the new value for updating customer information based on user choice.
     * 
     * @param choice The selected update option.
     * @param fieldName The field name being updated.
     * @return The new value for the customer field.
     */
    private String getUpdateCustomerValue(int choice, String fieldName) {
        switch (choice) {
            case 0:
                return InputterUtils.inputName(2, 25);
            case 1:
                return InputterUtils.inputWithPattern(fieldName, CustomerRules.Acceptable.VIETNAMESE_PHONE_REGEX);
            case 2:
                return InputterUtils.inputWithPattern(fieldName, CustomerRules.Acceptable.EMAIL_REGEX);
            default:
                return null;
        }
    }

    /**
     * Retrieves the new value for updating feast order information based on user choice.
     * 
     * @param choice The selected update option.
     * @param fieldName The field name being updated.
     * @return The new value for the feast order field.
     */
    private Object getUpdateFeastOrderValue(int choice, String fieldName) {
        switch (choice) {
            case 0:
                return InputterUtils.inputWithPattern(fieldName, FeastMenuRules.Acceptable.CODE_REGEX);
            case 1:
                return InputterUtils.inputPossitiveInteger(fieldName);
            case 2:
                return InputterUtils.inputFutureDate(fieldName);
            default:
                return null;
        }
    }

    /**
     * Updates customer information.
     * 
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateCustomer() {
        String customerCode = InputterUtils.inputWithPattern("customer code", CustomerRules.Acceptable.CODE_REGEX);

        if (!cdao.isExist(customerCode)) {
            ResponseViewUtils.displayError("find user", "The user code [" + customerCode + "] does not exist!");
            return false;
        }
        
        System.out.println(MapperUtils.toCustomerDTO(cdao.getCustomerByCode(customerCode)));
        
        OptionsInfo optionsInfo = OptionsInfo.CUSTOMER_MODIFICATION_OPTIONS;
        int choice = getChoice(optionsInfo, 1, 3) - 1;
        String fieldName = optionsInfo.getFieldName(choice);
        String value = getUpdateCustomerValue(choice, fieldName);

        if (cdao.update(customerCode, choice, value)) {
            ResponseViewUtils.displayResponse("Update [" + fieldName + "] of customer with code [" + customerCode + "] successfully!");
            return true;
        } else {
            ResponseViewUtils.displayError("update user", "Internal error!");
            return false;
        }
    }

    /**
     * Updates feast order information.
     * 
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateFeastOrder() {
        int feastOrderId = InputterUtils.inputPossitiveInteger("feast order id") - 1;

        if (!fodao.isExist(feastOrderId)) {
            ResponseViewUtils.displayError("find feast order id", "The feast order id [" + feastOrderId + "] does not exist!");
            return false;
        }

        FeastOrder fo = fodao.getFeastOrderById(feastOrderId);
        Customer c = cdao.getCustomerByCode(fo.getCustomerCode());
        FeastMenu fm = fmdao.getFeastMenuByCode(fo.getFeastMenuCode());
        
        try {
            if (isExpired(fo.getDate())) {
                ResponseViewUtils.displayError("find feast order id", "The event date is expired!");
                return false;
            }
        } catch (DateTimeParseException e) {
            ResponseViewUtils.displayError("Invalid date format", "The provided date [" + fo.getDate() + "] is invalid!");
            return false;
        } catch (Exception e) {
            ResponseViewUtils.displayError("find feast order id", "Internal error!");
            return false;
        }

        System.out.println(MapperUtils.toFeastOrderDTO(feastOrderId, fo, c, fm));
        
        OptionsInfo optionsInfo = OptionsInfo.FEAST_ORDER_MODIFICATION_OPTIONS;
        int choice = getChoice(optionsInfo, 1, 3) - 1;
        String fieldName = optionsInfo.getFieldName(choice);
        Object value = getUpdateFeastOrderValue(choice, fieldName);

        if (fodao.update(feastOrderId, choice, value)) {
            ResponseViewUtils.displayResponse("Update [" + fieldName + "] of feast order with id [#" + (feastOrderId + 1) + "] successfully!");
            return true;
        } else {
            ResponseViewUtils.displayError("update feast order id", "Internal error!");
            return false;
        }
    }
}
