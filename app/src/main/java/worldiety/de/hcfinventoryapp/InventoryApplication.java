package worldiety.de.hcfinventoryapp;

import org.homunculus.android.component.HomunculusApplication;
import org.homunculus.android.component.module.validator.HomunculusValidator;
import org.homunculusframework.factory.flavor.hcf.ScopeElement;

import worldiety.de.hcfinventoryapp.backend.Database;
import worldiety.de.hcfinventoryapp.backend.MockDatabase;

/**
 * Main application. Here all UIS are added to the configuration.
 * <p>
 * Created by aerlemann on 19.02.18.
 */

public class InventoryApplication extends HomunculusApplication<InventoryApplicationScope> {

    @ScopeElement
    Database createDatabase() {
        Database database = new MockDatabase();
        return database;
    }

    @ScopeElement
    HomunculusValidator createValidator(){
        return HomunculusValidator.createAndroidResourceMessagesValidator(this);
    }

    @Override
    protected InventoryApplicationScope createScope() {
        return new InventoryApplicationScope(this);
    }
}
