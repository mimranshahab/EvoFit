package edu.aku.akuh_health_first.managers.retrofit;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.fragments.LoginFragment;
import edu.aku.akuh_health_first.models.Content;
import edu.aku.akuh_health_first.models.UserModel;
import edu.aku.akuh_health_first.models.extramodels.AddressModel;
import edu.aku.akuh_health_first.models.wrappers.AddressWrapper;
import edu.aku.akuh_health_first.models.wrappers.AddressWrapper2;
import edu.aku.akuh_health_first.models.wrappers.NotificationWrapper;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public interface WebServiceProxy {


    @Multipart
    @POST("./")
    Call<WebResponse<JsonObject>> webServiceRequestAPI(
            @Part(WebServiceConstants.PARAMS_REQUEST_METHOD) RequestBody requestMethod,
            @Part(WebServiceConstants.PARAMS_REQUEST_DATA) RequestBody requestData
    );


    @Multipart
    @POST("./")
    Call<WebResponse<String>> uploadFileRequestApi(
            @Part(WebServiceConstants.PARAMS_REQUEST_METHOD) RequestBody requestMethod,
            @Part MultipartBody.Part requestData
    );


    @Headers(WebServiceConstants.WS_KEY_GET_REQUESTOR)
    @GET(WebServiceConstants.WS_KEY_GET_TOKEN)
    Call<String> getToken();


    @GET(WebServiceConstants.WS_KEY_IMAGE_URL)
    Call<ResponseBody> downloadFileWithFixedUrl();

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

    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_GET_SELECTED_ADDRESS)
    Call<WebResponse<AddressWrapper2>> getSelectedAddress(
            @Field("user_id") int userID);


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_SELECT_ADDRESS)
    Call<WebResponse<Object>> selectAddress(
            @Field("address_id") int addressID,
            @Field("user_id") int userID);


    @FormUrlEncoded
    @POST(WebServiceConstants.WS_KEY_UPDATE_TOKEN)
    Call<WebResponse<Object>> updateToken(
            @Field("device_type") String deviceType,
            @Field("device_token") String firebaseToken,
            @Field("user_id") int userID
    );


//    @DELETE(WebServiceConstants.WS_KEY_DELETE_ORDER)
//    Call<WebResponse<Object>> deleteOrder(
//            @Path("order_id") int orderId,
//            @Path("user_id") int userId
//    );


}

