/*
 * This interface defines formatting rules for displaying responses, errors,
 * and headers in the application. It helps standardize output messages
 * to maintain consistency.
 */
package utils.rules;

/**
 * Interface defining rules for response message formatting.
 */
public interface ResponseViewRules {
    
    /**
     * Formatting rules for response messages.
     */
    public interface Format {
        
        /**
         * Generic response message format.
         * Example usage: String.format(RESPONSE, "Success")
         */
        static final String RESPONSE = "\n>>%s";

        /**
         * Error message format including a failure reason.
         * Example usage: String.format(ERROR, "process request", "Invalid data")
         */
        static final String ERROR = 
                  "\n>>Fail to %s"
                + "\nReason: %s\n";

        /**
         * Header format for displaying section headers.
         * Example usage: String.format(HEADER, "Title", "Separator", "Subtitle")
         */
        static final String HEADER = 
                  "%s\n"
                + "%s\n"
                + "%s";
    }
    
    /**
     * Standard screen length for formatting purposes.
     */
    static final int SCREEN_LENGTH = 111;
    
}
