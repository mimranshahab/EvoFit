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

    @SerializedName("PatientVisitDateTime")
    @Expose
    private String patientVisitDateTime;
    @SerializedName("PatientVisitLocation")
    @Expose
    private String patientVisitLocation;
    @SerializedName("PatientVisitDoctor")
    @Expose
    private String patientVisitDoctor;
    @SerializedName("PatientVisitAdmissionID")
    @Expose
    private Integer patientVisitAdmissionID;
    @SerializedName("PatientVisitType")
    @Expose
    private String patientVisitType;
    @SerializedName("PatientVisitDoctorName")
    @Expose
    private String patientVisitDoctorName;
    @SerializedName("PatientVisitFinancialClass")
    @Expose
    private String patientVisitFinancialClass;
    @SerializedName("PatientVisitStatus")
    @Expose
    private String patientVisitStatus;
    @SerializedName("PatientVisitService")
    @Expose
    private String patientVisitService;
    @SerializedName("PatientVisitDischargeDisposition")
    @Expose
    private String patientVisitDischargeDisposition;
    @SerializedName("PatientVisitVisitRoom")
    @Expose
    private String patientVisitVisitRoom;
    @SerializedName("PatientVisitDischargeDate")
    @Expose
    private String patientVisitDischargeDate;
    @SerializedName("PatientVisitBed")
    @Expose
    private String patientVisitBed;
    @SerializedName("PatientVisitAccommodation")
    @Expose
    private String patientVisitAccommodation;
    @SerializedName("PatientVisitHospitalLocation")
    @Expose
    private String patientVisitHospitalLocation;
    @SerializedName("PatientName")
    @Expose
    private String patientName;
    @SerializedName("PatientUnitNumber")
    @Expose
    private String patientUnitNumber;
    @SerializedName("PatientVisitServiceDescription")
    @Expose
    private String patientVisitServiceDescription;


    private final static long serialVersionUID = 8893062797223876305L;

    public String getPatientVisitDateTime() {
        return patientVisitDateTime;
    }

    public void setPatientVisitDateTime(String patientVisitDateTime) {
        this.patientVisitDateTime = patientVisitDateTime;
    }

    public String getPatientVisitLocation() {
        return patientVisitLocation;
    }

    public void setPatientVisitLocation(String patientVisitLocation) {
        this.patientVisitLocation = patientVisitLocation;
    }

    public String getPatientVisitDoctor() {
        return patientVisitDoctor;
    }

    public void setPatientVisitDoctor(String patientVisitDoctor) {
        this.patientVisitDoctor = patientVisitDoctor;
    }

    public Integer getPatientVisitAdmissionID() {
        return patientVisitAdmissionID;
    }

    public void setPatientVisitAdmissionID(Integer patientVisitAdmissionID) {
        this.patientVisitAdmissionID = patientVisitAdmissionID;
    }

    public String getPatientVisitType() {
        return patientVisitType;
    }

    public void setPatientVisitType(String patientVisitType) {
        this.patientVisitType = patientVisitType;
    }

    public String getPatientVisitDoctorName() {
        return patientVisitDoctorName;
    }

    public void setPatientVisitDoctorName(String patientVisitDoctorName) {
        this.patientVisitDoctorName = patientVisitDoctorName;
    }

    public String getPatientVisitFinancialClass() {
        return patientVisitFinancialClass;
    }

    public void setPatientVisitFinancialClass(String patientVisitFinancialClass) {
        this.patientVisitFinancialClass = patientVisitFinancialClass;
    }

    public String getPatientVisitStatus() {
        return patientVisitStatus;
    }

    public void setPatientVisitStatus(String patientVisitStatus) {
        this.patientVisitStatus = patientVisitStatus;
    }

    public String getPatientVisitService() {
        return patientVisitService;
    }

    public void setPatientVisitService(String patientVisitService) {
        this.patientVisitService = patientVisitService;
    }

    public String getPatientVisitDischargeDisposition() {
        return patientVisitDischargeDisposition;
    }

    public void setPatientVisitDischargeDisposition(String patientVisitDischargeDisposition) {
        this.patientVisitDischargeDisposition = patientVisitDischargeDisposition;
    }

    public String getPatientVisitVisitRoom() {
        return patientVisitVisitRoom;
    }

    public void setPatientVisitVisitRoom(String patientVisitVisitRoom) {
        this.patientVisitVisitRoom = patientVisitVisitRoom;
    }

    public String getPatientVisitDischargeDate() {
        return patientVisitDischargeDate;
    }

    public void setPatientVisitDischargeDate(String patientVisitDischargeDate) {
        this.patientVisitDischargeDate = patientVisitDischargeDate;
    }

    public String getPatientVisitBed() {
        return patientVisitBed;
    }

    public void setPatientVisitBed(String patientVisitBed) {
        this.patientVisitBed = patientVisitBed;
    }

    public String getPatientVisitAccommodation() {
        return patientVisitAccommodation;
    }

    public void setPatientVisitAccommodation(String patientVisitAccommodation) {
        this.patientVisitAccommodation = patientVisitAccommodation;
    }

    public String getPatientVisitHospitalLocation() {
        return patientVisitHospitalLocation;
    }

    public void setPatientVisitHospitalLocation(String patientVisitHospitalLocation) {
        this.patientVisitHospitalLocation = patientVisitHospitalLocation;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientUnitNumber() {
        return patientUnitNumber;
    }

    public void setPatientUnitNumber(String patientUnitNumber) {
        this.patientUnitNumber = patientUnitNumber;
    }

    public String getPatientVisitServiceDescription() {
        return patientVisitServiceDescription;
    }

    public void setPatientVisitServiceDescription(String patientVisitServiceDescription) {
        this.patientVisitServiceDescription = patientVisitServiceDescription;
    }



}
