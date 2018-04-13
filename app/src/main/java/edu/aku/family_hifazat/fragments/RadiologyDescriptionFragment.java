package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.TitleBar;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.RadiologyDetailModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;

/**
 * Created by hamza.ahmed on 2/8/2018.
 */

public class RadiologyDescriptionFragment extends BaseFragment {
    @BindView(R.id.txtExamName)
    AnyTextView txtExamName;
    @BindView(R.id.txtExamDate)
    AnyTextView txtExamDate;
    @BindView(R.id.txtDescription)
    AnyTextView txtDescription;
    Unbinder unbinder;
    @BindView(R.id.btnDownload)
    AnyTextView btnDownload;
    private String jsonData;


    public static RadiologyDescriptionFragment newInstance(String jsonData) {

        Bundle args = new Bundle();

        RadiologyDescriptionFragment fragment = new RadiologyDescriptionFragment();
        fragment.jsonData = jsonData;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_radiology_description;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Radiology Report");
        titleBar.showBackButton(getBaseActivity());
        titleBar.showHome(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (onCreated) {
            return;
        }
        apiService();

    }

    private void apiService() {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_GET_RADIOLOGY_GET_EXAM_DETAIL,
                        jsonData,
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                RadiologyDetailModel radiologyDetailModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, RadiologyDetailModel.class);
                                txtExamName.setText(radiologyDetailModel.getExam());
                                txtExamDate.setText(radiologyDetailModel.getExamdate());
                                txtDescription.setText(Html.fromHtml(radiologyDetailModel.getReporttext()), TextView.BufferType.SPANNABLE);
                            }

                            @Override
                            public void onError() {
                                txtExamName.setText(null);
                                txtExamDate.setText(null);
                            }
                        });
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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

    @OnClick(R.id.btnDownload)
    public void onViewClicked() {
        showReportAPI();
//        showNextBuildToast();
    }


    private void showReportAPI() {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForWebResponseWithString(WebServiceConstants.METHOD_GET_RADIOLOGY_GET_REPORT,
                        jsonData, new WebServices.IRequestWebResponseWithStringDataCallBack() {
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
}
