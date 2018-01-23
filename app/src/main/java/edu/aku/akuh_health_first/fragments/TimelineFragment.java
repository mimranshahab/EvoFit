package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.TimelineAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.FileManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.Neurophysiology;
import edu.aku.akuh_health_first.models.TimelineModel;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

import static edu.aku.akuh_health_first.constatnts.AppConstants.DOC_PATH;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class TimelineFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.recylerView)
    RecyclerView recyclerTimeline;
    Unbinder unbinder;
    private ArrayList<TimelineModel> arrTimeLine;
    private TimelineAdapter timelineAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrTimeLine = new ArrayList<TimelineModel>();
        timelineAdapter = new TimelineAdapter(getBaseActivity(), arrTimeLine, this);

        TimelineModel timelineModel = new TimelineModel();
//        timelineModel.setText(getString(R.string.generic));

        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
        arrTimeLine.add(timelineModel);
    }

    public static TimelineFragment newInstance() {

        Bundle args = new Bundle();

        TimelineFragment fragment = new TimelineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_timeline;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Timeline");
        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerTimeline.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerTimeline.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerTimeline.setLayoutAnimation(animation);
        recyclerTimeline.setAdapter(timelineAdapter);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(int position, Object object) {
        if (object instanceof TimelineModel) {
            final TimelineModel neurophysiology = (TimelineModel) object;

        }

    }

}
