package worldiety.de.hcfinventoryapp.inventoryList;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import worldiety.de.hcfinventoryapp.addItem.model.InventoryItem;

/**
 * Created by aerlemann on 20.02.18.
 */

public class InventoryListAdapter extends BaseAdapter {

    private List<InventoryItem> inventoryItems;

    public InventoryListAdapter(List<InventoryItem> items) {
        if (items == null)
            this.inventoryItems = new ArrayList<>();
        else
            this.inventoryItems = new ArrayList<>(items);
    }

    @Override
    public int getCount() {
        return inventoryItems.size();
    }

    @Override
    public InventoryItem getItem(int position) {
        return inventoryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return inventoryItems.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InventoryItem item = getItem(position);
        RelativeLayout contentLayout = new RelativeLayout(parent.getContext());

        AppCompatTextView inventoryNumber = new AppCompatTextView(parent.getContext());
        inventoryNumber.setId(View.generateViewId());
        inventoryNumber.setText(item.getInventoryNumber());
        RelativeLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        contentLayout.addView(inventoryNumber, params);

        AppCompatTextView title = new AppCompatTextView(parent.getContext());
        title.setId(View.generateViewId());
        title.setText(item.getItemName());
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.BELOW, inventoryNumber.getId());
        contentLayout.addView(title, params);

        AppCompatTextView amount = new AppCompatTextView(parent.getContext());
        amount.setId(View.generateViewId());
        amount.setText(MessageFormat.format("{0}", item.getAmount()));
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        contentLayout.addView(amount, params);
        return contentLayout;
    }
}
