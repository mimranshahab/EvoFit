package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.SearchModel;
import edu.aku.akuh_health_first.models.wrappers.MenuModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.views.AnyTextView;

public class HealthHistoryFragment extends BaseFragment {


    private static final String IS_FROM_VISIT_TIMELINE = "isVisitTimeline";
    private static final String VISIT_ID = "patientVisitAdmissionID";
    @BindView(R.id.btnClinicalLab)
    AnyTextView btnClinicalLab;
    @BindView(R.id.btnRadiology)
    AnyTextView btnRadiology;
    @BindView(R.id.btnMedProf)
    AnyTextView btnMedProf;
    @BindView(R.id.btnImmunizationProfile)
    AnyTextView btnImmunizationProfile;
    @BindView(R.id.btnCardio)
    AnyTextView btnCardio;
    @BindView(R.id.btnNeurophysiology)
    AnyTextView btnNeurophysiology;
    @BindView(R.id.btnEndoscopy)
    AnyTextView btnEndoscopy;
    @BindView(R.id.btnDischargeSummary)
    AnyTextView btnDischargeSummary;
    Unbinder unbinder;
    @BindView(R.id.contLab)
    LinearLayout contLab;
    @BindView(R.id.contRadiology)
    LinearLayout contRadiology;
    @BindView(R.id.contMedicalProfile)
    LinearLayout contMedicalProfile;
    @BindView(R.id.contImmunization)
    LinearLayout contImmunization;
    @BindView(R.id.contCardio)
    LinearLayout contCardio;
    @BindView(R.id.contNeuroPhysiology)
    LinearLayout contNeuroPhysiology;
    @BindView(R.id.contEndo)
    LinearLayout contEndo;
    @BindView(R.id.contSummary)
    LinearLayout contSummary;
    private boolean isVisitTimeline;
    private List<MenuModel> arrData;
    private ArrayList<String> tempArr;
    private int patientVisitAdmissionID;

    public static HealthHistoryFragment newInstance(boolean isVisitTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();

        HealthHistoryFragment fragment = new HealthHistoryFragment();
        fragment.setArguments(args);
        args.putBoolean(IS_FROM_VISIT_TIMELINE, isVisitTimeline);
        args.putInt(VISIT_ID,patientVisitAdmissionID);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isVisitTimeline = getArguments().getBoolean(IS_FROM_VISIT_TIMELINE);
        patientVisitAdmissionID = getArguments().getInt(VISIT_ID);
        arrData = new ArrayList<MenuModel>();
        tempArr = new ArrayList<String>();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();

        if (isVisitTimeline) {
            serviceCall();

        }
    }

    private void serviceCall() {
        SearchModel model = new SearchModel();
        model.setMRNumber(WebServiceConstants.tempMRN_LAB);
        model.setVisitID(patientVisitAdmissionID+"");
        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_VISIT_MENU,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<MenuModel>>() {
                                }.getType();
                                arrData = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                bindViews();
                            }

                            @Override
                            public void onError() {

                            }
                        });

    }

    private void bindViews() {

        tempArray();
        if (arrData.size() > 0) {
            for (int i = 0; i < arrData.size(); i = i + 2) {
                if (tempArr.contains(arrData.get(i).getTitle())) {

                    switch (arrData.get(i).getTitle()) {
                        case "Discharge Summary":
                            contSummary.setEnabled(false);
                            contSummary.setAlpha(.15f);
                            Log.d(TAG, "bindViews: DS ");
                            break;
                        case "Clinical Laboratory":
                            contLab.setEnabled(false);
                            contLab.setAlpha(.15f);
                            Log.d(TAG, "bindViews: CL ");
                            break;
                        case "Radiology":
                            contRadiology.setEnabled(false);
                            contRadiology.setAlpha(.15f);
                            Log.d(TAG, "bindViews: Rad");
                            break;
                        case "Medication Profile":
                            contImmunization.setEnabled(false);
                            contImmunization.setAlpha(.15f);
                            break;
                        case "Immunization Profile":
                            contMedicalProfile.setEnabled(false);
                            contMedicalProfile.setAlpha(.15f);
                            break;
                        case "Cardiopulmonary":
                            contCardio.setEnabled(false);
                            contCardio.setAlpha(.15f);

                            break;
                        case "Neurophysiology":
                            contNeuroPhysiology.setEnabled(false);
                            contNeuroPhysiology.setAlpha(.15f);
                            break;
                        case "Endoscopy":
                            contEndo.setEnabled(false);
                            contEndo.setAlpha(.15f);
                            break;
                    }
                }


            }
        }
    }
        private void tempArray () {
            tempArr = new ArrayList<String>();
            tempArr.add("Discharge Summary");
            tempArr.add("Clinical Laboratory");
            tempArr.add("Medication Profile");
            tempArr.add("Immunization Profile");
            tempArr.add("Cardiopulmonary");
            tempArr.add("Neurophysiology");
            tempArr.add("Endoscopy");
            tempArr.add("Radiology");
        }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_health_history;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        if (isVisitTimeline) {
            titleBar.setTitle("Visit Menu");
        } else {
            titleBar.setTitle("Health History");
        }
        titleBar.showBackButton(getBaseActivity());
        titleBar.setCircleImageView();
        titleBar.showHome(getBaseActivity());
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
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

    @OnClick({R.id.contLab, R.id.contRadiology, R.id.contMedicalProfile, R.id.contImmunization, R.id.contCardio, R.id.contNeuroPhysiology, R.id.contEndo, R.id.contSummary})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contLab:
                getBaseActivity().addDockableFragment(ClinicalLaboratoryFragment.newInstance());
                break;
            case R.id.contRadiology:
                getBaseActivity().addDockableFragment(RadiologyFragment.newInstance());
                break;
            case R.id.contMedicalProfile:
                showNextBuildToast();
                break;
            case R.id.contImmunization:
                getBaseActivity().addDockableFragment(ImmunizationProfileFragment.newInstance());
                break;
            case R.id.contCardio:
                getBaseActivity().addDockableFragment(CardiolopulmonaryFragment.newInstance());

                break;
            case R.id.contNeuroPhysiology:
                getBaseActivity().addDockableFragment(NeurophysiologyFragment.newInstance());
                break;
            case R.id.contEndo:
                getBaseActivity().addDockableFragment(EndoscopyFragment.newInstance());
                break;
            case R.id.contSummary:
                getBaseActivity().addDockableFragment(DischargeSummaryFragment.newInstance());
                break;
        }
    }
}