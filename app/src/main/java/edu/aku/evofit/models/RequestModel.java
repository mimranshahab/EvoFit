package edu.aku.ehs.models;

import com.google.gson.annotations.SerializedName;

import edu.aku.ehs.managers.retrofit.GsonFactory;

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
