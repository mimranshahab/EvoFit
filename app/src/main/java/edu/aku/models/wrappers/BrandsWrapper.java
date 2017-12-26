package edu.aku.models.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.models.Brands;

import java.util.ArrayList;

/**
 * Created by khanhamza on 13-Mar-17.
 */

public class BrandsWrapper {
    @SerializedName("Brands")
    public ArrayList<Brands> brands;
}
