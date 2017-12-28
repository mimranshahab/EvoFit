package edu.aku.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import edu.aku.R;
import edu.aku.callbacks.OnActivityResultInterface;
import edu.aku.constatnts.WebServiceConstants;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.GooglePlaceHelper;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;
import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.RegisterOptionsModel;
import edu.aku.models.extramodels.AddressModel;
import edu.aku.models.wrappers.WebResponse;
import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 12-May-17.
 */

public class AddAdressFragment extends BaseFragment implements GooglePlaceHelper.GooglePlaceDataInterface, OnActivityResultInterface {


    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.btnDone)
    ImageButton btnDone;
    @BindView(R.id.txtSelectLocationFromGoogle)
    AnyTextView txtSelectLocationFromGoogle;
    @BindView(R.id.edtCountryName)
    FormEditText edtCountryName;
    @BindView(R.id.edtCityName)
    FormEditText edtCityName;
    @BindView(R.id.edtStreetName)
    FormEditText edtStreetName;
    @BindView(R.id.edtBuildingName)
    FormEditText edtBuildingName;
    @BindView(R.id.edtFloor)
    FormEditText edtFloor;
    @BindView(R.id.edtApartment)
    FormEditText edtApartment;
    @BindView(R.id.edtNearesLandmark)
    FormEditText edtNearesLandmark;
    @BindView(R.id.txtLocationType)
    AnyTextView txtLocationType;
    @BindView(R.id.spLocation)
    Spinner spLocation;
    private Unbinder unbinder;

    private ArrayList arrCity;
    private ArrayList arrLocation;

    //    private ArrayAdapter adaptCity;
    private ArrayAdapter adaptLocation;

    private AddressModel addressModelFromEdit;

    private boolean isFromEditAddress = false;
    private boolean isGoogleLocationSelected = false;
    //    @BindView(R.id.spCity)
//    Spinner spCity;


    private GooglePlaceHelper googlePlaceHelper;
    private OnAddressSelected onAddressSelected;

    private double longitude;
    private double latitude;
    private String locationName;
    private Call<WebResponse<AddressModel>> addAddressCall;
    private Call<WebResponse<AddressModel>> editAddressCall;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_add_address;
    }


    public static AddAdressFragment newInstance(OnAddressSelected onAddressSelected, boolean isFromEditAddress) {

        Bundle args = new Bundle();

        AddAdressFragment fragment = new AddAdressFragment();
        fragment.isFromEditAddress = isFromEditAddress;
        fragment.onAddressSelected = onAddressSelected;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrCity = new ArrayList();
        arrLocation = new ArrayList();


//        adaptCity = new ArrayAdapter<>(getMainActivity(), android.R.layout.simple_spinner_item, arrCity);
        adaptLocation = new ArrayAdapter<List<RegisterOptionsModel>>(getMainActivity(), android.R.layout.simple_spinner_item, arrLocation);


        googlePlaceHelper = new GooglePlaceHelper(getMainActivity(), GooglePlaceHelper.PLACE_PICKER, this, this);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMainActivity().setOnActivityResultInterface(this);
        addSpinnerData();


        setSpinner(adaptLocation, txtLocationType, spLocation);


        if (addressModelFromEdit != null && isFromEditAddress) {
            locationName = addressModelFromEdit.getLocationName();
            longitude = addressModelFromEdit.getLongitude();
            latitude = addressModelFromEdit.getLatitude();
            isGoogleLocationSelected = true;
            edtStreetName.setText(addressModelFromEdit.getStreet());
            edtBuildingName.setText(addressModelFromEdit.getBuilding());
            edtFloor.setText(addressModelFromEdit.getFloor());
            edtApartment.setText(addressModelFromEdit.getApartment());
            edtNearesLandmark.setTag(addressModelFromEdit.getLandMark());
            txtSelectLocationFromGoogle.setText(addressModelFromEdit.getLocationName());
            edtCityName.setText(addressModelFromEdit.getCity());
            edtCountryName.setText(addressModelFromEdit.getCountry());

            if (addressModelFromEdit.getLocationType().equals("Building")) {
                spLocation.setSelection(0);
            } else {
                spLocation.setSelection(1);
            }
//            spLocation.setSelection(addressModelFromEdit.getSpLocationTypePosition());


        }

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//
////        if (addressModelFromEdit != null && isFromEditAddress) {
////            locationName = addressModelFromEdit.getLocationName();
////            longitude = addressModelFromEdit.getLongitude();
////            latitude = addressModelFromEdit.getLatitude();
////            isGoogleLocationSelected = true;
////            edtStreetName.setText(addressModelFromEdit.getStreet());
////            edtBuildingName.setText(addressModelFromEdit.getBuilding());
////            edtFloor.setText(addressModelFromEdit.getFloor());
////            edtApartment.setText(addressModelFromEdit.getApartment());
////            edtNearesLandmark.setTag(addressModelFromEdit.getLandMark());
////            txtSelectLocationFromGoogle.setText(addressModelFromEdit.getLocationName());
////            edtCityName.setText(addressModelFromEdit.getCity());
////            edtCountryName.setText(addressModelFromEdit.getCountry());
////
////            spLocation.setSelection(addressModelFromEdit.getSpLocationTypePosition());
//
//    }

    private void addSpinnerData() {

        arrLocation.clear();

        arrLocation.add("Building");
        arrLocation.add("Villa");

        adaptLocation.notifyDataSetChanged();
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.GONE);
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


    @OnClick({R.id.btnBack, R.id.btnDone, R.id.txtLocationType, R.id.txtSelectLocationFromGoogle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                popBackStack();
                break;
            case R.id.btnDone:
                if (edtCountryName.testValidity() && edtCityName.testValidity() && edtStreetName.testValidity() && edtBuildingName.testValidity()) {
                    if (isGoogleLocationSelected) {
                        AddressModel addressModel = new AddressModel(edtCountryName.getText().toString().trim(), edtCityName.getText().toString().trim(), edtStreetName.getText().toString().trim(), edtBuildingName.getText().toString().trim(),
                                edtFloor.getText().toString().trim(), edtApartment.getText().toString().trim(), edtNearesLandmark.getText().toString().trim(), txtLocationType.getText().toString().trim(),
                                0, longitude, latitude, locationName, spLocation.getSelectedItemPosition());
                        if (isFromEditAddress) {
                            addressModel.setAddressID(addressModelFromEdit.getAddressID());
//                            addressModel.setAddressID(this.addressModelFromEdit.getAddressID());
                            editAddressService(addressModel);
                        } else {
                            addAddressService(addressModel);
                        }

                    } else {
                        UIHelper.showToast(getContext(), "Please select delivery location from google map");
                    }
                }

                break;
//            case R.id.txtSelectCity:
//                spCity.performClick();
//                break;
            case R.id.txtLocationType:
                spLocation.performClick();
                break;
            case R.id.txtSelectLocationFromGoogle:
                googlePlaceHelper.openAutocompleteActivity();
                break;
        }
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        googlePlaceHelper.onActivityResult(requestCode, resultCode, data);
//        return;
//    }

    @Override
    public void onPlaceActivityResult(double longitude, double latitude, String locationName) {
        isGoogleLocationSelected = true;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationName = locationName;

        GooglePlaceHelper.GoogleAddressModel googleAddressModel = GooglePlaceHelper.getAddress(getContext(), latitude, longitude);

        if (locationName != null && !locationName.equals("")) {

            txtSelectLocationFromGoogle.setText(locationName);
        }

        if (googleAddressModel.getCountry() != null && !googleAddressModel.getCountry().equals("")) {
            edtCountryName.setText(googleAddressModel.getCountry());
        }


        if (googleAddressModel.getCity() != null && !googleAddressModel.getCity().equals("")) {
            edtCityName.setText(googleAddressModel.getCity());
        }

        if (googleAddressModel.getStreetName() != null && !googleAddressModel.getStreetName().equals("")) {
            edtStreetName.setText(googleAddressModel.getStreetName());
        }
    }

    @Override
    public void onError(String error) {
        UIHelper.showToast(getContext(), error);
    }

    @Override
    public void onActivityResultInterface(int requestCode, int resultCode, Intent data) {
        googlePlaceHelper.onActivityResult(requestCode, resultCode, data);
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

    interface OnAddressSelected {
        void onAddressSelected(AddressModel addressModel);
    }

    public boolean getIsFromEditAddress() {
        return isFromEditAddress;
    }

    public void setAddressModelFromEdit(AddressModel addressModelFromEdit) {
        this.addressModelFromEdit = addressModelFromEdit;
    }

    private void addAddressService(final AddressModel addressModel) {
        addAddressCall = WebServiceFactory.getInstance(prefHelper.getUser().token).addAddress(WebServiceConstants.COUNTRY_ID_UAE, addressModel.getCountry(),
                WebServiceConstants.CITY_ID,
                addressModel.getCity(), addressModel.getStreet(), addressModel.getBuilding(), addressModel.getFloor(),
                addressModel.getApartment(), addressModel.getLandMark(), addressModel.getLocationType(), addressModel.getLocationName(), addressModel.getLatitude(), addressModel.getLongitude(),
                prefHelper.getUserID());

        addAddressCall.enqueue(new Callback<WebResponse<AddressModel>>() {
            @Override
            public void onResponse(Call<WebResponse<AddressModel>> call, Response<WebResponse<AddressModel>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    onAddressSelected.onAddressSelected(response.body().result);
                    popBackStack();
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<AddressModel>> call, Throwable t) {
                if (!addAddressCall.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });
    }

    private void editAddressService(final AddressModel addressModel) {
        editAddressCall = WebServiceFactory.getInstance(prefHelper.getUser().token).editAddress(addressModelFromEdit.getAddressID(), WebServiceConstants.COUNTRY_ID_UAE, addressModel.getCountry(),
                WebServiceConstants.CITY_ID,
                addressModel.getCity(), addressModel.getStreet(), addressModel.getBuilding(), addressModel.getFloor(),
                addressModel.getApartment(), addressModel.getLandMark(), addressModel.getLocationType(), addressModel.getLocationName(), addressModel.getLatitude(), addressModel.getLongitude(),
                prefHelper.getUserID());

        editAddressCall.enqueue(new Callback<WebResponse<AddressModel>>() {
            @Override
            public void onResponse(Call<WebResponse<AddressModel>> call, Response<WebResponse<AddressModel>> response) {
                if (response == null || response.body() == null) {
                    return;
                }
                if (response.body().isSuccess()) {
                    onAddressSelected.onAddressSelected(addressModel);
                    popBackStack();
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<AddressModel>> call, Throwable t) {
                if (!editAddressCall.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (editAddressCall != null) {
            editAddressCall.cancel();
        }
        if (addAddressCall != null) {
            addAddressCall.cancel();
        }
        super.onDestroy();
    }
}
