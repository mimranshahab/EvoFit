package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

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
import edu.aku.family_hifazat.helperclasses.CyberSoftSecurityHelper;
import edu.aku.family_hifazat.helperclasses.ui.helper.KeyboardHide;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.helperclasses.validator.CardNumberValidation;
import edu.aku.family_hifazat.libraries.maskformatter.MaskFormatter;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.PaymentRequestModel;
import edu.aku.family_hifazat.models.sending_model.LoginApiModel;
import edu.aku.family_hifazat.models.wrappers.Parameters;
import edu.aku.family_hifazat.models.wrappers.PaymentModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyEditTextView;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

import static edu.aku.family_hifazat.constatnts.AppConstants.CARD_MASK;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class PaymentTestingFragment extends BaseFragment {

    PaymentRequestModel payRequestModel;
    CyberSoftSecurityHelper cyberSoftSecurityHelper;
    @BindView(R.id.edCardNumber)
    AnyEditTextView edCardNumber;
    @BindView(R.id.btnSumbit)
    AnyTextView btnSumbit;
    Unbinder unbinder;
    @BindView(R.id.txtCode)
    AnyTextView txtCode;
    @BindView(R.id.edtMotherName)
    AnyEditTextView edtMotherName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payRequestModel = new PaymentRequestModel();
        cyberSoftSecurityHelper = new CyberSoftSecurityHelper();
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_forgot_password;
    }

    public static PaymentTestingFragment newInstance() {

        Bundle args = new Bundle();

        PaymentTestingFragment fragment = new PaymentTestingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edCardNumber.addValidator(new CardNumberValidation());
        edCardNumber.addTextChangedListener(new MaskFormatter(CARD_MASK, edCardNumber, '-'));
//        txtTitle.setText("Forgot Password");

        getCyberSignatureService(paymentModuleData());
    }

    @Override
    public void onResume() {
        super.onResume();


        edCardNumber.requestFocus();
        KeyboardHide.showSoftKeyboard(getContext(), edCardNumber);
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Forgot Password");
        titleBar.showBackButton(getBaseActivity());

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


    private void paymentGatewayService() {
        new WebServices(getBaseActivity(), null, BaseURLTypes.PAYMENT_GATEWAY_URL)
                .webServiceCyberSouce(
                        new WebServices.IRequestStringCallBack() {

                            @Override
                            public void requestDataResponse(String webResponse) {

                            }

                            @Override
                            public void onError() {

                            }
                        }
                        , payRequestModel);
    }

    private PaymentModel paymentModuleData() {
        UIHelper.showToast(getContext(), "Payment gateway service for testing..");

        PaymentModel paymentModel = new PaymentModel();
        Parameters parameters = new Parameters();

        parameters.setReferenceNumber(payRequestModel.getReferenceNo());
        parameters.setTransactionType(payRequestModel.getTransactionType());
        parameters.setCurrency(payRequestModel.getCurrency());
        parameters.setAmount(payRequestModel.getAmount());
        parameters.setLocale(payRequestModel.getLocale());
        parameters.setAccessKey(payRequestModel.getAccessKey());
        parameters.setProfileId(payRequestModel.getProfileID());
        parameters.setTransactionUuid(payRequestModel.getTransactionUUID());
        parameters.setSignedDateTime(payRequestModel.getSignedDateTimeString());
        parameters.setSignedFieldNames(payRequestModel.getSignedFieldNames());
        parameters.setUnsignedFieldNames(payRequestModel.getUnsignedFieldNames());
        parameters.setPaymentMethod(payRequestModel.getPaymentMethod());
        parameters.setCardType(payRequestModel.getCardType());
        parameters.setCardExpiryDate(payRequestModel.getCardExpirydate());
        parameters.setCardCvn(payRequestModel.getCVN());
        parameters.setBillToForename(payRequestModel.getBillForeName());
        parameters.setBillToSurname(payRequestModel.getBillSurName());
        parameters.setBillToEmail(payRequestModel.getBillEmailAddress());
        parameters.setBillToAddressLine1(payRequestModel.getBillAddressLine());
        parameters.setBillToAddressCity(payRequestModel.getBillAddressCity());
        parameters.setBillToAddressCountry(payRequestModel.getBillAddressCountry());
        parameters.setBill_to_address_postal_code(payRequestModel.getBillPostalAddress());
        parameters.setBill_to_address_state(payRequestModel.getBillState());

        paymentModel.setParameters(parameters);

        return paymentModel;
    }

    private void getCyberSignatureService(final PaymentModel paymentModel) {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceGetCyberSignature("PaymentManager.GetCybersourceSignature", paymentModel.toString(), new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        PaymentModel paymentModel1 = GsonFactory.getSimpleGson().fromJson(webResponse.result, PaymentModel.class);
                        payRequestModel.setSignature(paymentModel1.getSignature());
                        paymentGatewayService();
                    }

                    @Override
                    public void onError() {

                    }
                });
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


    @OnClick({R.id.btnSumbit, R.id.txtCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSumbit:
                validateAndCallAPI();
                break;
            case R.id.txtCode:
                getBaseActivity().addDockableFragment(ChangePasswordFragment.newInstance(null), false);
                break;

        }
    }

    private void validateAndCallAPI() {
        if (edCardNumber.testValidity() && edCardNumber.getText().length() == 14 && edtMotherName.testValidity()) {
            LoginApiModel loginApiModel = new LoginApiModel(edCardNumber.getStringTrimmed(), null);
            loginApiModel.setMotherName(edtMotherName.getText().toString());
            generatePasswordResetCodeAndEmail(loginApiModel);
        } else {
            UIHelper.showToast(getContext(), "Please enter valid Card Number and Mother Name");
        }
    }


    private void generatePasswordResetCodeAndEmail(final LoginApiModel loginApiModel) {
        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_USER_GENERATE_PASSWORD_CODE,
                        loginApiModel.toString(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
//                                String message = webResponse.result.get("RecordMessage").getAsString();
                                String message = "A verification code has been sent to subscriber’s registered email address.";

                                final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
                                genericDialogFragment.setCancelable(false);
                                UIHelper.genericPopUp(getBaseActivity(), genericDialogFragment, "Verification Code", message
                                        , "OK", null,
                                        () -> {
                                            genericDialogFragment.dismiss();
                                            getBaseActivity().addDockableFragment(ChangePasswordFragment.newInstance(loginApiModel.getCardNumber()), false);
                                        },
                                        () -> {
                                        });
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }
}