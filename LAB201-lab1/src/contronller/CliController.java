package contronller;

import view.MenuView;
import dao.MountainDAO;
import dao.RegistrationDAO;
import utils.inputter.Inputter;

/**
 * Command Line Interface (CLI) controller for the registration management system.
 * <p>
 * This class handles user interactions by displaying a menu and mapping user inputs
 * to the corresponding business operations.
 * </p>
 * 
 * @author ho huy
 */
public class CliController {

    // Fields

    /**
     * The menu view used for displaying the system name, menu options, and reminders.
     */
    private MenuView mv = new MenuView();
    
    /**
     * The Data Access Object for managing registration records.
     */
    private RegistrationDAO rdao = new RegistrationDAO();
    
    /**
     * The Data Access Object for managing mountain records.
     */
    private MountainDAO mdao = new MountainDAO();
    
    /**
     * The business controller that handles registration-related operations.
     */
    private BusinesssController bc = new RegistrationController(rdao, mdao);

    /**
     * Starts the CLI application.
     * <p>
     * This method displays the system name, then repeatedly shows the menu, retrieves the user's choice,
     * and dispatches the choice to the appropriate business logic until the user selects the exit option.
     * </p>
     */
    public void start() {
        int choice;
        mv.displaySystemName();
        do {
            mv.displayMenu();

            choice = Inputter.inputChoice("menu choice", 1, 9);
            mv.displayChoice(choice);

            mapHandler(choice);
        } while (choice != 9);
    }

    /**
     * Maps the user's menu input to the corresponding business operation.
     *
     * @param input the user's menu choice as an integer
     */
    private void mapHandler(int input) {
        switch (input) {
            case 1:
                bc.add();
                break;
            case 2:
                bc.update();
                break;
            case 3:
                bc.displayAll();
                break;
            case 4:
                bc.delete();
                break;
            case 5:
                bc.searchByName();
                break;
            case 6:
                bc.searchByCampus();
                break;
            case 7:
                bc.statistics();
                break;
            case 9:
                if (!Inputter.inputConfirm("save the current changes")) {
                    System.out.println("Thank you for using the registration management program!");
                    break;
                }
                System.out.println("Thank you for using the registration management program!");
                // Fall-through intended to save data before exiting
            case 8:
                rdao.save();
                mdao.save();
                break;
        }
        if (input != 8 && input != 9) {
            mv.reminder();
        }
    }
}
