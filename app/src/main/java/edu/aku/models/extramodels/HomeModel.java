package edu.aku.models.extramodels;

/**
 * Created by khanhamza on 23-Feb-17.
 */

public class HomeModel {


    private String mHeading;
    private String mSubHeading;
    private String mImage;


    public HomeModel(String heading, String subHeading, String imageURL) {
        this.mHeading = heading;
        this.mSubHeading = subHeading;
        this.mImage = imageURL;
    }

    public String getImageURL() {
        return mImage;
    }

    public String getHeading() {
        return mHeading;
    }

    public String getSubHeading() {
        return mSubHeading;
    }


}
