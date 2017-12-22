package edu.aku.model.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.model.ContactDetail;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class ContactDetailWrapper {
    @SerializedName("ContactDetails")
    public ContactDetail contactDetail;

}
