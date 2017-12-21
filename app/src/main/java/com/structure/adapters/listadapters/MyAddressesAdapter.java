package com.structure.adapters.listadapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.ctrlplusz.anytextview.AnyTextView;
import com.structure.R;
import com.structure.activities.MainActivity;
import com.structure.helperclasses.PaypalHelper;
import com.structure.model.extramodels.AddressModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by khanhamza on 27-Feb-17.
 */

public class MyAddressesAdapter extends ArrayAdapter<AddressModel> {

    private ArrayList<AddressModel> arrayListOrderItems = new ArrayList<AddressModel>();
    private View.OnClickListener listener;

    private MainActivity activity;


    public MyAddressesAdapter(Activity activity, ArrayList<AddressModel> arrayListOrderItems, View.OnClickListener listener) {

        super(activity, 0, arrayListOrderItems);
        this.arrayListOrderItems = arrayListOrderItems;
        this.activity = (MainActivity) activity;
        this.listener = listener;
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

        AddressModel currentItem = getItem(position);

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_my_address, parent, false);
            viewHolder = new ViewHolder(listItemView);
            listItemView.setTag(viewHolder);

        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) listItemView.getTag();
        }

        setListeners(viewHolder, listener, position);
        setViews(viewHolder, currentItem, position);

        return listItemView;
    }


    private void setListeners(ViewHolder viewHolder, View.OnClickListener onClick, int position) {

        viewHolder.btnDeleteAddress.setOnClickListener(onClick);
        viewHolder.btnDeleteAddress.setTag(position);

        viewHolder.btnEditAddress.setOnClickListener(onClick);
        viewHolder.btnEditAddress.setTag(position);


        viewHolder.imgDefaultAddress.setOnClickListener(onClick);
        viewHolder.imgDefaultAddress.setTag(position);

//        viewHolder.rbAddress.setTag(position);
//        viewHolder.rbAddress.setOnClickListener(onClick);
    }


    private void setViews(ViewHolder viewHolder, AddressModel currentItem, int position) {

//        viewHolder.rbAddress.setChecked(currentItem.getIsDefault());
        viewHolder.txtAddress.setText(currentItem.getAddressString());

        if (currentItem.getIsDefault()) {
            viewHolder.imgDefaultAddress.setImageResource(R.drawable.imgtick_selected);
        } else {
            viewHolder.imgDefaultAddress.setImageResource(R.drawable.imgtick_unselected);
        }

//        viewHolder.txtAdditionalNotes.setText(currentItem.getAdditionalNotes());

/***
 BELOW COMMENTED CODE IS FOR FUTURE USE
 */

//        String urlFromArray = Constants.IMAGES[position + randomUrlNumber];
//
//
//        LazyLoading imageLoader = LazyLoading.getInstance();
//        imageLoader.displayImage(urlFromArray, viewHolder.imgItem, LazyLoading.options);


    }


    static class ViewHolder {
        @BindView(R.id.imgDefaultAddress)
        ImageView imgDefaultAddress;
        @BindView(R.id.txtAddress)
        AnyTextView txtAddress;
        @BindView(R.id.btnDeleteAddress)
        ImageButton btnDeleteAddress;
        @BindView(R.id.btnEditAddress)
        ImageButton btnEditAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
