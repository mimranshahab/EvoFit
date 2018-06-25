package edu.aku.family_hifazat.fragments;

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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.adapters.recyleradapters.ClinicalLabDetailAdapter;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.fragments.dialogs.CommentsDialogFragment;
import edu.aku.family_hifazat.fragments.dialogs.HistoryDialogFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.models.EndoscopyModel;
import edu.aku.family_hifazat.models.LabHistoryModel;
import edu.aku.family_hifazat.widget.TitleBar;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.LaboratoryDetailModel;
import edu.aku.family_hifazat.models.LstLaboratorySpecimenResults;
import edu.aku.family_hifazat.models.SearchModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/25/2018.
 */

public class ClinicalLaboratoryDetailFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.listClinicalLabResult)
    RecyclerView listClinicalLabResult;
    Unbinder unbinder;
    @BindView(R.id.txtSpecimenNumber)
    AnyTextView txtSpecimenNumber;
    @BindView(R.id.txtReqDatetime)
    AnyTextView txtReqDatetime;
    //    @BindView(R.id.txtPhysicianName)
//    AnyTextView txtPhysicianName;
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
    @BindView(R.id.txtReportName)
    AnyTextView txtReportName;
    private ArrayList<LstLaboratorySpecimenResults> arrLabDetail;
    private LaboratoryDetailModel laboratoryDetailModel;
    private ClinicalLabDetailAdapter adapterClinicalLabDetail;


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
        titleBar.setTitle("Laboratory Results");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrLabDetail = new ArrayList<LstLaboratorySpecimenResults>();
        adapterClinicalLabDetail = new ClinicalLabDetailAdapter(getBaseActivity(), arrLabDetail, this);
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
//        listClinicalLabResult.setLayoutAnimation(animation);
        listClinicalLabResult.setAdapter(adapterClinicalLabDetail);
    }

    @Override
    public void setListeners() {
        cardView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

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
//        txtPhysicianName.setText(laboratoryDetailModel.getReferringDoctorID());
        txtSpecimenNumber.setText(laboratoryDetailModel.getSpecimenID());
        txtLocation.setText(laboratoryDetailModel.getVisitLocationID());
        txtReportName.setText(laboratoryDetailModel.getOrdered());

    }


    private void historyDialog(ArrayList<LabHistoryModel> arrLabHistoryModel) {
        final HistoryDialogFragment historyDialogFrag = HistoryDialogFragment.newInstance();
        if (arrLabHistoryModel != null && !arrLabHistoryModel.isEmpty()) {
            historyDialogFrag.setTitle(arrLabHistoryModel.get(0).getMnemonic());
            historyDialogFrag.setData(arrLabHistoryModel);
            historyDialogFrag.show(getFragmentManager(), null);
        } else {
            UIHelper.showToast(getContext(), "No previous history found");
        }
    }

    private void commentsDialog(LstLaboratorySpecimenResults model) {
        final CommentsDialogFragment commentsDialogFragment = CommentsDialogFragment.newInstance(model);
        commentsDialogFragment.show(getFragmentManager(), null);
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        LstLaboratorySpecimenResults lstLaboratorySpecimenResults = adapterClinicalLabDetail.getItem(position);
        switch (view.getId()) {
            case R.id.txtComments:

                commentsDialog(lstLaboratorySpecimenResults);

                break;
            case R.id.btnHistory:
                SearchModel searchModel = new SearchModel();
                searchModel.setRecordID(laboratoryDetailModel.getSpecimenNumber());
                searchModel.setVisitID(adapterClinicalLabDetail.getItem(position).getPerformedTestID());
                searchModel.setMRNumber(getCurrentUser().getMRNumber());
                webServiceLabHistory(searchModel);
                break;
        }

    }


    @Override
    public void onItemClick(int position, Object object) {
//        if (object instanceof LstLaboratorySpecimenResults) {
//            LstLaboratorySpecimenResults model = (LstLaboratorySpecimenResults) object;
//            switch (view.getId()) {
//                case R.id.txtComments:
//                    commentsDialog(model);
//
//                    break;
//                case R.id.btnHistory:
//                    historyDialog(model);
//                    break;
//            }
//
//
//        }

    }

    private void webServiceLabHistory(SearchModel searchModel) {
        new WebServices(getContext(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_CLINICAL_LAB_RESULT_HISTORY, searchModel.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<LabHistoryModel>>() {
                                }.getType();
                                ArrayList<LabHistoryModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                historyDialog(arrayList);

                            }

                            @Override
                            public void onError() {

                            }
                        });
    }
}
