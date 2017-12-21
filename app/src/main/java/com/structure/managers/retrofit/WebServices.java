package com.structure.managers.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.JsonObject;
import com.structure.helperclasses.Helper;
import com.structure.helperclasses.ui.helper.UIHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by muhammadmuzammil on 6/30/2017.
 */

public class WebServices {
    public static String testString = "2428";
    public static String testString2 = "9080";
    private WebServiceProxy apiService;
    private ProgressDialog mDialog;
    private Context mContext;

    public WebServices(Activity activity) {
        apiService = WebServiceFactory.getInstance("");
        mContext = activity;
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("Loading.....");
        mDialog.setTitle("Please Wait");
        mDialog.setCancelable(false);

        if (!((Activity) mContext).isFinishing())
            mDialog.show();
    }

    public WebServices(Context mContext) {
        this.mContext = mContext;
    }

    public static boolean IsResponseError(Response<JsonObject> response) {
        if (response != null && !response.isSuccessful() && response.errorBody() != null) {
            // handle carsResponse.errorBody()
            int code = response.raw().code();
            String message = response.raw().message();
            String errorBody = response.errorBody().toString();
            String errorMsg = "";

            return false;
        }
        return true;
    }

    public static boolean hasValidStatus(Response<JsonObject> response) {
        if (response != null && response.body() != null && response.body().has("status")) {
            int status = response.body().get("status").getAsInt();
            if (status == 200)
                return true;

            return false;
        }
        return false;
    }

    public void verifyCode(String verificationCode, String verify_code, final IRequestJsonDataCallBack callBack) {
        try {
            JSONObject params = new JSONObject();
            params.put("verification_code", verificationCode);
            params.put("verify_code", verify_code);

            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), params.toString());
            if (Helper.isNetworkConnected(mContext, true)) {
                WebServiceFactory.getInstance("").verifyCode("", 0).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        dismissDialog();
                        if (!IsResponseError(response)) {
                            String errorBody = null;
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
                            String message = response.body().has("message") ? response.body().get("message").getAsString() : "";
                            UIHelper.showShortToastInCenter(mContext, message);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        UIHelper.showShortToastInCenter(mContext, "Unable to verify, Please check your internet connection.");
                        dismissDialog();
                        callBack.onError();
                    }
                });
            } else {
                dismissDialog();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void dismissDialog() {
        mDialog.dismiss();
    }

    public interface IRequestJsonDataCallBack {
        void requestDataResponse(JsonObject jsonObject);

        void onError();
    }
}
