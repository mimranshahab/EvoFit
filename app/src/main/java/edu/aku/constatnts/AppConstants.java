package edu.aku.constatnts;

import android.os.Environment;

import edu.aku.BaseApplication;


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
    public static final String USER_PROFILE_PICTURE = ROOT_MEDIA_PATH + "/" + BaseApplication.getApplicationName() + " Profile";


    public static final int REQUEST_TIME_OUT = 25 * 1000;

    public static final String PREFIX_MESSAGE = "msg-single-";
    public static final String SUFFIX_THUMB_IMAGE = "_thumb";
    public static final String USER_PROFILE_PICTURE_NAME = "me.j";
    public static final String USER_PROFILE_THUMBNAIL_NAME = "me_thumb.j";
    public static final String USER_PROFILE_PICTURE_THUMBNAIL_PATH = "/data/user/0/"+ BaseApplication.getApplicationName()+"/app_userProfile/me_thumb.j";
    public static final String USER_PROFILE_PICTURE_PATH = "/data/user/0/"+ BaseApplication.getApplicationName()+"/app_userProfile/me.j";

    public static final int REQUEST_CAPTURE_IMAGE = 100;
    public static final int REQUEST_PICK_IMAGE = 101;


    /*******************Preferences KEYS******************/
    public static final String USER_DATA = "userData";
    public static final String USER_NOTIFICATION_DATA = "USER_NOTIFICATION_DATA";
    public static String PROFILE_REGISTRATION = "profile_registration";
    public static String FORCED_RESTART = "forced_restart";
    public static final String KEY_REGISTER_VM = "register_vm";


    /********************onActivity Result Codes***************************/
    public static final int RINGTONE_PICKER_MESSAGES = 1111;
    public static final int RINGTONE_PICKER_CALLS = 1112;
    public static final int OPEN_NOTIFICATIONS_ACTIVITY = 1113;
    public static final int CREATE_BROADCAST = 1114;
    public static final int CREATE_GROUP = 1115;
    public static final int OPEN_CONTACT_SELECTION = 1116;
    public static final int OPEN_ROOM_ACTIVITY = 1117;
    public static final int OPEN_AUDIO_PICKER = 1118;
    public static final int OPEN_CONTACT_SHARE = 1119;
    public static final int CREATE_CONFERENCE = 1120;


    public static String RESOURCE_PART = "Android";


}
