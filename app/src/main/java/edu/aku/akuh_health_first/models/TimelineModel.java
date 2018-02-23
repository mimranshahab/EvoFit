package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 1/19/2018.
 */

public class TimelineModel implements IsRecordFound {

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
    @SerializedName("PatientVisitHospitalLocation")
    private String PatientVisitHospitalLocation;
    @Expose
    @SerializedName("PatientVisitExists")
    private String PatientVisitExists;
    @Expose
    @SerializedName("PatientVisitAccommodation")
    private String PatientVisitAccommodation;
    @Expose
    @SerializedName("PatientVisitBed")
    private String PatientVisitBed;
    @Expose
    @SerializedName("PatientVisitDischargeDate")
    private String PatientVisitDischargeDate;
    @Expose
    @SerializedName("PatientVisitVisitRoom")
    private String PatientVisitVisitRoom;
    @Expose
    @SerializedName("PatientVisitDischargeDisposition")
    private String PatientVisitDischargeDisposition;
    @Expose
    @SerializedName("PatientVisitService")
    private String PatientVisitService;
    @Expose
    @SerializedName("PatientVisitStatus")
    private String PatientVisitStatus;
    @Expose
    @SerializedName("PatientVisitFinancialClass")
    private String PatientVisitFinancialClass;
    @Expose
    @SerializedName("PatientVisitDoctorName")
    private String PatientVisitDoctorName;
    @Expose
    @SerializedName("PatientVisitType")
    private String PatientVisitType;
    @Expose
    @SerializedName("PatientVisitAdmissionID")
    private int PatientVisitAdmissionID;
    @Expose
    @SerializedName("PatientVisitDoctor")
    private String PatientVisitDoctor;
    @Expose
    @SerializedName("PatientVisitLocation")
    private String PatientVisitLocation;
    @Expose
    @SerializedName("PatientVisitDateTime")
    private String PatientVisitDateTime;

    public String getPatientVisitHospitalLocation() {
        return PatientVisitHospitalLocation;
    }

    public void setPatientVisitHospitalLocation(String PatientVisitHospitalLocation) {
        this.PatientVisitHospitalLocation = PatientVisitHospitalLocation;
    }

    public String getPatientVisitExists() {
        return PatientVisitExists;
    }

    public void setPatientVisitExists(String PatientVisitExists) {
        this.PatientVisitExists = PatientVisitExists;
    }

    public String getPatientVisitAccommodation() {
        return PatientVisitAccommodation;
    }

    public void setPatientVisitAccommodation(String PatientVisitAccommodation) {
        this.PatientVisitAccommodation = PatientVisitAccommodation;
    }

    public String getPatientVisitBed() {
        return PatientVisitBed;
    }

    public void setPatientVisitBed(String PatientVisitBed) {
        this.PatientVisitBed = PatientVisitBed;
    }

    public String getPatientVisitDischargeDate() {
        return PatientVisitDischargeDate;
    }

    public void setPatientVisitDischargeDate(String PatientVisitDischargeDate) {
        this.PatientVisitDischargeDate = PatientVisitDischargeDate;
    }

    public String getPatientVisitVisitRoom() {
        return PatientVisitVisitRoom;
    }

    public void setPatientVisitVisitRoom(String PatientVisitVisitRoom) {
        this.PatientVisitVisitRoom = PatientVisitVisitRoom;
    }

    public String getPatientVisitDischargeDisposition() {
        return PatientVisitDischargeDisposition;
    }

    public void setPatientVisitDischargeDisposition(String PatientVisitDischargeDisposition) {
        this.PatientVisitDischargeDisposition = PatientVisitDischargeDisposition;
    }

    public String getPatientVisitService() {
        return PatientVisitService;
    }

    public void setPatientVisitService(String PatientVisitService) {
        this.PatientVisitService = PatientVisitService;
    }

    public String getPatientVisitStatus() {
        return PatientVisitStatus;
    }

    public void setPatientVisitStatus(String PatientVisitStatus) {
        this.PatientVisitStatus = PatientVisitStatus;
    }

    public String getPatientVisitFinancialClass() {
        return PatientVisitFinancialClass;
    }

    public void setPatientVisitFinancialClass(String PatientVisitFinancialClass) {
        this.PatientVisitFinancialClass = PatientVisitFinancialClass;
    }

    public String getPatientVisitDoctorName() {
        return PatientVisitDoctorName;
    }

    public void setPatientVisitDoctorName(String PatientVisitDoctorName) {
        this.PatientVisitDoctorName = PatientVisitDoctorName;
    }

    public String getPatientVisitType() {
        return PatientVisitType;
    }

    public void setPatientVisitType(String PatientVisitType) {
        this.PatientVisitType = PatientVisitType;
    }

    public int getPatientVisitAdmissionID() {
        return PatientVisitAdmissionID;
    }

    public void setPatientVisitAdmissionID(int PatientVisitAdmissionID) {
        this.PatientVisitAdmissionID = PatientVisitAdmissionID;
    }

    public String getPatientVisitDoctor() {
        return PatientVisitDoctor;
    }

    public void setPatientVisitDoctor(String PatientVisitDoctor) {
        this.PatientVisitDoctor = PatientVisitDoctor;
    }

    public String getPatientVisitLocation() {
        return PatientVisitLocation;
    }

    public void setPatientVisitLocation(String PatientVisitLocation) {
        this.PatientVisitLocation = PatientVisitLocation;
    }

    public String getPatientVisitDateTime() {
        return PatientVisitDateTime;
    }

    public void setPatientVisitDateTime(String PatientVisitDateTime) {
        this.PatientVisitDateTime = PatientVisitDateTime;
    }


}
