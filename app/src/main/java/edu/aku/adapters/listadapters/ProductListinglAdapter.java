package edu.aku.adapters.listadapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ctrlplusz.anytextview.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import edu.aku.R;
import edu.aku.libraries.imageloader.LazyLoading;
import edu.aku.models.Products;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by khanhamza on 27-Feb-17.
 */

public class ProductListinglAdapter extends ArrayAdapter<Products> {

    private ArrayList<Products> arrProducts = new ArrayList<>();
    private View.OnClickListener listener;

    private boolean hasLikeButton = false;
    private boolean hasQuantityButtons = false;


    private boolean hasOutofStock = true;


    public ProductListinglAdapter(Activity context, ArrayList<Products> arrProducts, View.OnClickListener listener) {
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

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);

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

        viewHolder.btnAdd.setOnClickListener(onClick);
        viewHolder.btnAdd.setTag(position);

        viewHolder.btnSubtract.setOnClickListener(onClick);
        viewHolder.btnSubtract.setTag(position);
    }


    private void setViews(ViewHolder viewHolder, Products currentItem, int position) {

        viewHolder.txtTitle.setText(currentItem.productName);
        viewHolder.txtDesc.setText(currentItem.weight);

        viewHolder.txtCurrencyType.setText(currentItem.getCurrencyType());
        viewHolder.txtPrice.setText(String.valueOf(currentItem.price));
        if (currentItem.isFavorite.equals("0")) {
            viewHolder.imgFav.setImageResource(R.drawable.imgheart_empty);
        } else {
            viewHolder.imgFav.setImageResource(R.drawable.imgheart_filled);
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(currentItem.productImage, viewHolder.imgItem, LazyLoading.options);


        if (currentItem.quantity <= 0) {
            viewHolder.imgFav.setVisibility(VISIBLE);
            if (hasOutofStock) {
                viewHolder.txtOutOfStock.setVisibility(VISIBLE);
            } else {
                viewHolder.txtOutOfStock.setVisibility(GONE);
            }
            setQuantityVisibility(viewHolder, GONE);
            viewHolder.txtQuantity.setText(String.valueOf(currentItem.getItemQuantityInCart()));
        } else {
            viewHolder.txtOutOfStock.setVisibility(GONE);
            viewHolder.imgFav.setVisibility(VISIBLE);
            viewHolder.txtQuantity.setText(String.valueOf(currentItem.getItemQuantityInCart()));

            if (hasLikeButton) {
                viewHolder.imgFav.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imgFav.setVisibility(GONE);
            }

            if (hasQuantityButtons) {
                setQuantityVisibility(viewHolder, View.VISIBLE);
            } else {
                setQuantityVisibility(viewHolder, GONE);
            }
        }
    }

    private void setQuantityVisibility(ViewHolder viewHolder, int visible) {
        viewHolder.contQuantity.setVisibility(visible);
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
        @BindView(R.id.txtCurrencyType)
        AnyTextView txtCurrencyType;
        @BindView(R.id.txtPrice)
        AnyTextView txtPrice;
        @BindView(R.id.btnSubtract)
        ImageButton btnSubtract;
        @BindView(R.id.txtQuantity)
        AnyTextView txtQuantity;
        @BindView(R.id.btnAdd)
        ImageButton btnAdd;
        @BindView(R.id.contQuantity)
        LinearLayout contQuantity;
        @BindView(R.id.txtOutOfStock)
        AnyTextView txtOutOfStock;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
