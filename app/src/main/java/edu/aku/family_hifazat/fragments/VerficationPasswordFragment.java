package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.PinEntryEditText;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by aqsa.sarwar on 4/23/2018.
 */

public class VerficationPasswordFragment extends BaseFragment {

    @BindView(R.id.txtPinForgotPass)
    PinEntryEditText txtPinForgotPass;
    @BindView(R.id.btnSumbit)
    AnyTextView btnSumbit;
    @BindView(R.id.llVerificationCode)
    LinearLayout llVerificationCode;
    Unbinder unbinder;
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;

    public static VerficationPasswordFragment newInstance() {

        Bundle args = new Bundle();

        VerficationPasswordFragment fragment = new VerficationPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_verification;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Verification Code");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtPinForgotPass.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
//                if (str.toString().equals("1234")) {
//                    Toast.makeText(getMainActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
//                } else {
//                    txtPinForgotPass.setError(true);
//                    Toast.makeText(getMainActivity(), "FAIL", Toast.LENGTH_SHORT).show();
//                    txtPinForgotPass.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
////                            txtPinForgotPass.setText(null);
//                        }
//                    }, 1000);
//                }
                txtTitle.setText("Pass Code Verification");
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


    @OnClick({R.id.btnBack, R.id.btnSumbit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getBaseActivity().onBackPressed();
                break;
            case R.id.btnSumbit:
//                getBaseActivity().addDockableFragment(ChangePasswordFragment.newInstance(ed), false);
                break;
        }
    }
}
