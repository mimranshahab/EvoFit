package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hamza.ahmed on 2/8/2018.
 */

public class RadiologyDetailModel implements IsRecordFound {

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
    @SerializedName("ADDENDUMINITIATOR")
    private String addenduminitiator;
    @Expose
    @SerializedName("ADDENDUMRADIOLOGIST")
    private String addendumradiologist;
    @Expose
    @SerializedName("ADDENDUMDTTM")
    private String addendumdttm;
    @Expose
    @SerializedName("EDITEDBY")
    private String editedby;
    @Expose
    @SerializedName("EDITEDDATETIME")
    private String editeddatetime;
    @Expose
    @SerializedName("TRANSCRIBEBY")
    private String transcribeby;
    @Expose
    @SerializedName("RESIDENTID")
    private String residentid;
    @Expose
    @SerializedName("ReportRADIOLOGISTID")
    private String reportradiologistid;
    @Expose
    @SerializedName("REPORTDATE")
    private String reportdate;
    @Expose
    @SerializedName("REPORTID")
    private String reportid;
    @Expose
    @SerializedName("EOELASTFILEUSER")
    private String eoelastfileuser;
    @Expose
    @SerializedName("CANCELDATETIME")
    private String canceldatetime;
    @Expose
    @SerializedName("CANCELUSER")
    private String canceluser;
    @Expose
    @SerializedName("CANCELREASON")
    private String cancelreason;
    @Expose
    @SerializedName("FILMACKNOWLEDGETYPE")
    private String filmacknowledgetype;
    @Expose
    @SerializedName("FILMACKNOWLEDGEUSER")
    private String filmacknowledgeuser;
    @Expose
    @SerializedName("FILMACKNOWLEDGEDATETIME")
    private String filmacknowledgedatetime;
    @Expose
    @SerializedName("EOERADIOLOGISTID")
    private String eoeradiologistid;
    @Expose
    @SerializedName("TECHNOLOGISTDATETIME")
    private String technologistdatetime;
    @Expose
    @SerializedName("TECHNOLOGISTID")
    private String technologistid;
    @Expose
    @SerializedName("PROCEDURESTARTDATETIME")
    private String procedurestartdatetime;
    @Expose
    @SerializedName("EXAMDATE")
    private String examdate;
    @Expose
    @SerializedName("EXAMSTATUSID")
    private String examstatusid;
    @Expose
    @SerializedName("EXAM")
    private String exam;
    @Expose
    @SerializedName("EXAMID")
    private String examid;
    @Expose
    @SerializedName("EXAMTYPEID")
    private String examtypeid;
    @Expose
    @SerializedName("EXAMORDEREXAMNUMBER")
    private String examorderexamnumber;
    @Expose
    @SerializedName("EXAMORDERNUMBER")
    private String examordernumber;
    @Expose
    @SerializedName("ORDERSTATUSID")
    private String orderstatusid;
    @Expose
    @SerializedName("PASSPORTNO")
    private String passportno;
    @Expose
    @SerializedName("ORDERDATE")
    private String orderdate;
    @Expose
    @SerializedName("RADLOCATIONID")
    private String radlocationid;
    @Expose
    @SerializedName("DOCTORID")
    private String doctorid;
    @Expose
    @SerializedName("CLINICALHISTORYPROVIDED")
    private String clinicalhistoryprovided;
    @Expose
    @SerializedName("REPORTTEXT")
    private String reporttext;

    public String getAddenduminitiator() {
        return addenduminitiator;
    }

    public void setAddenduminitiator(String addenduminitiator) {
        this.addenduminitiator = addenduminitiator;
    }

    public String getAddendumradiologist() {
        return addendumradiologist;
    }

    public void setAddendumradiologist(String addendumradiologist) {
        this.addendumradiologist = addendumradiologist;
    }

    public String getAddendumdttm() {
        return addendumdttm;
    }

    public void setAddendumdttm(String addendumdttm) {
        this.addendumdttm = addendumdttm;
    }

    public String getEditedby() {
        return editedby;
    }

    public void setEditedby(String editedby) {
        this.editedby = editedby;
    }

    public String getEditeddatetime() {
        return editeddatetime;
    }

    public void setEditeddatetime(String editeddatetime) {
        this.editeddatetime = editeddatetime;
    }

    public String getTranscribeby() {
        return transcribeby;
    }

    public void setTranscribeby(String transcribeby) {
        this.transcribeby = transcribeby;
    }

    public String getResidentid() {
        return residentid;
    }

    public void setResidentid(String residentid) {
        this.residentid = residentid;
    }

    public String getReportradiologistid() {
        return reportradiologistid;
    }

    public void setReportradiologistid(String reportradiologistid) {
        this.reportradiologistid = reportradiologistid;
    }

    public String getReportdate() {
        return reportdate;
    }

    public void setReportdate(String reportdate) {
        this.reportdate = reportdate;
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public String getEoelastfileuser() {
        return eoelastfileuser;
    }

    public void setEoelastfileuser(String eoelastfileuser) {
        this.eoelastfileuser = eoelastfileuser;
    }

    public String getCanceldatetime() {
        return canceldatetime;
    }

    public void setCanceldatetime(String canceldatetime) {
        this.canceldatetime = canceldatetime;
    }

    public String getCanceluser() {
        return canceluser;
    }

    public void setCanceluser(String canceluser) {
        this.canceluser = canceluser;
    }

    public String getCancelreason() {
        return cancelreason;
    }

    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }

    public String getFilmacknowledgetype() {
        return filmacknowledgetype;
    }

    public void setFilmacknowledgetype(String filmacknowledgetype) {
        this.filmacknowledgetype = filmacknowledgetype;
    }

    public String getFilmacknowledgeuser() {
        return filmacknowledgeuser;
    }

    public void setFilmacknowledgeuser(String filmacknowledgeuser) {
        this.filmacknowledgeuser = filmacknowledgeuser;
    }

    public String getFilmacknowledgedatetime() {
        return filmacknowledgedatetime;
    }

    public void setFilmacknowledgedatetime(String filmacknowledgedatetime) {
        this.filmacknowledgedatetime = filmacknowledgedatetime;
    }

    public String getEoeradiologistid() {
        return eoeradiologistid;
    }

    public void setEoeradiologistid(String eoeradiologistid) {
        this.eoeradiologistid = eoeradiologistid;
    }

    public String getTechnologistdatetime() {
        return technologistdatetime;
    }

    public void setTechnologistdatetime(String technologistdatetime) {
        this.technologistdatetime = technologistdatetime;
    }

    public String getTechnologistid() {
        return technologistid;
    }

    public void setTechnologistid(String technologistid) {
        this.technologistid = technologistid;
    }

    public String getProcedurestartdatetime() {
        return procedurestartdatetime;
    }

    public void setProcedurestartdatetime(String procedurestartdatetime) {
        this.procedurestartdatetime = procedurestartdatetime;
    }

    public String getExamdate() {
        return examdate;
    }

    public void setExamdate(String examdate) {
        this.examdate = examdate;
    }

    public String getExamstatusid() {
        return examstatusid;
    }

    public void setExamstatusid(String examstatusid) {
        this.examstatusid = examstatusid;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getExamtypeid() {
        return examtypeid;
    }

    public void setExamtypeid(String examtypeid) {
        this.examtypeid = examtypeid;
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

    public String getOrderstatusid() {
        return orderstatusid;
    }

    public void setOrderstatusid(String orderstatusid) {
        this.orderstatusid = orderstatusid;
    }

    public String getPassportno() {
        return passportno;
    }

    public void setPassportno(String passportno) {
        this.passportno = passportno;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getRadlocationid() {
        return radlocationid;
    }

    public void setRadlocationid(String radlocationid) {
        this.radlocationid = radlocationid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getClinicalhistoryprovided() {
        return clinicalhistoryprovided;
    }

    public void setClinicalhistoryprovided(String clinicalhistoryprovided) {
        this.clinicalhistoryprovided = clinicalhistoryprovided;
    }

    public String getReporttext() {
        return reporttext;
    }

    public void setReporttext(String reporttext) {
        this.reporttext = reporttext;
    }
}
