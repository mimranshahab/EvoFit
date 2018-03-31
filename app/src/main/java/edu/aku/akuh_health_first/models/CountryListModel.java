package edu.aku.akuh_health_first.models;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.akuh_health_first.models.receiving_model.RegisterOptionsModel;

public class CountryListModel implements Serializable
{

@SerializedName("CurrentCountryList")
@Expose
private List<RegisterOptionsModel> currentCountryList = null;
@SerializedName("PermanentCountryList")
@Expose
private List<RegisterOptionsModel> permanentCountryList = null;
@SerializedName("Member")
@Expose



private final static long serialVersionUID = 239803430888701467L;

public List<RegisterOptionsModel> getCurrentCountryList() {
return currentCountryList;
}

public void setCurrentCountryList(List<RegisterOptionsModel> currentCountryList) {
this.currentCountryList = currentCountryList;
}

public List<RegisterOptionsModel> getPermanentCountryList() {
return permanentCountryList;
}

public void setPermanentCountryList(List<RegisterOptionsModel> permanentCountryList) {
this.permanentCountryList = permanentCountryList;
}





}