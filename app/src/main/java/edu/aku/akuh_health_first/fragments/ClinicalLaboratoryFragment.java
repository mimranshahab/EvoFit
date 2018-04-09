package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.ClinicalLabAdapterV1;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.LaboratoryDetailModel;
import edu.aku.akuh_health_first.models.LaboratoryModel;
import edu.aku.akuh_health_first.models.SearchModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.widget.AnyEditTextView;
import edu.aku.akuh_health_first.widget.AnyTextView;


/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class ClinicalLaboratoryFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.recylerView)
    RecyclerView recyclerNeurophysiology;

    Unbinder unbinder;
    @BindView(R.id.empty_view)
    AnyTextView emptyView;
    @BindView(R.id.edtSearchBar)
    AnyEditTextView edtSearchBar;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    private ArrayList<LaboratoryModel> arrClinicalLabLists;
    private ClinicalLabAdapterV1 clinicalLabAdapterV1;
    boolean isFromTimeline;
    int patientVisitAdmissionID;
    private String testname;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrClinicalLabLists = new ArrayList<LaboratoryModel>();
        clinicalLabAdapterV1 = new ClinicalLabAdapterV1(getBaseActivity(), arrClinicalLabLists, this);
    }

    public static ClinicalLaboratoryFragment newInstance(boolean isFromTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();

        ClinicalLaboratoryFragment fragment = new ClinicalLaboratoryFragment();
        fragment.isFromTimeline = isFromTimeline;
        fragment.patientVisitAdmissionID = patientVisitAdmissionID;
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
        titleBar.setTitle("Laboratory");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        serviceCall();
        edtSearchBar.setVisibility(View.VISIBLE);
        imgSearch.setVisibility(View.VISIBLE);
        edtSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                clinicalLabAdapterV1.getFilter().filter(charSequence);
                if(edtSearchBar.getStringTrimmed().equalsIgnoreCase("")){

                    imgSearch.setImageResource(R.drawable.search2);

                } else {
                    imgSearch.setImageResource(R.drawable.ic_select_cross);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        if (recyclerNeurophysiology == null) {
            popBackStack();
        }
        recyclerNeurophysiology.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerNeurophysiology.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerNeurophysiology.setLayoutAnimation(animation);
        recyclerNeurophysiology.setAdapter(clinicalLabAdapterV1);

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
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
        if (object instanceof LaboratoryModel) {
            LaboratoryModel model = (LaboratoryModel) object;
            if (model.getStatusID().equalsIgnoreCase("Completed") || model.getStatusID().equalsIgnoreCase("Partially Completed")) {
                testname = (model.getOrdered());
                labDetailService(model.getSpecimenNumber());
            }
        }
    }


    private void serviceCall() {
        SearchModel model = new SearchModel();
        model.setMRNumber(getCurrentUser().getMRNumber());
        if (isFromTimeline) {
            model.setVisitID(String.valueOf(patientVisitAdmissionID));
        } else {
            model.setVisitID(null);
        }

        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_CLINICAL_LAB,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<LaboratoryModel>>() {
                                }.getType();
                                ArrayList<LaboratoryModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                arrClinicalLabLists.clear();
                                arrClinicalLabLists.addAll(arrayList);
                                clinicalLabAdapterV1.notifyDataSetChanged();

                                if (arrClinicalLabLists.size() > 0) {
                                    showView();

                                } else {
                                    showEmptyView();
                                }

                            }

                            @Override
                            public void onError() {
                                showEmptyView();
                            }

                        });

    }

    private void labDetailService(String specimenNumber) {
        SearchModel searchModel = new SearchModel();
        //        searchModel.setRecordID("56070141"); // MIC
//        searchModel.setRecordID("57380695"); // Report
        searchModel.setRecordID(specimenNumber); // Original Data
        searchModel.setVisitID(null);
        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_CLINICAL_LAB_DETAILS,
                        searchModel.toString(),

//                        WebServiceConstants.temp_Specimen_Num,
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                LaboratoryDetailModel laboratoryDetailModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, LaboratoryDetailModel.class);
                                laboratoryDetailModel.setOrdered(testname);
                                if (laboratoryDetailModel.getIsExternalReport()) {
                                    WebResponse<String> webResponse1 = new WebResponse<String>();
                                    if (laboratoryDetailModel.getExternalFile() == null || laboratoryDetailModel.getExternalFile().isEmpty()) {
                                        UIHelper.showToast(getContext(), "No file data");
                                        return;
                                    }
                                    webResponse1.result = laboratoryDetailModel.getExternalFile();
                                    saveAndOpenFile(webResponse1);
                                } else if (laboratoryDetailModel.getSpecimenType().equals("LAB")) {
                                    getBaseActivity().addDockableFragment(ClinicalLaboratoryDetailFragment.newInstance(laboratoryDetailModel));
                                } else if (laboratoryDetailModel.getSpecimenType().equals("MIC")) {
                                    getBaseActivity().addDockableFragment(ClinicalLaboratoryMICDetailFragment.newInstance(laboratoryDetailModel));
                                } else {
                                    showEmptyView();
                                }

                            }

                            @Override
                            public void onError() {
                                showEmptyView();
                                UIHelper.showShortToastInCenter(getContext(), "something went wrong");
                            }
                        });

    }

    private void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    private void showView() {
        bindView();
        emptyView.setVisibility(View.GONE);
    }

    @OnClick(R.id.imgSearch)
    public void onViewClicked() {
        edtSearchBar.setText("");
    }
}
