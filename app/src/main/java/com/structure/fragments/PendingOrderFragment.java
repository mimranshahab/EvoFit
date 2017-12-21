package com.structure.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import com.structure.R;
import com.structure.adapters.listadapters.PendingOrderAdapter;
import com.structure.constatnts.WebServiceConstants;
import com.structure.fragments.abstracts.BaseFragment;
import com.structure.fragments.abstracts.GenericClickableInterface;
import com.structure.fragments.abstracts.GenericDialogFragment;
import com.structure.callbacks.SelectedFrequencyDataInterface;
import com.structure.helperclasses.ui.helper.TitleBar;
import com.structure.helperclasses.ui.helper.UIHelper;

import com.structure.managers.retrofit.WebServiceFactory;
import com.structure.model.AddOrder;
import com.structure.model.Order;
import com.structure.model.Products;
import com.structure.model.wrappers.OrdersWrapper;
import com.structure.model.wrappers.ProductsWrapper;
import com.structure.model.wrappers.WebResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class PendingOrderFragment extends BaseFragment implements SelectedFrequencyDataInterface {


    private ListView lvInstantOrder;
    private PendingOrderAdapter adaptPendingOrder;
    private ArrayList<Order> arrInstantOrderModel;
    Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    boolean isDateandTimeSelected = false;
    private String date = "";
    private boolean isOptionPopupAlreadyOpen = true;
    private Call<WebResponse<OrdersWrapper>> getPendingOrdersCall;
    private Call<WebResponse<Object>> callDeleteOrder;
    private Call<WebResponse<Object>> selectDefaultAddressCall;
    private Call<WebResponse<AddOrder>> editScheduleCall;

    public static PendingOrderFragment newInstance() {
        Bundle args = new Bundle();
        PendingOrderFragment fragment = new PendingOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_listview;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.orderHistory));
        titleBar.showBackButton(getMainActivity());
    }

//
//    public void dummyData() {
//        arrInstantOrderModel.clear();
//        arrInstantOrderModel.add(new InstantOrderModel("00001"));
//        arrInstantOrderModel.add(new InstantOrderModel("00002"));
//        arrInstantOrderModel.add(new InstantOrderModel("00003"));
//        arrInstantOrderModel.add(new InstantOrderModel("00004"));
//        arrInstantOrderModel.add(new InstantOrderModel("00005"));
//        arrInstantOrderModel.add(new InstantOrderModel("00006"));
//        arrInstantOrderModel.add(new InstantOrderModel("00007"));
//        arrInstantOrderModel.add(new InstantOrderModel("00008"));
//        arrInstantOrderModel.add(new InstantOrderModel("00009"));
//        arrInstantOrderModel.add(new InstantOrderModel("00010"));
//        arrInstantOrderModel.add(new InstantOrderModel("00011"));
//        arrInstantOrderModel.add(new InstantOrderModel("00012"));
//        arrInstantOrderModel.add(new InstantOrderModel("00013"));
//    }


    @Override
    public void setListeners() {
        lvInstantOrder.setOnItemClickListener(this);


        onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }
        };


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        arrInstantOrderModel = new ArrayList<>();
        arrInstantOrderModel.clear();

        adaptPendingOrder = new PendingOrderAdapter(getActivity(), arrInstantOrderModel, this);

        myCalendar = Calendar.getInstance();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        dummyData();
        bindViews(view);
        setAdapter();
        getPendingOrders();
    }

    private void setAdapter() {
        lvInstantOrder.setEmptyView(view.findViewById(R.id.txtEmpty));
        lvInstantOrder.setAdapter(adaptPendingOrder);

    }

    private void bindViews(View view) {
        lvInstantOrder = (ListView) view.findViewById(R.id.lvSimple);
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();


        switch (v.getId()) {
            case R.id.btnOptions:
                if (isOptionPopupAlreadyOpen) {
                    isOptionPopupAlreadyOpen = false;
                    PendingOrderOptionsFragment fragment = PendingOrderOptionsFragment.newInstance(pos, this);
                    getMainActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_frame, fragment).addToBackStack(fragment.getClass().getSimpleName()).commit();
                }
                break;

            case R.id.btnOrderCancel:
                isOptionPopupAlreadyOpen = true;
                showRemovePopup(pos, false);
                break;

            case R.id.btnOrderDetail:
                isOptionPopupAlreadyOpen = true;
                getMainActivity().addDockableFragment(OrderDetailsFragment.newInstance(adaptPendingOrder.getItem(pos)));
                break;

            case R.id.txtAddRemove:
                isOptionPopupAlreadyOpen = true;
                getMainActivity().getSupportFragmentManager().popBackStack();
                editOrder(adaptPendingOrder.getItem(pos));
//                UIHelper.showToast(getContext(), "This feature will be implemented in next build");
                break;

            case R.id.txtEditScheduling:
                isOptionPopupAlreadyOpen = true;
                getMainActivity().getSupportFragmentManager().popBackStack();
                editOrder(adaptPendingOrder.getItem(pos));
//                UIHelper.showToast(getContext(), "This feature will be implemented in next build");
                break;

            case R.id.txtClose:
                isOptionPopupAlreadyOpen = true;
                getMainActivity().getSupportFragmentManager().popBackStack();
                break;

            case R.id.txtDelete:
                isOptionPopupAlreadyOpen = true;
                showRemovePopup(pos, true);
                break;
        }
    }

    private void showRemovePopup(final int position, final Boolean isOptionPopUpOpened) {
        final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
        genericPopUp(genericDialogFragment, getString(R.string.removerOrder), getString(R.string.doYouWantToRemove), getString(R.string.yes), getString(R.string.no), new GenericClickableInterface() {
            @Override
            public void click() {
                deleteOrder(arrInstantOrderModel.get(position).id, prefHelper.getUserID(), position);
                genericDialogFragment.dismiss();
                if (isOptionPopUpOpened)
                    getMainActivity().getSupportFragmentManager().popBackStack();
            }
        }, new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.dismiss();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }


    private void showDateDilaog() {
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(getMainActivity(), onDateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }


    private void updateDateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date = sdf.format(myCalendar.getTime());

        FrequencySelectionFragment frequencySelectionFragment = FrequencySelectionFragment.newInstance(date);
        frequencySelectionFragment.setSelectedDate(this);
        getMainActivity().addDockableFragment(frequencySelectionFragment);

    }

    @Override
    public void onTimeSelectedListener(int indexTimeSlot, String selectedTime, int indexFrequency, String additionalNotes) {
        this.isDateandTimeSelected = true;
//        UIHelper.showToast(getContext(), date + "Schedule updated: " + selectedTime);
    }


    private void getPendingOrders() {

        getPendingOrdersCall = WebServiceFactory.getInstance(prefHelper.getUserToken()).getPendingOrders(prefHelper.getUserID());
        getPendingOrdersCall.enqueue(new Callback<WebResponse<OrdersWrapper>>() {
            @Override
            public void onResponse(Call<WebResponse<OrdersWrapper>> call, Response<WebResponse<OrdersWrapper>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    arrInstantOrderModel.clear();
                    arrInstantOrderModel.addAll(response.body().result.orders);
                    adaptPendingOrder.notifyDataSetChanged();
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<OrdersWrapper>> call, Throwable t) {
                if (!getPendingOrdersCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }

    private void deleteOrder(int orderId, int userID, final int position) {
        callDeleteOrder = WebServiceFactory.getInstance(prefHelper.getUserToken()).deleteOrder(orderId, userID);
        callDeleteOrder.enqueue(new Callback<WebResponse<Object>>() {
            @Override
            public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                if (response == null || response.body() == null) return;
                if (response.body().isSuccess()) {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                    arrInstantOrderModel.remove(position);
                    adaptPendingOrder.notifyDataSetChanged();
                } else {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                if (!callDeleteOrder.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (callDeleteOrder != null) {
            callDeleteOrder.cancel();
        }
        super.onDestroy();
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


    private void showEditPopup(final Order order) {
        final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
        genericPopUp(genericDialogFragment, getString(R.string.edit_order), getString(R.string.edit_order_message), getString(R.string.proceed), getString(R.string.cancel)
                , new GenericClickableInterface() {
                    @Override
                    public void click() {
                        genericDialogFragment.dismiss();
                        selectDefaultAddress(order);
                        getMainActivity().isAddressChangedInEditMode = false;

                    }
                }, new GenericClickableInterface() {
                    @Override
                    public void click() {
                        genericDialogFragment.dismiss();
                    }
                });
    }


    private void selectDefaultAddress(final Order order) {

        selectDefaultAddressCall = WebServiceFactory.getInstance(prefHelper.getUser().token).selectAddress(order.addressID, prefHelper.getUserID());
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












