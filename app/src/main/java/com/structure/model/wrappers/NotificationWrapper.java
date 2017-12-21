package com.structure.model.wrappers;

import com.google.gson.annotations.SerializedName;
import com.structure.model.NotificationModel;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class NotificationWrapper {
    @SerializedName("Notifications")
    public ArrayList<NotificationModel> notifications;

}
