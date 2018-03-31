package edu.aku.akuh_health_first.models;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;

public class PacsView implements IsRecordFound {

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

    private final List<String> PACS_Accessions;
    @SerializedName("studyDataString")
    @Expose
    private List<List<String>> studyDataString = null;
    @SerializedName("studyDataCount")
    @Expose
    private List<String> studyDataCount = null;
    @SerializedName("studyDataDateTime")
    @Expose
    private List<String> studyDataDateTime = null;
    @SerializedName("studyTitle")
    @Expose
    private List<String> studyTitle = null;
    @SerializedName("patient_Name")
    @Expose
    private List<String> patientName = null;
    @SerializedName("patientMRN")
    @Expose
    private List<String> patientMRN = null;
    @SerializedName("patientGender")
    @Expose
    private List<String> patientGender = null;
    @SerializedName("patientDOB")
    @Expose
    private List<String> patientDOB = null;
    @SerializedName("patientAge")
    @Expose
    private List<String> patientAge = null;
    private final static long serialVersionUID = 6256764024113369012L;

    public List<List<String>> getStudyDataString() {
        return studyDataString;
    }

    public void setStudyDataString(List<List<String>> studyDataString) {
        this.studyDataString = studyDataString;
    }

    public List<String> getStudyDataCount() {
        return studyDataCount;
    }

    public void setStudyDataCount(List<String> studyDataCount) {
        this.studyDataCount = studyDataCount;
    }

    public List<String> getStudyDataDateTime() {
        return studyDataDateTime;
    }

    public void setStudyDataDateTime(List<String> studyDataDateTime) {
        this.studyDataDateTime = studyDataDateTime;
    }

    public List<String> getStudyTitle() {
        return studyTitle;
    }

    public void setStudyTitle(List<String> studyTitle) {
        this.studyTitle = studyTitle;
    }

    public List<String> getPatientName() {
        return patientName;
    }

    public void setPatientName(List<String> patientName) {
        this.patientName = patientName;
    }

    public List<String> getPatientMRN() {
        return patientMRN;
    }

    public void setPatientMRN(List<String> patientMRN) {
        this.patientMRN = patientMRN;
    }

    public List<String> getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(List<String> patientGender) {
        this.patientGender = patientGender;
    }

    public List<String> getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(List<String> patientDOB) {
        this.patientDOB = patientDOB;
    }

    public List<String> getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(List<String> patientAge) {
        this.patientAge = patientAge;
    }

    @Override
    public String toString() {
        return GsonFactory.getConfiguredGson().toJson(this);
    }


    public PacsView(List<String> PACS_Accessions) {
        this.PACS_Accessions = PACS_Accessions;
    }
}