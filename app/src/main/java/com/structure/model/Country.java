package com.structure.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 13-Apr-17.
 */

public class Country {

    public Country(String countryName) {
        this.countryName = countryName;
    }

    @SerializedName("countryID")
    public String countryID;

    @SerializedName("countryName")
    public String countryName;

    @SerializedName("localName")
    public String localName;

    @SerializedName("webCode")
    public String webCode;

    @SerializedName("region")
    public String region;

    @SerializedName("continent")
    public String continent;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("surfaceArea")
    public double surfaceArea;

    @SerializedName("population")
    public long population;


}