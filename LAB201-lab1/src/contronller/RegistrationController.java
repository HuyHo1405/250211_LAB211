package contronller;

import dao.MountainDAO;
import dao.RegistrationDAO;
import model.Registration;
import service.ManagementService;
import service.ModificationService;
import service.RetrievalService;
import utils.inputter.Inputter;
import utils.viewUtils.ResponseViewUitls;

/**
 * Controller for handling registration-related business operations.
 * <p>
 * This class interacts with the data access objects and service layers to add, update,
 * delete, search, and display registration records, as well as display statistics.
 * </p>
 * 
 * @author ho huy
 */
public class RegistrationController implements BusinesssController {

    // Fields

    /**
     * The Registration Data Access Object.
     */
    private RegistrationDAO rdao;

    /**
     * The Mountain Data Access Object.
     */
    private MountainDAO mdao;

    /**
     * The management service for registration operations.
     */
    private ManagementService mas;

    /**
     * The modification service for updating registration records.
     */
    private ModificationService mos;

    /**
     * The retrieval service for searching and displaying registrations.
     */
    private RetrievalService rs;
    
    // Constructor

    /**
     * Constructs a new RegistrationController with the specified DAOs.
     *
     * @param rdao the RegistrationDAO instance
     * @param mdao the MountainDAO instance
     */
    public RegistrationController(RegistrationDAO rdao, MountainDAO mdao) {
        this.rdao = rdao;
        this.mdao = mdao;
        this.mas = new ManagementService(rdao);
        this.mos = new ModificationService(rdao);
        this.rs = new RetrievalService(rdao, mdao);
    }

    // Private Methods

    /**
     * Prompts the user for deletion confirmation and proceeds with deletion.
     * <p>
     * Displays the information of the registration to be deleted, then asks for confirmation.
     * The deletion is confirmed via the management service.
     * </p>
     *
     * @param prev the Registration record to be deleted
     */
    private void confirmDelete(Registration prev) {
        System.out.println(prev.getInfo());
        boolean confirmation = Inputter.inputConfirm("delete student");
        mas.confirmDelete(prev, confirmation);
    }
    
    /**
     * Displays the available update options to the user.
     * <p>
     * The options include updating the student's name, phone number, email, or mountain code.
     * </p>
     */
    private void displayUpdateOption() {
        System.out.println("1. Student name.");
        System.out.println("2. Student phone number.");
        System.out.println("3. Student email.");
        System.out.println("4. Mountain code.");
    }
    
    /**
     * Retrieves the update data based on the user's chosen field.
     *
     * @param choice an integer representing the field to update:
     *               1 for student name,
     *               2 for student phone number,
     *               3 for student email,
     *               4 for mountain code
     * @return the new data input by the user for the chosen field, or {@code null} if no valid choice
     */
    private String getUpdateData(int choice) {
        switch(choice) {
            case 1:
                return Inputter.inputMatchPattern("student name", Registration.NAME_REGEX);
            case 2:
                return Inputter.inputMatchPattern("student phone number", Registration.PHONE_REGEX);
            case 3:
                return Inputter.inputMatchPattern("student email", Registration.EMAIL_REGEX);
            case 4:
                return inputMountainCode();
            default:
                return null;
        }
    }
    
    /**
     * Prompts the user to input a mountain code.
     * <p>
     * Displays the mountain list and asks the user to choose a mountain code by entering a number.
     * </p>
     *
     * @return the chosen mountain code as a String
     */
    private String inputMountainCode() {
        System.out.println(">>Mountain List");
        mdao.displayAll();
        return Inputter.inputChoice("mountain code", 1, mdao.retrieveAll().size()) + "";
    }
    
    // Overridden Methods from BusinesssController

    /**
     * Adds a new registration.
     * <p>
     * Prompts the user to input details such as student ID, name, email, phone, and mountain code,
     * creates a new Registration object, and adds it using the management service.
     * </p>
     */
    @Override
    public void add() {
        Registration registration = new Registration(
                Inputter.inputMatchPattern("student ID", Registration.STUDENT_ID_REGEX),
                Inputter.inputMatchPattern("student name", Registration.NAME_REGEX),
                Inputter.inputMatchPattern("student email", Registration.EMAIL_REGEX),
                Inputter.inputMatchPattern("student phone", Registration.PHONE_REGEX),
                inputMountainCode()
        );
        mas.add(registration);
    }

    /**
     * Updates an existing registration.
     * <p>
     * Prompts the user to input the student ID, retrieves the corresponding registration,
     * displays update options if found, and processes the update using the modification service.
     * If the registration does not exist, an error message is displayed.
     * </p>
     */
    @Override
    public void update() {
        String studentId = Inputter.inputMatchPattern("student ID", Registration.STUDENT_ID_REGEX);
        Registration registration = rdao.retrieve(studentId);
        
        if (registration != null) {
            displayUpdateOption();
            int choice = Inputter.inputChoice("update field", 1, 4);
            String value = getUpdateData(choice);
            mos.update(choice, value, registration);
        } else {
            ResponseViewUitls.displayError(
                    "find user", 
                    "Student ID [" + studentId + "] does not exist!");
        }
    }
        
    /**
     * Displays all registrations.
     */
    @Override
    public void displayAll() {
        rs.displayAll();
    }

    /**
     * Deletes a registration.
     * <p>
     * Prompts the user for a student ID, retrieves the corresponding registration,
     * and if found, proceeds with deletion after confirmation.
     * </p>
     */
    @Override
    public void delete() {
        String studentId = Inputter.inputMatchPattern("student ID", Registration.STUDENT_ID_REGEX);
        Registration prev = mas.delete(studentId);
        if (prev != null) {
            confirmDelete(prev);
        }
    }

    /**
     * Searches for registrations by student name.
     * <p>
     * Prompts the user for a student name and displays the search results.
     * </p>
     */
    @Override
    public void searchByName() {
        String name = Inputter.inputMatchPattern("student name", Registration.NAME_REGEX);
        rs.displaySearchByName(name);
    }

    /**
     * Searches for registrations by campus.
     * <p>
     * Prompts the user for a campus identifier and displays the matching registrations.
     * </p>
     */
    @Override
    public void searchByCampus() {
        String campus = Inputter.inputMatchPattern("campus", Registration.CAMPUS_REGEX);
        rs.displaySearchByCampus(campus);
    }

    /**
     * Displays registration statistics.
     */
    @Override
    public void statistics() {
        rs.displayStatistic();
    }
}
