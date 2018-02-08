package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.ImmunizationModel;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by aqsa.sarwar on 2/8/2018.
 */

public class AddUpdateVaccineFragment extends BaseFragment {
    @BindView(R.id.txtVaccine)
    AnyTextView txtVaccine;
    @BindView(R.id.spVaccine)
    Spinner spVaccine;
    Unbinder unbinder;
    private boolean isFromAdd;
    private ArrayList<String> arrVaccine;
    private ArrayAdapter adapterVaccine;

    public static AddUpdateVaccineFragment newInstance() {

        Bundle args = new Bundle();

        AddUpdateVaccineFragment fragment = new AddUpdateVaccineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrVaccine = new ArrayList<String>();
        adapterVaccine = new ArrayAdapter(getBaseActivity(), android.R.layout.simple_list_item_1, arrVaccine);
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        serviceCall();
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
        spVaccine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    private void serviceCall() {
        // FIXME: 1/18/2018 Use live data in future
        UserDetailModel currentUser = sharedPreferenceManager.getCurrentUser();
        currentUser.setMRNumber(WebServiceConstants.tempMRN);

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

                                for (int i = 0; i < arrayList.size(); i++) {

                                    arrVaccine.add(arrayList.get(i).getDescription());

                                }
                                setSpinner(adapterVaccine, txtVaccine, spVaccine);
                            }

                            @Override
                            public void onError() {
                                UIHelper.showShortToastInCenter(getContext(), "failure");
                            }
                        });

    }

    @OnClick(R.id.txtVaccine)
    public void onViewClicked() {
        spVaccine.performClick();
    }
}
