package com.structure.model.wrappers;

import com.google.gson.annotations.SerializedName;
import com.structure.model.Order;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Apr-17.
 */

public class OrdersWrapper {
    @SerializedName("Orders")
    public ArrayList<Order> orders;
}
