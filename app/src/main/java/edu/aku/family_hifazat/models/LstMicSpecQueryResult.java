package edu.aku.family_hifazat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstMicSpecQueryResult {
    @Expose
    @SerializedName("QUERYRESULT")
    private String QUERYRESULT;
    @Expose
    @SerializedName("QUERYSTRING")
    private String QUERYSTRING;
    @Expose
    @SerializedName("QUERYNUMBER")
    private String QUERYNUMBER;
    @Expose
    @SerializedName("PARANUMBER")
    private String PARANUMBER;
    @Expose
    @SerializedName("PERFORMEDPROCEDUREID")
    private String PERFORMEDPROCEDUREID;
    @Expose
    @SerializedName("SPECIMENNUMBER")
    private String SPECIMENNUMBER;

    @SerializedName("PARATYPE")
    @Expose
    private String paratype;

    public String getParatype() {
        return paratype;
    }

    public void setParatype(String paratype) {
        this.paratype = paratype;
    }

    public String getQUERYRESULT() {
        return QUERYRESULT;
    }

    public void setQUERYRESULT(String QUERYRESULT) {
        this.QUERYRESULT = QUERYRESULT;
    }

    public String getQUERYSTRING() {
        return QUERYSTRING;
    }

    public void setQUERYSTRING(String QUERYSTRING) {
        this.QUERYSTRING = QUERYSTRING;
    }

    public String getQUERYNUMBER() {
        return QUERYNUMBER;
    }

    public void setQUERYNUMBER(String QUERYNUMBER) {
        this.QUERYNUMBER = QUERYNUMBER;
    }

    public String getPARANUMBER() {
        return PARANUMBER;
    }

    public void setPARANUMBER(String PARANUMBER) {
        this.PARANUMBER = PARANUMBER;
    }

    public String getPERFORMEDPROCEDUREID() {
        return PERFORMEDPROCEDUREID;
    }

    public void setPERFORMEDPROCEDUREID(String PERFORMEDPROCEDUREID) {
        this.PERFORMEDPROCEDUREID = PERFORMEDPROCEDUREID;
    }

    public String getSPECIMENNUMBER() {
        return SPECIMENNUMBER;
    }

    public void setSPECIMENNUMBER(String SPECIMENNUMBER) {
        this.SPECIMENNUMBER = SPECIMENNUMBER;
    }
}
