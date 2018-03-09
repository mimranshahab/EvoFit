package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;

/**
 * Created by hamza.ahmed on 3/8/2018.
 */

public class AddNewMedicine {


    @Expose
    @SerializedName("MRNumber")
    private String mrnumber;
    @Expose
    @SerializedName("RXMedRoute")
    private String rxmedroute;
    @Expose
    @SerializedName("RXMedStopDateTime")
    private String rxmedstopdatetime;
    @Expose
    @SerializedName("RXMedStartDateTime")
    private String rxmedstartdatetime;
    @Expose
    @SerializedName("RXMedFrequencyID")
    private String rxmedfrequencyid;
    @Expose
    @SerializedName("RXMedMedication")
    private String rxmedmedication;

    public String getLastFileterminal() {
        return lastFileterminal;
    }

    public void setLastFileterminal(String lastFileterminal) {
        this.lastFileterminal = lastFileterminal;
    }

    @Expose
    @SerializedName("LastFileTerminal")
    private String lastFileterminal;

    public String getMrnumber() {
        return mrnumber;
    }

    public void setMrnumber(String mrnumber) {
        this.mrnumber = mrnumber;
    }

    public String getRxmedroute() {
        return rxmedroute;
    }

    public void setRxmedroute(String rxmedroute) {
        this.rxmedroute = rxmedroute;
    }

    public String getRxmedstopdatetime() {
        return rxmedstopdatetime;
    }

    public void setRxmedstopdatetime(String rxmedstopdatetime) {
        this.rxmedstopdatetime = rxmedstopdatetime;
    }

    public String getRxmedstartdatetime() {
        return rxmedstartdatetime;
    }

    public void setRxmedstartdatetime(String rxmedstartdatetime) {
        this.rxmedstartdatetime = rxmedstartdatetime;
    }

    public String getRxmedfrequencyid() {
        return rxmedfrequencyid;
    }

    public void setRxmedfrequencyid(String rxmedfrequencyid) {
        this.rxmedfrequencyid = rxmedfrequencyid;
    }

    public String getRxmedmedication() {
        return rxmedmedication;
    }

    public void setRxmedmedication(String rxmedmedication) {
        this.rxmedmedication = rxmedmedication;
    }


    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }
}
