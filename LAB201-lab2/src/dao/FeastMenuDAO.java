package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.FeastMenu;

/**
 * DAO class for managing FeastMenu data, loaded from a CSV file.
 */
public class FeastMenuDAO {
    
    /**
     * File name containing feast menu data.
     */
    private static final String FILE_NAME = "FeastMenu.csv";
    
    /**
     * Map storing FeastMenu objects indexed by their unique code.
     */
    private Map<String, FeastMenu> map;

    /**
     * Constructor initializes the FeastMenuDAO by loading data from the file.
     */
    public FeastMenuDAO() {
        this.map = load();
    }
    
    /**
     * Loads feast menu data from the CSV file into a map.
     * @return A map containing feast menu items indexed by their unique code.
     */
    private Map<String, FeastMenu> load() {
        Map<String, FeastMenu> read = new HashMap<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            while ((line = br.readLine()) != null) {
                if (line.equals("\ufeffCode,Name,Price,Ingredients")) { // Handles possible BOM issue
                    continue;
                }
                FeastMenu fm = lineToFeastMenu(line);
                read.put(fm.getCode(), fm);
            }
        } catch (IOException e) {
            // Handle error (could log it if needed)
        }
        return read;
    }
    
    /**
     * Retrieves the entire feast menu data map.
     * @return A map of feast menu items indexed by their code.
     */
    public Map<String, FeastMenu> getMap() {
        return map;
    }
    
    /**
     * Retrieves a FeastMenu object by its unique code.
     * @param code The unique identifier of the feast menu item.
     * @return The FeastMenu object if found, otherwise null.
     */
    public FeastMenu getFeastMenuByCode(String code){
        return map.getOrDefault(code, null);
    }
    
    /**
     * Converts a CSV line into a FeastMenu object.
     * @param line The CSV-formatted string.
     * @return A FeastMenu object with extracted data.
     */
    private FeastMenu lineToFeastMenu(String line){
        String[] values = line.split(",");
        String[] ingredients = values[3].replace("\"", "").replace("+ ", "").split("#");
        return new FeastMenu(values[0], values[1], Double.parseDouble(values[2]), ingredients);
    }
    
    /**
     * Checks if a feast menu item exists by its code.
     * @param code The unique identifier of the feast menu item.
     * @return True if the item exists, false otherwise.
     */
    public boolean isExist(String code){
        return map.containsKey(code);
    }
    
    /**
     * Retrieves a list of all feast menu items.
     * @return A list containing all feast menu objects.
     */
    public List<FeastMenu> getList(){
        return new ArrayList<>(map.values());
    }
    
    /**
     * Retrieves the price of a feast menu item by its code.
     * @param code The unique identifier of the feast menu item.
     * @return The price of the feast menu item.
     */
    public double getPriceById(String code){
        return this.map.get(code).getPrice();
    }
}
