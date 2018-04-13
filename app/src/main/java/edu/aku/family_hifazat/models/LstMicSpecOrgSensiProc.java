package edu.aku.family_hifazat.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstMicSpecOrgSensiProc implements Serializable
{

@SerializedName("lstILMMICOrganism")
@Expose
private Object lstILMMICOrganism;
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
@SerializedName("SENSITIVITYPROCEDUREID")
@Expose
private Object sENSITIVITYPROCEDUREID;
@SerializedName("SENSITIVITYENTRYUSER")
@Expose
private Object sENSITIVITYENTRYUSER;
@SerializedName("SENSITIVITYENTRYDTTM")
@Expose
private Object sENSITIVITYENTRYDTTM;
@SerializedName("SENSITIVITYBILLED")
@Expose
private Object sENSITIVITYBILLED;
@SerializedName("SENSITIVITYMICCHART")
@Expose
private Object sENSITIVITYMICCHART;
@SerializedName("MAXLENGTHMIC")
@Expose
private Object mAXLENGTHMIC;
@SerializedName("MAXLENGTHIQ")
@Expose
private Object mAXLENGTHIQ;
@SerializedName("MAXLENGTHCOST")
@Expose
private Object mAXLENGTHCOST;
@SerializedName("MAXLENGTHDOSE")
@Expose
private Object mAXLENGTHDOSE;
@SerializedName("LASTFILEDTTM")
@Expose
private Object lASTFILEDTTM;
@SerializedName("LASTFILETERMINALID")
@Expose
private Object lASTFILETERMINALID;
@SerializedName("LASTFILEUSERID")
@Expose
private Object lASTFILEUSERID;
@SerializedName("SENSITIVITYCOMMENTS")
@Expose
private Object sENSITIVITYCOMMENTS;
@SerializedName("MNEMONIC")
@Expose
private String mNEMONIC;
@SerializedName("SENSITIVITYTYPEID")
@Expose
private String sENSITIVITYTYPEID;
private final static long serialVersionUID = -6054673509063413562L;

public Object getLstILMMICOrganism() {
return lstILMMICOrganism;
}

public void setLstILMMICOrganism(Object lstILMMICOrganism) {
this.lstILMMICOrganism = lstILMMICOrganism;
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

public Object getSENSITIVITYPROCEDUREID() {
return sENSITIVITYPROCEDUREID;
}

public void setSENSITIVITYPROCEDUREID(Object sENSITIVITYPROCEDUREID) {
this.sENSITIVITYPROCEDUREID = sENSITIVITYPROCEDUREID;
}

public Object getSENSITIVITYENTRYUSER() {
return sENSITIVITYENTRYUSER;
}

public void setSENSITIVITYENTRYUSER(Object sENSITIVITYENTRYUSER) {
this.sENSITIVITYENTRYUSER = sENSITIVITYENTRYUSER;
}

public Object getSENSITIVITYENTRYDTTM() {
return sENSITIVITYENTRYDTTM;
}

public void setSENSITIVITYENTRYDTTM(Object sENSITIVITYENTRYDTTM) {
this.sENSITIVITYENTRYDTTM = sENSITIVITYENTRYDTTM;
}

public Object getSENSITIVITYBILLED() {
return sENSITIVITYBILLED;
}

public void setSENSITIVITYBILLED(Object sENSITIVITYBILLED) {
this.sENSITIVITYBILLED = sENSITIVITYBILLED;
}

public Object getSENSITIVITYMICCHART() {
return sENSITIVITYMICCHART;
}

public void setSENSITIVITYMICCHART(Object sENSITIVITYMICCHART) {
this.sENSITIVITYMICCHART = sENSITIVITYMICCHART;
}

public Object getMAXLENGTHMIC() {
return mAXLENGTHMIC;
}

public void setMAXLENGTHMIC(Object mAXLENGTHMIC) {
this.mAXLENGTHMIC = mAXLENGTHMIC;
}

public Object getMAXLENGTHIQ() {
return mAXLENGTHIQ;
}

public void setMAXLENGTHIQ(Object mAXLENGTHIQ) {
this.mAXLENGTHIQ = mAXLENGTHIQ;
}

public Object getMAXLENGTHCOST() {
return mAXLENGTHCOST;
}

public void setMAXLENGTHCOST(Object mAXLENGTHCOST) {
this.mAXLENGTHCOST = mAXLENGTHCOST;
}

public Object getMAXLENGTHDOSE() {
return mAXLENGTHDOSE;
}

public void setMAXLENGTHDOSE(Object mAXLENGTHDOSE) {
this.mAXLENGTHDOSE = mAXLENGTHDOSE;
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

public Object getSENSITIVITYCOMMENTS() {
return sENSITIVITYCOMMENTS;
}

public void setSENSITIVITYCOMMENTS(Object sENSITIVITYCOMMENTS) {
this.sENSITIVITYCOMMENTS = sENSITIVITYCOMMENTS;
}

public String getMNEMONIC() {
return mNEMONIC;
}

public void setMNEMONIC(String mNEMONIC) {
this.mNEMONIC = mNEMONIC;
}

public String getSENSITIVITYTYPEID() {
return sENSITIVITYTYPEID;
}

public void setSENSITIVITYTYPEID(String sENSITIVITYTYPEID) {
this.sENSITIVITYTYPEID = sENSITIVITYTYPEID;
}

}