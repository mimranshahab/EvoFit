package com.structure.model.wrappers;

import com.google.gson.annotations.SerializedName;
import com.structure.model.ContactDetail;

/**
 * Created by khanhamza on 11-Mar-17.
 */

public class ContactDetailWrapper {
    @SerializedName("ContactDetails")
    public ContactDetail contactDetail;

}
