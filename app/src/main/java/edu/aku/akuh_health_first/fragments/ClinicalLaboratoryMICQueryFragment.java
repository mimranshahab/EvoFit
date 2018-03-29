package edu.aku.akuh_health_first.fragments;

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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.ClinicalLabMICDetailAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.models.BannerModel;
import edu.aku.akuh_health_first.models.LaboratoryDetailModel;
import edu.aku.akuh_health_first.models.LstLaboratoryMicspecimenOrderedProc;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/25/2018.
 */

public class ClinicalLaboratoryMICQueryFragment extends BaseFragment implements OnItemClickListener {

    Unbinder unbinder;
    @BindView(R.id.txtProcedure)
    AnyTextView txtProcedure;
    @BindView(R.id.txtProcedureDesc)
    AnyTextView txtProcedureDesc;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList arrLabDetail;
    private LaboratoryDetailModel laboratoryDetailModel;
    private ClinicalLabMICDetailAdapter adapter;

    public static ClinicalLaboratoryMICQueryFragment newInstance(LaboratoryDetailModel laboratoryDetailModel) {

        Bundle args = new Bundle();

        ClinicalLaboratoryMICQueryFragment fragment = new ClinicalLaboratoryMICQueryFragment();
        fragment.laboratoryDetailModel = laboratoryDetailModel;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_lab_mic_query_result;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Lab Detail");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrLabDetail = new ArrayList();
        adapter = new ClinicalLabMICDetailAdapter(getBaseActivity(), arrLabDetail, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();


        arrLabDetail.clear();

        for (LstLaboratoryMicspecimenOrderedProc lstLaboratoryMicspecimenOrderedProc : laboratoryDetailModel.getLstLaboratoryMicSpecimenOrderedProc()) {
            arrLabDetail.add(new BannerModel(lstLaboratoryMicspecimenOrderedProc.getPROCEDUREDESCRIPTION(), laboratoryDetailModel.getSourceDescription()));
            arrLabDetail.addAll(lstLaboratoryMicspecimenOrderedProc.getLstLaboratoryMicSpecimenResults());
        }

        adapter.notifyDataSetChanged();
        bindData();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapter);
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


    private void bindData() {
//        txtCollecDatetime.setText(laboratoryDetailModel.getCollectionDttm());
//        txtReportDatetime.setText(laboratoryDetailModel.getSignoutDttm());
//        txtReqDatetime.setText(laboratoryDetailModel.getSortDttm());
//        txtPhysicianName.setText(laboratoryDetailModel.getReferringDoctorID());
//        txtSpecimenNumber.setText(laboratoryDetailModel.getSpecimenID());
//        txtLocation.setText(laboratoryDetailModel.getVisitLocationID());

    }

    @Override
    public void onItemClick(int position, Object object) {

    }
}