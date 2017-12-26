package edu.aku.models.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.models.Category;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class CategoryWrapper {
    @SerializedName("Category")
    public Category category;
}
