package edu.aku.ehs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstLaboratorySpecimenResults {

    @SerializedName("isExternalReport")
    @Expose
    private Boolean isExternalReport;
    @SerializedName("ExternalPath")
    @Expose
    private Object externalPath;
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
    private final static long serialVersionUID = 7059046181713280754L;

    public Boolean getIsExternalReport() {
        return isExternalReport;
    }

    public void setIsExternalReport(Boolean isExternalReport) {
        this.isExternalReport = isExternalReport;
    }

    public Object getExternalPath() {
        return externalPath;
    }

    public void setExternalPath(Object externalPath) {
        this.externalPath = externalPath;
    }

    public Object getExternalFile() {
        return externalFile;
    }

    public void setExternalFile(Object externalFile) {
        this.externalFile = externalFile;
    }

    public String getSpecimenNumber() {
        return specimenNumber;
    }

    public void setSpecimenNumber(String specimenNumber) {
        this.specimenNumber = specimenNumber;
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

}