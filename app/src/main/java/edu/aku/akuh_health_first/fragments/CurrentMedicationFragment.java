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
import edu.aku.akuh_health_first.adapters.recyleradapters.CurrentMedicationAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.MedicationProfileModel;
import edu.aku.akuh_health_first.models.SearchModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.widget.AnyTextView;

import static edu.aku.akuh_health_first.constatnts.AppConstants.NO_RECORD_FOUND;


/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class CurrentMedicationFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

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

    public static CurrentMedicationFragment newInstance(boolean isFromTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();

        CurrentMedicationFragment fragment = new CurrentMedicationFragment();
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
        updateData();

    }

    private void updateData() {
        if (sharedPreferenceManager.getCurrentUser().getVisitDetail().getIsPatientExists()) {
            showEmptyView("No Current Medication since patient is onBoard");
            mFab.setVisibility(View.GONE);
        } else {
            mFab.setVisibility(View.VISIBLE);
            serviceCall();
        }
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
                getBaseActivity().addDockableFragment(AddUpdateMedicationFragment.newInstance(true, null), false);
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
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_CURRENT_MEDICATION,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<MedicationProfileModel>>() {
                                }.getType();
                                ArrayList<MedicationProfileModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                if (arrayList.size() > 0) {
                                    if (arrayList.get(0).getMedicineexceedlimit()) {
                                        mFab.setEnabled(false);
                                        UIHelper.showToast(getContext(), "Medication Limit Exceed.");
                                    } else {
                                        mFab.setEnabled(true);
                                    }
                                } else {
                                    showEmptyView(NO_RECORD_FOUND);
                                 }

                                arrData.clear();
                                arrData.addAll(arrayList);
                                adapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onError() {
                                showEmptyView(NO_RECORD_FOUND);
                            }
                        });

    }

    private void showEmptyView(String text) {

        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText(text);

        switchTab();
    }

    private void switchTab() {
        if (((MedicationTabLayout)getParentFragment()) != null) {
            ((MedicationTabLayout)getParentFragment()).viewpager.setCurrentItem(1, true);
        }
    }
}
