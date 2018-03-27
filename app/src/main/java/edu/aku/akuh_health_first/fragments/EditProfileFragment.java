package edu.aku.akuh_health_first.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.constatnts.Events;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.receiving_model.CardMemberDetail;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.widget.AnyEditTextView;
import edu.aku.akuh_health_first.widget.AnyTextView;


/**
 * Created by aqsa.sarwar on 1/19/2018.
 */

public class EditProfileFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.edMobileNumber)
    AnyEditTextView edMobileNumber;
    @BindView(R.id.edtLandlineNumber)
    AnyEditTextView edtLandlineNumber;
    @BindView(R.id.edtCurrentAddress)
    AnyEditTextView edtCurrentAddress;
    @BindView(R.id.edtCurrentCity)
    AnyEditTextView edtCurrentCity;
    @BindView(R.id.txtCurrentCountry)
    AnyTextView txtCurrentCountry;
    @BindView(R.id.edtPermanentAddress)
    AnyEditTextView edtPermanentAddress;
    @BindView(R.id.edtPermanentCity)
    AnyEditTextView edtPermanentCity;
    @BindView(R.id.txtPermanentCountry)
    AnyTextView txtPermanentCountry;
    @BindView(R.id.btnUpdate)
    AnyTextView btnUpdate;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_edit_profile;
    }

    public static EditProfileFragment newInstance() {

        Bundle args = new Bundle();

        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    private void setData() {
        UserDetailModel user = sharedPreferenceManager.getCurrentUser();

        edMobileNumber.setText(user.getCellPhoneNumber());
        edtLandlineNumber.setText(user.getLandlineNumber());

        edtCurrentAddress.setText(user.getCurrentAddress());
        edtCurrentCity.setText(user.getCurrentCity());
        txtCurrentCountry.setText(user.getCurrentCountryDescription());

        edtPermanentCity.setText(user.getPermanentCity());
        edtPermanentAddress.setText(user.getPermanentAddress());
        txtPermanentCountry.setText(user.getPermanentCountryDescription());


    }

    private void webServiceCall(final UserDetailModel user) {

        user.setCellPhoneNumber(edMobileNumber.getStringTrimmed());
        user.setLandlineNumber(edtLandlineNumber.getStringTrimmed());

        user.setCurrentAddress(edtCurrentAddress.getStringTrimmed());
        user.setCurrentCity(edtCurrentCity.getStringTrimmed());
        user.setCurrentCountryID(txtCurrentCountry.getStringTrimmed());

        user.setPermanentAddress(edtPermanentAddress.getStringTrimmed());
        user.setPermanentCity(edtPermanentCity.getStringTrimmed());
        user.setPermanentCountryID(txtPermanentCountry.getStringTrimmed());
        user.setLastFileDateTime(null);
        if (edMobileNumber.getStringTrimmed() != "" &&
                edtCurrentAddress.getStringTrimmed() != "" &&
                edtCurrentCity.getStringTrimmed() != "" &&
                txtCurrentCountry.getStringTrimmed() != "" &&
                edtPermanentAddress.getStringTrimmed() != "" &&
                txtPermanentCountry.getStringTrimmed() != "" &&
                edtPermanentCity.getStringTrimmed() != "") {
            new WebServices(getBaseActivity(),
                    WebServiceConstants.temporaryToken,
                    BaseURLTypes.AHFA_BASE_URL)
                    .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_UPDATE_PROFILE,
                            user.toString(),
                            new WebServices.IRequestJsonDataCallBack() {
                                @Override
                                public void requestDataResponse(WebResponse<JsonObject> webResponse) {
//                                getBaseActivity().openActivity(HomeActivity.class);
                                    UIHelper.showToast(getContext(), webResponse.message);

                                    getCardDetailService(user);
                                }

                                @Override
                                public void onError() {

                                }
                            });
        } else {
            UIHelper.showToast(getBaseActivity(), "Fields cannot be empty");
        }
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Edit profile");
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


    @OnClick(R.id.btnUpdate)
    public void onViewClicked() {
        webServiceCall(sharedPreferenceManager.getCurrentUser());
    }


    private void getCardDetailService(final UserDetailModel user) {
        CardMemberDetail cardMemberDetail = new CardMemberDetail(WebServiceConstants.tempCardNumber);

        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL).webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_CARD_MEMBER,
                cardMemberDetail.toString(),
                new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        CardMemberDetail cardMemberDetail = GsonFactory.getSimpleGson().fromJson(webResponse.result, CardMemberDetail.class);
                        notifyToAll(Events.ON_CARD_MODEL_UPDATE, cardMemberDetail);
                        notifyToAll(Events.ON_EDIT_PROFILE_INFO, user);
                        popBackStack();

                    }

                    @Override
                    public void onError() {
                        UIHelper.showShortToastInCenter(getContext(), "Something went wrong!");
                    }
                });

    }
}
