package edu.aku.akuh_health_first.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstLaboratoryMicspecimenResults implements Serializable
{

@SerializedName("lstMicSpecParaResult")
@Expose
private List<LstMicSpecParaResult> lstMicSpecParaResult = null;
@SerializedName("lstMicSpecQueryResult")
@Expose
private List<LstMicSpecQueryResult> lstMicSpecQueryResult = null;
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
@SerializedName("PerformedProcedureId")
@Expose
private String performedProcedureId;
@SerializedName("Mnemonic")
@Expose
private String mnemonic;
@SerializedName("ProcedureDescription")
@Expose
private String procedureDescription;
@SerializedName("ProcedureTypeId")
@Expose
private String procedureTypeId;
@SerializedName("Reportable")
@Expose
private String reportable;
@SerializedName("ExcludeResultTransferCP")
@Expose
private String excludeResultTransferCP;
@SerializedName("TotalParagraphs")
@Expose
private String totalParagraphs;
@SerializedName("TotalVerifiedParagraphs")
@Expose
private String totalVerifiedParagraphs;
@SerializedName("ForwardUser")
@Expose
private Object forwardUser;
@SerializedName("MostRecentVerifiedParagraph")
@Expose
private Object mostRecentVerifiedParagraph;
private final static long serialVersionUID = -1580828081109211197L;

public List<LstMicSpecParaResult> getLstMicSpecParaResult() {
return lstMicSpecParaResult;
}

public void setLstMicSpecParaResult(List<LstMicSpecParaResult> lstMicSpecParaResult) {
this.lstMicSpecParaResult = lstMicSpecParaResult;
}

public List<LstMicSpecQueryResult> getLstMicSpecQueryResult() {
return lstMicSpecQueryResult;
}

public void setLstMicSpecQueryResult(List<LstMicSpecQueryResult> lstMicSpecQueryResult) {
this.lstMicSpecQueryResult = lstMicSpecQueryResult;
}

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

public String getPerformedProcedureId() {
return performedProcedureId;
}

public void setPerformedProcedureId(String performedProcedureId) {
this.performedProcedureId = performedProcedureId;
}

public String getMnemonic() {
return mnemonic;
}

public void setMnemonic(String mnemonic) {
this.mnemonic = mnemonic;
}

public String getProcedureDescription() {
return procedureDescription;
}

public void setProcedureDescription(String procedureDescription) {
this.procedureDescription = procedureDescription;
}

public String getProcedureTypeId() {
return procedureTypeId;
}

public void setProcedureTypeId(String procedureTypeId) {
this.procedureTypeId = procedureTypeId;
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

public String getTotalParagraphs() {
return totalParagraphs;
}

public void setTotalParagraphs(String totalParagraphs) {
this.totalParagraphs = totalParagraphs;
}

public String getTotalVerifiedParagraphs() {
return totalVerifiedParagraphs;
}

public void setTotalVerifiedParagraphs(String totalVerifiedParagraphs) {
this.totalVerifiedParagraphs = totalVerifiedParagraphs;
}

public Object getForwardUser() {
return forwardUser;
}

public void setForwardUser(Object forwardUser) {
this.forwardUser = forwardUser;
}

public Object getMostRecentVerifiedParagraph() {
return mostRecentVerifiedParagraph;
}

public void setMostRecentVerifiedParagraph(Object mostRecentVerifiedParagraph) {
this.mostRecentVerifiedParagraph = mostRecentVerifiedParagraph;
}

}