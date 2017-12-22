package edu.aku.adapters.listadapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ctrlplusz.anytextview.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import edu.aku.R;
import edu.aku.libraries.imageloader.LazyLoading;
import edu.aku.model.Products;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by khanhamza on 27-Feb-17.
 */

public class ShoppingCartlAdapter extends ArrayAdapter<Products> {

    private ArrayList<Products> arrProducts = new ArrayList<>();
    private View.OnClickListener listener;

    private boolean hasLikeButton = true;
    private boolean hasQuantityButtons = true;


    public ShoppingCartlAdapter(Activity context, ArrayList<Products> arrProducts, View.OnClickListener listener) {
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

        Products currentItem = getItem(position);

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_product_details, parent, false);

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
        viewHolder.imgDelete.setOnClickListener(onClick);
        viewHolder.imgDelete.setTag(position);

        viewHolder.btnIncrement.setOnClickListener(onClick);
        viewHolder.btnIncrement.setTag(position);

        viewHolder.btnDecrement.setOnClickListener(onClick);
        viewHolder.btnDecrement.setTag(position);
    }


    private void setViews(ViewHolder viewHolder, Products currentItem, int position) {

        viewHolder.txtItemTitle.setText(currentItem.productName);
        viewHolder.txtDescription.setText(currentItem.weight);
        viewHolder.txtItemCurrencyType.setText(currentItem.getCurrencyType());
        viewHolder.txtItemOfferPrice.setText(String.valueOf(currentItem.price));
        viewHolder.txtItemQuantity.setText(String.valueOf(currentItem.getItemQuantityInCart()));

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(currentItem.productImage, viewHolder.imgItemImage, LazyLoading.options);
    }

    static class ViewHolder {
        @BindView(R.id.placeHolder)
        ImageView placeHolder;
        @BindView(R.id.imgItemImage)
        ImageView imgItemImage;
        @BindView(R.id.layoutItem)
        RelativeLayout layoutItem;
        @BindView(R.id.txtItemTitle)
        AnyTextView txtItemTitle;
        @BindView(R.id.txtDescription)
        AnyTextView txtDescription;
        @BindView(R.id.txtItemCurrencyType)
        AnyTextView txtItemCurrencyType;
        @BindView(R.id.txtItemOfferPrice)
        AnyTextView txtItemOfferPrice;
        @BindView(R.id.imgDelete)
        ImageView imgDelete;
        @BindView(R.id.btnDecrement)
        ImageButton btnDecrement;
        @BindView(R.id.txtItemQuantity)
        AnyTextView txtItemQuantity;
        @BindView(R.id.btnIncrement)
        ImageButton btnIncrement;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
