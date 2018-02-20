package worldiety.de.hcfinventoryapp;

import org.homunculus.android.component.module.splash.Splash;
import org.homunculusframework.factory.container.Request;

import javax.inject.Named;

import worldiety.de.hcfinventoryapp.inventoryList.InventoryListController;
import worldiety.de.hcfinventoryapp.inventoryList.InventoryListUIS;

/**
 * A simple splash screen. Leads to {@link InventoryListController}
 * <p>
 * Created by aerlemann on 19.02.18.
 */
@Named(InventorySplash.NAME)
public class InventorySplash extends Splash {

    public static final String NAME = "inventorySplash";

    @Override
    protected Request getTarget() {
        return new Request(InventoryListController.NAME + InventoryListController.REQUEST_SHOW);
    }
}
