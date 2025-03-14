package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.FeastOrder;

/**
 * Data Access Object (DAO) for managing FeastOrder data.
 * This class provides methods to load, save, and perform CRUD operations
 * on feast orders stored in a serialized file.
 */
public class FeastOrderDAO {

    /** File name used for storing feast order data */
    private static final String FILE_NAME = "feast_order.dat";

    /** List of FeastOrder objects */
    private List<FeastOrder> list;

    /**
     * Constructor that initializes the list by loading data from the file.
     */
    public FeastOrderDAO() {
        list = load();
    }

    /**
     * Loads the list of feast orders from the serialized file.
     * If the file does not exist or cannot be read, it returns an empty list.
     *
     * @return List of FeastOrder objects
     */
    public List<FeastOrder> load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<FeastOrder>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves the current list of feast orders to the file.
     *
     * @return true if saving was successful, false otherwise
     */
    public boolean save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(list);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Retrieves the list of all feast orders.
     *
     * @return List of FeastOrder objects
     */
    public List<FeastOrder> getList() {
        return list;
    }

    /**
     * Adds a new FeastOrder to the list.
     *
     * @param feastOrder The FeastOrder object to be added
     * @return true if the order was successfully added
     */
    public boolean create(FeastOrder feastOrder) {
        return this.list.add(feastOrder);
    }

    /**
     * Deletes a FeastOrder based on its index in the list.
     *
     * @param id Index of the FeastOrder to be removed
     * @return The removed FeastOrder, or null if the index is invalid
     */
    public FeastOrder delete(int id) {
        if (id >= 0 && id < list.size()) {
            return this.list.remove(id);
        }
        return null;
    }

    /**
     * Updates a FeastOrder's attribute based on the given choice.
     *
     * @param id Index of the FeastOrder in the list
     * @param choice Field to update (0 = feast menu code, 1 = number of tables, 2 = date)
     * @param value New value for the selected field
     * @return true if the update was successful, false otherwise
     */
    public boolean update(int id, int choice, Object value) {
        if (id >= 0 && id < list.size()) {
            FeastOrder feastOrder = this.list.get(id);
            switch (choice) {
                case 0:
                    feastOrder.setFeastMenuCode((String) value);
                    break;
                case 1:
                    feastOrder.setNumberOfTable((Integer) value);
                    break;
                case 2:
                    feastOrder.setDate((String) value);
                    break;
                default:
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Retrieves a FeastOrder by its index.
     *
     * @param id Index of the FeastOrder in the list
     * @return FeastOrder object, or null if the index is invalid
     */
    public FeastOrder retrieve(int id) {
        return (id >= 0 && id < list.size()) ? list.get(id) : null;
    }

    /**
     * Checks if a FeastOrder exists at the given index.
     *
     * @param id Index of the FeastOrder
     * @return true if the index is valid, false otherwise
     */
    public boolean isExist(int id) {
        return id >= 0 && id < list.size();
    }

    /**
     * Gets a FeastOrder by its index in the list.
     *
     * @param id Index of the FeastOrder
     * @return FeastOrder object, or null if the index is invalid
     */
    public FeastOrder getFeastOrderById(int id) {
        return (id >= 0 && id < list.size()) ? list.get(id) : null;
    }

    /**
     * Gets the index of a given FeastOrder in the list.
     *
     * @param feastOrder The FeastOrder object to find
     * @return Index of the FeastOrder, or -1 if not found
     */
    public int getId(FeastOrder feastOrder) {
        return list.indexOf(feastOrder);
    }
}
