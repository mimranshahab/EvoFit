//package edu.aku.enums;
//
//import org.webrtc.SessionDescription;
//
//import static com.aaloo.android.enums.CallSessionTags.CANDIDATE;
//
///**
// * Created by khanhamza on 20/11/2017.
// */
//
//public enum CallSessionState {
//    CREATE, INPROGRESS, INVITED, ACCEPTED, DECLINED, INITIATED, WEBRTC, NOTIFY, LEAVE;
//
//    public String canonicalForm() {
//        return this.name().toLowerCase();
//    }
//
//    public static CallSessionState fromCanonicalForm(String canonical) {
//        return (CallSessionState) valueOf(CallSessionState.class, canonical.toUpperCase());
//    }
//
//    public static CallSessionState getState(String status) {
//        switch (status) {
//            default:
//            case "create":
//                return CREATE;
//
//            case "inprogress":
//                return INPROGRESS;
//
//            case "invited":
//                return INVITED;
//
//            case "accepted":
//                return ACCEPTED;
//
//            case "declined":
//                return DECLINED;
//
//            case "initiated":
//                return INITIATED;
//
//            case "webrtc":
//                return WEBRTC;
//
//            case "notify":
//                return NOTIFY;
//
//            case "leave":
//                return LEAVE;
//        }
//    }
//
//    public static String getState(CallSessionState status) {
//        switch (status) {
//            case NOTIFY:
//                return "notify";
//            default:
//            case CREATE:
//                return "create";
//            case INPROGRESS:
//                return "inprogress";
//            case INVITED:
//                return "invited";
//            case ACCEPTED:
//                return "accepted";
//            case DECLINED:
//                return "declined";
//            case INITIATED:
//                return "initiated";
//            case WEBRTC:
//                return "webrtc";
//            case LEAVE:
//                return "leave";
//
//        }
//    }
//}
