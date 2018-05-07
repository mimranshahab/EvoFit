package edu.aku.family_hifazat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamza.ahmed on 3/9/2018.
 */

public class PatientHealthSummaryModel {


    @Expose
    @SerializedName("RecordMessage")
    private String recordmessage;
    @Expose
    @SerializedName("RecordFound")
    private String recordfound;
    @Expose
    @SerializedName("HealthIndicatorList")
    private ArrayList<Subhealthindicator> healthindicatorlist;
    @Expose
    @SerializedName("SubHealthIndicator")
    private Subhealthindicator subhealthindicator;
    @Expose
    @SerializedName("HealthIndicator")
    private Healthindicator healthindicator;
    @Expose
    @SerializedName("MRN")
    private String mrn;

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

    public ArrayList<Subhealthindicator> getHealthindicatorlist() {
        return healthindicatorlist;
    }

    public void setHealthindicatorlist(ArrayList<Subhealthindicator> healthindicatorlist) {
        this.healthindicatorlist = healthindicatorlist;
    }

    public Subhealthindicator getSubhealthindicator() {
        return subhealthindicator;
    }

    public void setSubhealthindicator(Subhealthindicator subhealthindicator) {
        this.subhealthindicator = subhealthindicator;
    }

    public Healthindicator getHealthindicator() {
        return healthindicator;
    }

    public void setHealthindicator(Healthindicator healthindicator) {
        this.healthindicator = healthindicator;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }
}
