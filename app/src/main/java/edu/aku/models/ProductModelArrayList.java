package edu.aku.models;

import java.util.ArrayList;

/**
 * Created by khanhamza on 01-Mar-17.
 */

public class ProductModelArrayList {

    private ArrayList<ProductListingModel> arrayListProductListing;

    public ProductModelArrayList(ArrayList<ProductListingModel> arrayListProductListing) {
        this.arrayListProductListing = arrayListProductListing;
    }

    public ArrayList<ProductListingModel> getArrayListProductListing() {
        return arrayListProductListing;
    }

    public void setArrayListProductListing(ArrayList<ProductListingModel> arrayListProductListing) {
        this.arrayListProductListing = arrayListProductListing;
    }
}
