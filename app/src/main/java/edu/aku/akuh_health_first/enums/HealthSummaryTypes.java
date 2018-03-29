package edu.aku.akuh_health_first.enums;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by hamza.ahmed on 3/13/2018.
 */

public enum HealthSummaryTypes {

    Allergies,
    ClinicalLaboratory,
    Radiology,
    MedicationProfile,
    ImmunizationProfile,
    LastVisit,
    FutureAppointment;

    public static final int SIZE = java.lang.Integer.SIZE;

    public int getValue() {
        return this.ordinal();
    }

    public static HealthSummaryTypes forValue(int value) {
        return values()[value];
    }

    public String toStringForm() {
        return this.name();
    }

    public static HealthSummaryTypes fromStringForm(String canonical) {
        try {
            return valueOf(HealthSummaryTypes.class, canonical);
        } catch (IllegalArgumentException a) {
            Log.e(TAG, "health summary enum IllegalArgumentException: " + a.getMessage());
            return null;
        } catch (NullPointerException e) {
            Log.e(TAG, "health summary enum null: " + e.getMessage());
            return null;
        }
    }

//    public static HealthSummaryTypes getState(String status) {
//        switch (status) {
//
//            case "Allergies":
//                return Allergies;
//            default:
//            case "Clinical Laboratory":
//                return ClinicalLaboratory;
//
//            case "Radiology":
//                return Radiology;
//
//            case "Active Medications":
//                return ActiveMedication;
//
//            case "Immunization":
//                return Immunization;
//
//            case "Last Visit(s)":
//                return LastVisit;
//
//            case "Future Appointment(s)":
//                return FutureAppointment;
//        }
//    }
//
//    public static String getState(HealthSummaryTypes status) {
//        switch (status) {
//            case Allergies:
//                return "Allergies";
//            default:
//            case ClinicalLaboratory:
//                return "Clinical Laboratory";
//            case Radiology:
//                return "Radiology";
//            case ActiveMedication:
//                return "Active Medications";
//            case Immunization:
//                return "Immunization";
//            case LastVisit:
//                return "Last Visit(s)";
//            case FutureAppointment:
//                return "Future Appointment(s)";
//        }
//    }


}
