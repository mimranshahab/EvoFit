package com.structure.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ctrlplusz.anytextview.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.structure.R;
import com.structure.adapters.listadapters.ProductListinglAdapter;
import com.structure.fragments.abstracts.BaseFragment;
import com.structure.helperclasses.ui.helper.TitleBar;
import com.structure.helperclasses.ui.helper.UIHelper;

import com.structure.managers.retrofit.WebServiceFactory;
import com.structure.model.Products;
import com.structure.model.wrappers.ProductsWrapper;
import com.structure.model.wrappers.WebResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 30-Mar-17.
 */

public class ProductDetailedPageFragment extends BaseFragment {


    @BindView(R.id.txtItemTitle)
    AnyTextView txtItemTitle;
    @BindView(R.id.txtDescription)
    AnyTextView txtDescription;
    @BindView(R.id.txtItemCurrencyType)
    AnyTextView txtItemCurrencyType;
    @BindView(R.id.txtItemOfferPrice)
    AnyTextView txtItemOfferPrice;
    @BindView(R.id.txtOutOfStock)
    AnyTextView txtOutOfStock;
    @BindView(R.id.imgItemLike)
    ImageView imgItemLike;
    @BindView(R.id.btnDecrement)
    ImageButton btnDecrement;
    @BindView(R.id.txtItemQuantity)
    AnyTextView txtItemQuantity;
    @BindView(R.id.btnIncrement)
    ImageButton btnIncrement;
    @BindView(R.id.imgBanner)
    ImageView imgBanner;
    @BindView(R.id.txtMiniDescription)
    AnyTextView txtMiniDescription;


    private Products products;
    private View.OnClickListener onClickListener;
    private int position;
    private ProductListinglAdapter adapterProductDetail;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_product_detailed_page;
    }

    public static ProductDetailedPageFragment newInstance(View.OnClickListener listener, Products products, int position, ProductListinglAdapter adapter) {
        Bundle args = new Bundle();
        ProductDetailedPageFragment fragment = new ProductDetailedPageFragment();
        fragment.onClickListener = listener;
        fragment.adapterProductDetail = adapter;
        fragment.position = position;
        fragment.products = products;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindData();
    }

    private void bindData() {
        ImageLoader.getInstance().displayImage(adapterProductDetail.getItem(position).productImage, imgBanner);
        txtItemTitle.setText(adapterProductDetail.getItem(position).productName);
        txtMiniDescription.setText(adapterProductDetail.getItem(position).weight);
        txtDescription.setText(adapterProductDetail.getItem(position).productDescription);
        txtItemCurrencyType.setText(adapterProductDetail.getItem(position).getCurrencyType());
        txtItemOfferPrice.setText(String.valueOf(adapterProductDetail.getItem(position).price));
        bindFavorite();
        txtItemQuantity.setText(String.valueOf(adapterProductDetail.getItem(position).getItemQuantityInCart()));
    }

    private void bindFavorite() {
        if (adapterProductDetail.getItem(position).isFavorite.equals("1")) {
            imgItemLike.setImageResource(R.drawable.imgheart_filled);
        } else {
            imgItemLike.setImageResource(R.drawable.imgheart_empty);
        }
    }

    private void updateItem() {

        ProductsWrapper cart = prefHelper.getCart();

        for (int indexCartItem = 0; indexCartItem < cart.products.size(); indexCartItem++) {
            if (cart.products.get(indexCartItem).id == products.id) {
                products.itemQuantityInCart = cart.products.get(indexCartItem).itemQuantityInCart;
                if (cart.products.get(indexCartItem).itemQuantityInCart < 1) {
                    prefHelper.deleteItem(indexCartItem);
                    txtItemQuantity.setText(String.valueOf(products.getItemQuantityInCart()));
                    return;
                }
            }
        }
        txtItemQuantity.setText(String.valueOf(products.getItemQuantityInCart()));
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(products.productName);
        titleBar.showBackButton(getMainActivity());

        titleBar.setRightButton2(R.drawable.imgsearch, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().addDockableFragment(SearchFragment.newInstance());
            }
        });

        titleBar.setRightButton(R.drawable.imgcart, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().addDockableFragment(ShoppingCartFragment.newInstance());
            }
        });
        titleBar.setTxtCircle(prefHelper.getCart().getProducts().size());


    }

    private void doDummyLike(int position) {
        if (products.isFavorite.equals("1")) {
            adapterProductDetail.getItem(position).isFavorite = "0";
            imgItemLike.setImageResource(R.drawable.imgheart_empty);
        } else {
            adapterProductDetail.getItem(position).isFavorite = "1";
            imgItemLike.setImageResource(R.drawable.imgheart_filled);
        }
        adapterProductDetail.notifyDataSetChanged();
    }


    @Override
    public void setListeners() {

        btnDecrement.setOnClickListener(this);
        btnDecrement.setTag(position);
        btnIncrement.setOnClickListener(this);
        btnIncrement.setTag(position);
        imgItemLike.setOnClickListener(this);
        imgItemLike.setTag(position);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();


        switch (v.getId()) {
            case R.id.imgItemLike: {
                setFavorite(position, adapterProductDetail.getItem(position).id);
//                doDummyLike(position);
                break;
            }


            case R.id.btnIncrement: {
                if (adapterProductDetail.getItem(position).getItemQuantityInCart() >= adapterProductDetail.getItem(position).quantity) {
                    UIHelper.showToast(getContext(), "Stock limit reached.");
                } else {
                    prefHelper.addItem(adapterProductDetail.getItem(position));
                    adapterProductDetail.notifyDataSetChanged();
                    updateItem();
                }
                break;
            }

            case R.id.btnDecrement: {
                if ((adapterProductDetail.getItem(position).getItemQuantityInCart() < 1)) {
                    UIHelper.showToast(getContext(), "Can't be less than 0");
                } else {
                    prefHelper.decItem(adapterProductDetail.getItem(position));
                    adapterProductDetail.notifyDataSetChanged();
                    updateItem();
                    getMainActivity().getTitleBar().setTxtCircle(prefHelper.getCart().getProducts().size());
                }
                break;
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void setFavorite(final int position, int id) {
        if (prefHelper.isGuest()) {
            UIHelper.showToast(getContext(), "Please Login to use this feature");
        } else {

            WebServiceFactory.getInstance("").setFavorite(prefHelper.getUserID(), id)
                    .enqueue(new Callback<WebResponse<Object>>() {

                        @Override
                        public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {

                            if (response == null || response.body() == null) {

                                return;
                            }
                            if (response.body().isSuccess()) {
                                UIHelper.showToast(getContext(), response.body().message);
                                if (adapterProductDetail.getItem(position).isFavorite.equals("1")) {
                                    adapterProductDetail.getItem(position).isFavorite = "0";
                                    adapterProductDetail.notifyDataSetChanged();
                                    bindFavorite();
                                } else {
                                    adapterProductDetail.getItem(position).isFavorite = "1";
                                    adapterProductDetail.notifyDataSetChanged();
                                    bindFavorite();
                                }
                            } else {
                                UIHelper.showToast(getContext(), response.body().message);
                            }
                        }

                        @Override
                        public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
        }
    }
}
