package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.adapters.recyleradapters.GlucoseHistoryAdapter;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.models.PatientHealthSummaryModel;
import edu.aku.family_hifazat.models.Subhealthindicator;
import edu.aku.family_hifazat.widget.AnyEditTextView;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by aqsa.sarwar on 4/26/2018.
 */

public class GlucoseHistoryFragment extends BaseFragment implements OnItemClickListener {

    Unbinder unbinder;
    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    @BindView(R.id.empty_view)
    AnyTextView emptyView;
    @BindView(R.id.edtSearchBar)
    AnyEditTextView edtSearchBar;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.contParent)
    RelativeLayout contParent;
    @BindView(R.id.txtFasting)
    AnyTextView txtFasting;
    @BindView(R.id.txtRandom)
    AnyTextView txtRandom;
    @BindView(R.id.glucoTabs)
    LinearLayout glucoTabs;

    private PatientHealthSummaryModel modelRandomGlucose;
    private PatientHealthSummaryModel modelFastingGlucose;

    ArrayList<Subhealthindicator> arrData;

    GlucoseHistoryAdapter adapter;

    String typeFasting = "Fasting";
    String typeRandom = "Random";
    private boolean isGlucoseFasting = true;


    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    public static GlucoseHistoryFragment newInstance(PatientHealthSummaryModel modelFastingGlucose, PatientHealthSummaryModel modelRandomGlucose) {

        Bundle args = new Bundle();

        GlucoseHistoryFragment fragment = new GlucoseHistoryFragment();
        fragment.modelRandomGlucose = modelRandomGlucose;
        fragment.modelFastingGlucose = modelFastingGlucose;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_general_recyler_view;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Blood Glucose");
        titleBar.showHome(getBaseActivity());
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrData = new ArrayList<>();
        adapter = new GlucoseHistoryAdapter(getBaseActivity(), arrData, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        setData(typeFasting);
    }


    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recylerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recylerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerView.setLayoutAnimation(animation);
        recylerView.setAdapter(adapter);

        glucoTabs.setVisibility(View.VISIBLE);
    }

    private void setData(String glucoseType) {
        if (glucoseType.equals(typeFasting)) {

            if (modelFastingGlucose != null) {
                // Fasting Glucose
                if ((modelFastingGlucose.getHealthindicatorlist() == null || modelFastingGlucose.getHealthindicatorlist().isEmpty())) {
                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.setText("No Fasting Glucose Record Found");
                } else {
                    arrData.clear();
                    adapter.setStatus(glucoseType);
                    arrData.addAll(modelFastingGlucose.getHealthindicatorlist());
                    adapter.notifyDataSetChanged();
                }
            }
        } else {
            if (modelRandomGlucose != null) {
                // Fasting Glucose
                if ((modelRandomGlucose.getHealthindicatorlist() == null || modelRandomGlucose.getHealthindicatorlist().isEmpty())) {
                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.setText("No Random Glucose Record Found");
                } else {
                    arrData.clear();
                    adapter.setStatus(glucoseType);
                    arrData.addAll(modelRandomGlucose.getHealthindicatorlist());
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }


    @OnClick({R.id.txtFasting, R.id.txtRandom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtFasting:

                if (!isGlucoseFasting) {
                    isGlucoseFasting = true;
                    txtFasting.setBackgroundResource(R.drawable.gluco_button_left_selected);
                    txtFasting.setTextColor(getResources().getColor(R.color.c_white));
                    txtRandom.setBackgroundResource(R.drawable.gluco_button_right_unselected);
                    txtRandom.setTextColor(getResources().getColor(R.color.text_color_grey));

                    setData(typeFasting);
                }


                break;
            case R.id.txtRandom:

                if (isGlucoseFasting) {
                    isGlucoseFasting = false;
                    txtRandom.setBackgroundResource(R.drawable.gluco_button_right_selected);
                    txtRandom.setTextColor(getResources().getColor(R.color.c_white));
                    txtFasting.setBackgroundResource(R.drawable.gluco_button_left_unselected);
                    txtFasting.setTextColor(getResources().getColor(R.color.text_color_grey));
                    setData(typeRandom);
                }

                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(int position, Object object) {

    }
}
