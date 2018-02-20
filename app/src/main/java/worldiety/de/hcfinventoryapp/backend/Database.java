package worldiety.de.hcfinventoryapp.backend;

import java.sql.SQLException;
import java.util.List;

import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;

/**
 * Interface for the communication with the database
 * <p>
 * Created by aerlemann on 20.02.18.
 */
public interface Database {
    /**
     * Loads all {@link InventoryItem}s from the database
     *
     * @return all {@link InventoryItem}s in the database
     * @throws SQLException
     */
    List<InventoryItem> loadItems() throws SQLException;

    /**
     * Saves the given {@link InventoryItem} to the database
     *
     * @param item an {@link InventoryItem}
     * @throws SQLException
     */
    void saveItem(InventoryItem item) throws SQLException;
}
