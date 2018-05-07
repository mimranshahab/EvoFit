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
import edu.aku.family_hifazat.adapters.BPTabAdapter;
import edu.aku.family_hifazat.adapters.MeasurementTabAdapter;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.models.PatientHealthSummaryModel;
import edu.aku.family_hifazat.widget.CustomViewPager;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by hamza.ahmed on 3/6/2018.
 */

public class BPTabLayout extends BaseFragment {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    public CustomViewPager viewpager;
    Unbinder unbinder;
    BPTabAdapter tabPagerAdapter;
    private PatientHealthSummaryModel modelBPSYSTOLIC;
    private PatientHealthSummaryModel modelBPDIASTOLIC;

    public static BPTabLayout newInstance(PatientHealthSummaryModel modelBPSYSTOLIC, PatientHealthSummaryModel modelBPDIASTOLIC) {

        Bundle args = new Bundle();

        BPTabLayout fragment = new BPTabLayout();

        fragment.modelBPSYSTOLIC = modelBPSYSTOLIC;
        fragment.modelBPDIASTOLIC = modelBPDIASTOLIC;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_tablayout_bp;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Blood Pressure");
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
        tabPagerAdapter = new BPTabAdapter(getChildFragmentManager(), modelBPDIASTOLIC, modelBPSYSTOLIC);
        viewpager.setAdapter(tabPagerAdapter);
        tabs.setupWithViewPager(viewpager);
//        tabs.setBackgroundColor(getResources().getColor(R.color.c_white));
//        tabs.setBackground(getResources().getDrawable(R.drawable.tab_color_selector_bp));
//        tabs.setTabMode(TabLayout.MODE_FIXED);
//        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.base_blue));
//        tabs.setTabTextColors(getResources().getColor(R.color.txt_aku_blue), getResources().getColor(R.color.base_blue));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
