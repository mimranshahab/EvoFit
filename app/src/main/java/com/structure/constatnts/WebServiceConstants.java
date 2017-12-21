package com.structure.constatnts;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public class WebServiceConstants {



    public static String BASE_URL = "http://35.160.175.165/portfolio/grosta/api/";


    /**
     * CONSTANTS
     */
    public static String DEVICE_TYPE = "android";
    public static String DEVICE_TOKEN = "abc123";
    public static String CURRENCY_TYPE = "AED";
    public static String COUNRTY_ID_UAE = "AE";
    public static int CITY_ID = 0;

    public static String PAYMENT_TYPE_CREDIT_CARD = "cc";
    public static String PAYMENT_TYPE_CASH_ON_DELIVERY = "cod";

    public static String STATIC_KEY_TERMS = "terms";
    public static String STATIC_KEY_POLICIES = "policies";
    public static String STATIC_KEY_ABOUT_APP = "aboutapp";


    public static final String WS_KEY_LOGIN = "login";
    public static final String WS_KEY_REGISTER = "register";
    public static final String WS_KEY_EDIT_PROFILE = "user/update";
    public static final String WS_KEY_FORGOT_PASSWORD = "forgotpassword";


    public static final String WS_KEY_STATUS_SUBMITTED = "submitted";
    public static final String WS_KEY_STATUS_ASSIGNED = "assigned";
    public static final String WS_KEY_STATUS_IN_PROGRESS = "inprogress";
    public static final String WS_KEY_STATUS_OUT_FOR_DELIVERY = "outfordelivery";
    public static final String WS_KEY_STATUS_COMPLETED = "completed";


    public static final String WS_KEY_CATEGORY = "Category";
    public static final String WS_KEY_CATEGORY_BY_ID = "Category/{categoryId}";
    public static final String WS_KEY_USER_UPDATE = "user/update";
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
