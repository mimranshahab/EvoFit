package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hamza.ahmed on 3/9/2018.
 */

public class PatientHealthSummaryModel {


    @Expose
    @SerializedName("HeightDate")
    private String heightdate;
    @Expose
    @SerializedName("WeightDate")
    private String weightdate;
    @Expose
    @SerializedName("Height")
    private String height;
    @Expose
    @SerializedName("Weight")
    private String weight;
    @Expose
    @SerializedName("BloodType")
    private String bloodtype;

    public String getHeightdate() {
        return heightdate;
    }

    public void setHeightdate(String heightdate) {
        this.heightdate = heightdate;
    }

    public String getWeightdate() {
        return weightdate;
    }

    public void setWeightdate(String weightdate) {
        this.weightdate = weightdate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }
}
