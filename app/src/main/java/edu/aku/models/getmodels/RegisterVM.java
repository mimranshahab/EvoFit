package edu.aku.models.getmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import edu.aku.models.ChoiceList;

public class RegisterVM {
    @SerializedName("GenderList")
    public List<ChoiceList> ChoiceList;
    @SerializedName("RelationsList")
    public List<ChoiceList> RelationsList;
    @SerializedName("NationalityList")
    public List<ChoiceList> NationalityList;
    @SerializedName("CurrentCountryList")
    public List<ChoiceList> CurrentCountryList;
    @SerializedName("PermanentCountryList")
    public List<ChoiceList> PermanentCountryList;
    @SerializedName("CardTypeList")
    public List<ChoiceList> CardTypeList;
    @SerializedName("Subscriber")
    public String Subscriber;
    @SerializedName("FamilyMembersList")
    public List<ChoiceList> FamilyMembersList;
    @SerializedName("RegisterRequest")
    public String RegisterRequest;
}
