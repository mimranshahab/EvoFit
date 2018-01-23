package edu.aku.akuh_health_first.models.wrappers;

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

    @SerializedName("LaboratoryModel")
    public T result;

    public boolean isSuccess() {
        return responseCode == 200;
    }
}
