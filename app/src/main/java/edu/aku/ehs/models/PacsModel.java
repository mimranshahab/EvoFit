package edu.aku.ehs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import edu.aku.ehs.managers.retrofit.GsonFactory;

/**
 * Created by aqsa.sarwar on 2/8/2018.
 */

public class PacsModel implements IsRecordFound {

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
    @SerializedName("patientAge")
    private List<String> patientAge;
    @Expose
    @SerializedName("patientDOB")
    private List<String> patientDOB;
    @Expose
    @SerializedName("patientGender")
    private List<String> patientGender;
    @Expose
    @SerializedName("patientMRN")
    private List<String> patientMRN;
    @Expose
    @SerializedName("patient_Name")
    private List<String> patient_Name;
    @Expose
    @SerializedName("studyTitle")
    private List<String> studyTitle;
    @Expose
    @SerializedName("studyDataDateTime")
    private List<String> studyDataDateTime;
    @Expose
    @SerializedName("studyDataCount")
    private List<String> studyDataCount;
    @Expose
    @SerializedName("studyDataString")
    private List<List<String>> studyDataString;

    public List<String> getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(List<String> patientAge) {
        this.patientAge = patientAge;
    }

    public List<String> getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(List<String> patientDOB) {
        this.patientDOB = patientDOB;
    }

    public List<String> getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(List<String> patientGender) {
        this.patientGender = patientGender;
    }

    public List<String> getPatientMRN() {
        return patientMRN;
    }

    public void setPatientMRN(List<String> patientMRN) {
        this.patientMRN = patientMRN;
    }

    public List<String> getPatient_Name() {
        return patient_Name;
    }

    public void setPatient_Name(List<String> patient_Name) {
        this.patient_Name = patient_Name;
    }

    public List<String> getStudyTitle() {
        return studyTitle;
    }

    public void setStudyTitle(List<String> studyTitle) {
        this.studyTitle = studyTitle;
    }

    public List<String> getStudyDataDateTime() {
        return studyDataDateTime;
    }

    public void setStudyDataDateTime(List<String> studyDataDateTime) {
        this.studyDataDateTime = studyDataDateTime;
    }

    public List<String> getStudyDataCount() {
        return studyDataCount;
    }

    public void setStudyDataCount(List<String> studyDataCount) {
        this.studyDataCount = studyDataCount;
    }

    public List<List<String>> getStudyDataString() {
        return studyDataString;
    }

    public void setStudyDataString(List<List<String>> studyDataString) {
        this.studyDataString = studyDataString;
    }


    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }
}
