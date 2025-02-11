package utils.inputter;

import java.util.Scanner;

/**
 * Utility class for handling console input operations.
 * <p>
 * This class provides methods to prompt the user for various types of input,
 * including integer choices, non-empty strings, and inputs matching specific patterns.
 * It also includes methods for confirmation prompts and yes/no questions.
 * </p>
 * 
 * @author ho huy
 */
public class Inputter {

    // Private fields for message templates
    private static String inputMSG = "~~Please enter the input for %s: ";
    private static String errorMSG = "\n>>Invalid input for %s!"
            + "\nReason: %s"
            + "\n\n";
    private static String reminderMSG = "##Reminder: Press [enter] to go back to menu: ";
    private static String confirmMSG = "~~Please confirm that you want to %s? [y/n]: ";
    private static String questionMSG = "~~%s [y/n]: ";
    private static Scanner sc = new Scanner(System.in);

    // Public common regex patterns
    private static final String YN_REGEX = "^[yYnN]$";
    /**
     * Regular expression for validating a name.
     * <p>
     * The name must start with an uppercase letter followed by lowercase letters,
     * and may include multiple words separated by a space.
     * </p>
     */
    public static final String NAME_REGEX = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";

    /**
     * Prompts the user with a formatted yes/no question until a valid response is provided.
     *
     * @param format the message format for the prompt, expecting a parameter substitution
     * @param param  the parameter to be substituted into the format string
     * @return {@code true} if the user's input indicates "yes"; {@code false} otherwise
     */
    private static boolean inputYN(String format, String param) {
        String input;
        while (true) {
            System.out.format(format, param);
            input = sc.nextLine();
            if (!input.matches(YN_REGEX)) {
                System.out.format(errorMSG, "boolean input for question", "The input format must be in [y/n]!");
            } else {
                break;
            }
        }
        return toBoolean(input);
    }

    /**
     * Converts a yes/no input string to a boolean value.
     *
     * @param input the input string representing yes/no
     * @return {@code true} if the input is "y" or "Y"; {@code false} if it is "n" or "N"
     */
    private static boolean toBoolean(String input) {
        switch (input) {
            case "y":
            case "Y":
                return true;
            case "n":
            case "N":
                return false;
        }
        return false;
    }

    /**
     * Prompts the user to enter an integer choice within a specified range.
     * <p>
     * The method repeatedly prompts until a valid integer within the range [min, max] is entered.
     * </p>
     *
     * @param fieldName the name of the field or option for which input is requested
     * @param min       the minimum acceptable value (inclusive)
     * @param max       the maximum acceptable value (inclusive)
     * @return the integer value entered by the user that is within the specified range
     */
    public static int inputChoice(String fieldName, int min, int max) {
        while (true) {
            try {
                System.out.format(inputMSG, fieldName);
                int input = Integer.parseInt(sc.nextLine());
                if (input > max || input < min) {
                    throw new IllegalArgumentException();
                }
                System.out.println("");
                return input;
            } catch (NumberFormatException e) {
                System.out.format(errorMSG, fieldName, "Input format must be an integer!");
            } catch (IllegalArgumentException e) {
                System.out.format(errorMSG, fieldName, "The " + fieldName + " must be between " + min + " and " + max + '!');
            }
        }
    }

    /**
     * Prompts the user to enter a non-empty string.
     * <p>
     * The method repeatedly prompts until the user enters a string that is not empty.
     * </p>
     *
     * @param fieldName the name of the field for which input is requested
     * @return the non-empty string entered by the user
     */
    public static String inputNonEmptyString(String fieldName) {
        while (true) {
            System.out.format(inputMSG, fieldName);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.format(errorMSG, fieldName, "Input must be a non-empty string!");
            } else {
                System.out.println();
                return input;
            }
        }
    }

    /**
     * Prompts the user to enter a string that matches the specified regular expression.
     * <p>
     * If the provided regex equals the predefined {@link #NAME_REGEX}, a specific error message
     * regarding the length requirement is displayed. Otherwise, a generic error message is shown
     * if the input does not match the regex.
     * </p>
     *
     * @param fieldName the name of the field for which input is requested
     * @param regex     the regular expression that the input must match
     * @return the string entered by the user that matches the given regular expression
     */
    public static String inputMatchPattern(String fieldName, String regex) {
        while (true) {
            System.out.format(inputMSG, fieldName);
            String input = sc.nextLine().trim();
            if (!input.matches(regex) && regex.equals(NAME_REGEX)) {
                System.out.format(errorMSG, fieldName, "The " + fieldName + " must have the length between 2 to 20 characters!");
            } else if (!input.matches(regex)) {
                System.out.format(errorMSG, fieldName, "Input must match the pattern for " + fieldName + '!');
            } else {
                System.out.println();
                return input;
            }
        }
    }

    /**
     * Prompts the user to press [enter] to return to the menu.
     */
    public static void back() {
        System.out.print(reminderMSG);
        sc.nextLine();
        System.out.println("");
    }

    /**
     * Prompts the user to confirm an action using a yes/no input.
     *
     * @param action the action to be confirmed
     * @return {@code true} if the user confirms the action; {@code false} otherwise
     */
    public static boolean inputConfirm(String action) {
        return inputYN(confirmMSG, action);
    }

    /**
     * Prompts the user with a yes/no question.
     *
     * @param question the question to be asked
     * @return {@code true} if the user answers yes; {@code false} if the user answers no
     */
    public static boolean inputBooleanAnswer(String question) {
        return inputYN(questionMSG, question);
    }
}
