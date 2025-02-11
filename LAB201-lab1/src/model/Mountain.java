package model;

/**
 * Represents a mountain with its associated details.
 * <p>
 * This class encapsulates the information about a mountain, including its code,
 * name, province, and a description. It provides methods to access these details.
 * </p>
 * <p>
 * The {@code HEADER} field can be used as a header for displaying mountain information.
 * </p>
 * 
 * @author ho huy
 */
public class Mountain {

    /**
     * A header string for displaying mountain information.
     */
    public static final String HEADER = "Code, Mountain, Province, Description";
    
    private final String code;
    private final String mountain;
    private final String province;
    private final String description;

    /**
     * Constructs a new {@code Mountain} with the specified details.
     *
     * @param code        the unique code of the mountain
     * @param mountain    the name of the mountain
     * @param province    the province where the mountain is located
     * @param description a brief description of the mountain
     */
    public Mountain(String code, String mountain, String province, String description) {
        this.code = code;
        this.mountain = mountain;
        this.province = province;
        this.description = description;
    }

    /**
     * Returns the unique code of the mountain.
     *
     * @return the mountain code as a {@code String}
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the name of the mountain.
     *
     * @return the mountain name as a {@code String}
     */
    public String getMountain() {
        return mountain;
    }

    /**
     * Returns the province where the mountain is located.
     *
     * @return the province as a {@code String}
     */
    public String getProvince() {
        return province;
    }

    /**
     * Returns the description of the mountain.
     *
     * @return the mountain description as a {@code String}
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns a string representation of this {@code Mountain}.
     * <p>
     * The string is formatted to display the mountain code as a two-digit number,
     * followed by the mountain name and province.
     * </p>
     *
     * @return a formatted {@code String} representation of the mountain
     */
    @Override
    public String toString() {
        return String.format("%02d. %-20s | %s", Integer.parseInt(code), mountain, province);
    }
}
