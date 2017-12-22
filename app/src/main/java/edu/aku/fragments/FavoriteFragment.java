package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import edu.aku.R;
import edu.aku.adapters.listadapters.ProductDetailAdapter;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.model.Products;
import edu.aku.model.wrappers.ProductsWrapper;
import edu.aku.model.wrappers.WebResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends BaseFragment {

    @BindView(R.id.lvProductListing)
    ListView lvProductListing;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    Unbinder unbinder;
    private ProductDetailAdapter adapterProductDetail;
    private ArrayList<Products> arrayListProductListing = new ArrayList<Products>();
    private Call<WebResponse<ProductsWrapper>> getFavouriteCall;
    private Call<WebResponse<Object>> setFavourite;

    public static FavoriteFragment newInstance() {

        Bundle args = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_favorite_listing;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.myFavorites));
        titleBar.showBackButton(getMainActivity());
    }

    @Override
    public void setListeners() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void getFavouriteLists(int user_id) {

        getFavouriteCall = WebServiceFactory.getInstance(prefHelper.getUser().token).getFavoriteList(user_id);
        getFavouriteCall.enqueue(new Callback<WebResponse<ProductsWrapper>>() {
            @Override
            public void onResponse(Call<WebResponse<ProductsWrapper>> call,
                                   Response<WebResponse<ProductsWrapper>> response) {

                if (response == null || response.body() == null) {
                    return;
                }

                if (response.body().isSuccess()) {
                    arrayListProductListing.clear();
                    arrayListProductListing.addAll(response.body().result.products);
                    adapterProductDetail.notifyDataSetChanged();
                } else {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<ProductsWrapper>> call,
                                  Throwable t) {
                if (!getFavouriteCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFavouriteLists(prefHelper.getUserID());
        bindAdapter();
    }

    private void bindAdapter() {
        adapterProductDetail = new ProductDetailAdapter(getActivity(), arrayListProductListing, this);
        adapterProductDetail.setHasLikeButton(true);
        adapterProductDetail.setHasQuantityButtons(false);
        adapterProductDetail.setHasOutofStock(false);
        lvProductListing.setEmptyView(txtEmpty);
        lvProductListing.setAdapter(adapterProductDetail);
    }


    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();

        switch (v.getId()) {

            case R.id.btnIncrement: {

                if (adapterProductDetail.getItem(position).getItemQuantityInCart() >= adapterProductDetail.getItem(position).quantity) {
                    UIHelper.showToast(getContext(), "Items in stock: " + adapterProductDetail.getItem(position).quantity);
                } else {
                    adapterProductDetail.getItem(position).setItemQuantityInCart(adapterProductDetail.getItem(position).getItemQuantityInCart() + 1);
                    adapterProductDetail.notifyDataSetChanged();
                }
                break;
            }
            case R.id.btnDecrement: {
                if ((adapterProductDetail.getItem(position).getItemQuantityInCart() <= 0)) {
                } else {
                    adapterProductDetail.getItem(position).setItemQuantityInCart(adapterProductDetail.getItem(position).getItemQuantityInCart() - 1);
                    adapterProductDetail.notifyDataSetChanged();
                }
                adapterProductDetail.notifyDataSetChanged();
                break;
            }
            case R.id.imgItemLike:

                setFavorite(position, arrayListProductListing.get(position).id);
                break;
        }
    }

    private void setFavorite(final int position, int id) {
        showProgress();
        WebServiceFactory.getInstance("").setFavorite(prefHelper.getUserID(), id)
                .enqueue(new Callback<WebResponse<Object>>() {
                    @Override
                    public void onResponse(Call<WebResponse<Object>> call,
                                           Response<WebResponse<Object>> response) {

                        if (response == null || response.body() == null) {
                            dismissProgress();
                            return;
                        }
                        if (response.body().isSuccess()) {
                            dismissProgress();
                            if (adapterProductDetail.getCount() > position) {
                                if (adapterProductDetail.getItem(position).isFavorite.equals("1")) {
                                    adapterProductDetail.remove(adapterProductDetail.getItem(position));
                                } else {
                                    adapterProductDetail.getItem(position).isFavorite = "1";
                                }
                                adapterProductDetail.notifyDataSetChanged();
                            } else {
                                dismissProgress();
                                UIHelper.showToast(getContext(), response.body().message);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                        dismissProgress();
                        if (!setFavourite.isCanceled()) {
                            t.printStackTrace();
                        }
                    }
                });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        if (getFavouriteCall != null) {
            getFavouriteCall.cancel();
        }
        if (setFavourite != null) {
            setFavourite.cancel();
        }
        super.onDestroy();
    }
}
