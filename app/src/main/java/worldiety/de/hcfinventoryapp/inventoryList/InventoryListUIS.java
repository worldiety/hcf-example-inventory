package worldiety.de.hcfinventoryapp.inventoryList;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import org.homunculusframework.factory.container.Request;
import org.homunculusframework.navigation.Navigation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import worldiety.de.hcfinventoryapp.R;
import worldiety.de.hcfinventoryapp.addItem.AddItemUIS;
import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;

/**
 * UIS, which is responsible for showing a list of inventorized items
 * <p>
 * Created by aerlemann on 19.02.18.
 */
@Named(InventoryListUIS.NAME)
public class InventoryListUIS extends FrameLayout {

    public final static String NAME = "inventoryList";
    public final static String PARAMETER_INVENTORY_ITEMS = "inventoryItems";

    @Inject
    private Activity activity;

    @Inject
    @Named(PARAMETER_INVENTORY_ITEMS)
    private List<InventoryItem> itemList;

    @Inject
    Navigation navigation;

    public InventoryListUIS(@NonNull Context context) {
        super(context);
    }

    @PostConstruct
    private void apply() {
        //load layout and set this as content View
        activity.setContentView(this);
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.uis_inventory_list, null);
        addView(layout);

        ListView inventoryList = layout.findViewById(R.id.inventoryList);
        inventoryList.setAdapter(new InventoryListAdapter(itemList));

        //setup action button to navigate to AddItemUIS
        FloatingActionButton addItemButton = layout.findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(v -> navigation.forward(new Request(AddItemUIS.class)));
    }
}
