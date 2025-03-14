package model;

import utils.ResponseViewUtils;
import utils.rules.FeastMenuRules;

/**
 * Represents a feast menu with a unique code, name, price, and a list of ingredients.
 * <p>
 * This class implements {@link IDisplayTable} for displaying feast menu details
 * in different formats and {@link Comparable} to allow sorting by menu code.
 * </p>
 */
public class FeastMenu implements IDisplayTable, Comparable<FeastMenu> {

    /** Unique identifier for the feast menu. */
    private String code;

    /** Name of the feast menu. */
    private String name;

    /** Price of the feast menu. */
    private double price;

    /** List of ingredients used in the feast menu. */
    private String[] ingredients;

    /**
     * Constructs a new {@code FeastMenu} with the given details.
     *
     * @param code        Unique identifier of the feast menu.
     * @param name        Name of the feast menu.
     * @param price       Price of the feast menu.
     * @param ingredients List of ingredients used in the feast menu.
     */
    public FeastMenu(String code, String name, double price, String[] ingredients) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    // Getters

    /**
     * Gets the feast menu code.
     *
     * @return The feast menu code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the name of the feast menu.
     *
     * @return The feast menu name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the feast menu.
     *
     * @return The feast menu price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the list of ingredients in the feast menu.
     *
     * @return An array of ingredient names.
     */
    public String[] getIngredients() {
        return ingredients;
    }

    // Display methods

    /**
     * Returns a formatted string representation of the feast menu in list format.
     *
     * @return A formatted string containing feast menu details.
     */
    @Override
    public String toString() {
        String line = ResponseViewUtils.line('-', ResponseViewUtils.SCREEN_LENGTH);
        return String.format(FeastMenuRules.Format.LIST, 
                line,
                code, 
                name, 
                price, 
                ingredients[0], 
                ingredients[1], 
                ingredients[2],
                line);
    }

    /**
     * Returns a formatted string representation of the feast menu in table format.
     *
     * @return A formatted string suitable for displaying in a table.
     */
    @Override
    public String toTable(){
        String line = ResponseViewUtils.line('-', ResponseViewUtils.SCREEN_LENGTH);
        return String.format(FeastMenuRules.Format.TABLE, 
                code, 
                name, 
                price, 
                ingredients[0], 
                ingredients[1], 
                ingredients[2],
                line);
    }

    // Comparable implementation

    /**
     * Compares this feast menu with another based on their menu codes.
     *
     * @param o The feast menu to compare with.
     * @return A negative integer, zero, or a positive integer if this menu's code is
     *         less than, equal to, or greater than the other menu's code.
     */
    @Override
    public int compareTo(FeastMenu o) {
        return this.code.compareTo(o.code);
    }
}
