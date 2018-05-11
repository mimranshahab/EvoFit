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
import edu.aku.family_hifazat.adapters.recyleradapters.CardioAdapter;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.TitleBar;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.CardioModel;
import edu.aku.family_hifazat.models.SearchModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class CardiolopulmonaryFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.recylerView)
    RecyclerView recyclerCardio;
    Unbinder unbinder;

    @BindView(R.id.empty_view)
    AnyTextView emptyView;
    private ArrayList<CardioModel> arrCardioModelLists;
    private CardioAdapter adapterCardio;
    boolean isFromTimeline;
    int patientVisitAdmissionID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrCardioModelLists = new ArrayList<CardioModel>();
        adapterCardio = new CardioAdapter(getBaseActivity(), arrCardioModelLists, this);
    }

    public static CardiolopulmonaryFragment newInstance(boolean isFromTimeline, int patientVisitAdmissionID) {

        Bundle args = new Bundle();

        CardiolopulmonaryFragment fragment = new CardiolopulmonaryFragment();
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
        titleBar.setTitle("Cardiolopulmonary");
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

    private void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    private void showView() {
        bindView();
        emptyView.setVisibility(View.GONE);
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerCardio.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerCardio.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerCardio.setLayoutAnimation(animation);
        recyclerCardio.setAdapter(adapterCardio);
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

                showGraphAPI(adapterCardio.getItem(position));
                break;
            case R.id.RlReport:
                showReportAPI(adapterCardio.getItem(position));
                break;
        }

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

    private void showGraphAPI(final CardioModel cardiolopulmonary) {


        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForWebResponseWithString(WebServiceConstants.METHOD_CARDIO_SHOW_GRAPH,
                        cardiolopulmonary.toString(), new WebServices.IRequestWebResponseWithStringDataCallBack() {
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

    private void showReportAPI(final CardioModel cardiolopulmonary) {
//        String fileName = AppConstants.FILE_NAME + DateManager.getTime(DateManager.getCurrentMillis());
//
//        final File file = new File(DOC_PATH
//                + "/" + fileName);
//        if (FileManager.isFileExits(file.getPath())) {
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    FileManager.openFile(getContext(), file);
//                }
//            }, 300);
//        } else {

        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForWebResponseWithString(WebServiceConstants.METHOD_CARDIO_SHOW_REPORT,
                        cardiolopulmonary.toString(), new WebServices.IRequestWebResponseWithStringDataCallBack() {
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

    private void serviceCall() {
         SearchModel model = new SearchModel();
//        model.setMRNumber("200-47-97");
        model.setMRNumber(getCurrentUser().getMRNumber());
        if (isFromTimeline) {
            model.setVisitID(String.valueOf(patientVisitAdmissionID));
        } else {
            model.setVisitID(null);
        }
        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_CARDIO,
                        model.toString(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<CardioModel>>() {
                                }.getType();
                                ArrayList<CardioModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                arrCardioModelLists.clear();
                                arrCardioModelLists.addAll(arrayList);
                                adapterCardio.notifyDataSetChanged();

                                if (arrCardioModelLists.size() > 0) {
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


}
