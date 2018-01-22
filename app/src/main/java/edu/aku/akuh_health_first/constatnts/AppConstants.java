package edu.aku.akuh_health_first.constatnts;

import android.os.Environment;

import edu.aku.akuh_health_first.BaseApplication;


/**
 * Created by khanhamza on 4/20/2017.
 */

public class AppConstants {

    public static final String INPUT_DATE_FORMAT = "yyyy-dd-MM hh:mm:ss";
    public static final String INPUT_DATE_FORMAT_AM_PM = "yyyy-dd-MM hh:mm:ss a";
    public static final String OUTPUT_DATE_FORMAT = "EEEE dd,yyyy";
    public static final String INPUT_TIME_FORMAT = "yyyy-dd-MM hh:mm:ss a";
    public static final String OUTPUT_TIME_FORMAT = "hh:mm a";
    public static final String OUTPUT_DATE_TIME_FORMAT = "EEEE dd,yyyy hh:mm a";

    public static final String GENERAL_DATE_FORMAT = "dd-MM-yy";
    public static final String ROOT_MEDIA_PATH = Environment.getExternalStorageDirectory().getPath()
            + "/" + BaseApplication.getApplicationName() + "/Media";
    public static final String DOC_PATH = ROOT_MEDIA_PATH + "/Docs";


    public static final String CNIC_MASK = "99999-9999999-9";
    public static final String CARD_MASK = "9999-9999-9999";
    public static final String MR_NUMBER_MASK = "999-99-99";


    public static final int REQUEST_TIME_OUT = 25 * 1000;


    /*******************Preferences KEYS******************/
    public static final String KEY_CURRENT_USER_MODEL = "userModel";
    public static final String KEY_CARD_MEMBER_DETAIL = "cardMemberDetail";
    public static final String USER_NOTIFICATION_DATA = "USER_NOTIFICATION_DATA";
    public static String PROFILE_REGISTRATION = "profile_registration";
    public static String FORCED_RESTART = "forced_restart";
    public static final String KEY_REGISTER_VM = "register_vm";


    public static String RESOURCE_PART = "Android";


}
