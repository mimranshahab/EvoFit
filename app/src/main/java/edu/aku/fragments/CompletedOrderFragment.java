package edu.aku.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import edu.aku.R;
import edu.aku.adapters.listadapters.PendingOrderAdapter;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.callbacks.SelectedFrequencyDataInterface;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.Order;
import edu.aku.models.wrappers.OrdersWrapper;
import edu.aku.models.wrappers.WebResponse;

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

public class CompletedOrderFragment extends BaseFragment implements SelectedFrequencyDataInterface {


    private ListView lvInstantOrder;
    private PendingOrderAdapter adaptCompletedOrder;
    private ArrayList<Order> arrCompletedOrderModel;
    Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    boolean isDateandTimeSelected = false;
    private String date = "";
    private Call<WebResponse<OrdersWrapper>> getCompletedOrdersCall;
    private Call<WebResponse<Object>> callDeleteOrder;

    public static CompletedOrderFragment newInstance() {

        Bundle args = new Bundle();

        CompletedOrderFragment fragment = new CompletedOrderFragment();
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


//    public void dummyData() {
//        arrCompletedOrderModel.clear();
//        arrCompletedOrderModel.add(new InstantOrderModel("00001"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00002"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00003"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00004"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00005"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00006"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00007"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00008"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00009"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00010"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00011"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00012"));
//        arrCompletedOrderModel.add(new InstantOrderModel("00013"));
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


        arrCompletedOrderModel = new ArrayList<>();
        arrCompletedOrderModel.clear();

        adaptCompletedOrder = new PendingOrderAdapter(getActivity(), arrCompletedOrderModel, this);

        myCalendar = Calendar.getInstance();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bindViews(view);
        setAdapter();
        setGetCompletedOrdersCall();
    }

    private void setAdapter() {
        adaptCompletedOrder.setHasOptionButton(false);
        lvInstantOrder.setEmptyView(view.findViewById(R.id.txtEmpty));
        lvInstantOrder.setAdapter(adaptCompletedOrder);
        adaptCompletedOrder.notifyDataSetChanged();
    }

    private void bindViews(View view) {
        lvInstantOrder = (ListView) view.findViewById(R.id.lvSimple);
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();

        PendingOrderOptionsFragment fragment = PendingOrderOptionsFragment.newInstance(pos, this);


        switch (v.getId()) {
            case R.id.btnOptions:
                getMainActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_frame, fragment).addToBackStack(fragment.getClass().getSimpleName()).commit();
                break;

            case R.id.btnOrderCancel:
                showRemovePopup(pos);
                break;

            case R.id.btnOrderDetail:
                getMainActivity().addDockableFragment(OrderDetailsFragment.newInstance(adaptCompletedOrder.getItem(pos)));
                break;

//
//            case R.id.txtAddRemove:
//                UIHelper.showToast(getContext(), "This orderVariable has been completed.");
//                break;
//
//            case R.id.txtEditScheduling:
//                UIHelper.showToast(getContext(), "This orderVariable has been completed.");
//                break;
//
//            case R.id.txtClose:
//                getMainActivity().getSupportFragmentManager().popBackStack();
//                break;
//
//            case R.id.txtDelete:
//                getMainActivity().getSupportFragmentManager().popBackStack();
//                showRemovePopup(pos);
//                break;
        }
    }

    private void showRemovePopup(final int position) {
        final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
        genericPopUp(genericDialogFragment, getString(R.string.removerOrder), getString(R.string.doYouWantToRemove), getString(R.string.yes), getString(R.string.no), new GenericClickableInterface() {
            @Override
            public void click() {
                deleteOrder(arrCompletedOrderModel.get(position).id, prefHelper.getUserID(), position);
                genericDialogFragment.dismiss();

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


    private void setGetCompletedOrdersCall() {

        getCompletedOrdersCall = WebServiceFactory.getInstance(prefHelper.getUserToken()).getCompletedOrders(prefHelper.getUserID());
        getCompletedOrdersCall.enqueue(new Callback<WebResponse<OrdersWrapper>>() {
            @Override
            public void onResponse(Call<WebResponse<OrdersWrapper>> call, Response<WebResponse<OrdersWrapper>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    arrCompletedOrderModel.clear();
                    arrCompletedOrderModel.addAll(response.body().result.orders);
                    adaptCompletedOrder.notifyDataSetChanged();
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<OrdersWrapper>> call, Throwable t) {
                if (!getCompletedOrdersCall.isCanceled())
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
                    arrCompletedOrderModel.remove(position);
                    adaptCompletedOrder.notifyDataSetChanged();
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
}
