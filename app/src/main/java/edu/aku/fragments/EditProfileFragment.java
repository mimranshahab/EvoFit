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
import com.nostra13.universalimageloader.core.ImageLoader;
import edu.aku.R;
import edu.aku.activities.MainActivity;
import edu.aku.constatnts.WebServiceConstants;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.helperclasses.MobileNumberValidation;
import edu.aku.helperclasses.RunTimePermissions;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;
import edu.aku.libraries.imageloader.LazyLoading;
import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.model.UserModel;
import edu.aku.model.wrappers.WebResponse;

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
 * Created by shehrozmirza on 5/15/2017.
 */

public class EditProfileFragment extends
        BaseFragment implements MainActivity.ChoosePictureInterface {

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
    @BindView(R.id.txtMobileNumber)
    AnyTextView txtMobileNumber;
    @BindView(R.id.edMobileNumber)
    FormEditText edMobileNumber;
    private UserModel userModel;
    private ImageLoader imageLoader;
    private MultipartBody.Part bodyProfilePicture;
    private String imagePath;
    private TitleBar titleBar;
    private Call<WebResponse<UserModel>> editProfileCall;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_edit_profile;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        this.titleBar = titleBar;
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.editProfile));
        titleBar.showBackButton(getMainActivity());
        titleBar.setRightButton(R.drawable.imgtick, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edFullName.testValidity() && edEmailAddress.testValidity()
                        && edMobileNumber.testValidity()) {
                    editProfileClick();
                }
            }
        });
    }


    public static EditProfileFragment newInstance() {
        Bundle args = new Bundle();
        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RunTimePermissions.verifyStoragePermissions(getMainActivity());
        imageLoader = ImageLoader.getInstance();
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

    @OnClick(R.id.btnUploadImage)
    public void onViewClicked() {
        imageSelectionDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edMobileNumber.addValidator(new MobileNumberValidation());
        if (prefHelper.getUser() != null) {
            userModel = prefHelper.getUser();
            if (userModel.userProfilePictureURL != null) {
                imageLoader.displayImage(userModel.userProfilePictureURL, btnUploadImage, LazyLoading.options);
            }
            edFullName.setText(userModel.userName);
            edEmailAddress.setText(userModel.userEmail);
            edMobileNumber.setText(userModel.userPhoneNumber);
        }
    }

    private void imageSelectionDialog() {

        final GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();

        genericDialogFragment.setTitle(getString(R.string.selectImage));
        genericDialogFragment.setMessage(getString(R.string.pleaseSelectImageFrom));
        genericDialogFragment.setButton1(getString(R.string.camera), new GenericClickableInterface() {
            @Override
            public void click() {
                getMainActivity().takePicture();
                genericDialogFragment.getDialog().dismiss();
            }
        });

        genericDialogFragment.setButton2(getString(R.string.gallery), new GenericClickableInterface() {
            @Override
            public void click() {
                getMainActivity().chooseImage();
                genericDialogFragment.getDialog().dismiss();
            }
        });
        genericDialogFragment.show(getFragmentManager(), null);
    }

    private void editProfileClick() {
        showProgress();

        if (titleBar.btnRight1 != null) {
            titleBar.btnRight1.setClickable(false);
        }


        RequestBody bodyUserID =
                getRequestBody(okhttp3.MultipartBody.FORM, String.valueOf(userModel.userID));
        RequestBody bodyFullName =
                getRequestBody(okhttp3.MultipartBody.FORM, edFullName.getText().toString().trim());
        RequestBody bodyMobileNumber =
                getRequestBody(okhttp3.MultipartBody.FORM, edMobileNumber.getText().toString().trim());
        RequestBody bodyDeviceType =
                getRequestBody(okhttp3.MultipartBody.FORM, WebServiceConstants.DEVICE_TYPE);
        RequestBody deviceToken =
                getRequestBody(okhttp3.MultipartBody.FORM, WebServiceConstants.DEVICE_TOKEN);

        if (imagePath != null) {
            File file = new File(imagePath);
            bodyProfilePicture =
                    MultipartBody.Part.createFormData("image", file.getName(),
                            RequestBody.create(MediaType.parse("image/*"), file));
        }

        editProfileCall = WebServiceFactory.getInstance(userModel.token).postEditProfile(bodyUserID, bodyFullName, bodyMobileNumber,
                bodyProfilePicture, bodyDeviceType, deviceToken);
        editProfileCall.enqueue(new Callback<WebResponse<UserModel>>() {
            @Override
            public void onResponse(Call<WebResponse<UserModel>> call, Response<WebResponse<UserModel>> response) {

                if (titleBar != null)
                    titleBar.btnRight1.setClickable(true);

                if (response.body().isSuccess()) {
                    prefHelper.putUser(response.body().result);
                    userModel = response.body().result;
                    dismissProgress();
                    popBackStack();
                    UIHelper.showToast(getContext(), response.body().message);
                } else {
                    dismissProgress();
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<UserModel>> call, Throwable t) {
                dismissProgress();
                if (!editProfileCall.isCanceled()) {
                    if (titleBar != null)
                        titleBar.btnRight1.setClickable(true);
                    t.printStackTrace();
                }
            }
        });
    }

    @NonNull
    private RequestBody getRequestBody(MediaType form, String trim) {
        return RequestBody.create(form, trim);
    }

    @Override
    public void onChoosePicture(String originalFilePath, String thumbnailFilePath, String thumbnailSmallFilePath) {
        btnUploadImage.setImageURI(Uri.parse(String.valueOf(new File(thumbnailFilePath))));
        imagePath = originalFilePath;
    }

    @Override
    public void onDestroy() {
        if (editProfileCall != null) {
            editProfileCall.cancel();
        }
        super.onDestroy();
    }
}
