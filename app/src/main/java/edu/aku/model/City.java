package edu.aku.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 13-Apr-17.
 */

public class City {

    @SerializedName("cityID")
    public int cityID;

    @SerializedName("cityName")
    public String cityName;


    @SerializedName("stateID")
    public int stateID;

    @SerializedName("countryID")
    public String countryID;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longtitude;

}