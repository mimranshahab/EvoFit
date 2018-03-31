package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.JsonObject;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.CyberSoftSecurityHelper;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.PaymentRequestModel;
import edu.aku.akuh_health_first.models.wrappers.Parameters;
import edu.aku.akuh_health_first.models.wrappers.PaymentModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class ForgotPassowrdFragment extends BaseFragment {

    PaymentRequestModel payRequestModel;
    CyberSoftSecurityHelper cyberSoftSecurityHelper;

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

    public static ForgotPassowrdFragment newInstance() {

        Bundle args = new Bundle();

        ForgotPassowrdFragment fragment = new ForgotPassowrdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        getCyberSignatureService(paymentModel);
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Forgot Password");

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

}
