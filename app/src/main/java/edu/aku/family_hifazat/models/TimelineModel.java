package edu.aku.family_hifazat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 1/19/2018.
 */

public class TimelineModel {


    @Expose
    @SerializedName("RecordMessage")
    private String recordMessage;
    @Expose
    @SerializedName("RecordFound")
    private String recordFound;
    @Expose
    @SerializedName("PatientVisitDischargeDispositionDesc")
    private String PatientVisitDischargeDispositionDesc;
    @Expose
    @SerializedName("PatientClinicApptNatureID")
    private String PatientClinicApptNatureID;
    @Expose
    @SerializedName("PatientClinicApptNatureDesc")
    private String PatientClinicApptNatureDesc;
    @Expose
    @SerializedName("PatientLengthofStay")
    private String PatientLengthofStay;
    @Expose
    @SerializedName("PatientVisitServiceDescription")
    private String PatientVisitServiceDescription;
    @Expose
    @SerializedName("PatientUnitNumber")
    private String PatientUnitNumber;
    @Expose
    @SerializedName("PatientName")
    private String PatientName;
    @Expose
    @SerializedName("PatientVisitHospitalLocation")
    private String PatientVisitHospitalLocation;
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


    public String getRecordMessage() {
        return recordMessage;
    }

    public void setRecordMessage(String recordMessage) {
        this.recordMessage = recordMessage;
    }

    public String getRecordFound() {
        return recordFound;
    }

    public void setRecordFound(String recordFound) {
        this.recordFound = recordFound;
    }

    public String getPatientVisitDischargeDispositionDesc() {
        return PatientVisitDischargeDispositionDesc;
    }

    public void setPatientVisitDischargeDispositionDesc(String patientVisitDischargeDispositionDesc) {
        PatientVisitDischargeDispositionDesc = patientVisitDischargeDispositionDesc;
    }

    public String getPatientClinicApptNatureID() {
        return PatientClinicApptNatureID;
    }

    public void setPatientClinicApptNatureID(String patientClinicApptNatureID) {
        PatientClinicApptNatureID = patientClinicApptNatureID;
    }

    public String getPatientClinicApptNatureDesc() {
        return PatientClinicApptNatureDesc;
    }

    public void setPatientClinicApptNatureDesc(String patientClinicApptNatureDesc) {
        PatientClinicApptNatureDesc = patientClinicApptNatureDesc;
    }

    public String getPatientLengthofStay() {
        return PatientLengthofStay;
    }

    public void setPatientLengthofStay(String patientLengthofStay) {
        PatientLengthofStay = patientLengthofStay;
    }

    public String getPatientVisitServiceDescription() {
        return PatientVisitServiceDescription;
    }

    public void setPatientVisitServiceDescription(String patientVisitServiceDescription) {
        PatientVisitServiceDescription = patientVisitServiceDescription;
    }

    public String getPatientUnitNumber() {
        return PatientUnitNumber;
    }

    public void setPatientUnitNumber(String patientUnitNumber) {
        PatientUnitNumber = patientUnitNumber;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientVisitHospitalLocation() {
        return PatientVisitHospitalLocation;
    }

    public void setPatientVisitHospitalLocation(String patientVisitHospitalLocation) {
        PatientVisitHospitalLocation = patientVisitHospitalLocation;
    }

    public String getPatientVisitAccommodation() {
        return PatientVisitAccommodation;
    }

    public void setPatientVisitAccommodation(String patientVisitAccommodation) {
        PatientVisitAccommodation = patientVisitAccommodation;
    }

    public String getPatientVisitBed() {
        return PatientVisitBed;
    }

    public void setPatientVisitBed(String patientVisitBed) {
        PatientVisitBed = patientVisitBed;
    }

    public String getPatientVisitDischargeDate() {
        return PatientVisitDischargeDate;
    }

    public void setPatientVisitDischargeDate(String patientVisitDischargeDate) {
        PatientVisitDischargeDate = patientVisitDischargeDate;
    }

    public String getPatientVisitVisitRoom() {
        return PatientVisitVisitRoom;
    }

    public void setPatientVisitVisitRoom(String patientVisitVisitRoom) {
        PatientVisitVisitRoom = patientVisitVisitRoom;
    }

    public String getPatientVisitDischargeDisposition() {
        return PatientVisitDischargeDisposition;
    }

    public void setPatientVisitDischargeDisposition(String patientVisitDischargeDisposition) {
        PatientVisitDischargeDisposition = patientVisitDischargeDisposition;
    }

    public String getPatientVisitService() {
        return PatientVisitService;
    }

    public void setPatientVisitService(String patientVisitService) {
        PatientVisitService = patientVisitService;
    }

    public String getPatientVisitStatus() {
        return PatientVisitStatus;
    }

    public void setPatientVisitStatus(String patientVisitStatus) {
        PatientVisitStatus = patientVisitStatus;
    }

    public String getPatientVisitFinancialClass() {
        return PatientVisitFinancialClass;
    }

    public void setPatientVisitFinancialClass(String patientVisitFinancialClass) {
        PatientVisitFinancialClass = patientVisitFinancialClass;
    }

    public String getPatientVisitDoctorName() {
        return PatientVisitDoctorName;
    }

    public void setPatientVisitDoctorName(String patientVisitDoctorName) {
        PatientVisitDoctorName = patientVisitDoctorName;
    }

    public String getPatientVisitType() {
        return PatientVisitType;
    }

    public void setPatientVisitType(String patientVisitType) {
        PatientVisitType = patientVisitType;
    }

    public int getPatientVisitAdmissionID() {
        return PatientVisitAdmissionID;
    }

    public void setPatientVisitAdmissionID(int patientVisitAdmissionID) {
        PatientVisitAdmissionID = patientVisitAdmissionID;
    }

    public String getPatientVisitDoctor() {
        return PatientVisitDoctor;
    }

    public void setPatientVisitDoctor(String patientVisitDoctor) {
        PatientVisitDoctor = patientVisitDoctor;
    }

    public String getPatientVisitLocation() {
        return PatientVisitLocation;
    }

    public void setPatientVisitLocation(String patientVisitLocation) {
        PatientVisitLocation = patientVisitLocation;
    }

    public String getPatientVisitDateTime() {
        return PatientVisitDateTime;
    }

    public void setPatientVisitDateTime(String patientVisitDateTime) {
        PatientVisitDateTime = patientVisitDateTime;
    }
}
