package edu.aku.family_hifazat.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LstLaboratoryMicspecimenOrderedProc
{

@SerializedName("lstLaboratoryMicSpecimenResults")
@Expose
private List<LstLaboratoryMicspecimenResults> lstLaboratoryMicSpecimenResults = null;
@SerializedName("SPECIMENNUMBER")
@Expose
private String sPECIMENNUMBER;
@SerializedName("ORDEREDPROCEDUREID")
@Expose
private String oRDEREDPROCEDUREID;
@SerializedName("PROCEDUREDESCRIPTION")
@Expose
private String pROCEDUREDESCRIPTION;
@SerializedName("MNEMONIC")
@Expose
private String mNEMONIC;
private final static long serialVersionUID = 8165598763073931528L;

public List<LstLaboratoryMicspecimenResults> getLstLaboratoryMicSpecimenResults() {
return lstLaboratoryMicSpecimenResults;
}

public void setLstLaboratoryMicSpecimenResults(List<LstLaboratoryMicspecimenResults> lstLaboratoryMicSpecimenResults) {
this.lstLaboratoryMicSpecimenResults = lstLaboratoryMicSpecimenResults;
}

public String getSPECIMENNUMBER() {
return sPECIMENNUMBER;
}

public void setSPECIMENNUMBER(String sPECIMENNUMBER) {
this.sPECIMENNUMBER = sPECIMENNUMBER;
}

public String getORDEREDPROCEDUREID() {
return oRDEREDPROCEDUREID;
}

public void setORDEREDPROCEDUREID(String oRDEREDPROCEDUREID) {
this.oRDEREDPROCEDUREID = oRDEREDPROCEDUREID;
}

public String getPROCEDUREDESCRIPTION() {
return pROCEDUREDESCRIPTION;
}

public void setPROCEDUREDESCRIPTION(String pROCEDUREDESCRIPTION) {
this.pROCEDUREDESCRIPTION = pROCEDUREDESCRIPTION;
}

public String getMNEMONIC() {
return mNEMONIC;
}

public void setMNEMONIC(String mNEMONIC) {
this.mNEMONIC = mNEMONIC;
}

}