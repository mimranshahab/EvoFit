package edu.aku.akuh_health_first.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.libraries.slideshow.SlideShowAdapter;
import edu.aku.akuh_health_first.libraries.slideshow.SlideShowView;
import edu.aku.akuh_health_first.libraries.slideshow.adapter.GenericBitmapAdapter;
import edu.aku.akuh_health_first.libraries.slideshow.adapter.RemoteBitmapAdapter;
import edu.aku.akuh_health_first.libraries.slideshow.adapter.ResourceBitmapAdapter;
import edu.aku.akuh_health_first.libraries.slideshow.picasso.GenericPicassoBitmapAdapter;
import edu.aku.akuh_health_first.libraries.slideshow.picasso.PicassoRemoteBitmapAdapter;

/**
 * Created by User on 1/10/2018.
 */

public class SlideShowFragment extends BaseFragment {
    SlideShowView slideShowView;
    Unbinder unbinder;

    private SlideShowAdapter adapter;

    public static SlideShowFragment newInstance() {

        Bundle args = new Bundle();

        SlideShowFragment fragment = new SlideShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_slideshow;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {

        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setRightButton(R.drawable.menu_divider, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getContext(), getMainActivity().getTitleBar().btnRight1);
                popup.getMenuInflater().inflate(R.menu.slideshow, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        return id == R.id.action_settings;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        slideShowView = view.findViewById(R.id.slideshow);

    }

    private SlideShowAdapter createRemoteAdapter() {
        String[] slideUrls = new String[]{
                "http://lorempixel.com/1280/720/sports",
                "http://lorempixel.com/1280/720/nature",
                "http://lorempixel.com/1280/720/people",
                "http://lorempixel.com/1280/720/city",
        };
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;
        adapter = new RemoteBitmapAdapter(getContext(), Arrays.asList(slideUrls), opts);
        return adapter;
    }

    private SlideShowAdapter createPicassoAdapter() {
        Picasso.with(getContext()).setLoggingEnabled(true);

        String[] slideUrls = new String[]{
                "http://www.marvinlabs.com/wp-content/uploads/2013/10/logo.png",
                "http://lorempixel.com/1280/720/sports",
                "http://lorempixel.com/1280/720/nature",
                "http://lorempixel.com/1280/720/people",
                "http://lorempixel.com/1280/720/city",
        };
        adapter = new PicassoRemoteBitmapAdapter(getContext(), Arrays.asList(slideUrls));
        return adapter;
    }

    private SlideShowAdapter createResourceAdapter() {
        Integer[] slideResources = new Integer[]{R.raw.slide_01, R.raw.slide_02, R.raw.slide_03, R.raw.slide_04};
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;
        adapter = new ResourceBitmapAdapter(getContext(), Arrays.asList(slideResources), opts);
        return adapter;
    }

    @Override
    public void onResume() {
        super.onResume();
        startSlideShow();
    }

    @Override
    public void onStop() {
        if (adapter instanceof GenericBitmapAdapter) {
            ((GenericBitmapAdapter) adapter).shutdown();
        } else if (adapter instanceof GenericPicassoBitmapAdapter) {
            ((GenericPicassoBitmapAdapter) adapter).shutdown();
        }
        super.onStop();
    }

    private void startSlideShow() {
        // Create an adapter
        // slideShowView.setAdapter(createResourceAdapter());
        slideShowView.setAdapter(createRemoteAdapter());
        // slideShowView.setAdapter(createPicassoAdapter());

        // Optional customisation follows
        // slideShowView.setTransitionFactory(new RandomTransitionFactory());
        // slideShowView.setPlaylist(new RandomPlayList());

        // Some listeners if needed
        slideShowView.setOnSlideShowEventListener(slideShowListener);
        slideShowView.setOnSlideClickListener(slideClickListener);

        // Then attach the adapter
        slideShowView.play();
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        getActivity().getMenuInflater().inflate(R.menu.slideshow, menu);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private SlideShowView.OnSlideClickListener slideClickListener = new SlideShowView.OnSlideClickListener() {
        @Override
        public void onItemClick(SlideShowView parent, int position) {
            Toast.makeText(getContext(), "Slide clicked: " + position, Toast.LENGTH_SHORT).show();
        }
    };

    private SlideShowView.OnSlideShowEventListener slideShowListener = new SlideShowView.OnSlideShowEventListener() {
        @Override
        public void beforeSlideShown(SlideShowView parent, int position) {
            Log.d("SlideShowDemo", "OnSlideShowEventListener.beforeSlideShown: " + position);
        }

        @Override
        public void onSlideShown(SlideShowView parent, int position) {
            Log.d("SlideShowDemo", "OnSlideShowEventListener.onSlideShown: " + position);
        }

        @Override
        public void beforeSlideHidden(SlideShowView parent, int position) {
            Log.d("SlideShowDemo", "OnSlideShowEventListener.beforeSlideHidden: " + position);
        }

        @Override
        public void onSlideHidden(SlideShowView parent, int position) {
            Log.d("SlideShowDemo", "OnSlideShowEventListener.onSlideHidden: " + position);
        }
    };

}
