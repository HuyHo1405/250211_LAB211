package view;

/**
 * Enum representing different menu options in the system.
 * Each enum constant contains a menu name and a list of available options.
 */
public enum OptionsInfo {
    /**
     * Main menu options available in the system.
     */
    MAIN_MENU("Main Menu Options", new String[]{
        "Register customers.",
        "Update customer information.",
        "Search for customer information by name.",
        "Display feast menus.",
        "Place a feast order.",
        "Update order information.",
        "Save data to file.",
        "Display Customer or Order lists.",
        "Quit."
    }),
    
    /**
     * Options for displaying customer or order lists.
     */
    DISPLAY_ORDER_OR_CUSTOMER("Display list", new String[]{
        "Display Customer lists.",
        "Display Order lists."
    }),
    
    /**
     * Options for modifying customer information.
     */
    CUSTOMER_MODIFICATION_OPTIONS("Update Customer Information Options", new String[]{
        "Name",
        "Phone Number",
        "Email"
    }),
    
    /**
     * Options for modifying feast order details.
     */
    FEAST_ORDER_MODIFICATION_OPTIONS("Update Feast Order Information Options", new String[]{
        "Code of Set Menu",
        "Number of Tables",
        "Preferred Event Date"
    });

    /**
     * The name of the menu.
     */
    private String menuName;
    
    /**
     * The list of menu options.
     */
    private String[] menuOptions;

    /**
     * Constructor to initialize menu name and menu options.
     * 
     * @param menuName The name of the menu.
     * @param menuOptions The array of menu options.
     */
    OptionsInfo(String menuName, String[] menuOptions) {
        this.menuName = menuName;
        this.menuOptions = menuOptions;
    }

    /**
     * Retrieves the menu name.
     * 
     * @return The name of the menu.
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * Retrieves the list of menu options.
     * 
     * @return The array of menu options.
     */
    public String[] getMenuOptions() {
        return menuOptions;
    }
    
    /**
     * Retrieves the menu name in lowercase format for action-related processing.
     * 
     * @return The menu name in lowercase.
     */
    public String getAction(){
        return menuName.toLowerCase();
    }
    
    /**
     * Retrieves a specific field name from the menu options in lowercase format.
     * 
     * @param index The index of the field name.
     * @return The field name in lowercase.
     */
    public String getFieldName(int index){
        return menuOptions[index].toLowerCase();
    }
}