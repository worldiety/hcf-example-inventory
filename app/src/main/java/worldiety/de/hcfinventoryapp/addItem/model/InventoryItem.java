package worldiety.de.hcfinventoryapp.addItem.model;

import org.hibernate.validator.constraints.EAN;
import org.hibernate.validator.constraints.NotEmpty;
import org.homunculus.android.flavor.Resource;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;

import worldiety.de.hcfinventoryapp.R;

/**
 * This is the model for an item in the inventory list. Fields are annotated, so that they can be
 * validated by the {@link org.homunculus.android.component.module.validator.HomunculusValidator}
 * <p>
 * Created by aerlemann on 19.02.18.
 */
public class InventoryItem implements Serializable{

    //Resource annotation is needed, so the validator can match this field to a View
    @Resource(R.id.ed_inventoryNumber)
    //Field shall not be empty. If it is R.string.error_not_empty is shown as error in the View
    @NotEmpty(message = "error_not_empty")
    private String inventoryNumber;

    @Resource(R.id.ed_inventoryName)
    @NotEmpty(message = "error_not_empty")
    private String itemName;

    @Resource(R.id.ed_inventoryAmount)
    @DecimalMin(value = "0", message = "error_min_zero")
    private int amount;

    @Resource(R.id.ed_inventoryLocation)
    @NotEmpty(message = "error_not_empty")
    private String location;

    @Resource(R.id.ed_inventoryEAN)
    @EAN(message = "error_no_ean")
    private String EAN;

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }
}
