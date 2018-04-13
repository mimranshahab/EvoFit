package edu.aku.family_hifazat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hamza.ahmed on 2/12/2018.
 */

public class EndoscopyModel implements IsRecordFound {

    @Expose
    @SerializedName("RecordMessage")
    private String recordmessage;
    @Expose
    @SerializedName("RecordFound")
    private String recordfound;


    @Expose
    @SerializedName("Active")
    private String active;
    @Expose
    @SerializedName("LastFileTerminal")
    private String lastfileterminal;
    @Expose
    @SerializedName("LastFileUser")
    private String lastfileuser;
    @Expose
    @SerializedName("LastFileDateTime")
    private String lastfiledatetime;
    @Expose
    @SerializedName("ReportPath")
    private String reportpath;
    @Expose
    @SerializedName("PhysicianName")
    private String physicianname;
    @Expose
    @SerializedName("PhysicianID")
    private String physicianid;
    @Expose
    @SerializedName("VisitID")
    private String visitid;
    @Expose
    @SerializedName("MRNumber")
    private String mrnumber;
    @Expose
    @SerializedName("ReportTypeId")
    private String reporttypeid;
    @Expose
    @SerializedName("Procedures")
    private String procedures;
    @Expose
    @SerializedName("ProcedureDateTime")
    private String proceduredatetime;
    @Expose
    @SerializedName("ReportId")
    private String reportid;

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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getLastfileterminal() {
        return lastfileterminal;
    }

    public void setLastfileterminal(String lastfileterminal) {
        this.lastfileterminal = lastfileterminal;
    }

    public String getLastfileuser() {
        return lastfileuser;
    }

    public void setLastfileuser(String lastfileuser) {
        this.lastfileuser = lastfileuser;
    }

    public String getLastfiledatetime() {
        return lastfiledatetime;
    }

    public void setLastfiledatetime(String lastfiledatetime) {
        this.lastfiledatetime = lastfiledatetime;
    }

    public String getReportpath() {
        return reportpath;
    }

    public void setReportpath(String reportpath) {
        this.reportpath = reportpath;
    }

    public String getPhysicianname() {
        return physicianname;
    }

    public void setPhysicianname(String physicianname) {
        this.physicianname = physicianname;
    }

    public String getPhysicianid() {
        return physicianid;
    }

    public void setPhysicianid(String physicianid) {
        this.physicianid = physicianid;
    }

    public String getVisitid() {
        return visitid;
    }

    public void setVisitid(String visitid) {
        this.visitid = visitid;
    }

    public String getMrnumber() {
        return mrnumber;
    }

    public void setMrnumber(String mrnumber) {
        this.mrnumber = mrnumber;
    }

    public String getReporttypeid() {
        return reporttypeid;
    }

    public void setReporttypeid(String reporttypeid) {
        this.reporttypeid = reporttypeid;
    }

    public String getProcedures() {
        return procedures;
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
    }

    public String getProceduredatetime() {
        return proceduredatetime;
    }

    public void setProceduredatetime(String proceduredatetime) {
        this.proceduredatetime = proceduredatetime;
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    @Override
    public boolean isRecordFound() {
        return getRecordfound().equals("true");
    }
}
