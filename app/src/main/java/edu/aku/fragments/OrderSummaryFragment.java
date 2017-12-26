package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.DateHelper;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.Order;
import edu.aku.models.wrappers.WebResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSummaryFragment extends BaseFragment {


    @BindView(R.id.txtThankyou)
    AnyTextView txtThankyou;
    @BindView(R.id.txtorder)
    AnyTextView txtorder;
    @BindView(R.id.btnOrderHistory)
    AnyTextView btnOrderHistory;
    @BindView(R.id.btnContinueShopping)
    AnyTextView btnContinueShopping;
    Unbinder unbinder;
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.txtOrderId)
    AnyTextView txtOrderId;
    @BindView(R.id.txtOrderDate)
    AnyTextView txtOrderDate;
    @BindView(R.id.txtDeliveryTime)
    AnyTextView txtDeliveryTime;
    @BindView(R.id.txtPayment)
    AnyTextView txtPayment;
    @BindView(R.id.txtFrequency)
    AnyTextView txtFrequency;
    @BindView(R.id.txtAdditionalNotes)
    AnyTextView txtAdditionalNotes;
    @BindView(R.id.txtAddress)
    AnyTextView txtAddress;
    @BindView(R.id.txtCurrencyType)
    AnyTextView txtCurrencyType;
    @BindView(R.id.txtTotalPrice)
    AnyTextView txtTotalPrice;

    private int orderID;
    private Call<WebResponse<Order>> getOrderDetailsCall;
    private Order order;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_order_summary;
    }


    public static OrderSummaryFragment newInstance(String date, int orderID) {
        Bundle args = new Bundle();
        OrderSummaryFragment fragment = new OrderSummaryFragment();
        fragment.orderID = orderID;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.GONE);
    }

    @Override
    public void setListeners() {


    }

    @Override
    public void onClick(View v) {

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTitle.setText(getString(R.string.order_summary));
        getOrderDetail(orderID);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());

    }

    @OnClick({R.id.btnOrderHistory, R.id.btnContinueShopping, R.id.btnBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnOrderHistory:
                popBackStack();
                getMainActivity().addDockableFragment(OrderHistoryTabFragment.newInstance());
                break;
            case R.id.btnContinueShopping:
                emptyBackStack();
                getMainActivity().addDockableFragment(HomeFragment.newInstance());
                break;

            case R.id.btnBack:
                popBackStack();
        }
    }


    private void getOrderDetail(final int orderID) {

        getOrderDetailsCall = WebServiceFactory.getInstance(prefHelper.getUserToken()).getOrderDetails(orderID);
        getOrderDetailsCall.enqueue(new Callback<WebResponse<Order>>() {
            @Override
            public void onResponse(Call<WebResponse<Order>> call, Response<WebResponse<Order>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    order = response.body().result;
                    txtOrderId.setText(String.valueOf(orderID));
                    txtOrderDate.setText(DateHelper.getFormattedDeliveryDateAndTime(order.deliveryDate));
                    txtDeliveryTime.setText(order.deliveryHourString);
                    txtFrequency.setText(order.getFrequency());
                    txtAdditionalNotes.setText(order.additionalNotes);
                    if (order.shippedToAddress != null){
                        txtAddress.setText(order.shippedToAddress.getAddressString());
                    }
                    txtTotalPrice.setText(String.valueOf(order.totalAmount));

                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Order>> call, Throwable t) {
                if (!getOrderDetailsCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }


    @Override
    public void onDestroy() {
        if (getOrderDetailsCall != null) {
            getOrderDetailsCall.cancel();
        }
        super.onDestroy();
    }
}
