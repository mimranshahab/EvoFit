package edu.aku.family_hifazat.firebase;

import android.os.CountDownTimer;
import android.os.Looper;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import edu.aku.family_hifazat.activities.HomeActivity;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.managers.SharedPreferenceManager;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.sending_model.InsertRegisteredDeviceModel;
import edu.aku.family_hifazat.models.sending_model.RegisteredDeviceModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;

import static edu.aku.family_hifazat.constatnts.AppConstants.KEY_FIREBASE_TOKEN_UPDATED;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    SharedPreferenceManager prefHelper;


    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token: " + refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        prefHelper = SharedPreferenceManager.getInstance(getApplicationContext());
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        InsertRegisteredDeviceModel object = prefHelper.getObject(AppConstants.KEY_INSERT_REGISTERED_DEVICE, InsertRegisteredDeviceModel.class);
        RegisteredDeviceModel object2 = prefHelper.getObject(AppConstants.KEY_REGISTERED_DEVICE, RegisteredDeviceModel.class);


        if (object == null) {
            object = new InsertRegisteredDeviceModel();
            object.setDevicetoken(token);
            prefHelper.putObject(AppConstants.KEY_INSERT_REGISTERED_DEVICE, object);
        } else {
            object.setDevicetoken(token);
            prefHelper.putObject(AppConstants.KEY_INSERT_REGISTERED_DEVICE, object);
            prefHelper.putValue(KEY_FIREBASE_TOKEN_UPDATED, true);
        }


        if (object2 == null) {
            object2 = new RegisteredDeviceModel();
            object2.setDevicetoken(token);
            prefHelper.putObject(AppConstants.KEY_REGISTERED_DEVICE, object2);
        } else {
            object2.setDevicetoken(token);
            prefHelper.putObject(AppConstants.KEY_REGISTERED_DEVICE, object);
        }


        Log.d(TAG, "sendRegistrationToServer: " + "---------" + token);
    }



}