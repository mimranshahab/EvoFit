package edu.aku.model.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.model.Country;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class CountryWrapper {


    @SerializedName("Country")
    public ArrayList<Country> countries;




}
