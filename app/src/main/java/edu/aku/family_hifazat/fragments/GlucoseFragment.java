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
import java.util.Formatter;

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
import static android.view.View.VISIBLE;
import static edu.aku.family_hifazat.constatnts.AppConstants.ENTRY_SOURCE;

/**
 * Created by aqsa.sarwar on 4/26/2018.
 */

public class GlucoseFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.txtFastingGlucose)
    AnyTextView txtFastingGlucose;
    @BindView(R.id.txtFastingGlucoseDate)
    AnyTextView txtFastingGlucoseDate;
    @BindView(R.id.txtRandomGlucose)
    AnyTextView txtRandomGlucose;
    @BindView(R.id.txtRandomGlucoseDate)
    AnyTextView txtRandomGlucoseDate;
    @BindView(R.id.txtSugarDDTM)
    AnyTextView txtSugarDDTM;
    @BindView(R.id.txtGlucoUnit)
    AnyTextView txtGlucoUnit;
    @BindView(R.id.cardBloodGlucose)
    CardView cardBloodGlucose;
    @BindView(R.id.btnRecordMenually)
    AnyTextView btnRecordMenually;
    @BindView(R.id.txtGLUR)
    AnyTextView txtDescription;
    @BindView(R.id.number_picker)
    NumberPicker numberPicker;
    @BindView(R.id.txtFasting)
    AnyTextView txtFasting;
    @BindView(R.id.txtRandom)
    AnyTextView txtRandom;
    @BindView(R.id.btnSave)
    AnyTextView btnSave;
    @BindView(R.id.txtFastingGlucoseStatus)
    AnyTextView txtFastingGlucoseStatus;
    @BindView(R.id.txtRandomGlucoseStatus)
    AnyTextView txtRandomGlucoseStatus;
    @BindView(R.id.txtGlucoUnit2)
    AnyTextView txtGlucoUnit2;
    @BindView(R.id.btnCancel)
    AnyTextView btnCancel;

    private PatientHealthSummaryModel modelGLUF;
    private PatientHealthSummaryModel modelGLUR;
    private boolean isGlucoseFasting = true;

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    public static GlucoseFragment newInstance(PatientHealthSummaryModel modelGLUF, PatientHealthSummaryModel modelGLUR) {

        Bundle args = new Bundle();

        GlucoseFragment fragment = new GlucoseFragment();
        fragment.modelGLUF = modelGLUF;
        fragment.modelGLUR = modelGLUR;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_blood_glucose;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(VISIBLE);
        titleBar.setTitle("Blood Glucose");
        titleBar.showHome(getBaseActivity());
        titleBar.showBackButton(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
    }

    @Override
    public void setListeners() {

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (!btnSave.isEnabled()) {
                    btnSave.setEnabled(true);
                    btnSave.setBackground(getResources().getDrawable(R.drawable.rounded_box_filled_btn_blue));

                    btnSave.setAlpha(1);
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

        if (modelGLUF != null && modelGLUR != null) {

            if (modelGLUR.getHealthindicatorlist() == null || modelGLUR.getHealthindicatorlist().isEmpty()) {
                txtRandomGlucoseDate.setVisibility(GONE);
                txtRandomGlucoseStatus.setVisibility(GONE);
            } else {
                txtRandomGlucoseDate.setVisibility(VISIBLE);
                txtRandomGlucoseStatus.setVisibility(VISIBLE);


                txtRandomGlucose.setText(modelGLUR.getHealthindicatorlist().get(0).getHealthindicatorvalue());
                txtRandomGlucoseDate.setText(modelGLUR.getHealthindicatorlist().get(0).getDatetimestr());
                txtRandomGlucoseStatus.setText(modelGLUR.getHealthindicatorlist().get(0).getSource());
                txtGlucoUnit2.setText(modelGLUR.getHealthindicator().getUnit());
            }


            if (modelGLUF.getHealthindicatorlist() == null || modelGLUF.getHealthindicatorlist().isEmpty()) {
                txtFastingGlucoseDate.setVisibility(GONE);
                txtFastingGlucoseStatus.setVisibility(GONE);
            } else {
                txtFastingGlucoseDate.setVisibility(VISIBLE);
                txtFastingGlucoseStatus.setVisibility(VISIBLE);
                txtFastingGlucose.setText(modelGLUF.getHealthindicatorlist().get(0).getHealthindicatorvalue());
                numberPicker.setValue(Integer.valueOf(modelGLUF.getHealthindicatorlist().get(0).getHealthindicatorvalue()));

                txtFastingGlucoseDate.setText(modelGLUF.getHealthindicatorlist().get(0).getDatetimestr());
                txtFastingGlucoseStatus.setText(modelGLUF.getHealthindicatorlist().get(0).getSource());
                txtGlucoUnit.setText(modelGLUF.getHealthindicator().getUnit());

            }

            if (StringHelper.IsInt_ByJonas(modelGLUF.getHealthindicator().getMinvalue())) {
                numberPicker.setMinValue(Integer.valueOf(modelGLUF.getHealthindicator().getMinvalue()));
            } else {
                UIHelper.showToast(getContext(), "Minimum value cannot be converted into Integer");
            }
            if (StringHelper.IsInt_ByJonas(modelGLUF.getHealthindicator().getMaxvalue())) {
                numberPicker.setMaxValue(Integer.valueOf(modelGLUF.getHealthindicator().getMaxvalue()));
            } else {
                UIHelper.showToast(getContext(), "Maximum value cannot be converted into Integer");
            }

            txtDescription.setText(modelGLUF.getHealthindicator().getHealthindicatordescription() + " (" + modelGLUF.getHealthindicator().getUnit() + ")");
            numberPicker.setWrapSelectorWheel(false);


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtFasting, R.id.txtRandom, R.id.btnSave, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtFasting:

                if (!isGlucoseFasting) {
                    isGlucoseFasting = true;
                    txtFasting.setBackgroundResource(R.drawable.gluco_button_left_selected);
                    txtFasting.setTextColor(getResources().getColor(R.color.c_white));
                    txtRandom.setBackgroundResource(R.drawable.gluco_button_right_unselected);
                    txtRandom.setTextColor(getResources().getColor(R.color.base_reddish_light));
//                    txtDescription.setText("Glucose Fasting (mg/dl)");
                    txtDescription.setText(modelGLUF.getHealthindicator().getHealthindicatordescription() + " (" + modelGLUF.getHealthindicator().getUnit() + ")");
                }


                break;
            case R.id.txtRandom:

                if (isGlucoseFasting) {
                    isGlucoseFasting = false;
                    txtRandom.setBackgroundResource(R.drawable.gluco_button_right_selected);
                    txtRandom.setTextColor(getResources().getColor(R.color.c_white));
                    txtFasting.setBackgroundResource(R.drawable.gluco_button_left_unselected);
                    txtFasting.setTextColor(getResources().getColor(R.color.base_reddish_light));
//                    txtDescription.setText("Glucose Random (mg/dl)");
                    txtDescription.setText(modelGLUR.getHealthindicator().getHealthindicatordescription() + " (" + modelGLUR.getHealthindicator().getUnit() + ")");
                }

                break;
            case R.id.btnSave:
                String toJson = getSaveRecordData();
                saveRecord(toJson);

                break;

            case R.id.btnCancel:
                getBaseActivity().onBackPressed();
                break;
        }
    }

    private String getSaveRecordData() {
        PatientHealthSummaryModel patientHealthSummaryModel;
        if (isGlucoseFasting) {
            patientHealthSummaryModel = modelGLUF;
        } else {
            patientHealthSummaryModel = modelGLUR;
        }
        patientHealthSummaryModel.setHealthindicatorlist(null);
        patientHealthSummaryModel.getSubhealthindicator().setHealthindicatorvalue(String.valueOf(numberPicker.getValue()));
        patientHealthSummaryModel.getSubhealthindicator().setSource(ENTRY_SOURCE);
        patientHealthSummaryModel.getSubhealthindicator().setUserid(getCurrentUser().getCardNumber());
        patientHealthSummaryModel.getSubhealthindicator().setTerminalid(AppConstants.DEVICE_OS_ANDROID);

        ArrayList<PatientHealthSummaryModel> arrayList = new ArrayList<PatientHealthSummaryModel>();
        arrayList.add(patientHealthSummaryModel);

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

}
