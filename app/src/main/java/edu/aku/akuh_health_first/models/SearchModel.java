package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;

/**
 * Created by aqsa.sarwar on 2/19/2018.
 */

public class SearchModel implements IsRecordFound {

    @Expose
    @SerializedName("RecordMessage")
    private String recordmessage;
    @Expose
    @SerializedName("RecordFound")
    private String recordfound;

    @Override
    public boolean isRecordFound() {
        return getRecordfound().equals("true");
    }

    public String getRecordmessage() {
        return recordmessage;
    }

    public void setRecordmessage(String recordmessage) {
        this.recordmessage = recordmessage;
    }

    public String getRecordfound() {
        return recordfound;
    }

    public void setRecordfound(String recordfound) {
        this.recordfound = recordfound;
    }

    @Expose
    @SerializedName("VisitID")
    private String VisitID;
    @Expose
    @SerializedName("MRNumber")
    private String MRNumber;

    public String getVisitID() {
        return VisitID;
    }

    public void setVisitID(String VisitID) {
        this.VisitID = VisitID;
    }

    public String getMRNumber() {
        return MRNumber;
    }

    public void setMRNumber(String MRNumber) {
        this.MRNumber = MRNumber;
    }

    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }

}
