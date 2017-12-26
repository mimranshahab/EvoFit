package edu.aku.models.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.models.Order;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Apr-17.
 */

public class OrdersWrapper {
    @SerializedName("Orders")
    public ArrayList<Order> orders;
}
