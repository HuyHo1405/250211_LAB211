package controller;

import utils.InputterUtils;
import utils.ResponseViewUtils;
import view.MainMenuView;

/**
 * CLI Controller that manages user interactions via the command line.
 * It displays menus, handles user input, and invokes appropriate business operations.
 */
public class CliController {

    private MainMenuView mmv = new MainMenuView();
    private BusinessController bc = new BusinessController();

    /**
     * Starts the CLI application, displaying menus and handling user choices.
     */
    public void start() {
        int choice = -1;
        
        mmv.displaySystemName();
        while (choice != 9) {
            mmv.displayMainMenu();
            
            choice = InputterUtils.inputChoice("choice", 1, 9);
            mmv.displayChoice(choice - 1);
            
            handle(choice);
        }
        
        mmv.displayGoodbye();
    }

    /**
     * Handles user choices by invoking the corresponding business operation.
     *
     * @param choice The user's menu selection.
     */
    private void handle(int choice) {
        switch (choice) {
            case 1:
                bc.registerCustomer();
                break;
            case 2:
                bc.updateCustomer();
                break;
            case 3:
                bc.searchCustomer();
                break;
            case 4:
                bc.displayFeastMenus();
                break;
            case 5:
                bc.placeFeastOrder();
                break;
            case 6:
                bc.updateOrderInformation();
                break;
            case 7:
                bc.saveDataToFile();
                break;
            case 8:
                bc.displayCustomerOrOrderLists();
                break;
            case 9:
                bc.exit();
                break;
        }
        if (choice != 9) {
            ResponseViewUtils.displayLine();
            InputterUtils.reminder();
        }
    }
}
