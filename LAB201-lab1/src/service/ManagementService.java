package service;

import dao.RegistrationDAO;
import model.Registration;
import utils.viewUtils.ResponseViewUitls;

/**
 * Provides management services for handling registration operations.
 * <p>
 * This class manages the addition, deletion, and confirmation of deletion for
 * registration records. It utilizes a {@code RegistrationDAO} to persist the
 * registrations and uses {@code ResponseViewUitls} to display messages to the user.
 * </p>
 * 
 * @author ho huy
 */
public class ManagementService {

    /**
     * The Registration Data Access Object used to manage registration records.
     */
    private RegistrationDAO rdao;

    /**
     * Constructs a new {@code ManagementService} with the specified {@code RegistrationDAO}.
     *
     * @param rdao the {@code RegistrationDAO} to be used for registration operations
     */
    public ManagementService(RegistrationDAO rdao) {
        this.rdao = rdao;
    }

    /**
     * Adds a new registration record.
     * <p>
     * This method first checks if the registration is unique. If the registration is not unique,
     * an error message is displayed and the registration is not added. Otherwise, the registration is added,
     * and a success message is displayed.
     * </p>
     *
     * @param registration the {@code Registration} object to be added
     * @return {@code true} if the registration was added successfully; {@code false} otherwise
     */
    public boolean add(Registration registration) {
        if (!rdao.isUnique(registration)) {
            ResponseViewUitls.displayError(
                    "create student with id[" + registration.getStudentId() + "]!", 
                    "Duplicated data on unique field!");
            return false;
        }

        if (rdao.create(registration)) {
            ResponseViewUitls.displayResponse(
                    "Create student with id[" + registration.getStudentId() + "] successfully!");
            return true;
        } else {
            ResponseViewUitls.displayError(
                    "create student with id[" + registration.getStudentId() + "]!", 
                    "Internal error!");
            return false;
        }
    }

    /**
     * Deletes a registration record by student ID.
     * <p>
     * This method attempts to delete the registration with the given student ID.
     * If no such registration exists, an error message is displayed.
     * </p>
     *
     * @param studentId the student ID of the registration to be deleted
     * @return the deleted {@code Registration} object if deletion was successful; {@code null} otherwise
     */
    public Registration delete(String studentId) {
        Registration prev = rdao.delete(studentId);
        
        if (prev == null) {
            ResponseViewUitls.displayError(
                    "find user",
                    "Student ID [" + studentId + "] does not exist!");
            return null;
        }
        
        return prev;
    }
    
    /**
     * Confirms the deletion of a registration record.
     * <p>
     * If confirmation is {@code true}, a success message for deletion is displayed.
     * Otherwise, the deletion is undone by re-adding the registration record and an undo success
     * message is displayed.
     * </p>
     *
     * @param prev the {@code Registration} object that was deleted
     * @param confirmation {@code true} if deletion is confirmed; {@code false} to undo deletion
     * @return {@code true} after processing the confirmation
     */
    public boolean confirmDelete(Registration prev, boolean confirmation) {
        if (confirmation) {
            ResponseViewUitls.displayResponse(
                    "Delete student with id[" + prev.getStudentId() + "] successfully!");
        } else {
            ResponseViewUitls.displayResponse(
                    "Undo student with id[" + prev.getStudentId() + "] successfully!");
            rdao.create(prev);
        }
        return true;
    }
}
