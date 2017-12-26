package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.adapters.listadapters.OrderDetailslAdapter;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.DateHelper;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.ItemModel;
import edu.aku.models.Order;
import edu.aku.models.wrappers.WebResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 20-May-17.
 */

public class OrderDetailsFragment extends BaseFragment {

    @BindView(R.id.txtDate)
    AnyTextView txtDate;
    @BindView(R.id.txtOrderID)
    AnyTextView txtOrderID;
    @BindView(R.id.txtFrequency)
    AnyTextView txtFrequency;
    @BindView(R.id.txtDeliveryTime)
    AnyTextView txtDeliveryTime;
    @BindView(R.id.txtAddress)
    AnyTextView txtAddress;
    @BindView(R.id.txtAdditionalNotes)
    AnyTextView txtAdditionalNotes;
    @BindView(R.id.txtCurrencyType)
    AnyTextView txtTotal;
    @BindView(R.id.gvOrderDetail)
    GridView gvOrderDetail;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    Unbinder unbinder;
    private OrderDetailslAdapter adaptOrderDetailsFragment;
    private ArrayList<ItemModel> arrItemInOrderDetail;
    private Order order;


    public static OrderDetailsFragment newInstance(Order order) {

        Bundle args = new Bundle();

        OrderDetailsFragment fragment = new OrderDetailsFragment();
        fragment.order = order;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_order_details;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrItemInOrderDetail = new ArrayList<ItemModel>();
        arrItemInOrderDetail.clear();
        arrItemInOrderDetail.addAll(order.items);
        adaptOrderDetailsFragment = new OrderDetailslAdapter(getActivity(), arrItemInOrderDetail, this);
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.orderDetails));
        titleBar.showBackButton(getMainActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        txtDate.setText(DateHelper.getFormattedDeliveryDateAndTime(order.deliveryDate));
        txtDeliveryTime.setText(order.deliveryHourString);
        txtFrequency.setText(order.getFrequency());
        txtAdditionalNotes.setText(order.additionalNotes);
        if (order.shippedToAddress != null) {
            txtAddress.setText(order.shippedToAddress.getAddressString());
        }
        txtOrderID.setText(String.valueOf(order.getOrderID()));
        txtTotal.setText(String.valueOf(order.totalAmount));

        setAdapter();
    }

//    private void dummyData() {
//        arrItemInOrderDetail.clear();
//        for (int i = 0; i < 8; i++) {
//            arrItemInOrderDetail.add(new Products(i, "Item" + i, "500 GMS", Constants.CHIPS_IMAGES[i], 3.985f, 6));
//        }
//    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();

        switch (v.getId()) {

            case R.id.imgFav:
//                doDummyLike(position);
                setFavorite(position, adaptOrderDetailsFragment.getItem(position).product.id);
                break;
        }

    }


    private void setAdapter() {
        gvOrderDetail.setAdapter(adaptOrderDetailsFragment);
        adaptOrderDetailsFragment.notifyDataSetChanged();
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


    private void doDummyLike(int position) {
        if (arrItemInOrderDetail.get(position).product.isFavorite.equals("1")) {
            arrItemInOrderDetail.get(position).product.isFavorite = "0";
        } else {
            arrItemInOrderDetail.get(position).product.isFavorite = "1";
        }
        adaptOrderDetailsFragment.notifyDataSetChanged();
    }


    private void setFavorite(final int position, int id) {
        WebServiceFactory.getInstance("").setFavorite(prefHelper.getUserID(), id)
                .enqueue(new Callback<WebResponse<Object>>() {
                    @Override
                    public void onResponse(Call<WebResponse<Object>> call,
                                           Response<WebResponse<Object>> response) {

                        if (response == null || response.body() == null) {
                            return;
                        }
                        if (response.body().isSuccess()) {
                            if (arrItemInOrderDetail.get(position).product.isFavorite.equals("1")) {
                                arrItemInOrderDetail.get(position).product.isFavorite = "0";
                            } else {
                                arrItemInOrderDetail.get(position).product.isFavorite = "1";
                            }
                            adaptOrderDetailsFragment.notifyDataSetChanged();
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
