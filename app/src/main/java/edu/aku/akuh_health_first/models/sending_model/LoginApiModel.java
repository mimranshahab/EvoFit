package edu.aku.akuh_health_first.models.sending_model;

import com.google.gson.annotations.SerializedName;

import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;

/**
 * Created by aqsa.sarwar on 1/2/2018.
 */

public class LoginApiModel {


    @SerializedName("CardNumber")
    private String cardNumber;
    @SerializedName("Password")
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;


    public LoginApiModel(String userid, String password) {
        this.cardNumber = userid;
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
       return GsonFactory.getConfiguredGson().toJson(this);
     }
}
