package edu.aku.model.wrappers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public class WebResponse<T> {

    @SerializedName("ResponseMessage")
    public String message;

    @SerializedName("ResponseCode")
    public int responseCode;

    @SerializedName("ResponseType")
    public String responseType;

    @SerializedName("Result")
    public T result;

    public boolean isSuccess() {
        return responseCode == 200;
    }
}
