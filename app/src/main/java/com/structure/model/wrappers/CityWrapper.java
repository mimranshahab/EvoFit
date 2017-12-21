package com.structure.model.wrappers;

import com.google.gson.annotations.SerializedName;
import com.structure.model.City;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class CityWrapper {
    @SerializedName("Cities")
    public ArrayList<City> cities;

}
