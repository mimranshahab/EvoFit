package edu.aku.family_hifazat.models.sending_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.family_hifazat.managers.retrofit.GsonFactory;

/**
 * Created by hamza.ahmed on 4/25/2018.
 */

public class RegisteredDeviceModel {

    @Expose
    @SerializedName("DeviceModel")
    private String devicemodel;
    @Expose
    @SerializedName("DeviceManufacturer")
    private String devicemanufacturer;
    @Expose
    @SerializedName("DeviceToken")
    private String devicetoken;
    @Expose
    @SerializedName("DeviceScreenSize")
    private String devicescreensize;
    @Expose
    @SerializedName("DeviceOSVersion")
    private String deviceosversion;
    @Expose
    @SerializedName("DeviceOS")
    private String deviceos;
    @Expose
    @SerializedName("DeviceType")
    private String devicetype;
    @Expose
    @SerializedName("DeviceID")
    private String deviceid;
    @Expose
    @SerializedName("RegCardNo")
    private String regcardno;
    @Expose
    @SerializedName("RegCardNumber")
    private String regcardnumber;
    @Expose
    @SerializedName("LoginCode")
    private String loginCode;

    public RegisteredDeviceModel() {
    }

    public RegisteredDeviceModel(String devicemodel, String devicemanufacturer, String devicetoken, String devicescreensize, String deviceosversion, String deviceos, String devicetype, String deviceid, String regcardno) {
        this.devicemodel = devicemodel;
        this.devicemanufacturer = devicemanufacturer;
        this.devicetoken = devicetoken;
        this.devicescreensize = devicescreensize;
        this.deviceosversion = deviceosversion;
        this.deviceos = deviceos;
        this.devicetype = devicetype;
        this.deviceid = deviceid;
        this.regcardno = regcardno;
    }


    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getDevicemodel() {
        return devicemodel;
    }

    public void setDevicemodel(String devicemodel) {
        this.devicemodel = devicemodel;
    }

    public String getDevicemanufacturer() {
        return devicemanufacturer;
    }

    public void setDevicemanufacturer(String devicemanufacturer) {
        this.devicemanufacturer = devicemanufacturer;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getDevicescreensize() {
        return devicescreensize;
    }

    public void setDevicescreensize(String devicescreensize) {
        this.devicescreensize = devicescreensize;
    }

    public String getDeviceosversion() {
        return deviceosversion;
    }

    public void setDeviceosversion(String deviceosversion) {
        this.deviceosversion = deviceosversion;
    }

    public String getDeviceos() {
        return deviceos;
    }

    public void setDeviceos(String deviceos) {
        this.deviceos = deviceos;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getRegcardno() {
        return regcardno;
    }

    public void setRegcardno(String regcardno) {
        this.regcardno = regcardno;
        this.regcardnumber = regcardno;
    }

    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }
}
