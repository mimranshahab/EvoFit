package edu.aku.managers.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonObject;

import java.lang.String;

import edu.aku.constatnts.WebServiceConstants;
import edu.aku.enums.FileType;
import edu.aku.helperclasses.Helper;
import edu.aku.helperclasses.ui.helper.UIHelper;

import java.io.File;
import java.io.IOException;

import edu.aku.models.wrappers.WebResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static edu.aku.managers.FileManager.getExtension;

/**
 * Created by hamzakhan on 6/30/2017.
 */

public class WebServices {
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

    public static boolean hasValidStatusForStringResult(Response<WebResponse<String>> response) {
        if (response != null && response.body() != null) {
            return response.body().isSuccess();
        }
        return false;
    }

    public void webServiceRequestFileAPI(String requestMethod, String filePath, FileType fileType, final IRequestJsonDataCallBackForStringResult callBack) {

        RequestBody bodyRequestMethod = getRequestBody(okhttp3.MultipartBody.FORM, requestMethod);
        MultipartBody.Part bodyRequestData;
        if (filePath == null) {
            dismissDialog();
            UIHelper.showShortToastInCenter(mContext, "File path is empty.");
            return;
        }

        File file = new File(filePath);

        bodyRequestData =
                MultipartBody.Part.createFormData(WebServiceConstants.PARAMS_REQUEST_DATA, file.getName(),
                        RequestBody.create(MediaType.parse(fileType.canonicalForm() + "/" +  getExtension(file.getName())), file));
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

    public interface IRequestJsonDataCallBackForStringResult {
        void requestDataResponse(WebResponse<String> webResponse);

        void onError();
    }



}
