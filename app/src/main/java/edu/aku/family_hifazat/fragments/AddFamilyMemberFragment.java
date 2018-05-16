package edu.aku.family_hifazat.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import edu.aku.family_hifazat.widget.AnyTextView;

import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.enums.FileType;
 import edu.aku.family_hifazat.helperclasses.RunTimePermissions;
import edu.aku.family_hifazat.helperclasses.ui.helper.KeyboardHelper;
import edu.aku.family_hifazat.widget.SquareImageView;
import edu.aku.family_hifazat.widget.TitleBar;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.helperclasses.validator.CnicValidation;
import edu.aku.family_hifazat.helperclasses.validator.MobileNumberValidation;
import edu.aku.family_hifazat.libraries.maskformatter.MaskFormatter;
import edu.aku.family_hifazat.managers.DateManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.validator.MRValidation;
import edu.aku.family_hifazat.helperclasses.validator.PassportValidation;
import edu.aku.family_hifazat.managers.FileManager;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.receiving_model.RegisterOptionsModel;
import edu.aku.family_hifazat.models.receiving_model.RegisterVM;
import edu.aku.family_hifazat.models.wrappers.WebResponse;

import static android.app.Activity.RESULT_OK;
import static edu.aku.family_hifazat.constatnts.AppConstants.CNIC_MASK;
import static edu.aku.family_hifazat.constatnts.AppConstants.MR_NUMBER_MASK;

/**
 * Created by hamzakhan on 5/10/2017.
 */
public class AddFamilyMemberFragment extends BaseFragment {

    String nameCNICUploadedFile;
    String namePassportUploadedFile;
    boolean isSelectingCNICPic;
    boolean isFileUploaded = false;
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
    @BindView(R.id.edtMRNumber)
    FormEditText edtMRNumber;
    @BindView(R.id.txtRelationship)
    AnyTextView txtRelationship;
    @BindView(R.id.spRelationship)
    Spinner spRelationship;
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
    @BindView(R.id.imgCNIC)
    SquareImageView imgCNIC;
    @BindView(R.id.txtCNICImageName)
    AnyTextView txtCNICImageName;
    @BindView(R.id.imgPassport)
    SquareImageView imgPassport;
    @BindView(R.id.txtPassportImageName)
    AnyTextView txtPassportImageName;
    @BindView(R.id.btnAddMember)
    AnyTextView btnAddMember;

    private File fileTemporaryProfilePicture;


    Unbinder unbinder;
    private ArrayList<RegisterOptionsModel> arrGender;
    private ArrayAdapter adaptGender;

    private ArrayList<RegisterOptionsModel> arrRelationship;
    private ArrayAdapter adaptRelationship;
    private RegisterVM registerVM;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_add_family_member;
    }

    public static AddFamilyMemberFragment newInstance() {
        Bundle args = new Bundle();
        AddFamilyMemberFragment fragment = new AddFamilyMemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RunTimePermissions.verifyStoragePermissions(getBaseActivity());
        initializeArray();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        registerVM = SharedPreferenceManager.getInstance(getContext()).getObject(AppConstants.KEY_REGISTER_VM, RegisterVM.class);
        setValidations();
//        setSpinnerData();
    }

    private void setSpinnerData() {
        if (registerVM != null) {
            arrRelationship.clear();
            arrRelationship.addAll(registerVM.getRelationsList());
            arrGender.clear();
            arrGender.addAll(registerVM.getGenderList());

            setSpinner(adaptGender, txtGender, spGender);
            setSpinner(adaptRelationship, txtRelationship, spRelationship);
        }
    }


    private void initializeArray() {
        arrGender = new ArrayList<RegisterOptionsModel>();
        adaptGender = new ArrayAdapter<RegisterOptionsModel>(getBaseActivity(),
                android.R.layout.simple_list_item_1, arrGender);

        arrRelationship = new ArrayList<RegisterOptionsModel>();
        adaptRelationship = new ArrayAdapter<RegisterOptionsModel>(getBaseActivity(),
                android.R.layout.simple_list_item_1, arrRelationship);
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Add Family Member");
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
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }



    private void setValidations() {
        edMobileNumber.addValidator(new MobileNumberValidation());
        edtCNICNumber.addValidator(new CnicValidation());
        edtMRNumber.addValidator(new MRValidation());
        edtPassportNumber.addValidator(new PassportValidation());

        edtCNICNumber.addTextChangedListener(new MaskFormatter(CNIC_MASK, edtCNICNumber, '-'));
        edtMRNumber.addTextChangedListener(new MaskFormatter(MR_NUMBER_MASK, edtMRNumber, '-'));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnAddMember, R.id.imgCNIC, R.id.imgPassport, R.id.txtDateofBirth, R.id.txtGender, R.id.txtRelationship})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.txtDateofBirth:
                DateManager.showDatePicker(getContext(), txtDateofBirth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                    }
                }, false);

                break;
            case R.id.imgCNIC:
                isSelectingCNICPic = true;
                cropImagePicker();
                break;

            case R.id.imgPassport:
                isSelectingCNICPic = false;
                cropImagePicker();
                break;

            case R.id.btnAddMember:
                if (edFullName.testValidity() && !txtDateofBirth.getText().toString().isEmpty() && edtCNICNumber.testValidity()
                        && edtPassportNumber.testValidity() && edEmailAddress.testValidity() && edMobileNumber.testValidity()
                        && edtLandlineNumber.testValidity()) {
                    if (txtDateofBirth.getText().toString().isEmpty()) {
                        UIHelper.showShortToastInCenter(getContext(), "Please select date of Birth");
                        break;
                    }
                    if (nameCNICUploadedFile == null && namePassportUploadedFile == null) {
                        UIHelper.showShortToastInCenter(getContext(), "Please upload CNIC or Passport Image");
                        break;
                    }


                    UIHelper.showShortToastInCenter(getContext(), "Successful registration");


                } else {
                    UIHelper.showShortToastInCenter(getContext(), "Please fill all fields correctly.");
                }


                break;

            case R.id.txtGender:
                spGender.performClick();
                break;

            case R.id.txtRelationship:
                spRelationship.performClick();
                break;

        }
    }

    private void uploadImageFile(final String uploadFilePath, final String uploadFileUriPath) {
        new WebServices(getBaseActivity(), getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceUploadFileAPI(WebServiceConstants.METHOD_USER_UPLOAD_REQUEST_FILE,
                        uploadFilePath, FileType.IMAGE,
                        // FIXME: 3/21/2018 putlivedata
                        "",
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
//                                if (webResponse.result.isEmpty()) {
//                                    UIHelper.showToast(getContext(), "Failed to upload file. Please try again.");
//                                } else {

//                                    String namePassportUploadedFile = webResponse.result;
//
//                                    UIHelper.showShortToastInCenter(getContext(), webResponse.message);
//                                    setImageAfterResult(uploadFileUriPath);
//                                }
                            }

                            @Override
                            public void onError() {

                            }
                        });

    }


    @Override
    public void onResume() {
        super.onResume();
        KeyboardHelper.hideSoftKeyboard(getBaseActivity(), getView());
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
        if (isSelectingCNICPic) {
            ImageLoader.getInstance().displayImage(uploadFilePath, imgCNIC);
            txtCNICImageName.setText(nameCNICUploadedFile);
        } else {
            ImageLoader.getInstance().displayImage(uploadFilePath, imgPassport);
            txtPassportImageName.setText(namePassportUploadedFile);
        }
    }
}

