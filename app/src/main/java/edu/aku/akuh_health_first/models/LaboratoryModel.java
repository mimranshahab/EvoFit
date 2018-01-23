package edu.aku.akuh_health_first.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaboratoryModel implements Serializable
{

@SerializedName("sOrderedTestParentId")
@Expose
private Object sOrderedTestParentId;
@SerializedName("sOrderedTestChildId")
@Expose
private Object sOrderedTestChildId;
@SerializedName("sOrderedTestParentMnemonic")
@Expose
private Object sOrderedTestParentMnemonic;
@SerializedName("sOrderedTestChildMnemonic")
@Expose
private Object sOrderedTestChildMnemonic;
@SerializedName("bChildren")
@Expose
private Boolean bChildren;
@SerializedName("dTestResult")
@Expose
private Integer dTestResult;
@SerializedName("sTestResultDttm")
@Expose
private Object sTestResultDttm;
@SerializedName("sTestResultDttmPlot")
@Expose
private Object sTestResultDttmPlot;
@SerializedName("Abnormal")
@Expose
private Object abnormal;
@SerializedName("Comment")
@Expose
private Object comment;
@SerializedName("sIsNumericResult")
@Expose
private Object sIsNumericResult;
@SerializedName("sResultGroup")
@Expose
private Object sResultGroup;
@SerializedName("sGenderID")
@Expose
private Object sGenderID;
@SerializedName("sAgeID")
@Expose
private Object sAgeID;
@SerializedName("dNormalLow")
@Expose
private Integer dNormalLow;
@SerializedName("dNormalHigh")
@Expose
private Integer dNormalHigh;
@SerializedName("dPanicLow")
@Expose
private Integer dPanicLow;
@SerializedName("dPanicHigh")
@Expose
private Integer dPanicHigh;
@SerializedName("dNormal")
@Expose
private Integer dNormal;
@SerializedName("SpecimenNumber")
@Expose
private String specimenNumber;
@SerializedName("RequestNumber")
@Expose
private String requestNumber;
@SerializedName("SpecimenID")
@Expose
private String specimenID;
@SerializedName("SpecimenSectionID")
@Expose
private String specimenSectionID;
@SerializedName("StatusID")
@Expose
private String statusID;
@SerializedName("EnteredDTTM")
@Expose
private String enteredDTTM;
@SerializedName("ReceivedOnFloorDTTM")
@Expose
private String receivedOnFloorDTTM;
@SerializedName("ForwardDTTM")
@Expose
private String forwardDTTM;
@SerializedName("VisitID")
@Expose
private String visitID;
@SerializedName("VisitTypeID")
@Expose
private String visitTypeID;
@SerializedName("DischargeDttm")
@Expose
private String dischargeDttm;
@SerializedName("VisitLocationID")
@Expose
private String visitLocationID;
@SerializedName("DoctorID")
@Expose
private Object doctorID;
@SerializedName("RoomID")
@Expose
private String roomID;
@SerializedName("BedID")
@Expose
private String bedID;
@SerializedName("VisitDTTM")
@Expose
private String visitDTTM;
@SerializedName("SelfPayeeReceivable")
@Expose
private String selfPayeeReceivable;
@SerializedName("Ordered")
@Expose
private String ordered;
@SerializedName("Source")
@Expose
private String source;
@SerializedName("SpecimenType")
@Expose
private String specimenType;
@SerializedName("Exists")
@Expose
private Object exists;
@SerializedName("VisitDateRange")
@Expose
private Object visitDateRange;
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
private final static long serialVersionUID = -434772577431124865L;

public Object getSOrderedTestParentId() {
return sOrderedTestParentId;
}

public void setSOrderedTestParentId(Object sOrderedTestParentId) {
this.sOrderedTestParentId = sOrderedTestParentId;
}

public Object getSOrderedTestChildId() {
return sOrderedTestChildId;
}

public void setSOrderedTestChildId(Object sOrderedTestChildId) {
this.sOrderedTestChildId = sOrderedTestChildId;
}

public Object getSOrderedTestParentMnemonic() {
return sOrderedTestParentMnemonic;
}

public void setSOrderedTestParentMnemonic(Object sOrderedTestParentMnemonic) {
this.sOrderedTestParentMnemonic = sOrderedTestParentMnemonic;
}

public Object getSOrderedTestChildMnemonic() {
return sOrderedTestChildMnemonic;
}

public void setSOrderedTestChildMnemonic(Object sOrderedTestChildMnemonic) {
this.sOrderedTestChildMnemonic = sOrderedTestChildMnemonic;
}

public Boolean getBChildren() {
return bChildren;
}

public void setBChildren(Boolean bChildren) {
this.bChildren = bChildren;
}

public Integer getDTestResult() {
return dTestResult;
}

public void setDTestResult(Integer dTestResult) {
this.dTestResult = dTestResult;
}

public Object getSTestResultDttm() {
return sTestResultDttm;
}

public void setSTestResultDttm(Object sTestResultDttm) {
this.sTestResultDttm = sTestResultDttm;
}

public Object getSTestResultDttmPlot() {
return sTestResultDttmPlot;
}

public void setSTestResultDttmPlot(Object sTestResultDttmPlot) {
this.sTestResultDttmPlot = sTestResultDttmPlot;
}

public Object getAbnormal() {
return abnormal;
}

public void setAbnormal(Object abnormal) {
this.abnormal = abnormal;
}

public Object getComment() {
return comment;
}

public void setComment(Object comment) {
this.comment = comment;
}

public Object getSIsNumericResult() {
return sIsNumericResult;
}

public void setSIsNumericResult(Object sIsNumericResult) {
this.sIsNumericResult = sIsNumericResult;
}

public Object getSResultGroup() {
return sResultGroup;
}

public void setSResultGroup(Object sResultGroup) {
this.sResultGroup = sResultGroup;
}

public Object getSGenderID() {
return sGenderID;
}

public void setSGenderID(Object sGenderID) {
this.sGenderID = sGenderID;
}

public Object getSAgeID() {
return sAgeID;
}

public void setSAgeID(Object sAgeID) {
this.sAgeID = sAgeID;
}

public Integer getDNormalLow() {
return dNormalLow;
}

public void setDNormalLow(Integer dNormalLow) {
this.dNormalLow = dNormalLow;
}

public Integer getDNormalHigh() {
return dNormalHigh;
}

public void setDNormalHigh(Integer dNormalHigh) {
this.dNormalHigh = dNormalHigh;
}

public Integer getDPanicLow() {
return dPanicLow;
}

public void setDPanicLow(Integer dPanicLow) {
this.dPanicLow = dPanicLow;
}

public Integer getDPanicHigh() {
return dPanicHigh;
}

public void setDPanicHigh(Integer dPanicHigh) {
this.dPanicHigh = dPanicHigh;
}

public Integer getDNormal() {
return dNormal;
}

public void setDNormal(Integer dNormal) {
this.dNormal = dNormal;
}

public String getSpecimenNumber() {
return specimenNumber;
}

public void setSpecimenNumber(String specimenNumber) {
this.specimenNumber = specimenNumber;
}

public String getRequestNumber() {
return requestNumber;
}

public void setRequestNumber(String requestNumber) {
this.requestNumber = requestNumber;
}

public String getSpecimenID() {
return specimenID;
}

public void setSpecimenID(String specimenID) {
this.specimenID = specimenID;
}

public String getSpecimenSectionID() {
return specimenSectionID;
}

public void setSpecimenSectionID(String specimenSectionID) {
this.specimenSectionID = specimenSectionID;
}

public String getStatusID() {
return statusID;
}

public void setStatusID(String statusID) {
this.statusID = statusID;
}

public String getEnteredDTTM() {
return enteredDTTM;
}

public void setEnteredDTTM(String enteredDTTM) {
this.enteredDTTM = enteredDTTM;
}

public String getReceivedOnFloorDTTM() {
return receivedOnFloorDTTM;
}

public void setReceivedOnFloorDTTM(String receivedOnFloorDTTM) {
this.receivedOnFloorDTTM = receivedOnFloorDTTM;
}

public String getForwardDTTM() {
return forwardDTTM;
}

public void setForwardDTTM(String forwardDTTM) {
this.forwardDTTM = forwardDTTM;
}

public String getVisitID() {
return visitID;
}

public void setVisitID(String visitID) {
this.visitID = visitID;
}

public String getVisitTypeID() {
return visitTypeID;
}

public void setVisitTypeID(String visitTypeID) {
this.visitTypeID = visitTypeID;
}

public String getDischargeDttm() {
return dischargeDttm;
}

public void setDischargeDttm(String dischargeDttm) {
this.dischargeDttm = dischargeDttm;
}

public String getVisitLocationID() {
return visitLocationID;
}

public void setVisitLocationID(String visitLocationID) {
this.visitLocationID = visitLocationID;
}

public Object getDoctorID() {
return doctorID;
}

public void setDoctorID(Object doctorID) {
this.doctorID = doctorID;
}

public String getRoomID() {
return roomID;
}

public void setRoomID(String roomID) {
this.roomID = roomID;
}

public String getBedID() {
return bedID;
}

public void setBedID(String bedID) {
this.bedID = bedID;
}

public String getVisitDTTM() {
return visitDTTM;
}

public void setVisitDTTM(String visitDTTM) {
this.visitDTTM = visitDTTM;
}

public String getSelfPayeeReceivable() {
return selfPayeeReceivable;
}

public void setSelfPayeeReceivable(String selfPayeeReceivable) {
this.selfPayeeReceivable = selfPayeeReceivable;
}

public String getOrdered() {
return ordered;
}

public void setOrdered(String ordered) {
this.ordered = ordered;
}

public String getSource() {
return source;
}

public void setSource(String source) {
this.source = source;
}

public String getSpecimenType() {
return specimenType;
}

public void setSpecimenType(String specimenType) {
this.specimenType = specimenType;
}

public Object getExists() {
return exists;
}

public void setExists(Object exists) {
this.exists = exists;
}

public Object getVisitDateRange() {
return visitDateRange;
}

public void setVisitDateRange(Object visitDateRange) {
this.visitDateRange = visitDateRange;
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

}