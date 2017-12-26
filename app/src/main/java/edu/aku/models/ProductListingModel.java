package edu.aku.models;

/**
 * Created by khanhamza on 23-Feb-17.
 */

public class ProductListingModel {


    // Permanent Properties
    private int itemImage;
    private String itemTitle;
    private String itemWeight;
    private String weightUnit;
    private String currencyType;
    private String itemPrice;
    private int itemQuantity;
    private boolean isItemLiked;



// Necessary Properties Constructor

    public ProductListingModel(int itemImage, String itemTitle, String itemWeight, String weightUnit, String currencyType, String itemPrice) {
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
        this.itemWeight = itemWeight;
        this.weightUnit = weightUnit;
        this.currencyType = currencyType;
        this.itemPrice = itemPrice;
        this.itemQuantity = 1;
        this.isItemLiked = false;
    }


    // for Favorite fragment
    public ProductListingModel(int itemImage, String itemTitle, String itemWeight, String weightUnit, String currencyType, String itemPrice, boolean isItemLiked) {
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
        this.itemWeight = itemWeight;
        this.weightUnit = weightUnit;
        this.currencyType = currencyType;
        this.itemPrice = itemPrice;
        this.itemQuantity = 1;
        this.isItemLiked = isItemLiked;
    }


    // for Products Lists


    public ProductListingModel(int itemImage, String itemTitle) {
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
    }

    public int getItemImage() {
        return itemImage;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public boolean isItemLiked() {
        return isItemLiked;
    }

    public void setItemLiked(boolean itemLiked) {
        isItemLiked = itemLiked;
    }
}
