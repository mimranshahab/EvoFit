package edu.aku.akuh_health_first.models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.managers.DateManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;

public class ImmunizationModel implements IsRecordFound {

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

    @SerializedName("MRN")
    @Expose
    private String mRN;
    @SerializedName("strVisitDate")
    @Expose
    private Object strVisitDate;
    @SerializedName("VisitDate")
    @Expose
    private Object visitDate;
    @SerializedName("VisitID")
    @Expose
    private String visitID;
    @SerializedName("VaccineID")
    @Expose
    private String vaccineID;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("AdmissionTime")
    @Expose
    private Object admissionTime;
    @SerializedName("RouteID")
    @Expose
    private String routeID;
    @SerializedName("SiteOfInjectionID")
    @Expose
    private Object siteOfInjectionID;
    @SerializedName("DoseType")
    @Expose
    private Object doseType;
    @SerializedName("ActualDose")
    @Expose
    private Object actualDose;
    @SerializedName("DoseUnit")
    @Expose
    private Object doseUnit;
    @SerializedName("HospitalLocation")
    @Expose
    private String hospitalLocation;
    @SerializedName("IRN")
    @Expose
    private String iRN;
    @SerializedName("VersionNo")
    @Expose
    private String versionNo;
    @SerializedName("ScheduleID")
    @Expose
    private String scheduleID;
    @SerializedName("VaccinePlanDate")
    @Expose
    private String vaccinePlanDate;
    @SerializedName("VaccinationDate")
    @Expose
    private String vaccinationDate;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("Remarks")
    @Expose
    private Object remarks;
    @SerializedName("Source")
    @Expose
    private String source;

    @SerializedName("VaccinationStatus")
    @Expose
    private String vaccinationStatus;
    @SerializedName("LastFileDateTime")
    @Expose
    private Object lastFileDateTime;
    @SerializedName("LastFileUser")
    @Expose
    private String lastFileUser;
    @SerializedName("LastFileTerminal")
    @Expose
    private String lastFileTerminal;
    @SerializedName("Active")
    @Expose
    private String active;
    private final static long serialVersionUID = -2088648535189790337L;

    public Object getMRN() {
        return mRN;
    }

    public void setMRN(String mRN) {
        this.mRN = mRN;
    }

    public Object getStrVisitDate() {
        return strVisitDate;
    }

    public void setStrVisitDate(Object strVisitDate) {
        this.strVisitDate = strVisitDate;
    }

    public Object getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Object visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitID() {
        return visitID;
    }

    public void setVisitID(String visitID) {
        this.visitID = visitID;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Object admissionTime) {
        this.admissionTime = admissionTime;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public Object getSiteOfInjectionID() {
        return siteOfInjectionID;
    }

    public void setSiteOfInjectionID(Object siteOfInjectionID) {
        this.siteOfInjectionID = siteOfInjectionID;
    }

    public Object getDoseType() {
        return doseType;
    }

    public void setDoseType(Object doseType) {
        this.doseType = doseType;
    }

    public Object getActualDose() {
        return actualDose;
    }

    public void setActualDose(Object actualDose) {
        this.actualDose = actualDose;
    }

    public Object getDoseUnit() {
        return doseUnit;
    }

    public void setDoseUnit(Object doseUnit) {
        this.doseUnit = doseUnit;
    }

    public String getHospitalLocation() {
        return hospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
    }

    public String getIRN() {
        return iRN;
    }

    public void setIRN(String iRN) {
        this.iRN = iRN;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getVaccinePlanDate() {
        return vaccinePlanDate;
    }

    public void setVaccinePlanDate(String vaccinePlanDate) {
        this.vaccinePlanDate = vaccinePlanDate;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Object getLastFileDateTime() {
        return lastFileDateTime;
    }

    public void setLastFileDateTime(Object lastFileDateTime) {
        this.lastFileDateTime = lastFileDateTime;
    }

    public Object getLastFileUser() {
        return lastFileUser;
    }

    public void setLastFileUser(String lastFileUser) {
        this.lastFileUser = lastFileUser;
    }

    public Object getLastFileTerminal() {
        return lastFileTerminal;
    }

    public void setLastFileTerminal(String lastFileTerminal) {
        this.lastFileTerminal = lastFileTerminal;
    }

    public Object getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setVaccinationStatus(String vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    public String getVaccinationStatus() {

//        if (getVaccinationDate() == null || getVaccinationDate().isEmpty()) {
//            if (DateManager.getTimeInMillis(DateManager.sdfDateInputImmunization, getVaccinationDate()) < DateManager.getCurrentMillis())
//                return AppConstants.schedule;
//            else {
//                return AppConstants.due;
//            }
//        } else {
//            return AppConstants.vaccinated;
//        }

        return vaccinationStatus;
    }

    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }
}