package edu.aku.family_hifazat.managers.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonObject;

import java.lang.String;

import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.enums.FileType;
import edu.aku.family_hifazat.helperclasses.Helper;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.managers.FileManager;
import edu.aku.family_hifazat.models.PaymentRequestModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;

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

    public WebServices(Context activity, String token, BaseURLTypes baseURLTypes) {
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
        mDialog.setMessage("Processing your request ...");
        mDialog.setTitle("Please Wait");
        mDialog.setCancelable(false);

        if (!((Activity) mContext).isFinishing())
            mDialog.show();
    }

    public WebServices(Context mContext) {
        this.mContext = mContext;
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("Loading...");
        mDialog.setTitle("Please Wait");
        mDialog.setCancelable(true);

        if (!((Activity) mContext).isFinishing())
            mDialog.show();
    }

    private static boolean IsResponseError(Response<WebResponse<JsonObject>> response) {
        return !(response != null && !response.isSuccessful() && response.errorBody() != null);
    }

    private static boolean IsResponseErrorForArray(Response<WebResponse<ArrayList<JsonObject>>> response) {
        return !(response != null && !response.isSuccessful() && response.errorBody() != null);
    }

    private boolean IsResponseErrorForStringResult(Response<WebResponse<String>> response) {
        return !(response != null && !response.isSuccessful() && response.errorBody() != null);
    }

    private boolean hasValidStatus(Response<WebResponse<JsonObject>> response) {
        if (response != null && response.body() != null) {
            if (response.body().isSuccess()) {

//                for testing
//                return true;

                if (response.body().result.get("RecordFound") == null) {
                    if (response.body().result.get("StrStatus") == null) {
                        return false;
                    } else if (response.body().result.get("StrStatus").isJsonNull()) {
                        return false;
                    } else {
                        return !response.body().result.get("StrStatus").getAsString().isEmpty();
                    }
                } else if (response.body().result.get("RecordFound").isJsonNull()) {
                    return false;
                } else {
                    return response.body().result.get("RecordFound").getAsString().equals("true");
                }
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    private boolean hasValidStatusForArray(Response<WebResponse<ArrayList<JsonObject>>> response) {


        if (response != null && response.body() != null && response.body().isSuccess()) {


            // FIXME: 5/2/2018   //for testing
//            return true;
//

            if (response.body().result == null || response.body().result.isEmpty() || response.body().result.get(0) == null) {
                return false;
            }

            if (response.body().result.get(0).get("RecordFound") == null) {
                return false;
            } else if (response.body().result.get(0).get("RecordFound").isJsonNull()) {
                return false;
            } else {
                return response.body().result.get(0).get("RecordFound").getAsString().equals("true");
            }


        } else {
            return false;
        }
    }

    private boolean hasValidStatusForStringResult(Response<WebResponse<String>> response) {
        return response != null && response.body() != null && response.body().isSuccess();
    }

    public void webServiceUploadFileAPI(String requestMethod, String filePath, FileType fileType, String reqBody,
                                        final IRequestJsonDataCallBack callBack) {

        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        RequestBody requestBody = getRequestBody(okhttp3.MultipartBody.FORM, reqBody);

        MultipartBody.Part part;
        if (filePath == null || !FileManager.isFileExits(filePath)) {
            dismissDialog();
            UIHelper.showShortToastInCenter(mContext, "File path is empty.");
            return;
        }

        File file = new File(filePath);

        part =
                MultipartBody.Part.createFormData(WebServiceConstants.PARAMS_REQUEST_DATA, file.getName(),
                        RequestBody.create(MediaType.parse(fileType.canonicalForm() + "/" + FileManager.getExtension(file.getName())), file)
                );


        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                apiService.uploadFileRequestApi(bodyRequestMethod, requestBody, part).enqueue(
                        new Callback<WebResponse<JsonObject>>() {
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
//                                    UIHelper.showShortToastInCenter(mContext, message);
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
                callBack.onError();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }
    }

    public void webServiceRequestAPIForJsonObject(final String requestMethod, String requestData, final IRequestJsonDataCallBack callBack) {

        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        RequestBody bodyRequestData = getRequestBody(okhttp3.MultipartBody.FORM, requestData);

        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                Call<WebResponse<JsonObject>> webResponseCall = apiService.webServiceRequestAPI(bodyRequestMethod, bodyRequestData);
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
                            if (response.body() != null) {
                                if (response.body().isSuccess()) {
                                    if (response.body().result.get("RecordMessage") == null) {

                                        if (response.body().result.get("Message") == null) {
                                            errorToastForObject(response);
                                        } else if (response.body().result.get("Message").isJsonNull()) {
                                            errorToastForObject(response);
                                        } else {
                                            String message = response.body().result.get("Message").toString();
                                            if (requestMethod.equals(WebServiceConstants.METHOD_USER_LOGIN)) {
                                                UIHelper.showShortToastInCenter(mContext, message);
                                            } else {
//                                            UIHelper.showShortToastInCenter(mContext, message);
                                            }
                                        }

                                    } else if (response.body().result.get("RecordMessage").isJsonNull()) {
                                        errorToastForObject(response);
                                    } else {
                                        // Show Toast here
                                        String message = response.body().result.get("RecordMessage").toString();
                                        if (requestMethod.equals(WebServiceConstants.METHOD_USER_LOGIN)
                                                || requestMethod.equals(WebServiceConstants.METHOD_USER_GENERATE_PASSWORD_CODE)
                                                || requestMethod.equals(WebServiceConstants.METHOD_USER_VERIFY_PASSWORD_CODE_AND_UPDATE_PASSWORD)

                                                ) {
                                            UIHelper.showShortToastInCenter(mContext, message);
                                        } else {
                                            UIHelper.showShortToastInCenter(mContext, message);
                                        }
                                        callBack.onError();
                                    }

                                } else {
                                    String message = response.body().message != null ? response.body().message : response.errorBody().toString();
//                                    UIHelper.showToast(mContext, message);
                                }
                            }

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
                callBack.onError();
            }

        } catch (
                Exception e)

        {
            dismissDialog();
            e.printStackTrace();

        }

    }

    public void webServiceRequestAPIForArray(String requestMethod, String requestData, final IRequestArrayDataCallBack callBack) {

        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        RequestBody bodyRequestData = getRequestBody(okhttp3.MultipartBody.FORM, requestData);

        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                Call<WebResponse<ArrayList<JsonObject>>> webResponseCall =
                        apiService.webServiceRequestAPIForArray(bodyRequestMethod, bodyRequestData);
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
                            if (response != null && response.body() != null) {
                                if (response.body().isSuccess()) {

                                    if (response.body().result == null || response.body().result.isEmpty() || response.body().result.get(0) == null) {
                                        UIHelper.showToast(mContext, "Record Missing in API");
                                        callBack.onError();
                                    } else if (response.body().result.get(0).get("RecordMessage") == null) {
                                        errorToastForArray(response);
                                        callBack.onError();
                                    } else if (response.body().result.get(0).get("RecordMessage").isJsonNull()) {
                                        errorToastForArray(response);
                                        callBack.onError();
                                    } else {

                                        // FIXME: 5/2/2018 Show Toast here

                                        String message = response.body().result.get(0).get("RecordMessage").toString();

                                        if (requestMethod.equals(WebServiceConstants.METHOD_PATIENT_HEALTH_SUMMARY)
//                                            || requestMethod.equals(WebServiceConstants.METHOD_USER_GENERATE_PASSWORD_CODE)
//                                            || requestMethod.equals(WebServiceConstants.METHOD_USER_VERIFY_PASSWORD_CODE_AND_UPDATE_PASSWORD)
                                                ) {
                                            UIHelper.showShortToastInCenter(mContext, message);
                                        } else {
//                                        UIHelper.showShortToastInCenter(mContext, message);
                                        }
                                        callBack.onError();
                                    }

                                } else {
                                    String message = response.body().message != null ? response.body().message : response.errorBody().toString();
//                                    UIHelper.showShortToastInCenter(mContext, message);
                                    callBack.onError();
                                }
                            }

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
                callBack.onError();
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
                callBack.onError();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }

    }

    public void webServiceRequestAPIAnyObject(String requestMethod, String requestData, final IRequestWebResponseAnyObjectCallBack callBack) {
        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        RequestBody bodyRequestData = getRequestBody(okhttp3.MultipartBody.FORM, requestData);

        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                Call<WebResponse<Object>> webResponseCall = apiService.webServiceRequestAPIForWebResponseAnyObject(bodyRequestMethod, bodyRequestData);
//                webResponseCall.request().newBuilder().addHeader("name", "hkhkhkhkhk").build();
                webResponseCall.enqueue(new Callback<WebResponse<Object>>() {
                    @Override
                    public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                        dismissDialog();

                        if (response.body() == null) {
                            callBack.onError("null response");
                            return;
                        }

                        if (response.isSuccessful() && response.body().isSuccess()) {
                            callBack.requestDataResponse(response.body());
                        } else {
                            String message = response.body().message != null ? response.body().message : response.errorBody().toString();
                            callBack.onError(message);
                        }
                    }

                    @Override
                    public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                        UIHelper.showShortToastInCenter(mContext, "Something went wrong, Please check your internet connection.");
                        dismissDialog();
                        callBack.onError("Failure");
                    }
                });
            } else {
                dismissDialog();
                callBack.onError("Internet not connected");
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }

    }

    private void errorToastForArray(Response<WebResponse<ArrayList<JsonObject>>> response) {
//        UIHelper.showShortToastInCenter(mContext, "API Error in RecordFound");
    }

    private void errorToastForObject(Response<WebResponse<JsonObject>> response) {
//        String message = response.body().message != null ? response.body().message : response.errorBody().toString();
//        UIHelper.showShortToastInCenter(mContext, "API Error in RecordFound");
    }


    public void webServicegetToken(final IRequestStringCallBack callBack) {


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
                callBack.onError();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }

    }

    public void webServiceCyberSouce(final IRequestStringCallBack callBack, PaymentRequestModel payRequestModel) {

        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                apiService.cyberSoft(

                        payRequestModel.getReferenceNo() + "",
                        payRequestModel.getTransactionType() + "",
                        payRequestModel.getCurrency() + "",
                        payRequestModel.getAmount() + "",
                        payRequestModel.getLocale() + "",
                        payRequestModel.getAccessKey() + "",
                        payRequestModel.getProfileID() + "",
                        payRequestModel.getTransactionUUID() + "",
                        payRequestModel.getSignedDateTimeString() + "",
                        payRequestModel.getSignedFieldNames() + "",
                        payRequestModel.getUnsignedFieldNames() + "",
                        payRequestModel.getSignature() + "",
                        payRequestModel.getPaymentMethod() + "",
                        payRequestModel.getCardType() + "",
                        payRequestModel.getCardNumber() + "",
                        payRequestModel.getCardExpirydate() + "",
                        payRequestModel.getCVN() + "",
                        payRequestModel.getBillForeName() + "",
                        payRequestModel.getBillSurName() + "",
                        payRequestModel.getBillEmailAddress() + "",
                        payRequestModel.getBillAddressLine() + "",
                        payRequestModel.getBillAddressCity() + "",
                        payRequestModel.getBillAddressCountry() + "",
                        payRequestModel.getBillPostalAddress() + "",
                        payRequestModel.getBillState() + ""

                ).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        dismissDialog();
                        if (response.body() != null) {
//                            if (!response.body()) {
//                                callBack.requestDataResponse(response.body());
//                            }
                            UIHelper.showToast(mContext, response.body().toString());

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
                callBack.onError();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }

    }

    public void webServiceGetCyberSignature(String requestMethod, String requestData, final IRequestJsonDataCallBack callBack) {

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


                        if (response.body() != null && response.body().isSuccess()) {
                            callBack.requestDataResponse(response.body());
                        } else {
                            if (response.body() != null) {
                                if (response.body().isSuccess()) {
                                    if (response.body().result.get("RecordMessage") == null) {

                                        if (response.body().result.get("Message") == null) {
                                            errorToastForObject(response);
                                        } else if (response.body().result.get("Message").isJsonNull()) {
                                            errorToastForObject(response);
                                        } else {
                                            String message = response.body().result.get("Message").toString();
//                                            UIHelper.showShortToastInCenter(mContext, message);
                                        }

                                    } else if (response.body().result.get("RecordMessage").isJsonNull()) {
                                        errorToastForObject(response);
                                    } else {
                                        String message = response.body().result.get("RecordMessage").toString();
//                                        UIHelper.showShortToastInCenter(mContext, message);
                                    }

                                } else {
                                    String message = response.body().message != null ? response.body().message : response.errorBody().toString();
                                    UIHelper.showToast(mContext, message);
                                }
                            }

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
                callBack.onError();
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

    public interface IRequestWebResponseAnyObjectCallBack {
        void requestDataResponse(WebResponse<Object> webResponse);

        void onError(Object object);
    }


    public interface IRequestStringCallBack {
        void requestDataResponse(String webResponse);

        void onError();

    }


    public void webServiceUploadFileAPIV1(String requestMethod, final IRequestWebResponseWithStringDataCallBack callBack) {

        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        RequestBody bodyRequestData = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);


        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                apiService.webServiceRequestAPI(bodyRequestMethod, bodyRequestData).
                        enqueue(new Callback<WebResponse<JsonObject>>() {
                            @Override
                            public void onResponse(Call<WebResponse<JsonObject>> call, Response<WebResponse<JsonObject>> response) {
                                dismissDialog();
                                if (!IsResponseError(response)) {
//                            String errorBody;
//                            try {
//                                errorBody = response.errorBody().string();
//                                UIHelper.showShortToastInCenter(mContext, errorBody);
//                                callBack.onError();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            return;
//                        }

//                        if (hasValidStatusForStringResult(response))
//                            callBack.requestDataResponse(response.body());
//                        else {
//                            String message = response.body().message != null ? response.body().message : response.errorBody().toString();
//                            UIHelper.showShortToastInCenter(mContext, message);
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
                callBack.onError();
            }

        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();

        }
    }


}
