package com.structure.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ctrlplusz.anytextview.AnyTextView;
import com.structure.R;
import com.structure.constatnts.WebServiceConstants;
import com.structure.fragments.abstracts.BaseFragment;
import com.structure.fragments.abstracts.GenericClickableInterface;
import com.structure.fragments.abstracts.GenericDialogFragment;
import com.structure.helperclasses.ui.helper.KeyboardHide;
import com.structure.helperclasses.ui.helper.TitleBar;
import com.structure.helperclasses.ui.helper.UIHelper;

import com.structure.managers.retrofit.WebServiceFactory;
import com.structure.model.Order;
import com.structure.model.Products;
import com.structure.model.wrappers.WebResponse;
import com.structure.model.wrappers.ProductsWrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.structure.R.id.txtOutForDeliveryTime;

/**
 * Created by shehrozmirza on 5/15/2017.
 */

public class InstantOrderDetailFragment extends BaseFragment {

    @BindView(R.id.btnClose)
    ImageView btnClose;
    @BindView(R.id.txtOrderNo)
    AnyTextView txtOrderNo;
    @BindView(R.id.imgSubmitted)
    ImageView imgSubmitted;
    @BindView(R.id.imgAssigned)
    ImageView imgAssigned;
    @BindView(R.id.imgInProgress)
    ImageView imgInProgress;
    @BindView(R.id.imgDelivery)
    ImageView imgDelivery;
    @BindView(R.id.txtEstimatedTime)
    AnyTextView txtEstimatedTime;
    @BindView(R.id.btnCallShop)
    ImageButton btnCallShop;
    @BindView(R.id.btnCancelOrder)
    ImageButton btnCancelOrder;
    @BindView(R.id.btnReportAnIssue)
    ImageButton btnReportAnIssue;
    @BindView(R.id.btnOrderDetails)
    ImageButton btnOrderDetails;
    Unbinder unbinder;
    @BindView(R.id.txtEditOrder)
    AnyTextView txtEditOrder;
    private View.OnClickListener onClickListener;
    private OnItemRemove onItemRemove;
    private int position = 0;
    View rootView;
    private Order order;
    Call<WebResponse<Object>> callCancelOrder;
    private Call<WebResponse<Object>> selectDefaultAddressCall;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_instant_detail;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
        titleBar.resetViews();
    }

    public static InstantOrderDetailFragment newInstance(View.OnClickListener onClickListener, OnItemRemove onItemRemove, Order order, int position) {
        Bundle args = new Bundle();
        InstantOrderDetailFragment fragment = new InstantOrderDetailFragment();
        fragment.onClickListener = onClickListener;
        fragment.order = order;
        fragment.position = position;
        fragment.onItemRemove = onItemRemove;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtOrderNo.setText("Order ID " + order.getOrderID());
        txtEstimatedTime.setText(order.deliveryHourString);

        setInitialViews();
        showOrderStatus(order.status);
    }

    @Override
    public void setListeners() {
        btnCallShop.setOnClickListener(onClickListener);
        btnReportAnIssue.setOnClickListener(onClickListener);
        btnOrderDetails.setOnClickListener(onClickListener);

        btnCallShop.setTag(position);
        btnReportAnIssue.setTag(position);
        btnOrderDetails.setTag(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        onClickListener.onClick(v);
    }


    @OnClick({R.id.btnClose, R.id.btnCancelOrder, R.id.txtEditOrder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClose:
                popBackStack();
                break;
            case R.id.btnCancelOrder:
                 showRemovePopup(position);
                break;

            case R.id.txtEditOrder:
                editOrder(order);
                break;

            case R.id.btnCallShop:
                dialPhoneNumber(order.sellerPhone);
                break;

        }
    }

    private void editOrder(Order order) {
        if (order.status.equals(WebServiceConstants.WS_KEY_STATUS_IN_PROGRESS) || order.status.equals(WebServiceConstants.WS_KEY_STATUS_OUT_FOR_DELIVERY) || order.status.equals(WebServiceConstants.WS_KEY_STATUS_COMPLETED)) {
            UIHelper.showToast(getContext(), getString(R.string.cant_edit_order));
        } else {
            if (order.shippedToAddress == null) {
                UIHelper.showToast(getContext(), "Address of this order doesn't exist in your account.");
            }else {
                showEditPopup(order);
            }
        }
    }

    private void showRemovePopup(final int position) {
        final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
        genericPopUp(genericDialogFragment, getString(R.string.removerOrder), getString(R.string.doYouWantToRemove), getString(R.string.yes), getString(R.string.no), new GenericClickableInterface() {
            @Override
            public void click() {
                deleteOrder(order.id, prefHelper.getUserID());
                genericDialogFragment.dismiss();


            }
        }, new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.dismiss();
            }
        });
    }


    private void showEditPopup(final Order order) {
        final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
        genericPopUp(genericDialogFragment, getString(R.string.edit_order), getString(R.string.edit_order_message), getString(R.string.proceed), getString(R.string.cancel)
                , new GenericClickableInterface() {
                    @Override
                    public void click() {
                        genericDialogFragment.dismiss();
                        selectDefaultAddress(order.addressID);
                        getMainActivity().isAddressChangedInEditMode = false;

                    }
                }, new GenericClickableInterface() {
                    @Override
                    public void click() {
                        genericDialogFragment.dismiss();
                    }
                });
    }


    interface OnItemRemove {
        void onItemRemoved(int position);
    }

    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void setInitialViews() {

        Integer btnStatusIds[] = {R.id.imgSubmitted, R.id.imgAssigned, R.id.imgInProgress, R.id.imgDelivery};
        Integer timeIds[] = {R.id.txtSubmittedTime, R.id.txtAssignedTime, R.id.txtProgressTime, R.id.txtOutForDeliveryTime};
        Integer drawableEmpty[] = {R.drawable.imgsubmitted_empty, R.drawable.imgassigned_empty, R.drawable.inprocess_two, R.drawable.imgdelivery_empty};

        ImageView btnStatus = null;
        AnyTextView txtTime = null;

        for (int i = 0; i < btnStatusIds.length; i++) {
            btnStatus = (ImageView) rootView.findViewById(btnStatusIds[i]);
            txtTime = (AnyTextView) rootView.findViewById(timeIds[i]);
            btnStatus.setImageResource(drawableEmpty[i]);
            txtTime.setVisibility(View.INVISIBLE);
        }
    }

    public void showOrderStatus(String subStatus) {

        ArrayList<String> status = new ArrayList<>();

        status.add(0, WebServiceConstants.WS_KEY_STATUS_SUBMITTED);
        status.add(1, WebServiceConstants.WS_KEY_STATUS_ASSIGNED);
        status.add(2, WebServiceConstants.WS_KEY_STATUS_IN_PROGRESS);
        status.add(3, WebServiceConstants.WS_KEY_STATUS_OUT_FOR_DELIVERY);
        status.add(4, WebServiceConstants.WS_KEY_STATUS_COMPLETED);


        Integer btnStatusIds[] = {R.id.imgSubmitted, R.id.imgAssigned, R.id.imgInProgress, R.id.imgDelivery};
        Integer timeIds[] = {R.id.txtSubmittedTime, R.id.txtAssignedTime, R.id.txtProgressTime, txtOutForDeliveryTime};
        Integer drawableFilled[] = {R.drawable.imgsubmitted_filled, R.drawable.imgassigned_filled, R.drawable.inprocess, R.drawable.imgdelivery_filled};

        ImageView btnStatus = null;
        AnyTextView txtTime = null;

        List<String> items;


        if (order.statusSchedule != null) {

            if (order.statusSchedule.contains(",")) {
                items = Arrays.asList(order.statusSchedule.split("\\s*,\\s*"));
            } else {
                items = new ArrayList<>();
                items.add(order.statusSchedule);
            }

//            Log.d("Order Schedule", order.statusSchedule);
//            Log.d("Sub Status", subStatus);

            for (int i = 0; i < status.size(); i++) {
                btnStatus = (ImageView) rootView.findViewById(btnStatusIds[i]);
                txtTime = (AnyTextView) rootView.findViewById(timeIds[i]);
                btnStatus.setImageResource(drawableFilled[i]);
                txtTime.setVisibility(View.VISIBLE);
                if (items.size() >= i)
                    txtTime.setText(setDateFormat(items.get(i)));
                if (status.get(i).equals(subStatus)) {
                    break;
                }
            }
        }
    }

    public String setDateFormat(String deliveryTime) {

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df.parse(deliveryTime);
            deliveryTime = df.format(date);

            SimpleDateFormat df2 = new SimpleDateFormat("hh:mm a");

            deliveryTime = df2.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveryTime;
    }

    private void deleteOrder(int orderId, int userID) {
        btnCancelOrder.setEnabled(false);
        callCancelOrder = WebServiceFactory.getInstance(prefHelper.getUserToken()).deleteOrder(orderId, userID);
        callCancelOrder.enqueue(new Callback<WebResponse<Object>>() {
            @Override
            public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                btnCancelOrder.setEnabled(true);
                if (response == null || response.body() == null) return;
                if (response.body().isSuccess()) {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                    onItemRemove.onItemRemoved(position);
                    popBackStack();
                } else {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                if (!callCancelOrder.isCanceled()) {
                    btnCancelOrder.setEnabled(true);
                    t.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (callCancelOrder != null) {
            callCancelOrder.cancel();
        }
        super.onDestroy();
    }


    private void selectDefaultAddress(int addressID) {

        selectDefaultAddressCall = WebServiceFactory.getInstance(prefHelper.getUser().token).selectAddress(addressID, prefHelper.getUserID());
        selectDefaultAddressCall.enqueue(new Callback<WebResponse<Object>>() {
            @Override
            public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                if (response.body().isSuccess()) {
//                    getMainActivity().isAddressChangedInEditMode = true;

                    prefHelper.removeCart();
                    ProductsWrapper productsWrapper = new ProductsWrapper();
                    productsWrapper.products = new ArrayList<Products>();
                    productsWrapper.products.clear();

                    productsWrapper.products.addAll(order.products);

                    for (int i = 0; i < productsWrapper.products.size(); i++) {
                        productsWrapper.products.get(i).itemQuantityInCart = order.items.get(i).quantity;
                    }

                    prefHelper.putCart(productsWrapper);

                    prefHelper.putEditableOrder(order);
                    prefHelper.setIsEditingOrder(true);

                    popBackStack();
                    prefHelper.putSelectedAddress(order.shippedToAddress);
                    getMainActivity().addDockableFragment(ShoppingCartFragment.newInstance());


                    UIHelper.showToast(getContext(), response.body().message);
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                if (!selectDefaultAddressCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }

}
