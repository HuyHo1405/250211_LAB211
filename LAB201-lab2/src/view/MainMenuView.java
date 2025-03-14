/*
 * This class represents the main menu view of the system.
 * It provides methods to display system information, menu options, and user choices.
 */
package view;

import utils.ResponseViewUtils;

/**
 * The MainMenuView class handles displaying the system name, main menu, and user choices.
 */
public class MainMenuView {

    /**
     * ASCII art representing the system name.
     */
    private final String SYSTEM_NAME = " /$$$$$$$$                       /$$ /$$   /$$     /$$                               /$$                      \n" +
"|__  $$__/                      | $$|__/  | $$    |__/                              | $$                      \n" +
"   | $$  /$$$$$$  /$$$$$$   /$$$$$$$ /$$ /$$$$$$   /$$  /$$$$$$  /$$$$$$$   /$$$$$$ | $$                      \n" +
"   | $$ /$$__  $$|____  $$ /$$__  $$| $$|_  $$_/  | $$ /$$__  $$| $$__  $$ |____  $$| $$                      \n" +
"   | $$| $$  \\__/ /$$$$$$$| $$  | $$| $$  | $$    | $$| $$  \\ $$| $$  \\ $$  /$$$$$$$| $$                      \n" +
"   | $$| $$      /$$__  $$| $$  | $$| $$  | $$ /$$| $$| $$  | $$| $$  | $$ /$$__  $$| $$                      \n" +
"   | $$| $$     |  $$$$$$$|  $$$$$$$| $$  |  $$$$/| $$|  $$$$$$/| $$  | $$|  $$$$$$$| $$                      \n" +
"   |__/|__/      \\_______/ \\_______/|__/   \\___/  |__/ \\______/ |__/  |__/ \\_______/|__/                      \n" +
"                                                                                                              \n" +
"                                                                                                              \n" +
"                                                                                                              \n" +
" /$$$$$$$$                              /$$            /$$$$$$                  /$$                           \n" +
"| $$_____/                             | $$           /$$__  $$                | $$                           \n" +
"| $$     /$$$$$$   /$$$$$$   /$$$$$$$ /$$$$$$        | $$  \\ $$  /$$$$$$   /$$$$$$$  /$$$$$$   /$$$$$$        \n" +
"| $$$$$ /$$__  $$ |____  $$ /$$_____/|_  $$_/        | $$  | $$ /$$__  $$ /$$__  $$ /$$__  $$ /$$__  $$       \n" +
"| $$__/| $$$$$$$$  /$$$$$$$|  $$$$$$   | $$          | $$  | $$| $$  \\__/| $$  | $$| $$$$$$$$| $$  \\__/       \n" +
"| $$   | $$_____/ /$$__  $$ \\____  $$  | $$ /$$      | $$  | $$| $$      | $$  | $$| $$_____/| $$             \n" +
"| $$   |  $$$$$$$|  $$$$$$$ /$$$$$$$/  |  $$$$/      |  $$$$$$/| $$      |  $$$$$$$|  $$$$$$$| $$             \n" +
"|__/    \\_______/ \\_______/|_______/    \\___/         \\______/ |__/       \\_______/ \\_______/|__/             \n" +
"                                                                                                              \n" +
"                                                                                                              \n" +
"                                                                                                              \n" +
" /$$      /$$                                                                                       /$$       \n" +
"| $$$    /$$$                                                                                      | $$       \n" +
"| $$$$  /$$$$  /$$$$$$  /$$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$  /$$$$$$/$$$$   /$$$$$$  /$$$$$$$  /$$$$$$     \n" +
"| $$ $$/$$ $$ |____  $$| $$__  $$ |____  $$ /$$__  $$ /$$__  $$| $$_  $$_  $$ /$$__  $$| $$__  $$|_  $$_/     \n" +
"| $$  $$$| $$  /$$$$$$$| $$  \\ $$  /$$$$$$$| $$  \\ $$| $$$$$$$$| $$ \\ $$ \\ $$| $$$$$$$$| $$  \\ $$  | $$       \n" +
"| $$\\  $ | $$ /$$__  $$| $$  | $$ /$$__  $$| $$  | $$| $$_____/| $$ | $$ | $$| $$_____/| $$  | $$  | $$ /$$   \n" +
"| $$ \\/  | $$|  $$$$$$$| $$  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$| $$ | $$ | $$|  $$$$$$$| $$  | $$  |  $$$$//$$\n" +
"|__/     |__/ \\_______/|__/  |__/ \\_______/ \\____  $$ \\_______/|__/ |__/ |__/ \\_______/|__/  |__/   \\___/ |__/\n" +
"                                            /$$  \\ $$                                                         \n" +
"                                           |  $$$$$$/                                                         \n" +
"                                            \\______/                                                          ";
    /**
     * Options for the main menu.
     */
    private final OptionsInfo MAIN_MENU = OptionsInfo.MAIN_MENU;
    
    /**
     * Displays the system name in ASCII art format.
     */
    public void displaySystemName(){
        System.out.println(SYSTEM_NAME);
        ResponseViewUtils.displayLine();
    }
    
    /**
     * Displays the main menu options.
     */
    public void displayMainMenu(){
        ResponseViewUtils.displayList(MAIN_MENU.getMenuName(), MAIN_MENU.getMenuOptions());
    }
    
    /**
     * Displays the user's selected choice from the menu.
     * 
     * @param choice The index of the chosen menu option.
     */
    public void displayChoice(int choice){
        ResponseViewUtils.displayLine();
        ResponseViewUtils.displayResponse(MAIN_MENU.getMenuOptions()[choice]);
    }
    
    /**
     * Displays a farewell message when the program exits.
     */
    public void displayGoodbye(){
        System.out.println("Thank you for using the registration management program!");
    }
    
}
