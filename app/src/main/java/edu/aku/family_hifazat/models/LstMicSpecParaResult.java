package edu.aku.family_hifazat.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstMicSpecParaResult implements Serializable
{

@SerializedName("lstMicSpecOrganism")
@Expose
private ArrayList<LstMicSpecOrganism> lstMicSpecOrganism = null;
@SerializedName("lstMicSpecOrgSensiProc")
@Expose
private List<LstMicSpecOrgSensiProc> lstMicSpecOrgSensiProc = null;
@SerializedName("lstMicSpecAntibiotics")
@Expose
private List<LstMicSpecAntibiotic> lstMicSpecAntibiotics = null;
@SerializedName("SPECIMENNUMBER")
@Expose
private String sPECIMENNUMBER;
@SerializedName("PERFORMEDPROCEDUREID")
@Expose
private String pERFORMEDPROCEDUREID;
@SerializedName("PARANUMBER")
@Expose
private String pARANUMBER;
@SerializedName("LASTFILEDTTM")
@Expose
private Object lASTFILEDTTM;
@SerializedName("LASTFILETERMINALID")
@Expose
private Object lASTFILETERMINALID;
@SerializedName("LASTFILEUSERID")
@Expose
private Object lASTFILEUSERID;
@SerializedName("PARARESULT")
@Expose
private String pARARESULT;
@SerializedName("PARATYPE")
@Expose
private String pARATYPE;
private final static long serialVersionUID = 3353171977678905030L;

public ArrayList<LstMicSpecOrganism> getLstMicSpecOrganism() {
return lstMicSpecOrganism;
}

public void setLstMicSpecOrganism(ArrayList<LstMicSpecOrganism> lstMicSpecOrganism) {
this.lstMicSpecOrganism = lstMicSpecOrganism;
}

public List<LstMicSpecOrgSensiProc> getLstMicSpecOrgSensiProc() {
return lstMicSpecOrgSensiProc;
}

public void setLstMicSpecOrgSensiProc(List<LstMicSpecOrgSensiProc> lstMicSpecOrgSensiProc) {
this.lstMicSpecOrgSensiProc = lstMicSpecOrgSensiProc;
}

public List<LstMicSpecAntibiotic> getLstMicSpecAntibiotics() {
return lstMicSpecAntibiotics;
}

public void setLstMicSpecAntibiotics(List<LstMicSpecAntibiotic> lstMicSpecAntibiotics) {
this.lstMicSpecAntibiotics = lstMicSpecAntibiotics;
}

public String getSPECIMENNUMBER() {
return sPECIMENNUMBER;
}

public void setSPECIMENNUMBER(String sPECIMENNUMBER) {
this.sPECIMENNUMBER = sPECIMENNUMBER;
}

public String getPERFORMEDPROCEDUREID() {
return pERFORMEDPROCEDUREID;
}

public void setPERFORMEDPROCEDUREID(String pERFORMEDPROCEDUREID) {
this.pERFORMEDPROCEDUREID = pERFORMEDPROCEDUREID;
}

public String getPARANUMBER() {
return pARANUMBER;
}

public void setPARANUMBER(String pARANUMBER) {
this.pARANUMBER = pARANUMBER;
}

public Object getLASTFILEDTTM() {
return lASTFILEDTTM;
}

public void setLASTFILEDTTM(Object lASTFILEDTTM) {
this.lASTFILEDTTM = lASTFILEDTTM;
}

public Object getLASTFILETERMINALID() {
return lASTFILETERMINALID;
}

public void setLASTFILETERMINALID(Object lASTFILETERMINALID) {
this.lASTFILETERMINALID = lASTFILETERMINALID;
}

public Object getLASTFILEUSERID() {
return lASTFILEUSERID;
}

public void setLASTFILEUSERID(Object lASTFILEUSERID) {
this.lASTFILEUSERID = lASTFILEUSERID;
}

public String getPARARESULT() {
return pARARESULT;
}

public void setPARARESULT(String pARARESULT) {
this.pARARESULT = pARARESULT;
}

public String getPARATYPE() {
return pARATYPE;
}

public void setPARATYPE(String pARATYPE) {
this.pARATYPE = pARATYPE;
}

}