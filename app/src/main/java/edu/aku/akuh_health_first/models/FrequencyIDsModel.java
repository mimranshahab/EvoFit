package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hamza.ahmed on 3/8/2018.
 */

public class FrequencyIDsModel {


    @Expose
    @SerializedName("RecordFound")
    private String recordfound;
    @Expose
    @SerializedName("RXMedFrequencyDescription")
    private String rxmedfrequencydescription;
    @Expose
    @SerializedName("RXMedFrequencyID")
    private String rxmedfrequencyid;

    public String getRecordfound() {
        return recordfound;
    }

    public void setRecordfound(String recordfound) {
        this.recordfound = recordfound;
    }

    public String getRxmedfrequencydescription() {
        return rxmedfrequencydescription;
    }

    public void setRxmedfrequencydescription(String rxmedfrequencydescription) {
        this.rxmedfrequencydescription = rxmedfrequencydescription;
    }

    public String getRxmedfrequencyid() {
        return rxmedfrequencyid;
    }

    public void setRxmedfrequencyid(String rxmedfrequencyid) {
        this.rxmedfrequencyid = rxmedfrequencyid;
    }
}
