/*
 * This interface defines the rules and constraints for Feast Order-related data.
 * It includes formatting rules for displaying feast order details.
 */
package utils.rules;

/**
 * Interface defining feast order-related rules.
 */
public interface FeastOrderRules {
    
    /**
     * Formatting rules for displaying feast order data.
     */
    public interface Format {
        
        /**
         * Table format for displaying feast order details.
         */
        static final String TABLE = 
              "| %2s | %10s | %-13s | %-8s | %11s | %6s | %11s |";
        
        /**
         * Table header format.
         */
        static final String TABLE_HEADER = String.format(TABLE, 
                "ID", "Event Date", "Customer Code", "Set Menu", "Price", "Tables", "Cost");
        
        /**
         * Detailed list format for displaying feast order information.
         */
        static final String LIST = 
            "\n>> Feast Order [#ID: %02d] Information\n"
            + "%s\n"
            + "Code          : %s\n"
            + "Customer Name : %s\n"
            + "Customer Email: %s\n"
            + "Phone Number  : %s\n"
            + "%s\n"
            + "Set Menu Code  : %s\n"
            + "Set Menu Name  : %s\n"
            + "Event Date     : %s\n"
            + "Number of Tables: %d\n"
            + "Price          : %,.0f VND\n"
            + "%s\n"
            + "Ingredients    :\n"
            + " - %s\n"
            + " - %s\n"
            + " - %s\n"
            + "%s\n"
            + "Total Cost     : %,.0f VND\n"
            + "%s";
    }
    
    /**
     * Acceptable validation rules for feast order attributes.
     */
    public interface Acceptable {
        // Add validation patterns here if needed
    }
}
