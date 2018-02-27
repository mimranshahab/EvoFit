package edu.aku.akuh_health_first.models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;

public class CardioModel
        implements IsRecordFound {

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



    @SerializedName("HospitalLocation")
    @Expose
    private String hospitalLocation;
    @SerializedName("MRN")
    @Expose
    private String mRN;
    @SerializedName("RequestServiceDateTime")
    @Expose
    private String requestServiceDateTime;
    @SerializedName("RequestNumber")
    @Expose
    private String requestNumber;
    @SerializedName("AdmissionNumber")
    @Expose
    private String admissionNumber;
    @SerializedName("Service")
    @Expose
    private String service;
    @SerializedName("ReportID")
    @Expose
    private String reportID;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Reportable")
    @Expose
    private String reportable;
    @SerializedName("HasCompanionReport")
    @Expose
    private String hasCompanionReport;
    @SerializedName("GraphAvailable")
    @Expose
    private String graphAvailable;
    @SerializedName("AttachmentAvailable")
    @Expose
    private String attachmentAvailable;
    @SerializedName("DetailReportID")
    @Expose
    private String detailReportID;
    @SerializedName("DetailGraphID")
    @Expose
    private String detailGraphID;
    @SerializedName("either_rorg_exist")
    @Expose
    private Boolean eitherRorgExist;
    @SerializedName("exists")
    @Expose
    private String exists;
    @SerializedName("IsAngiogram")
    @Expose
    private String isAngiogram;
    @SerializedName("LastFileDateTime")
    @Expose
    private String lastFileDateTime;
    @SerializedName("LastFileUser")
    @Expose
    private String lastFileUser;
    @SerializedName("LastFileTerminal")
    @Expose
    private String lastFileTerminal;
    @SerializedName("Active")
    @Expose
    private String active;

    private final static long serialVersionUID = 2241373079708123524L;

    public String getHospitalLocation() {
        return hospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
    }

    public String getMRN() {
        return mRN;
    }

    public void setMRN(String mRN) {
        this.mRN = mRN;
    }

    public String getRequestServiceDateTime() {
        return requestServiceDateTime;
    }

    public void setRequestServiceDateTime(String requestServiceDateTime) {
        this.requestServiceDateTime = requestServiceDateTime;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportable() {
        return reportable;
    }

    public void setReportable(String reportable) {
        this.reportable = reportable;
    }

    public String getHasCompanionReport() {
        return hasCompanionReport;
    }

    public void setHasCompanionReport(String hasCompanionReport) {
        this.hasCompanionReport = hasCompanionReport;
    }

    public String getGraphAvailable() {
        return graphAvailable;
    }

    public void setGraphAvailable(String graphAvailable) {
        this.graphAvailable = graphAvailable;
    }

    public String getAttachmentAvailable() {
        return attachmentAvailable;
    }

    public void setAttachmentAvailable(String attachmentAvailable) {
        this.attachmentAvailable = attachmentAvailable;
    }

    public String getDetailReportID() {
        return detailReportID;
    }

    public void setDetailReportID(String detailReportID) {
        this.detailReportID = detailReportID;
    }

    public String getDetailGraphID() {
        return detailGraphID;
    }

    public void setDetailGraphID(String detailGraphID) {
        this.detailGraphID = detailGraphID;
    }

    public Boolean getEitherRorgExist() {
        return eitherRorgExist;
    }

    public void setEitherRorgExist(Boolean eitherRorgExist) {
        this.eitherRorgExist = eitherRorgExist;
    }

    public String getExists() {
        return exists;
    }

    public void setExists(String exists) {
        this.exists = exists;
    }

    public String getIsAngiogram() {
        return isAngiogram;
    }

    public void setIsAngiogram(String isAngiogram) {
        this.isAngiogram = isAngiogram;
    }

    public String getLastFileDateTime() {
        return lastFileDateTime;
    }

    public void setLastFileDateTime(String lastFileDateTime) {
        this.lastFileDateTime = lastFileDateTime;
    }

    public String getLastFileUser() {
        return lastFileUser;
    }

    public void setLastFileUser(String lastFileUser) {
        this.lastFileUser = lastFileUser;
    }

    public String getLastFileTerminal() {
        return lastFileTerminal;
    }

    public void setLastFileTerminal(String lastFileTerminal) {
        this.lastFileTerminal = lastFileTerminal;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    

    public String tempCardioOb = "{\n" +
        "            \"HospitalLocation\": \"Stadium Road\",\n" +
                "            \"RequestServiceDateTime\": \"2017-01-05T10:42:51\",\n" +
                "            \"RequestNumber\": \"1025362\",\n" +
                "            \"AdmissionNumber\": \"104979464\",\n" +
                "            \"Service\": \"ECHO - ECHO\",\n" +
                "            \"ReportID\": \"2017:ECH100\",\n" +
                "        \n" +
                "            \"DetailReportID\": \"20170105_CPS_2017ECH100.pdf\"\n" +
                "        }";

    @Override
    public String toString() {
        return GsonFactory.getConfiguredGson().toJson(this);
    }

    public String tempCardioOb() {
        return tempCardioOb;
    }

}