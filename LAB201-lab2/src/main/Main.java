package main;

import controller.CliController;
import java.util.Locale;

/**
 * Entry point of the application.
 * <p>
 * This class initializes the application by setting the default locale to US
 * and starting the command-line interface (CLI) controller.
 * </p>
 */
public class Main {
    
    /**
     * Main method that serves as the entry point of the application.
     * 
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Set the default locale to US for consistent formatting
        Locale.setDefault(Locale.US);
        
        // Create and start the CLI controller
        CliController cc = new CliController();
        cc.start();
    }
}
