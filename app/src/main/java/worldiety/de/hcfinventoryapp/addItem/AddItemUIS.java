package worldiety.de.hcfinventoryapp.addItem;

import android.app.Activity;
import android.app.AlertDialog;
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
import org.homunculusframework.factory.flavor.hcf.Bind;
import org.homunculusframework.navigation.Navigation;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import worldiety.de.hcfinventoryapp.R;
import worldiety.de.hcfinventoryapp.addItem.AsyncAddItemController.InvokeAddItemControllerSave;
import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;
import worldiety.de.hcfinventoryapp.views.inventory.InventoryitemView;

/**
 * UIS, which is responsible for adding a new item to the inventory list
 * <p>
 * Created by aerlemann on 19.02.18.
 */
@Bind
public class AddItemUIS extends FrameLayout {


    @Inject
    Activity activity;

    @Inject
    Navigation navigation;

    @Bind
    @Nullable
    InventoryItem viewModel;

    @Bind
    BindingResult<InventoryItem> errors;

    @Inject
    DefaultModelViewPopulator<InventoryItem> modelViewPopulator;

    public AddItemUIS(@NonNull Context context) {
        super(context);
    }

    @PostConstruct
    void apply() {
        //load layout and set this as content View
        activity.setContentView(this);
        InventoryitemView layout = new InventoryitemView(getContext());
        addView(layout);

        //Initialize model to validate the View with
        if (viewModel == null) {
            viewModel = new InventoryItem();
        }

        //Fill the View with values from the model
        modelViewPopulator.populateView(viewModel, layout);

        //Errors came back from the controller, so we need to handle them
        if (errors != null) {
            //Correct navigation stack here. Otherwise the back button will lead here multiple times
            navigation.pop();
            //Insert errors from the validator into the view
            BindingResult<InventoryItem> errorResult = modelViewPopulator.insertErrorState(layout, errors);

            //Handle errors, which are specific to a field, but cannot be mapped to a View
            StringBuilder otherFieldSpecificErrorsBuilder = null;
            for (FieldSpecificValidationError<InventoryItem> error : errorResult.getFieldSpecificValidationErrors()) {
                if (otherFieldSpecificErrorsBuilder == null)
                    otherFieldSpecificErrorsBuilder = new StringBuilder();

                otherFieldSpecificErrorsBuilder.append(getContext().getString(R.string.error_field_specific_but_no_view, error.getField(), error.getObjectName(), error.getDefaultMessage())).append("\n");
            }

            if (otherFieldSpecificErrorsBuilder != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(otherFieldSpecificErrorsBuilder.toString());
                builder.create().show();
            }

            //Handle other errors
            for (UnspecificValidationError error : errorResult.getUnspecificValidationErrors()) {
                //TODO better handling of UnspecificValidationErrors
                throw new RuntimeException(error.getException());

            }
        }

        Button buttonAdd = layout.getBtAddItem();
        buttonAdd.setOnClickListener(v -> {
            //Fill the model with values from the View
            modelViewPopulator.populateBean(layout, viewModel);
            //Send the model to the controller
            navigation.forward(new InvokeAddItemControllerSave(viewModel));
        });
    }
}
