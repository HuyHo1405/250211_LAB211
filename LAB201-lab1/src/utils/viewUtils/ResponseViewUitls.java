package utils.viewUtils;

/**
 * Provides utility methods for displaying formatted response messages.
 * <p>
 * This class includes methods to generate separator lines, headers, and to display
 * both response and error messages on the console.
 * </p>
 * 
 * @author ho huy
 */
public class ResponseViewUitls {

    /**
     * The default length for the display line.
     */
    public static final int DISPLAY_LENGTH = 107;
    
    /**
     * Creates a line string consisting of '-' characters using the default length ({@link #DISPLAY_LENGTH}).
     *
     * @return a string composed of '-' characters with a length equal to {@link #DISPLAY_LENGTH}
     */
    public static String line() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DISPLAY_LENGTH; i++) {
            sb.append("-");
        }
        return sb.toString();
    }
    
    /**
     * Creates a line string consisting of '-' characters with the specified length.
     *
     * @param length the desired length of the line string
     * @return a string composed of '-' characters with a length equal to the provided {@code length}
     */
    public static String line(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("-");
        }
        return sb.toString();
    }
    
    /**
     * Generates a header string by surrounding the provided string with separator lines.
     * <p>
     * The separator lines have a length equal to the length of the provided string.
     * </p>
     *
     * @param str the string to be wrapped with separator lines
     * @return a formatted header string containing the separator line, the provided string, and another separator line
     */
    public static String header(String str) {
        String line = line(str.length());
        return "\n" + line + "\n" + str + "\n" + line;
    }
    
    /**
     * Displays a response message on the console.
     *
     * @param action the action message to be displayed
     */
    public static void displayResponse(String action) {
        System.out.println("");
        System.out.println(">>" + action);
    }
    
    /**
     * Displays an error message on the console, including the action that failed and the reason for the failure.
     *
     * @param action the action that failed
     * @param reason the reason for the failure
     */
    public static void displayError(String action, String reason) {
        System.out.println("");
        System.out.println(">>Fail to " + action);
        System.out.println("Reason: " + reason);
    }
}
