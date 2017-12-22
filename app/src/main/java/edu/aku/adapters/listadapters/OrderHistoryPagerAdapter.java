package edu.aku.adapters.listadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import edu.aku.constatnts.Constants;
import edu.aku.fragments.CompletedOrderFragment;
import edu.aku.fragments.InstantOrderFragment;
import edu.aku.fragments.PendingOrderFragment;

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
