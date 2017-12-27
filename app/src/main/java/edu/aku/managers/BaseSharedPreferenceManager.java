package edu.aku.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.gson.Gson;
import edu.aku.activities.MainActivity;
import edu.aku.models.UserModel;
import edu.aku.models.extramodels.AddressModel;

import java.util.Locale;

public class BaseSharedPreferenceManager extends SharedPreferenceManager {
    private static final String FILENAME = "prefrences";
    private static final String KEY_USER = "user";
    private static final String KEY_GUEST = "guest";
    private static final String KEY_FIREBASE_TOKEN = "firebase";
    private static final String KEY_DEFAULT_LANG = "LANGUAGE";
    private static final String KEY_SELECTED_ADDRESS = "SELECTED_ADDRESS";

    private final Context context;


    public BaseSharedPreferenceManager(Context context) {
        this.context = context;
    }


    public UserModel getUser() {
        return new Gson().fromJson(
                getStringPreference(context, FILENAME, KEY_USER), UserModel.class);
    }

    public void putUser(UserModel userModel) {
        putStringPreference(context, FILENAME, KEY_USER, new Gson().toJson(userModel));
    }

    public AddressModel getSelectedAddress() {
        return new Gson().fromJson(
                getStringPreference(context, FILENAME, KEY_SELECTED_ADDRESS), AddressModel.class);
    }


    public void putSelectedAddress(AddressModel addressModel) {
        putStringPreference(context, FILENAME, KEY_SELECTED_ADDRESS, new Gson().toJson(addressModel));
    }



    public void setGuest(boolean isGuest) {
        putBooleanPreference(context, FILENAME, KEY_GUEST, isGuest);
    }

    public boolean isGuest() {
        return getBooleanPreference(context, FILENAME, KEY_GUEST);
    }

    public void putFirebaseToken(String token) {
        putStringPreference(context, FILENAME, KEY_FIREBASE_TOKEN, token);
    }

    public String getFirebaseToken() {
        return getStringPreference(context, FILENAME, KEY_FIREBASE_TOKEN);
    }

    public String getUserToken() {
        if (isGuest() || getUser().token == null) {
            return "abc123";
        } else {
            return getUser().token;
        }
    }

    public int getUserID() {
//        if (isGuest()) {
//            return 0;
//        } else {
            return getUser().userID;
//        }
    }

    public void removeLocalData() {
//        clearDB();
        removePreference(context, FILENAME, KEY_USER);
        removePreference(context, FILENAME, KEY_GUEST);
        removePreference(context, FILENAME, KEY_SELECTED_ADDRESS);
    }


    /**
     * LANGUAGE SECTION
     */


    public void putLang(Activity activity, String lang, boolean isRestartRequired) {
        Log.v("lang", "|" + lang);
        Resources resources = context.getResources();

        if (lang.equals("ar"))
            lang = "ar";
        else
            lang = "en";

        putStringPreference(context, FILENAME, KEY_DEFAULT_LANG, lang);

        DisplayMetrics dm = resources.getDisplayMetrics();
        android.content.res.Configuration conf = resources.getConfiguration();
        conf.locale = new Locale(lang);
        resources.updateConfiguration(conf, dm);

        activity.recreate();

        if (isRestartRequired) {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            activity.finish();
            activity.startActivity(intent);
        }
    }


    public void putLang2(Activity activity, String lang, boolean isRestartRequired) {
        Log.v("lang", "|" + lang);
        Resources resources = context.getResources();

        if (lang.equals("ar"))
            lang = "ar";
        else
            lang = "en";

        putStringPreference(context, FILENAME, KEY_DEFAULT_LANG, lang);

        DisplayMetrics dm = resources.getDisplayMetrics();
        android.content.res.Configuration conf = resources.getConfiguration();
        conf.locale = new Locale(lang);
        resources.updateConfiguration(conf, dm);


        if (isRestartRequired) {
            activity.recreate();
        }
    }


    public String getLang() {
        return getStringPreference(context, FILENAME, KEY_DEFAULT_LANG);
    }

    public boolean isLanguageArabic() {
        return getLang().equalsIgnoreCase("ar");
    }


}


