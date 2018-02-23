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
    @SerializedName("LastFileDateTime")
    @Expose
    private Object lastFileDateTime;
    @SerializedName("LastFileUser")
    @Expose
    private Object lastFileUser;
    @SerializedName("LastFileTerminal")
    @Expose
    private Object lastFileTerminal;
    @SerializedName("Active")
    @Expose
    private Object active;
    private final static long serialVersionUID = -1358548789672497396L;

    public String getHospitalLocation() {
        return hospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
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



    public Object getLastFileDateTime() {
        return lastFileDateTime;
    }

    public void setLastFileDateTime(Object lastFileDateTime) {
        this.lastFileDateTime = lastFileDateTime;
    }

    public Object getLastFileUser() {
        return lastFileUser;
    }

    public void setLastFileUser(Object lastFileUser) {
        this.lastFileUser = lastFileUser;
    }

    public Object getLastFileTerminal() {
        return lastFileTerminal;
    }

    public void setLastFileTerminal(Object lastFileTerminal) {
        this.lastFileTerminal = lastFileTerminal;
    }

    public Object getActive() {
        return active;
    }

    public void setActive(Object active) {
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