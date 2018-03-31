package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;

/**
 * Created by aqsa.sarwar on 2/8/2018.
 */

public class PacsDescriptionModel implements IsRecordFound {

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


    private String patientAge;

    private String patientDOB;

    private String patientGender;

    private String patientMRN;

    private String patient_Name;

    private String studyTitle;

    private String studyDataDateTime;

    private String studyDataCount;

    private List<String> studyDataString;

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(String patientDOB) {
        this.patientDOB = patientDOB;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientMRN() {
        return patientMRN;
    }

    public void setPatientMRN(String patientMRN) {
        this.patientMRN = patientMRN;
    }

    public String getPatient_Name() {
        return patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        this.patient_Name = patient_Name;
    }

    public String getStudyTitle() {
        return studyTitle;
    }

    public void setStudyTitle(String studyTitle) {
        this.studyTitle = studyTitle;
    }

    public String getStudyDataDateTime() {
        return studyDataDateTime;
    }

    public void setStudyDataDateTime(String studyDataDateTime) {
        this.studyDataDateTime = studyDataDateTime;
    }

    public String getStudyDataCount() {
        return studyDataCount;
    }

    public void setStudyDataCount(String studyDataCount) {
        this.studyDataCount = studyDataCount;
    }

    public List<String> getStudyDataString() {
        return studyDataString;
    }

    public void setStudyDataString(List<String> studyDataString) {
        this.studyDataString = studyDataString;
    }


    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }
}
