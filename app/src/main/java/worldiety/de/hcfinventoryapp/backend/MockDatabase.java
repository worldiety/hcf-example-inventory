package worldiety.de.hcfinventoryapp.backend;

import android.content.Context;

import org.homunculus.android.component.module.storage.Persistent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;

/**
 * Simple mock-database, which internally uses a List
 * <p>
 * Created by aerlemann on 20.02.18.
 */
public class MockDatabase implements Database {

    private Persistent<List<InventoryItem>> pseudoDB;

    public MockDatabase(Context context) {
        pseudoDB = new Persistent<>(context, "pseudoDB");
        if (pseudoDB.get() == null) {
            pseudoDB.set(initTestItems());
        }
    }


    /**
     * Creates some test items
     *
     * @return a {@link List} of {@link InventoryItem}s
     */
    private List<InventoryItem> initTestItems() {
        List<InventoryItem> items = new ArrayList<>();
        InventoryItem item1 = new InventoryItem();
        item1.setInventoryNumber("1");
        item1.setAmount(1);
        item1.setEAN("8888888888888");
        item1.setItemName("Super item");
        item1.setLocation("WDY Headquarters");

        items.add(item1);

        InventoryItem item2 = new InventoryItem();
        item2.setInventoryNumber("2");
        item2.setAmount(3);
        item2.setEAN("8888888888887");
        item2.setItemName("Awesome item");
        item2.setLocation("WDY Headquarters");

        items.add(item2);

        return items;
    }

    @Override
    public List<InventoryItem> loadItems() throws SQLException {
        try {
            //Databases have IO, which we simulate here
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //because this is a simple mock, we don't do anything here
        }
        return pseudoDB.get();
    }

    @Override
    public void saveItem(InventoryItem item) throws SQLException {
        pseudoDB.get().add(item);
        pseudoDB.set(pseudoDB.get());
        try {
            pseudoDB.save();
        } catch (IOException e) {
            throw new SQLException(e);
        }
        try {
            //Databases have IO, which we simulate here
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //because this is a simple mock, we don't do anything here
        }
    }
}
