package worldiety.de.hcfinventoryapp;

import org.homunculus.android.component.HomunculusActivity;
import org.homunculus.android.component.module.validator.HomunculusValidator;
import org.homunculusframework.factory.container.Binding;

/**
 * The main activity. Here we add additional members to the scope, e.g. the {@link HomunculusValidator}.
 * <p>
 * Created by aerlemann on 19.02.18.
 */
public class InventoryActivity extends HomunculusActivity<InventoryActivityScope> {
    @Override
    protected Binding<?, ?> create() {
        return new BindInventorySplash();
    }



    @Override
    protected InventoryActivityScope createScope() {
        return new InventoryActivityScope(((InventoryApplication) getApplication()).getScope(), this);
    }
}
