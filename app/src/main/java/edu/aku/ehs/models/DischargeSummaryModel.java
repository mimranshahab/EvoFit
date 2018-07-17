package edu.aku.ehs.models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.ehs.managers.retrofit.GsonFactory;

public class DischargeSummaryModel implements Serializable

        , IsRecordFound {

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


    @SerializedName("VisitID")
    @Expose
    private String visitID;
    @SerializedName("MRNumber")
    @Expose
    private String mRNumber;
    @SerializedName("VisitType")
    @Expose
    private String visitType;
    @SerializedName("PhyID")
    @Expose
    private String phyID;
    @SerializedName("PhyName")
    @Expose
    private String phyName;
    @SerializedName("AdmissionDateTime")
    @Expose
    private String admissionDateTime;
    @SerializedName("SummaryServiceID")
    @Expose
    private String summaryServiceID;
    @SerializedName("SummaryPath")
    @Expose
    private String summaryPath;
    @SerializedName("LastFileDttm")
    @Expose
    private String lastFileDttm;
    @SerializedName("LastFileUser")
    @Expose
    private String lastFileUser;
    @SerializedName("Exists")
    @Expose
    private String exists;
    private final static long serialVersionUID = 146432562002565302L;

    @SerializedName("DischargeDateTime")
    @Expose
    private String dischargeDateTime;

    @SerializedName("DischargeDisposition")
    @Expose
    private String dischargeDisposition;


    @Override
    public String toString() {
        return GsonFactory.getConfiguredGson().toJson(this);
    }

    public String getVisitID() {
        return visitID;
    }

    public void setVisitID(String visitID) {
        this.visitID = visitID;
    }

    public String getMRNumber() {
        return mRNumber;
    }

    public void setMRNumber(String mRNumber) {
        this.mRNumber = mRNumber;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getPhyID() {
        return phyID;
    }

    public void setPhyID(String phyID) {
        this.phyID = phyID;
    }

    public String getPhyName() {
        return phyName;
    }

    public void setPhyName(String phyName) {
        this.phyName = phyName;
    }

    public String getAdmissionDateTime() {
        return admissionDateTime;
    }

    public void setAdmissionDateTime(String admissionDateTime) {
        this.admissionDateTime = admissionDateTime;
    }

    public String getSummaryServiceID() {
        return summaryServiceID;
    }

    public void setSummaryServiceID(String summaryServiceID) {
        this.summaryServiceID = summaryServiceID;
    }

    public String getSummaryPath() {
        return summaryPath;
    }

    public void setSummaryPath(String summaryPath) {
        this.summaryPath = summaryPath;
    }

    public String getLastFileDttm() {
        return lastFileDttm;
    }

    public void setLastFileDttm(String lastFileDttm) {
        this.lastFileDttm = lastFileDttm;
    }

    public String getLastFileUser() {
        return lastFileUser;
    }

    public void setLastFileUser(String lastFileUser) {
        this.lastFileUser = lastFileUser;
    }

    public String getExists() {
        return exists;
    }

    public void setExists(String exists) {
        this.exists = exists;
    }


    public String getDischargeDateTime() {
        return dischargeDateTime;
    }

    public void setDischargeDateTime(String dischargeDateTime) {
        this.dischargeDateTime = dischargeDateTime;
    }

    public String getDischargeDisposition() {
        return dischargeDisposition;
    }

    public void setDischargeDisposition(String dischargeDisposition) {
        this.dischargeDisposition = dischargeDisposition;
    }
}