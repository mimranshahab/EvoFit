package edu.aku.ehs.models.receiving_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterVM {
    @SerializedName("GenderList")
    private List<RegisterOptionsModel> GenderList;
    @SerializedName("RelationsList")
    private List<RegisterOptionsModel> RelationsList;
    @SerializedName("NationalityList")
    private List<RegisterOptionsModel> NationalityList;
    @SerializedName("CurrentCountryList")
    private List<RegisterOptionsModel> CurrentCountryList;
    @SerializedName("asdf")
    private List<RegisterOptionsModel> PermanentCountryList;
    @SerializedName("CardTypeList")
    private List<RegisterOptionsModel> CardTypeList;
    @SerializedName("UserDetailModel")
    private String Subscriber;
    @SerializedName("FamilyMembersList")
    private List<RegisterOptionsModel> FamilyMembersList;
    @SerializedName("RegisterRequest")
    private String RegisterRequest;


    public List<RegisterOptionsModel> getGenderList() {
        return GenderList;
    }

    public List<RegisterOptionsModel> getRelationsList() {
        return RelationsList;
    }

    public List<RegisterOptionsModel> getNationalityList() {
        return NationalityList;
    }

    public List<RegisterOptionsModel> getCurrentCountryList() {
        return CurrentCountryList;
    }

    public List<RegisterOptionsModel> getPermanentCountryList() {
        return PermanentCountryList;
    }

    public List<RegisterOptionsModel> getCardTypeList() {
        return CardTypeList;
    }

    public String getSubscriber() {
        return Subscriber;
    }

    public List<RegisterOptionsModel> getFamilyMembersList() {
        return FamilyMembersList;
    }

    public String getRegisterRequest() {
        return RegisterRequest;
    }
}
