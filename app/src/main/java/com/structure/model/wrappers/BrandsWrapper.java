package com.structure.model.wrappers;

import com.google.gson.annotations.SerializedName;
import com.structure.model.Brands;

import java.util.ArrayList;

/**
 * Created by khanhamza on 13-Mar-17.
 */

public class BrandsWrapper {
    @SerializedName("Brands")
    public ArrayList<Brands> brands;
}
