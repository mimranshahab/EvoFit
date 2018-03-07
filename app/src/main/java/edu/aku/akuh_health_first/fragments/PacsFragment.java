package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.PacsActivity;
import edu.aku.akuh_health_first.adapters.recyleradapters.PacsDescriptionAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.PacsDescriptionModel;
import edu.aku.akuh_health_first.models.PacsModel;
import edu.aku.akuh_health_first.models.RadiologyModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.views.AnyTextView;


/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class PacsFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    private static final String RADIOLOGY_MODEL_KEY = "RADIOLOGY_MODEL_KEY";
    @BindView(R.id.recylerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.empty_view)
    AnyTextView emptyView;

    private PacsDescriptionAdapter adapterPacsDescriptionAdapter;
    private RadiologyModel radioModel;
    private ArrayList<PacsDescriptionModel> arrData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrData = new ArrayList<PacsDescriptionModel>();
        adapterPacsDescriptionAdapter = new PacsDescriptionAdapter(getBaseActivity(), arrData, this);
    }

    public static PacsFragment newInstance(RadiologyModel item) {

        Bundle args = new Bundle();
        PacsFragment fragment = new PacsFragment();
        fragment.radioModel = item;
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
        titleBar.setTitle("Pacs Detail");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay();
        titleBar.showHome(getBaseActivity());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

             /*
        //////////Get Bearer token
         */
        bindView();
        serviceCall();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapterPacsDescriptionAdapter);
    }

    @Override
    public void setListeners() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                serviceCall();
                refreshLayout.setRefreshing(false);
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

        if (object instanceof PacsDescriptionModel) {
            PacsDescriptionModel pacsDescriptionModel = (PacsDescriptionModel) object;

            sharedPreferenceManager.putValue("JSON_STRING_KEY", pacsDescriptionModel.toString());

            getBaseActivity().openActivity(PacsActivity.class, "");

        }
    }


    private void serviceCall() {
        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.PACS_VIEWER)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_PACS_MANAGER,
                        WebServiceConstants.METHOD_PACS_ACCESSIONS + radioModel.getAccessionnumberwithComma() + WebServiceConstants.METHOD_PACS_ACCESSIONS_end
                        ,
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                PacsModel pacsModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, PacsModel.class);
                                ArrayList arrayList = new ArrayList<PacsDescriptionAdapter>();
//                                if (pacsModel != null) {
                                for (int i = 0; i < pacsModel.getPatient_Name().size(); i++) {

                                    PacsDescriptionModel pacsDescriptionModel = new PacsDescriptionModel();

                                    pacsDescriptionModel.setPatient_Name(pacsModel.getPatient_Name().get(i));
                                    pacsDescriptionModel.setPatientDOB(pacsModel.getPatientDOB().get(i));
                                    pacsDescriptionModel.setPatientAge(pacsModel.getPatientAge().get(i));
                                    pacsDescriptionModel.setPatientGender(pacsModel.getPatientGender().get(i));
                                    pacsDescriptionModel.setPatientMRN(pacsModel.getPatientMRN().get(i));
                                    pacsDescriptionModel.setStudyDataCount(pacsModel.getStudyDataCount().get(i));
                                    pacsDescriptionModel.setStudyTitle(pacsModel.getStudyTitle().get(i));
                                    pacsDescriptionModel.setStudyDataString(pacsModel.getStudyDataString().get(i));
                                    pacsDescriptionModel.setStudyDataDateTime(pacsModel.getStudyDataDateTime().get(i));


                                    arrayList.add(pacsDescriptionModel);

                                }

                                arrData.clear();

                                arrData.addAll(arrayList);
                                adapterPacsDescriptionAdapter.notifyDataSetChanged();

                                if (arrData.size() > 0) {
                                    showView();

                                } else {
                                    showEmptyView();
                                }


                            }

                            @Override
                            public void onError() {

                            }
                        });


    }

    private void showEmptyView() {
        refreshLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    private void showView() {
        bindView();
        emptyView.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.VISIBLE);
    }

}
