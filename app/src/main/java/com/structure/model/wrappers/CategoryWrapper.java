package com.structure.model.wrappers;

import com.google.gson.annotations.SerializedName;
import com.structure.model.Category;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class CategoryWrapper {
    @SerializedName("Category")
    public Category category;
}
