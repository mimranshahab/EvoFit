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

import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.GraphActivity;
import edu.aku.akuh_health_first.adapters.recyleradapters.ClinicalLabDetailAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.LaboratoryModel;
import edu.aku.akuh_health_first.models.LaboratoryUpdateModel;
import edu.aku.akuh_health_first.models.SearchModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/25/2018.
 */

public class ClinicalLaboratoryDetailFragment extends BaseFragment implements OnItemClickListener {
    @BindView(R.id.txtCollectionDate)
    AnyTextView txtCollectionDate;
    @BindView(R.id.txtReportedDateTime)
    AnyTextView txtReportedDateTime;

    @BindView(R.id.listClinicalLabResult)
    RecyclerView listClinicalLabResult;
    Unbinder unbinder;
    private ArrayList<LaboratoryModel> arrClinicalLabLists;
    private ClinicalLabDetailAdapter adapterClinicalLabDetail;


    public static ClinicalLaboratoryDetailFragment newInstance() {

        Bundle args = new Bundle();

        ClinicalLaboratoryDetailFragment fragment = new ClinicalLaboratoryDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_clinical_lab_detail;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Lab Detail");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setCircleImageView( );
        titleBar.showHome(getBaseActivity());
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrClinicalLabLists = new ArrayList<LaboratoryModel>();
        adapterClinicalLabDetail = new ClinicalLabDetailAdapter(getBaseActivity(), arrClinicalLabLists, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        serviceCall();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());

        listClinicalLabResult.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) listClinicalLabResult.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        listClinicalLabResult.setLayoutAnimation(animation);
        listClinicalLabResult.setAdapter(adapterClinicalLabDetail);
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

    private void serviceCall() {
        // FIXME: 1/18/2018 Use live data in future
        SearchModel model = new SearchModel();
        model.setRecordID(WebServiceConstants.temp_Specimen_Num);
        model.setVisitID(null);
        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_CLINICAL_LAB_DETAILS,
                        model.toString(),


//                        WebServiceConstants.temp_Specimen_Num,
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        LaboratoryUpdateModel laboratoryUpdateModel =
                                GsonFactory.getSimpleGson().fromJson(webResponse.result, LaboratoryUpdateModel.class);

                            }

                            @Override
                            public void onError() {
                                UIHelper.showShortToastInCenter(getContext(), "failure");
                            }
                        });

    }

    @Override
    public void onItemClick(int position, Object object) {
        if (object instanceof LaboratoryModel) {
            LaboratoryModel laboratoryModel = (LaboratoryModel) object;
            if (laboratoryModel.isNumeric()) {
                getBaseActivity().openActivity(GraphActivity.class, laboratoryModel);
            }
        }

    }
}
