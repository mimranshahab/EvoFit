package edu.aku.akuh_health_first.managers.retrofit;

import edu.aku.akuh_health_first.constatnts.WebServiceConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.enums.WebServiceTypes;
import edu.aku.akuh_health_first.fragments.LoginFragment;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by khanhamza on 09-Mar-17.
 */

public class WebServiceFactory {

    private static Retrofit retrofit;
    private static String token = "";
    private static String bearerToken = "";

    /***
     *      SINGLETON Design Pattern
     */
    public static WebServiceProxy getInstance(final String _token, final WebServiceTypes webServiceTypes, BaseURLTypes baseURLTypes) {

        retrofit = null;
        if (retrofit == null) {

//            Gson gson = new GsonBuilder()
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//                    .create();


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            // set your desired log level
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(120, TimeUnit.SECONDS);
            httpClient.readTimeout(121, TimeUnit.SECONDS);


//             add your other interceptors …
            httpClient.addInterceptor(new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();

                    switch (webServiceTypes) {
                        case ONLY_TOKEN:
                            requestBuilder.addHeader("_token", _token + "");
                            break;
                        case TOKEN_AND_BEARER:
                            requestBuilder.addHeader("_token", _token + "");
                            requestBuilder.addHeader("Authorization", "Bearer " + LoginFragment.getBearerToken());
                            requestBuilder.addHeader("Requestor", "aku.edu");
                            break;
                        case THIRD:
                            break;
                    }

                    // Request customization: add request headers

                    Request request = requestBuilder.build();
                    return chain.proceed(request);

                }
            });


            // add logging as last interceptor
//            httpClient.addNetworkInterceptor(interceptor).addInterceptor(interceptor);  // <-- this is the important line!
            httpClient.addInterceptor(interceptor);  // <-- this is the important line!
            retrofit = new Retrofit.Builder()
                    .baseUrl(WebServiceConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSimpleGson()))
                    .client(httpClient.build())
                    .build();

//            WebServiceFactory.retrofit = retrofit.create(WebServiceProxy.class);
        }

        return retrofit.create(WebServiceProxy.class);
    }


    public static WebServiceProxy getInstance() throws IOException {

        retrofit = null;
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            // set your desired log level
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(1000, TimeUnit.SECONDS);
            httpClient.readTimeout(1000, TimeUnit.SECONDS);
            httpClient.addInterceptor(new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
                    requestBuilder.addHeader("Authorization", "Bearer " + LoginFragment.getBearerToken());
                    requestBuilder .addHeader("Requestor", "aku.edu");
                    requestBuilder.addHeader("Accept", "image/jpeg" );


                    // Request customization: add request headers

                    Request request = requestBuilder.build();
                    return chain.proceed(request);

                }
            });


            httpClient.addInterceptor(interceptor);  // <-- this is the important line!
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://pacsviewer.aku.edu/")
                    .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSimpleGson()))
                    .client(httpClient.build())
                    .build();



        }

        return retrofit.create(WebServiceProxy.class);


    }


}