package edu.aku.ehs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hamza.ahmed on 6/25/2018.
 */

public class LabHistoryModel {


    @Expose
    @SerializedName("RecordMessage")
    private String recordmessage;
    @Expose
    @SerializedName("RecordFound")
    private String recordfound;
    @Expose
    @SerializedName("sortdttm")
    private String sortdttm;
    @Expose
    @SerializedName("AbnormalFlag")
    private String abnormalflag;
    @Expose
    @SerializedName("Unit")
    private String unit;
    @Expose
    @SerializedName("Result")
    private String result;
    @Expose
    @SerializedName("NormalRangeFormatted")
    private String normalrangeformatted;
    @Expose
    @SerializedName("DepartmentID")
    private String departmentid;
    @Expose
    @SerializedName("Mnemonic")
    private String mnemonic;
    @Expose
    @SerializedName("PerformedTestID")
    private String performedtestid;
    @Expose
    @SerializedName("SpecimenNumber")
    private String specimennumber;

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

    public String getSortdttm() {
        return sortdttm;
    }

    public void setSortdttm(String sortdttm) {
        this.sortdttm = sortdttm;
    }

    public String getAbnormalflag() {
        return abnormalflag;
    }

    public void setAbnormalflag(String abnormalflag) {
        this.abnormalflag = abnormalflag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNormalrangeformatted() {
        return normalrangeformatted;
    }

    public void setNormalrangeformatted(String normalrangeformatted) {
        this.normalrangeformatted = normalrangeformatted;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getPerformedtestid() {
        return performedtestid;
    }

    public void setPerformedtestid(String performedtestid) {
        this.performedtestid = performedtestid;
    }

    public String getSpecimennumber() {
        return specimennumber;
    }

    public void setSpecimennumber(String specimennumber) {
        this.specimennumber = specimennumber;
    }
}
