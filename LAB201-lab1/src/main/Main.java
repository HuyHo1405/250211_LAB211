package main;

import contronller.CliController;

/**
 * The entry point for the registration management application.
 * <p>
 * This class initializes the CLI controller and starts the application.
 * </p>
 * 
 * @author ho huy
 */
public class Main {

    /**
     * The CLI controller used to interact with the user.
     */
    private static CliController cc = new CliController();

    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        cc.start();
    }
}
