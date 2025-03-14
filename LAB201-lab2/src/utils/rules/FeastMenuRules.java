/*
 * This interface defines rules and constraints for Feast Menu-related data.
 * It includes formatting rules for displaying feast menu details and
 * acceptable patterns for validating input data.
 */
package utils.rules;

/**
 * Interface defining feast menu-related rules.
 */
public interface FeastMenuRules {
    
    /**
     * Formatting rules for displaying feast menu data.
     */
    public interface Format {
        
        /**
         * Detailed list format for displaying feast menu information.
         */
        static final String LIST = 
            "\n>> Feast Menu Information\n"
            + "%s\n"
            + "Code       : %s\n"
            + "Name       : %s\n"
            + "Price      : %,.0f VND\n"
            + "Ingredients:\n"
            + " - %s\n"
            + " - %s\n"
            + " - %s\n"
            + "%s";
        
        /**
         * Table format for displaying feast menu details.
         */
        static final String TABLE = 
              "Code       : %s\n"
            + "Name       : %s\n"
            + "Price      : %,.0f VND\n"
            + "Ingredients:\n"
            + " - %s\n"
            + " - %s\n"
            + " - %s\n"
            + "%s";
    }

    /**
     * Acceptable validation rules for feast menu attributes.
     */
    public interface Acceptable {
        
        /**
         * Regex pattern for feast menu codes (e.g., PW001, PW123).
         */
        static final String CODE_REGEX = "^PW\\d{3}";
    }
}
