package edu.aku.family_hifazat.constatnts;

import android.content.Context;
import android.os.Environment;

import edu.aku.family_hifazat.BaseApplication;
import edu.aku.family_hifazat.managers.SharedPreferenceManager;


/**
 * Created by khanhamza on 4/20/2017.
 */

public class AppConstants {

    /**
     * Date Formats
     */

    public static final String INPUT_DATE_FORMAT = "yyyy-dd-MM hh:mm:ss";
    public static final String INPUT_DATE_FORMAT_AM_PM = "yyyy-dd-MM hh:mm:ss a";
    public static final String OUTPUT_DATE_FORMAT = "EEEE dd,yyyy";
    public static final String INPUT_TIME_FORMAT = "yyyy-dd-MM hh:mm:ss a";
    public static final String OUTPUT_TIME_FORMAT = "hh:mm a";
    public static final String OUTPUT_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String OUTPUT_DATE_TIME_FORMAT = "EEEE dd,yyyy hh:mm a";
    public static final String INPUT_LAB_DATE_FORMAT_AM_PM = "mm/dd/yyyy hh:mm:ss a";

    // Custom For AKUH
    public static final String INPUT_DATE_FORMAT_IMMUNIZATION = "dd/MM/yyyy";
    public static final String GENERAL_DATE_FORMAT = "dd-MM-yy";



    /**
     * Path to save Media
     */
    public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getPath()
            + "/" + BaseApplication.getApplicationName();

    public static final String DOC_PATH = ROOT_PATH + "/Docs";

    public static String getUserFolderPath(Context context) {
        return DOC_PATH + "/" + SharedPreferenceManager.getInstance(context).getCurrentUser().getMRNumber();
    }


    /**
     * MASKING FORMATs
     */

    public static final String CNIC_MASK = "99999-9999999-9";
    public static final String CARD_MASK = "9999-9999-9999";
//    public static final String CARD_MASK = "wwww-wwww-wwww";
    public static final String MR_NUMBER_MASK = "999-99-99";



    public static final int REQUEST_TIME_OUT = 25 * 1000;


    /*************** INTENT DATA KEYS **************/
    public static final String LABORATORY_MODEL = "laboratoryModel";
    public static final String JSON_STRING_KEY = "JSON_STRING_KEY";
    public static final String IMAGE_PREVIEW_URL = "url";
    public static final String IMAGE_PREVIEW_TITLE = "title";


    /*******************Preferences KEYS******************/
    public static final String KEY_CURRENT_USER_MODEL = "userModel";
    public static final String KEY_CARD_MEMBER_DETAIL = "cardMemberDetail";
    public static final String KEY_CARD_NUMBER = "card_number";
    public static final String USER_NOTIFICATION_DATA = "USER_NOTIFICATION_DATA";
    public static String PROFILE_REGISTRATION = "profile_registration";
    public static String FORCED_RESTART = "forced_restart";
    public static final String KEY_REGISTER_VM = "register_vm";
    public static final String KEY_FIREBASE_TOKEN = "firebase_token";
    public static final String KEY_TOKEN = "getToken";
    public static final String KEY_CROSS_TAB_DATA = "cross_tab";


    /**
     * Vaccines Types
     */
    public static final String schedule = "Scheduled";
    public static final String vaccinated = "Vaccinated";
    public static final String due = "Due";
    public static final String over_due = "Overdue";

    /**
     * File Name initials if user download the pdf
     */
    public static String FILE_NAME = "AKUH-PatientReport";

    public static String AboutUs = "<B>Family Hifazat</B> is a product of Aga Khan University Hospital Karachi. It provides discounts on medical services as well as patients’ access to their personal health records through web portal and mobile applications." + "<BR><BR>"+
            "The <B>Family Hifazat</B> “Patient Portal” is secure, confidential and easy to use web portal &amp; mobile apps to provide AKUH patients 24 hour access to their personal health records including diagnostic results, imaging, medications, vaccines and discharge summaries.";

    public static String NO_RECORD_FOUND = "No Record Found";
}
