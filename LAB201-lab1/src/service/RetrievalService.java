package service;

import dao.MountainDAO;
import dao.RegistrationDAO;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import model.Mountain;
import model.Registration;
import model.Statistic;
import utils.viewUtils.ResponseViewUitls;

/**
 * Provides retrieval services for the registration management system.
 * <p>
 * This service handles the retrieval and display of registration records,
 * including filtering by student name or campus and generating statistics
 * based on mountain registrations.
 * </p>
 * 
 * @author ho huy
 */
public class RetrievalService {
    
    /**
     * The Registration Data Access Object.
     */
    private RegistrationDAO rdao;
    
    /**
     * The Mountain Data Access Object.
     */
    private MountainDAO mdao;

    /**
     * Constructs a new {@code RetrievalService} with the specified DAOs.
     *
     * @param rdao the RegistrationDAO instance
     * @param mdao the MountainDAO instance
     */
    public RetrievalService(RegistrationDAO rdao, MountainDAO mdao) {
        this.rdao = rdao;
        this.mdao = mdao;
    }
    
    /**
     * Displays a list of registration records.
     * <p>
     * This method prints a header for the registration list and then outputs each
     * registration. If the list is empty or {@code null}, it prints a row of "N/A" values.
     * </p>
     *
     * @param list the list of {@link Registration} objects to display
     */
    private void displayList(List<Registration> list) {
        rdao.displayRegistrationHeader();
        if (list != null && !list.isEmpty()) {
            for (Registration registration : list) {
                System.out.println(registration);
            }
        } else {
            String na = "N/A";
            System.out.println(String.format(
                    " %-10s | %-20s | %-35s | %-15s | %-13s ", 
                    na, na, na, na, na));
        }
    }
    
    /**
     * Computes registration statistics aggregated by mountain.
     * <p>
     * This method creates a mapping of mountain codes to their corresponding
     * {@link Statistic} objects. It initializes statistics for each mountain and
     * updates them based on the registration fees.
     * </p>
     *
     * @return a {@link HashMap} mapping mountain codes to their {@link Statistic} objects
     */
    private HashMap<String, Statistic> getStatistics() {
        LinkedHashMap<String, Statistic> statisticsList = new LinkedHashMap<>();

        for (Mountain m : mdao.retrieveAll()) {
            statisticsList.put(m.getCode(), new Statistic(m.getCode()));
        }
        
        for (Registration r : rdao.retrieveAll()) {
            Statistic s = statisticsList.get(r.getMountainCode());
            if (s != null) {
                s.update(r.getFee());
            }
        }
        return statisticsList;
    }
    
    /**
     * Displays all registration records.
     */
    public void displayAll() {
        displayList(rdao.retrieveAll());
    }
    
    /**
     * Displays registration records filtered by student name.
     * <p>
     * It prints a message indicating the filter criteria and then displays the list
     * of registrations where the student's name matches the provided name.
     * </p>
     *
     * @param name the student name to filter by
     */
    public void displaySearchByName(String name) {
        System.out.println(">>Display registration list filter by name [" + name + "].");
        displayList(rdao.retrieveList(r -> r.getName().equals(name)));
    }
    
    /**
     * Displays registration records filtered by campus.
     * <p>
     * It prints a message indicating the filter criteria and then displays the list
     * of registrations where the student ID starts with the specified campus code.
     * </p>
     *
     * @param campus the campus code to filter by
     */
    public void displaySearchByCampus(String campus) {
        System.out.println(">>Display registration list filter by campus [" + campus + "].");
        displayList(rdao.retrieveList(r -> r.getStudentId().startsWith(campus)));
    }
    
    /**
     * Displays statistical data for registrations grouped by mountain.
     * <p>
     * This method prints a header for the statistics table, then displays each statistic,
     * and finally prints a separator line based on the header length.
     * </p>
     */
    public void displayStatistic() {
        HashMap<String, Statistic> list = getStatistics();
        System.out.println(ResponseViewUitls.header(Statistic.header()));
        for (Statistic s : list.values()) {
            System.out.println(s);
        }
        System.out.println(ResponseViewUitls.line(Statistic.header().length()));
    }
}
