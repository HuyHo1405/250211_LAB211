/*
 * This interface defines the rules and constraints for Customer-related data.
 * It includes formatting rules for displaying customer information and
 * acceptable patterns for validating input data such as names, phone numbers, and emails.
 */
package utils.rules;

/**
 * Interface defining customer-related rules.
 */
public interface CustomerRules {

    /**
     * Formatting rules for displaying customer data.
     */
    public interface Format {
        
        /**
         * Table format for displaying customer information.
         */
        static final String TABLE = 
                  "| %-5s | %-25s | %-25s | %-12s |";
              
        /**
         * Table header format.
         */
        static final String TABLE_HEADER = String.format(Format.TABLE, 
                "Code", "Customer Name", "Customer Email", "Phone Number");
        
        /**
         * Detailed list format for displaying customer information.
         */
        static final String LIST = 
                "\n>> Customer Information\n"
                + "%s\n"
                + "Code          : %s\n"
                + "Customer Name : %s\n"
                + "Customer Email: %s\n"
                + "Phone Number  : %s\n"
                + "%s";
        
        /**
         * Phone number formatting pattern.
         */
        static final String PHONE = "$1-$2-$3";
        
    }
    
    /**
     * Acceptable validation rules for customer attributes.
     */
    public interface Acceptable {

        /**
         * Regex pattern for customer codes (e.g., C1234, G5678, K9012).
         */
        static final String CODE_REGEX = "(C|G|K)+([0-9]{4})\\b";

        /**
         * Regex pattern for validating customer names (must start with uppercase letters).
         */
        static final String NAME_REGEX = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";

        /**
         * Regex pattern for Vietnamese phone numbers.
         */
        static final String VIETNAMESE_PHONE_REGEX = "(84|0[3,5,7,8,9])+([0-9]{8})\\b";

        /**
         * Regex pattern for validating email addresses.
         */
        static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        
        /**
         * Regex pattern for phone number formatting.
         */
        static final String PHONE_REGEX = "(\\d{3})(\\d{3})(\\d{4})";
    }
    
}