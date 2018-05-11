package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.shawnlin.numberpicker.NumberPicker;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.StringHelper;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.PatientHealthSummaryModel;
import edu.aku.family_hifazat.models.Subhealthindicator;
import edu.aku.family_hifazat.models.receiving_model.AddUpdateModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

import static android.view.View.GONE;
import static edu.aku.family_hifazat.constatnts.AppConstants.ENTRY_SOURCE;

/**
 * Created by aqsa.sarwar on 4/26/2018.
 */

public class MeasurementFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.txtHeight)
    AnyTextView txtHeight;
    @BindView(R.id.txtHeightUnit)
    AnyTextView txtHeightUnit;
    @BindView(R.id.txtWeight)
    AnyTextView txtWeight;
    @BindView(R.id.txtWeightUnit)
    AnyTextView txtWeightUnit;
    @BindView(R.id.txtBSA)
    AnyTextView txtBSA;
    @BindView(R.id.txtBSAUnit)
    LinearLayout txtBSAUnit;
    @BindView(R.id.txtDttm)
    AnyTextView txtDttm;
    @BindView(R.id.cardMeasurement)
    CardView cardMeasurement;
    @BindView(R.id.btnRecordMenually)
    AnyTextView btnRecordMenually;
    @BindView(R.id.txtWeightDesc)
    AnyTextView txtWeightDesc;
    @BindView(R.id.pickerWeight)
    NumberPicker pickerWeight;
    @BindView(R.id.contWeight)
    LinearLayout contWeight;
    @BindView(R.id.txtHeightDesc)
    AnyTextView txtHeightDesc;
    @BindView(R.id.pickerHeight)
    NumberPicker pickerHeight;
    @BindView(R.id.contHeight)
    LinearLayout contHeight;
    @BindView(R.id.btnSave)
    AnyTextView btnSave;
    private PatientHealthSummaryModel modelWEIGHT;
    private PatientHealthSummaryModel modelHEIGHT;

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    public static MeasurementFragment newInstance(PatientHealthSummaryModel modelHEIGHT, PatientHealthSummaryModel modelWEIGHT) {

        Bundle args = new Bundle();

        MeasurementFragment fragment = new MeasurementFragment();
        fragment.modelHEIGHT = modelHEIGHT;
        fragment.modelWEIGHT = modelWEIGHT;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_measurements;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Measurement");
        titleBar.showHome(getBaseActivity());
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
    }

    boolean isHeightValueChanged = false;
    boolean isWeightValuieChanged = false;

    @Override
    public void setListeners() {
        pickerHeight.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                isHeightValueChanged = true;
                if (isWeightValuieChanged) {
                    if (!btnSave.isEnabled()) {
                        btnSave.setEnabled(true);
                        btnSave.setBackground(getResources().getDrawable(R.drawable.rounded_box_filled_btn_blue));
                        btnSave.setAlpha(1);
                    }
                }
            }
        });

        pickerWeight.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                isWeightValuieChanged = true;
                if (isHeightValueChanged) {
                    if (!btnSave.isEnabled()) {
                        btnSave.setEnabled(true);
                        btnSave.setBackground(getResources().getDrawable(R.drawable.rounded_box_filled_btn_blue));
                        btnSave.setAlpha(1);
                    }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (modelHEIGHT != null && modelWEIGHT != null) {

            // Height
            if (modelHEIGHT.getHealthindicatorlist() == null || modelHEIGHT.getHealthindicatorlist().isEmpty()) {
                txtDttm.setVisibility(GONE);
                txtHeightUnit.setVisibility(GONE);
            } else {
                txtDttm.setVisibility(View.VISIBLE);
                txtHeightUnit.setVisibility(View.VISIBLE);
                txtHeight.setText(modelHEIGHT.getHealthindicatorlist().get(0).getHealthindicatorvalue());
                pickerHeight.setValue(Integer.valueOf(modelHEIGHT.getHealthindicatorlist().get(0).getHealthindicatorvalue()));
                txtDttm.setText(modelHEIGHT.getHealthindicatorlist().get(0).getDatetimestr());
                txtHeightUnit.setText(modelHEIGHT.getHealthindicator().getUnit());

            }


            // Weight
            if (modelWEIGHT.getHealthindicatorlist() == null || modelWEIGHT.getHealthindicatorlist().isEmpty()) {
                txtDttm.setVisibility(GONE);
                txtWeightUnit.setVisibility(GONE);
            } else {
                txtDttm.setVisibility(View.VISIBLE);
                txtWeightUnit.setVisibility(View.VISIBLE);
                txtWeight.setText(modelWEIGHT.getHealthindicatorlist().get(0).getHealthindicatorvalue());
                pickerWeight.setValue(Integer.valueOf(modelWEIGHT.getHealthindicatorlist().get(0).getHealthindicatorvalue()));
                txtDttm.setText(modelWEIGHT.getHealthindicatorlist().get(0).getDatetimestr());
                txtWeightUnit.setText(modelWEIGHT.getHealthindicator().getUnit());

            }


            if (StringHelper.IsInt_ByJonas(modelHEIGHT.getHealthindicator().getMinvalue())) {
                pickerHeight.setMinValue(Integer.valueOf(modelHEIGHT.getHealthindicator().getMinvalue()));
            } else {
                UIHelper.showToast(getContext(), "Minimum value cannot be converted into Integer");
            }
            if (StringHelper.IsInt_ByJonas(modelHEIGHT.getHealthindicator().getMaxvalue())) {
                pickerHeight.setMaxValue(Integer.valueOf(modelHEIGHT.getHealthindicator().getMaxvalue()));
            } else {
                UIHelper.showToast(getContext(), "Maximum value cannot be converted into Integer");
            }


            if (StringHelper.IsInt_ByJonas(modelWEIGHT.getHealthindicator().getMinvalue())) {
                pickerWeight.setMinValue(Integer.valueOf(modelWEIGHT.getHealthindicator().getMinvalue()));
            } else {
                UIHelper.showToast(getContext(), "Minimum value cannot be converted into Integer");
            }
            if (StringHelper.IsInt_ByJonas(modelWEIGHT.getHealthindicator().getMaxvalue())) {
                pickerWeight.setMaxValue(Integer.valueOf(modelWEIGHT.getHealthindicator().getMaxvalue()));
            } else {
                UIHelper.showToast(getContext(), "Maximum value cannot be converted into Integer");
            }


            if (txtWeightUnit.getVisibility() == View.VISIBLE && txtHeightUnit.getVisibility() == View.VISIBLE) {


                double round = Math.round(Math.sqrt(Double.valueOf(modelHEIGHT.getHealthindicatorlist().get(0).getHealthindicatorvalue()) * Double.valueOf(modelWEIGHT.getHealthindicatorlist().get(0).getHealthindicatorvalue()) / 3600));
                txtBSAUnit.setVisibility(View.VISIBLE);
                txtBSA.setText(String.valueOf(round));
            } else {
                txtBSAUnit.setVisibility(GONE);
            }
        }

        pickerWeight.setWrapSelectorWheel(false);
        pickerHeight.setWrapSelectorWheel(false);
        txtHeightDesc.setText(modelHEIGHT.getHealthindicator().getHealthindicatordescription() + " (" + modelHEIGHT.getHealthindicator().getUnit() + ")");
        txtWeightDesc.setText(modelWEIGHT.getHealthindicator().getHealthindicatordescription() + " (" + modelWEIGHT.getHealthindicator().getUnit() + ")");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private String getSaveRecordData() {
        // Initialize variable
        PatientHealthSummaryModel heightModel = modelHEIGHT;
        PatientHealthSummaryModel weightModel = modelWEIGHT;


        // Generic data set
        // Generic data set
        heightModel.getSubhealthindicator().setSource(ENTRY_SOURCE);
        heightModel.getSubhealthindicator().setUserid(getCurrentUser().getCardNumber());
        heightModel.getSubhealthindicator().setTerminalid(AppConstants.DEVICE_OS_ANDROID);

        weightModel.getSubhealthindicator().setSource(ENTRY_SOURCE);
        weightModel.getSubhealthindicator().setUserid(getCurrentUser().getCardNumber());
        weightModel.getSubhealthindicator().setTerminalid(AppConstants.DEVICE_OS_ANDROID);


        //Height data set
        heightModel.setHealthindicatorlist(null);
        heightModel.getSubhealthindicator().setHealthindicatorvalue(String.valueOf(pickerHeight.getValue()));


        // Weight data set
        weightModel.setHealthindicatorlist(null);
        weightModel.getSubhealthindicator().setHealthindicatorvalue(String.valueOf(pickerWeight.getValue()));


        // adding data to array
        ArrayList<PatientHealthSummaryModel> arrayList = new ArrayList<PatientHealthSummaryModel>();
        arrayList.add(heightModel);
        arrayList.add(weightModel);

        Type type = new TypeToken<ArrayList<PatientHealthSummaryModel>>() {
        }.getType();

        return GsonFactory.getSimpleGson().toJson(arrayList, type);
    }


    private void saveRecord(String jsonData) {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_SAVE_HEALTH_SUMMARY_DETAILS,
                        jsonData,
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                AddUpdateModel addUpdateModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, AddUpdateModel.class);

                                UIHelper.showToast(getContext(), addUpdateModel.getMessage());
                                if (addUpdateModel.getStatus()) {
                                    getBaseActivity().popBackStack();
                                }
                            }

                            @Override
                            public void onError() {
                                UIHelper.showToast(getContext(), "Unable to save record.");
                            }
                        });
    }


    @OnClick({R.id.btnSave, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                saveRecord(getSaveRecordData());
                break;
            case R.id.btnCancel:
                getBaseActivity().onBackPressed();
                break;
        }
    }
}
