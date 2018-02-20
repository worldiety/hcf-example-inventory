package worldiety.de.hcfinventoryapp.addItem;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import org.homunculus.android.component.module.validator.BindingResult;
import org.homunculus.android.component.module.validator.DefaultModelViewPopulator;
import org.homunculus.android.component.module.validator.FieldSpecificValidationError;
import org.homunculus.android.component.module.validator.UnspecificValidationError;
import org.homunculusframework.factory.container.Request;
import org.homunculusframework.navigation.Navigation;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import worldiety.de.hcfinventoryapp.R;
import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;

/**
 * UIS, which is responsible for adding a new item to the inventory list
 * <p>
 * Created by aerlemann on 19.02.18.
 */
@Named(AddItemUIS.NAME)
public class AddItemUIS extends FrameLayout {

    public final static String NAME = "addItem";
    public final static String PARAMETER_VIEWMODEL = "viewModel";
    public final static String PARAMETER_ERRORS = "errors";

    @Inject
    private Activity activity;

    @Inject
    Navigation navigation;

    @Inject
    @Named(PARAMETER_VIEWMODEL)
    private InventoryItem viewModel;

    @Inject
    @Named(PARAMETER_ERRORS)
    private BindingResult<InventoryItem> errors;

    @Inject
    private DefaultModelViewPopulator<InventoryItem> modelViewPopulator;

    public AddItemUIS(@NonNull Context context) {
        super(context);
    }

    @PostConstruct
    private void apply() {
        //load layout and set this as content View
        activity.setContentView(this);
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.uis_add_item, null);
        addView(layout);

        //Initialize model to validate the View with
        if (viewModel == null) {
            viewModel = new InventoryItem();
        }

        //Fill the View with values from the model
        modelViewPopulator.populateView(viewModel, layout);

        //Errors came back from the controller, so we need to handle them
        if (errors != null) {
            //Insert errors from the validator into the view
            BindingResult<InventoryItem> errorResult = modelViewPopulator.insertErrorState(layout, errors);
            //Handle errors, which are specific to a field, but cannot be mapped to a View
            for (FieldSpecificValidationError<InventoryItem> error : errorResult.getFieldSpecificValidationErrors()) {
                //TODO handle errors, which cannot be assigned to a View
                LoggerFactory.getLogger(this.getClass()).error("Handle me! I'm a constraint error!: " + error.getField() + ", " + error.getObjectName() + ", " + error.getRejectedValue() + ", " + error.getDefaultMessage());

            }

            //Handle other errors
            for (UnspecificValidationError error : errorResult.getUnspecificValidationErrors()) {
                //TODO handle UnspecificValidationErrors
                LoggerFactory.getLogger(this.getClass()).error("Handle me! I'm a custom error!: " + error.getMessage() + ", " + error.getException());

            }
        }

        Button buttonAdd = layout.findViewById(R.id.bt_add_item);
        buttonAdd.setOnClickListener(v -> {
            //Fill the model with values from the View
            modelViewPopulator.populateBean(layout, viewModel);
            //Send the model to the controller
            navigation.forward(new Request(AddItemController.NAME + AddItemController.REQUEST_SAVE).put("entity", viewModel));
        });
    }
}
