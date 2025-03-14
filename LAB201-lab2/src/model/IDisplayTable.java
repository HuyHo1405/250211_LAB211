package model;

/**
 * Interface for displaying objects in a table format.
 * <p>
 * Classes that implement this interface should provide a method to represent
 * their data as a formatted table string.
 * </p>
 */
public interface IDisplayTable {
    
    /**
     * Converts the object's data into a formatted table string.
     *
     * @return A string representation of the object in table format.
     */
    String toTable();
}
