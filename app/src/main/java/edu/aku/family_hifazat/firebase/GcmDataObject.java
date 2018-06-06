package edu.aku.family_hifazat.firebase;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GcmDataObject implements Serializable {

    String message = "";
    String screenToRedirect = "";
    boolean isUserExist;

    public boolean isUserExist() {
        return isUserExist;
    }

    public void setUserExist(boolean userExist) {
        isUserExist = userExist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getScreenToRedirect() {
        return screenToRedirect;
    }

    public void setScreenToRedirect(String screenToRedirect) {
        this.screenToRedirect = screenToRedirect;
    }


}
