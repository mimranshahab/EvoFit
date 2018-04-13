package edu.aku.family_hifazat.libraries.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.Map;

import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.managers.SharedPreferenceManager;

import static edu.aku.family_hifazat.constatnts.WebServiceConstants.GETIMAGE_BASE_URL;
import static edu.aku.family_hifazat.constatnts.WebServiceConstants.METHOD_USER_GET_USER_IMAGE;

/**
 * Created by khanhamza on 08-Mar-17.
 */

public class ImageLoaderHelper {


    public static void loadImageWithConstantHeadersWithoutAnimation(Context context, ImageView imageView, String path) {
        ImageLoader.getInstance().displayImage(ImageLoaderHelper.getUserImageURL(path),
                imageView,
                ImageLoaderHelper.getOptionsSimple(WebServiceConstants
                        .getHeaders(SharedPreferenceManager.getInstance(context).getString(AppConstants.KEY_TOKEN))));
    }

    public static void loadImageWithConstantHeaders(Context context, ImageView imageView, String path) {
        ImageLoader.getInstance().displayImage(ImageLoaderHelper.getUserImageURL(path),
                imageView,
                ImageLoaderHelper.getOptionsWithAnimation(WebServiceConstants
                        .getHeaders(SharedPreferenceManager.getInstance(context).getString(AppConstants.KEY_TOKEN))));
    }

    public static String getImageURL(String path, String requestMethod) {
        return GETIMAGE_BASE_URL + path + "&requestmethod=" + requestMethod;
    }

    public static String getUserImageURL(String path) {
        return GETIMAGE_BASE_URL + path + "&requestmethod=" + METHOD_USER_GET_USER_IMAGE;
    }

    public static DisplayImageOptions getOptionsSimple() {

        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.color.base_dark_gray)
                .showImageOnFail(R.drawable.profile_placeholder)
                .showImageOnLoading(R.drawable.profile_placeholder)
                .build();
    }


    public static DisplayImageOptions getOptionsSimple(Map<String, String> headers) {

        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.color.base_dark_gray)
                .showImageOnFail(R.drawable.profile_placeholder)
                .showImageOnLoading(R.drawable.profile_placeholder)
                .extraForDownloader(headers)
                .build();
    }

    public static DisplayImageOptions getOptionsWithAnimation() {

        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.color.base_dark_gray)
                .showImageOnFail(R.drawable.profile_placeholder)
                .showImageOnLoading(R.drawable.profile_placeholder)
                .imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(500)).build();
    }


    public static DisplayImageOptions getOptionsWithAnimation(Map<String, String> headers) {

        return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.color.base_dark_gray)
                .showImageOnFail(R.drawable.profile_placeholder)
                .showImageOnLoading(R.drawable.profile_placeholder)
                .extraForDownloader(headers)
                .imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(500)).build();
    }
}
