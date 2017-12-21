package com.structure.model;

import com.structure.model.extramodels.OrdersModel;

import java.util.ArrayList;

/**
 * Created by khanhamza on 01-Mar-17.
 */

public class OrdersModelArrayList {

    private ArrayList<OrdersModel> arrayListOrders;

    public OrdersModelArrayList(ArrayList<OrdersModel> arrayListOrdersModel) {
        this.arrayListOrders = arrayListOrdersModel;
    }

    public ArrayList<OrdersModel> getArrayListOrders() {
        return arrayListOrders;
    }

    public void setArrayListOrders(ArrayList<OrdersModel> arrayListOrdersModel) {
        this.arrayListOrders = arrayListOrdersModel;
    }
}
