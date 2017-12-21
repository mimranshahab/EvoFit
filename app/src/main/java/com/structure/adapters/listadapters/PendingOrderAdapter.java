package com.structure.adapters.listadapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ctrlplusz.anytextview.AnyTextView;
import com.structure.R;
import com.structure.helperclasses.DateHelper;
import com.structure.model.InstantOrderModel;
import com.structure.model.Order;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.order;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class PendingOrderAdapter extends ArrayAdapter<Order> {

    public ArrayList<Order> arrayListNotifications = new ArrayList<>();
    private View.OnClickListener onClickListener;
    private boolean hasOptionButton = true;

    public PendingOrderAdapter(Activity context, ArrayList<Order> instantOrderModels, View.OnClickListener onClickListener) {

        super(context, 0, instantOrderModels);
        this.onClickListener = onClickListener;
        arrayListNotifications = instantOrderModels;
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

        final Order currentItem = getItem(position);


        if (listItemView == null) {


            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_pending_order, parent, false);

            viewHolder = new ViewHolder(listItemView);

            listItemView.setTag(viewHolder);

        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) listItemView.getTag();
        }

        viewHolder.btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentItem.isExpanded()) {
                    currentItem.setExpanded(true);
                    viewHolder.btnExpand.setImageResource(R.drawable.imgdrop_down2);
                    viewHolder.contOrderExpandable.setVisibility(View.VISIBLE);

                } else {
                    currentItem.setExpanded(false);
                    viewHolder.btnExpand.setImageResource(R.drawable.imgarrow);
                    viewHolder.contOrderExpandable.setVisibility(View.GONE);
                }
            }
        });
        setViews(viewHolder, currentItem);
        setListener(position, viewHolder);

        return listItemView;
    }

    private void setListener(int position, ViewHolder viewHolder) {
        viewHolder.btnOptions.setTag(position);
        viewHolder.btnOptions.setOnClickListener(onClickListener);

        viewHolder.btnOrderDetail.setTag(position);
        viewHolder.btnOrderDetail.setOnClickListener(onClickListener);

        viewHolder.btnOrderCancel.setTag(position);
        viewHolder.btnOrderCancel.setOnClickListener(onClickListener);
    }

    private void setViews(ViewHolder viewHolder, Order currentItem) {
        viewHolder.txtOrderNo.setText(String.valueOf(currentItem.getOrderID()));
        viewHolder.txtOrderDate.setText(DateHelper.getFormattedDeliveryDateAndTime(currentItem.deliveryDate));
        viewHolder.txtDeliveryTime.setText(currentItem.deliveryHourString);
        viewHolder.txtPrice.setText(String.valueOf(currentItem.totalAmount));


        if (currentItem.isExpanded()) {
            viewHolder.btnExpand.setImageResource(R.drawable.imgdrop_down2);
            viewHolder.contOrderExpandable.setVisibility(View.VISIBLE);
        } else {
            viewHolder.btnExpand.setImageResource(R.drawable.imgarrow);
            viewHolder.contOrderExpandable.setVisibility(View.GONE);
        }


        if (hasOptionButton) {
            viewHolder.btnOptions.setVisibility(View.VISIBLE);
        } else {
            viewHolder.btnOptions.setVisibility(View.GONE);

        }

    }


    public void setHasOptionButton(boolean hasOptionButton) {
        this.hasOptionButton = hasOptionButton;
    }


    static class ViewHolder {
        @BindView(R.id.txtOrderNo)
        AnyTextView txtOrderNo;
        @BindView(R.id.btnOptions)
        ImageButton btnOptions;
        @BindView(R.id.btnExpand)
        ImageButton btnExpand;
        @BindView(R.id.txtOrderDate)
        AnyTextView txtOrderDate;
        @BindView(R.id.anyTextView2)
        AnyTextView anyTextView2;
        @BindView(R.id.txtDeliveryTime)
        AnyTextView txtDeliveryTime;
        @BindView(R.id.txtCurrencyType)
        AnyTextView txtCurrencyType;
        @BindView(R.id.txtPrice)
        AnyTextView txtPrice;
        @BindView(R.id.btnOrderDetail)
        ImageButton btnOrderDetail;
        @BindView(R.id.btnOrderCancel)
        ImageButton btnOrderCancel;
        @BindView(R.id.contOrderExpandable)
        LinearLayout contOrderExpandable;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
