package edu.aku.model;

import com.google.gson.annotations.SerializedName;
import edu.aku.model.extramodels.AddressModel;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Apr-17.
 */

public class Order {


    private boolean isExpanded = false;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


    @SerializedName("id")
    public int id;

    public String getOrderID() {
        return String.format("%05d", id);
    }

    @SerializedName("user_id")
    public int userID;

    @SerializedName("items_ids")
    public String itemIDs;

    @SerializedName("quantities")
    public String
            quantities;

    @SerializedName("total_amount")
    public float totalAmount;

    @SerializedName("discount")
    public float discount;

    @SerializedName("final_amount")
    public float finalAmount;

    @SerializedName("shipping_cost")
    public float shippingCost;

    @SerializedName("address_id")
    public int addressID;

    @SerializedName("shipped_to_address")
    public AddressModel shippedToAddress;

    @SerializedName("additional_notes")
    public String additionalNotes;

    @SerializedName("payment_method")
    public String paymentMethod;

    @SerializedName("status")
    public String status;

    @SerializedName("delivery_datetime")
    public String deliveryDate;

    @SerializedName("delivery_hour")
    public int deliveryHour;

    @SerializedName("delivery_hour_string")
    public String deliveryHourString;

    @SerializedName("is_instant")
    public int isInstant;

    //Product for editiing use.

    @SerializedName("Products")
    public ArrayList<Products> products;

    @SerializedName("order_time")
    public String orderTime;

    @SerializedName("delivery_time")
    public String deliveryTime;

    @SerializedName("minimum_order")
    public double minimumOrder;

    @SerializedName("items")
    public ArrayList<ItemModel> items;


    @SerializedName("frequency")
    public int frequency;

    public String getFrequency() {
        if (frequency == 1) {
            return "One Time";
        } else if (frequency == 2) {
            return "Weekly";
        } else if (frequency == 3) {
            return "Monthly";
        } else {
            return "Frequency undefined";
        }
    }


    @SerializedName("service_fee")
    public int serviceFee;

    @SerializedName("seller_phone")
    public String sellerPhone;

    @SerializedName("status_schedule")
    public String statusSchedule;


}
