package edu.aku.akuh_health_first.constatnts;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public class WebServiceConstants {

    String postmanLink = "https://www.getpostman.com/collections/1d39c488e283b95f6e23";

    public static String BASE_URL = "http://ahfapidev.aku.edu/api/";


    public static final String PARAMS_REQUEST_METHOD = "RequestMethod";
    public static final String PARAMS_REQUEST_DATA = "RequestData";
    public static final String temporaryToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ3d3cuYWt1LmVkdSIsInN1YiI6Im1ubUBtbm0uY29tIiwiaXNzIjoiTW5NIn0.PRbF6RNmjYm7ai8jQNShBmyDLlZfIWv2Z6V2KosJgjE";

    public static final String tempUserName = "mahrukh.mehmood";
    public static final String tempPassword = "yU~6PWMs(,;3Wk3qK{J0{EWhEb-tl7C";
    /**
     * REQUEST METHODS NAMES
     */

    // UserManager
    public static final String METHOD_USER_GET_REGISTER_VM = "UserManager.GetRegisterVM";
    public static final String METHOD_USER_GET_USER = "UserManager.Login";
    public static final String METHOD_USER_UPLOAD_REQUEST_FILE = "UserManager.UploadRequestFile";


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


    public static final String WS_KEY_STATUS_COMPLETED = "completed";


    public static final String WS_KEY_CATEGORY = "Category";
    public static final String WS_KEY_CATEGORY_BY_ID = "Category/{categoryId}";
    public static final String WS_KEY_SUBCATEGORY_BY_ID = "SubCategories/Category/{categoryId}";
    public static final String WS_KEY_BRAND_BY_ID = "Brands/{categoryId}";
    public static final String WS_KEY_PRODUCT_BY_ID = "Products/SubCategory";
    public static final String WS_KEY_FAVORITE = "Product/favorite";
    public static final String WS_KEY_FAVORITE_LIST = "Products/Liked";
    public static final String WS_KEY_SEARCH = "Products/Search";
    public static final String WS_KEY_STATIC_PAGE = "cms";
    public static final String WS_KEY_COUNTRIES = "countries";
    public static final String WS_KEY_CITIES = "cities/{countryID}";
    public static final String WS_KEY_ADDRESSES = "address";
    public static final String WS_KEY_ADD_ADDRESS = "address/add";
    public static final String WS_KEY_DELETE_ADDRESS = "address/delete";
    public static final String WS_KEY_EDIT_ADDRESS = "address/edit";
    public static final String WS_KEY_NOTIFICATIONS_CLEAR = "notifications/clear";
    public static final String WS_KEY_NOTIFICATIONS_GET = "notifications/get";
    public static final String WS_KEY_CONTACT_DETAIL = "sellerphone";
    public static final String WS_KEY_FEEDBACK = "feedback/add";
    public static final String WS_KEY_SELECT_ADDRESS = "address/select";
    public static final String WS_KEY_GET_SELECTED_ADDRESS = "address/selected";
    public static final String WS_KEY_ADD_ORDER = "addOrder";
    public static final String WS_KEY_UPDATE_TOKEN = "updateToken";
    public static final String WS_KEY_SEND_CODE = "sendCode";
    public static final String WS_KEY_VERIFY_CODE = "verifyCode";
    public static final String WS_KEY_ORDER_VARIABLES = "variables";
    public static final String WS_KEY_REPORT_AN_ISSUE = "issue/report";
    public static final String WS_KEY_ORDER_DETAILS = "order/details";
    public static final String WS_KEY_RATING_BAR = "rate/shopping";
    public static final String WS_KEY_INSTANT_ORDER = "instantorders";
    public static final String WS_KEY_PENDING_ORDER = "pendingorders";
    public static final String WS_KEY_COMPLETED_ORDERS = "completeorders";
    public static final String WS_KEY_DELETE_ORDER = "deleteorder/{order_id}/{user_id}";
    public static final String WS_KEY_EDIT_SCHEDULE = "order/edit/schedule";
    public static final String WS_KEY_CHANGE_PASSWORD = "changepassword";
}
