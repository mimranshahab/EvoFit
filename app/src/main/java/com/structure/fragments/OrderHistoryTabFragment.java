package com.structure.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;

import com.structure.R;
import com.structure.adapters.listadapters.OrderHistoryPagerAdapter;
import com.structure.constatnts.Constants;
import com.structure.fragments.abstracts.BaseFragment;
import com.structure.helperclasses.ui.helper.CustomViewPager;
import com.structure.helperclasses.ui.helper.TitleBar;

/**
 * Created by khanhamza on 27-Feb-17.
 */

public class OrderHistoryTabFragment extends BaseFragment {
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private OrderHistoryPagerAdapter orderHistoryPagerAdapter;

    public static OrderHistoryTabFragment newInstance() {

        Bundle args = new Bundle();
        OrderHistoryTabFragment fragment = new OrderHistoryTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_tablayout;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bindViews(view);
        setListeners();
        setViewPagerAdapter();

    }

    private void setViewPagerAdapter() {
        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(Constants.ORDERS_TABS.length);
        orderHistoryPagerAdapter = new OrderHistoryPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(orderHistoryPagerAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void bindViews(View view) {
        viewPager = (CustomViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
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
}
