package com.structure.model.wrappers;

import com.google.gson.annotations.SerializedName;
import com.structure.model.Country;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class CountryWrapper {


    @SerializedName("Country")
    public ArrayList<Country> countries;




}
