package view;

import utils.inputter.Inputter;
import utils.viewUtils.ResponseViewUitls;

/**
 * Provides a view for displaying the system menu.
 * <p>
 * This class is responsible for displaying the system name, menu options,
 * and various view-related prompts and separators.
 * </p>
 * 
 * @author ho huy
 */
public class MenuView {

    /**
     * The system name displayed at the top of the menu.
     */
    private final String SYSTEM_NAME = 
"   _____                       __         .__           ___ ___ .__ __   .__               \n" +
"  /     \\   ____  __ __  _____/  |______  |__| ____    /   |   \\|__|  | _|__| ____   ____  \n" +
" /  \\ /  \\ /  _ \\|  |  \\/    \\   __\\__  \\ |  |/    \\  /    ~    |  |  |/ |  |/    \\ / ___\\ \n" +
"/    Y    (  <_> |  |  |   |  |  |  / __ \\|  |   |  \\ \\    Y    |  |    <|  |   |  / /_/  >\n" +
"\\____|__  /\\____/|____/|___|  |__| (____  |__|___|  /  \\___|_  /|__|__|_ |__|___|  \\___  / \n" +
"        \\/                  \\/          \\/        \\/         \\/         \\/       \\/_____/  \n" +
"__________              .__         __                 __  .__                             \n" +
"\\______   \\ ____   ____ |__| ______/  |_____________ _/  |_|__| ____   ____                \n" +
" |       __/ __ \\ / ___\\|  |/  ___\\   __\\_  __ \\__  \\\\   __|  |/  _ \\ /    \\               \n" +
" |    |   \\  ___// /_/  |  |\\___ \\ |  |  |  | \\// __ \\|  | |  (  <_> |   |  \\              \n" +
" |____|_  /\\___  \\___  /|__/____  >|__|  |__|  (____  |__| |__|\\____/|___|  /              \n" +
"        \\/     \\/_____/         \\/                  \\/                    \\/               ";

    /**
     * An array of menu options.
     */
    private String[] menu = {
        "New Registration.",
        "Update Registration Information.",
        "Display Registered List.",
        "Delete Registration Information.",
        "Search Participants by Name.",
        "Filter Data by Campus.",
        "Statistics of Registration Numbers by Location.",
        "Save Data to File.",
        "Exit the Program."
    };

    /**
     * Displays the main menu.
     * <p>
     * This method prints a separator line, the menu title, and each menu option.
     * </p>
     */
    public void displayMenu() {
        displayLine();
        System.out.println(">>Menu Options");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(String.format("  %02d. %s", i + 1, menu[i]));
        }
    }

    /**
     * Displays the chosen menu option.
     * <p>
     * If the choice is within the valid range, a separator line is printed followed by
     * the selected menu option.
     * </p>
     *
     * @param choice the number representing the chosen menu option
     */
    public void displayChoice(int choice) {
        if (choice >= 1 && choice <= 9) {
            displayLine();
            System.out.println(">>" + menu[choice - 1]);
        }
    }

    /**
     * Displays a separator line.
     * <p>
     * The separator line is generated by the {@link ResponseViewUitls#line()} method.
     * </p>
     */
    public void displayLine() {
        System.out.println(ResponseViewUitls.line());
    }

    /**
     * Displays the system name.
     * <p>
     * The system name is printed with a preceding newline for proper formatting.
     * </p>
     */
    public void displaySystemName() {
        System.out.println("\n" + SYSTEM_NAME);
    }

    /**
     * Displays a reminder to the user and waits for confirmation.
     * <p>
     * A separator line is printed followed by a prompt to press Enter to return to the menu.
     * </p>
     */
    public void reminder() {
        System.out.println(ResponseViewUitls.line());
        Inputter.back();
    }
}
