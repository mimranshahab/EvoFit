package edu.aku.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.R;
import edu.aku.activities.MainActivity;
import edu.aku.constatnts.WebServiceConstants;
import edu.aku.enums.FileType;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.fragments.dialogs.SuccessDialogFragment;
import edu.aku.helperclasses.MobileNumberValidation;
import edu.aku.helperclasses.RunTimePermissions;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.SquareImageView;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;
import edu.aku.managers.FileManager;
import edu.aku.managers.retrofit.WebServices;
import edu.aku.models.wrappers.WebResponse;

import static android.app.Activity.RESULT_OK;
import static edu.aku.constatnts.WebServiceConstants.METHOD_USER_UPLOAD_REQUEST_FILE;

/**
 * Created by hamzakhan on 5/10/2017.
 */
public class SignUpFragment extends BaseFragment {

    String uploadedFileName;
    //    String filePathCNIC;
//        String filePathPassport;
    boolean isSelectingCNICPic;
    boolean isFileUploaded = false;
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


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_signup;
    }

    public static SignUpFragment newInstance() {
        Bundle args = new Bundle();
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RunTimePermissions.verifyStoragePermissions(getMainActivity());
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.sign_up));
        titleBar.showBackButton(getMainActivity());
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
//        edPassword.addValidator(new PasswordValidation());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnSignUp, R.id.imgCNIC, R.id.imgPassport})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.imgCNIC:
                isSelectingCNICPic = true;
                cropImagePicker();
                break;

            case R.id.imgPassport:
                isSelectingCNICPic = false;
                cropImagePicker();
                break;

            case R.id.btnSignUp:


                break;
        }
    }

    private void uploadImageFile(final String uploadFilePath) {
        new WebServices(getMainActivity(), WebServiceConstants.temporaryToken)
                .webServiceRequestFileAPI(METHOD_USER_UPLOAD_REQUEST_FILE, uploadFilePath, FileType.IMAGE, new WebServices.IRequestJsonDataCallBackForStringResult() {
                    @Override
                    public void requestDataResponse(WebResponse<String> webResponse) {
                        if (webResponse.result.isEmpty()) {
                            UIHelper.showToast(getContext(), "Failed to upload file. Please try again.");
                         } else {
                            String[] strings = webResponse.result.split("-");
                            isFileUploaded = strings[0].equals("true");
                            if (isFileUploaded) {
                                uploadedFileName = strings[1];
                            }

                            UIHelper.showShortToastInCenter(getContext(), webResponse.message);
                            setImageAfterResult(uploadFilePath);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

//    private void showImageDialog() {
//        final GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();
//        genericDialogFragment.setTitle(getString(R.string.selectImage));
//        genericDialogFragment.setMessage(getString(R.string.pleaseSelectImageFrom));
//        genericDialogFragment.setButton1(getString(R.string.camera), new GenericClickableInterface() {
//            @Override
//            public void click() {
//                genericDialogFragment.getDialog().dismiss();
//                getMainActivity().takePicture();
//            }
//        });
//
//        genericDialogFragment.setButton2(getString(R.string.gallery), new GenericClickableInterface() {
//            @Override
//            public void click() {
//                genericDialogFragment.getDialog().dismiss();
//                getMainActivity().chooseImage();
//            }
//        });
//        genericDialogFragment.show(getFragmentManager(), null);
//    }

//    @Override
//    public void onChoosePicture(String originalFilePath, String thumbnailFilePath, String thumbnailSmallFilePath) {
////        btnUploadImage.setImageURI(Uri.parse(String.valueOf(new File(thumbnailFilePath))));
//        uploadImageFile(originalFilePath);
//    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());
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
                .setOutputCompressQuality(100)
                .start(getContext(), this);
    }

    private void showSignUpSuccessDialog() {

        final SuccessDialogFragment successDialogFragment = SuccessDialogFragment.newInstance();
        successDialogFragment.setTitle(getString(R.string.sign_up));
        successDialogFragment.setMessage(edFullName.getText().toString());
        successDialogFragment.setButton1(getString(R.string.Ok), new GenericClickableInterface() {
            @Override
            public void click() {
                getMainActivity().addDockableFragment(VerifyYourNumberFragment.newInstance());
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
                uploadImageFile(result.getUri().toString());
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
        } else {
            ImageLoader.getInstance().displayImage(uploadFilePath, imgPassport);
        }
    }
}

