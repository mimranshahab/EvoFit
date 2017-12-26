package edu.aku.managers.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonObject;

import edu.aku.helperclasses.Helper;
import edu.aku.helperclasses.ui.helper.UIHelper;

import java.io.IOException;

import edu.aku.models.wrappers.WebResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hamzakhan on 6/30/2017.
 */

public class WebServices {
    public static String testString = "2428";
    public static String testString2 = "9080";
    private WebServiceProxy apiService;
    private ProgressDialog mDialog;
    private Context mContext;

    public WebServices(Activity activity, String token) {
        apiService = WebServiceFactory.getInstance(token);
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
    }

    public static boolean IsResponseError(Response<WebResponse<JsonObject>> response) {
        if (response != null && !response.isSuccessful() && response.errorBody() != null) {
            // handle carsResponse.errorBody()
//            int code = response.raw().code();
//            String message = response.raw().message();
//            String errorBody = response.errorBody().toString();
//            String errorMsg = "";
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


    public void webServiceRequestAPI(String requestMethod, String requestData, final IRequestJsonDataCallBack callBack) {

        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        RequestBody bodyRequestData = getRequestBody(okhttp3.MultipartBody.FORM, requestData);

        try {
            if (Helper.isNetworkConnected(mContext, true)) {
                apiService.webServiceRequestAPI(bodyRequestMethod, bodyRequestData).enqueue(new Callback<WebResponse<JsonObject>>() {
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


//    public void verifyCode(String verificationCode, String verify_code, final IRequestJsonDataCallBack callBack) {
//        try {
//            JSONObject params = new JSONObject();
//            params.put("verification_code", verificationCode);
//            params.put("verify_code", verify_code);
//
//            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), params.toString());
//            if (Helper.isNetworkConnected(mContext, true)) {
//                WebServiceFactory.getInstance("").verifyCode("", 0).enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                        dismissDialog();
//                        if (!IsResponseError(response)) {
//                            String errorBody = null;
//                            try {
//                                errorBody = response.errorBody().string();
//                                UIHelper.showShortToastInCenter(mContext, errorBody);
//                                callBack.onError();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            return;
//                        }
//
//                        if (hasValidStatus(response))
//                            callBack.requestDataResponse(response.body());
//                        else {
//                            String message = response.body().has("message") ? response.body().get("message").getAsString() : "";
//                            UIHelper.showShortToastInCenter(mContext, message);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<JsonObject> call, Throwable t) {
//                        UIHelper.showShortToastInCenter(mContext, "Unable to verify, Please check your internet connection.");
//                        dismissDialog();
//                        callBack.onError();
//                    }
//                });
//            } else {
//                dismissDialog();
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    @NonNull
    private RequestBody getRequestBody(MediaType form, String trim) {
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
}
