package edu.aku.family_hifazat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class LaboratoryModel implements Serializable, IsRecordFound {

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

    public String getSelfPayeeReceivableMsg() {
        return selfPayeeReceivableMsg;
    }

    public void setSelfPayeeReceivableMsg(String selfPayeeReceivableMsg) {
        this.selfPayeeReceivableMsg = selfPayeeReceivableMsg;
    }

    @SerializedName("SelfPayeeReceivableMsg")
    @Expose
    private String selfPayeeReceivableMsg;
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
    @SerializedName("isExternalReport")
    @Expose
    private boolean isExternalReport;
    @SerializedName("ExternalPath")
    @Expose
    private String externalPath;
    @SerializedName("ExternalFile")
    @Expose
    private Object externalFile;
    @SerializedName("SpecimenNumber")
    @Expose
    private String specimenNumber;
    @SerializedName("PerformedTestID")
    @Expose
    private String performedTestID;
    @SerializedName("Mnemonic")
    @Expose
    private String mnemonic;
    @SerializedName("Abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("DepartmentID")
    @Expose
    private String departmentID;
    @SerializedName("ResultMethod")
    @Expose
    private String resultMethod;
    @SerializedName("NormalRangeFormatted")
    @Expose
    private String normalRangeFormatted;
    @SerializedName("Result")
    @Expose
    private String result;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("AbnormalFlag")
    @Expose
    private String abnormalFlag;
    @SerializedName("ResultEntryUser")
    @Expose
    private String resultEntryUser;
    @SerializedName("ResultEntryDttm")
    @Expose
    private String resultEntryDttm;
    @SerializedName("ResultVerifyUser")
    @Expose
    private String resultVerifyUser;
    @SerializedName("ResultVerifyDttm")
    @Expose
    private String resultVerifyDttm;
    @SerializedName("FirstEntryUser")
    @Expose
    private String firstEntryUser;
    @SerializedName("FirstEntryDTTM")
    @Expose
    private String firstEntryDTTM;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("ResultComments")
    @Expose
    private String resultComments;
    @SerializedName("ReviewFlag")
    @Expose
    private String reviewFlag;
    @SerializedName("Optional")
    @Expose
    private String optional;
    @SerializedName("Reportable")
    @Expose
    private String reportable;
    @SerializedName("ExcludeResultTransferCP")
    @Expose
    private String excludeResultTransferCP;
    @SerializedName("ForwardUser")
    @Expose
    private String forwardUser;
    @SerializedName("ReportName")
    @Expose
    private String reportName;
    @SerializedName("isNumericResult")
    @Expose
    private String isNumericResult;
    @SerializedName("IsVerified")
    @Expose
    private Boolean isVerified;
    @SerializedName("PrevSpecimen1")
    @Expose
    private String prevSpecimen1;
    @SerializedName("PrevResult1Dttm")
    @Expose
    private String prevResult1Dttm;
    @SerializedName("PrevResult1")
    @Expose
    private String prevResult1;
    @SerializedName("PrevSpecimen2")
    @Expose
    private String prevSpecimen2;
    @SerializedName("PrevResult2Dttm")
    @Expose
    private String prevResult2Dttm;
    @SerializedName("PrevResult2")
    @Expose
    private String prevResult2;
    @SerializedName("PrevSpecimen3")
    @Expose
    private String prevSpecimen3;
    @SerializedName("PrevResult3Dttm")
    @Expose
    private String prevResult3Dttm;
    @SerializedName("PrevResult3")
    @Expose
    private String prevResult3;


    private String tempSpiceNum;

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

    public String getSpicemanNumwithComma() {
        return "\"" + tempSpiceNum + "\"";
    }


    private final static long serialVersionUID = 9133402102176218178L;

    public Boolean getIsExternalReport() {
        return isExternalReport;
    }

    public void setIsExternalReport(Boolean isExternalReport) {
        this.isExternalReport = isExternalReport;
    }

    public Object getExternalPath() {
        return externalPath;
    }

    public void setExternalPath(String externalPath) {
        this.externalPath = externalPath;
    }

    public Object getExternalFile() {
        return externalFile;
    }

    public void setExternalFile(Object externalFile) {
        this.externalFile = externalFile;
    }


    public String getPerformedTestID() {
        return performedTestID;
    }

    public void setPerformedTestID(String performedTestID) {
        this.performedTestID = performedTestID;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getResultMethod() {
        return resultMethod;
    }

    public void setResultMethod(String resultMethod) {
        this.resultMethod = resultMethod;
    }

    public String getNormalRangeFormatted() {
        return normalRangeFormatted;
    }

    public void setNormalRangeFormatted(String normalRangeFormatted) {
        this.normalRangeFormatted = normalRangeFormatted;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAbnormalFlag() {
        return abnormalFlag;
    }

    public void setAbnormalFlag(String abnormalFlag) {
        this.abnormalFlag = abnormalFlag;
    }

    public String getResultEntryUser() {
        return resultEntryUser;
    }

    public void setResultEntryUser(String resultEntryUser) {
        this.resultEntryUser = resultEntryUser;
    }

    public String getResultEntryDttm() {
        return resultEntryDttm;
    }

    public void setResultEntryDttm(String resultEntryDttm) {
        this.resultEntryDttm = resultEntryDttm;
    }

    public String getResultVerifyUser() {
        return resultVerifyUser;
    }

    public void setResultVerifyUser(String resultVerifyUser) {
        this.resultVerifyUser = resultVerifyUser;
    }

    public String getResultVerifyDttm() {
        return resultVerifyDttm;
    }

    public void setResultVerifyDttm(String resultVerifyDttm) {
        this.resultVerifyDttm = resultVerifyDttm;
    }

    public String getFirstEntryUser() {
        return firstEntryUser;
    }

    public void setFirstEntryUser(String firstEntryUser) {
        this.firstEntryUser = firstEntryUser;
    }

    public String getFirstEntryDTTM() {
        return firstEntryDTTM;
    }

    public void setFirstEntryDTTM(String firstEntryDTTM) {
        this.firstEntryDTTM = firstEntryDTTM;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getResultComments() {
        return resultComments;
    }

    public void setResultComments(String resultComments) {
        this.resultComments = resultComments;
    }

    public String getReviewFlag() {
        return reviewFlag;
    }

    public void setReviewFlag(String reviewFlag) {
        this.reviewFlag = reviewFlag;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String getReportable() {
        return reportable;
    }

    public void setReportable(String reportable) {
        this.reportable = reportable;
    }

    public String getExcludeResultTransferCP() {
        return excludeResultTransferCP;
    }

    public void setExcludeResultTransferCP(String excludeResultTransferCP) {
        this.excludeResultTransferCP = excludeResultTransferCP;
    }

    public String getForwardUser() {
        return forwardUser;
    }

    public void setForwardUser(String forwardUser) {
        this.forwardUser = forwardUser;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getIsNumericResult() {
        return isNumericResult;
    }

    public void setIsNumericResult(String isNumericResult) {
        this.isNumericResult = isNumericResult;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getPrevSpecimen1() {
        return prevSpecimen1;
    }

    public void setPrevSpecimen1(String prevSpecimen1) {
        this.prevSpecimen1 = prevSpecimen1;
    }

    public String getPrevResult1Dttm() {
        return prevResult1Dttm;
    }

    public void setPrevResult1Dttm(String prevResult1Dttm) {
        this.prevResult1Dttm = prevResult1Dttm;
    }

    public String getPrevResult1() {
        return prevResult1;
    }

    public void setPrevResult1(String prevResult1) {
        this.prevResult1 = prevResult1;
    }

    public String getPrevSpecimen2() {
        return prevSpecimen2;
    }

    public void setPrevSpecimen2(String prevSpecimen2) {
        this.prevSpecimen2 = prevSpecimen2;
    }

    public String getPrevResult2Dttm() {
        return prevResult2Dttm;
    }

    public void setPrevResult2Dttm(String prevResult2Dttm) {
        this.prevResult2Dttm = prevResult2Dttm;
    }

    public String getPrevResult2() {
        return prevResult2;
    }

    public void setPrevResult2(String prevResult2) {
        this.prevResult2 = prevResult2;
    }

    public String getPrevSpecimen3() {
        return prevSpecimen3;
    }

    public void setPrevSpecimen3(String prevSpecimen3) {
        this.prevSpecimen3 = prevSpecimen3;
    }

    public String getPrevResult3Dttm() {
        return prevResult3Dttm;
    }

    public void setPrevResult3Dttm(String prevResult3Dttm) {
        this.prevResult3Dttm = prevResult3Dttm;
    }

    public String getPrevResult3() {
        return prevResult3;
    }

    public void setPrevResult3(String prevResult3) {
        this.prevResult3 = prevResult3;
    }


    public boolean isNumeric() {
        return isNumericResult.equals("Y");
    }


}