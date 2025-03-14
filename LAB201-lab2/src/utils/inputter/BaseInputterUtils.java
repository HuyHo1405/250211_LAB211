package utils.inputter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;
import utils.rules.InputterRules;

/**
 * Utility class for handling various types of user input, including boolean,
 * integer, string, and date inputs, with validation and error handling.
 */
public abstract class BaseInputterUtils {
    
    /** Scanner instance for reading user input. */
    private static Scanner sc = new Scanner(System.in);
    
    /**
     * Displays an error message for invalid input.
     *
     * @param fieldName The name of the field where the error occurred.
     * @param reason The reason why the input is invalid.
     */
    private static void displayError(String fieldName, String reason) {
        System.out.println(String.format(InputterRules.Format.ERROR_MSG, fieldName, reason));
    }
    
    /**
     * Displays a prompt message for user input.
     *
     * @param fieldName The name of the field being requested.
     */
    private static void displayInput(String fieldName) {
        System.out.format(InputterRules.Format.INPUT_MSG, fieldName);
    }
    
    /**
     * Displays a reminder message and waits for user confirmation.
     */
    public static void reminder(){
        System.out.print(InputterRules.Format.REMINDER_MSG);
        sc.nextLine();
    }
    
    /**
     * Gets a boolean input from the user, ensuring it matches 'y' or 'n'.
     *
     * @param format The prompt message format.
     * @param param The parameter to be included in the prompt message.
     * @return true if the user inputs 'y', false if the user inputs 'n'.
     */
    public static boolean inputBoolean(String format, String param) {
        String input;
        while (true) {
            System.out.format(format, param);
            input = sc.nextLine();
            if (!input.matches(InputterRules.Acceptable.YN_REGEX)) {
                displayError("boolean input for question", "The input format must be in [y/n]!");
            } else {
                return input.equalsIgnoreCase("y");
            }
        }
    }
    
    /**
     * Gets an integer input from the user within a specified range.
     *
     * @param fieldName The name of the field being requested.
     * @param min The minimum allowed value.
     * @param max The maximum allowed value.
     * @param isPositive Whether the value must be positive.
     * @return The validated integer input.
     */
    public static int inputInteger(String fieldName, int min, int max, boolean isPositive) {
        while (true) {
            try {
                displayInput(fieldName);
                int input = Integer.parseInt(sc.nextLine());
                
                if(isPositive && input <= 0){
                    displayError(fieldName, "The " + fieldName + " must be a positive integer!");
                    continue;
                }
                
                if (input > max || input < min) {
                    displayError(fieldName, "The " + fieldName + " must be between " + min + " and " + max + "!");
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                displayError(fieldName, "Input format must be an integer!");
            }
        }
    }
    
    /**
     * Gets a string input from the user with validation for length and format.
     *
     * @param fieldName The name of the field being requested.
     * @param regex The regular expression pattern for validation.
     * @param minLength The minimum length of the string.
     * @param maxLength The maximum length of the string.
     * @param formatReason The error message for format violations.
     * @param lengthReason The error message for length violations.
     * @return The validated string input.
     */
    public static String inputString(String fieldName, String regex, int minLength, int maxLength, String formatReason, String lengthReason) {
        String input;
        while (true) {
            displayInput(fieldName);
            input = sc.nextLine().trim();
            
            if (input.isEmpty()) {
                displayError(fieldName, "Input must be a non-empty string!");
                continue;
            }
            
            if (minLength > 0 && maxLength > 0) {
                if(input.length() < minLength || input.length() > maxLength){
                    displayError(fieldName, lengthReason);
                    continue;
                }
            }
            
            if (regex != null && !input.matches(regex)) {
                displayError(fieldName, formatReason);
                continue;
            }
            
            return input;
        }
    }
    
    /**
     * Gets a future date input from the user in 'dd/MM/yyyy' format.
     *
     * @param event The name of the event associated with the date.
     * @return The validated future date as a string.
     */
    public static String inputFutureDate(String event) {
        while (true) {
            displayInput("date of " + event + " [dd/MM/yyyy]");
            String input = sc.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
                LocalDate eventDate = LocalDate.parse(input, formatter);
                if(!eventDate.isAfter(LocalDate.now())){
                    displayError("date of " + event, "The date must be in the future!");
                    continue;
                }
                return input;
            } catch (Exception e) {
                displayError("date of " + event, "Wrong format or value for date!");
            }
        }
    }
}
