package controller;

import view.OptionsInfo;
import service.CreationService;
import service.UpdateService;
import service.RetrievalService;
import service.SaveService;
import utils.InputterUtils;
import utils.ResponseViewUtils;
import utils.rules.CustomerRules;
import utils.rules.FeastOrderRules;

/**
 * Controller class that manages business operations related to customers,
 * feast orders, and data persistence.
 */
public class BusinessController {
    
    private CreationService cs = new CreationService();
    private RetrievalService rs = new RetrievalService();
    private UpdateService us = new UpdateService();
    private SaveService ss = new SaveService();

    /**
     * Displays a menu based on the provided options and retrieves the user's choice.
     *
     * @param optionsInfo The menu options and action prompt.
     * @return The selected option index.
     */
    private int getChoice(OptionsInfo optionsInfo) {
        ResponseViewUtils.displayList(
                optionsInfo.getMenuName(),
                optionsInfo.getMenuOptions());
        return InputterUtils.inputChoice(optionsInfo.getAction(), 1, optionsInfo.getMenuOptions().length);
    }
    
    /**
     * Registers a new customer.
     */
    public void registerCustomer() {
        cs.addCustomer();
    }

    /**
     * Updates existing customer information.
     */
    public void updateCustomer() {
        us.updateCustomer();
    }

    /**
     * Searches for customers by name and displays the results in a table format.
     */
    public void searchCustomer() {
        ResponseViewUtils.displayTable(CustomerRules.Format.TABLE_HEADER, rs.searchCustomerName());
    }

    /**
     * Displays a list of available feast menus for ordering.
     */
    public void displayFeastMenus() {
        ResponseViewUtils.displayTable(String.format("%-111s", "List of Set Menu for ordering party"), rs.retrieveAllFeastMenu());
    }

    /**
     * Places a new feast order.
     */
    public void placeFeastOrder() {
        cs.addFeastOrder();
    }

    /**
     * Updates an existing feast order.
     */
    public void updateOrderInformation() {
        us.updateFeastOrder();
    }

    /**
     * Saves all data to a file for persistence.
     */
    public void saveDataToFile() {
        ss.save();
    }

    /**
     * Displays a list of customers or feast orders based on the user's choice.
     */
    public void displayCustomerOrOrderLists() {
        switch (getChoice(OptionsInfo.DISPLAY_ORDER_OR_CUSTOMER)) {
            case 1:
                ResponseViewUtils.displayTable(CustomerRules.Format.TABLE_HEADER, rs.retrieveAllCustomer());
                break;
            case 2:
                ResponseViewUtils.displayTable(FeastOrderRules.Format.TABLE_HEADER, rs.retrieveAllFeastOrder());
                break;
        }
    }

    /**
     * Confirms and saves changes before exiting the application.
     */
    public void exit() {
        ss.confirmChanges();
    }
}
