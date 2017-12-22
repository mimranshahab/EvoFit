package edu.aku.constatnts;

import android.os.Environment;

import edu.aku.BaseApplication;


/**
 * Created by muhammadmuzammil on 4/20/2017.
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
    public static final String FIELD_THUMBNAIL = "THUMBNAIL";
    public static final String FIELD_USER_STATUS = "STATUS";
    public static final String AS3_BUCKET_NAME = "communication-app";
    public static final String AS3_REGION = "us-west-2";
    public static final String AS3_PROFILE_PICTURE = "profilepictures";
    public static final String AS3_GROUP_PICTURE = "grouppictures";
    public static final String AS3_MEDIA_IMAGE = "mediaimages";
    public static final int PING_INTERVAL = 5;
    public static final String AS3_ACCESS_URL = "https://" + AS3_BUCKET_NAME + ".s3-" + AS3_REGION + ".amazonaws.com/";
    public static final long ONE_DAY = 24 * 60 * 60 * 1000;
    public static final String USER_PROFILE_PICTURE_FOLDER_DIRECTORY = "userProfile";
    public static final String GROUP_IMAGE_FOLDER_DIRECTORY = "group_image";
    public static final int MESSAGE_NOTIFICATION_ID = 1705;
    public static final String USER_PROFILE_PICTURE_NAME = "me.j";
    public static final String USER_PROFILE_THUMBNAIL_NAME = "me_thumb.j";
    public static final String USER_PROFILE_PICTURE_THUMBNAIL_PATH = "/data/user/0/"+ BaseApplication.getApplicationName()+"/app_userProfile/me_thumb.j";
    public static final String USER_PROFILE_PICTURE_PATH = "/data/user/0/"+ BaseApplication.getApplicationName()+"/app_userProfile/me.j";

    public static final int REQUEST_CAPTURE_IMAGE = 100;
    public static final int REQUEST_PICK_IMAGE = 101;


    /*******************Preferences KEYS******************/
    public static final String USER_DATA = "userData";
    public static final String PRIVACY_LAST_SEEN = "lastSeen";
    public static final String PRIVACY_PROFILE_PICTURE = "profilePicture";
    public static final String PRIVACY_STATUS = "status";
    public static final String READ_RECEIPTS = "readReceipts";
    public static final String STATUS_PREFERENCE_KEY = "STATUS_LIST_KEY";
    public static final String SELECTED_STATUS_POSITION_KEY = "SELECTED_STATUS_POSITION";
    public static final String SELECTED_STATUS_KEY = "SELECTED_STATUS_KEY";
    public static final String USER_NOTIFICATION_DATA = "USER_NOTIFICATION_DATA";
    public static final String CONNECTION_TIME = "CONNECTION_TIME";
    public static final String STANZA_LISTENER_ID = "stanza_listener_id-";
    public static final String TWO_STEP_VERIFIED_ENABLE = "two_step_verified_enable";
    public static final String TWO_STEP_VERIFIED_PIN = "two_step_verified_pin";
    public static final String TWO_STEP_VERIFIED_EMAIL = "two_step_verified_email";
    public static final String API_HIT_DURATION = "apiHitDuration";


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


    /***************** OTHER CONSTANTS ***********************/
    public static final int STATUS_MAX_LENGTH = 140;
    public static final int CLEAR_TOKEN_DURATION = 30;
    public static final int VIDEO_VIEW_CIRCLE_SIZE = 800;
    public static final int VIDEO_VIEW_CORNER_RADIUS = 102;

    // XEP Related
    public static final String MULTICAST_DOMAIN = "multicast.ejabberd.tk";
    /******************SINGLETON VARIABLES*******************/
//    public static String previousNumber = null;
//    public static int verificationCounter = 0;
//    public static String verification_code = null;


    public static final String MISCALL_PREFIX = "miscall-";
    public static final int CHECK_NEED_TO_RECONNECT_DELAY = 8000;
    public static final int FIRST_RECONNECT_DELAY = 2;
    public static int RESEND_TIMER = 15;
    public static long MINIMUM_TIME_OF_LOADER = 300;
    public static int MAX_ATTACHMENT_COUNT = 10;
    public static String KEY_IS_FORWARDING_MESSAGE = "isForwardingMessage";
    public static String KEY_CHATMODEL_ID = "chatModelId";
    public static String KEY_IS_CHATMODEL = "isChatModel";
    public static String KEY_IS_SCROLL_TO_MESSAGE = "isScrollToMessage";
    public static String KEY_CHAT_MODEL = "chatModel";
    public static String KEY_MESSAGE_MODEL = "messageModel";
    public static String KEY_KEYWORD = "keyword";
    public static String KEY_CONTACT_DETAIL_MODEL = "contactDetailModel";
    public static int LIGHT_DURATION_ON = 500;
    public static int LIGHT_DURATION_OFF = 5000;
    /*********************GROUP CODES**********************/
    public static String PRESENCE_ANY_ROOM = "110";
    public static String PUBSUB_EVENT = "http://jabber.org/protocol/pubsub#event";


    public static String PROFILE_REGISTRATION = "profile_registration";
    public static String FORCED_RESTART = "forced_restart";
    public static String RESOURCE_PART = "Android";


}
