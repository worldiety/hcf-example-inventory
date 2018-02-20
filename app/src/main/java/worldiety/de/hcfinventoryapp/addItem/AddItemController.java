package worldiety.de.hcfinventoryapp.addItem;

import android.content.Context;

import org.homunculus.android.component.module.validator.BindingResult;
import org.homunculus.android.component.module.validator.HomunculusValidator;
import org.homunculus.android.component.module.validator.UnspecificValidationError;
import org.homunculusframework.navigation.ModelAndView;

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
@Named(AddItemController.NAME)
public class AddItemController {
    final static String NAME = "/addItemController";
    final static String REQUEST_SAVE = "/save";

    @Inject
    private Database database;

    @Inject
    private Context context;

    @Inject
    private InventoryListController inventoryListController;

    /**
     * Saves the given {@link InventoryItem} to the {@link Database}. Before doing that, it checks the model using
     * {@link HomunculusValidator}.
     *
     * @param entity    an {@link InventoryItem}
     * @param validator a {@link HomunculusValidator}
     * @return {@link ModelAndView} for going back to {@link InventoryListUIS}, if successful, else {@link ModelAndView} for going to {@link AddItemUIS} including the errors
     */
    @Named(AddItemController.REQUEST_SAVE)
    public ModelAndView save(InventoryItem entity, HomunculusValidator validator) {
        BindingResult<InventoryItem> errors = validator.validate(entity);
        if (errors.hasErrors()) {
            //We already have errors. Don't do heavy database IO.
            return new ModelAndView(AddItemUIS.NAME).put(AddItemUIS.PARAMETER_VIEWMODEL, entity).put(AddItemUIS.PARAMETER_ERRORS, errors);
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
            return new ModelAndView(AddItemUIS.NAME).put(AddItemUIS.PARAMETER_VIEWMODEL, entity).put(AddItemUIS.PARAMETER_ERRORS, errors);
        }


    }
}
