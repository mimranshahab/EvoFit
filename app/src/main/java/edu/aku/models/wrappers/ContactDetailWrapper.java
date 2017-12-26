package edu.aku.models.wrappers;

import com.google.gson.annotations.SerializedName;
import edu.aku.models.ContactDetail;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class ContactDetailWrapper {
    @SerializedName("ContactDetails")
    public ContactDetail contactDetail;

}
