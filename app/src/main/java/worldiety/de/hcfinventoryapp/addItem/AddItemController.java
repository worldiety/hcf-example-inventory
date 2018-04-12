package worldiety.de.hcfinventoryapp.addItem;

import android.content.Context;

import org.homunculus.android.component.module.validator.BindingResult;
import org.homunculus.android.component.module.validator.HomunculusValidator;
import org.homunculus.android.component.module.validator.UnspecificValidationError;
import org.homunculusframework.factory.container.Binding;
import org.homunculusframework.factory.container.ModelAndView;
import org.homunculusframework.factory.container.ObjectBinding;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import worldiety.de.hcfinventoryapp.R;
import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;
import worldiety.de.hcfinventoryapp.backend.Database;
import worldiety.de.hcfinventoryapp.inventoryList.InventoryListController;
import worldiety.de.hcfinventoryapp.inventoryList.InventoryListUIS;

/**
 * Controller which is responsible for adding a new item to the inventory list
 * <p>
 * Created by aerlemann on 19.02.18.
 */
@Singleton
public class AddItemController {

    @Inject
    public Database database;

    @Inject
    public Context context;

    @Inject
    public InventoryListController inventoryListController;

    @Inject
    public HomunculusValidator validator;

    /**
     * Saves the given {@link InventoryItem} to the {@link Database}. Before doing that, it checks the model using
     * {@link HomunculusValidator}.
     *
     * @param entity    an {@link InventoryItem}
     * @return {@link Binding} for going back to {@link InventoryListUIS}, if successful, else {@link ModelAndView} for going to {@link AddItemUIS} including the errors
     */
    ObjectBinding save(InventoryItem entity) {
        BindingResult<InventoryItem> errors = validator.validate(entity);
        if (errors.hasErrors()) {
            //We already have errors. Don't do heavy database IO.
            return new BindAddItemUIS(entity,errors);
        }

        try {
            database.saveItem(entity);
        } catch (SQLException e) {
            //Because this error is not field-specific, we add it, as UnspecificValidationError
            errors.addCustomValidationError(new UnspecificValidationError(context.getString(R.string.error_could_not_save_to_database), e));
        }

        if (!errors.hasErrors()) {
            //TODO implement backward directive
            return inventoryListController.show();
        } else {
            return new BindAddItemUIS(entity,errors);
        }


    }

    ObjectBinding save2(InventoryItem entity) {
        return null;
    }
}
