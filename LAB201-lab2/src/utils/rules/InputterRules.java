/*
 * This interface defines the input validation and formatting rules.
 * It includes messages for input prompts, errors, reminders, and confirmations.
 * Additionally, it specifies acceptable input patterns for various data types.
 */
package utils.rules;

/**
 * Interface defining rules for user input handling.
 */
public interface InputterRules {

    /**
     * Formatting rules for input messages and error prompts.
     */
    public interface Format {

        /**
         * Message displayed when requesting user input.
         */
        static final String INPUT_MSG = 
                  "~~Please enter the input for %s: ";

        /**
         * Error message format for invalid inputs.
         */
        static final String ERROR_MSG = 
                  "\n>> Invalid input for %s!\n"
                + "Reason: %s\n";

        /**
         * Reminder message prompting the user to return to the menu.
         */
        static final String REMINDER_MSG = 
                  "## Reminder: Press [enter] to go back to menu: ";

        /**
         * Confirmation message for user actions.
         */
        static final String CONFIRM_MSG = 
                  "~~Please confirm that you want to %s? [y/n]: ";

        /**
         * Message format for yes/no questions.
         */
        static final String QUESTION_MSG = 
                  "~~%s [y/n]: ";
    }

    /**
     * Acceptable validation rules for user input.
     */
    public interface Acceptable {

        /**
         * Regex pattern for yes/no input validation.
         */
        static final String YN_REGEX = "^[yYnN]$";

        /**
         * Regex pattern for validating names (must start with uppercase letters).
         */
        static final String NAME_REGEX = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";

        /**
         * Regex pattern for date validation (dd/MM/yyyy format).
         */
        static final String DATE_REGEX = "([0-9]{2})/+([0-9]{2})/+([0-9]{4})\\b";
    }
}
