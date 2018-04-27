package edu.aku.family_hifazat.models.sending_model;

import com.google.gson.annotations.SerializedName;

import edu.aku.family_hifazat.managers.retrofit.GsonFactory;

/**
 * Created by aqsa.sarwar on 1/2/2018.
 */

public class LoginApiModel {


    @SerializedName("CardNumber")
    private String cardNumber;
    @SerializedName("Password")
    private String password;
    @SerializedName("LoginDeviceID")
    private String loginDeviceID ;
    @SerializedName("LoginDeviceType")
    private String loginDeviceType ;
    @SerializedName("MotherName")
    private String motherName;

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getLoginDeviceID() {
        return loginDeviceID;
    }

    public void setLoginDeviceID(String loginDeviceID) {
        this.loginDeviceID = loginDeviceID;
    }

    public String getLoginDeviceType() {
        return loginDeviceType;
    }

    public void setLoginDeviceType(String loginDeviceType) {
        this.loginDeviceType = loginDeviceType;
    }

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
