package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class ForgotPassowrdFragment extends BaseFragment {
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_forgot_password;
    }

    public static ForgotPassowrdFragment newInstance() {

        Bundle args = new Bundle();

        ForgotPassowrdFragment fragment = new ForgotPassowrdFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Forgot Password");

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
