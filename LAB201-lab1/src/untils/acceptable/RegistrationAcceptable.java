package untils.acceptable;

/**
 * Defines acceptable regular expression patterns for various registration fields.
 * <p>
 * This interface provides regex patterns for validating:
 * <ul>
 *   <li>Student IDs</li>
 *   <li>Names</li>
 *   <li>Email addresses</li>
 *   <li>General phone numbers</li>
 *   <li>Campus codes</li>
 *   <li>VNPT phone numbers</li>
 *   <li>Viettel phone numbers</li>
 * </ul>
 * </p>
 * 
 * @author ho huy
 */
public interface RegistrationAcceptable {

    /**
     * Regex pattern for validating student IDs.
     * <p>
     * The student ID must start with one of the characters 'S', 'H', 'D', 'Q', or 'C',
     * followed by the letter 'E' and exactly six digits.
     * </p>
     */
    public static final String STUDENT_ID_REGEX = "^[SHDQC][E]\\d{6}$";

    /**
     * Regex pattern for validating names.
     * <p>
     * The name must start with an uppercase letter followed by lowercase letters,
     * with optional additional words that also start with an uppercase letter.
     * </p>
     */
    public static final String NAME_REGEX = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";

    /**
     * Regex pattern for validating email addresses.
     */
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    /**
     * Regex pattern for validating general phone numbers.
     * <p>
     * Matches phone numbers that start with either "84" or a "0" followed by any digit,
     * and then exactly eight digits.
     * </p>
     */
    public static final String PHONE_REGEX = "(84|0[0-9])+([0-9]{8})\\b";

    /**
     * Regex pattern for validating campus codes.
     * <p>
     * The campus code must consist of one of the characters 'S', 'H', 'D', 'Q', or 'C'
     * followed by the letter 'E'.
     * </p>
     */
    public static final String CAMPUS_REGEX = "^[SHDQC][E]$";

    /**
     * Regex pattern for validating VNPT phone numbers.
     * <p>
     * VNPT phone numbers must start with "08" followed by one of the digits 1, 2, 3, 4, or 5,
     * and then exactly seven digits.
     * </p>
     */
    public static final String VNPT_PHONE_REGEX = "(08[1,2,3,4,5])+([0-9]{7})";

    /**
     * Regex pattern for validating Viettel phone numbers.
     * <p>
     * Viettel phone numbers must start with one of the following:
     * <ul>
     *   <li>"03" followed by one of the digits 2, 3, 4, 5, 6, 7, 8, or 9,</li>
     *   <li>"086", or</li>
     *   <li>"09" followed by one of the digits 6, 7, or 8</li>
     * </ul>
     * and then exactly seven digits.
     * </p>
     */
    public static final String VIETTEL_PHONE_REGEX = "(03[2,3,4,5,6,7,8,9]|086|09[6,7,8])+([0-9]{7})";
}
