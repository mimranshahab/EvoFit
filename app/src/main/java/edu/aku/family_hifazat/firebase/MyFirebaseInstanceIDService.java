package edu.aku.family_hifazat.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import edu.aku.family_hifazat.activities.HomeActivity;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.managers.SharedPreferenceManager;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.sending_model.RegisteredDeviceModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;


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
        RegisteredDeviceModel object = prefHelper.getObject(AppConstants.KEY_REGISTERED_DEVICE, RegisteredDeviceModel.class);
        if (object == null) {
            object = new RegisteredDeviceModel();
            object.setDevicetoken(token);
            prefHelper.putObject(AppConstants.KEY_REGISTERED_DEVICE, object);
        } else {
            object.setDevicetoken(token);
            prefHelper.putObject(AppConstants.KEY_REGISTERED_DEVICE, object);
            insertRegisteredDevice(object);
        }


        Log.d(TAG, "sendRegistrationToServer: " + "---------" + token);
    }


    private void insertRegisteredDevice(RegisteredDeviceModel registeredDeviceModel) {
        new WebServices(getApplicationContext(),
                SharedPreferenceManager.getInstance(getApplicationContext()).getString(AppConstants.KEY_TOKEN),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIAnyObject(WebServiceConstants.METHOD_USER_INSERT_REGISTERED_DEVICE,
                        registeredDeviceModel.toString(),
                        new WebServices.IRequestWebResponseAnyObjectCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<Object> webResponse) {
                                boolean isDataInserted = false;
                                if (webResponse.result instanceof Boolean) {
                                    isDataInserted = (boolean) webResponse.result;
                                }
                            }

                            @Override
                            public void onError(Object object) {

                            }
                        });
    }
}