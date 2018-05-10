package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.fragments.abstracts.GenericDialogFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by hamza.ahmed on 5/9/2018.
 */

public class SettingsFragment extends BaseFragment {
    @BindView(R.id.txtPinCode)
    AnyTextView txtPinCode;
    @BindView(R.id.switchPinCode)
    Switch switchPinCode;
    @BindView(R.id.txtChangePassword)
    AnyTextView txtChangePassword;
    Unbinder unbinder;


    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_preferences;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Settings");
        titleBar.showBackButton(getBaseActivity());
        titleBar.showHome(getBaseActivity());
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switchPinCode.setChecked(sharedPreferenceManager.getBoolean(AppConstants.KEY_IS_PIN_ENABLE));

    }

    @Override
    public void onResume() {
        super.onResume();
        switchPinCode.setChecked(sharedPreferenceManager.getBoolean(AppConstants.KEY_IS_PIN_ENABLE));

        switchPinCode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    getBaseActivity().addDockableFragment(EnterNewPinFragment.newInstance(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    }), false);
                } else {
                    sharedPreferenceManager.putValue(AppConstants.KEY_IS_PIN_ENABLE, false);
                }
            }
        });
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtPinCode, R.id.txtChangePassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtPinCode:
                switchPinCode.performClick();
                break;
            case R.id.txtChangePassword:

                forgotPasswordPopup();
                break;
        }
    }

    private void forgotPasswordPopup() {
        final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
        UIHelper.genericPopUp(getBaseActivity(), genericDialogFragment, "Change Password",
                "Are you sure you want to reset your password?\n" +
                        "A verification code will be sent to the subscriber’s email address, if you proceed.", "Proceed", "No",
                () -> {
                    genericDialogFragment.dismiss();
                    getBaseActivity().addDockableFragment(ForgotPassowrdFragment.newInstance(true), false);

                }, genericDialogFragment::dismiss
        );
    }

}
