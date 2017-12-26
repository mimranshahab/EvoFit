package edu.aku.models.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.models.City;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class CityWrapper {
    @SerializedName("Cities")
    public ArrayList<City> cities;

}
