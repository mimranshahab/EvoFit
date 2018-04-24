package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.andreabaccega.widget.FormEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by aqsa.sarwar on 4/20/2018.
 */

public class ChangePasswordFragment extends BaseFragment {
    @BindView(R.id.edtOldPassword)
    FormEditText edtOldPassword;
    @BindView(R.id.edtNewPassword)
    FormEditText edtNewPassword;
    @BindView(R.id.edtConPassword)
    FormEditText edtConPassword;
    @BindView(R.id.btnLogin)
    AnyTextView btnLogin;
    Unbinder unbinder;

    public static ChangePasswordFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_change_password;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Change Password");
        titleBar.showBackButton(getBaseActivity());
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

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
    }
}
