package edu.aku.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 16-Jun-17.
 */

public class ItemModel {

    @SerializedName("id")
    public int id;

    @SerializedName("order_id")
    public int orderID;

    @SerializedName("product_id")
    public int productID;

    @SerializedName("unit_price")
    public float unitPrice;

    @SerializedName("quantity")
    public int quantity;

    @SerializedName("total_price")
    public float totalPrice;

    @SerializedName("sale_price")
    public float salePrice;

    @SerializedName("promotion_id")
    public int promotionID;

    @SerializedName("discount_percentage")
    public float discoutPercentage;

    @SerializedName("product")
    public Products product;

}
