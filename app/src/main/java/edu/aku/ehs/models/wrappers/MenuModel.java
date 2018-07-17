package edu.aku.ehs.models.wrappers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aqsa.sarwar on 2/19/2018.
 */

public class MenuModel {

    @Expose
    @SerializedName("TotalCount")
    private int TotalCount;
    @Expose
    @SerializedName("NotificationCount")
    private int NotificationCount;
    @Expose
    @SerializedName("ImageURL")
    private String ImageURL;
    @Expose
    @SerializedName("Controller")
    private String Controller;
    @Expose
    @SerializedName("Action")
    private String Action;
    @Expose
    @SerializedName("Title")
    private String Title;

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int TotalCount) {
        this.TotalCount = TotalCount;
    }

    public int getNotificationCount() {
        return NotificationCount;
    }

    public void setNotificationCount(int NotificationCount) {
        this.NotificationCount = NotificationCount;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public String getController() {
        return Controller;
    }

    public void setController(String Controller) {
        this.Controller = Controller;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String Action) {
        this.Action = Action;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
}
