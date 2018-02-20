package worldiety.de.hcfinventoryapp.inventoryList;

import org.homunculusframework.navigation.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;
import worldiety.de.hcfinventoryapp.backend.Database;

/**
 * Controller for {@link InventoryListUIS}, which loads data from the database and initializes the UI-State afterwards.
 * <p>
 * Created by aerlemann on 20.02.18.
 */
@Singleton
@Named(InventoryListController.NAME)
public class InventoryListController {
    public final static String NAME = "/inventoryListController";
    public final static String REQUEST_SHOW = "/show";

    @Inject
    private Database database;

    /**
     * Prepares the model for {@link InventoryListUIS} (loads data from the database). Afterwards it shows the UI-State.
     *
     * @return {@link ModelAndView}, with loaded {@link InventoryItem}s which leads to {@link InventoryListUIS}
     */
    @Named(REQUEST_SHOW)
    public ModelAndView show() {
        List<InventoryItem> items = new ArrayList<>();

        try {
            items.addAll(database.loadItems());
        } catch (SQLException e) {
            //TODO handle me better
            throw new RuntimeException("Items could not be loaded!", e);
        }

        return new ModelAndView(InventoryListUIS.NAME).put(InventoryListUIS.PARAMETER_INVENTORY_ITEMS, items);
    }
}
