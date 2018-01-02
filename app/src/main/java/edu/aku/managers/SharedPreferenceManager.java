package edu.aku.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import edu.aku.constatnts.AppConstants;
import edu.aku.models.NotificationModel;
import edu.aku.models.UserModel;

/**
 * Class that can be extended to make available simple preference
 * setter/getters.
 *
 * Should be extended to provide Facades.
 *
 */
public class SharedPreferenceManager {
    private static SharedPreferences pref;
    private static SharedPreferenceManager factory;

    public static SharedPreferenceManager getInstance(Context context) {
        if (pref == null)
            pref = context.getSharedPreferences("mypref", Context.MODE_PRIVATE);

        if (factory == null)
            factory = new SharedPreferenceManager();

        return factory;
    }

    public static void clearDB() {
        pref.edit().clear().commit();
    }

    public static void clearKey(String key) {
        pref.edit().remove(key).commit();
    }


    public void putValue(String key, Object value) {
        if (value instanceof Boolean)
            pref.edit().putBoolean(key, (Boolean) value).commit();
        else if (value instanceof String)
            pref.edit().putString(key, (String) value).commit();
        else if (value instanceof Integer)
            pref.edit().putInt(key, (int) value).commit();
        else if (value instanceof Long)
            pref.edit().putLong(key, (long) value).commit();

//        else
//            pref.edit().putString(key, (String) value).apply();
    }

    public int getInt(String key) {
        return pref.getInt(key, -1);
    }

    public long getLong(String key) {
        return pref.getLong(key, -1);
    }

    public String getString(String key) {
        return pref.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return pref.getBoolean(key, false);
    }

    public void putObject(String key, Object object) {
        if (object == null || object.equals("")) {
            pref.edit().putString(key, (String) object).commit();
            return;
        }

        pref.edit().putString(key, new Gson().toJson(object)).commit();
    }

    public void removeObject(String key) {
        pref.edit().remove(key).commit();
    }

    public <T> T getObject(String key, Class<T> a) {
        String json = pref.getString(key, null);
        if (json == null) {
            return null;
        } else {
            try {
                return new Gson().fromJson(json, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object stored with key "
                        + key + " is instance of other class");
            }
        }
    }

    public boolean hasValue(String key) {
        return pref.contains(key);
    }

    public UserModel getCurrentUser() {
        return getObject(AppConstants.USER_DATA, UserModel.class);
    }

    public NotificationModel getNotificationModel() {
        return getObject(AppConstants.USER_NOTIFICATION_DATA, NotificationModel.class);
    }

    public boolean isProfileRegistered() {
        return getBoolean(AppConstants.PROFILE_REGISTRATION);
    }

    public void setProfileRegistered(boolean profileRegistered) {
        putValue(AppConstants.PROFILE_REGISTRATION, profileRegistered);
    }

    public boolean isForcedRestart() {
        return getBoolean(AppConstants.FORCED_RESTART);
    }

    public void setForcedRestart(boolean isForcedRestart) {
        putValue(AppConstants.FORCED_RESTART, isForcedRestart);
    }


    protected void putStringPreference( Context context, String prefsName,String key, String value ) {

        SharedPreferences preferences = context.getSharedPreferences( prefsName, Activity.MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString( key, value );
        editor.commit();
    }

    protected String getStringPreference( Context context, String prefsName,
                                          String key ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        String value = preferences.getString( key, "" );
        return value;
    }

    protected void putBooleanPreference( Context context, String prefsName,
                                         String key, boolean value ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean( key, value );
        editor.commit();
    }

    protected boolean getBooleanPreference( Context context, String prefsName,
                                            String key ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        boolean value = preferences.getBoolean( key, false );
        return value;
    }

    protected void putIntegerPreference( Context context, String prefsName,
                                         String key, int value ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt( key, value );
        editor.commit();
    }

    protected int getIntegerPreference( Context context, String prefsName,
                                        String key ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        int value = preferences.getInt( key, -1 );
        return value;
    }

    protected void putLongPreference( Context context, String prefsName,
                                      String key, long value ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong( key, value );
        editor.commit();
    }


    protected long getLongPreference( Context context, String prefsName,
                                      String key ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        long value = preferences.getLong( key, Integer.MIN_VALUE );
        return value;
    }

    protected void putFloatPreference( Context context, String prefsName,
                                       String key, float value ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat( key, value );
        editor.commit();
    }

    protected float getFloatPreference( Context context, String prefsName,
                                        String key ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        float value = preferences.getFloat( key, Float.MIN_VALUE );
        return value;
    }

    protected void removePreference( Context context, String prefsName,
                                     String key ) {

        SharedPreferences preferences = context.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();

        editor.remove( key );
        editor.commit();
    }
}
