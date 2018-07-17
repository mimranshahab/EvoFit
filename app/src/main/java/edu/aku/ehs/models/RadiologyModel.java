package edu.aku.ehs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.ehs.managers.retrofit.GsonFactory;

/**
 * Created by hamza.ahmed on 2/7/2018.
 */

public class RadiologyModel implements IsRecordFound {

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
    @SerializedName("VisitLocation")
    private String visitlocation;
    @Expose
    @SerializedName("exists")
    private String exists;
    @Expose
    @SerializedName("Acknowledge")
    private String acknowledge;
    @Expose
    @SerializedName("Status")
    private String status;
    @Expose
    @SerializedName("Exam")
    private String exam;
    @Expose
    @SerializedName("ExamTypeID")
    private String examtypeid;
    @Expose
    @SerializedName("RADLocation")
    private String radlocation;
    @Expose
    @SerializedName("VisitType")
    private String visittype;
    @Expose
    @SerializedName("ExamDate")
    private String examdate;
    @Expose
    @SerializedName("ReportId")
    private String reportid;
    @Expose
    @SerializedName("HasCompanionReport")
    private String hascompanionreport;
    @Expose
    @SerializedName("MPI")
    private String mpi;
    @Expose
    @SerializedName("VisitId")
    private String visitid;
    @Expose
    @SerializedName("AccessionNumber")
    private String accessionnumber;
    @Expose
    @SerializedName("ExamOrderExamNumber")
    private String examorderexamnumber;
    @Expose
    @SerializedName("ExamOrderNumber")
    private String examordernumber;

    @Expose
    @SerializedName("BalanceAmount")
    private String BalanceAmount;

    @Expose
    @SerializedName("BalanceMessage")
    private String BalanceMessage;

    public String getBalanceAmount() {
        return BalanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        BalanceAmount = balanceAmount;
    }

    public String getBalanceMessage() {
        return BalanceMessage;
    }

    public void setBalanceMessage(String balanceMessage) {
        BalanceMessage = balanceMessage;
    }

    public String getVisitlocation() {
        return visitlocation;
    }

    public void setVisitlocation(String visitlocation) {
        this.visitlocation = visitlocation;
    }

    public String getExists() {
        return exists;
    }

    public void setExists(String exists) {
        this.exists = exists;
    }

    public String getAcknowledge() {
        return acknowledge;
    }

    public void setAcknowledge(String acknowledge) {
        this.acknowledge = acknowledge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getExamtypeid() {
        return examtypeid;
    }

    public void setExamtypeid(String examtypeid) {
        this.examtypeid = examtypeid;
    }

    public String getRadlocation() {
        return radlocation;
    }

    public void setRadlocation(String radlocation) {
        this.radlocation = radlocation;
    }

    public String getVisittype() {
        return visittype;
    }

    public void setVisittype(String visittype) {
        this.visittype = visittype;
    }

    public String getExamdate() {
        return examdate;
    }

    public void setExamdate(String examdate) {
        this.examdate = examdate;
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public String getHascompanionreport() {
        return hascompanionreport;
    }

    public void setHascompanionreport(String hascompanionreport) {
        this.hascompanionreport = hascompanionreport;
    }

    public String getMpi() {
        return mpi;
    }

    public void setMpi(String mpi) {
        this.mpi = mpi;
    }

    public String getVisitid() {
        return visitid;
    }

    public void setVisitid(String visitid) {
        this.visitid = visitid;
    }

    public String getAccessionnumber() {
        return accessionnumber;
    }

    public String getAccessionnumberwithComma() {
        return "\"" + accessionnumber + "\"";

    }

    public void setAccessionnumber(String accessionnumber) {
        this.accessionnumber = accessionnumber;
    }

    public String getExamorderexamnumber() {
        return examorderexamnumber;
    }

    public void setExamorderexamnumber(String examorderexamnumber) {
        this.examorderexamnumber = examorderexamnumber;
    }

    public String getExamordernumber() {
        return examordernumber;
    }

    public void setExamordernumber(String examordernumber) {
        this.examordernumber = examordernumber;
    }


    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }

}
