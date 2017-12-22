package edu.aku.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class InstantOrderModel {

    @SerializedName("order_id")
    private String orderID;
    private boolean isExpanded = false;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public InstantOrderModel(String orderID) {
        this.orderID = orderID;
    }


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

}
