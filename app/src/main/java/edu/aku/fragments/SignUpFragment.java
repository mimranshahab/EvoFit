package edu.aku.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import com.google.gson.JsonObject;

import edu.aku.R;
import edu.aku.activities.MainActivity;
import edu.aku.constatnts.WebServiceConstants;
import edu.aku.enums.FileType;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.fragments.dialogs.SuccessDialogFragment;
import edu.aku.helperclasses.MarshMallowPermission;
import edu.aku.helperclasses.MobileNumberValidation;
import edu.aku.helperclasses.PasswordValidation;
import edu.aku.helperclasses.RunTimePermissions;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.FileManager;
import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.managers.retrofit.WebServices;
import edu.aku.models.UserModel;
import edu.aku.models.wrappers.WebResponse;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static edu.aku.constatnts.WebServiceConstants.METHOD_USER_UPLOAD_REQUEST_FILE;

/**
 * Created by hamzakhan on 5/10/2017.
 */
public class SignUpFragment extends BaseFragment implements MainActivity.ChoosePictureInterface {

    String uploadedFileName;
    boolean isFileUploaded = false;
    Unbinder unbinder;
    @BindView(R.id.btnUploadImage)
    CircleImageView btnUploadImage;
    @BindView(R.id.txtFullName)
    AnyTextView txtFullName;
    @BindView(R.id.edFullName)
    FormEditText edFullName;
    @BindView(R.id.txtEmailAddress)
    AnyTextView txtEmailAddress;
    @BindView(R.id.edEmailAddress)
    FormEditText edEmailAddress;
    @BindView(R.id.txtPassword)
    AnyTextView txtPassword;
    @BindView(R.id.edPassword)
    FormEditText edPassword;
    @BindView(R.id.txtMobileNumber)
    AnyTextView txtMobileNumber;
    @BindView(R.id.edMobileNumber)
    FormEditText edMobileNumber;
    @BindView(R.id.btnSignUp)
    AnyTextView btnSignUp;
     private MultipartBody.Part bodyProfilePicture;

    private Call<WebResponse<UserModel>> signUpClickCall;

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
        getMainActivity().setChoosePictureListener(this);
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
        edPassword.addValidator(new PasswordValidation());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnUploadImage, R.id.btnSignUp})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btnUploadImage:
                showImageDialog();
                break;

            case R.id.btnSignUp:


                break;
        }
    }

    private void uploadImageFile(String originalFilePath) {
        new WebServices(getMainActivity(), WebServiceConstants.temporaryToken)
                .webServiceRequestFileAPI(METHOD_USER_UPLOAD_REQUEST_FILE, originalFilePath, FileType.IMAGE, new WebServices.IRequestJsonDataCallBackForStringResult() {
                    @Override
                    public void requestDataResponse(WebResponse<String> webResponse) {
                        if (webResponse.result.isEmpty()) {
                            return;
                        } else {
                            String[] strings = webResponse.result.split("-");
                            isFileUploaded = strings[0].equals("true");
                            if (isFileUploaded) {
                                uploadedFileName = strings[1];
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void showImageDialog() {
        final GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();
        genericDialogFragment.setTitle(getString(R.string.selectImage));
        genericDialogFragment.setMessage(getString(R.string.pleaseSelectImageFrom));
        genericDialogFragment.setButton1(getString(R.string.camera), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
                getMainActivity().takePicture();
            }
        });

        genericDialogFragment.setButton2(getString(R.string.gallery), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
                getMainActivity().chooseImage();
            }
        });
        genericDialogFragment.show(getFragmentManager(), null);
    }

    @Override
    public void onChoosePicture(String originalFilePath, String thumbnailFilePath, String thumbnailSmallFilePath) {
        btnUploadImage.setImageURI(Uri.parse(String.valueOf(new File(thumbnailFilePath))));
        uploadImageFile(originalFilePath);
     }

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
    public void onDestroy() {
        if (signUpClickCall != null) {
            signUpClickCall.cancel();
        }
        super.onDestroy();
    }
}
