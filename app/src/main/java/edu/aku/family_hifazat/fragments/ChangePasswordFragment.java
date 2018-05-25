package edu.aku.family_hifazat.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.andreabaccega.widget.FormEditText;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.fragments.abstracts.GenericDialogFragment;
import edu.aku.family_hifazat.helperclasses.Helper;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.helperclasses.validator.CardNumberValidation;
import edu.aku.family_hifazat.helperclasses.validator.PasswordValidation;
import edu.aku.family_hifazat.libraries.maskformatter.MaskFormatter;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.receiving_model.AddUpdateModel;
import edu.aku.family_hifazat.models.sending_model.LoginApiModel;
import edu.aku.family_hifazat.models.sending_model.NewPasswordModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyEditTextView;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.PinEntryEditText;
import edu.aku.family_hifazat.widget.TitleBar;

import static edu.aku.family_hifazat.constatnts.AppConstants.CARD_MASK;
import static edu.aku.family_hifazat.constatnts.AppConstants.KEY_CODE;

/**
 * Created by aqsa.sarwar on 4/20/2018.
 */

public class ChangePasswordFragment extends BaseFragment {

    @BindView(R.id.contLogos)
    LinearLayout contLogos;
    @BindView(R.id.txtPinForgotPass)
    PinEntryEditText txtPinForgotPass;
    @BindView(R.id.llVerificationCode)
    LinearLayout llVerificationCode;
    @BindView(R.id.edtOldPassword)
    FormEditText edtOldPassword;
    @BindView(R.id.edtNewPassword)
    FormEditText edtNewPassword;
    @BindView(R.id.edtConPassword)
    FormEditText edtConPassword;
    @BindView(R.id.btnLogin)
    AnyTextView btnLogin;
    @BindView(R.id.edCardNumber)
    AnyEditTextView edCardNumber;
    private String cardNumber;
    private Unbinder unbinder;
    private ProgressDialog mDialog;

    public static ChangePasswordFragment newInstance(String cardNumber) {

        Bundle args = new Bundle();

        ChangePasswordFragment fragment = new ChangePasswordFragment();
        fragment.cardNumber = cardNumber;
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
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Change Password");
        titleBar.showBackButton(getBaseActivity());

        titleBar.setTitle("Change Password");
        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public void setListeners() {

        if (cardNumber != null) {
            txtPinForgotPass.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    edtNewPassword.requestFocus();
                    edtNewPassword.performClick();
                }
            });
        }
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
//        txtTitle.setText("Change Password");
        if (cardNumber != null) {
            edCardNumber.setText(cardNumber);
            edCardNumber.setClickable(false);
            edCardNumber.setEnabled(false);
            edCardNumber.setAlpha(0.5f);
        }

        edtNewPassword.addValidator(new PasswordValidation());
        edtConPassword.addValidator(new PasswordValidation(edtNewPassword));

        edCardNumber.addValidator(new CardNumberValidation());
        edCardNumber.addTextChangedListener(new MaskFormatter(CARD_MASK, edCardNumber, '-'));

        mDialog = Helper.getLoader(getContext());
        mDialog.setMessage("Processing your request ...");
        mDialog.setTitle("Please Wait");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:

                if (edCardNumber.testValidity() && edCardNumber.getStringTrimmed().length() == 14 && edtNewPassword.testValidity() && edtConPassword.testValidity() && txtPinForgotPass.getText().toString().length() == 6) {
                    if (edtNewPassword.getText().toString().length() >= 7 && edtConPassword.getText().toString().length() >= 7) {
                        if (edtNewPassword.getText().toString().equals(edtConPassword.getText().toString())) {
                            NewPasswordModel newPasswordModel = new NewPasswordModel();
                            newPasswordModel.setCardnumber(edCardNumber.getStringTrimmed());
                            newPasswordModel.setPasswordresetcode(txtPinForgotPass.getText().toString().trim());
                            newPasswordModel.setPassword(edtConPassword.getText().toString().trim());

                            mDialog.show();
                            getOneTimeTokenService(newPasswordModel);
                        } else {
                            UIHelper.showToast(getContext(), "Password not match");
                        }

                    } else {
                        UIHelper.showToast(getContext(), "Password should be greater than 6 digits");
                    }


                } else {
                    UIHelper.showToast(getContext(), "Please filled all fields");
                }


                break;
        }
    }

    private void newPasswordRequest(NewPasswordModel newPasswordModel, String token) {
        new WebServices(getBaseActivity(),
                token,
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_USER_VERIFY_PASSWORD_CODE_AND_UPDATE_PASSWORD,
                        newPasswordModel.toString(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
//                                String message = webResponse.result.get("RecordMessage").getAsString();
                                String message = "Your password has been updated.";

                                String password = webResponse.result.get("Password").getAsString();

                                if (sharedPreferenceManager.getString(KEY_CODE) != null && !sharedPreferenceManager.getString(KEY_CODE).isEmpty()) {
                                    sharedPreferenceManager.putValue(KEY_CODE, password);
                                }


                                final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
                                genericDialogFragment.setCancelable(false);
                                UIHelper.genericPopUp(getBaseActivity(), genericDialogFragment, "Password Updated", message
                                        , "OK", null,
                                        () -> {
                                            genericDialogFragment.dismiss();
                                            getBaseActivity().reload();
                                        },
                                        () -> {
                                        });
                                mDialog.dismiss();
                            }

                            @Override
                            public void onError() {
                                mDialog.dismiss();
                            }
                        });
    }


    private void getOneTimeTokenService(NewPasswordModel newPasswordModel) {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL, false)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_GET_ONE_TIME_TOKEN,
                        "",
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                AddUpdateModel addUpdateModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, AddUpdateModel.class);
                                if (addUpdateModel.getStatus()) {
                                    putOneTimeToken(addUpdateModel.getRecordid());
                                    newPasswordRequest(newPasswordModel, addUpdateModel.getRecordid());
                                }

                            }

                            @Override
                            public void onError() {
                                mDialog.dismiss();
                                UIHelper.showToast(getContext(), "Something went wrong, Please try again");
                            }
                        });
    }

}
