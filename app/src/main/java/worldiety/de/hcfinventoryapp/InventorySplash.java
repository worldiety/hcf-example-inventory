package worldiety.de.hcfinventoryapp;

import org.homunculus.android.component.module.splash.Splash;

import javax.inject.Named;

import worldiety.de.hcfinventoryapp.inventoryList.InventoryListUIS;

/**
 * A simple splash screen. Leads to {@link InventoryListUIS}
 * <p>
 * Created by aerlemann on 19.02.18.
 */
@Named(InventorySplash.NAME)
public class InventorySplash extends Splash {

    public static final String NAME = "inventorySplash";

    @Override
    protected Class<?> getTarget() {
        return InventoryListUIS.class;
    }
}
