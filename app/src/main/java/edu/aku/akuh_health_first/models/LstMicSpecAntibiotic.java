package edu.aku.akuh_health_first.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstMicSpecAntibiotic implements Serializable {

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
    private String sENSITIVITYPROCEDUREID;
    @SerializedName("ANTIBIOTICID")
    @Expose
    private String aNTIBIOTICID;
    @SerializedName("ZONESIZE")
    @Expose
    private Object zONESIZE;
    @SerializedName("REACTION")
    @Expose
    private String rEACTION;
    @SerializedName("ABNORMAL")
    @Expose
    private String aBNORMAL;
    @SerializedName("KBCOSTPERDAY")
    @Expose
    private Object kBCOSTPERDAY;
    @SerializedName("DONOTPRINT")
    @Expose
    private String dONOTPRINT;
    @SerializedName("LASTFILEDTTM")
    @Expose
    private Object lASTFILEDTTM;
    @SerializedName("LASTFILETERMINALID")
    @Expose
    private Object lASTFILETERMINALID;
    @SerializedName("LASTFILEUSERID")
    @Expose
    private Object lASTFILEUSERID;
    @SerializedName("MICVALUE")
    @Expose
    private Object mICVALUE;
    @SerializedName("PRINTMICVALUE")
    @Expose
    private String pRINTMICVALUE;
    @SerializedName("SENSITIVITYPROCEDURETYPE")
    @Expose
    private String sENSITIVITYPROCEDURETYPE;
    @SerializedName("ABBREVIATION")
    @Expose
    private String aBBREVIATION;
    @SerializedName("MNEMONIC")
    @Expose
    private String mNEMONIC;
    @SerializedName("VALUE")
    @Expose
    private String vALUE;
    private final static long serialVersionUID = -8733150203623695448L;

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

    public String getSENSITIVITYPROCEDUREID() {
        return sENSITIVITYPROCEDUREID;
    }

    public void setSENSITIVITYPROCEDUREID(String sENSITIVITYPROCEDUREID) {
        this.sENSITIVITYPROCEDUREID = sENSITIVITYPROCEDUREID;
    }

    public String getANTIBIOTICID() {
        return aNTIBIOTICID;
    }

    public void setANTIBIOTICID(String aNTIBIOTICID) {
        this.aNTIBIOTICID = aNTIBIOTICID;
    }

    public Object getZONESIZE() {
        return zONESIZE;
    }

    public void setZONESIZE(Object zONESIZE) {
        this.zONESIZE = zONESIZE;
    }

    public String getREACTION() {
        return rEACTION;
    }

    public void setREACTION(String rEACTION) {
        this.rEACTION = rEACTION;
    }

    public String getABNORMAL() {
        return aBNORMAL;
    }

    public void setABNORMAL(String aBNORMAL) {
        this.aBNORMAL = aBNORMAL;
    }

    public Object getKBCOSTPERDAY() {
        return kBCOSTPERDAY;
    }

    public void setKBCOSTPERDAY(Object kBCOSTPERDAY) {
        this.kBCOSTPERDAY = kBCOSTPERDAY;
    }

    public String getDONOTPRINT() {
        return dONOTPRINT;
    }

    public void setDONOTPRINT(String dONOTPRINT) {
        this.dONOTPRINT = dONOTPRINT;
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

    public Object getMICVALUE() {
        return mICVALUE;
    }

    public void setMICVALUE(Object mICVALUE) {
        this.mICVALUE = mICVALUE;
    }

    public String getPRINTMICVALUE() {
        return pRINTMICVALUE;
    }

    public void setPRINTMICVALUE(String pRINTMICVALUE) {
        this.pRINTMICVALUE = pRINTMICVALUE;
    }

    public String getSENSITIVITYPROCEDURETYPE() {
        return sENSITIVITYPROCEDURETYPE;
    }

    public void setSENSITIVITYPROCEDURETYPE(String sENSITIVITYPROCEDURETYPE) {
        this.sENSITIVITYPROCEDURETYPE = sENSITIVITYPROCEDURETYPE;
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

    public String getVALUE() {
        return vALUE;
    }

    public void setVALUE(String vALUE) {
        this.vALUE = vALUE;
    }

    @Override
    public String toString() {
        return this.getABBREVIATION();
    }
}