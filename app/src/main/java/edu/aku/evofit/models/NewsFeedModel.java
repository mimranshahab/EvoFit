package edu.aku.evofit.models;

/**
 * Created by hamza.ahmed on 7/23/2018.
 */

public class NewsFeedModel {

    String userName;
    String title;
    String desc;
    String imageURL;

    public NewsFeedModel(String userName, String title, String desc, String imageURL) {
        this.userName = userName;
        this.title = title;
        this.desc = desc;
        this.imageURL = imageURL;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
