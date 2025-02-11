package model;

/**
 * Represents statistics for a mountain registration.
 * <p>
 * This class stores information about the mountain code, the number of participants,
 * and the total price accumulated from registrations.
 * </p>
 * 
 * @author ho huy
 */
public class Statistic {
    private String mountainCode;
    private int participants;
    private double totalPrice;

    /**
     * Constructs a new Statistic object with the specified mountain code.
     * <p>
     * Initializes the number of participants to 0 and the total price to 0.0.
     * </p>
     *
     * @param mountainCode the code representing the mountain
     */
    public Statistic(String mountainCode) {
        this.mountainCode = mountainCode;
        this.participants = 0;
        this.totalPrice = 0.0;
    }

    /**
     * Returns the mountain code.
     *
     * @return the mountain code as a String
     */
    public String getMountainCode() {
        return mountainCode;
    }

    /**
     * Returns the number of participants.
     *
     * @return the number of participants
     */
    public int getParticipants() {
        return participants;
    }

    /**
     * Returns the total price accumulated.
     *
     * @return the total price as a double
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the mountain code.
     *
     * @param mountainCode the new mountain code
     */
    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    /**
     * Sets the number of participants.
     *
     * @param participants the new number of participants
     */
    public void setParticipants(int participants) {
        this.participants = participants;
    }

    /**
     * Sets the total price.
     *
     * @param totalPrice the new total price
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Updates the statistics by incrementing the participant count and adding a fee.
     *
     * @param fee the registration fee to add to the total price
     */
    public void update(double fee) {
        this.participants += 1;
        this.totalPrice += fee;
    }
    
    /**
     * Returns a formatted string representation of the statistic.
     * <p>
     * The string includes the mountain code (as a 2-digit number),
     * the number of participants, and the total price.
     * </p>
     *
     * @return a formatted string representing the statistic
     */
    @Override
    public String toString() {
        return String.format("            %02d | %12d | %,11.0f ", 
                Integer.parseInt(mountainCode), participants, totalPrice);
    }
    
    /**
     * Returns a header string for displaying the statistics table.
     *
     * @return a formatted header string with column titles for mountain code,
     *         participants, and total price
     */
    public static String header() {
        return String.format(" %-13s | %-11s | %-11s ", "Mountain Code", "Participants", "Total Price");
    }
}
