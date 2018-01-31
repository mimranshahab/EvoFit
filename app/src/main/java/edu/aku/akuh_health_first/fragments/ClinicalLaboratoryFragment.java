package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.os.Handler;
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
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.ClinicalLabAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.FileManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.LaboratoryModel;
import edu.aku.akuh_health_first.models.Neurophysiology;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

import static edu.aku.akuh_health_first.constatnts.AppConstants.DOC_PATH;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class ClinicalLaboratoryFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.listNeurophysiology)
    RecyclerView recyclerNeurophysiology;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    Unbinder unbinder;
    private ArrayList<LaboratoryModel> arrClinicalLabLists;
    private ClinicalLabAdapter adaptNeuropysiology;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrClinicalLabLists = new ArrayList<LaboratoryModel>();
        adaptNeuropysiology = new ClinicalLabAdapter(getBaseActivity(), arrClinicalLabLists, this);
    }

    public static ClinicalLaboratoryFragment newInstance() {

        Bundle args = new Bundle();

        ClinicalLaboratoryFragment fragment = new ClinicalLaboratoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_neurophysiology;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Clinical Laboratory");
        titleBar.showBackButton(getBaseActivity());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        serviceCall();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerNeurophysiology.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerNeurophysiology.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerNeurophysiology.setLayoutAnimation(animation);
        recyclerNeurophysiology.setAdapter(adaptNeuropysiology);
    }

    @Override
    public void setListeners() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                serviceCall();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
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

            getBaseActivity().addDockableFragment(ClinicalLaboratoryDetailFragment.newInstance());


        }

    }


    private void serviceCall() {
        // FIXME: 1/18/2018 Use live data in future
        UserDetailModel currentUser = sharedPreferenceManager.getCurrentUser();
        currentUser.setMRNumber(WebServiceConstants.tempMRN_LAB);

        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_CLINICAL_LAB,
                        currentUser.getMRNumberwithComma(),
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
                                adaptNeuropysiology.notifyDataSetChanged();
                            }

                            @Override
                            public void onError() {
                                UIHelper.showShortToastInCenter(getContext(), "failure");
                            }
                        });

    }
}