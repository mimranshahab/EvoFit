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
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.adapters.recyleradapters.BPHistoryAdapter;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.models.HealthSummaryHistoryModel;
import edu.aku.family_hifazat.models.PatientHealthSummaryModel;
import edu.aku.family_hifazat.widget.AnyEditTextView;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by aqsa.sarwar on 4/26/2018.
 */

public class BPHistoryFragment extends BaseFragment implements OnItemClickListener {

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
    @BindView(R.id.contParentLayout)
    LinearLayout contParentLayout;

    private PatientHealthSummaryModel modelDiastolic;
    private PatientHealthSummaryModel modelSystolic;

    ArrayList<HealthSummaryHistoryModel> arrData;

    BPHistoryAdapter adapter;


    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    public static BPHistoryFragment newInstance(PatientHealthSummaryModel modelSystolic, PatientHealthSummaryModel modelDiastolic) {

        Bundle args = new Bundle();

        BPHistoryFragment fragment = new BPHistoryFragment();
        fragment.modelSystolic = modelSystolic;
        fragment.modelDiastolic = modelDiastolic;
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
        titleBar.setTitle("Blood Pressure");
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
        adapter = new BPHistoryAdapter(getBaseActivity(), arrData, this);

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
        setData();
    }


    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recylerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recylerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerView.setLayoutAnimation(animation);
        recylerView.setAdapter(adapter);
        contParentLayout.setBackgroundColor(getResources().getColor(R.color.c_white));

    }

    private void setData() {
        if (modelSystolic != null && modelDiastolic != null) {
            // Height
            if ((modelSystolic.getHealthindicatorlist() == null || modelSystolic.getHealthindicatorlist().isEmpty()) || (modelDiastolic.getHealthindicatorlist() == null || modelDiastolic.getHealthindicatorlist().isEmpty())) {
                emptyView.setVisibility(View.VISIBLE);
                emptyView.setText("No Record Found");
            } else {
                arrData.clear();
                for (int i = 0; i < modelSystolic.getHealthindicatorlist().size(); i++) {
                    arrData.add(new HealthSummaryHistoryModel(modelSystolic.getHealthindicatorlist().get(i), modelDiastolic.getHealthindicatorlist().get(i)));
                }
                adapter.notifyDataSetChanged();
            }
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
