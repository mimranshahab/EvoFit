package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

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
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.sending_model.LoginApiModel;
import edu.aku.family_hifazat.models.sending_model.NewPasswordModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.PinEntryEditText;
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
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.txtPinForgotPass)
    PinEntryEditText txtPinForgotPass;
    private String cardNumber;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTitle.setText("Change Password");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.btnBack, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getBaseActivity().onBackPressed();
                break;
            case R.id.btnLogin:

                if (edtNewPassword.testValidity() && edtConPassword.testValidity() && txtPinForgotPass.getText().toString().length() == 6) {
                    if (edtNewPassword.getText().toString().length() >= 6 && edtConPassword.getText().toString().length() >= 6) {
                        if (edtNewPassword.getText().toString().equals(edtConPassword.getText().toString())) {
                            NewPasswordModel newPasswordModel = new NewPasswordModel();
                            newPasswordModel.setCardnumber(cardNumber);
                            newPasswordModel.setPasswordresetcode(txtPinForgotPass.getText().toString().trim());
                            newPasswordModel.setPassword(edtConPassword.getText().toString().trim());

                            newPasswordRequest(newPasswordModel);
                        } else {
                            UIHelper.showToast(getContext(), "Password not match");
                        }

                    } else {
                        UIHelper.showToast(getContext(), "Password should be greater than 5 digits");
                    }


                } else {
                    UIHelper.showToast(getContext(), "Please filled all fields");
                }


                break;
        }
    }

    private void newPasswordRequest(NewPasswordModel newPasswordModel) {
        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_USER_VERIFY_PASSWORD_CODE_AND_UPDATE_PASSWORD,
                        newPasswordModel.toString(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                String message = webResponse.result.get("RecordMessage").getAsString();
                                UIHelper.showToast(getContext(), message);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        getBaseActivity().reload();
                                    }
                                }, 500);
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }

}
