package edu.aku.family_hifazat.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstMicSpecOrganism implements Serializable
{

@SerializedName("lstILMMICSpecParaOrgSen")
@Expose
private Object lstILMMICSpecParaOrgSen;
@SerializedName("SPECIMENNUMBER")
@Expose
private String sPECIMENNUMBER;
@SerializedName("PERFORMEDPROCEDUREID")
@Expose
private String pERFORMEDPROCEDUREID;
@SerializedName("PARANUMBER")
@Expose
private String pARANUMBER;
@SerializedName("ORGANISMID")
@Expose
private String oRGANISMID;
@SerializedName("QUEUENUMBER")
@Expose
private Object qUEUENUMBER;
@SerializedName("ABBREVIATION")
@Expose
private String aBBREVIATION;
@SerializedName("MNEMONIC")
@Expose
private String mNEMONIC;
@SerializedName("LASTFILEDTTM")
@Expose
private Object lASTFILEDTTM;
@SerializedName("LASTFILETERMINALID")
@Expose
private Object lASTFILETERMINALID;
@SerializedName("LASTFILEUSERID")
@Expose
private Object lASTFILEUSERID;
private final static long serialVersionUID = -1996859977834708136L;

public Object getLstILMMICSpecParaOrgSen() {
return lstILMMICSpecParaOrgSen;
}

public void setLstILMMICSpecParaOrgSen(Object lstILMMICSpecParaOrgSen) {
this.lstILMMICSpecParaOrgSen = lstILMMICSpecParaOrgSen;
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

public String getORGANISMID() {
return oRGANISMID;
}

public void setORGANISMID(String oRGANISMID) {
this.oRGANISMID = oRGANISMID;
}

public Object getQUEUENUMBER() {
return qUEUENUMBER;
}

public void setQUEUENUMBER(Object qUEUENUMBER) {
this.qUEUENUMBER = qUEUENUMBER;
}

public String getABBREVIATION() {
return aBBREVIATION;
}

public void setABBREVIATION(String aBBREVIATION) {
this.aBBREVIATION = aBBREVIATION;
}

public String getMNEMONIC() {
return mNEMONIC;
}

public void setMNEMONIC(String mNEMONIC) {
this.mNEMONIC = mNEMONIC;
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

}