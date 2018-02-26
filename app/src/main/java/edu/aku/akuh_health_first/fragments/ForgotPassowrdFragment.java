package edu.aku.akuh_health_first.fragments;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

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
import edu.aku.akuh_health_first.models.receiving_model.AddUpdateVaccineModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class ForgotPassowrdFragment extends BaseFragment {

    PaymentRequestModel payRequestModel;
    CyberSoftSecurityHelper cyberSoftSecurityHelper;
    HashMap parameter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payRequestModel = new PaymentRequestModel();
        cyberSoftSecurityHelper = new CyberSoftSecurityHelper();
        parameter = new HashMap<String, String>();
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

        WifiManager wm = (WifiManager) getContext().getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        payRequestModel.setConsIPAddress(ip);


        //add items

        parameter.put("access_key", payRequestModel.getAccessKey());
        parameter.put("profile_id", payRequestModel.getProfileID());
        parameter.put("transaction_uuid", payRequestModel.getTransactionUUID());
        parameter.put("signed_field_names", payRequestModel.getSignedFieldNames());
        parameter.put("unsigned_field_names", payRequestModel.getUnsignedFieldNames());
        parameter.put("signed_date_time", payRequestModel.getSignedDateTimeString());
        parameter.put("locale", payRequestModel.getLocale());
        parameter.put("bill_to_address_line1", payRequestModel.getBillAddressLine());
        parameter.put("bill_to_address_city", payRequestModel.getBillAddressCity());
        parameter.put("bill_to_address_country", payRequestModel.getBillAddressCountry());
        parameter.put("bill_to_email", payRequestModel.getBillEmailAddress());
        parameter.put("bill_to_surname", payRequestModel.getBillSurName());
        parameter.put("bill_to_forename", payRequestModel.getBillForeName());
        parameter.put("bill_to_phone", payRequestModel.getBillPhone());
        parameter.put("bill_to_company_name", payRequestModel.getBillCompanyName());
        parameter.put("consumer_id", payRequestModel.getConsumerID());
        parameter.put("customer_ip_address", payRequestModel.getConsIPAddress());
        parameter.put("transaction_type", payRequestModel.getTransactionType());
        parameter.put("reference_number", payRequestModel.getReferenceNo());
        parameter.put("amount", payRequestModel.getAmount() + "");
        parameter.put("currency", payRequestModel.getCurrency());
        parameter.put("merchant_defined_data1", payRequestModel.getMerchantDefinedData());

//        parameter.put("card_type", payRequestModel.getCardType());
//        parameter.put("card_number", payRequestModel.getCardNumber());
//        parameter.put("card_expiry_date", payRequestModel.getCardExpirydate());
//        parameter.put("card_cvn", payRequestModel.getCVN());


        try {
            String signature = cyberSoftSecurityHelper.sign(parameter);
            payRequestModel.setSignature(signature);

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        parameter.put("signature", payRequestModel.getSignature());

        getCyberSoftToken();


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


    private void getCyberSoftToken() {
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
                );
    }

}
