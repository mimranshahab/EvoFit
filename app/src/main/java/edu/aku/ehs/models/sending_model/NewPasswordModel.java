package edu.aku.ehs.models.sending_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.ehs.managers.retrofit.GsonFactory;

/**
 * Created by hamza.ahmed on 4/25/2018.
 */

public class NewPasswordModel {

    @Expose
    @SerializedName("Password")
    private String password;
    @Expose
    @SerializedName("PasswordResetCode")
    private String passwordresetcode;
    @Expose
    @SerializedName("CardNumber")
    private String cardnumber;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordresetcode() {
        return passwordresetcode;
    }

    public void setPasswordresetcode(String passwordresetcode) {
        this.passwordresetcode = passwordresetcode;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }


    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }
}
