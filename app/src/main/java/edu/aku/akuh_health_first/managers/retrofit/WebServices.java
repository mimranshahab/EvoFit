package edu.aku.akuh_health_first.managers.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonObject;

import java.lang.String;

import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.enums.FileType;
import edu.aku.akuh_health_first.helperclasses.Helper;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.FileManager;
import edu.aku.akuh_health_first.models.PaymentRequestModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hamzakhan on 6/30/2017.
 */

public class WebServices {
    private WebServiceProxy apiService;
    private ProgressDialog mDialog;
    private Context mContext;
    private static String bearerToken = "";

    public static String getBearerToken() {
        return bearerToken;
    }

    public static void setBearerToken(String bearerToken) {
        WebServices.bearerToken = bearerToken;
    }

    public WebServices(Activity activity, String token, BaseURLTypes baseURLTypes) {
        switch (baseURLTypes) {
            case PACS_VIEWER:
                apiService = WebServiceFactory.getInstancePACSURL(token, bearerToken);
                break;
            case AHFA_BASE_URL:
                apiService = WebServiceFactory.getInstanceBaseURL(token);
                break;
            case PACS_IMAGE_DOWNLOAD:
                apiService = WebServiceFactory.getInstancePACSURL(token, bearerToken);
                break;
            case PAYMENT_GATEWAY_URL:
                apiService = WebServiceFactory.getInstancePaymentGateway("");
        }


        mContext = activity;
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("Loading.....");
        mDialog.setTitle("Please Wait");
        mDialog.setCancelable(true);

        if (!((Activity) mContext).isFinishing())
            mDialog.show();
    }

    public WebServices(Context mContext) {
        this.mContext = mContext;
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("Loading.....");
        mDialog.setTitle("Please Wait");
        mDialog.setCancelable(true);

        if (!((Activity) mContext).isFinishing())
            mDialog.show();
    }

    public static boolean IsResponseError(Response<WebResponse<JsonObject>> response) {
        if (response != null && !response.isSuccessful() && response.errorBody() != null) {
            return false;
        }
        return true;
    }

    public static boolean IsResponseErrorForArray(Response<WebResponse<ArrayList<JsonObject>>> response) {
        if (response != null && !response.isSuccessful() && response.errorBody() != null) {
            return false;
        }
        return true;
    }

    public static boolean IsResponseErrorForStringResult(Response<WebResponse<String>> response) {
        if (response != null && !response.isSuccessful() && response.errorBody() != null) {
            return false;
        }
        return true;
    }

    public static boolean hasValidStatus(Response<WebResponse<JsonObject>> response) {
        if (response != null && response.body() != null) {
            return response.body().isSuccess();
        }
        return false;
    }

    public static boolean hasValidStatusForArray(Response<WebResponse<ArrayList<JsonObject>>> response) {
        if (response != null && response.body() != null) {
            return response.body().isSuccess();
        }
        return false;
    }

    public static boolean hasValidStatusForStringResult(Response<WebResponse<String>> response) {
        if (response != null && response.body() != null) {
            return response.body().isSuccess();
        }
        return false;
    }

    public void webServiceUploadFileAPI(String requestMethod, String filePath, FileType fileType, final IRequestWebResponseWithStringDataCallBack callBack) {

        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        MultipartBody.Part bodyRequestData;
        if (filePath == null || !FileManager.isFileExits(filePath)) {
            dismissDialog();
            UIHelper.showShortToastInCenter(mContext, "File path is empty.");
            return;
        }

        File file = new File(filePath);

        bodyRequestData =
                MultipartBody.Part.createFormData(WebServiceConstants.PARAMS_REQUEST_DATA, file.getName(),
                        RequestBody.create(MediaType.parse(fileType.canonicalForm() + "/" + FileManager.getExtension(file.getName())), file));
        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                apiService.uploadFileRequestApi(bodyRequestMethod, bodyRequestData).enqueue(new Callback<WebResponse<String>>() {
                    @Override
                    public void onResponse(Call<WebResponse<String>> call, Response<WebResponse<String>> response) {
                        dismissDialog();
                        if (!IsResponseErrorForStringResult(response)) {
                            String errorBody;
                            try {
                                errorBody = response.errorBody().string();
                                UIHelper.showShortToastInCenter(mContext, errorBody);
                                callBack.onError();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }

                        if (hasValidStatusForStringResult(response))
                            callBack.requestDataResponse(response.body());
                        else {
                            String message = response.body().message != null ? response.body().message : response.errorBody().toString();
                            UIHelper.showShortToastInCenter(mContext, message);
                        }
                    }

                    @Override
                    public void onFailure(Call<WebResponse<String>> call, Throwable t) {
                        UIHelper.showShortToastInCenter(mContext, "Something went wrong, Please check your internet connection.");
                        dismissDialog();
                        callBack.onError();
                    }
                });
            } else {
                dismissDialog();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }
    }

    public void webServiceRequestAPI(String requestMethod, String requestData, final IRequestJsonDataCallBack callBack) {

        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        RequestBody bodyRequestData = getRequestBody(okhttp3.MultipartBody.FORM, requestData);

        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                Call<WebResponse<JsonObject>> webResponseCall = apiService.webServiceRequestAPI(bodyRequestMethod, bodyRequestData);
//                webResponseCall.request().newBuilder().addHeader("name", "hkhkhkhkhk").build();
                webResponseCall.enqueue(new Callback<WebResponse<JsonObject>>() {
                    @Override
                    public void onResponse(Call<WebResponse<JsonObject>> call, Response<WebResponse<JsonObject>> response) {
                        dismissDialog();
                        if (!IsResponseError(response)) {
                            String errorBody;
                            try {
                                errorBody = response.errorBody().string();
                                UIHelper.showShortToastInCenter(mContext, errorBody);
                                callBack.onError();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }

                        if (hasValidStatus(response))
                            callBack.requestDataResponse(response.body());
                        else {
                            String message = response.body().message != null ? response.body().message : response.errorBody().toString();
                            UIHelper.showShortToastInCenter(mContext, message);
                        }
                    }

                    @Override
                    public void onFailure(Call<WebResponse<JsonObject>> call, Throwable t) {
                        UIHelper.showShortToastInCenter(mContext, "Something went wrong, Please check your internet connection.");
                        dismissDialog();
                        callBack.onError();
                    }
                });
            } else {
                dismissDialog();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }

    }

    public void webServiceRequestAPIForWebResponseWithString(String requestMethod, String requestData, final IRequestWebResponseWithStringDataCallBack callBack) {
        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        RequestBody bodyRequestData = getRequestBody(okhttp3.MultipartBody.FORM, requestData);

        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                Call<WebResponse<String>> webResponseCall = apiService.webServiceRequestAPIForWebResponseWithString(bodyRequestMethod, bodyRequestData);
//                webResponseCall.request().newBuilder().addHeader("name", "hkhkhkhkhk").build();
                webResponseCall.enqueue(new Callback<WebResponse<String>>() {
                    @Override
                    public void onResponse(Call<WebResponse<String>> call, Response<WebResponse<String>> response) {
                        dismissDialog();
                        if (!IsResponseErrorForStringResult(response)) {
                            String errorBody;
                            try {
                                errorBody = response.errorBody().string();
                                UIHelper.showShortToastInCenter(mContext, errorBody);
                                callBack.onError();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }

                        if (hasValidStatusForStringResult(response))
                            callBack.requestDataResponse(response.body());
                        else {
                            String message = response.body().message != null ? response.body().message : response.errorBody().toString();
                            UIHelper.showShortToastInCenter(mContext, message);
                        }
                    }

                    @Override
                    public void onFailure(Call<WebResponse<String>> call, Throwable t) {
                        UIHelper.showShortToastInCenter(mContext, "Something went wrong, Please check your internet connection.");
                        dismissDialog();
                        callBack.onError();
                    }
                });
            } else {
                dismissDialog();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }

    }


    public void webServiceRequestAPIForArray(String requestMethod, String requestData, final IRequestArrayDataCallBack callBack) {

        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        RequestBody bodyRequestData = getRequestBody(okhttp3.MultipartBody.FORM, requestData);

        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                Call<WebResponse<ArrayList<JsonObject>>> webResponseCall = apiService.webServiceRequestAPIForArray(bodyRequestMethod, bodyRequestData);
//                webResponseCall.request().newBuilder().addHeader("name", "hkhkhkhkhk").build();
                webResponseCall.enqueue(new Callback<WebResponse<ArrayList<JsonObject>>>() {
                    @Override
                    public void onResponse(Call<WebResponse<ArrayList<JsonObject>>> call, Response<WebResponse<ArrayList<JsonObject>>> response) {
                        dismissDialog();
                        if (!IsResponseErrorForArray(response)) {
                            String errorBody;
                            try {
                                errorBody = response.errorBody().string();
                                UIHelper.showShortToastInCenter(mContext, errorBody);
                                callBack.onError();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }

                        if (hasValidStatusForArray(response))
                            callBack.requestDataResponse(response.body());
                        else {
                            String message = response.body().message != null ? response.body().message : response.errorBody().toString();
                            UIHelper.showShortToastInCenter(mContext, message);
                        }
                    }

                    @Override
                    public void onFailure(Call<WebResponse<ArrayList<JsonObject>>> call, Throwable t) {
                        UIHelper.showShortToastInCenter(mContext, "Something went wrong, Please check your internet connection.");
                        dismissDialog();
                        callBack.onError();
                    }
                });
            } else {
                dismissDialog();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }

    }


    public void webServiceGetToken(final IRequestStringCallBack callBack) {


        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                WebServiceFactory.getInstance().getToken().enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        dismissDialog();
                        if (response != null && response.body() != null) {
                            if (!response.body().isEmpty()) {
                                callBack.requestDataResponse(response.body());
                            }

                        } else {
                            UIHelper.showToast(mContext, "Null Response");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        UIHelper.showShortToastInCenter(mContext, "Something went wrong, Please check your internet connection.");
                        dismissDialog();
                        callBack.onError();
                    }
                });
            } else {
                dismissDialog();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }

    }

    public void webServiceCyberSouce(final IRequestStringCallBack callBack) {
        PaymentRequestModel payRequestModel = new PaymentRequestModel();

        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                apiService.cyberSoft(

                        payRequestModel.getAccessKey() + "",
                        payRequestModel.getProfileID() + "",
                        payRequestModel.getTransactionUUID() + "",
                        payRequestModel.getSignedFieldNames() + "",
                        payRequestModel.getUnsignedFieldNames() + "",
                        payRequestModel.getSignedDateTimeString() + "",
                        payRequestModel.getLocale() + "",
                        payRequestModel.getBillAddressLine() + "",
                        payRequestModel.getBillAddressCity() + "",
                        payRequestModel.getBillAddressCountry() + "",
                        payRequestModel.getBillEmailAddress() + "",
                        payRequestModel.getBillSurName() + "",
                        payRequestModel.getBillForeName() + "",
                        payRequestModel.getBillPhone() + "",
                        payRequestModel.getBillCompanyName() + "",
                        payRequestModel.getConsumerID() + "",
                        payRequestModel.getConsIPAddress() + "",
                        payRequestModel.getTransactionType() + "",
                        payRequestModel.getReferenceNo() + "",
                        payRequestModel.getAmount() + "",
                        payRequestModel.getCurrency() + "",
                        payRequestModel.getMerchantDefinedData() + "",
//                        payRequestModel.getMerchantDefinedData() + "",
//                        payRequestModel.getMerchantDefinedData() + "",
//                        payRequestModel.getMerchantDefinedData() + "",
//                        payRequestModel.getMerchantDefinedData() + "",
//                        WebServiceConstants.Secret_token_paymentGatway + ""
                        payRequestModel.getSignature()+""


                ).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        dismissDialog();
                        if (response != null && response.body() != null) {
//                            if (!response.body()) {
//                                callBack.requestDataResponse(response.body());
//                            }

                        } else {
                            UIHelper.showToast(mContext, "Null Response");
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        UIHelper.showShortToastInCenter(mContext, "Something went wrong, Please check your internet connection.");
                        dismissDialog();
                        callBack.onError();
                    }
                });
            } else {
                dismissDialog();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }

    }


    @NonNull
    public RequestBody getRequestBody(MediaType form, String trim) {
        return RequestBody.create(
                form, trim);
    }


    private void dismissDialog() {
        mDialog.dismiss();
    }

    public interface IRequestJsonDataCallBack {
        void requestDataResponse(WebResponse<JsonObject> webResponse);

        void onError();
    }

    public interface IRequestArrayDataCallBack {
        void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse);

        void onError();
    }

    public interface IRequestWebResponseWithStringDataCallBack {
        void requestDataResponse(WebResponse<String> webResponse);

        void onError();
    }

    public interface IRequestStringCallBack {
        void requestDataResponse(String webResponse);

        void onError();
    }


}
