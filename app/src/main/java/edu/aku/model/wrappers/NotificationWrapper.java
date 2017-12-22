package edu.aku.model.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.model.NotificationModel;

import java.util.ArrayList;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class NotificationWrapper {
    @SerializedName("Notifications")
    public ArrayList<NotificationModel> notifications;

}
