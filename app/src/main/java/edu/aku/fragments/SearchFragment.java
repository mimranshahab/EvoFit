package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.adapters.listadapters.ProductListinglAdapter;
import edu.aku.constatnts.WebServiceConstants;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.model.Products;
import edu.aku.model.wrappers.ProductsWrapper;
import edu.aku.model.wrappers.WebResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 27-Feb-17.
 */

public class SearchFragment extends BaseFragment implements TextView.OnEditorActionListener {

    @BindView(R.id.gvProductDetail)
    GridView gvProductListing;
    @BindView(R.id.txtCurrencyType)
    AnyTextView txtCurrencyType;
    @BindView(R.id.txtPrice)
    AnyTextView txtPrice;
    @BindView(R.id.contCheckout)
    LinearLayout contCheckout;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    private ProductListinglAdapter adapterProductListing;
    private static final String CLASS_TAG = "ProductListingFragment";
    private static final String ID_KEY = "ID_KEY";
    private static final String TITLE_KEY = "TITLE_KEY";
    private String title = "";
    private int brandID;
    private ArrayList<Products> arrProducts;
    private Call<WebResponse<ProductsWrapper>> getSearchListCall;
    private Call<WebResponse<Object>> setFavouriteCall;

    @BindView(R.id.txtItemInCart)
    AnyTextView txtItemInCart;

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_product_listing;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(TITLE_KEY);
        brandID = getArguments().getInt(ID_KEY);
        arrProducts = new ArrayList<Products>();
        arrProducts.clear();
//        dummyData();

        adapterProductListing = new ProductListinglAdapter(getActivity(), arrProducts, this);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contCheckout.setVisibility(View.VISIBLE);
        txtCurrencyType.setText(WebServiceConstants.CURRENCY_TYPE);
        gvProductListing.setEmptyView(txtEmpty);

        bindData();
        setAdapter();
    }

    private void bindData() {
        txtItemInCart.setText(String.valueOf(prefHelper.getCart().getProducts().size()));
        txtPrice.setText(getFormattedNumber(prefHelper.getCart().getTotalPrice(), 3));
    }

//    private void dummyData() {
//        arrProducts.clear();
//        int offsetForDummyID = 32;
//        for (int i = 1; i < 8; i++) {
//            arrProducts.add(new Products((i + offsetForDummyID), "Item" + (i + offsetForDummyID), "185 GMS", Constants.CHIPS_IMAGES[i], 0.005f, 4));
//        }
//
//    }

    @Override
    public void setTitlebar(final TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.search));
        titleBar.setSearchField(getMainActivity(), this);
        titleBar.setLeftButtonSearchBar(R.drawable.imgsearch, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardHide.hideSoftKeyboard(getContext(), getView());
                titleBar.closeSearchField(getMainActivity());
            }
        });

        titleBar.setRightButtonSearchBar(R.drawable.imgcancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardHide.hideSoftKeyboard(getContext(), getView());
                titleBar.closeSearchField(getMainActivity());
            }
        });

    }

    @Override
    public void setListeners() {
        contCheckout.setOnClickListener(this);
        contCheckout.setTag(R.id.contCheckout);
        gvProductListing.setOnItemClickListener(this);
    }


    private void setAdapter() {
        updateCart();


        if (!arrProducts.isEmpty()) {
            txtCurrencyType.setText(arrProducts.get(0).getCurrencyType());
        }
        adapterProductListing.setHasLikeButton(true);
        adapterProductListing.setHasQuantityButtons(true);
        gvProductListing.setAdapter(adapterProductListing);
        adapterProductListing.notifyDataSetChanged();
    }

    private void updateCart() {
        ProductsWrapper cart = prefHelper.getCart();

        for (int indexCartItem = 0; indexCartItem < cart.products.size(); indexCartItem++) {
            for (int indexProduct = 0; indexProduct < arrProducts.size(); indexProduct++) {
                if (cart.products.get(indexCartItem).id == arrProducts.get(indexProduct).id) {
                    arrProducts.get(indexProduct).itemQuantityInCart = cart.products.get(indexCartItem).itemQuantityInCart;
                    if (cart.products.get(indexCartItem).itemQuantityInCart < 1) {
                        prefHelper.deleteItem(indexCartItem);
                        return;
                    }
                }
            }
        }
    }


    private void setFavorite(final int position, int id) {
        if (prefHelper.isGuest()) {
            UIHelper.showToast(getContext(), "Please Login to use this feature");
        } else {
            setFavouriteCall = WebServiceFactory.getInstance("").setFavorite(prefHelper.getUserID(), id);
            setFavouriteCall.enqueue(new Callback<WebResponse<Object>>() {

                @Override
                public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {

                    if (response == null || response.body() == null) {
                        return;
                    }
                    if (response.body().isSuccess()) {
                        UIHelper.showToast(getContext(), response.body().message);
                        if (adapterProductListing.getItem(position).isFavorite.equals("1")) {
                            adapterProductListing.getItem(position).isFavorite = "0";
                            adapterProductListing.notifyDataSetChanged();
                        } else {
                            adapterProductListing.getItem(position).isFavorite = "1";
                            adapterProductListing.notifyDataSetChanged();
                        }
                    } else {
                        UIHelper.showToast(getContext(), response.body().message);
                    }
                }

                @Override
                public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                    if (!setFavouriteCall.isCanceled()) {
                        t.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();


        switch (v.getId()) {

            case R.id.contCheckout: {
                getMainActivity().addDockableFragment(ShoppingCartFragment.newInstance());
                break;
            }

            case R.id.imgFav: {
//                doDummyLike(position);
                setFavorite(position, adapterProductListing.getItem(position).id);
                break;
            }

            case R.id.btnAdd: {
                if (adapterProductListing.getItem(position).getItemQuantityInCart() >= adapterProductListing.getItem(position).quantity) {
                    UIHelper.showToast(getContext(), "Stock limit reached.");
                } else {
                    prefHelper.addItem(adapterProductListing.getItem(position));
                    updateCart();
                    bindData();
                    adapterProductListing.notifyDataSetChanged();
                }
                break;
            }

            case R.id.btnSubtract: {
                if ((adapterProductListing.getItem(position).getItemQuantityInCart() < 1)) {
                    UIHelper.showToast(getContext(), "Can't be less than 0");
                } else {
                    prefHelper.decItem(adapterProductListing.getItem(position));
                    updateCart();
                    bindData();
                    adapterProductListing.notifyDataSetChanged();
                }
                break;
            }
        }
    }


    private void doDummyLike(int position) {
        if (arrProducts.get(position).isFavorite.equals("1")) {
            arrProducts.get(position).isFavorite = "0";
        } else {
            arrProducts.get(position).isFavorite = "1";
        }
        adapterProductListing.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getMainActivity().addDockableFragment(ProductDetailedPageFragment.newInstance(this, adapterProductListing.getItem(position), position, adapterProductListing));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void getSearchList(String keyword) {
        getSearchListCall = WebServiceFactory.getInstance(prefHelper.getUser().token).getSearchList(keyword, prefHelper.getUserID());
        getSearchListCall.enqueue(new Callback<WebResponse<ProductsWrapper>>() {

            @Override
            public void onResponse(Call<WebResponse<ProductsWrapper>> call, Response<WebResponse<ProductsWrapper>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    arrProducts.clear();
                    arrProducts.addAll(response.body().result.products);
                    updateCart();
                    adapterProductListing.notifyDataSetChanged();

                } else {
                    UIHelper.showToast(getContext(), response.body().message);

                }
            }

            @Override
            public void onFailure(Call<WebResponse<ProductsWrapper>> call, Throwable t) {
                if (!getSearchListCall.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });

    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String keyword = getMainActivity().getTitleBar().getEdtSearchFieldText();
            if (keyword.isEmpty() || keyword.length() < 4) {
                UIHelper.showToast(getContext(), "Keyword length must be greater than 3");
            } else {
                getSearchList(keyword);
            }
            KeyboardHide.hideSoftKeyboard(getContext(), getView());
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        if (getSearchListCall != null) {
            getSearchListCall.cancel();
        }

        if (setFavouriteCall != null) {
            setFavouriteCall.cancel();
        }
        super.onDestroy();
    }
}
