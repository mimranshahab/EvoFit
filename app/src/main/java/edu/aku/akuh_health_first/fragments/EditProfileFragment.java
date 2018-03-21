package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.widget.AnyEditTextView;
import edu.aku.akuh_health_first.widget.AnyTextView;


/**
 * Created by aqsa.sarwar on 1/19/2018.
 */

public class EditProfileFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.edMobileNumber)
    AnyEditTextView edMobileNumber;
    @BindView(R.id.edtLandlineNumber)
    AnyEditTextView edtLandlineNumber;
    @BindView(R.id.edtCurrentAddress)
    AnyEditTextView edtCurrentAddress;
    @BindView(R.id.edtCurrentCity)
    AnyEditTextView edtCurrentCity;
    @BindView(R.id.txtCurrentCountry)
    AnyTextView txtCurrentCountry;
    @BindView(R.id.edtPermanentAddress)
    AnyEditTextView edtPermanentAddress;
    @BindView(R.id.edtPermanentCity)
    AnyEditTextView edtPermanentCity;
    @BindView(R.id.txtPermanentCountry)
    AnyTextView txtPermanentCountry;
    @BindView(R.id.btnUpdate)
    AnyTextView btnUpdate;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_edit_profile;
    }

    public static EditProfileFragment newInstance() {

        Bundle args = new Bundle();

        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }



    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Edit profile");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
