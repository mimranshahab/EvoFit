package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hamza.ahmed on 2/12/2018.
 */

public class EndoscopyModel {

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
    @SerializedName("ProcedureDttm")
    private String proceduredttm;
    @Expose
    @SerializedName("ReportId")
    private String reportid;

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

    public String getProceduredttm() {
        return proceduredttm;
    }

    public void setProceduredttm(String proceduredttm) {
        this.proceduredttm = proceduredttm;
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }
}
