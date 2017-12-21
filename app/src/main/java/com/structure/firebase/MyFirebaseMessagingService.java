package com.structure.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.structure.R;
import com.structure.activities.MainActivity;
import com.structure.managers.retrofit.GsonFactory;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    public static int NOTIFICATION_ID = 1;
    NotificationCompat.Builder builder;
    private NotificationManager mNotificationManager;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());

            sendNotification(remoteMessage);
//            sendNotification(true,remoteMessage);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(RemoteMessage messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(messageBody.getData().get("msg"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

//    private void sendNotification(boolean isAdded, RemoteMessage extras) {
//        if (!isAdded) {
//            return;
//        }
//        Bundle extra= new Bundle();
//        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        GcmDataObject gcmObject = new GcmDataObject();
//        Intent intent = new Intent(this, MainActivity.class);
////        gcmObject.setAlert(extras.getData().get("alert"));
////        gcmObject.setSenderName(extras.getData().get("Sendername"));
//        gcmObject.setMsg(extras.getData().get("msg"));
//        gcmObject.setUrl(extras.getData().get("url"));
//
////        gcmObject.setPush_type(extras.getData().get("push_type"));
////        gcmObject.setBooking_id(extras.getData().get("booking_id"));
////
////        gcmObject.setObjectType(extras.getData().get("object_type"));
////        gcmObject.setDriverId(extras.getData().get("driver_id"));
////        gcmObject.setObjectId(extras.getData().get("object_id"));
//        String str = GsonFactory.getConfiguredGson().toJson(gcmObject);
//
//        extra.putString("gcmObject", str);
//        intent.putExtras(extra);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Flag added to resume
//        // running app.
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        // TODO PEnding IOntent
//        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
//                this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Smart Mart Notification")
//                .setContentText(gcmObject.getMsg())
//                .setTicker(gcmObject.getAlert())
//                .setSound(uri)
//                .setStyle(
//                        new NotificationCompat.BigTextStyle().bigText(
//                                gcmObject.getAlert()).setSummaryText(
//                                gcmObject.getAlert())).setOnlyAlertOnce(true)
//                .setAutoCancel(true).setContentText(gcmObject.getAlert());
//        mBuilder.setContentIntent(contentIntent);
//        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
//        NOTIFICATION_ID++;
//
//    }

}