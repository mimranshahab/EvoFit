package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;

/**
 * Created by aqsa.sarwar on 1/26/2018.
 */

public class HomeDetailFragment extends BaseFragment {
    @BindView(R.id.contSummary)
    LinearLayout contSummary;
    @BindView(R.id.contHistory)
    LinearLayout contHistory;
    @BindView(R.id.contProfile)
    LinearLayout contProfile;
    @BindView(R.id.contTimeline)
    LinearLayout contTimeline;
    Unbinder unbinder;

    public static HomeDetailFragment newInstance() {

        Bundle args = new Bundle();

        HomeDetailFragment fragment = new HomeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home_detail;
    }

    @Override
    public void setTitlebar(final TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Detail");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
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

    @OnClick({R.id.contSummary, R.id.contHistory, R.id.contProfile, R.id.contTimeline})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contSummary:
                getBaseActivity().addDockableFragment(HealthSummaryFragment.newInstance());
                break;
            case R.id.contHistory:
                getBaseActivity().addDockableFragment(HealthHistoryFragment.newInstance(false, -1));
                break;
            case R.id.contProfile:
                getBaseActivity().addDockableFragment(ProfileFragment.newInstance());
                break;
            case R.id.contTimeline:
                getBaseActivity().addDockableFragment(TimelineFragment.newInstance());
                break;
        }
    }
}
