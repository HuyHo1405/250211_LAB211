/*
 * Service class responsible for saving customer and feast order data.
 * Ensures data persistence by calling the respective DAO save methods.
 */
package service;

import dao.DAOContainer;
import dao.CustomerDAO;
import dao.FeastOrderDAO;
import utils.InputterUtils;
import utils.ResponseViewUtils;

/**
 * Service class that handles saving customer and feast order lists.
 */
public class SaveService {
    
    private CustomerDAO cdao = DAOContainer.CDAO;
    private FeastOrderDAO fodao = DAOContainer.FODAO;
    
    /**
     * Saves customer and feast order lists to persistent storage.
     * 
     * @return true if both customer and feast order lists are saved successfully, false otherwise.
     */
    public boolean save(){
        
        if(!cdao.save()){
            ResponseViewUtils.displayError("save customer list", "Internal error!");
            return false;
        }
        
        if(!fodao.save()){
            ResponseViewUtils.displayError("save feast order list", "Internal error!");
            return false;
        }
        
        return true;
    }
    
    /**
     * Prompts the user to confirm whether they want to save the changes.
     * If confirmed, the save operation is executed.
     */
    public void confirmChanges(){
        if(InputterUtils.inputConfirm("save the changes")){
            save();
        }
    }
}
