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
import edu.aku.family_hifazat.adapters.recyleradapters.RadiologyAdapter;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.TitleBar;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.RadiologyModel;
import edu.aku.family_hifazat.models.SearchModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;


/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class RadiologyFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.recylerView)
    RecyclerView recyclerView;

    Unbinder unbinder;
    @BindView(R.id.empty_view)
    AnyTextView emptyView;
    private ArrayList<RadiologyModel> arrData;
    private RadiologyAdapter adapterRadiology;
    boolean isFromTimeline;
    int patientVisitAdmissionID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrData = new ArrayList<RadiologyModel>();
        adapterRadiology = new RadiologyAdapter(getBaseActivity(), arrData, this);
    }

    public static RadiologyFragment newInstance(boolean isFromTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();
        RadiologyFragment fragment = new RadiologyFragment();
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
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Radiology");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bearerTokenCall();
        bindView();

        if (onCreated) {
            return;
        }
        serviceCall();


    }


    private void bearerTokenCall() {
        new WebServices(getContext()).webServicegetToken(new WebServices.IRequestStringCallBack() {
            @Override
            public void requestDataResponse(String webResponse) {
                WebServices.setBearerToken(webResponse);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapterRadiology);
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
        switch (view.getId()) {
            case R.id.RlGraph:

                showPacsImages(adapterRadiology.getItem(position));
                break;
            case R.id.RlReport:
                RadiologyModel object = adapterRadiology.getItem(position);
                if (object != null) {
                    RadiologyModel radiologyModel = (RadiologyModel) object;
//                    radiologyModel.setExamordernumber("4416119");
//                    radiologyModel.setExamorderexamnumber("1");
//                    radiologyModel.setVisitlocation("Stadium Road");
//                    radiologyModel.setReportid("15779861");
                    getBaseActivity().addDockableFragment(RadiologyDescriptionFragment.newInstance(radiologyModel.toString()), false);
                }


                break;
        }
    }

    private void showPacsImages(final RadiologyModel item) {


        getBaseActivity().addDockableFragment(PacsFragment.newInstance(item), false);


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
            model.setMRNumber(getCurrentUser().getMRNumber());
            model.setVisitID(patientVisitAdmissionID + "");
        } else {
            model.setVisitID(null);
        }
        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_GET_RADIOLOGY_EXAMS,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<RadiologyModel>>() {
                                }.getType();
                                ArrayList<RadiologyModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                arrData.clear();
                                arrData.addAll(arrayList);
                                adapterRadiology.notifyDataSetChanged();


                                if (arrData.size() > 0) {
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

    private void showEmptyView() {

        emptyView.setVisibility(View.VISIBLE);
    }

    private void showView() {
        bindView();
        emptyView.setVisibility(View.GONE);

    }

}