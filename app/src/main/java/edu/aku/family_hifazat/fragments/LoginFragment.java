package edu.aku.family_hifazat.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.andreabaccega.widget.FormEditText;

import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.widget.AnyTextView;

import com.google.gson.JsonObject;

import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.activities.HomeActivity;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.validator.CardNumberValidation;
import edu.aku.family_hifazat.helperclasses.validator.PasswordValidation;
import edu.aku.family_hifazat.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.libraries.maskformatter.MaskFormatter;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.sending_model.LoginApiModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;

import static edu.aku.family_hifazat.constatnts.AppConstants.CARD_MASK;
import static edu.aku.family_hifazat.constatnts.AppConstants.KEY_CARD_NUMBER;
import static edu.aku.family_hifazat.constatnts.AppConstants.KEY_TOKEN;

/**
 * Created by khanhamza on 08-May-17.
 */

public class LoginFragment extends BaseFragment {

    @BindView(R.id.edtUserName)
    FormEditText edtCardNumber;
    @BindView(R.id.edtPassword)
    FormEditText edtPassword;
    @BindView(R.id.txtForgotPassword)
    AnyTextView txtForgotPassword;
    @BindView(R.id.btnLogin)
    AnyTextView btnLogin;
    @BindView(R.id.txtSignUp)
    AnyTextView txtSignUp;
    Unbinder unbinder;


    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.GONE);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtPassword.addValidator(new PasswordValidation());
        edtCardNumber.addValidator(new CardNumberValidation());
        edtCardNumber.addTextChangedListener(new MaskFormatter(CARD_MASK, edtCardNumber, '-'));

        Log.d(TAG, "onViewCreated: UNIQUE ID:  " +  AppConstants.getDeviceID(getContext()));
        Log.d(TAG, "onViewCreated: UNIQUE ID:  " +  AppConstants.getDeviceID(getContext()));

        Log.d(TAG, "onViewCreated: UNIQUE ID:  " +  AppConstants.getDeviceID(getContext()));


    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtForgotPassword, R.id.btnLogin, R.id.txtSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtForgotPassword:
                getBaseActivity().addDockableFragment(ForgotPassowrdFragment.newInstance(), false);
                break;
            case R.id.btnLogin:
                 if (edtCardNumber.testValidity() && edtPassword.testValidity()) {
                    LoginApiModel loginApiModel = new LoginApiModel(edtCardNumber.getText().toString(), edtPassword.getText().toString());
                    loginApiModel.setLoginDeviceType(AppConstants.DEVICE_TYPE);
                    loginApiModel.setLoginDeviceID(Build.VERSION.RELEASE);
                    loginCall(loginApiModel);
                }
                break;

            case R.id.txtSignUp:
                getBaseActivity().addDockableFragment(RegisterFragment.newInstance(), false);
        }
    }

    private void loginCall(LoginApiModel loginApiModel) {
        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_USER_LOGIN,
                        loginApiModel.toString(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                String token = webResponse.result.get("_token").getAsString();
                                String cardNumber = webResponse.result.get("CardNumber").getAsString();
                                sharedPreferenceManager.putValue(KEY_TOKEN, token);
                                sharedPreferenceManager.putValue(KEY_CARD_NUMBER, cardNumber);
                                getBaseActivity().openActivity(HomeActivity.class);
                                getBaseActivity().finish();
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }

}
