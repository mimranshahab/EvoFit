package edu.aku.models.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.models.Category;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class SubcategoriesWrapper {
    @SerializedName("SubCategories")
    public ArrayList<Category> subCategories;
}
