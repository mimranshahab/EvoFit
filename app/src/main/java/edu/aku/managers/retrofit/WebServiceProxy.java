package edu.aku.managers.retrofit;

import com.google.gson.JsonObject;
import edu.aku.constatnts.WebServiceConstants;
import edu.aku.model.AddOrder;
import edu.aku.model.ContactDetail;
import edu.aku.model.Content;
import edu.aku.model.Order;
import edu.aku.model.OrderVariable;
import edu.aku.model.UserModel;
import edu.aku.model.extramodels.AddressModel;
import edu.aku.model.wrappers.AddressWrapper;
import edu.aku.model.wrappers.AddressWrapper2;
import edu.aku.model.wrappers.BrandsWrapper;
import edu.aku.model.wrappers.CategoriesWrapper;
import edu.aku.model.wrappers.CategoryWrapper;
import edu.aku.model.wrappers.CityWrapper;
import edu.aku.model.wrappers.CountryWrapper;
import edu.aku.model.wrappers.NotificationWrapper;
import edu.aku.model.wrappers.OrdersWrapper;
import edu.aku.model.wrappers.ProductsWrapper;
import edu.aku.model.wrappers.SubcategoriesWrapper;
import edu.aku.model.wrappers.WebResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public interface WebServiceProxy {

    /*
     * @param userEmail
     * @param userPassword
     * @param userName
     * @param userPhoneNumber
     * @param deviceType
     * @param deviceToken
     * @return
     */


    @Multipart

    @POST(WebServiceConstants.WS_KEY_REGISTER)
    Call<WebResponse<UserModel>> postSignUp(
            @Part("full_name") RequestBody userName,
            @Part("email") RequestBody userEmail,
            @Part("mobile_no") RequestBody userPhoneNumber,
            @Part("password") RequestBody userPassword,
            @Part("device_type") RequestBody deviceType,
            @Part("device_token") RequestBody deviceToken,
            @Part MultipartBody.Part profilePicture
    );

    /**
     * Login
     *
     * @param userEmail
     * @param userPassword
     * @return
     */


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_LOGIN)
    Call<WebResponse<UserModel>> login(
            @Field("email") String userEmail,
            @Field("password") String userPassword);


    @Multipart
    @POST(WebServiceConstants.WS_KEY_EDIT_PROFILE)
    Call<WebResponse<UserModel>> postEditProfile(
            @Part("user_id") RequestBody userID,
            @Part("full_name") RequestBody userName,
            @Part("mobile_no") RequestBody userPhoneNumber,
            @Part MultipartBody.Part profilePicture,
            @Part("device_type") RequestBody deviceType,
            @Part("device_token") RequestBody deviceToken
    );

    /**
     * Forgot Password
     *
     * @param userEmail
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_FORGOT_PASSWORD)
    Call<WebResponse<Object>> postForgotPassword(
            @Field("email") String userEmail);


    /**
     * GET USER ADDRESSES
     *
     * @param userID
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_ADDRESSES)
    Call<WebResponse<AddressWrapper>> getAddresses(
            @Field("user_id") int userID);


    /**
     * ADD ADDRESÄS
     *
     * @param userCountryID
     * @param userCountry
     * @param userCityID
     * @param userCity
     * @param userStreet
     * @param userBuildingName
     * @param userFloor
     * @param userApartment
     * @param userNearestLandmark
     * @param userLocationType
     * @param userID
     * @return
     */


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_ADD_ADDRESS)
    Call<WebResponse<AddressModel>> addAddress(
            @Field("country_id") String userCountryID,
            @Field("country") String userCountry,
            @Field("city_id") int userCityID,
            @Field("city") String userCity,
            @Field("street_name") String userStreet,
            @Field("building_name") String userBuildingName,
            @Field("floor") String userFloor,
            @Field("appartment") String userApartment,
            @Field("nearest_landmark") String userNearestLandmark,
            @Field("location_type") String userLocationType,
            @Field("location_name") String userLocationName,
            @Field("latitude") double latitude,
            @Field("longitude") double longitude,
            @Field("user_id") int userID);


    /**
     * DELETE ADDRESS
     *
     * @param userID
     * @param addressID
     * @return
     */


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_DELETE_ADDRESS)
    Call<WebResponse<Object>> deleteAddress(
            @Field("user_id") int userID,
            @Field("id") int addressID);


    /**
     * @param addressID
     * @param userCountryID
     * @param userCountry
     * @param userCityID
     * @param userCity
     * @param userStreet
     * @param userBuildingName
     * @param userFloor
     * @param userApartment
     * @param userNearestLandmark
     * @param userLocationType
     * @param latitude
     * @param longitude
     * @param userID
     * @return
     */


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_EDIT_ADDRESS)
    Call<WebResponse<AddressModel>> editAddress(
            @Field("id") int addressID,
            @Field("country_id") String userCountryID,
            @Field("country") String userCountry,
            @Field("city_id") int userCityID,
            @Field("city") String userCity,
            @Field("street_name") String userStreet,
            @Field("building_name") String userBuildingName,
            @Field("floor") String userFloor,
            @Field("appartment") String userApartment,
            @Field("nearest_landmark") String userNearestLandmark,
            @Field("location_type") String userLocationType,
            @Field("location_name") String userLocationName,
            @Field("latitude") double latitude,
            @Field("longitude") double longitude,
            @Field("user_id") int userID);


//    /**
//     * Get Category
//     *
//     * @return
//     */
//
//    @GET(WebServiceConstants.WS_KEY_CATEGORY)
//    Call<WebResponse<CategoriesWrapper>> getCategories();


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_CATEGORY)
    Call<WebResponse<CategoriesWrapper>> getCategories(
            @Query("user_id") int userID,
            @Field("latitude") double latitude,
            @Field("longitude") double longitude,
            @Field("keyword") String keyword);

    /**
     * Category ID
     *
     * @param categoryId
     * @return
     */

    @GET(WebServiceConstants.WS_KEY_CATEGORY_BY_ID)
    Call<WebResponse<CategoryWrapper>> getSingleCategory(
            @Path("categoryId") int categoryId);


    /**
     * USER UPDATE
     *
     * @param oldPassword
     * @param password
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_CHANGE_PASSWORD)
    Call<WebResponse<Object>> postChangePassword(
            @Field("old_password") String oldPassword,
            @Field("password") String password,
            @Field("password_confirmation") String passwordConfirmation,
            @Query("user_id") int userID);


    /**
     * SubCategory ID
     *
     * @param categoryId
     * @return
     */


    @GET(WebServiceConstants.WS_KEY_SUBCATEGORY_BY_ID)
    Call<WebResponse<SubcategoriesWrapper>> getSubcategories(
            @Path("categoryId") int categoryId);


    /**
     * BRAND BY CATEGORY ID
     *
     * @param categoryId
     * @return
     */

    @GET(WebServiceConstants.WS_KEY_BRAND_BY_ID)
    Call<WebResponse<BrandsWrapper>> getBrands(
            @Path("categoryId") int categoryId);


    /**
     * @param subcategoryId
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_PRODUCT_BY_ID)
    Call<WebResponse<ProductsWrapper>> getProducts(
            @Field("subcategoryId") int subcategoryId,
            @Field("user_id") int userId,
            @Field("sortby") int sortBy);


    /**
     * FAVORITE
     *
     * @param userID
     * @param productID
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_FAVORITE)
    Call<WebResponse<Object>> setFavorite(
            @Field("user_id") int userID,
            @Field("product_id") int productID);


    /**
     * FAVORITE LIST
     *
     * @param userID
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_FAVORITE_LIST)
    Call<WebResponse<ProductsWrapper>> getFavoriteList(@Field("user_id") int userID);

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_ORDER_VARIABLES)
    Call<WebResponse<OrderVariable>> getOrderVariable(@Field("user_id") int userID);

    /**
     * SEND VERFICATION CODE
     *
     * @param phoneNumber
     * @param userID
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_SEND_CODE)
    Call<WebResponse<Object>> sendVerificationCode(
            @Field("code") String countryCode,
            @Field("phone") String phoneNumber,
            @Field("user_id") int userID
    );


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_VERIFY_CODE)
    Call<JsonObject> verifyCode(
            @Field("code") String countryCode,
            @Field("user_id") int userID
    );


    /**
     * PENDING ORDERS
     *
     * @param userID
     * @return
     */

    /**
     * POST FEEDBACK
     *
     * @param userID
     * @param feedbackMessage
     * @return
     */


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_FEEDBACK)
    Call<WebResponse<Object>> postfeedBack(
            @Field("user_id") int userID,
            @Field("suggestion") String feedbackMessage
    );

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_REPORT_AN_ISSUE)
    Call<WebResponse<Object>> reportAnIssue(
            @Field("user_id") int userID,
            @Field("email") String email,
            @Field("issue_type") String issueType,
            @Field("issue_detail") String issueDetail
    );


    /**
     * SEARCH VIA KEYWORD WEB SERVICE
     *
     * @param keyword
     * @param userID
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_SEARCH)
    Call<WebResponse<ProductsWrapper>> getSearchList(
            @Field("keyword") String keyword,
            @Field("user_id") int userID);


    /**
     * GET RATINGS
     *
     * @param userID
     * @param delivery_speed
     * @param product_accuracy
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_RATING_BAR)
    Call<WebResponse<Object>> postRatings(
            @Field("user_id") int userID,
            @Field("delivery_speed") float delivery_speed,
            @Field("product_accuracy") float product_accuracy
    );


    /**
     * GET STATIC PAGE CONTENT
     *
     * @param userID
     * @param key
     * @return
     */


    @GET(WebServiceConstants.WS_KEY_STATIC_PAGE)
    Call<WebResponse<ArrayList<Content>>> getStaticPageData(
            @Query("user_id") int userID,
            @Query("key") String key);


    /**
     * GET COUNTRIES LIST
     *
     * @return
     */

    @GET(WebServiceConstants.WS_KEY_COUNTRIES)
    Call<WebResponse<CountryWrapper>> getCountries();


    /**
     * GET CITIES
     *
     * @param countryID
     * @return
     */

    @GET(WebServiceConstants.WS_KEY_CITIES)
    Call<WebResponse<CityWrapper>> getCities(
            @Path("countryID") String countryID);


    /**
     * @param userID
     * @return
     */
    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_NOTIFICATIONS_CLEAR)
    Call<WebResponse<Object>> clearNotification(
            @Field("user_id") int userID);


    /**
     * GET NOTIFICATIONS
     *
     * @param userID
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_NOTIFICATIONS_GET)
    Call<WebResponse<NotificationWrapper>> getNotifications(
            @Field("user_id") int userID);


    /**
     * GET CONTACT DETAILS
     *
     * @param userID
     * @return
     */


    @GET(WebServiceConstants.WS_KEY_CONTACT_DETAIL)
    Call<WebResponse<ContactDetail>> getContactDetails(
            @Query("user_id") int userID);


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_GET_SELECTED_ADDRESS)
    Call<WebResponse<AddressWrapper2>> getSelectedAddress(
            @Field("user_id") int userID);


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_SELECT_ADDRESS)
    Call<WebResponse<Object>> selectAddress(
            @Field("address_id") int addressID,
            @Field("user_id") int userID);


    /**
     * ADD ORDER
     * <p>
     * Time format = 2017-04-19 13:50:55
     *
     * @param itemsIDs
     * @param quantities
     * @param addressID
     * @param deliveryDateAndTime
     * @param additionalNotes
     * @param userID
     * @return
     */

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_ADD_ORDER)
    Call<WebResponse<AddOrder>> addOrder(
            @Field("items_ids") String itemsIDs,
            @Field("quantities") String quantities,
            @Field("address_id") int addressID,
            @Field("delivery_datetime") String deliveryDateAndTime,
            @Field("additional_notes") String additionalNotes,
            @Field("user_id") int userID,
            @Field("payment_method") String paymentMethod,
            @Field("delivery_hour") int deliveryHour,
            @Field("delivery_hour_string") String deliveryHourString,
            @Field("frequency") int frequency,
            @Field("is_instant") int isInstant,
            @Field("order_id") int orderID
    );


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_ORDER_DETAILS)
    Call<WebResponse<Order>> getOrderDetails(
            @Field("order_id") int orderID
    );


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_INSTANT_ORDER)
    Call<WebResponse<OrdersWrapper>> getInstantOrders(
            @Field("user_id") int userID
    );


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_PENDING_ORDER)
    Call<WebResponse<OrdersWrapper>> getPendingOrders(
            @Field("user_id") int userID
    );


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_COMPLETED_ORDERS)
    Call<WebResponse<OrdersWrapper>> getCompletedOrders(
            @Field("user_id") int userID
    );


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_UPDATE_TOKEN)
    Call<WebResponse<Object>> updateToken(
            @Field("device_type") String deviceType,
            @Field("device_token") String firebaseToken,
            @Field("user_id") int userID
    );


    @DELETE(WebServiceConstants.WS_KEY_DELETE_ORDER)
    Call<WebResponse<Object>> deleteOrder(
            @Path("order_id") int orderId,
            @Path("user_id") int userId
    );

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_EDIT_SCHEDULE)
    Call<WebResponse<Object>> setEditSchedule(
            @Field("additional_notes") String additional_notes,
            @Field("user_id") String userID,
            @Field("delivery_hour") int delivery_hour,
            @Field("delivery_hour_string") String delivery_hour_string,
            @Field("frequency") int frequency,
            @Field("delivery_datetime") String deliveryDate,
            @Field("order_id") int order_id
    );

}

