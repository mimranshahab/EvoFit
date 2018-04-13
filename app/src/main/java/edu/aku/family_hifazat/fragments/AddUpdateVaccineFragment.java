package edu.aku.family_hifazat.fragments;

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

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.TitleBar;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.managers.DateManager;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.ImmunizationModel;
import edu.aku.family_hifazat.models.IntWrapper;
import edu.aku.family_hifazat.models.SpinnerModel;
import edu.aku.family_hifazat.models.receiving_model.AddUpdateModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyEditTextView;
import edu.aku.family_hifazat.widget.AnyTextView;

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
    @BindView(R.id.txtNote)
    AnyTextView txtNote;
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
            txtVaccine.setClickable(false);
            txtVaccine.setFocusable(false);
        } else {
            getVaccineIdsService();
            getRouteIdsService();
        }
        txtNote.setText("The vaccinations entered would be recorded as being vaccinated from outside of AKUH and AKUH does not take any responsibility for these recorded vaccinations.");
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
        titleBar.showHome(getBaseActivity());
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
                getToken(),
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
                getToken(),
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
                DateManager.showDatePicker(getContext(), txtVaccinatedDate, null, true);
                break;


            case R.id.txtVaccinationPlanDate:
                if (isFromAdd) {
                    DateManager.showDatePicker(getContext(), txtVaccinationPlanDate, null, false);
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

        if (isFromAdd) {
            ImmunizationModel immunizationModel = new ImmunizationModel();
            immunizationModel.setMRN(getCurrentUser().getMRNumber());
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
            immunizationModel.setMRN(getCurrentUser().getMRNumber());
            immunizationModel.setActive("Y");
            immunizationModel.setLastFileTerminal("MOBILE");
            immunizationModel.setSource("MOBILE");
            immunizationModel.setVaccinationDate(txtVaccinatedDate.getStringTrimmed());
            immunizationModel.setHospitalLocation(txtVaccineLocation.getStringTrimmed());
            updateVaccine(immunizationModel.toString());
            Log.d(TAG, "onViewClicked: " + immunizationModel.toString());
        }

    }

    private void addVaccineService(String jsonData) {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_IMMUNIZATION_ADD_VACCINE,
                        jsonData,
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                AddUpdateModel addUpdateModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, AddUpdateModel.class);
                                if (addUpdateModel.getStatus()) {
                                    popBackStack();
                                }
                                UIHelper.showToast(getContext(), addUpdateModel.getMessage());
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }


    private void updateVaccine(String jsonData) {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_IMMUNIZATION_UPDATE_VACCINE,
                        jsonData,
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                AddUpdateModel addUpdateModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, AddUpdateModel.class);
                                if (addUpdateModel.getStatus()) {
                                    popBackStack();
                                }
                                UIHelper.showToast(getContext(), addUpdateModel.getMessage());
                            }

                            @Override
                            public void onError() {

                            }
                        });
    }


}