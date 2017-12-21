package com.structure.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public class UserModel {

    @SerializedName("id")
    public int userID;

    @SerializedName("role_id")
    public int roleID;

    @SerializedName("full_name")
    public String userName;

    @SerializedName("email")
    public String userEmail;

    @SerializedName("mobile_no")
    public String userPhoneNumber;

    @SerializedName("image")
    public String userProfilePictureURL;

//
//    @SerializedName("postal_code")
//    public String postalCode;
//
//    @SerializedName("address")
//    public String userAddress;

    @SerializedName("notification_status")
    public String notificationStatus;


    @SerializedName("is_verified")
    public int isVerified;

    @SerializedName("_token")
    public String token;


    public boolean getIsVerified() {
        return isVerified == 1;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }
}
