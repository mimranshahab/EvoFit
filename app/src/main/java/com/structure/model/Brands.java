package com.structure.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 13-Mar-17.
 */

public class Brands {

    public Brands(int id, String name, String image, int categoryID) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("image")
    public String image;

    @SerializedName("status")
    public String status;

    @SerializedName("category_id")
    public int categoryID;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("updated_at")
    public  String updatedAt;
}
