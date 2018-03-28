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
import android.widget.LinearLayout;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.HealthSummaryAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.enums.HealthSummaryTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.fragments.dialogs.HealthSummaryDialogFragment;
import edu.aku.akuh_health_first.helperclasses.StringHelper;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.DetailHealthSummaryModel;
import edu.aku.akuh_health_first.models.PatientHealthSummaryModel;
import edu.aku.akuh_health_first.models.SearchModel;
import edu.aku.akuh_health_first.models.Shortmessagemobile;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.widget.AnyTextView;
import edu.aku.akuh_health_first.widget.recyclerview_layout.CustomLayoutManager;

/**
 * Created by aqsa.sarwar on 1/26/2018.
 */

public class HealthSummaryFragment extends BaseFragment implements OnItemClickListener {
    Unbinder unbinder;
    //    Typeface regular, bold, light;


    HealthSummaryAdapter adapter;
    ArrayList<DetailHealthSummaryModel> arrData;
    @BindView(R.id.txtBloodType)
    AnyTextView txtBloodType;
    @BindView(R.id.txtHeightDate)
    AnyTextView txtHeightDate;
    @BindView(R.id.txtHeight)
    AnyTextView txtHeight;
    @BindView(R.id.txtWeightDate)
    AnyTextView txtWeightDate;
    @BindView(R.id.txtWeight)
    AnyTextView txtWeight;
    @BindView(R.id.recylerView)
    RecyclerView recyclerView;
    @BindView(R.id.contLastLab)
    LinearLayout contLastLab;


    public static HealthSummaryFragment newInstance() {

        Bundle args = new Bundle();

        HealthSummaryFragment fragment = new HealthSummaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        regular = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto-Regular_1B.ttf");
//        bold = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto-Medium_2.ttf");
//        light = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto-Light_D.ttf");


        arrData = new ArrayList<>();

        adapter = new HealthSummaryAdapter(getBaseActivity(), arrData, this);
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_health_summary_v1;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Health Summary");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        setSpannyText();

        bindView();
        serviceCallBasicHealthSummary();
        serviceCallDetailedHealthSummary();

    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new CustomLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false);
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

//    private void setSpannyText() {
//
////
////        Spanny greenText = new Spanny("67.5kg",
////                new CustomTypefaceSpan(bold),
////                new ForegroundColorSpan(getResources().getColor(R.color.c_green)))
////                .append("\nWeight",
////                        new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.s14)),
////                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)));
////        Spanny redText = new Spanny("5+",
////                new CustomTypefaceSpan(bold),
////                new ForegroundColorSpan(getResources().getColor(R.color.base_reddish)))
////                .append("\nBlood Type",
////                        new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.s14)),
////                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)));
////
////
////        Spanny spanny = new Spanny("Glucose Random",
////                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey))).append(" 302 mg/dl ",
////                new ForegroundColorSpan(getResources().getColor(R.color.base_reddish))).append("\nMarch 04,2017 12:20",
////                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey))).append(" (Panic High)",
////                new ForegroundColorSpan(getResources().getColor(R.color.base_reddish))
////        );
////
////        Spanny spanny1 = new Spanny("Serum Creatinline ",
////                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey))).append(" 1.1 mg/dl ",
////                new ForegroundColorSpan(getResources().getColor(R.color.base_blue))).append("\nMarch 04,2017 12:20",
////                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey))).append(" (Normal)",
////                new ForegroundColorSpan(getResources().getColor(R.color.c_applegreen))
////        );
////
////        Spanny medi1 = new Spanny("Lipitor",
////                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
////                new CustomTypefaceSpan(light)).append(" 10mg Oral", new CustomTypefaceSpan(bold))
////                .append("\nTRASTUZUMAB ",
////                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
////                        new CustomTypefaceSpan(light)).append("440mg/20ml Oral", new CustomTypefaceSpan(bold))
////                .append("\nTRASTUZUMAB ",
////                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
////                        new CustomTypefaceSpan(light)).append("440mg/20ml", new CustomTypefaceSpan(bold))
////                .append("\nSodium Chloride ",
////                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
////                        new CustomTypefaceSpan(light)).append("440mg/20ml", new CustomTypefaceSpan(bold));
////
////        Spanny lastVisit = new Spanny("Date: ",
////                new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
////                new CustomTypefaceSpan(light)).append(" Mar 04, 2017", new CustomTypefaceSpan(light))
////                .append("\nLocation: ",
////                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
////                        new CustomTypefaceSpan(light)).
////                        append("Stadium Road, Community Health Center", new CustomTypefaceSpan(light))
////                .append("\nDoctor: ",
////                        new ForegroundColorSpan(getResources().getColor(R.color.text_color_grey)),
////                        new CustomTypefaceSpan(light)).append("Rabia Hassan Shaikh", new CustomTypefaceSpan(light));
////
//////
//////        txtReport.setText(spanny);
//////        txtReport1.setText(spanny1);
//////        txtActiveMedication.setText(medi1);
//////        txtLastVisit.setText(lastVisit);
//////        myTextProgressGreen.setText(greenText);
//////        myTextProgress.setText(redText);
////        Spanny bloodType = new Spanny("B+", new CustomTypefaceSpan(bold));
////        Spanny weight = new Spanny("47.5", new CustomTypefaceSpan(bold)).append("kg",
////                new AbsoluteSizeSpan(getBaseActivity().getResources().getDimensionPixelSize(R.dimen.s12)));
////
////
////        Spanny height = new Spanny("5.5", new CustomTypefaceSpan(bold)).append("ft\n",
////                new AbsoluteSizeSpan(getBaseActivity().getResources().getDimensionPixelSize(R.dimen.s12))).append("166",
////                new CustomTypefaceSpan(bold)).append("cm",
////                new AbsoluteSizeSpan(getBaseActivity().getResources().getDimensionPixelSize(R.dimen.s12)));
////
////        txtBloodType.setText(bloodType);
////        txtHeight.setText(height);
////        txtWeight.setText(weight);
//    }


    private void serviceCallBasicHealthSummary() {

        SearchModel model = new SearchModel();
        model.setMRNumber(getCurrentUser().getMRNumber());


        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_PATIENT_HEALTH_SUMMARY,
                        model.toString(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {

                                PatientHealthSummaryModel patientHealthSummaryModel = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , PatientHealthSummaryModel.class);

                                txtBloodType.setText(patientHealthSummaryModel.getBloodtype());

                                if (StringHelper.IsInt_ByJonas(patientHealthSummaryModel.getWeight()) || StringHelper.IsDecimal_ByCompiledRegex(patientHealthSummaryModel.getWeight())) {
                                    txtWeight.setText(patientHealthSummaryModel.getWeight() + "kg");
                                } else {
                                    txtWeight.setText(patientHealthSummaryModel.getWeight());
                                }

                                if (StringHelper.IsInt_ByJonas(patientHealthSummaryModel.getHeight()) || StringHelper.IsDecimal_ByCompiledRegex(patientHealthSummaryModel.getHeight())) {
                                    txtHeight.setText(patientHealthSummaryModel.getHeight() + "cm");
                                } else {
                                    txtHeight.setText(patientHealthSummaryModel.getHeight());
                                }
                                txtHeightDate.setText(patientHealthSummaryModel.getHeightdate());
                                txtWeightDate.setText(patientHealthSummaryModel.getWeightdate());

                            }

                            @Override
                            public void onError() {

                            }
                        });

    }


    private void serviceCallDetailedHealthSummary() {

        SearchModel model = new SearchModel();
        model.setMRNumber(getCurrentUser().getMRNumber());


        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_DETAIL_HEALTH_SUMMARY,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<DetailHealthSummaryModel>>() {
                                }.getType();
                                ArrayList<DetailHealthSummaryModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                arrData.clear();
                                arrData.addAll(arrayList);
                                adapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onError() {

                            }
                        });

    }


    @Override
    public void onItemClick(int position, Object object) {
        if (object instanceof DetailHealthSummaryModel) {
            DetailHealthSummaryModel model = (DetailHealthSummaryModel) object;

            boolean isLinkToHistory = Boolean.parseBoolean(model.getLinktohistory());


            if (isLinkToHistory) {
                HealthSummaryTypes state = HealthSummaryTypes.fromStringForm(model.getWebandmobilemodel().getLink());

                if (state == null) {
                    UIHelper.showToast(getContext(), "No Redirection Available");
                    return;
                }
                switch (state) {
                    case Allergies:
                        UIHelper.showToast(getContext(), "No Redirection Available");
                        break;
                    case ClinicalLaboratory:
                        getBaseActivity().addDockableFragment(ClinicalLaboratoryFragment.newInstance(false, -1));
                        break;
                    case Radiology:
                        getBaseActivity().addDockableFragment(RadiologyFragment.newInstance(false, -1));
                        break;
                    case ActiveMedication:
                        getBaseActivity().addDockableFragment(MedicationTabLayout.newInstance(false, -1));
                        break;
                    case ImmunizationProfile:
                        getBaseActivity().addDockableFragment(ImmunizationProfileFragment.newInstance(false, -1));
                        break;
                    case LastVisit:
                        getBaseActivity().addDockableFragment(TimelineFragment.newInstance());
                        break;
                    case FutureAppointment:
                        UIHelper.showToast(getContext(), "No Redirection Available");
                        break;
                }
            } else {
                if (model.getDetailMessageMobileArray().isEmpty()) {
                    UIHelper.showToast(getContext(), "No Details available");
                } else {
                    showDetailDialog(model.getDetailMessageMobileArray(), model.getSummarytitle());
                }
            }


        }
    }


    public void showDetailDialog(ArrayList<Shortmessagemobile> arrData, String title) {
        final HealthSummaryDialogFragment dialogFragment = HealthSummaryDialogFragment.newInstance(title);
        dialogFragment.setArrData(arrData);
        dialogFragment.show(getFragmentManager(), null);
    }
}
