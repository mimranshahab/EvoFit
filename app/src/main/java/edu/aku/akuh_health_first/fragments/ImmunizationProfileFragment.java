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

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.ImmunizationAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.ImmunizationModel;
import edu.aku.akuh_health_first.models.SearchModel;
import edu.aku.akuh_health_first.models.SpinnerModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.widget.AnyTextView;


/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class ImmunizationProfileFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.recylerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.empty_view)
    AnyTextView emptyView;
    private ArrayList<ImmunizationModel> arrImmunization;
    private ImmunizationAdapter adapterImmunization;
    boolean isFromTimeline;
    int patientVisitAdmissionID;
    private ArrayList<SpinnerModel> arrUsedVaccineDes = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrImmunization = new ArrayList<ImmunizationModel>();
        adapterImmunization = new ImmunizationAdapter(getBaseActivity(), arrImmunization, this);
    }

    public static ImmunizationProfileFragment newInstance(boolean isFromTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();

        ImmunizationProfileFragment fragment = new ImmunizationProfileFragment();
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
        titleBar.setTitle("Vaccines");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        isImmunizationRecordExistServce();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapterImmunization);
    }

    @Override
    public void setListeners() {

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().addDockableFragment(AddUpdateVaccineFragment.newInstance(true, null, arrUsedVaccineDes));
            }
        });
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
        if (object instanceof ImmunizationModel) {
            getBaseActivity().addDockableFragment(AddUpdateVaccineFragment.newInstance(false, (ImmunizationModel) object, arrUsedVaccineDes));
        }
    }


    private void getVaccineScheduleService() {
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
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_IMMUNIZATION_VACCINE_SCHEDULE,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<ImmunizationModel>>() {
                                }.getType();
                                ArrayList<ImmunizationModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                arrImmunization.clear();
                                arrImmunization.addAll(arrayList);
                                adapterImmunization.notifyDataSetChanged();

                                arrUsedVaccineDes.clear();
                                for (ImmunizationModel immunizationModel : arrImmunization) {
                                    arrUsedVaccineDes.add(new SpinnerModel(immunizationModel.getDescription()));
                                }

                                if (arrImmunization.size() > 0) {
                                    showView();
                                } else {
                                    showEmptyView();
                                }
                            }

                            @Override
                            public void onError() {
//                                UIHelper.showShortToastInCenter(getContext(), "failure");
                                showEmptyView();
                            }
                        });

    }


    private void isImmunizationRecordExistServce() {
        SearchModel model = new SearchModel();
        model.setMRNumber(getCurrentUser().getMRNumber());
        model.setVisitID(null);

        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_IMMUNIZATION_RECORD_EXIST,
                        model.toString(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {

                                ImmunizationModel immunizationModel = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , ImmunizationModel.class);

                                if (immunizationModel.isImmunizationRecordFound()) {
                                    mFab.setVisibility(View.VISIBLE);
                                    getVaccineScheduleService();
                                } else {
                                    mFab.setVisibility(View.GONE);
                                    showEmptyView("No Immunization Record Found");
                                }
                            }

                            @Override
                            public void onError() {
                                mFab.setVisibility(View.GONE);
                                showEmptyView("No Immunization Record Found");
                            }
                        });

    }

    private void showEmptyView(String text) {

        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText(text);
    }


    private void showEmptyView() {

        emptyView.setVisibility(View.VISIBLE);
    }

    private void showView() {
        bindView();
        emptyView.setVisibility(View.GONE);

    }
}
