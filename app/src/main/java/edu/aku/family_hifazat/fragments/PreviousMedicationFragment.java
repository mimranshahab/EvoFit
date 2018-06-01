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

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.adapters.recyleradapters.CurrentMedicationAdapter;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.TitleBar;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.MedicationProfileModel;
import edu.aku.family_hifazat.models.SearchModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;


/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class PreviousMedicationFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.recylerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.empty_view)
    AnyTextView emptyView;
    private ArrayList<MedicationProfileModel> arrData;
    private CurrentMedicationAdapter adapter;
    boolean isFromTimeline;
    int patientVisitAdmissionID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrData = new ArrayList<MedicationProfileModel>();
        adapter = new CurrentMedicationAdapter(getBaseActivity(), arrData, this);

    }

    public static PreviousMedicationFragment newInstance(boolean isFromTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();

        PreviousMedicationFragment fragment = new PreviousMedicationFragment();
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
        titleBar.setTitle("Medicines");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        adapter.isHistory = true;
        if (onCreated) {
            return;
        }
        serviceCall();
    }


    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListeners() {


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextBuildToast();
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
        if (object instanceof MedicationProfileModel) {
//            getBaseActivity().addDockableFragment(AddUpdateVaccineFragment.newInstance(false, (MedicationProfileModel) object, arrUsedVaccineDes));

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
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_PREVIOUS_MEDICATION,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<MedicationProfileModel>>() {
                                }.getType();
                                ArrayList<MedicationProfileModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                arrData.clear();
                                arrData.addAll(arrayList);
                                adapter.notifyDataSetChanged();

                                if (arrData.size() > 0) {
                                    showView();
                                } else {
                                    showEmptyView(AppConstants.NO_MEDICATION_HISTORY_MESSAGE);
                                }
                            }

                            @Override
                            public void onError() {
//                                UIHelper.showShortToastInCenter(getContext(), "failure");
                                showEmptyView(AppConstants.NO_MEDICATION_HISTORY_MESSAGE);
                            }
                        });

    }

    private void showEmptyView(String text) {
        emptyView.setText(text);
        emptyView.setVisibility(View.VISIBLE);
    }

    private void showView() {
        bindView();
        emptyView.setVisibility(View.GONE);

    }
}
