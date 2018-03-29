package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.ClinicalLabDetailAdapterv1;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.EndoscopyModel;
import edu.aku.akuh_health_first.models.LaboratoryDetailModel;
import edu.aku.akuh_health_first.models.LstLaboratorySpecimenResults;
import edu.aku.akuh_health_first.models.SearchModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/25/2018.
 */

public class ClinicalLaboratoryDetailFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.listClinicalLabResult)
    RecyclerView listClinicalLabResult;
    Unbinder unbinder;
    @BindView(R.id.txtSpecimenNumber)
    AnyTextView txtSpecimenNumber;
    @BindView(R.id.txtReqDatetime)
    AnyTextView txtReqDatetime;
    @BindView(R.id.txtPhysicianName)
    AnyTextView txtPhysicianName;
    @BindView(R.id.txtCollecDatetime)
    AnyTextView txtCollecDatetime;
    @BindView(R.id.txtLocation)
    AnyTextView txtLocation;
    @BindView(R.id.txtReportDatetime)
    AnyTextView txtReportDatetime;
    @BindView(R.id.btnDownload)
    AnyTextView btnDownload;
    @BindView(R.id.cardView2)
    CardView cardView2;
    private ArrayList<LstLaboratorySpecimenResults> arrLabDetail;
    private LaboratoryDetailModel laboratoryDetailModel;
    private ClinicalLabDetailAdapterv1 adapterClinicalLabDetail;

    public static ClinicalLaboratoryDetailFragment newInstance(LaboratoryDetailModel laboratoryDetailModel) {

        Bundle args = new Bundle();

        ClinicalLaboratoryDetailFragment fragment = new ClinicalLaboratoryDetailFragment();
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
        return R.layout.fragment_clinical_lab_detail;
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
        arrLabDetail = new ArrayList<LstLaboratorySpecimenResults>();
        adapterClinicalLabDetail = new ClinicalLabDetailAdapterv1(getBaseActivity(), arrLabDetail, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();


        arrLabDetail.clear();
        arrLabDetail.addAll(laboratoryDetailModel.getLstLaboratorySpecimenResults());
        adapterClinicalLabDetail.notifyDataSetChanged();
        bindData();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());

        listClinicalLabResult.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) listClinicalLabResult.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        listClinicalLabResult.setLayoutAnimation(animation);
        listClinicalLabResult.setAdapter(adapterClinicalLabDetail);
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
        txtCollecDatetime.setText(laboratoryDetailModel.getCollectionDttm());
        txtReportDatetime.setText(laboratoryDetailModel.getSignoutDttm());
        txtReqDatetime.setText(laboratoryDetailModel.getSortDttm());
        txtPhysicianName.setText(laboratoryDetailModel.getReferringDoctorID());
        txtSpecimenNumber.setText(laboratoryDetailModel.getSpecimenID());
        txtLocation.setText(laboratoryDetailModel.getVisitLocationID());

    }

    @Override
    public void onItemClick(int position, Object object) {

    }

    @OnClick(R.id.btnDownload)
    public void onViewClicked() {

        SearchModel searchModel = new SearchModel();

        searchModel.setRecordID(laboratoryDetailModel.getSpecimenNumber());
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForWebResponseWithString(WebServiceConstants.METHOD_CLINICAL_LAB_REPORT,
                        searchModel.toString(), new WebServices.IRequestWebResponseWithStringDataCallBack() {
            @Override
            public void requestDataResponse(WebResponse<String> webResponse) {
                saveAndOpenFile(webResponse);
            }

            @Override
            public void onError() {

            }
        }
                    );


    }
}
