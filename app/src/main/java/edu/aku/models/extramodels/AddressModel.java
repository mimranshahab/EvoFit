package edu.aku.models.extramodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 04-Apr-17.
 */

public class AddressModel {


    @SerializedName("country_id")
    private String countryID;

    @SerializedName("country")
    private String country;

    @SerializedName("city_id")
    private String cityID;

    @SerializedName("city")
    private String city;

    @SerializedName("street_name")
    private String street;

    @SerializedName("building_name")
    private String building;

    @SerializedName("floor")
    private String floor;

    @SerializedName("appartment")
    private String apartment;

    @SerializedName("nearest_landmark")
    private String landMark;

    @SerializedName("is_selected")
    private int isDefault;

    @SerializedName("location_type")
    private String locationType;


    @SerializedName("location_name")
    private String locationName;

    @SerializedName("id")
    private int addressID;

    private double latitude;

    private double longitude;


    private int spLocationTypePosition;


    public AddressModel(String country, String city, String street, String building, String floor, String apartment, String landMark, String locationType, int isDefault, double longitude, double latitude, String locationName) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.floor = floor;
        this.apartment = apartment;
        this.landMark = landMark;
        this.isDefault = isDefault;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.locationName = locationName;
    }


    public AddressModel(String country, String city, String street, String building, String floor, String apartment, String landMark, String locationType, int isDefault, double longitude, double latitude, String locationName, int spLocationTypePosition) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.floor = floor;
        this.apartment = apartment;
        this.landMark = landMark;
        this.isDefault = isDefault;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.locationName = locationName;
        this.spLocationTypePosition = spLocationTypePosition;
    }

    public int getSpLocationTypePosition() {
        return spLocationTypePosition;
    }

    public void setSpLocationTypePosition(int spLocationTypePosition) {
        this.spLocationTypePosition = spLocationTypePosition;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddressString() {


        return getLocationType() + " " + getBuilding() + " " + getStreet() + " " + getCity() + " " + getCountry() + ".";
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getLandMark() {

        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }


    public boolean getIsDefault() {
        if (isDefault == 1)
            return true;
        else
            return false;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
