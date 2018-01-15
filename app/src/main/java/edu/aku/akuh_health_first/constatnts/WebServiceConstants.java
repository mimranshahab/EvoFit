package edu.aku.akuh_health_first.constatnts;

import edu.aku.akuh_health_first.fragments.LoginFragment;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public class WebServiceConstants {

    String postmanLink = "https://www.getpostman.com/collections/1d39c488e283b95f6e23";

    public static String BASE_URL = "http://ahfapidev.aku.edu/api/";
    public static String PACS_URL = "https://pacsviewer.aku.edu/api/";


    public static final String PARAMS_REQUEST_METHOD = "RequestMethod";
    public static final String PARAMS_REQUEST_DATA = "RequestData";
    public static final String requestor = "aku.edu";
    public static final String temporaryToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ3d3cuYWt1LmVkdSIsInN1YiI6Im1ubUBtbm0uY29tIiwiaXNzIjoiTW5NIn0.PRbF6RNmjYm7ai8jQNShBmyDLlZfIWv2Z6V2KosJgjE";

    public static final String tempUserName = "mahrukh.mehmood";
    public static final String tempPacViews = "46015891";
    public static final String tempPassword = "yU~6PWMs(,;3Wk3qK{J0{EWhEb-tl7C";
    /**
     * REQUEST METHODS NAMES
     */

    // UserManager
    public static final String METHOD_USER_GET_REGISTER_VM = "UserManager.GetRegisterVM";
    public static final String METHOD_USER_GET_USER = "UserManager.Login";
    public static final String METHOD_USER_UPLOAD_REQUEST_FILE = "UserManager.UploadRequestFile";
    public static final String METHOD_PACMANAGER = "PACSManager.GetPacs";


    /**
     * CONSTANTS
     */
    public static String DEVICE_TYPE = "android";
    public static String DEVICE_TOKEN = "abc123";
    public static String CURRENCY_TYPE = "AED";
    public static String COUNTRY_ID_UAE = "AE";

    public static int CITY_ID = 0;


    public static final String WS_KEY_LOGIN = "login";
    public static final String WS_KEY_REGISTER = "register";
    public static final String WS_KEY_EDIT_PROFILE = "user/update";
    public static final String WS_KEY_FORGOT_PASSWORD = "forgotpassword";
    public static final String WS_KEY_GET_TOKEN = "GetToken";
    public static final String WS_KEY_GET_REQUESTOR= "Requestor: aku.edu";
    public static final String WS_KEY_PACS_VIEWER = "PACSViewer";
    public static final String WS_KEY_IMAGE_URL = "wado?requestType=WADO&studyUID=1.2.392.200036.9125.2.353644173100.64863020020.362418&seriesUID=1.2.392.200036.9125.3.353644173100.64863020020.362419&objectUID=1.2.392.200036.9125.9.0.253360372.1325662243.606907748&contentType=image/jpeg";


    public static final String WS_KEY_STATIC_PAGE = "cms";
    public static final String WS_KEY_ADDRESSES = "address";
    public static final String WS_KEY_ADD_ADDRESS = "address/add";
    public static final String WS_KEY_DELETE_ADDRESS = "address/delete";
    public static final String WS_KEY_EDIT_ADDRESS = "address/edit";
    public static final String WS_KEY_NOTIFICATIONS_CLEAR = "notifications/clear";
    public static final String WS_KEY_NOTIFICATIONS_GET = "notifications/get";
    public static final String WS_KEY_FEEDBACK = "feedback/add";
    public static final String WS_KEY_SELECT_ADDRESS = "address/select";
    public static final String WS_KEY_GET_SELECTED_ADDRESS = "address/selected";
    public static final String WS_KEY_UPDATE_TOKEN = "updateToken";
    public static final String WS_KEY_SEND_CODE = "sendCode";
    public static final String WS_KEY_REPORT_AN_ISSUE = "issue/report";
    public static final String WS_KEY_RATING_BAR = "rate/shopping";
}
