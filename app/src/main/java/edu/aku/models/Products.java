package edu.aku.models;

import com.google.gson.annotations.SerializedName;
import edu.aku.constatnts.WebServiceConstants;

/**
 * Created by khanhamza on 13-Mar-17.
 */

public class Products {



    public Products() {
    }

    public Products(int id, String productName, String productDescription, String productImage, float price, int quantity) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.price = price;
        this.quantity = quantity;
    }

    @SerializedName("id")
    public int id;

    @SerializedName("product_name")
    public String productName;

    @SerializedName("product_description")
    public String productDescription;

    @SerializedName("product_image")
    public String productImage;

    @SerializedName("price")
    public float price;

    @SerializedName("delivery_days")
    public int deliveryDays;

    @SerializedName("quantity")
    public int quantity;


    @SerializedName("weight")
    public String weight;

    @SerializedName("tax")
    public double tax;

    @SerializedName("courier")
    public String courier;

    @SerializedName("is_favorite")
    public String isFavorite = "0";

    @SerializedName("shipping_cost")
    public String shippingCost;

    @SerializedName("return_policy")
    public String returnPolicy;

    @SerializedName("category_id")
    public int categoryID;


    public int itemQuantityInCart = 0;


    private String currencyType = WebServiceConstants.CURRENCY_TYPE;


    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setItemQuantityInCart(int itemQuantityInCart) {
        this.itemQuantityInCart = itemQuantityInCart;
    }

    public int getItemQuantityInCart() {
        return itemQuantityInCart;
    }



//    @Override
//    public boolean equals(Object obj) {
//        Products product = (Products) obj;
//        if (obj == null) {
//            return false;
//        } else {
//            return this.id == product.id;
//        }
//    }
//
//    @Override
//    public int hashCode() {
//        return this.id;
//    }
}
