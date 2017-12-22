package edu.aku.model;

import edu.aku.model.extramodels.AddressModel;

/**
 * Created by khanhamza on 31-Mar-17.
 */

public class OrderSummaryModel {

    private boolean isAddressSelected = false;
    private AddressModel address;
    private String dateAndTime;
    private String additionalNotes;

    private String price;
    private String currencyType;


    public OrderSummaryModel(boolean isAddressSelected) {
        this.isAddressSelected = isAddressSelected;
    }


    public OrderSummaryModel(boolean isAddressSelected, AddressModel address, String price, String currencyType, String dateAndTime, String additionalNotes) {
        this.isAddressSelected = isAddressSelected;
        this.address = address;
        this.price = price;
        this.currencyType = currencyType;
        this.dateAndTime = dateAndTime;
        this.additionalNotes = additionalNotes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }


    public boolean isAddressSelected() {
        return isAddressSelected;
    }

    public void setAddressSelected(boolean addressSelected) {
        isAddressSelected = addressSelected;
    }

    public AddressModel getAddress() {
        return address;
    }


    public String getAddressString() {


        return address.getLocationType() + " " + address.getBuilding() + " " + address.getStreet() + " " + address.getCity() + " " + address.getCountry() + ".";
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public String getDateAndTime() {

        if (dateAndTime == null || dateAndTime == "") {
            return "Please select Date and Time";
        }
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
