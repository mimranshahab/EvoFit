package edu.aku.adapters.listadapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ctrlplusz.anytextview.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import edu.aku.R;
import edu.aku.libraries.imageloader.LazyLoading;
import edu.aku.model.ItemModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by khanhamza on 27-Feb-17.
 */

public class OrderDetailslAdapter extends ArrayAdapter<ItemModel> {

    private ArrayList<ItemModel> arrProducts = new ArrayList<>();
    private View.OnClickListener listener;

    private boolean hasLikeButton = false;

    public OrderDetailslAdapter(Activity context, ArrayList<ItemModel> arrProducts, View.OnClickListener listener) {
        super(context, 0, arrProducts);
        this.arrProducts = arrProducts;
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

        ItemModel currentItem = getItem(position);

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_order_details, parent, false);

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
        viewHolder.imgFav.setOnClickListener(onClick);
        viewHolder.imgFav.setTag(position);
    }


    private void setViews(ViewHolder viewHolder, ItemModel currentItem, int position) {
        viewHolder.txtTitle.setText(currentItem.product.productName);
        viewHolder.txtDesc.setText(currentItem.product.weight);
        viewHolder.txtCurrencyType.setText(currentItem.product.getCurrencyType());
        viewHolder.txtPrice.setText(String.valueOf(currentItem.totalPrice));
        viewHolder.txtOrderQuantity.setText(currentItem.quantity + " Units x " + currentItem.unitPrice);

        if (currentItem.product.isFavorite.equals("0")) {
            viewHolder.imgFav.setImageResource(R.drawable.imgheart_empty);
        } else {
            viewHolder.imgFav.setImageResource(R.drawable.imgheart_filled);
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(currentItem.product.productImage, viewHolder.imgItem, LazyLoading.options);


//        if (hasLikeButton) {
//            viewHolder.imgFav.setVisibility(View.VISIBLE);
//        } else {
//            viewHolder.imgFav.setVisibility(GONE);
//        }


    }

    static class ViewHolder {
        @BindView(R.id.placeHolder)
        ImageView placeHolder;
        @BindView(R.id.imgItem)
        ImageView imgItem;
        @BindView(R.id.imgFav)
        ImageView imgFav;
        @BindView(R.id.txtTitle)
        AnyTextView txtTitle;
        @BindView(R.id.txtDesc)
        AnyTextView txtDesc;
        @BindView(R.id.txtOrderQuantity)
        AnyTextView txtOrderQuantity;
        @BindView(R.id.txtCurrencyType)
        AnyTextView txtCurrencyType;
        @BindView(R.id.txtPrice)
        AnyTextView txtPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

