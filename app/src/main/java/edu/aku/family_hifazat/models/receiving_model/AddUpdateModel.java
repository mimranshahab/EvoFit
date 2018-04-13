package edu.aku.family_hifazat.models.receiving_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hamza.ahmed on 2/13/2018.
 */

public class AddUpdateModel {


    @Expose
    @SerializedName("RecordID")
    private String recordid;
    @Expose
    @SerializedName("Message")
    private String message;
    @Expose
    @SerializedName("Title")
    private String title;
    @Expose
    @SerializedName("StrStatus")
    private String strstatus;
    @Expose
    @SerializedName("Status")
    private boolean status;

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
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

    public String getStrstatus() {
        return strstatus;
    }

    public void setStrstatus(String strstatus) {
        this.strstatus = strstatus;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
