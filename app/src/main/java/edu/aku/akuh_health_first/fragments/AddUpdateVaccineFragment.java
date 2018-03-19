package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.DateManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.ImmunizationModel;
import edu.aku.akuh_health_first.models.IntWrapper;
import edu.aku.akuh_health_first.models.SpinnerModel;
import edu.aku.akuh_health_first.models.receiving_model.AddUpdateVaccineModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.views.AnyEditTextView;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by hamza.ahmed on 2/8/2018.
 */

public class AddUpdateVaccineFragment extends BaseFragment {
    @BindView(R.id.txtVaccinatedDate)
    AnyTextView txtVaccinatedDate;
    @BindView(R.id.txtVaccinationPlanDate)
    AnyTextView txtVaccinationPlanDate;
    @BindView(R.id.txtVaccine)
    AnyTextView txtVaccine;
    @BindView(R.id.txtRouteId)
    AnyTextView txtRouteId;
    @BindView(R.id.txtVaccineLocation)
    AnyEditTextView txtVaccineLocation;
    @BindView(R.id.btnSave)
    Button btnSave;

    Unbinder unbinder;

    private boolean isFromAdd;
    private ArrayList<SpinnerModel> arrVaccine;
    private ArrayList<SpinnerModel> arrRouteIDs;
    private ArrayAdapter adapterVaccine;
    private ArrayAdapter adapterRoute;
    private ImmunizationModel immunizationModel;

    HashMap<String, String> vaccineIDandDescriptions = new HashMap<String, String>();
    HashMap<String, String> routeIDandDescriptions = new HashMap<String, String>();
    private ArrayList<SpinnerModel> arrUsedVaccineDes;

    private IntWrapper vaccinePosition = new IntWrapper(-1);
    private IntWrapper routePosition = new IntWrapper(-1);

    public static AddUpdateVaccineFragment newInstance(boolean isFromAdd, ImmunizationModel immunizationModel, ArrayList<SpinnerModel> arrUsedVaccineDes) {

        Bundle args = new Bundle();

        AddUpdateVaccineFragment fragment = new AddUpdateVaccineFragment();
        fragment.isFromAdd = isFromAdd;
        fragment.immunizationModel = immunizationModel;
        fragment.arrUsedVaccineDes = arrUsedVaccineDes;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrVaccine = new ArrayList<>();
        arrRouteIDs = new ArrayList<>();
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!isFromAdd) {
            txtVaccine.setText(immunizationModel.getDescription());
            txtVaccinationPlanDate.setText(immunizationModel.getVaccinePlanDate());
            txtVaccineLocation.setText(immunizationModel.getHospitalLocation());
            txtRouteId.setText(immunizationModel.getRouteDescription()
            );
//            txtVaccineLocation.setFocusable(false);
//            txtVaccineLocation.setClickable(false);
            txtRouteId.setFocusable(false);
            txtRouteId.setClickable(false);
        } else {
            getVaccineIdsService();
            getRouteIdsService();
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_immunization_vaccine_add_update;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        if (isFromAdd) {
            titleBar.setTitle("Add Vaccine");
        } else {
            titleBar.setTitle("Update Vaccine");
        }

        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getVaccineIdsService() {

        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_IMMUNIZATION_VACCINE_IDS,
                        "",
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<ImmunizationModel>>() {
                                }.getType();
                                ArrayList<ImmunizationModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                for (ImmunizationModel model : arrayList) {
                                    vaccineIDandDescriptions.put(model.getDescription(), model.getVaccineID());
                                    arrVaccine.add(new SpinnerModel(model.getDescription()));
                                }

                                arrVaccine.removeAll(arrUsedVaccineDes);
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }


    private void getRouteIdsService() {

        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_IMMUNIZATION_ROUTE_IDS,
                        "",
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<ImmunizationModel>>() {
                                }.getType();
                                ArrayList<ImmunizationModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);


                                for (ImmunizationModel model : arrayList) {
                                    routeIDandDescriptions.put(model.getDescription(), model.getRouteID());
                                    arrRouteIDs.add(new SpinnerModel(model.getDescription()));
                                }

                            }

                            @Override
                            public void onError() {

                            }
                        });

    }


    @OnClick({R.id.txtRouteId, R.id.txtVaccine, R.id.txtVaccinatedDate, R.id.btnSave, R.id.txtVaccinationPlanDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtVaccine:
                UIHelper.showSpinnerDialog(this, arrVaccine, "Select Frequency", txtVaccine, null, vaccinePosition);

                break;

            case R.id.txtRouteId:
                UIHelper.showSpinnerDialog(this, arrRouteIDs, "Select Route", txtRouteId, null, routePosition);

                break;
            case R.id.txtVaccinatedDate:
                DateManager.showDatePicker(getContext(), txtVaccinatedDate, null);
                break;


            case R.id.txtVaccinationPlanDate:
                if (isFromAdd) {
                    DateManager.showDatePicker(getContext(), txtVaccinationPlanDate, null);
                } else {
                    UIHelper.showToast(getContext(), "You can't update previous plan date");
                }

                break;

            case R.id.btnSave:
                saveButtonPressed();
                break;
        }
    }

    private void saveButtonPressed() {
        if (txtVaccine.getStringTrimmed().isEmpty()) {
            UIHelper.showToast(getContext(), "Please Select Vaccine");
            return;
        }

        if (txtRouteId.getStringTrimmed().isEmpty()) {
            UIHelper.showToast(getContext(), "Please Select Route");
            return;
        }

        if (txtVaccinationPlanDate.getText().toString().isEmpty()) {
            UIHelper.showToast(getContext(), "Please select Vaccination Schedule Date");
            return;
        }

        if (txtVaccineLocation.testValidity()) {


            if (isFromAdd) {
                ImmunizationModel immunizationModel = new ImmunizationModel();
                immunizationModel.setMRN(WebServiceConstants.tempMRN_immunization);
                immunizationModel.setScheduleID("U");
                immunizationModel.setVisitID("U");
                immunizationModel.setActive("Y");
//                immunizationModel.setRouteID("PO");
                immunizationModel.setLastFileTerminal("MOBILE");
                immunizationModel.setSource("MOBILE");

                immunizationModel.setVaccineID(vaccineIDandDescriptions.get(txtVaccine.getText().toString()));
                immunizationModel.setRouteID(routeIDandDescriptions.get(txtRouteId.getText().toString()));


                immunizationModel.setHospitalLocation(txtVaccineLocation.getStringTrimmed());
                immunizationModel.setVaccinePlanDate(txtVaccinationPlanDate.getStringTrimmed());
                immunizationModel.setVaccinationDate(txtVaccinatedDate.getStringTrimmed());


                addVaccineService(immunizationModel.toString());
                Log.d(TAG, "onViewClicked: " + immunizationModel.toString());

            } else {
                immunizationModel.setMRN(WebServiceConstants.tempMRN_immunization);
                immunizationModel.setActive("Y");
                immunizationModel.setLastFileTerminal("MOBILE");
                immunizationModel.setSource("MOBILE");
                immunizationModel.setVaccinationDate(txtVaccinatedDate.getStringTrimmed());
                immunizationModel.setHospitalLocation(txtVaccineLocation.getStringTrimmed());
                updateVaccine(immunizationModel.toString());
                Log.d(TAG, "onViewClicked: " + immunizationModel.toString());
            }

        }
    }

    private void addVaccineService(String jsonData) {
        new WebServices(getBaseActivity(), WebServiceConstants.temporaryToken, BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_IMMUNIZATION_ADD_VACCINE,
                        jsonData,
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                AddUpdateVaccineModel addUpdateVaccineModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, AddUpdateVaccineModel.class);
                                if (addUpdateVaccineModel.getStatus()) {
                                    popBackStack();
                                }
                                UIHelper.showToast(getContext(), addUpdateVaccineModel.getMessage());
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }


    private void updateVaccine(String jsonData) {
        new WebServices(getBaseActivity(), WebServiceConstants.temporaryToken, BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_IMMUNIZATION_UPDATE_VACCINE,
                        jsonData,
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                AddUpdateVaccineModel addUpdateVaccineModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, AddUpdateVaccineModel.class);
                                if (addUpdateVaccineModel.getStatus()) {
                                    popBackStack();
                                }
                                UIHelper.showToast(getContext(), addUpdateVaccineModel.getMessage());
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }


}
