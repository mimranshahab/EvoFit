package edu.aku.akuh_health_first.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.enums.FileType;
import edu.aku.akuh_health_first.fragments.dialogs.SuccessDialogFragment;
import edu.aku.akuh_health_first.helperclasses.RunTimePermissions;
import edu.aku.akuh_health_first.helperclasses.ui.helper.KeyboardHide;
import edu.aku.akuh_health_first.helperclasses.ui.helper.SquareImageView;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.helperclasses.validator.CnicValidation;
import edu.aku.akuh_health_first.helperclasses.validator.MobileNumberValidation;
import edu.aku.akuh_health_first.libraries.maskformatter.MaskFormatter;
import edu.aku.akuh_health_first.managers.DateManager;
import edu.aku.akuh_health_first.managers.retrofit.WebServiceFactory;
import edu.aku.akuh_health_first.models.PacsView;
import edu.aku.akuh_health_first.models.receiving_model.RegisterVM;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.fragments.abstracts.GenericClickableInterface;
import edu.aku.akuh_health_first.helperclasses.validator.MRValidation;
import edu.aku.akuh_health_first.helperclasses.validator.PassportValidation;
import edu.aku.akuh_health_first.managers.FileManager;
import edu.aku.akuh_health_first.managers.SharedPreferenceManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.receiving_model.RegisterOptionsModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static edu.aku.akuh_health_first.constatnts.AppConstants.CNIC_MASK;
import static edu.aku.akuh_health_first.constatnts.AppConstants.MR_NUMBER_MASK;

/**
 * Created by hamzakhan on 5/10/2017.
 */
public class RegisterFragment extends BaseFragment {

    String nameCNICUploadedFile;
    String namePassportUploadedFile;
    boolean isSelectingCNICPic;
    boolean isFileUploaded = false;
    @BindView(R.id.txtCNICImageName)
    AnyTextView txtCNICImageName;
    @BindView(R.id.txtPassportImageName)
    AnyTextView txtPassportImageName;
    private File fileTemporaryProfilePicture;


    @BindView(R.id.edtMotherName)
    FormEditText edtMotherName;
    @BindView(R.id.txtCardType)
    AnyTextView txtCardType;
    @BindView(R.id.spCardType)
    Spinner spCardType;
    @BindView(R.id.btnSignUp)
    AnyTextView btnSignUp;
    @BindView(R.id.txtFullName)
    AnyTextView txtFullName;
    @BindView(R.id.edFullName)
    FormEditText edFullName;
    @BindView(R.id.txtDateofBirth)
    AnyTextView txtDateofBirth;
    @BindView(R.id.txtGender)
    AnyTextView txtGender;
    @BindView(R.id.spGender)
    Spinner spGender;
    @BindView(R.id.edtCNICNumber)
    FormEditText edtCNICNumber;
    @BindView(R.id.edtPassportNumber)
    FormEditText edtPassportNumber;
    @BindView(R.id.txtEmailAddress)
    AnyTextView txtEmailAddress;
    @BindView(R.id.edEmailAddress)
    FormEditText edEmailAddress;
    @BindView(R.id.txtMobileNumber)
    AnyTextView txtMobileNumber;
    @BindView(R.id.edMobileNumber)
    FormEditText edMobileNumber;
    @BindView(R.id.edtLandlineNumber)
    FormEditText edtLandlineNumber;
    @BindView(R.id.edtCurrentAddress)
    FormEditText edtCurrentAddress;
    @BindView(R.id.edtCurrentCity)
    FormEditText edtCurrentCity;
    @BindView(R.id.txtCurrentCountry)
    AnyTextView txtCurrentCountry;
    @BindView(R.id.spCurrentCountry)
    Spinner spCurrentCountry;
    @BindView(R.id.edtPermanentAddress)
    FormEditText edtPermanentAddress;
    @BindView(R.id.edtPermanentCity)
    FormEditText edtPermanentCity;
    @BindView(R.id.txtPermanentCountry)
    AnyTextView txtPermanentCountry;
    @BindView(R.id.spPermanentCountry)
    Spinner spPermanentCountry;
    @BindView(R.id.edtMRNumber)
    FormEditText edtMRNumber;
    @BindView(R.id.imgCNIC)
    SquareImageView imgCNIC;
    @BindView(R.id.imgPassport)
    SquareImageView imgPassport;

    Unbinder unbinder;
    private ArrayList<RegisterOptionsModel> arrGender;
    private ArrayAdapter adaptGender;

    private ArrayList<RegisterOptionsModel> arrCurrentCountry;
    private ArrayAdapter adaptCurrentCountry;

    private ArrayList<RegisterOptionsModel> arrPermanentCountry;
    private ArrayAdapter adaptPermanentCountry;

    private ArrayList<RegisterOptionsModel> arrCardType;
    private ArrayAdapter adaptCardType;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_registration;
    }

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RunTimePermissions.verifyStoragePermissions(getBaseActivity());

        arrGender = new ArrayList<RegisterOptionsModel>();
        adaptGender = new ArrayAdapter<RegisterOptionsModel>(getBaseActivity(),
                android.R.layout.simple_list_item_1, arrGender);

        arrCurrentCountry = new ArrayList<RegisterOptionsModel>();
        adaptCurrentCountry = new ArrayAdapter<RegisterOptionsModel>(getBaseActivity(),
                android.R.layout.simple_list_item_1, arrCurrentCountry);

        arrPermanentCountry = new ArrayList<RegisterOptionsModel>();
        adaptPermanentCountry = new ArrayAdapter<RegisterOptionsModel>(getBaseActivity(),
                android.R.layout.simple_list_item_1, arrPermanentCountry);

        arrCardType = new ArrayList<RegisterOptionsModel>();
        adaptCardType = new ArrayAdapter<RegisterOptionsModel>(getBaseActivity(),
                android.R.layout.simple_list_item_1, arrCardType);
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Register");
        titleBar.showBackButton(getBaseActivity());
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edMobileNumber.addValidator(new MobileNumberValidation());
        edtCNICNumber.addValidator(new CnicValidation());
        edtMRNumber.addValidator(new MRValidation());
        edtPassportNumber.addValidator(new PassportValidation());
        edtCNICNumber.addTextChangedListener(new MaskFormatter(CNIC_MASK, edtCNICNumber, '-'));
        edtMRNumber.addTextChangedListener(new MaskFormatter(MR_NUMBER_MASK, edtMRNumber, '-'));
        getRegisterVM();
//        CallPacManager();
    }

    private void getRegisterVM() {
        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken, BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPI(WebServiceConstants.METHOD_USER_GET_REGISTER_VM, "", new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        RegisterVM registerVM = GsonFactory.getSimpleGson().fromJson(webResponse.result, RegisterVM.class);
                        UIHelper.showShortToastInCenter(getContext(), webResponse.message);
                        SharedPreferenceManager.getInstance(getContext()).putObject(AppConstants.KEY_REGISTER_VM, webResponse.result);
                        setSpinnerData(registerVM);
                    }

                    @Override
                    public void onError() {
                        UIHelper.showShortToastInCenter(getContext(), "failure");
                    }
                });
    }

    private void setSpinnerData(RegisterVM registerVM) {
        arrGender.clear();
        arrGender.addAll(registerVM.getGenderList());
        setSpinner(adaptGender, txtGender, spGender);

        arrCurrentCountry.clear();
        arrCurrentCountry.addAll(registerVM.getCurrentCountryList());
        setSpinner(adaptCurrentCountry, txtCurrentCountry, spCurrentCountry);

        arrPermanentCountry.clear();
        arrPermanentCountry.addAll(registerVM.getPermanentCountryList());
        setSpinner(adaptPermanentCountry, txtPermanentCountry, spPermanentCountry);

        arrCardType.clear();
        arrCardType.addAll(registerVM.getCardTypeList());
        setSpinner(adaptCardType, txtCardType, spCardType);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnSignUp, R.id.imgCNIC, R.id.imgPassport, R.id.txtDateofBirth, R.id.txtGender, R.id.txtCurrentCountry, R.id.txtPermanentCountry, R.id.txtCardType})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.txtDateofBirth:
                DateManager.showDatePicker(getContext(), txtDateofBirth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                    }
                });

                break;
            case R.id.imgCNIC:
                isSelectingCNICPic = true;
                cropImagePicker();
                break;

            case R.id.imgPassport:
                isSelectingCNICPic = false;
                cropImagePicker();
                break;

            case R.id.btnSignUp:
//                if (edFullName.testValidity() && !txtDateofBirth.getText().toString().isEmpty() && edtCNICNumber.testValidity()
//                        && edtPassportNumber.testValidity() && edEmailAddress.testValidity() && edMobileNumber.testValidity()
//                        && edtLandlineNumber.testValidity() && edtCurrentAddress.testValidity() && edtCurrentCity.testValidity()
//                        && edtPermanentAddress.testValidity() && edtPermanentCity.testValidity() && edtMRNumber.testValidity()
//                        && edtMotherName.testValidity()) {
//                    if (txtDateofBirth.getText().toString().isEmpty()) {
//                        UIHelper.showShortToastInCenter(getContext(), "Please select date of Birth");
//                        break;
//                    }
//                    if (nameCNICUploadedFile == null && namePassportUploadedFile == null) {
//                        UIHelper.showShortToastInCenter(getContext(), "Please upload CNIC or Passport Image");
//                        break;
//                    }
//
//
//                    UIHelper.showShortToastInCenter(getContext(), "Successful registration");
//                    getBaseActivity().addDockableFragment(MyFamilyFragment.newInstance());
//
//
//                } else {
//                    UIHelper.showShortToastInCenter(getContext(), "Please fill all fields correctly.");
//                }


                if (edtCNICNumber.testValidity() && edtMRNumber.testValidity()) {
                    getBaseActivity().addDockableFragment(MyFamilyFragment.newInstance());
                }

                break;

            case R.id.txtGender:
                spGender.performClick();
                break;

            case R.id.txtCurrentCountry:
                spCurrentCountry.performClick();
                break;

            case R.id.txtPermanentCountry:
                spPermanentCountry.performClick();
                break;

            case R.id.txtCardType:
                spCardType.performClick();
                break;
        }
    }

    private void uploadImageFile(final String uploadFilePath, final String uploadFileUriPath) {
        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken, BaseURLTypes.AHFA_BASE_URL)
                .webServiceUploadFileAPI(WebServiceConstants.METHOD_USER_UPLOAD_REQUEST_FILE, uploadFilePath, FileType.IMAGE, new WebServices.IRequestJsonDataCallBackForStringResult() {
                    @Override
                    public void requestDataResponse(WebResponse<String> webResponse) {
                        if (webResponse.result.isEmpty()) {
                            UIHelper.showToast(getContext(), "Failed to upload file. Please try again.");
                        } else {
//                            String[] strings = webResponse.result.split("-");
//                            isFileUploaded = strings[0].equals("true");
//                            if (isFileUploaded) {
//                                if (isSelectingCNICPic) nameCNICUploadedFile = strings[1];
//                                else namePassportUploadedFile = strings[1];
//                            }

                            if (isSelectingCNICPic) {
                                nameCNICUploadedFile = webResponse.result;
                            } else {
                                namePassportUploadedFile = webResponse.result;
                            }
                            UIHelper.showShortToastInCenter(getContext(), webResponse.message);
                            setImageAfterResult(uploadFileUriPath);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getBaseActivity(), getView());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void cropImagePicker() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .setMinCropWindowSize(192, 192)
                .setMinCropResultSize(192, 192)
                .setMultiTouchEnabled(false)
                .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                // FIXME: 15-Jul-17 Fix Quality if required
                .setRequestedSize(640, 640, CropImageView.RequestSizeOptions.RESIZE_FIT)
                .setOutputCompressQuality(80)
                .start(getContext(), this);
    }

    private void showSignUpSuccessDialog() {

        final SuccessDialogFragment successDialogFragment = SuccessDialogFragment.newInstance();
        successDialogFragment.setTitle(getString(R.string.sign_up));
        successDialogFragment.setMessage(edFullName.getText().toString());
        successDialogFragment.setButton1(getString(R.string.Ok), new GenericClickableInterface() {
            @Override
            public void click() {
                getBaseActivity().addDockableFragment(VerifyYourNumberFragment.newInstance());
                successDialogFragment.getDialog().dismiss();
            }
        });
        successDialogFragment.show(getFragmentManager(), null);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                fileTemporaryProfilePicture = new File(result.getUri().getPath());
                uploadImageFile(fileTemporaryProfilePicture.getPath(), result.getUri().toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                error.printStackTrace();
            }
        }
    }


    private void setImageAfterResult(final String uploadFilePath) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    setAndUploadImage(uploadFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setAndUploadImage(String uploadFilePath) throws IOException {
        Log.d("PICTURE", FileManager.getFileSize(uploadFilePath));

//            long originalLength = fileOriginal.length();
//            originalLength = originalLength / 1024;
//
//            String msgOriginalFile = "setAndUploadImage: size Original " + originalLength + " Kb" + " -dimensions " + originalBitmap.getWidth() + " x " + originalBitmap.getHeight();
//            Log.d("PICTURE", msgOriginalFile);
//
//
//            long thumbLength = fileThumbnail.length();
//            thumbLength = thumbLength / 1024;
//            Log.d("PICTURE", "setAndUploadImage: size Thumbnail " + thumbLength + " Kb" + " -dimensions " + thumbnailBitmap.getWidth() + " x " + thumbnailBitmap.getHeight());
        if (isSelectingCNICPic) {
            ImageLoader.getInstance().displayImage(uploadFilePath, imgCNIC);
            txtCNICImageName.setText(nameCNICUploadedFile);
        } else {
            ImageLoader.getInstance().displayImage(uploadFilePath, imgPassport);
            txtCNICImageName.setText(namePassportUploadedFile);
        }
    }


    private void CallPacManager() {
        ArrayList<String> item = new ArrayList<String>();
        item.add(WebServiceConstants.tempPacViews.toString());

        PacsView pacViews = new PacsView(item);


        new WebServices(getBaseActivity(), WebServiceConstants.temporaryToken, BaseURLTypes.PACS_VIEWER)
                .webServiceRequestAPI(WebServiceConstants.METHOD_PACMANAGER, pacViews.toString(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {

                                PacsView entity = GsonFactory.getSimpleGson().fromJson(webResponse.result, PacsView.class);

                                UIHelper.showToast(getContext(), entity.toString());
                            }

                            @Override
                            public void onError() {

                            }
                        });



    }

    @NonNull
    public RequestBody getRequestBody(MediaType form, String trim) {
        return RequestBody.create(
                form, trim);
    }
}

