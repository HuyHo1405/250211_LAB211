package utils;

import utils.inputter.BaseInputterUtils;
import utils.rules.InputterRules;

/**
 * Utility class for handling various user input operations.
 */
public class InputterUtils {
    
    /**
     * Prompts the user with a yes/no question.
     * 
     * @param question The question to be asked.
     * @return true if the user confirms, false otherwise.
     */
    public static boolean inputBoolean(String question) {
        return BaseInputterUtils.inputBoolean(InputterRules.Format.QUESTION_MSG, question);
    }
    
    /**
     * Prompts the user to confirm an action.
     * 
     * @param action The action to be confirmed.
     * @return true if the user confirms, false otherwise.
     */
    public static boolean inputConfirm(String action) {
        return BaseInputterUtils.inputBoolean(InputterRules.Format.CONFIRM_MSG, action);
    }
    
    /**
     * Displays a reminder message to the user.
     */
    public static void reminder() {
        BaseInputterUtils.reminder();
    }
    
    /**
     * Prompts the user to input a string value.
     * 
     * @param fieldName The name of the field being input.
     * @return The user-inputted string.
     */
    public static String inputString(String fieldName) {
        return BaseInputterUtils.inputString(
                fieldName, 
                null, 
                0, 0, 
                null, 
                null);
    }
    
    /**
     * Prompts the user to input a string that matches a given pattern.
     * 
     * @param fieldName The name of the field being input.
     * @param regex The regular expression pattern to validate input.
     * @param reason The reason for requiring this pattern.
     * @return The user-inputted string.
     */
    public static String inputWithPattern(String fieldName, String regex, String reason) {
        return BaseInputterUtils.inputString(
                fieldName, 
                regex, 
                0, 0, 
                reason, 
                null);
    }
    
    /**
     * Prompts the user to input a string that matches a given pattern.
     * 
     * @param fieldName The name of the field being input.
     * @param regex The regular expression pattern to validate input.
     * @return The user-inputted string.
     */
    public static String inputWithPattern(String fieldName, String regex) {
        return BaseInputterUtils.inputString(
                fieldName, 
                regex, 
                0, 0, 
                "Wrong format for " + fieldName + '!', 
                null);
    }
    
    /**
     * Prompts the user to input a name with a given length range.
     * 
     * @param minLength The minimum length of the name.
     * @param maxLength The maximum length of the name.
     * @return The user-inputted name.
     */
    public static String inputName(int minLength, int maxLength) {
        return BaseInputterUtils.inputString(
                "name", 
                InputterRules.Acceptable.NAME_REGEX, 
                minLength, maxLength, 
                "Wrong format for name", 
                "The name must have a length between " + minLength + " and " + maxLength + '!');
    }
    
    /**
     * Prompts the user to input a future date.
     * 
     * @param eventName The name of the event associated with the date.
     * @return The user-inputted future date.
     */
    public static String inputFutureDate(String eventName) {
        return BaseInputterUtils.inputFutureDate(eventName);
    }
    
    /**
     * Prompts the user to input an integer.
     * 
     * @param fieldName The name of the field being input.
     * @return The user-inputted integer.
     */
    public static int inputInteger(String fieldName) {
        return BaseInputterUtils.inputInteger(fieldName, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
    }
    
    /**
     * Prompts the user to input a positive integer.
     * 
     * @param fieldName The name of the field being input.
     * @return The user-inputted positive integer.
     */
    public static int inputPossitiveInteger(String fieldName) {
        return BaseInputterUtils.inputInteger(fieldName, 1, Integer.MAX_VALUE, true);
    }
    
    /**
     * Prompts the user to input a choice within a given range.
     * 
     * @param fieldName The name of the field being input.
     * @param min The minimum valid choice.
     * @param max The maximum valid choice.
     * @return The user-selected choice.
     */
    public static int inputChoice(String fieldName, int min, int max) {
        return BaseInputterUtils.inputInteger(fieldName, min, max, true);
    }
}
