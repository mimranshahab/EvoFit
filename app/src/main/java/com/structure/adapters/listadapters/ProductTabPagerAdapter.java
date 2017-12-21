package com.structure.adapters.listadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.structure.fragments.ProductListingFragment;
import com.structure.model.Category;

import java.util.ArrayList;

/**
 * Created by khanhamza on 01-Mar-17.
 */

public class ProductTabPagerAdapter extends FragmentStatePagerAdapter {


    private String title = "";
    private ArrayList<Category> arrSubCategories;

    public ProductTabPagerAdapter(android.support.v4.app.FragmentManager fm, String title, ArrayList<Category> arrSubCategories) {
        super(fm);
        this.title = title;
        this.arrSubCategories = arrSubCategories;
    }

    // CURRENT FRAGMENT

    SparseArray<Fragment> registeredFragments = new SparseArray<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        registeredFragments.remove(position);
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }


    @Override
    public Fragment getItem(int position) {
//        return ForgotPasswordFragment.newInstance();
        return  ProductListingFragment.newInstance(arrSubCategories.get(position).id, title);

    }


    @Override
    public int getCount() {
        return arrSubCategories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return arrSubCategories.get(position).categoryName;
    }
}
