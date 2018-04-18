package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.adapters.recyleradapters.NotificationsAdapter;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.TitleBar;
import edu.aku.family_hifazat.models.NotificationModel;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class NotificationFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.recylerView)
    RecyclerView recyclerTimeline;

    Unbinder unbinder;
    private ArrayList<NotificationModel> arrNotifications;
    private NotificationsAdapter notificationsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrNotifications = new ArrayList<NotificationModel>();
        notificationsAdapter = new NotificationsAdapter(getBaseActivity(), arrNotifications, this);

        NotificationModel notificationModel = new NotificationModel();


        arrNotifications.add(notificationModel);
        arrNotifications.add(notificationModel);
        arrNotifications.add(notificationModel);
        arrNotifications.add(notificationModel);
        arrNotifications.add(notificationModel);
        arrNotifications.add(notificationModel);
        arrNotifications.add(notificationModel);
    }

    public static NotificationFragment newInstance() {

        Bundle args = new Bundle();

        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_general_recyler_view;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Notifications");
        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        if (onCreated) {
            return;
        }
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerTimeline.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerTimeline.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerTimeline.setLayoutAnimation(animation);
        recyclerTimeline.setAdapter(notificationsAdapter);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(int position, Object object) {

    }

}
