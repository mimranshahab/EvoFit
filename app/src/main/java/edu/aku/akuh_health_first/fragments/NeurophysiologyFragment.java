package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class NeurophysiologyFragment extends BaseFragment {

    public static NeurophysiologyFragment newInstance() {

        Bundle args = new Bundle();

        NeurophysiologyFragment fragment = new NeurophysiologyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_generic_content;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Neurophysiology");
        titleBar.showBackButton(getBaseActivity());
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
