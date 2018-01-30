package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.JsonObject;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

/**
 * Created by aqsa.sarwar on 1/30/2018.
 */

public class DischargeSummaryFragment extends BaseFragment {
    public static DischargeSummaryFragment newInstance() {

        Bundle args = new Bundle();

        DischargeSummaryFragment fragment = new DischargeSummaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        serviceCall();
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
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_discharge_summary;
    }

    @Override
    public void setTitlebar(final TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Discharge Summary");
        titleBar.showBackButton(getBaseActivity());
        titleBar.setCircleImageView(R.drawable.user_image, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleBar.showAndHideDropDown();
            }
        });
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
}
