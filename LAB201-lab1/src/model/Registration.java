package model;

import java.io.Serializable;
import untils.acceptable.RegistrationAcceptable;

/**
 * Represents a registration record for a student.
 * <p>
 * This class stores registration details including the student ID, name, email,
 * phone number, mountain code, and the computed fee. The fee is calculated based on
 * the phone number format using the {@code computeFee} method.
 * </p>
 * <p>
 * This class implements {@link Serializable} for object serialization and
 * {@link RegistrationAcceptable} to provide acceptable registration patterns.
 * </p>
 * 
 * @author ho huy
 */
public class Registration implements Serializable, RegistrationAcceptable {

    // Fields

    /**
     * The student's identification number.
     */
    private String studentId;

    /**
     * The student's name.
     */
    private String name;

    /**
     * The student's email address.
     */
    private String email;

    /**
     * The student's phone number.
     */
    private String phone;

    /**
     * The mountain code associated with the registration.
     */
    private String mountainCode;

    /**
     * The computed registration fee.
     */
    private double fee;

    // Constructors

    /**
     * Constructs a new {@code Registration} with the specified details.
     *
     * @param studentId    the student's identification number
     * @param name         the student's name
     * @param email        the student's email address
     * @param phone        the student's phone number
     * @param mountainCode the code of the mountain for which registration is made
     */
    public Registration(String studentId, String name, String email, String phone, String mountainCode) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.mountainCode = mountainCode;
        this.fee = computeFee(phone);
    }

    // Getters

    /**
     * Returns the student's ID.
     *
     * @return the student ID as a {@code String}
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the student's name.
     *
     * @return the student's name as a {@code String}
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the student's email address.
     *
     * @return the email address as a {@code String}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the student's phone number.
     *
     * @return the phone number as a {@code String}
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the mountain code associated with the registration.
     *
     * @return the mountain code as a {@code String}
     */
    public String getMountainCode() {
        return mountainCode;
    }

    /**
     * Returns the registration fee.
     *
     * @return the fee as a {@code double}
     */
    public double getFee() {
        return fee;
    }

    // Setters

    /**
     * Sets the student's ID.
     *
     * @param studentId the new student ID
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Sets the student's name.
     *
     * @param name the new student name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the student's email address.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the student's phone number.
     *
     * @param phone the new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the mountain code associated with the registration.
     *
     * @param mountainCode the new mountain code
     */
    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    /**
     * Sets the registration fee.
     *
     * @param fee the new fee
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    // Other Methods

    /**
     * Returns a formatted string representation of the registration.
     * <p>
     * The string includes the student ID, a truncated version of the student's name (if necessary),
     * a truncated version of the email (if necessary), the phone number, and the mountain code.
     * </p>
     *
     * @return a formatted {@code String} representing the registration details
     */
    @Override
    public String toString() {
        return String.format(" %-10s | %-20s | %-35s | %-15s | %-13s",
                studentId,
                name.length() > 20 ? name.substring(0, 16) + "..." : name,
                email.length() > 35 ? email.substring(0, 31) + "..." : email,
                phone,
                mountainCode);
    }

    /**
     * Computes the registration fee based on the student's phone number.
     * <p>
     * A base fee of 6,000,000.0 is applied. If the phone number matches either the Viettel or VNPT
     * phone number regex patterns, a discount of 35% is applied. Otherwise, the base fee is charged.
     * </p>
     *
     * @param phone the student's phone number
     * @return the computed fee as a {@code double}
     */
    private double computeFee(String phone) {
        double base = 6_000_000.0;
        double discount = 0.35;
        if (!phone.matches(VIETTEL_PHONE_REGEX) && !phone.matches(VNPT_PHONE_REGEX)) {
            return base;
        }
        return base * (1 - discount);
    }

    /**
     * Returns detailed registration information.
     *
     * @return a formatted {@code String} containing the registration details
     */
    public String getInfo() {
        return String.format(
                "Student Information\n"
                + "1. Student id   : %s\n"
                + "2. Student Name : %s\n"
                + "3. Email        : %s\n"
                + "4. Phone        : %s\n"
                + "5. Mountain Code: %s\n",
                studentId, name, email, phone, mountainCode);
    }
}
