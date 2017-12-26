package edu.aku.models;

import com.google.gson.annotations.SerializedName;

public class ChoiceList {
    @SerializedName("Disabled")
    public boolean Disabled;
    @SerializedName("Group")
    public String Group;
    @SerializedName("Selected")
    public boolean Selected;
    @SerializedName("Text")
    public String Text;
    @SerializedName("Value")
    public String Value;
}
