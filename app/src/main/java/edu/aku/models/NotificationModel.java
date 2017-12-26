package edu.aku.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class NotificationModel {

    @SerializedName("order_id")
    private int orderID;

    @SerializedName("notification_text")
    private String notificationText;

    @SerializedName("order_status")
    private String orderStatus;


    public NotificationModel(int orderID, String notificationText) {
        this.orderID = orderID;
        this.notificationText = notificationText;
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public String getOrderStatus() {

        return orderStatus;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
}
