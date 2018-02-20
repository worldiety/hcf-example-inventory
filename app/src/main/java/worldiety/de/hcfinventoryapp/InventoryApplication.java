package worldiety.de.hcfinventoryapp;

import org.homunculus.android.component.HomunculusApplication;
import org.homunculusframework.factory.container.Configuration;
import org.homunculusframework.scope.Scope;

import worldiety.de.hcfinventoryapp.addItem.AddItemController;
import worldiety.de.hcfinventoryapp.addItem.AddItemUIS;
import worldiety.de.hcfinventoryapp.backend.Database;
import worldiety.de.hcfinventoryapp.backend.MockDatabase;
import worldiety.de.hcfinventoryapp.inventoryList.InventoryListController;
import worldiety.de.hcfinventoryapp.inventoryList.InventoryListUIS;

/**
 * Main application. Here all UIS are added to the configuration.
 * <p>
 * Created by aerlemann on 19.02.18.
 */

public class InventoryApplication extends HomunculusApplication {
    @Override
    protected void onConfigure(Configuration configuration) {
        configuration.add(InventorySplash.class);
        configuration.add(InventoryListUIS.class);
        configuration.add(AddItemUIS.class);
        configuration.add(AddItemController.class);
        configuration.add(InventoryListController.class);
        configuration.add(MockDatabase.class);
    }

    @Override
    protected void onProvide(Scope scope) {
        super.onProvide(scope);

        //Setup the database here
        setupDB(scope);
    }

    private void setupDB(Scope scope) {
        Database database = new MockDatabase();
        scope.put("database", database);
    }
}
