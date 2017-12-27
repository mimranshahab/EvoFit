package edu.aku.models;

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
    @SerializedName("PermanentCountryList")
    private List<RegisterOptionsModel> PermanentCountryList;
    @SerializedName("CardTypeList")
    private List<RegisterOptionsModel> CardTypeList;
    @SerializedName("Subscriber")
    private String Subscriber;
    @SerializedName("FamilyMembersList")
    private List<RegisterOptionsModel> FamilyMembersList;
    @SerializedName("RegisterRequest")
    private String RegisterRequest;


    public List<edu.aku.models.RegisterOptionsModel> getRegisterOptionsModel() {
        return GenderList;
    }

    public List<edu.aku.models.RegisterOptionsModel> getRelationsList() {
        return RelationsList;
    }

    public List<edu.aku.models.RegisterOptionsModel> getNationalityList() {
        return NationalityList;
    }

    public List<edu.aku.models.RegisterOptionsModel> getCurrentCountryList() {
        return CurrentCountryList;
    }

    public List<edu.aku.models.RegisterOptionsModel> getPermanentCountryList() {
        return PermanentCountryList;
    }

    public List<edu.aku.models.RegisterOptionsModel> getCardTypeList() {
        return CardTypeList;
    }

    public String getSubscriber() {
        return Subscriber;
    }

    public List<edu.aku.models.RegisterOptionsModel> getFamilyMembersList() {
        return FamilyMembersList;
    }

    public String getRegisterRequest() {
        return RegisterRequest;
    }
}
