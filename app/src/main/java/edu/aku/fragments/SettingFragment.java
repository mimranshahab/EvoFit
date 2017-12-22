package edu.aku.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by shehrozmirza on 5/10/2017.
 */

public class SettingFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.toggleBtn)
    ToggleButton toggleBtn;
    @BindView(R.id.contNotifications)
    LinearLayout contNotifications;
    @BindView(R.id.contChangePassword)
    LinearLayout contChangePassword;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_setting;
    }

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.settings));
        titleBar.showBackButton(getMainActivity());
        titleBar.setRightButton(R.drawable.imgtick, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  UIHelper.showToast(getMainActivity(), "Clicked");

            }

        });
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

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());

    }



    @OnClick({R.id.contNotifications, R.id.contChangePassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contNotifications:

                toggleBtn.toggle();
               // toggleBtn.setChecked(true);

                if(toggleBtn.isChecked())
                {
                    UIHelper.showToast(getMainActivity(), "Push Notifications on");
                    toggleBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.imgtoggle_on));
                    toggleBtn.setChecked(true);
                }
                else {
                    toggleBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.imgtoggle_off));
                           UIHelper.showToast(getMainActivity(), "Push Notifications off");
                    toggleBtn.setChecked(false);
                }

                break;
            case R.id.contChangePassword:
                getMainActivity().addDockableFragment(ChangePasswordFragment.newInstance());
                break;
        }
    }
}
