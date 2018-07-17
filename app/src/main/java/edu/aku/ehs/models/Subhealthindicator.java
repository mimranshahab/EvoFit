package edu.aku.ehs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subhealthindicator {
    @Expose
    @SerializedName("TerminalID")
    private String terminalid;
    @Expose
    @SerializedName("UserID")
    private String userid;
    @Expose
    @SerializedName("Source")
    private String source;
    @Expose
    @SerializedName("HealthIndicatorValue")
    private String healthindicatorvalue;
    @Expose
    @SerializedName("DateTimeStr")
    private String datetimestr;
    @Expose
    @SerializedName("DateTime")
    private String datetime;

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHealthindicatorvalue() {
        return healthindicatorvalue;
    }

    public void setHealthindicatorvalue(String healthindicatorvalue) {
        this.healthindicatorvalue = healthindicatorvalue;
    }

    public String getDatetimestr() {
        return datetimestr;
    }

    public void setDatetimestr(String datetimestr) {
        this.datetimestr = datetimestr;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
