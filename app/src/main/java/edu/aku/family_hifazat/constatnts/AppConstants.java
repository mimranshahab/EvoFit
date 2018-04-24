package edu.aku.family_hifazat.constatnts;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import edu.aku.family_hifazat.BaseApplication;
import edu.aku.family_hifazat.activities.BaseActivity;
import edu.aku.family_hifazat.managers.SharedPreferenceManager;

import static android.provider.Settings.Secure.getString;


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
    public static final String KEY_UNIQUE_DEVICE_ID = "unique_device_id";


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

    public static String AboutUs = "<B>Family Hifazat</B> is a product of Aga Khan University Hospital Karachi. It provides discounts on medical services as well as patients’ access to their personal health records through web portal and mobile applications." + "<BR><BR>" +
            "The <B>Family Hifazat</B> “Patient Portal” is secure, confidential and easy to use web portal &amp; mobile apps to provide AKUH patients 24 hour access to their personal health records including diagnostic results, imaging, medications, vaccines and discharge summaries.";

    public static String NO_RECORD_FOUND = "No Record Found";


    public static String DEVICE_TYPE = "ANDROID";


    public static String getDeviceID(Context context) {

        String deviceID = SharedPreferenceManager.getInstance(context).getString(KEY_UNIQUE_DEVICE_ID);

        if (deviceID == null || deviceID.isEmpty()) {

/*String Return_DeviceID = USERNAME_and_PASSWORD.getString(DeviceID_key,"Guest");
return Return_DeviceID;*/

            TelephonyManager TelephonyMgr = (TelephonyManager) context.getApplicationContext().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

            String m_szImei = ""; // Requires
            if (TelephonyMgr != null) {
                m_szImei = TelephonyMgr.getDeviceId();
            }
// READ_PHONE_STATE

// 2 compute DEVICE ID
            String m_szDevIDShort = "35"
                    + // we make this look like a valid IMEI
                    Build.BOARD.length() % 10 + Build.BRAND.length() % 10
                    + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
                    + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
                    + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
                    + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
                    + Build.TAGS.length() % 10 + Build.TYPE.length() % 10
                    + Build.USER.length() % 10; // 13 digits
// 3 android ID - unreliable
            String m_szAndroidID = "";
            if (getString(context.getContentResolver(), Settings.Secure.ANDROID_ID) != null) {
                m_szAndroidID = getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
// 4 wifi manager, read MAC address - requires
// android.permission.ACCESS_WIFI_STATE or comes as null
            WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            String m_szWLANMAC = "";
            if (wm != null) {
                m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
            }
// 5 Bluetooth MAC address android.permission.BLUETOOTH required
            BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
            m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            String m_szBTMAC = "";
            if (m_BluetoothAdapter != null) {
                m_szBTMAC = m_BluetoothAdapter.getAddress();
            }
            System.out.println("m_szBTMAC " + m_szBTMAC);

// 6 SUM THE IDs
            String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID + m_szWLANMAC + m_szBTMAC;
            System.out.println("m_szLongID " + m_szLongID);
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
            byte p_md5Data[] = m.digest();

            String m_szUniqueID = "";
            for (int i = 0; i < p_md5Data.length; i++) {
                int b = (0xFF & p_md5Data[i]);
// if it is a single digit, make sure it have 0 in front (proper
// padding)
                if (b <= 0xF)
                    m_szUniqueID += "0";
// add number to string
                m_szUniqueID += Integer.toHexString(b);
            }
            m_szUniqueID = m_szUniqueID.toUpperCase();

            Log.i("------DeviceID------", m_szUniqueID);
            Log.d("DeviceIdCheck", "DeviceId that generated MPreferenceActivity:" + m_szUniqueID);
            SharedPreferenceManager.getInstance(context).putValue(KEY_UNIQUE_DEVICE_ID, m_szUniqueID);
            return m_szUniqueID;
        } else {
            return deviceID;
        }
    }

//    public static String getDeviceID(Context context) {
//        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//
//        final String tmDevice, tmSerial, androidId;
//        tmDevice = "" + tm.getDeviceId();
//        tmSerial = "" + tm.getSimSerialNumber();
//        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//
//        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
//        return deviceUuid.toString();
//
//    }
}
