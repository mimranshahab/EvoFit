package edu.aku.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

/**
 * Created by khanhamza on 11-Apr-17.
 */

public class OrderVariable {


    private boolean isServerTimeChanged = false;


    @SerializedName("id")
    public int id;

    @SerializedName("service_fee")
    public int serviceFee;

    @SerializedName("delivery_time")
    public String deliveryTimeInString;

    @SerializedName("minimum_order")
    public double minimumOrder;

    @SerializedName("server_time")
    public String serverTime;


    public String getServerTime() {


        if (isServerTimeChanged) {
            return serverTime;
        }


        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df.parse(serverTime);
//            df.setTimeZone(TimeZone.getDefault());
            serverTime = df.format(date);

//            SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df2.setTimeZone(TimeZone.getDefault());
            serverTime = df2.format(date);

//            serverTime = addTimeTwoHours(serverTime);
            isServerTimeChanged = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return serverTime;
    }


    public String addTimeTwoHours(String time) {
        /**
         * @param s H:m timestamp, i.e. [Hour in day (0-23)]:[Minute in hour (0-59)]
         * @return total minutes after 00:00
         */

        String[] hourMin = time.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);


        hour = hour + 2;
        if (hour >= 24) {
            hour = hour - 24;
        }
        String newTime;
        if (mins < 10) {
            newTime = time + " - " + String.valueOf(hour) + ":0" + String.valueOf(mins);
        } else {
            newTime = time + " - " + String.valueOf(hour) + ":" + String.valueOf(mins);
        }

        return newTime;
    }


    public long getUnixTime() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = new Date();
        date2 = df.parse(getServerTime());
        long epoch = date2.getTime();
        Log.d(TAG, "getUnixTime: EPOCH UNIX" + epoch);
        return epoch;
    }

}
