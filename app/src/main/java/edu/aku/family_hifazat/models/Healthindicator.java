package edu.aku.family_hifazat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Healthindicator {
    @Expose
    @SerializedName("MinValue")
    private String minvalue;
    @Expose
    @SerializedName("MaxValue")
    private String maxvalue;
    @Expose
    @SerializedName("Unit")
    private String unit;
    @Expose
    @SerializedName("HealthIndicatorDescription")
    private String healthindicatordescription;
    @Expose
    @SerializedName("HealthIndicatorID")
    private String healthindicatorid;

    public String getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(String minvalue) {
        this.minvalue = minvalue;
    }

    public String getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(String maxvalue) {
        this.maxvalue = maxvalue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getHealthindicatordescription() {
        return healthindicatordescription;
    }

    public void setHealthindicatordescription(String healthindicatordescription) {
        this.healthindicatordescription = healthindicatordescription;
    }

    public String getHealthindicatorid() {
        return healthindicatorid;
    }

    public void setHealthindicatorid(String healthindicatorid) {
        this.healthindicatorid = healthindicatorid;
    }
}
