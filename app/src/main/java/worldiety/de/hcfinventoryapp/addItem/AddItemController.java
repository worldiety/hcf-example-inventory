package worldiety.de.hcfinventoryapp.addItem;

import org.homunculus.android.component.module.validator.BindingResult;
import org.homunculus.android.component.module.validator.HomunculusValidator;
import org.homunculusframework.navigation.ModelAndView;

import javax.inject.Named;
import javax.inject.Singleton;

import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;
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

    @Named(AddItemController.REQUEST_SAVE)
    public ModelAndView save(InventoryItem entity, HomunculusValidator validator) {
        BindingResult<InventoryItem> errors = validator.validate(entity);

        if (!errors.hasErrors()) {
            return new ModelAndView(InventoryListUIS.NAME);
        } else {
            return new ModelAndView(AddItemUIS.NAME).put("viewModel", entity).put("errors", errors);
        }


    }
}
