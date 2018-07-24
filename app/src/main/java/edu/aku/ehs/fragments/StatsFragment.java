package edu.aku.ehs.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import edu.aku.ehs.R;
import edu.aku.ehs.fragments.abstracts.BaseFragment;
import edu.aku.ehs.widget.TitleBar;

/**
 * Created by hamza.ahmed on 7/19/2018.
 */

public class StatsFragment extends BaseFragment {


    public static StatsFragment newInstance() {

        Bundle args = new Bundle();

        StatsFragment fragment = new StatsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getDrawerLockMode() {
        return 0;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_statistics;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Stats");
        titleBar.showHome(getBaseActivity());
        titleBar.showBackButton(getBaseActivity());
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
}
