package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.adapters.listadapters.ProductListinglAdapter;
import edu.aku.constatnts.WebServiceConstants;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.Products;
import edu.aku.models.wrappers.ProductsWrapper;
import edu.aku.models.wrappers.WebResponse;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 27-Feb-17.
 */

public class ProductListingFragment extends BaseFragment {

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
    @BindView(R.id.txtItemInCart)
    AnyTextView txtItemInCart;
    private ProductListinglAdapter adapterProductListing;
    private static final String CLASS_TAG = "ProductListingFragment";
    private static final String ID_KEY = "ID_KEY";
    private static final String TITLE_KEY = "TITLE_KEY";
    private String title = "";
    private int subCategoryID;
    private ArrayList<Products> arrProducts;
    private Call<WebResponse<ProductsWrapper>> getProductsCall;
    private Call<WebResponse<Object>> setFavoriteCall;
    ProductTabFragment parentFragment;
    ProductListingFragment registeredFragment;


    public static ProductListingFragment newInstance(int id, String title) {
        Bundle args = new Bundle();
        args.putInt(ID_KEY, id);
        args.putString(TITLE_KEY, title);
        ProductListingFragment fragment = new ProductListingFragment();
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
        subCategoryID = getArguments().getInt(ID_KEY);
        arrProducts = new ArrayList<Products>();
        arrProducts.clear();
        adapterProductListing = new ProductListinglAdapter(getActivity(), arrProducts, this);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contCheckout.setVisibility(View.VISIBLE);
        txtCurrencyType.setText(WebServiceConstants.CURRENCY_TYPE);
        gvProductListing.setEmptyView(txtEmpty);

        bindData();
        setAdapter();
        getProducts(subCategoryID);

        getCurrentFragment();




    }

    private void getCurrentFragment() {
        parentFragment = (ProductTabFragment) getParentFragment();

//        registeredFragment = (ProductListingFragment) parentFragment.adapterProductTabPager.getRegisteredFragment(parentFragment.viewPager.getCurrentItem());
//      registeredFragment.getProducts(subCategoryID);

        parentFragment.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                registeredFragment = (ProductListingFragment) parentFragment.adapterProductTabPager.getRegisteredFragment(parentFragment.viewPager.getCurrentItem());
                registeredFragment = (ProductListingFragment) parentFragment.adapterProductTabPager.getRegisteredFragment(position);
                if (registeredFragment != null) {
                    registeredFragment.txtPrice.setText(getFormattedNumber(prefHelper.getCart().getTotalPrice(), 3));
                    registeredFragment.txtItemInCart.setText(String.valueOf(prefHelper.getCart().getProducts().size()));

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void bindData() {
        txtPrice.setText(getFormattedNumber(prefHelper.getCart().getTotalPrice(), 2));
        txtItemInCart.setText(String.valueOf(prefHelper.getCart().getProducts().size()));

    }

//    private void dummyData() {
//        arrProducts.clear();
//        int offsetForDummyID = subCategoryID * 10;
//        if ((subCategoryID % 2) == 0) {
//            for (int i = 1; i < 8; i++) {
//                arrProducts.add(new Products((i + offsetForDummyID), "Item" + (i + offsetForDummyID), "185 GMS", Constants.CHIPS_IMAGES[i], 0.205f, 4));
//            }
//        } else {
//            for (int i = 1; i < 8; i++) {
//                arrProducts.add(new Products((i + offsetForDummyID), "Item" + (i + offsetForDummyID), "500 GMS", Constants.FRUITS_AND_VEGETABLES_IMAGES[i], 3.985f, 6));
//            }
//        }
//    }


//    private void getProducts(final int subCategoryId) {
//        if (prefHelper.isGuest()) {
//            showProgress();
//            callProductsGuest.clone()
//                    .enqueue(new Callback<WebResponse<ProductsWrapper>>() {
//                        @Override
//                        public void onResponse(Call<WebResponse<ProductsWrapper>> call, Response<WebResponse<ProductsWrapper>> responseCode) {
//                            if (responseCode == null || responseCode.content() == null) {
//                                dismissProgress();
//                                return;
//                            }
//                            if (responseCode.content().isSuccess()) {
//                                arrProducts.clear();
//                                arrProducts.addAll(responseCode.content().result.products);
//                                updateCart();
//                                adapterProductListing.notifyDataSetChanged();
//                                dismissProgress();
//                            } else {
//                                UIHelper.showToast(getContext(), responseCode.content().message);
//                                dismissProgress();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<WebResponse<ProductsWrapper>> call, Throwable t) {
//                            dismissProgress();
//                            t.printStackTrace();
//                        }
//                    });
//        } else {
//            showProgress();
//            callProductsUser.clone()
//                    .enqueue(new Callback<WebResponse<ProductsWrapper>>() {
//
//                        @Override
//                        public void onResponse(Call<WebResponse<ProductsWrapper>> call, Response<WebResponse<ProductsWrapper>> responseCode) {
//                            if (responseCode == null || responseCode.content() == null) {
//                                dismissProgress();
//                                return;
//                            }
//                            if (responseCode.content().isSuccess()) {
//                                arrProducts.clear();
//                                arrProducts.addAll(responseCode.content().result.products);
////                                setAdapter();
//                                updateCart();
//                                adapterProductListing.notifyDataSetChanged();
//                                dismissProgress();
//                            } else {
//                                UIHelper.showToast(getContext(), responseCode.content().message);
//                                dismissProgress();
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<WebResponse<ProductsWrapper>> call, Throwable t) {
//                            dismissProgress();
//                            t.printStackTrace();
//                        }
//                    });
//        }
//    }


//    @Override
//    public void onPause() {
//        super.onPause();
//        if (callProductsGuest == null || callProductsUser == null) {
//            return;
//        } else {
//            if (prefHelper.isGuest()) {
//                callProductsGuest.cancel();
//                callProductsGuest.cancel();
//                Log.d(CLASS_TAG, "Guest Products Call Canceled");
//            } else {
//                callProductsGuest.cancel();
//                Log.d(CLASS_TAG, "UserModel Products Call Canceled");
//            }
//        }
//
//    }

    @Override
    public void setTitlebar(final TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(title);
        titleBar.showBackButton(getMainActivity());


        titleBar.setRightButton3(R.drawable.imgsearch, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().addDockableFragment(SearchFragment.newInstance());
            }
        });

        titleBar.setRightButton2(R.drawable.imgsort, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().addDockableFragment(SortByFragment.newInstance());
//                UIHelper.showToast(getContext(), getString(R.string.next_build));
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
            setFavoriteCall = WebServiceFactory.getInstance(prefHelper.getUser().token).setFavorite(prefHelper.getUserID(), id);
            setFavoriteCall.enqueue(new Callback<WebResponse<Object>>() {

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
                    if (!setFavoriteCall.isCanceled())
                        t.printStackTrace();
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
        getMainActivity().addDockableFragment(ProductDetailedPageFragment.newInstance(this,
                adapterProductListing.getItem(position), position, adapterProductListing));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void getProducts(int subCategoryId) {
        getProductsCall = WebServiceFactory.getInstance(prefHelper.getUser().token).getProducts(subCategoryId
                , prefHelper.getUserID(), getMainActivity().sortBy);

        getProductsCall.enqueue(new Callback<WebResponse<ProductsWrapper>>() {
            @Override
            public void onResponse(Call<WebResponse<ProductsWrapper>> call,
                                   Response<WebResponse<ProductsWrapper>> response) {

                if (response == null || response.body() == null) {
                    return;
                }

                if (response.body().isSuccess()) {
                    arrProducts.clear();
                    arrProducts.addAll(response.body().result.products);
                    updateCart();
                    adapterProductListing.notifyDataSetChanged();
                } else {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<ProductsWrapper>> call, Throwable t) {
                if (!getProductsCall.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (getProductsCall != null) {
            getProductsCall.cancel();
        }
        if (setFavoriteCall != null) {
            setFavoriteCall.cancel();
        }
        super.onDestroy();
    }
}
