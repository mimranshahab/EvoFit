package edu.aku.model.wrappers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public class WebResponse<T> {

    @SerializedName("Message")
    public String message;

    @SerializedName("Response")
    public String response;

    @SerializedName("Result")
    public T result ;

    public boolean isSuccess() {
        return response.equals("2000");
    }
}
