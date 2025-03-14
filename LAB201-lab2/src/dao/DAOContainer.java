package dao;

/**
 * A container class that holds static instances of DAO classes.
 * This allows for centralized access to data management objects.
 */
public abstract class DAOContainer {
    
    /**
     * Static instance of CustomerDAO for managing customer data.
     */
    public static final CustomerDAO CDAO = new CustomerDAO();
    
    /**
     * Static instance of FeastMenuDAO for managing feast menu data.
     */
    public static final FeastMenuDAO FMDAO = new FeastMenuDAO();
    
    /**
     * Static instance of FeastOrderDAO for managing feast order data.
     */
    public static final FeastOrderDAO FODAO = new FeastOrderDAO();
}
