package edu.aku.akuh_health_first.libraries.slideshow.picasso;

import android.content.Context;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.net.URL;
import java.util.Collection;

import retrofit2.http.Url;

/**
 * An implementation of GenericPicassoBitmapAdapter to load remote images from the Internet
 * <p/>
 * Created by Vincent Mimoun-Prat @ MarvinLabs on 29/05/2014.
 */
public class PicassoRemoteBitmapAdapter extends GenericPicassoBitmapAdapter<String> {

    public PicassoRemoteBitmapAdapter(Context context, Collection<String> items) {
        super(context, items);
    }

    @Override
    protected RequestCreator createRequestCreator(Picasso picasso, String url) {
        return picasso.load(url).noFade().skipMemoryCache();
    }

}
