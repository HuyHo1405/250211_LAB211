package contronller;

/**
 * Defines the contract for business controllers in the registration management system.
 * <p>
 * Implementing classes should provide concrete implementations for adding, updating,
 * displaying, deleting, and searching registration records, as well as displaying statistics.
 * </p>
 * 
 * @author ho huy
 */
public interface BusinesssController {
    
    /**
     * Adds a new registration record.
     */
    void add();
    
    /**
     * Updates an existing registration record.
     */
    void update();
    
    /**
     * Displays all registration records.
     */
    void displayAll();
    
    /**
     * Deletes a registration record.
     */
    void delete();
    
    /**
     * Searches for registration records by student name.
     */
    void searchByName();
    
    /**
     * Searches for registration records by campus.
     */
    void searchByCampus();
    
    /**
     * Displays statistical information about the registrations.
     */
    void statistics();
}
