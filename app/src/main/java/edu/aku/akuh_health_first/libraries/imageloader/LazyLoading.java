package edu.aku.akuh_health_first.libraries.imageloader;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import edu.aku.akuh_health_first.R;

/**
 * Created by khanhamza on 08-Mar-17.
 */

public class LazyLoading {

    public static DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
            .showImageForEmptyUri(R.color.base_grey)
            .showImageOnFail(R.drawable.placeholder)
            .showImageOnLoading(R.drawable.placeholder).build();
}
