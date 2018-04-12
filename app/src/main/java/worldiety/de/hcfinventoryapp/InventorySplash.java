package worldiety.de.hcfinventoryapp;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;

import org.homunculus.android.component.module.splash.Splash;
import org.homunculusframework.factory.container.Binding;
import org.homunculusframework.factory.flavor.hcf.Bind;
import org.homunculusframework.navigation.Navigation;

import javax.inject.Named;

import worldiety.de.hcfinventoryapp.inventoryList.AsyncInventoryListController.InvokeInventoryListControllerShow;
import worldiety.de.hcfinventoryapp.inventoryList.InventoryListController;
import worldiety.de.hcfinventoryapp.inventoryList.InventoryListUIS;

/**
 * A simple splash screen. Leads to {@link InventoryListController}
 * <p>
 * Created by aerlemann on 19.02.18.
 */
@Bind
public class InventorySplash extends Splash {


    public InventorySplash(Activity activity, Handler main, Navigation navigation, LayoutInflater inflater) {
        super(activity, main, navigation, inflater);
    }


    @Override
    protected Binding<?, ?> getTarget() {
        return new InvokeInventoryListControllerShow();
    }
}
