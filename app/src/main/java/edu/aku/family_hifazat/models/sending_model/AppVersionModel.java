package edu.aku.family_hifazat.models.sending_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.aku.family_hifazat.managers.retrofit.GsonFactory;

/**
 * Created by hamza.ahmed on 4/25/2018.
 */

public class AppVersionModel {


    @Expose
    @SerializedName("RecordMessage")
    private String recordmessage;
    @Expose
    @SerializedName("RecordFound")
    private String recordfound;
    @Expose
    @SerializedName("LastFileUser")
    private String lastfileuser;
    @Expose
    @SerializedName("LastFileDateTime")
    private String lastfiledatetime;
    @Expose
    @SerializedName("AndAllowOldVersion")
    private String andallowoldversion;
    @Expose
    @SerializedName("IosAllowOldVersion")
    private String iosallowoldversion;
    @Expose
    @SerializedName("AndAppVersionCode")
    private int andappversioncode;
    @Expose
    @SerializedName("AndAppVersionName")
    private String andappversionname;
    @Expose
    @SerializedName("IosAppVersionCode")
    private int iosappversioncode;
    @Expose
    @SerializedName("IosAppVersionName")
    private String iosappversionname;
    @Expose
    @SerializedName("ApplicationName")
    private String applicationname;
    @Expose
    @SerializedName("OrganizationName")
    private String organizationname;


    public String getRecordmessage() {
        return recordmessage;
    }

    public void setRecordmessage(String recordmessage) {
        this.recordmessage = recordmessage;
    }

    public String getRecordfound() {
        return recordfound;
    }

    public void setRecordfound(String recordfound) {
        this.recordfound = recordfound;
    }

    public String getLastfileuser() {
        return lastfileuser;
    }

    public void setLastfileuser(String lastfileuser) {
        this.lastfileuser = lastfileuser;
    }

    public String getLastfiledatetime() {
        return lastfiledatetime;
    }

    public void setLastfiledatetime(String lastfiledatetime) {
        this.lastfiledatetime = lastfiledatetime;
    }

    public String getAndallowoldversion() {
        return andallowoldversion;
    }

    public void setAndallowoldversion(String andallowoldversion) {
        this.andallowoldversion = andallowoldversion;
    }

    public String getIosallowoldversion() {
        return iosallowoldversion;
    }

    public void setIosallowoldversion(String iosallowoldversion) {
        this.iosallowoldversion = iosallowoldversion;
    }

    public int getAndappversioncode() {
        return andappversioncode;
    }

    public void setAndappversioncode(int andappversioncode) {
        this.andappversioncode = andappversioncode;
    }

    public String getAndappversionname() {
        return andappversionname;
    }

    public void setAndappversionname(String andappversionname) {
        this.andappversionname = andappversionname;
    }

    public int getIosappversioncode() {
        return iosappversioncode;
    }

    public void setIosappversioncode(int iosappversioncode) {
        this.iosappversioncode = iosappversioncode;
    }

    public String getIosappversionname() {
        return iosappversionname;
    }

    public void setIosappversionname(String iosappversionname) {
        this.iosappversionname = iosappversionname;
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname;
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }


    @Override
    public String toString() {
        return GsonFactory.getSimpleGson().toJson(this);
    }
}
