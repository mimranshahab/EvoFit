package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
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
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.TitleBar;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.SearchModel;
import edu.aku.family_hifazat.models.TimelineModel;
import edu.aku.family_hifazat.models.wrappers.MenuModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;

public class HealthHistoryFragment extends BaseFragment {


    private static final String IS_FROM_VISIT_TIMELINE = "isVisitTimeline";
    private static final String VISIT_ID = "patientVisitAdmissionID";

    Unbinder unbinder;
    @BindView(R.id.contLab)
    CardView contLab;
    @BindView(R.id.contRadiology)
    CardView contRadiology;
    @BindView(R.id.contMedicalProfile)
    CardView contMedicalProfile;
    @BindView(R.id.contImmunization)
    CardView contImmunization;
    @BindView(R.id.contCardio)
    CardView contCardio;
    @BindView(R.id.contNeuroPhysiology)
    CardView contNeuroPhysiology;
    @BindView(R.id.contEndo)
    CardView contEndo;
    @BindView(R.id.contSummary)
    CardView contSummary;
    @BindView(R.id.contParentLayout)
    LinearLayout contParentLayout;
    @BindView(R.id.txtTimelineView)
    AnyTextView txtTimelineView;
    @BindView(R.id.contTimeLinebar)
    LinearLayout contTimeLinebar;
    @BindView(R.id.txtClinicCount)
    AnyTextView txtClinicCount;
    @BindView(R.id.txtRadiologyCount)
    AnyTextView txtRadiologyCount;
    @BindView(R.id.txtMedicationCount)
    AnyTextView txtMedicationCount;
    @BindView(R.id.txtImmunizationCount)
    AnyTextView txtImmunizationCount;
    @BindView(R.id.txtCardioCount)
    AnyTextView txtCardioCount;
    @BindView(R.id.txtNeuroCount)
    AnyTextView txtNeuroCount;
    @BindView(R.id.txtEndoscopyCount)
    AnyTextView txtEndoscopyCount;
    @BindView(R.id.txtDischargeCount)
    AnyTextView txtDischargeCount;
    boolean isVisitTimeline;
    private List<MenuModel> arrData;
    private ArrayList<String> tempArr;
    private int patientVisitAdmissionID;
    private TimelineModel timelineModel;

    public static HealthHistoryFragment newInstance(boolean isVisitTimeline, int patientVisitAdmissionID, TimelineModel timelineModel) {

        Bundle args = new Bundle();

        HealthHistoryFragment fragment = new HealthHistoryFragment();
        fragment.setArguments(args);
        fragment.timelineModel = timelineModel;
        fragment.isVisitTimeline = isVisitTimeline;
        fragment.patientVisitAdmissionID = patientVisitAdmissionID;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrData = new ArrayList<MenuModel>();
        tempArr = new ArrayList<String>();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();

        if (isVisitTimeline) {
//            contTimeLinebar.setVisibility(View.VISIBLE);
//            txtTimelineView.setText("Displaying results for Visit Admission ID: " + patientVisitAdmissionID);
            contParentLayout.setVisibility(View.GONE);
            timelineServiceCall();
            setBadgesInvisible();
        } else {
            setBadgesInvisible();
        }

    }

    private void setBadgesInvisible() {
        txtClinicCount.setVisibility(View.INVISIBLE);
        txtRadiologyCount.setVisibility(View.INVISIBLE);
        txtMedicationCount.setVisibility(View.INVISIBLE);
        txtImmunizationCount.setVisibility(View.INVISIBLE);
        txtCardioCount.setVisibility(View.INVISIBLE);
        txtNeuroCount.setVisibility(View.INVISIBLE);
        txtDischargeCount.setVisibility(View.INVISIBLE);
        txtEndoscopyCount.setVisibility(View.INVISIBLE);

    }

    private void timelineServiceCall() {
        SearchModel model = new SearchModel();
        model.setMRNumber(getCurrentUser().getMRNumber());
        model.setVisitID(patientVisitAdmissionID + "");
        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.SHARED_MANAGER_GET_VISIT_MENU_LIST,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {


                                Type type = new TypeToken<ArrayList<MenuModel>>() {
                                }.getType();
                                arrData = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

//                                for (int i = 0; i < arrData.size(); i++) {
//                                    setCounts(i);
//                                }
                                bindViewsVisitTimeline();
                            }

                            @Override
                            public void onError() {
                                UIHelper.showToast(getContext(), "Something went wrong");
                                contParentLayout.setVisibility(View.GONE);
                            }
                        });

    }

    private void bindViewsVisitTimeline() {

        contParentLayout.setVisibility(View.VISIBLE);
        tempArray();
        if (arrData.size() > 0) {
            contImmunization.setEnabled(false);
            txtImmunizationCount.setVisibility(View.INVISIBLE);
            contImmunization.setAlpha(.15f);
            for (int i = 0; i < arrData.size(); i++) {

                if (arrData.get(i).getTitle().equals("Medicines")) {
                    Log.d(TAG, "MEDICINES: " + arrData.get(i).getTotalCount());
                }

                if (arrData.get(i).getTotalCount() < 1) {

                    switch (arrData.get(i).getTitle()) {

                        case "Discharge Summary":
                            contSummary.setEnabled(false);
                            contSummary.setAlpha(.15f);
                            Log.d(TAG, "bindViewsVisitTimeline: DS ");
                            txtDischargeCount.setVisibility(View.INVISIBLE);

                            break;
                        case "Laboratory":
                            contLab.setEnabled(false);
                            contLab.setAlpha(.15f);
                            Log.d(TAG, "bindViewsVisitTimeline: CL ");
                            txtClinicCount.setVisibility(View.INVISIBLE);

                            break;
                        case "Radiology":

                            contRadiology.setEnabled(false);
                            contRadiology.setAlpha(.15f);
                            Log.d(TAG, "bindViewsVisitTimeline: Rad");
                            txtRadiologyCount.setVisibility(View.INVISIBLE);

                            break;

                        case "Medicines":
                            contMedicalProfile.setEnabled(false);
                            contMedicalProfile.setAlpha(.15f);
                            txtMedicationCount.setVisibility(View.INVISIBLE);

                            break;
                        case "Cardiopulmonary":
                            contCardio.setEnabled(false);
                            contCardio.setAlpha(.15f);
                            txtCardioCount.setVisibility(View.INVISIBLE);

                            break;
                        case "Neurophysiology":
                            contNeuroPhysiology.setEnabled(false);
                            contNeuroPhysiology.setAlpha(.15f);
                            txtNeuroCount.setVisibility(View.INVISIBLE);


                            break;
                        case "Endoscopy":
                            contEndo.setEnabled(false);
                            contEndo.setAlpha(.15f);
                            txtEndoscopyCount.setVisibility(View.INVISIBLE);

                            break;
                    }
                }
            }
        } else {
            setBadgesInvisible();
        }

    }

    private void setCounts(int i) {
        if (txtNeuroCount == null) {
            return;
        }
//        txtNeuroCount.setText(String.valueOf(arrData.get(i).getNotificationCount()));
//        txtClinicCount.setText(String.valueOf(arrData.get(i).getNotificationCount()));
//        txtRadiologyCount.setText(String.valueOf(arrData.get(i).getNotificationCount()));
//        txtImmunizationCount.setText(String.valueOf(arrData.get(i).getNotificationCount()));
//        txtMedicationCount.setText(String.valueOf(arrData.get(i).getNotificationCount()));
//        txtDischargeCount.setText(String.valueOf(arrData.get(i).getNotificationCount()));
//        txtCardioCount.setText(String.valueOf(arrData.get(i).getNotificationCount()));
    }

    private void tempArray() {
        tempArr = new ArrayList<String>();
        tempArr.add("Discharge Summary");
        tempArr.add("Clinical Laboratory");
        tempArr.add("Medication Profile");
        tempArr.add("Cardiopulmonary");
        tempArr.add("Neurophysiology");
        tempArr.add("Endoscopy");
        tempArr.add("Radiology");
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_health_historyv1;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        if (isVisitTimeline) {
            titleBar.setTitle("Visit Detail");
            titleBar.setUserTimeLineDisplay(sharedPreferenceManager.getCurrentUser(), getContext(), timelineModel);
        } else {
            titleBar.setTitle("Health Profile");
            titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        }
        titleBar.showBackButton(getBaseActivity());

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
                getBaseActivity().addDockableFragment(ClinicalLaboratoryFragment.newInstance(isVisitTimeline, patientVisitAdmissionID), false);
                break;
            case R.id.contRadiology:
                getBaseActivity().addDockableFragment(RadiologyFragment.newInstance(isVisitTimeline, patientVisitAdmissionID), false);
                break;
            case R.id.contMedicalProfile:
                getBaseActivity().addDockableFragment(MedicationTabLayout.newInstance(isVisitTimeline, patientVisitAdmissionID), false);
                break;
            case R.id.contImmunization:
                getBaseActivity().addDockableFragment(ImmunizationProfileFragment.newInstance(isVisitTimeline, patientVisitAdmissionID), false);
                break;
            case R.id.contCardio:
                getBaseActivity().addDockableFragment(CardiolopulmonaryFragment.newInstance(isVisitTimeline, patientVisitAdmissionID), false);

                break;
            case R.id.contNeuroPhysiology:
                getBaseActivity().addDockableFragment(NeurophysiologyFragment.newInstance(isVisitTimeline, patientVisitAdmissionID), false);
                break;
            case R.id.contEndo:
                getBaseActivity().addDockableFragment(EndoscopyFragment.newInstance(isVisitTimeline, patientVisitAdmissionID), false);
                break;
            case R.id.contSummary:
                getBaseActivity().addDockableFragment(DischargeSummaryFragment.newInstance(isVisitTimeline, patientVisitAdmissionID), false);
                break;
        }
    }
}