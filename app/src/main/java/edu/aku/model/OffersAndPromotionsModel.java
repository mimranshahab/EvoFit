package edu.aku.model;

/**
 * Created by khanhamza on 28-Feb-17.
 */

public class OffersAndPromotionsModel {

    private int imgItemIcon;
    private String itemTitle;
    private String actualPrice;
    private double discountPrice;
    private int discountPercentage;

    public OffersAndPromotionsModel(int imgItemIcon, String itemTitle, String actualPrice, double discountPrice, int discountPercentage) {
        this.imgItemIcon = imgItemIcon;
        this.itemTitle = itemTitle;
        this.actualPrice = actualPrice;
        this.discountPrice = discountPrice;
        this.discountPercentage = discountPercentage;
    }

    public int getImgItemIcon() {
        return imgItemIcon;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}
