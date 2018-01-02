package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by khanhamza on 10-Mar-17.
 */

public class Category {


    public Category(int id, String categoryName, String subtitle, String image, ArrayList<Category> subCategories) {
        this.id = id;
        this.categoryName = categoryName;
        this.subtitle = subtitle;
        this.image = image;
        this.subCategories = subCategories;
    }

    public Category(int id, String categoryName, String subtitle, String image) {
        this.id = id;
        this.categoryName = categoryName;
        this.subtitle = subtitle;
        this.image = image;
    }


    @SerializedName("id")
    public int id;

    @SerializedName("category_name")
    public String categoryName;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("parent_id")
    public int parentID;

    //    @SerializedName("status")
    public String status;

    @SerializedName("subtitle")
    public String subtitle;

    @SerializedName("image")
    public String image;

    @SerializedName("subcategories")
    public ArrayList<Category> subCategories;

}
