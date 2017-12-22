package edu.aku.adapters.listadapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import edu.aku.R;
import edu.aku.libraries.imageloader.LazyLoading;
import edu.aku.model.Products;
import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ProductDetailAdapter extends ArrayAdapter<Products> {

     private ArrayList<Products> arrProducts = new ArrayList<>();
    private View.OnClickListener listener;

    private boolean hasLikeButton = false;
    private boolean hasQuantityButtons = false;


    private boolean hasOutofStock = true;

    public ProductDetailAdapter(Activity context, ArrayList<Products> arrProducts, View.OnClickListener listener) {
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

            viewHolder = new ViewHolder();

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_favorite, parent, false);

            bindViews(listItemView, viewHolder);


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
        viewHolder.imgLike.setOnClickListener(onClick);
        viewHolder.imgLike.setTag(position);

        viewHolder.btnIncQuantity.setOnClickListener(onClick);
        viewHolder.btnIncQuantity.setTag(position);

        viewHolder.btnDecQuantity.setOnClickListener(onClick);
        viewHolder.btnDecQuantity.setTag(position);
    }

    private void bindViews(View listItemView, ViewHolder viewHolder) {
        viewHolder.imgItem = (ImageView) listItemView.findViewById(R.id.imgItemImage);
        viewHolder.txtTitle = (TextView) listItemView.findViewById(R.id.txtItemTitle);
        viewHolder.txtDescription = (TextView) listItemView.findViewById(R.id.txtDescription);
        viewHolder.txtViewCurrencyType = (TextView) listItemView.findViewById(R.id.txtItemCurrencyType);
        viewHolder.txtPrice = (TextView) listItemView.findViewById(R.id.txtItemOfferPrice);
        viewHolder.imgLike = (ImageView) listItemView.findViewById(R.id.imgItemLike);
        viewHolder.txtOutOfStock = (TextView) listItemView.findViewById(R.id.txtOutOfStock);
        viewHolder.txtQuantityField = (TextView) listItemView.findViewById(R.id.txtItemQuantity);
        viewHolder.btnDecQuantity = (ImageButton) listItemView.findViewById(R.id.btnDecrement);
        viewHolder.btnIncQuantity = (ImageButton) listItemView.findViewById(R.id.btnIncrement);
    }

    private void setViews(ViewHolder viewHolder, Products currentItem, int position) {

        viewHolder.txtTitle.setText(currentItem.productName);
        viewHolder.txtDescription.setText(currentItem.productDescription);
        viewHolder.txtViewCurrencyType.setText(currentItem.getCurrencyType());
        viewHolder.txtPrice.setText(String.valueOf(currentItem.price));
        if (currentItem.isFavorite.equals("0")) {
            viewHolder.imgLike.setImageResource(R.drawable.imgheart_empty);
        } else {
            viewHolder.imgLike.setImageResource(R.drawable.imgheart_filled);
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(currentItem.productImage, viewHolder.imgItem, LazyLoading.options);


        if (currentItem.quantity <= 0) {
            viewHolder.imgLike.setVisibility(VISIBLE);
            if(hasOutofStock){
                viewHolder.txtOutOfStock.setVisibility(VISIBLE);
            } else {
                viewHolder.txtOutOfStock.setVisibility(GONE);
            }
            setQuantityVisibility(viewHolder, GONE);
            viewHolder.txtQuantityField.setText(String.valueOf(currentItem.getItemQuantityInCart()));
        } else {
            viewHolder.txtOutOfStock.setVisibility(GONE);
            viewHolder.imgLike.setVisibility(VISIBLE);
            viewHolder.txtQuantityField.setText(String.valueOf(currentItem.getItemQuantityInCart()));

            if (hasLikeButton) {
                viewHolder.imgLike.setVisibility(VISIBLE);
            } else {
                viewHolder.imgLike.setVisibility(GONE);
            }

            if (hasQuantityButtons) {
                setQuantityVisibility(viewHolder, VISIBLE);
            } else {
                setQuantityVisibility(viewHolder, GONE);
            }
        }

    }

    private void setQuantityVisibility(ViewHolder viewHolder, int visible) {
        viewHolder.txtQuantityField.setVisibility(visible);
        viewHolder.btnDecQuantity.setVisibility(visible);
        viewHolder.btnIncQuantity.setVisibility(visible);
    }

    public void setHasLikeButton(boolean hasLikeButton) {
        this.hasLikeButton = hasLikeButton;
    }

    public void setHasQuantityButtons(boolean hasQuantityButtons) {
        this.hasQuantityButtons = hasQuantityButtons;
    }


    public void setHasOutofStock(boolean hasOutofStock) {
        this.hasOutofStock = hasOutofStock;
    }

    static class ViewHolder {
        private ImageView imgItem;
        private TextView txtTitle;
        private TextView txtDescription;
        private TextView txtViewCurrencyType;
        private TextView txtPrice;
        private ImageView imgLike;
        private TextView txtOutOfStock;
        private TextView txtQuantityField;
        private ImageButton btnIncQuantity;
        private ImageButton btnDecQuantity;
    }
}
