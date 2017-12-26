package edu.aku.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 10-Mar-17.
 */

public class SubCategory {

    @SerializedName("id")
    public int id;

    @SerializedName("category_name")
    public String categoryName;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("parent_id")
    public String parentID;

    @SerializedName("status")
    public String status;
}
