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
import edu.aku.R;
import edu.aku.activities.MainActivity;
import edu.aku.constatnts.WebServiceConstants;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.fragments.dialogs.SuccessDialogFragment;
import edu.aku.helperclasses.MobileNumberValidation;
import edu.aku.helperclasses.PasswordValidation;
import edu.aku.helperclasses.RunTimePermissions;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
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

/**
 * Created by shehrozmirza on 5/10/2017.
 */
public class SignUpFragment extends BaseFragment implements MainActivity.ChoosePictureInterface {

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
    private String imagePath;
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
                if (edFullName.testValidity() && edEmailAddress.testValidity() &&
                        edPassword.testValidity() &&
                        edMobileNumber.testValidity()) {
                    signUpClick();
                }
                break;
        }
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
        imagePath = originalFilePath;
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


    private void signUpClick() {

        RequestBody bodyFullName =
                getRequestBody(okhttp3.MultipartBody.FORM, edFullName.getText().toString().trim());

        RequestBody bodyEmailAddress =
                getRequestBody(okhttp3.MultipartBody.FORM, edEmailAddress.getText().toString().trim());

        RequestBody bodyMobileNumber =
                getRequestBody(okhttp3.MultipartBody.FORM, edMobileNumber.getText().toString().trim());

        RequestBody bodyPassword =
                getRequestBody(okhttp3.MultipartBody.FORM, edPassword.getText().toString().trim());

        RequestBody bodyDeviceType =
                getRequestBody(okhttp3.MultipartBody.FORM, WebServiceConstants.DEVICE_TYPE);

        RequestBody bodyDeviceToken =
                getRequestBody(okhttp3.MultipartBody.FORM, WebServiceConstants.DEVICE_TOKEN);


        if (imagePath != null) {
            File file = new File(imagePath);
            bodyProfilePicture =
                    MultipartBody.Part.createFormData("image", file.getName(),
                            RequestBody.create(MediaType.parse("image/*"), file));
        }

        btnSignUp.setEnabled(false);
        signUpClickCall = WebServiceFactory.getInstance("").postSignUp(bodyFullName, bodyEmailAddress,
                bodyMobileNumber, bodyPassword,
                bodyDeviceType, bodyDeviceToken, bodyProfilePicture);
        signUpClickCall.enqueue(new Callback<WebResponse<UserModel>>() {

            @Override
            public void onResponse(Call<WebResponse<UserModel>> call,
                                   Response<WebResponse<UserModel>> response) {
                btnSignUp.setEnabled(true);
                if (response.body() == null || response.body().result == null)
                    return;

                if (response.body().isSuccess()) {
                    prefHelper.setGuest(false);
                    prefHelper.putUser(response.body().result);
                    showSignUpSuccessDialog();
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<UserModel>> call, Throwable t) {
                if (!signUpClickCall.isCanceled()) {
                    btnSignUp.setEnabled(true);
                    t.printStackTrace();
                }
            }
        });
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

    @NonNull
    private RequestBody getRequestBody(MediaType form, String trim) {
        return RequestBody.create(
                form, trim);
    }

    @Override
    public void onDestroy(){
        if (signUpClickCall != null) {
            signUpClickCall.cancel();
        }
        super.onDestroy();
    }
}
