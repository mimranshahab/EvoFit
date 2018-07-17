package edu.aku.ehs.enums;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by hamza.ahmed on 3/13/2018.
 */

public enum HealthSummaryIndicatorTypes {

    GLUF,
    GLUR,
    HEIGHT,
    WEIGHT,
    BPSYSTOLIC,
    BPDIASTOLIC,
    BLOODGROUP;

    public static final int SIZE = Integer.SIZE;

    public int getValue() {
        return this.ordinal();
    }

    public static HealthSummaryIndicatorTypes forValue(int value) {
        return values()[value];
    }

    public String toStringForm() {
        return this.name();
    }

    public static HealthSummaryIndicatorTypes fromStringForm(String canonical) {
        try {
            return valueOf(HealthSummaryIndicatorTypes.class, canonical);
        } catch (IllegalArgumentException a) {
            Log.e(TAG, "health summary enum IllegalArgumentException: " + a.getMessage());
            return null;
        } catch (NullPointerException e) {
            Log.e(TAG, "health summary enum null: " + e.getMessage());
            return null;
        }
    }


}
