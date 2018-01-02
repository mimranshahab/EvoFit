package edu.aku.akuh_health_first.models.wrappers;

import com.google.gson.annotations.SerializedName;

import edu.aku.akuh_health_first.models.extramodels.AddressModel;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class AddressWrapper {
    @SerializedName("Address")
    public ArrayList<AddressModel> addresses;

}
