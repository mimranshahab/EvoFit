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
import edu.aku.family_hifazat.adapters.GlucoseTabAdapter;
import edu.aku.family_hifazat.adapters.MeasurementTabAdapter;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.models.PatientHealthSummaryModel;
import edu.aku.family_hifazat.widget.CustomViewPager;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by hamza.ahmed on 3/6/2018.
 */

public class GlucoseTabLayout extends BaseFragment {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    public CustomViewPager viewpager;
    Unbinder unbinder;
    GlucoseTabAdapter tabPagerAdapter;
    private PatientHealthSummaryModel modelGLUF;
    private PatientHealthSummaryModel modelGLUR;

    public static GlucoseTabLayout newInstance(PatientHealthSummaryModel modelGLUF, PatientHealthSummaryModel modelGLUR) {

        Bundle args = new Bundle();

        GlucoseTabLayout fragment = new GlucoseTabLayout();
        fragment.modelGLUF = modelGLUF;
        fragment.modelGLUR = modelGLUR;

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_tablayout_glucose;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Blood Glucose");
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
        tabPagerAdapter = new GlucoseTabAdapter(getChildFragmentManager(), modelGLUF, modelGLUR);
        viewpager.setAdapter(tabPagerAdapter);
        tabs.setupWithViewPager(viewpager);

//         tabs.setBackground(getResources().getDrawable(R.drawable.tab_color_selector_gluco));
//        tabs.setTabMode(TabLayout.MODE_FIXED);
//        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.base_reddish));
//        tabs.setTabTextColors(getResources().getColor(R.color.txt_aku_blue), getResources().getColor(R.color.c_white));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
