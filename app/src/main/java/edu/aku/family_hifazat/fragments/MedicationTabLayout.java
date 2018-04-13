package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.adapters.MedicationProfileTabPagerAdapter;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.CustomViewPager;
import edu.aku.family_hifazat.helperclasses.ui.helper.TitleBar;

/**
 * Created by hamza.ahmed on 3/6/2018.
 */

public class MedicationTabLayout extends BaseFragment {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    public CustomViewPager viewpager;
    Unbinder unbinder;
    MedicationProfileTabPagerAdapter tabPagerAdapter;
    private boolean isFromTimeline;
    private int patientVisitAdmissionID;

    public static MedicationTabLayout newInstance(boolean isFromTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();

        MedicationTabLayout fragment = new MedicationTabLayout();
        fragment.isFromTimeline = isFromTimeline;
        fragment.patientVisitAdmissionID = patientVisitAdmissionID;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_tablayout;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Medicines");
        titleBar.showHome(getBaseActivity());
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
        setViewPagerAdapter();
    }

    private void setViewPagerAdapter() {
        viewpager.setPagingEnabled(true);
        viewpager.setOffscreenPageLimit(1);
        tabPagerAdapter = new MedicationProfileTabPagerAdapter(getChildFragmentManager(), isFromTimeline, patientVisitAdmissionID);
        viewpager.setAdapter(tabPagerAdapter);
        tabs.setupWithViewPager(viewpager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
