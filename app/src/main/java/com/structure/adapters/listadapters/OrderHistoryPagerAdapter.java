package com.structure.adapters.listadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.structure.constatnts.Constants;
import com.structure.fragments.CompletedOrderFragment;
import com.structure.fragments.InstantOrderFragment;
import com.structure.fragments.PendingOrderFragment;
import com.structure.fragments.ProductListingFragment;
import com.structure.model.Category;

import java.util.ArrayList;

/**
 * Created by khanhamza on 01-Mar-17.
 */

public class OrderHistoryPagerAdapter extends FragmentPagerAdapter {


    public OrderHistoryPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return InstantOrderFragment.newInstance();
        } else if (position == 1) {
            return PendingOrderFragment.newInstance();
        } else {
            return CompletedOrderFragment.newInstance();
        }
    }


    @Override
    public int getCount() {
        return Constants.ORDERS_TABS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return Constants.ORDERS_TABS[position];
    }

}
