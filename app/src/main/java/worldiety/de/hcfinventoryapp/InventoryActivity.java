package worldiety.de.hcfinventoryapp;

import android.os.Bundle;

import org.homunculus.android.component.HomunculusActivity;
import org.homunculus.android.component.module.validator.HomunculusValidator;
import org.homunculusframework.factory.container.Request;

/**
 * The main activity. Here we add additional members to the scope, e.g. the {@link HomunculusValidator}.
 * <p>
 * Created by aerlemann on 19.02.18.
 */
public class InventoryActivity extends HomunculusActivity {
    @Override
    protected Request create() {
        //On startup, go to splash
        return new Request(InventorySplash.NAME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setup validator for form validation
        HomunculusValidator validator = HomunculusValidator.createAndroidResourceMessagesValidator(this);
        getScope().put("$validator", validator);
    }
}
