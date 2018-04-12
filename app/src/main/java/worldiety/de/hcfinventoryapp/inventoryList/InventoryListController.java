package worldiety.de.hcfinventoryapp.inventoryList;

import org.homunculusframework.factory.container.Binding;
import org.homunculusframework.factory.container.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import worldiety.de.hcfinventoryapp.ActivityScope;
import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;
import worldiety.de.hcfinventoryapp.backend.Database;

/**
 * Controller for {@link InventoryListUIS}, which loads data from the database and initializes the UI-State afterwards.
 * <p>
 * Created by aerlemann on 20.02.18.
 */
@Singleton
public class InventoryListController {

    @Inject
    public Database database;

    /**
     * Prepares the model for {@link InventoryListUIS} (loads data from the database). Afterwards it shows the UI-State.
     *
     * @return {@link Binding}, with loaded {@link InventoryItem}s which leads to {@link InventoryListUIS}
     */
    public ModelAndView show() {
        List<InventoryItem> items = new ArrayList<>();

        try {
            items.addAll(database.loadItems());
        } catch (SQLException e) {
            //TODO handle me better
            throw new RuntimeException("Items could not be loaded!", e);
        }
        return new BindInventoryListUIS(items);
    }
}
