package com.structure.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.structure.R;
import com.structure.adapters.listadapters.ProductTabPagerAdapter;
import com.structure.fragments.abstracts.BaseFragment;
import com.structure.helperclasses.ui.helper.CustomViewPager;
import com.structure.helperclasses.ui.helper.TitleBar;
import com.structure.model.Category;

import java.util.ArrayList;

/**
 * Created by khanhamza on 27-Feb-17.
 */

public class ProductTabFragment extends BaseFragment {

    private TabLayout tabLayout;
    public CustomViewPager viewPager;
    public ProductTabPagerAdapter adapterProductTabPager;
    private static final String TITLE_KEY = "TITLE_KEY";
    private static final String SUBCATEGORIES_KEY = "subcategories";
    private String title = "";
    private ArrayList<Category> arrSubCategories;
    private Gson gson = new Gson();

    public static ProductTabFragment newInstance(String title, ArrayList<Category> arrSubCategories) {

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        Gson gson = new Gson();
        args.putString(SUBCATEGORIES_KEY, gson.toJson(arrSubCategories));
        ProductTabFragment fragment = new ProductTabFragment();
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
        title = getArguments().getString(TITLE_KEY);
        arrSubCategories = new ArrayList<Category>();
        arrSubCategories.clear();
        arrSubCategories = gson.fromJson(getArguments().getString(SUBCATEGORIES_KEY), new TypeToken<ArrayList<Category>>(){}.getType());
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
        viewPager.setOffscreenPageLimit(arrSubCategories.size());
        adapterProductTabPager = new ProductTabPagerAdapter(getChildFragmentManager(), title, arrSubCategories);
        viewPager.setAdapter(adapterProductTabPager);
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
