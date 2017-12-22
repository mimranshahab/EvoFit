package edu.aku.model.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.model.Category;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class CategoryWrapper {
    @SerializedName("Category")
    public Category category;
}
