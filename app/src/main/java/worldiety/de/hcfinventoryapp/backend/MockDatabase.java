package worldiety.de.hcfinventoryapp.backend;

import android.support.annotation.NonNull;

import org.homunculusframework.factory.flavor.hcf.Persistent;
import org.homunculusframework.lang.Reference;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;

/**
 * Simple mock-database, which internally uses a List
 * <p>
 * Created by aerlemann on 20.02.18.
 */
public class MockDatabase implements Database {

    @Persistent(name = "pseudo-db")
    private Reference<DamnIt> savedItems;


    @PostConstruct
    private void init() {
        if (savedItems.get() == null) {
            savedItems.set(initTestItems());
        }
    }

    /**
     * Creates some test items
     *
     * @return a {@link List} of {@link InventoryItem}s
     */
    private DamnIt initTestItems() {
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

        return new DamnIt(items);
    }

    @Override
    public DamnIt loadItems() throws SQLException {
        try {
            //Databases have IO, which we simulate here
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //because this is a simple mock, we don't do anything here
        }
        return new DamnIt(savedItems.get());
    }

    @Override
    public void saveItem(InventoryItem item) throws SQLException {
        savedItems.get().add(item);
        //TODO this does not seem to persist anything
        savedItems.set(savedItems.get());
        try {
            //Databases have IO, which we simulate here
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //because this is a simple mock, we don't do anything here
        }
    }

    //TODO @Persistant does not work with generified types
    private static class DamnIt extends ArrayList<InventoryItem> {
        public DamnIt(int initialCapacity) {
            super(initialCapacity);
        }

        public DamnIt() {
        }

        public DamnIt(@NonNull Collection<? extends InventoryItem> c) {
            super(c);
        }
    }
}
