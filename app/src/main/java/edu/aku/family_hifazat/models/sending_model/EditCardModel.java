package edu.aku.family_hifazat.models.sending_model;

import com.google.gson.annotations.SerializedName;

import edu.aku.family_hifazat.managers.retrofit.GsonFactory;

/**
 * Created by aqsa.sarwar on 1/2/2018.
 */

public class EditCardModel {


    @SerializedName("CardNumber")
    private String cardNumber;
    @SerializedName("MRNumber")
    private String mrnNumber;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;


//    public EditCardModel(String cardNumber, String mrnNumber) {
//        this.cardNumber = cardNumber;
//        this.mrnNumber = mrnNumber;
//    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMrnNumber() {
        return mrnNumber;
    }

    public void setMrnNumber(String mrnNumber) {
        this.mrnNumber = mrnNumber;
    }

    @Override
    public String toString() {
       return GsonFactory.getConfiguredGson().toJson(this);
     }
}
