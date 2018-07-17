package edu.aku.ehs.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LaboratoryDetailModel

        implements Serializable, IsRecordFound {
    public String getOrdered() {
        return ordered;
    }

    public void setOrdered(String ordered) {
        this.ordered = ordered;
    }

    @SerializedName("Ordered")
    @Expose
    private String ordered;
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


    @SerializedName("lstLaboratorySpecimenResults")
    @Expose
    private List<LstLaboratorySpecimenResults> lstLaboratorySpecimenResults;
    @SerializedName("lstLaboratoryMicSpecimenOrderedProc")
    @Expose
    private List<LstLaboratoryMicspecimenOrderedProc> lstLaboratoryMicSpecimenOrderedProc;
    @SerializedName("SpecimenID")
    @Expose
    private String specimenID;
    @SerializedName("OrderedTests")
    @Expose
    private String orderedTests;
    @SerializedName("CollectedBy")
    @Expose
    private String collectedBy;
    @SerializedName("ReceivedBy")
    @Expose
    private String receivedBy;
    @SerializedName("ForwardedBy")
    @Expose
    private String forwardedBy;
    @SerializedName("SignoutBy")
    @Expose
    private String signoutBy;
    @SerializedName("SpecimenComments")
    @Expose
    private String specimenComments;
    @SerializedName("SignoutDttm")
    @Expose
    private String signoutDttm;
    @SerializedName("StatusID")
    @Expose
    private String statusID;
    @SerializedName("SpecimenSectionID")
    @Expose
    private String specimenSectionID;
    @SerializedName("ReportDispatchUser")
    @Expose
    private String reportDispatchUser;
    @SerializedName("PerformedAtSTATLocation")
    @Expose
    private String performedAtSTATLocation;
    @SerializedName("SortDttm")
    @Expose
    private String sortDttm;
    @SerializedName("OrderedTestDisplayList")
    @Expose
    private String orderedTestDisplayList;
    @SerializedName("CollectionDttm")
    @Expose
    private String collectionDttm;
    @SerializedName("ReceivedOnFloorBy")
    @Expose
    private String receivedOnFloorBy;
    @SerializedName("ReceivedOnFloorDttm")
    @Expose
    private String receivedOnFloorDttm;
    @SerializedName("ForwardedDttm")
    @Expose
    private String forwardedDttm;
    @SerializedName("SpecimenCancelComments")
    @Expose
    private String specimenCancelComments;
    @SerializedName("SourceMnemonic")
    @Expose
    private String sourceMnemonic;
    @SerializedName("SourceDescription")
    @Expose
    private String sourceDescription;
    @SerializedName("PatientUnitNumber")
    @Expose
    private String patientUnitNumber;
    @SerializedName("VisitLocationID")
    @Expose
    private String visitLocationID;
    @SerializedName("ReferringDoctorID")
    @Expose
    private String referringDoctorID;
    @SerializedName("SpecimenType")
    @Expose
    private String specimenType;
    @SerializedName("isExternalReport")
    @Expose
    private Boolean isExternalReport;
    @SerializedName("ExternalPath")
    @Expose
    private String externalPath;
    @SerializedName("ExternalFile")
    @Expose
    private String externalFile;
    @SerializedName("SpecimenNumber")
    @Expose
    private String specimenNumber;
    @SerializedName("LastFileDateTime")
    @Expose
    private Object lastFileDateTime;
    @SerializedName("LastFileUser")
    @Expose
    private Object lastFileUser;
    @SerializedName("LastFileTerminal")
    @Expose
    private Object lastFileTerminal;
    @SerializedName("Active")
    @Expose
    private Object active;

    private final static long serialVersionUID = -792760789047274044L;

    public List<LstLaboratorySpecimenResults> getLstLaboratorySpecimenResults() {
        return lstLaboratorySpecimenResults;
    }

    public void setLstLaboratorySpecimenResults(List<LstLaboratorySpecimenResults> lstLaboratorySpecimenResults) {
        this.lstLaboratorySpecimenResults = lstLaboratorySpecimenResults;
    }

    public List<LstLaboratoryMicspecimenOrderedProc> getLstLaboratoryMicSpecimenOrderedProc() {
        return lstLaboratoryMicSpecimenOrderedProc;
    }

    public void setLstLaboratoryMicSpecimenOrderedProc(List<LstLaboratoryMicspecimenOrderedProc> lstLaboratoryMicSpecimenOrderedProc) {
        this.lstLaboratoryMicSpecimenOrderedProc = lstLaboratoryMicSpecimenOrderedProc;
    }

    public String getSpecimenID() {
        return specimenID;
    }

    public void setSpecimenID(String specimenID) {
        this.specimenID = specimenID;
    }

    public String getOrderedTests() {
        return orderedTests;
    }

    public void setOrderedTests(String orderedTests) {
        this.orderedTests = orderedTests;
    }

    public String getCollectedBy() {
        return collectedBy;
    }

    public void setCollectedBy(String collectedBy) {
        this.collectedBy = collectedBy;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getForwardedBy() {
        return forwardedBy;
    }

    public void setForwardedBy(String forwardedBy) {
        this.forwardedBy = forwardedBy;
    }

    public String getSignoutBy() {
        return signoutBy;
    }

    public void setSignoutBy(String signoutBy) {
        this.signoutBy = signoutBy;
    }

    public String getSpecimenComments() {
        return specimenComments;
    }

    public void setSpecimenComments(String specimenComments) {
        this.specimenComments = specimenComments;
    }

    public String getSignoutDttm() {
        return signoutDttm;
    }

    public void setSignoutDttm(String signoutDttm) {
        this.signoutDttm = signoutDttm;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getSpecimenSectionID() {
        return specimenSectionID;
    }

    public void setSpecimenSectionID(String specimenSectionID) {
        this.specimenSectionID = specimenSectionID;
    }

    public String getReportDispatchUser() {
        return reportDispatchUser;
    }

    public void setReportDispatchUser(String reportDispatchUser) {
        this.reportDispatchUser = reportDispatchUser;
    }

    public String getPerformedAtSTATLocation() {
        return performedAtSTATLocation;
    }

    public void setPerformedAtSTATLocation(String performedAtSTATLocation) {
        this.performedAtSTATLocation = performedAtSTATLocation;
    }

    public String getSortDttm() {
        return sortDttm;
    }

    public void setSortDttm(String sortDttm) {
        this.sortDttm = sortDttm;
    }

    public String getOrderedTestDisplayList() {
        return orderedTestDisplayList;
    }

    public void setOrderedTestDisplayList(String orderedTestDisplayList) {
        this.orderedTestDisplayList = orderedTestDisplayList;
    }

    public String getCollectionDttm() {
        return collectionDttm;
    }

    public void setCollectionDttm(String collectionDttm) {
        this.collectionDttm = collectionDttm;
    }

    public String getReceivedOnFloorBy() {
        return receivedOnFloorBy;
    }

    public void setReceivedOnFloorBy(String receivedOnFloorBy) {
        this.receivedOnFloorBy = receivedOnFloorBy;
    }

    public String getReceivedOnFloorDttm() {
        return receivedOnFloorDttm;
    }

    public void setReceivedOnFloorDttm(String receivedOnFloorDttm) {
        this.receivedOnFloorDttm = receivedOnFloorDttm;
    }

    public String getForwardedDttm() {
        return forwardedDttm;
    }

    public void setForwardedDttm(String forwardedDttm) {
        this.forwardedDttm = forwardedDttm;
    }

    public String getSpecimenCancelComments() {
        return specimenCancelComments;
    }

    public void setSpecimenCancelComments(String specimenCancelComments) {
        this.specimenCancelComments = specimenCancelComments;
    }

    public String getSourceMnemonic() {
        return sourceMnemonic;
    }

    public void setSourceMnemonic(String sourceMnemonic) {
        this.sourceMnemonic = sourceMnemonic;
    }

    public String getPatientUnitNumber() {
        return patientUnitNumber;
    }

    public void setPatientUnitNumber(String patientUnitNumber) {
        this.patientUnitNumber = patientUnitNumber;
    }

    public String getVisitLocationID() {
        return visitLocationID;
    }

    public void setVisitLocationID(String visitLocationID) {
        this.visitLocationID = visitLocationID;
    }

    public String getReferringDoctorID() {
        return referringDoctorID;
    }

    public void setReferringDoctorID(String referringDoctorID) {
        this.referringDoctorID = referringDoctorID;
    }

    public String getSpecimenType() {
        return specimenType;
    }

    public void setSpecimenType(String specimenType) {
        this.specimenType = specimenType;
    }

    public boolean getIsExternalReport() {
        return isExternalReport;
    }

    public void setIsExternalReport(Boolean isExternalReport) {
        this.isExternalReport = isExternalReport;
    }

    public String getExternalPath() {
        return externalPath;
    }

    public void setExternalPath(String externalPath) {
        this.externalPath = externalPath;
    }

    public String getSpecimenNumber() {
        return specimenNumber;
    }

    public void setSpecimenNumber(String specimenNumber) {
        this.specimenNumber = specimenNumber;
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

    public void setLastFileUser(Object lastFileUser) {
        this.lastFileUser = lastFileUser;
    }

    public Object getLastFileTerminal() {
        return lastFileTerminal;
    }

    public void setLastFileTerminal(Object lastFileTerminal) {
        this.lastFileTerminal = lastFileTerminal;
    }

    public Object getActive() {
        return active;
    }

    public void setActive(Object active) {
        this.active = active;
    }

    public String getExternalFile() {
        return externalFile;
    }

    public void setExternalFile(String externalFile) {
        this.externalFile = externalFile;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }

    public Boolean getExternalReport() {
        return isExternalReport;
    }

    public void setExternalReport(Boolean externalReport) {
        isExternalReport = externalReport;
    }



}
