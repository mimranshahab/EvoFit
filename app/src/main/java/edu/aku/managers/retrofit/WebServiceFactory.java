package edu.aku.managers.retrofit;

import edu.aku.constatnts.WebServiceConstants;

import java.io.IOException;

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

    private static WebServiceProxy webServiceProxy = null;


    /***
     *      SINGLETON Design Pattern
     */
    public static WebServiceProxy getInstance(final String _token) {

     webServiceProxy = null;

        if (webServiceProxy == null) {

//            Gson gson = new GsonBuilder()
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//                    .create();


            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//            httpClient.connectTimeout(120, TimeUnit.SECONDS);
//            httpClient.readTimeout(121, TimeUnit.SECONDS);


            // add your other interceptors …
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder().addHeader("token", _token + "").addHeader("_token", _token + "");
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });


            // add logging as last interceptor
            httpClient.addInterceptor(logging);  // <-- this is the important line!
            Retrofit retrofit
                    = new Retrofit.Builder()
                    .baseUrl(WebServiceConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            webServiceProxy = retrofit.create(WebServiceProxy.class);
        }

        return webServiceProxy;
    }

}