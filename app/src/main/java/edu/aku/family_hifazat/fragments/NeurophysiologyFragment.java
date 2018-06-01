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
import edu.aku.family_hifazat.adapters.recyleradapters.NeurophysiologyAdapter;
import edu.aku.family_hifazat.callbacks.GenericClickableInterface;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.fragments.abstracts.GenericDialogFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.models.NeurophysiologyModel;
import edu.aku.family_hifazat.widget.TitleBar;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.SearchModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;


/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class NeurophysiologyFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.recylerView)
    RecyclerView recyclerNeurophysiology;

    Unbinder unbinder;
    @BindView(R.id.empty_view)
    AnyTextView emptyView;
    private ArrayList<NeurophysiologyModel> arrData;
    private NeurophysiologyAdapter adaptNeuropysiology;
    boolean isFromTimeline;
    int patientVisitAdmissionID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrData = new ArrayList<NeurophysiologyModel>();
        adaptNeuropysiology = new NeurophysiologyAdapter(getBaseActivity(), arrData, this);
    }

    public static NeurophysiologyFragment newInstance(boolean isFromTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();

        NeurophysiologyFragment fragment = new NeurophysiologyFragment();
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
        titleBar.setTitle("Neurophysiology");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());
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
        recyclerNeurophysiology.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerNeurophysiology.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerNeurophysiology.setLayoutAnimation(animation);
        recyclerNeurophysiology.setAdapter(adaptNeuropysiology);
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
        if (object instanceof NeurophysiologyModel) {
            final NeurophysiologyModel model = (NeurophysiologyModel) object;

            if (model.getBalanceMessage() == null || model.getBalanceMessage().isEmpty()) {
                showReportAPI(model);
            } else {
                showSelfPayeePopup(model);
            }

        }

    }

    private void showReportAPI(final NeurophysiologyModel neurophysiologyModel) {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForWebResponseWithString(WebServiceConstants.METHOD_NEUROPHIOLOGY_SHOW_REPORT,
                        neurophysiologyModel.toString(), new WebServices.IRequestWebResponseWithStringDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<String> webResponse) {
//                                String fileName = neurophysiologyModel.getDetailReportID();
                                saveAndOpenFile(webResponse);
                            }

                            @Override
                            public void onError() {


                            }
                        }
                );
    }


    private void serviceCall() {
        SearchModel model = new SearchModel();
//        model.setMRNumber(WebServiceConstants.tempMRN_Neuro);
//        model.setMRNumber("074-18-91");
        model.setMRNumber(getCurrentUser().getMRNumber());
        if (isFromTimeline) {
            model.setVisitID(String.valueOf(patientVisitAdmissionID));
        } else {
            model.setVisitID(null);
        }
        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_NEUROPHIOLOGY,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<NeurophysiologyModel>>() {
                                }.getType();
                                ArrayList<NeurophysiologyModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                arrData.clear();
                                arrData.addAll(arrayList);

                                adaptNeuropysiology.notifyDataSetChanged();
                                if (arrData.size() > 0) {
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

    private void showEmptyView() {

        emptyView.setVisibility(View.VISIBLE);
    }

    private void showView() {
        bindView();
        emptyView.setVisibility(View.GONE);

    }

    private void showSelfPayeePopup(NeurophysiologyModel model) {
        GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();
        UIHelper.genericPopUp(getBaseActivity(), genericDialogFragment, "Alert", model.getBalanceMessage(),
                "OK", null, new GenericClickableInterface() {
                    @Override
                    public void click() {
                        genericDialogFragment.dismiss();
                    }
                }, null);
    }
}
