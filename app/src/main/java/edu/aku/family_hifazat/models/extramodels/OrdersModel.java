package edu.aku.family_hifazat.models.extramodels;

/**
 * Created by khanhamza on 23-Feb-17.
 */

public class OrdersModel {


    private String orderID;
    private String orderTime;
    private String deliverDate;
    private String deliverTIme;
    private String totalPrice;

    public OrdersModel(String orderID, String orderTime, String deliverDate, String deliverTIme, String totalPrice) {
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.deliverDate = deliverDate;
        this.deliverTIme = deliverTIme;
        this.totalPrice = totalPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getDeliverTIme() {
        return deliverTIme;
    }

    public void setDeliverTIme(String deliverTIme) {
        this.deliverTIme = deliverTIme;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
