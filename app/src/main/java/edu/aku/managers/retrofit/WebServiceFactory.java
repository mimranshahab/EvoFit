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

    private static Retrofit retrofit;


    /***
     *      SINGLETON Design Pattern
     */
    public static WebServiceProxy getInstance(final String _token) {

//     webServiceProxy = null;

        if (retrofit == null) {

//            Gson gson = new GsonBuilder()
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//                    .create();


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            // set your desired log level
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//            httpClient.connectTimeout(120, TimeUnit.SECONDS);
//            httpClient.readTimeout(121, TimeUnit.SECONDS);


            // add your other interceptors …
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder().addHeader("_token", _token + "");
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });


            // add logging as last interceptor
//            httpClient.addNetworkInterceptor(interceptor).addInterceptor(interceptor);  // <-- this is the important line!
            httpClient.addInterceptor(interceptor);  // <-- this is the important line!
            retrofit = new Retrofit.Builder()
                    .baseUrl(WebServiceConstants.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

//            WebServiceFactory.retrofit = retrofit.create(WebServiceProxy.class);
        }

        return retrofit.create(WebServiceProxy.class);
    }

}