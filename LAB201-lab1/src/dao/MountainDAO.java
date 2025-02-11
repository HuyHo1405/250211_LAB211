package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Mountain;

/**
 * Data Access Object (DAO) for managing {@link Mountain} objects.
 * <p>
 * This class provides methods for loading Mountain objects from a CSV file,
 * saving them back to the file, retrieving all mountains, and displaying them.
 * </p>
 * 
 * @author ho huy
 */
public class MountainDAO {

    /**
     * The name of the CSV file storing the mountain list.
     */
    private static String FILE_NAME = "MountainList.csv";

    /**
     * The list of {@link Mountain} objects loaded from the file.
     */
    private List<Mountain> list;

    /**
     * Constructs a new {@code MountainDAO} and initializes the mountain list by loading data from the CSV file.
     */
    public MountainDAO() {
        this.list = load();
    }

    /**
     * Retrieves all Mountain objects.
     *
     * @return a list of {@link Mountain} objects
     */
    public List<Mountain> retrieveAll() {
        return list;
    }
    
    /**
     * Displays all Mountain objects to the console.
     */
    public void displayAll(){
        for (Mountain mountain : list) {
            System.out.println(mountain);
        }
        System.out.println();
    }

    /**
     * Loads Mountain objects from the CSV file.
     * <p>
     * This method reads each line from the file specified by {@code FILE_NAME},
     * skips the header line, converts each valid line to a {@link Mountain} object using
     * {@link #lineToMountain(String)}, and returns the list of mountains.
     * </p>
     *
     * @return a list of {@link Mountain} objects, or {@code null} if an I/O error occurs
     */
    private List<Mountain> load() {
        List<Mountain> ls = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            while ((line = br.readLine()) != null) {
                if (line.matches(Mountain.HEADER)) {
                    continue;
                }

                Mountain m = lineToMountain(line);
                if (m != null) {
                    ls.add(m);
                }
            }
            return ls;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Saves the list of Mountain objects to the CSV file.
     * <p>
     * This method writes the header followed by each {@link Mountain} object converted to a line of text
     * using {@link #mountainToLine(Mountain)} into the file specified by {@code FILE_NAME}.
     * </p>
     *
     * @return {@code true} if the save operation was successful; {@code false} otherwise
     */
    public boolean save() {
        String line;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(Mountain.HEADER);
            writer.newLine();

            for (Mountain mountain : list) {
                line = mountainToLine(mountain);
                if (line != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Converts a line of text from the CSV file into a {@link Mountain} object.
     * <p>
     * The input string is split using ", " as the delimiter. If the resulting array contains 3 parameters,
     * a {@link Mountain} is created with a {@code null} description; if it contains 4 parameters, the description is used.
     * </p>
     *
     * @param input the line of text to convert
     * @return a {@link Mountain} object represented by the input string, or {@code null} if the input is {@code null}
     *         or the format is unrecognized
     */
    private Mountain lineToMountain(String input) {
        if (input == null) {
            return null;
        }

        String[] params = input.split(", ");
        switch (params.length) {
            case 3:
                return new Mountain(params[0], params[1], params[2], null);
            case 4:
                return new Mountain(params[0], params[1], params[2], params[3]);
            default:
                return null;
        }
    }

    /**
     * Converts a {@link Mountain} object into a line of text for the CSV file.
     * <p>
     * The Mountain is converted to a comma-separated string containing its code, name,
     * province, and description. If the description is {@code null}, an empty string is used.
     * </p>
     *
     * @param mountain the {@link Mountain} object to convert
     * @return a formatted string representing the Mountain, or {@code null} if the Mountain is {@code null}
     */
    private String mountainToLine(Mountain mountain) {
        if (mountain == null) {
            return null;
        }

        return String.format("%s, %s, %s, %s",
                mountain.getCode(),
                mountain.getMountain(),
                mountain.getProvince(),
                mountain.getDescription() == null ? "" : mountain.getProvince());
    }
}
