package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

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
import edu.aku.family_hifazat.models.receiving_model.AddUpdateModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

import static android.view.View.GONE;
import static edu.aku.family_hifazat.constatnts.AppConstants.ENTRY_SOURCE;

/**
 * Created by aqsa.sarwar on 4/26/2018.
 */

public class BPFragment extends BaseFragment {

    @BindView(R.id.txtBPDDTM)
    AnyTextView txtBPDDTM;
    @BindView(R.id.cardBP)
    CardView cardBP;
    @BindView(R.id.btnRecordMenually)
    AnyTextView btnRecordMenually;
    @BindView(R.id.txtBPDiastolicDes)
    AnyTextView txtBPDiastolicDes;
    @BindView(R.id.pickerSystolic)
    NumberPicker pickerSystolic;
    @BindView(R.id.txtBPsystolicDes)
    AnyTextView txtBPsystolicDes;
    @BindView(R.id.pickerDiastolic)
    NumberPicker pickerDiastolic;
    @BindView(R.id.btnSave)
    AnyTextView btnSave;
    @BindView(R.id.txtBPUnit)
    AnyTextView txtBPUnit;
    @BindView(R.id.txtBPsystolic)
    AnyTextView txtBPsystolic;
    @BindView(R.id.txtBPdiastolic)
    AnyTextView txtBPdiastolic;
    Unbinder unbinder;
    private PatientHealthSummaryModel modelBPDIASTOLIC;
    private PatientHealthSummaryModel modelBPSYSTOLIC;

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    public static BPFragment newInstance(PatientHealthSummaryModel modelBPDIASTOLIC, PatientHealthSummaryModel modelBPSYSTOLIC) {

        Bundle args = new Bundle();

        BPFragment fragment = new BPFragment();

        fragment.modelBPDIASTOLIC = modelBPDIASTOLIC;
        fragment.modelBPSYSTOLIC = modelBPSYSTOLIC;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_blood_pressure;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Blood Pressure");
        titleBar.showHome(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showBackButton(getBaseActivity());
    }


    boolean isSystolicValueChanged = false;
    boolean isDiastolicValuieChanged = false;

    @Override
    public void setListeners() {

        pickerSystolic.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                isSystolicValueChanged = true;
                if (isDiastolicValuieChanged) {
                    if (!btnSave.isEnabled()) {
                        btnSave.setEnabled(true);
                        btnSave.setAlpha(1);
                    }
                }
            }
        });

        pickerDiastolic.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                isDiastolicValuieChanged = true;
                if (isSystolicValueChanged) {
                    if (!btnSave.isEnabled()) {
                        btnSave.setEnabled(true);
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

        if (modelBPSYSTOLIC != null && modelBPDIASTOLIC != null) {

            if (modelBPDIASTOLIC.getHealthindicatorlist() == null || modelBPDIASTOLIC.getHealthindicatorlist().isEmpty()) {
                txtBPDDTM.setVisibility(GONE);
            } else {
                txtBPDDTM.setVisibility(View.VISIBLE);

                txtBPdiastolic.setText(modelBPDIASTOLIC.getHealthindicatorlist().get(0).getHealthindicatorvalue());
                pickerDiastolic.setValue(Integer.valueOf(modelBPDIASTOLIC.getHealthindicatorlist().get(0).getHealthindicatorvalue()));
                txtBPDDTM.setText(modelBPDIASTOLIC.getHealthindicatorlist().get(0).getDatetimestr());
                txtBPUnit.setText(modelBPDIASTOLIC.getHealthindicator().getUnit());

            }


            if (modelBPSYSTOLIC.getHealthindicatorlist() == null || modelBPSYSTOLIC.getHealthindicatorlist().isEmpty()) {
                txtBPDDTM.setVisibility(GONE);
            } else {
                txtBPDDTM.setVisibility(View.VISIBLE);
                txtBPsystolic.setText(modelBPSYSTOLIC.getHealthindicatorlist().get(0).getHealthindicatorvalue());
                pickerSystolic.setValue(Integer.valueOf(modelBPSYSTOLIC.getHealthindicatorlist().get(0).getHealthindicatorvalue()));
                txtBPDDTM.setText(modelBPSYSTOLIC.getHealthindicatorlist().get(0).getDatetimestr());
                txtBPUnit.setText(modelBPSYSTOLIC.getHealthindicator().getUnit());

            }

            if (StringHelper.IsInt_ByJonas(modelBPDIASTOLIC.getHealthindicator().getMinvalue())) {
                pickerDiastolic.setMinValue(Integer.valueOf(modelBPDIASTOLIC.getHealthindicator().getMinvalue()));
            } else {
                UIHelper.showToast(getContext(), "Minimum value cannot be converted into Integer");
            }
            if (StringHelper.IsInt_ByJonas(modelBPDIASTOLIC.getHealthindicator().getMaxvalue())) {
                pickerDiastolic.setMaxValue(Integer.valueOf(modelBPDIASTOLIC.getHealthindicator().getMaxvalue()));
            } else {
                UIHelper.showToast(getContext(), "Maximum value cannot be converted into Integer");
            }


            if (StringHelper.IsInt_ByJonas(modelBPSYSTOLIC.getHealthindicator().getMinvalue())) {
                pickerSystolic.setMinValue(Integer.valueOf(modelBPSYSTOLIC.getHealthindicator().getMinvalue()));
            } else {
                UIHelper.showToast(getContext(), "Minimum value cannot be converted into Integer");
            }
            if (StringHelper.IsInt_ByJonas(modelBPSYSTOLIC.getHealthindicator().getMaxvalue())) {
                pickerSystolic.setMaxValue(Integer.valueOf(modelBPSYSTOLIC.getHealthindicator().getMaxvalue()));
            } else {
                UIHelper.showToast(getContext(), "Maximum value cannot be converted into Integer");
            }
        }

        pickerSystolic.setWrapSelectorWheel(false);
        pickerDiastolic.setWrapSelectorWheel(false);
        txtBPDiastolicDes.setText(modelBPDIASTOLIC.getHealthindicator().getHealthindicatordescription() + " (" + modelBPDIASTOLIC.getHealthindicator().getUnit() + ")");
        txtBPsystolicDes.setText(modelBPSYSTOLIC.getHealthindicator().getHealthindicatordescription() + " (" + modelBPSYSTOLIC.getHealthindicator().getUnit() + ")");


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private String getSaveRecordData() {
        // Initialize variable
        PatientHealthSummaryModel systolicModel = modelBPSYSTOLIC;
        PatientHealthSummaryModel diaStolicModel = modelBPDIASTOLIC;


        // Generic data set
        systolicModel.getSubhealthindicator().setSource(ENTRY_SOURCE);
        systolicModel.getSubhealthindicator().setUserid(getCurrentUser().getCardNumber());
        systolicModel.getSubhealthindicator().setTerminalid(AppConstants.DEVICE_OS_ANDROID);

        diaStolicModel.getSubhealthindicator().setSource(ENTRY_SOURCE);
        diaStolicModel.getSubhealthindicator().setUserid(getCurrentUser().getCardNumber());
        diaStolicModel.getSubhealthindicator().setTerminalid(AppConstants.DEVICE_OS_ANDROID);


        //Systolic data set
        systolicModel.setHealthindicatorlist(null);
        systolicModel.getSubhealthindicator().setHealthindicatorvalue(String.valueOf(pickerSystolic.getValue()));


        // Diastolic data set
        diaStolicModel.setHealthindicatorlist(null);
        diaStolicModel.getSubhealthindicator().setHealthindicatorvalue(String.valueOf(pickerDiastolic.getValue()));


        // adding data to array
        ArrayList<PatientHealthSummaryModel> arrayList = new ArrayList<PatientHealthSummaryModel>();
        arrayList.add(systolicModel);
        arrayList.add(diaStolicModel);

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
