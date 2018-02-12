package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 1/19/2018.
 */

public class TimelineModel {

    @Expose
    @SerializedName("PatientVisitHospitalLocation")
    private String patientvisithospitallocation;
    @Expose
    @SerializedName("PatientVisitExists")
    private String patientvisitexists;
    @Expose
    @SerializedName("PatientVisitAccommodation")
    private String patientvisitaccommodation;
    @Expose
    @SerializedName("PatientVisitBed")
    private String patientvisitbed;
    @Expose
    @SerializedName("PatientVisitDischargeDate")
    private String patientvisitdischargedate;
    @Expose
    @SerializedName("PatientVisitVisitRoom")
    private String patientvisitvisitroom;
    @Expose
    @SerializedName("PatientVisitDischargeDisposition")
    private String patientvisitdischargedisposition;
    @Expose
    @SerializedName("PatientVisitService")
    private String patientvisitservice;
    @Expose
    @SerializedName("PatientVisitStatus")
    private String patientvisitstatus;
    @Expose
    @SerializedName("PatientVisitFinancialClass")
    private String patientvisitfinancialclass;
    @Expose
    @SerializedName("PatientVisitDoctorName")
    private String patientvisitdoctorname;
    @Expose
    @SerializedName("PatientVisitType")
    private String patientvisittype;
    @Expose
    @SerializedName("PatientVisitAdmissionID")
    private int patientvisitadmissionid;
    @Expose
    @SerializedName("PatientVisitDoctor")
    private String patientvisitdoctor;
    @Expose
    @SerializedName("PatientVisitLocation")
    private String patientvisitlocation;
    @Expose
    @SerializedName("PatientVisitDateTime")
    private String patientvisitdatetime;

    public String getPatientvisithospitallocation() {
        return patientvisithospitallocation;
    }

    public void setPatientvisithospitallocation(String patientvisithospitallocation) {
        this.patientvisithospitallocation = patientvisithospitallocation;
    }

    public String getPatientvisitexists() {
        return patientvisitexists;
    }

    public void setPatientvisitexists(String patientvisitexists) {
        this.patientvisitexists = patientvisitexists;
    }

    public String getPatientvisitaccommodation() {
        return patientvisitaccommodation;
    }

    public void setPatientvisitaccommodation(String patientvisitaccommodation) {
        this.patientvisitaccommodation = patientvisitaccommodation;
    }

    public String getPatientvisitbed() {
        return patientvisitbed;
    }

    public void setPatientvisitbed(String patientvisitbed) {
        this.patientvisitbed = patientvisitbed;
    }

    public String getPatientvisitdischargedate() {
        return patientvisitdischargedate;
    }

    public void setPatientvisitdischargedate(String patientvisitdischargedate) {
        this.patientvisitdischargedate = patientvisitdischargedate;
    }

    public String getPatientvisitvisitroom() {
        return patientvisitvisitroom;
    }

    public void setPatientvisitvisitroom(String patientvisitvisitroom) {
        this.patientvisitvisitroom = patientvisitvisitroom;
    }

    public String getPatientvisitdischargedisposition() {
        return patientvisitdischargedisposition;
    }

    public void setPatientvisitdischargedisposition(String patientvisitdischargedisposition) {
        this.patientvisitdischargedisposition = patientvisitdischargedisposition;
    }

    public String getPatientvisitservice() {
        return patientvisitservice;
    }

    public void setPatientvisitservice(String patientvisitservice) {
        this.patientvisitservice = patientvisitservice;
    }

    public String getPatientvisitstatus() {
        return patientvisitstatus;
    }

    public void setPatientvisitstatus(String patientvisitstatus) {
        this.patientvisitstatus = patientvisitstatus;
    }

    public String getPatientvisitfinancialclass() {
        return patientvisitfinancialclass;
    }

    public void setPatientvisitfinancialclass(String patientvisitfinancialclass) {
        this.patientvisitfinancialclass = patientvisitfinancialclass;
    }

    public String getPatientvisitdoctorname() {
        return patientvisitdoctorname;
    }

    public void setPatientvisitdoctorname(String patientvisitdoctorname) {
        this.patientvisitdoctorname = patientvisitdoctorname;
    }

    public String getPatientvisittype() {
        return patientvisittype;
    }

    public void setPatientvisittype(String patientvisittype) {
        this.patientvisittype = patientvisittype;
    }

    public int getPatientvisitadmissionid() {
        return patientvisitadmissionid;
    }

    public void setPatientvisitadmissionid(int patientvisitadmissionid) {
        this.patientvisitadmissionid = patientvisitadmissionid;
    }

    public String getPatientvisitdoctor() {
        return patientvisitdoctor;
    }

    public void setPatientvisitdoctor(String patientvisitdoctor) {
        this.patientvisitdoctor = patientvisitdoctor;
    }

    public String getPatientvisitlocation() {
        return patientvisitlocation;
    }

    public void setPatientvisitlocation(String patientvisitlocation) {
        this.patientvisitlocation = patientvisitlocation;
    }

    public String getPatientvisitdatetime() {
        return patientvisitdatetime;
    }

    public void setPatientvisitdatetime(String patientvisitdatetime) {
        this.patientvisitdatetime = patientvisitdatetime;
    }
}
