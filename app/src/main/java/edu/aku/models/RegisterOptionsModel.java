package edu.aku.models;

import com.google.gson.annotations.SerializedName;

public class RegisterOptionsModel {
    @SerializedName("Disabled")
    private boolean Disabled;
    @SerializedName("Group")
    private String Group;
    @SerializedName("Selected")
    private boolean Selected;
    @SerializedName("Text")
    private String Text;
    @SerializedName("Value")
    private String Value;


    public boolean isDisabled() {
        return Disabled;
    }

    public String getGroup() {
        return Group;
    }

    public boolean isSelected() {
        return Selected;
    }

    public String getText() {
        return Text;
    }

    public String getValue() {
        return Value;
    }


    @Override
    public String toString() {
        return getText();
    }
}
