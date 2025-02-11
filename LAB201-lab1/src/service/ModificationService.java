package service;

import dao.RegistrationDAO;
import model.Registration;
import utils.viewUtils.ResponseViewUitls;

/**
 * Provides services for modifying registration records.
 * <p>
 * This service supports updating specific fields of a {@link Registration} record.
 * It validates uniqueness constraints before applying updates.
 * </p>
 * 
 * @author ho huy
 */
public class ModificationService {

    /**
     * The Registration Data Access Object used for managing registration records.
     */
    private RegistrationDAO rdao;

    /**
     * Constructs a new {@code ModificationService} with the specified {@code RegistrationDAO}.
     *
     * @param rdao the registration DAO used to manage registration records
     */
    public ModificationService(RegistrationDAO rdao) {
        this.rdao = rdao;
    }

    /**
     * Updates the specified field of a registration record.
     * <p>
     * The method updates the registration based on the given choice and value.
     * It first attempts to set the new data using {@link #setUpdateData(int, String, Registration)}.
     * If the new value violates uniqueness constraints, an error is displayed and the update fails.
     * Otherwise, the registration is updated in the DAO.
     * </p>
     *
     * @param choice an integer representing which field to update:
     *               <ul>
     *                 <li>1 for student name,</li>
     *                 <li>2 for student phone,</li>
     *                 <li>3 for student email,</li>
     *                 <li>4 for mountain code.</li>
     *               </ul>
     * @param value the new value to update
     * @param registration the {@link Registration} record to update
     * @return {@code true} if the update was successful; {@code false} otherwise
     */
    public boolean update(int choice, String value, Registration registration) {
        registration = setUpdateData(choice, value, registration);
        if (registration == null) {
            ResponseViewUitls.displayError(
                    "update student",
                    "Duplicated data on unique field");
            return false;
        }
        
        if (rdao.update(registration.getStudentId(), registration) != null) {
            ResponseViewUitls.displayResponse(
                    "Update student with id [" + registration.getStudentId() + "] successfully!");
            return true;
        } else {
            ResponseViewUitls.displayError(
                    "update student with id [" + registration.getStudentId() + "]",
                    "Internal error!");
            return false;
        }
    }

    /**
     * Sets the update data for the registration record.
     * <p>
     * This method checks if the new value is unique for the specified field.
     * If it is not unique, the method returns {@code null} to indicate failure.
     * Otherwise, it updates the corresponding field of the registration record.
     * </p>
     *
     * @param choice an integer representing which field to update:
     *               <ul>
     *                 <li>1 for student name,</li>
     *                 <li>2 for student phone,</li>
     *                 <li>3 for student email,</li>
     *                 <li>4 for mountain code.</li>
     *               </ul>
     * @param value the new value to update
     * @param registration the {@link Registration} record to update
     * @return the updated {@link Registration} record, or {@code null} if the new value is not unique or the choice is invalid
     */
    private Registration setUpdateData(int choice, String value, Registration registration) {
        if (!rdao.isUnique(choice, value)) {
            return null;
        }

        switch (choice) {
            case 1:
                registration.setName(value);
                break;
            case 2:
                registration.setPhone(value);
                break;
            case 3:
                registration.setEmail(value);
                break;
            case 4:
                registration.setMountainCode(value);
                break;
            default:
                return null;
        }
        return registration;
    }
}
