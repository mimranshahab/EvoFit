package edu.aku.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.aku.R;
import edu.aku.adapters.listadapters.InstantOrderAdapter;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.Order;
import edu.aku.models.wrappers.OrdersWrapper;
import edu.aku.models.wrappers.WebResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class InstantOrderFragment extends BaseFragment implements InstantOrderDetailFragment.OnItemRemove {


    private static final String TAG = "**";
    private ListView lvInstantOrder;
    private InstantOrderAdapter adaptInstantOrder;
    private ArrayList<Order> arrInstantOrderModel;
    private Call<WebResponse<OrdersWrapper>> getInstantOrdersCall;

    public static InstantOrderFragment newInstance() {

        Bundle args = new Bundle();

        InstantOrderFragment fragment = new InstantOrderFragment();
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
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        arrInstantOrderModel = new ArrayList<>();
        arrInstantOrderModel.clear();

        adaptInstantOrder = new InstantOrderAdapter(getActivity(), arrInstantOrderModel);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        bindViews(view);
        setAdapter();
        getInstantOrders();
    }

    private void setAdapter() {
        lvInstantOrder.setEmptyView(view.findViewById(R.id.txtEmpty));
        lvInstantOrder.setAdapter(adaptInstantOrder);
        adaptInstantOrder.notifyDataSetChanged();
    }

    private void bindViews(View view) {
        lvInstantOrder = (ListView) view.findViewById(R.id.lvSimple);
    }

    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();

        switch (v.getId()) {
            case R.id.btnCallShop:
                dialPhoneNumber(adaptInstantOrder.getItem(position).sellerPhone);
                break;

            case R.id.btnReportAnIssue:
                getMainActivity().addDockableFragment(ReportAnIssueFragment.newInstance());

                break;


            case R.id.btnOrderDetails:
                getMainActivity().addDockableFragment(OrderDetailsFragment.newInstance(adaptInstantOrder.getItem(position)));
                break;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getMainActivity().addDockableFragment(InstantOrderDetailFragment.newInstance(this, this, adaptInstantOrder.getItem(position), position));
    }


    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }


    @Override
    public void onItemRemoved(int position) {

        Log.d(TAG, "onItemRemoved: " + adaptInstantOrder.arrData.size());
        adaptInstantOrder.remove(arrInstantOrderModel.get(position));
        Log.d(TAG, "onItemRemoved: " + adaptInstantOrder.arrData.size());
        adaptInstantOrder.notifyDataSetChanged();


    }


    private void getInstantOrders() {

        getInstantOrdersCall = WebServiceFactory.getInstance(prefHelper.getUserToken()).getInstantOrders(prefHelper.getUserID());
        getInstantOrdersCall.enqueue(new Callback<WebResponse<OrdersWrapper>>() {
            @Override
            public void onResponse(Call<WebResponse<OrdersWrapper>> call, Response<WebResponse<OrdersWrapper>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    arrInstantOrderModel.clear();
                    arrInstantOrderModel.addAll(response.body().result.orders);
                    adaptInstantOrder.notifyDataSetChanged();
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<OrdersWrapper>> call, Throwable t) {
                if (!getInstantOrdersCall.isCanceled())
                    t.printStackTrace();
            }
        });
    }


//    @Override
//    public void onDestroy() {
//        if (getInstantOrdersCall != null) {
//            getInstantOrdersCall.cancel();
//        }
//        super.onDestroy();
//    }


}
