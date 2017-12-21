package com.structure.model.wrappers;

import com.google.gson.annotations.SerializedName;
import com.structure.model.Products;

import java.util.ArrayList;

/**
 * Created by khanhamza on 13-Mar-17.
 */

public class ProductsWrapper {
    @SerializedName("Products")
    public ArrayList<Products> products;

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    private float totalPrice = 0.0f;

    public float getTotalPrice() {
        if (products.isEmpty()) {
            return 0.0f;
        }

        for (Products item : products) {
            totalPrice += (item.price * item.itemQuantityInCart);
        }
        return totalPrice;
    }

    private void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
