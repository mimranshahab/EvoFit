package com.structure.adapters.listadapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ctrlplusz.anytextview.AnyTextView;
import com.structure.R;
import com.structure.model.InstantOrderModel;
import com.structure.model.Order;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class InstantOrderAdapter extends ArrayAdapter<Order> {

    public ArrayList<Order> arrData = new ArrayList<>();

    public InstantOrderAdapter(Activity context, ArrayList<Order> instantOrderModels) {

        super(context, 0, instantOrderModels);

        arrData = instantOrderModels;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;


        final ViewHolder viewHolder;

        Order currentItem = getItem(position);


        if (listItemView == null) {


            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_instant_order, parent, false);

            viewHolder = new ViewHolder(listItemView);

            listItemView.setTag(viewHolder);

        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) listItemView.getTag();
        }

        setViews(viewHolder, currentItem);
        return listItemView;
    }

    private void setViews(ViewHolder viewHolder, Order currentItem) {
        viewHolder.txtOrderNo.setText( currentItem.getOrderID());
    }


    static class ViewHolder {
        @BindView(R.id.txtOrderNo)
        AnyTextView txtOrderNo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
