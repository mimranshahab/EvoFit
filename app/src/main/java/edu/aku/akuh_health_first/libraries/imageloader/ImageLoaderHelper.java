package edu.aku.akuh_health_first.libraries.imageloader;

import android.app.Activity;
import android.content.Context;
import android.os.UserManager;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.HashMap;
import java.util.Map;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.BaseActivity;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;

import static edu.aku.akuh_health_first.constatnts.WebServiceConstants.METHOD_USER_GET_USER_IMAGE;

/**
 * Created by khanhamza on 08-Mar-17.
 */

public class ImageLoaderHelper {


    public static void loadImageWithConstantHeaders(Context context, ImageView imageView, String path) {
        ImageLoader.getInstance().displayImage(ImageLoaderHelper.getUserImageURL(path),
                imageView,
                ImageLoaderHelper.getOptionsWithAnimation(WebServiceConstants.getHeaders()));
    }

    public static String getImageURL(String path, String requestMethod) {
        return "http://ahfapidev.aku.edu/getimage?path=" + path + "&requestmethod=" + requestMethod;
    }

    public static String getUserImageURL(String path) {
        return "http://ahfapidev.aku.edu/getimage?path=" + path + "&requestmethod=" + METHOD_USER_GET_USER_IMAGE;
    }

    public static DisplayImageOptions getOptionsSimple() {

        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.color.base_grey)
                .showImageOnFail(R.drawable.placeholder)
                .showImageOnLoading(R.drawable.placeholder)
                .build();
    }


    public static DisplayImageOptions getOptionsSimple(Map<String, String> headers) {

        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.color.base_grey)
                .showImageOnFail(R.drawable.placeholder)
                .showImageOnLoading(R.drawable.placeholder)
                .extraForDownloader(headers)
                .build();
    }

    public static DisplayImageOptions getOptionsWithAnimation() {

        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.color.base_grey)
                .showImageOnFail(R.drawable.placeholder)
                .showImageOnLoading(R.drawable.placeholder)
                .imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(300)).build();
    }


    public static DisplayImageOptions getOptionsWithAnimation(Map<String, String> headers) {

        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.color.base_grey)
                .showImageOnFail(R.drawable.placeholder)
                .showImageOnLoading(R.drawable.placeholder)
                .extraForDownloader(headers)
                .imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(300)).build();
    }
}
