package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import model.Registration;
import utils.viewUtils.ResponseViewUitls;

/**
 * Data Access Object (DAO) for managing {@link Registration} objects.
 * <p>
 * This class provides methods for creating, updating, deleting, and retrieving
 * Registration records. It also handles loading from and saving to a file.
 * </p>
 * 
 * @author ho huy
 */
public class RegistrationDAO implements Serializable {

    /**
     * The file name used for storing Registration objects.
     */
    private static final String FILE_NAME = "Registrations.dat";

    /**
     * The list of Registration objects.
     */
    private List<Registration> list;

    /**
     * Constructs a new {@code RegistrationDAO} and initializes the registration list
     * by loading it from the file. If loading fails, a new empty list is created.
     */
    public RegistrationDAO() {
        this.list = load();
        this.list = list == null ? new ArrayList<>() : list;
    }

    /**
     * Creates a new Registration record.
     * <p>
     * The method adds the given Registration to the list and sorts the list by student ID.
     * </p>
     *
     * @param registration the Registration object to add
     * @return {@code true} if the registration was successfully added; {@code false} otherwise
     */
    public boolean create(Registration registration) {
        boolean result = list.add(registration);
        sortByStudentID();
        return result;
    }
    
    /**
     * Deletes a Registration record by student ID.
     * <p>
     * The method retrieves the Registration with the specified ID and then removes it from the list.
     * After deletion, the list is sorted by student ID.
     * </p>
     *
     * @param id the student ID of the Registration to delete
     * @return the deleted Registration if removal was successful; {@code null} otherwise
     */
    public Registration delete(String id) {
        Registration prev = retrieve(id);
        boolean check = list.removeIf(r -> r.getStudentId().equals(id));
        sortByStudentID();
        return check ? prev : null;
    }

    /**
     * Updates a Registration record by student ID.
     * <p>
     * The method finds the Registration record with a matching student ID (ignoring the first two characters)
     * and replaces it with the provided Registration.
     * </p>
     *
     * @param id the student ID of the Registration to update
     * @param registration the new Registration object to replace the existing one
     * @return the previous Registration object if the update was successful; {@code null} otherwise
     */
    public Registration update(String id, Registration registration) {
        for (int i = 0; i < list.size(); i++) {
            String idNum = list.get(i).getStudentId().substring(2);
            if (idNum.endsWith(id.substring(2))) {
                return list.set(i, registration);
            }
        }
        return null;
    }

    /**
     * Retrieves a list of Registration records that satisfy the given condition.
     *
     * @param condition a {@code Predicate} defining the condition to filter registrations
     * @return a list of Registration objects that match the condition
     */
    public List<Registration> retrieveList(Predicate<Registration> condition) {
        return list.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves a Registration record by student ID.
     *
     * @param studentID the student ID to search for
     * @return the Registration object with the matching student ID, or {@code null} if not found
     */
    public Registration retrieve(String studentID) {
        for (Registration x : list) {
            if (x.getStudentId().equals(studentID)) {
                return x;
            }
        }
        return null;
    }
    
    /**
     * Retrieves all Registration records.
     *
     * @return a list of all Registration objects
     */
    public List<Registration> retrieveAll() {
        return list;
    }

    /**
     * Checks if a given Registration object has unique student ID, email, and phone.
     *
     * @param registration the Registration object to check for uniqueness
     * @return {@code true} if the registration is unique; {@code false} otherwise
     */
    public boolean isUnique(Registration registration) {
        List search = retrieveList(r -> (r.getStudentId().equals(registration.getStudentId())
                || r.getEmail().equals(registration.getEmail())
                || r.getPhone().equals(registration.getPhone())));
        return search.isEmpty();
    }
    
    /**
     * Checks if a particular field value is unique among the registrations.
     *
     * @param field the field index to check (0 for student ID, 3 for email, 2 for phone)
     * @param value the value to check for uniqueness
     * @return {@code true} if the value is unique; {@code false} otherwise
     */
    public boolean isUnique(int field, String value) {
        switch (field) {
            case 0: // Student ID must be unique
                return retrieveList(r -> r.getStudentId().equals(value)).isEmpty();
            case 3: // Email must be unique
                return retrieveList(r -> r.getEmail().equals(value)).isEmpty();
            case 2: // Phone must be unique
                return retrieveList(r -> r.getPhone().equals(value)).isEmpty();
            default:
                return true;
        }
    }
    
    /**
     * Loads the list of Registration objects from the file.
     *
     * @return the list of Registration objects, or {@code null} if loading fails
     */
    private List<Registration> load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Registration>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    
    /**
     * Saves the list of Registration objects to the file.
     *
     * @return {@code true} if the save operation was successful; {@code false} otherwise
     */
    public boolean save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(retrieveAll());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Sorts the list of Registration objects by their student ID.
     */
    public void sortByStudentID() {
        list.sort((r1, r2) -> r1.getStudentId().compareTo(r2.getStudentId()));
    }
    
    /**
     * Displays the header for registration information.
     * <p>
     * The header is formatted using the {@link ResponseViewUitls#header(String)} method.
     * </p>
     */
    public void displayRegistrationHeader() {
        System.out.println(ResponseViewUitls.header(String.format(" %-10s | %-20s | %-35s | %-15s | %-13s ",
                "Student ID", "Student Name", "Student Email", "Phone Number", "Mountain Code")));
    }
    
}
