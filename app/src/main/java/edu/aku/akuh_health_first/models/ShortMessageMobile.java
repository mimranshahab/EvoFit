package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShortMessageMobile {
    @Expose
    @SerializedName("MoreMessage")
    private String moremessage;
    @Expose
    @SerializedName("Message")
    private String message;
    @Expose
    @SerializedName("Title")
    private String title;

    public String getMoremessage() {
        return moremessage;
    }

    public void setMoremessage(String moremessage) {
        this.moremessage = moremessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
