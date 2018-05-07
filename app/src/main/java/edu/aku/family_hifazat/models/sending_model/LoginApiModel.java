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

    @SerializedName("AccessLog")
    private RegisteredDeviceModel accessLog;

    @SerializedName("Device")
    private RegisteredDeviceModel device;

    @SerializedName("RegisteredDevice")
    private RegisteredDeviceModel registeredDevice;

    @SerializedName("MotherName")
    private String motherName;


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

    public RegisteredDeviceModel getAccessLog() {
        return accessLog;
    }

    public void setAccessLog(RegisteredDeviceModel accessLog) {
        this.accessLog = accessLog;
    }

    public RegisteredDeviceModel getDevice() {
        return device;
    }

    public void setDevice(RegisteredDeviceModel device) {
        this.device = device;
    }

    public RegisteredDeviceModel getRegisteredDevice() {
        return registeredDevice;
    }

    public void setRegisteredDevice(RegisteredDeviceModel registeredDevice) {
        this.registeredDevice = registeredDevice;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    @Override
    public String toString() {
        return GsonFactory.getConfiguredGson().toJson(this);
    }
}
