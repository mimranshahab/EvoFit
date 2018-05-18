package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.andreabaccega.widget.FormEditText;

import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.fragments.abstracts.GenericDialogFragment;
import edu.aku.family_hifazat.helperclasses.GooglePlaceHelper;
import edu.aku.family_hifazat.helperclasses.ui.helper.KeyboardHelper;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.libraries.countrypicker.Country;
import edu.aku.family_hifazat.models.sending_model.RegisteredDeviceModel;
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

import static edu.aku.family_hifazat.constatnts.AppConstants.ACCESS_LOGIN_DONE;
import static edu.aku.family_hifazat.constatnts.AppConstants.CARD_MASK;
import static edu.aku.family_hifazat.constatnts.AppConstants.KEY_CARD_NUMBER;
import static edu.aku.family_hifazat.constatnts.AppConstants.KEY_CODE;
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
    private GooglePlaceHelper.GoogleAddressModel currentLocation;


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

        currentLocation = GooglePlaceHelper.getCurrentLocation(getContext(), true);

//        if (currentLocation != null) {
//            UIHelper.showToast(getContext(), currentLocation.getAddress());
//        }
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    public void onResume() {
        super.onResume();

        KeyboardHelper.showSoftKeyboard(getContext(), edtCardNumber);
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
                forgotPasswordPopup();

                break;
            case R.id.btnLogin:

                if (edtCardNumber.testValidity() && edtPassword.testValidity()) {
                    LoginApiModel loginApiModel = new LoginApiModel(edtCardNumber.getText().toString(), edtPassword.getText().toString());
                    RegisteredDeviceModel registeredDevice = AppConstants.getRegisteredDevice(getContext(), getBaseActivity());

                    if (currentLocation == null) {
                        currentLocation = GooglePlaceHelper.getCurrentLocation(getContext(), false);
                        if (currentLocation == null) {
                            registeredDevice.setDeviceLocation("");
                        } else {
                            registeredDevice.setDeviceLocation(currentLocation.getCountry());
                        }
                    } else {
                        registeredDevice.setDeviceLocation(currentLocation.getCountry());
                    }


                    registeredDevice.setRegcardno(loginApiModel.getCardNumber());
                    loginApiModel.setAccessLog(registeredDevice);
                    loginApiModel.setDevice(registeredDevice);
                    loginApiModel.setRegisteredDevice(registeredDevice);


                    loginCall(loginApiModel);
                }
                break;

            case R.id.txtSignUp:
                getBaseActivity().addDockableFragment(RegisterFragment.newInstance(), false);
        }
    }

    private void forgotPasswordPopup() {
        final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
        UIHelper.genericPopUp(getBaseActivity(), genericDialogFragment, "Forgot Password",
                "Are you sure you want to reset your password?\n" +
                        "A verification code will be sent to the subscriber’s email address, if you proceed.", "Proceed", "No",
                () -> {
                    genericDialogFragment.dismiss();
                    getBaseActivity().addDockableFragment(ForgotPassowrdFragment.newInstance(false), false);

                }, genericDialogFragment::dismiss
        );
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
                                String code = webResponse.result.get("Password").getAsString();

                                sharedPreferenceManager.putValue(KEY_TOKEN, token);
                                sharedPreferenceManager.putValue(KEY_CARD_NUMBER, cardNumber);
                                sharedPreferenceManager.putValue(KEY_CODE, code);

                                getBaseActivity().openActivity(HomeActivity.class, ACCESS_LOGIN_DONE);
                                getBaseActivity().finish();
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }


}
