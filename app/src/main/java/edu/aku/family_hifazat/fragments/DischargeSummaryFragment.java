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

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.adapters.recyleradapters.DischargeSummaryAdapter;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.TitleBar;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.DischargeSummaryModel;
import edu.aku.family_hifazat.models.SearchModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/30/2018.
 */

public class DischargeSummaryFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {
    @BindView(R.id.recylerView)
    RecyclerView recylerViewDischageSummary;

    @BindView(R.id.empty_view)
    AnyTextView emptyView;

    Unbinder unbinder;
    private ArrayList<DischargeSummaryModel> arrDischargeSummary;
    private DischargeSummaryAdapter adapterDischargesummary;
    boolean isFromTimeline;
    int patientVisitAdmissionID;

    public static DischargeSummaryFragment newInstance(boolean isFromTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();

        DischargeSummaryFragment fragment = new DischargeSummaryFragment();
        fragment.isFromTimeline = isFromTimeline;
        fragment.patientVisitAdmissionID = patientVisitAdmissionID;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrDischargeSummary = new ArrayList<DischargeSummaryModel>();
        adapterDischargesummary = new DischargeSummaryAdapter(getBaseActivity(), arrDischargeSummary, this);
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        if (onCreated) {
            return;
        }
        serviceCall();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recylerViewDischageSummary.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recylerViewDischageSummary.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recylerViewDischageSummary.setLayoutAnimation(animation);
        recylerViewDischageSummary.setAdapter(adapterDischargesummary);
    }

    private void serviceCall() {
        SearchModel model = new SearchModel();
        model.setMRNumber(getCurrentUser().getMRNumber());
        if (isFromTimeline) {
            model.setVisitID(String.valueOf(patientVisitAdmissionID));
        } else {
            model.setVisitID(null);
        }
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_DISCHARGE_SUMMARY_LIST,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {


                                Type type = new TypeToken<ArrayList<DischargeSummaryModel>>() {
                                }.getType();
                                ArrayList<DischargeSummaryModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                arrDischargeSummary.clear();
                                arrDischargeSummary.addAll(arrayList);
                                adapterDischargesummary.notifyDataSetChanged();

                                if (arrDischargeSummary.size() > 0) {
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

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_general_recyler_view;
    }

    @Override
    public void setTitlebar(final TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Discharge Summary");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
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


    private void showReportAPI(final DischargeSummaryModel summaryModel) {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForWebResponseWithString(WebServiceConstants.METHOD_SHOW_REPORT_DS,
                        summaryModel.toString(), new WebServices.IRequestWebResponseWithStringDataCallBack() {
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
    public void onItemClick(int position, Object object) {
        if (object instanceof DischargeSummaryModel) {
            final DischargeSummaryModel summaryModel = (DischargeSummaryModel) object;
            showReportAPI(summaryModel);
        }

    }

    private void showEmptyView() {
         emptyView.setVisibility(View.VISIBLE);
    }

    private void showView() {
         emptyView.setVisibility(View.GONE);
     }
}