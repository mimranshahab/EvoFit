package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnCalendarUpdate;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.DateManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.AddNewMedicine;
import edu.aku.akuh_health_first.models.FrequencyIDsModel;
import edu.aku.akuh_health_first.models.ImmunizationModel;
import edu.aku.akuh_health_first.models.IntWrapper;
import edu.aku.akuh_health_first.models.MedicationProfileModel;
import edu.aku.akuh_health_first.models.SpinnerModel;
import edu.aku.akuh_health_first.models.receiving_model.AddUpdateModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.widget.AnyEditTextView;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 * Created by aqsa.sarwar on 2/8/2018.
 */

public class AddUpdateMedicationFragment extends BaseFragment {

    @BindView(R.id.edtMedicineName)
    AnyEditTextView edtMedicineName;
    @BindView(R.id.txtFrequencyDesc)
    AnyTextView txtFrequencyDesc;
    @BindView(R.id.txtRouteId)
    AnyTextView txtRouteId;
    @BindView(R.id.txtStartDateTime)
    AnyTextView txtStartDateTime;
    @BindView(R.id.txtStopDateTime)
    AnyTextView txtStopDateTime;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.chkLifeTimeMedication)
    CheckBox chkLifeTimeMedication;
    Unbinder unbinder;
    private boolean isFromAdd;
    private ArrayList<SpinnerModel> arrFrequency;
    private ArrayList<SpinnerModel> arrRouteIDs;
    private ArrayAdapter adapterFrequency;
    private ArrayAdapter adapterRoute;
    private MedicationProfileModel medicationProfileModel;

    HashMap<String, String> frequencyIDandDescriptions = new HashMap<String, String>();
    HashMap<String, String> routeIDandDescriptions = new HashMap<String, String>();

    OnCalendarUpdate onCalendarUpdateStartDate;
    OnCalendarUpdate onCalendarUpdateStopDate;

    long startDateInMillis;
    long stopDateInMillis;

    private IntWrapper freqPosition = new IntWrapper(-1);
    private IntWrapper routePosition = new IntWrapper(-1);

    public static AddUpdateMedicationFragment newInstance(boolean isFromAdd, MedicationProfileModel medicationProfileModel) {

        Bundle args = new Bundle();

        AddUpdateMedicationFragment fragment = new AddUpdateMedicationFragment();
        fragment.isFromAdd = isFromAdd;
        fragment.medicationProfileModel = medicationProfileModel;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrFrequency = new ArrayList<>();
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
//            txtFrequencyDesc.setText(immunizationModel.getDescription());
//            txtStartDateTime.setText(immunizationModel.getVaccinePlanDate());
//            txtRouteId.setText(immunizationModel.getRouteDescription()
//            );
//            txtRouteId.setFocusable(false);
//            txtRouteId.setClickable(false);
        } else {
            getFrequencyIds();
            getRouteIdsService();
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_medication_add_update;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        if (isFromAdd) {
            titleBar.setTitle("Add New Medicine");
        } else {
            titleBar.setTitle("Update Medicine");
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
        onCalendarUpdateStartDate = new OnCalendarUpdate() {
            @Override
            public void onCalendarUpdate(Calendar calendar) {
                startDateInMillis = calendar.getTimeInMillis();
            }
        };

        onCalendarUpdateStopDate = new OnCalendarUpdate() {
            @Override
            public void onCalendarUpdate(Calendar calendar) {
                stopDateInMillis = calendar.getTimeInMillis();
            }
        };

        chkLifeTimeMedication.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    txtStartDateTime.setEnabled(false);
                    txtStopDateTime.setEnabled(false);
                } else {
                    txtStartDateTime.setEnabled(true);
                    txtStopDateTime.setEnabled(true);
                }
            }
        });

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

    private void getFrequencyIds() {

        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_FREQUENCY_IDS,
                        "",
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<FrequencyIDsModel>>() {
                                }.getType();
                                ArrayList<FrequencyIDsModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                for (FrequencyIDsModel model : arrayList) {
                                    frequencyIDandDescriptions.put(model.getRxmedfrequencydescription(), model.getRxmedfrequencyid());
                                    arrFrequency.add(new SpinnerModel(model.getRxmedfrequencydescription()));
                                }

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


    @OnClick({R.id.txtRouteId, R.id.txtFrequencyDesc, R.id.txtStopDateTime, R.id.btnSave, R.id.txtStartDateTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtFrequencyDesc:
                UIHelper.showSpinnerDialog(this, arrFrequency, "Select Frequency", txtFrequencyDesc, null, freqPosition);
                break;

            case R.id.txtRouteId:
                UIHelper.showSpinnerDialog(this, arrRouteIDs, "Select Route", txtRouteId, null, routePosition);

                break;
            case R.id.txtStartDateTime:
                DateManager.showDateTimePicker(getContext(), txtStartDateTime, onCalendarUpdateStartDate, false);
                break;


            case R.id.txtStopDateTime:
                DateManager.showDateTimePicker(getContext(), txtStopDateTime, onCalendarUpdateStopDate, false);
                break;

            case R.id.btnSave:
                saveButtonPressed();
                break;
        }
    }

    private void saveButtonPressed() {
        if (edtMedicineName.testValidity()) {

            if (txtFrequencyDesc.getText().toString().isEmpty()) {
                UIHelper.showToast(getContext(), "Please Select Frequency");
            } else if (txtRouteId.getText().toString().isEmpty()) {
                UIHelper.showToast(getContext(), "Please Select Route");
            } else if (chkLifeTimeMedication.isChecked()) {
                if (txtStartDateTime.getText().toString().isEmpty()) {
                    UIHelper.showToast(getContext(), "Please Select Start Time");
                } else {
                    addMedicineData(true);
                }
            } else {
                dateValidations();
            }
        }
    }

    private void dateValidations() {
        if (txtStartDateTime.getText().toString().isEmpty()) {
             UIHelper.showToast(getContext(), "Please Select Start Date");
            return;
        }
        if (txtStopDateTime.getText().toString().isEmpty()) {
            UIHelper.showToast(getContext(), "Please Select Stop Date");
            return;
        }

        if (startDateInMillis < stopDateInMillis) {
            addMedicineData(false);
        } else {
            UIHelper.showToast(getContext(), "Start Date Time should be smaller than Stop Date Time.");
        }
    }

    private void addMedicineData(boolean isLifeTime) {
        AddNewMedicine addNewMedicine = new AddNewMedicine();
        addNewMedicine.setLastFileterminal("MOBILE");
        addNewMedicine.setMrnumber(getCurrentUser().getMRNumber());
        addNewMedicine.setRxmedfrequencyid(frequencyIDandDescriptions.get(txtFrequencyDesc.getStringTrimmed()));
        addNewMedicine.setRxmedmedication(edtMedicineName.getStringTrimmed());
        addNewMedicine.setRxmedroute(routeIDandDescriptions.get(txtRouteId.getStringTrimmed()));
        addNewMedicine.setLifeTimeMedicine(isLifeTime);
        addNewMedicine.setRxmedstartdatetime(txtStartDateTime.getStringTrimmed());

        if (isLifeTime) {
            addNewMedicine.setRxmedstopdatetime("");
        } else {
            addNewMedicine.setRxmedstartdatetime(txtStartDateTime.getStringTrimmed());
            addNewMedicine.setRxmedstopdatetime(txtStopDateTime.getStringTrimmed());
        }
        addMedicineService(addNewMedicine.toString());
    }


    private void addMedicineService(String jsonData) {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_ADD_MEDICINE,
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
