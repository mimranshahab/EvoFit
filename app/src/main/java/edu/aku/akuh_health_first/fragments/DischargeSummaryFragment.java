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
import edu.aku.akuh_health_first.adapters.recyleradapters.DischargeSummaryAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.managers.FileManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.DischargeSummaryModel;
import edu.aku.akuh_health_first.models.Neurophysiology;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

import static edu.aku.akuh_health_first.constatnts.AppConstants.DOC_PATH;

/**
 * Created by aqsa.sarwar on 1/30/2018.
 */

public class DischargeSummaryFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {
    @BindView(R.id.listNeurophysiology)
    RecyclerView recylerViewDischageSummary;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ArrayList<DischargeSummaryModel> arrDischargeSummary;
    private DischargeSummaryAdapter adapterDischargesummary;

    public static DischargeSummaryFragment newInstance() {

        Bundle args = new Bundle();

        DischargeSummaryFragment fragment = new DischargeSummaryFragment();
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
        serviceCall();
        bindView();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recylerViewDischageSummary.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recylerViewDischageSummary.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recylerViewDischageSummary.setLayoutAnimation(animation);
        recylerViewDischageSummary.setAdapter(adapterDischargesummary);
    }

    private void serviceCall() {
        UserDetailModel currentUser = sharedPreferenceManager.getCurrentUser();
        currentUser.setMRNumber(WebServiceConstants.tempMRN_LAB);

        new WebServices(getBaseActivity(), WebServiceConstants.temporaryToken, BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPI(WebServiceConstants.METHOD_PATIENT_DETAIL_DS,
                        currentUser.getMRNumberwithComma(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {

                            }

                            @Override
                            public void onError() {

                            }
                        });

        new WebServices(getBaseActivity(), WebServiceConstants.temporaryToken, BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_DISCHARGE_SUMMARY_LIST,
                        currentUser.getMRNumberwithComma(),
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
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_neurophysiology;
    }

    @Override
    public void setTitlebar(final TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Discharge Summary");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setCircleImageView( );
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
        new WebServices(getBaseActivity(), WebServiceConstants.temporaryToken, BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForWebResponseWithString(WebServiceConstants.METHOD_SHOW_REPORT_DS,
                        summaryModel.toString(), new WebServices.IRequestWebResponseWithStringDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<String> webResponse) {
                                String fileName =  FileManager.getFileNameFromPath(FileManager.getReplacedSlash(summaryModel.getSummaryPath()));

                                FileManager.writeResponseBodyToDisk(webResponse.result, fileName, AppConstants.getUserFolderPath(getContext()));

                                final File file = new File(DOC_PATH
                                        + "/" + fileName);

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        FileManager.openFile(getContext(), file);
                                    }
                                }, 300);


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

            String fileName = FileManager.getFileNameFromPath(FileManager.getReplacedSlash(summaryModel.getSummaryPath()));
            final File file = new File(DOC_PATH
                    + "/" + fileName);

            if (FileManager.isFileExits(file.getPath())) {

//                UIHelper.showToast(getContext(), "File already exist");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FileManager.openFile(getContext(), file);
                    }
                }, 300);
            } else {
                showReportAPI(summaryModel);
            }


        }

    }
}
