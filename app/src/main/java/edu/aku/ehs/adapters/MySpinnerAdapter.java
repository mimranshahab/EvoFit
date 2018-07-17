package edu.aku.ehs.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.aku.ehs.models.receiving_model.RegisterOptionsModel;
import edu.aku.ehs.R;

public class MySpinnerAdapter extends ArrayAdapter<RegisterOptionsModel> {

    public MySpinnerAdapter(Context context, List<RegisterOptionsModel> items) {
        super(context, android.R.layout.simple_list_item_single_choice, items);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        if (position == 0) {
            return initialSelection(true);
        }
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (position == 0) {
            return initialSelection(false);
        }
        return getCustomView(position, convertView, parent);
    }


    @Override
    public int getCount() {
        return super.getCount() + 1; // Adjust for initial selection item
    }

    private View initialSelection(boolean dropdown) {
        // Just an example using a simple TextView. Create whatever default view
        // to suit your needs, inflating a separate layout if it's cleaner.
        TextView view = new TextView(getContext());
        view.setText("select please");
        int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.x5dp);
        view.setPadding(0, spacing, 0, spacing);

        if (dropdown) { // Hidden when the dropdown is opened
            view.setHeight(0);
        }

        return view;
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        // Distinguish "real" spinner items (that can be reused) from initial selection item
        View row = convertView != null && !(convertView instanceof TextView)
                ? convertView :
                LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_single_choice, parent, false);

        position = position - 1; // Adjust for initial selection item
        RegisterOptionsModel item = getItem(position);

        // ... Resolve edu.aku.family_hifazat.views & populate with data ...

        return row;
    }

}