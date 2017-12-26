package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.aku.R;
import edu.aku.adapters.listadapters.NotificationAdapter;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.dialogs.RatingDialogFragment;
import edu.aku.callbacks.RatingBarDataInterface;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.NotificationModel;
import edu.aku.models.wrappers.NotificationWrapper;
import edu.aku.models.wrappers.WebResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class NotificationFragment extends BaseFragment implements RatingBarDataInterface {


    private ListView listViewNotification;
    private NotificationAdapter adapterNotification;
    private ArrayList<NotificationModel> arrNotification;
    private RatingDialogFragment ratingDialogFragment;
    private Call<WebResponse<Object>> callpostRating;
    public float ratingProductAccuracy = 0f, ratingProductDeliverySpeed = 0f;
    private Call<WebResponse<NotificationWrapper>> callNotifications;

    public static NotificationFragment newInstance() {

        Bundle args = new Bundle();

        NotificationFragment fragment = new NotificationFragment();
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
        titleBar.setTitle(getString(R.string.notifications));
        titleBar.showBackButton(getMainActivity());
    }


//    public void dummyData() {
//        arrNotification.clear();
//        arrNotification.add(new NotificationModel(0, "is picked"));
//        arrNotification.add(new NotificationModel(1, "is on way"));
//        arrNotification.add(new NotificationModel(2, "is assigned"));
//        arrNotification.add(new NotificationModel(3, "is in progress"));
//        arrNotification.add(new NotificationModel(4, "is delivered, please give feedback"));
//    }


    public void setNotificationsArray(ArrayList<NotificationModel> arrNotification) {
        this.arrNotification = arrNotification;
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrNotification = new ArrayList<>();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bindViews(view);
        bindData();
        getNotificationsList();
    }

    private void bindData() {
        adapterNotification = new NotificationAdapter(getActivity(), arrNotification, this);
        listViewNotification.setEmptyView(view.findViewById(R.id.txtEmpty));
        listViewNotification.setAdapter(adapterNotification);
    }

    private void getNotificationsList() {
        callNotifications = WebServiceFactory.getInstance(prefHelper.getUserToken()).getNotifications(prefHelper.getUserID());
        callNotifications.enqueue(new Callback<WebResponse<NotificationWrapper>>() {
            @Override
            public void onResponse(Call<WebResponse<NotificationWrapper>> call, Response<WebResponse<NotificationWrapper>> response) {
                if (response == null || response.body() == null) return;

                if (response.body().isSuccess()) {
                    arrNotification.clear();
                    arrNotification.addAll(response.body().result.notifications);
                    adapterNotification.notifyDataSetChanged();
                } else {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<NotificationWrapper>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    private void bindViews(View view) {
        listViewNotification = (ListView) view.findViewById(R.id.lvSimple);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btnGiveFeedback:
                ratingDialogFragment = RatingDialogFragment.newInstance(this);
                ratingDialogFragment.setRatingBarDataInterface(this);
                ratingDialogFragment.show(getFragmentManager(), "feedback");
                getNotificationsList();
                break;

            case R.id.btnYes:
                getRatings();
                ratingDialogFragment.dismiss();
                break;

            case R.id.btnNo:
                ratingDialogFragment.dismiss();
                break;
        }
    }

    private void getRatings() {

        callpostRating = WebServiceFactory.getInstance(
                prefHelper.getUserToken()).postRatings(prefHelper.getUserID(),
                ratingProductDeliverySpeed,
                ratingProductAccuracy);

        callpostRating.enqueue(new Callback<WebResponse<Object>>() {
            @Override
            public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                UIHelper.showToast(getMainActivity(), response.body().message);
            }

            @Override
            public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }


    private void clearNotification() {
        WebServiceFactory.getInstance(prefHelper.getUser().token).clearNotification(prefHelper.getUserID())
                .enqueue(new Callback<WebResponse<Object>>() {
                    @Override
                    public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                        if (response.body().isSuccess()) {
                            arrNotification.clear();
                            adapterNotification.notifyDataSetChanged();
                            UIHelper.showToast(getContext(), response.body().message);
                        } else {
                            UIHelper.showToast(getContext(), response.body().message);
                        }
                    }

                    @Override
                    public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                        if (!callpostRating.isCanceled())
                            t.printStackTrace();
                    }
                });
    }

    @Override
    public void onDestroy() {
        if (callpostRating != null) {
            callpostRating.cancel();
        }
        super.onDestroy();
    }

    @Override
    public void getRatings(float ratingValue1, float ratingValue2) {
        ratingProductDeliverySpeed = ratingValue1;
        ratingProductAccuracy = ratingValue2;
    }
}
