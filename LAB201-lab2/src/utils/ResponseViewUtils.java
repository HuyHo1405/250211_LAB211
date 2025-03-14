package utils;

import model.IDisplayTable;
import java.util.List;
import model.FeastMenu;
import utils.rules.ResponseViewRules;

/**
 * Utility class for displaying formatted responses, tables, and error messages.
 * This class provides methods to print structured output to the console,
 * such as error messages, response messages, lists, and formatted tables.
 */
public class ResponseViewUtils implements ResponseViewRules {

    /**
     * Generates a line of repeated characters.
     * @param token the character to repeat
     * @param length the number of times to repeat the character
     * @return a string consisting of the repeated character
     */
    public static String line(char token, int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append(token);
        }
        return line.toString();
    }

    /**
     * Displays a separator line of default screen length.
     */
    public static void displayLine() {
        System.out.println(line('=', SCREEN_LENGTH));
    }

    /**
     * Displays a formatted response message.
     * @param action the message to be displayed
     */
    public static void displayResponse(String action) {
        System.out.println(
                String.format(Format.RESPONSE, action)
        );
    }

    /**
     * Displays an error message with a specific reason.
     * @param action the action that caused the error
     * @param reason the reason for the error
     */
    public static void displayError(String action, String reason) {
        System.out.println(
                String.format(Format.ERROR, action, reason)
        );
    }

    /**
     * Displays a list of elements with an indexed format.
     * @param listName the name of the list
     * @param elements the array of elements to display
     */
    public static void displayList(String listName, String[] elements) {
        displayResponse(listName);
        for (int i = 0; i < elements.length; i++) {
            System.out.format("%02d. %s\n", i + 1, elements[i]);
        }
    }

    /**
     * Displays a formatted header with surrounding lines.
     * @param header the header text to display
     */
    public static void displayHeader(String header) {
        String line = line('-', header.length());
        System.out.println(
                String.format(Format.HEADER, line, header, line)
        );
    }

    /**
     * Displays a footer line for table formatting.
     * @param length the length of the footer line
     */
    public static void displayFooter(int length) {
        System.out.println(line('-', length));
    }

    /**
     * Displays a table containing a list of objects that implement IDisplayTable.
     * @param header the header title for the table
     * @param list the list of objects to be displayed
     * @param <E> the type of objects in the list, extending IDisplayTable
     */
    public static <E extends IDisplayTable> void displayTable(String header, List<E> list) {
        if (list == null) {
            displayResponse("Can not read data from file. Please check it!");
            return;
        }
        
        if (!list.isEmpty()) {
            boolean isFeastMenu = false;
            displayHeader(header);
            for (E e : list) {
                System.out.println(e.toTable());
                isFeastMenu = (e instanceof FeastMenu);
            }
            if (!isFeastMenu) {
                displayFooter(header.length());
            }
        } else {
            displayResponse("The list is empty!");
        }
    }
}
