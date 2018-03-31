package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by aqsa.sarwar on 2/19/2018.
 */

public class RequestModel {

    @SerializedName("MRNumber")
    private String MRNumber;

    public String getMRNumber() {
        return MRNumber;
    }

    public void setMRNumber(String MRNumber) {
        this.MRNumber = MRNumber;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.CardNumber = cardNumber;
    }

    @SerializedName("CardNumber")
    private String CardNumber;


    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }

}
