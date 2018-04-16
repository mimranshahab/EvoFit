package edu.aku.family_hifazat.fragments;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.callbacks.OnNewPacketReceivedListener;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.constatnts.Events;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.enums.FileType;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.TitleBar;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.libraries.imageloader.ImageLoaderHelper;
import edu.aku.family_hifazat.managers.FileManager;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.RequestModel;
import edu.aku.family_hifazat.models.receiving_model.AddUpdateModel;
import edu.aku.family_hifazat.models.receiving_model.CardMemberDetail;
import edu.aku.family_hifazat.models.receiving_model.UserDetailModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by aqsa.sarwar on 1/19/2018.
 */

public class ProfileFragment extends BaseFragment implements OnNewPacketReceivedListener {

    Unbinder unbinder;
    @BindView(R.id.circleImageView)
    CircleImageView circleImageView;
    @BindView(R.id.btnCamera)
    ImageButton btnCamera;
    @BindView(R.id.txtUserName)
    AnyTextView txtUserName;
    @BindView(R.id.txtMRN)
    AnyTextView txtMRN;
    @BindView(R.id.txtAge)
    AnyTextView txtAge;
    @BindView(R.id.txtCincNumber)
    AnyTextView txtCincNumber;
    @BindView(R.id.txtPhoneNum)
    AnyTextView txtPhoneNum;
    @BindView(R.id.txtAddress)
    AnyTextView txtAddress;
    @BindView(R.id.txtEmailAddress)
    AnyTextView txtEmailAddress;
    @BindView(R.id.refreshLayout)
    LinearLayout refreshLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.contParent)
    LinearLayout contParent;

    private File fileTemporaryProfilePicture;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_profile;
    }

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeToNewPacket(this);
        setData(null);
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    private void setData(UserDetailModel userDetailModel) {
        UserDetailModel currentUser;
        if (userDetailModel == null) {
            currentUser = sharedPreferenceManager.getCurrentUser();
        } else {
            currentUser = userDetailModel;
        }


        if (txtUserName == null) return;

        txtUserName.setText(currentUser.getName());
        txtAge.setText(currentUser.getGenderDescription() + " / " + currentUser.getAge() + " Y");
        txtEmailAddress.setText(currentUser.getEmailAddress());
        txtMRN.setText(currentUser.getMRNumber());
        txtCincNumber.setText(currentUser.getCNICNumber());
        txtAddress.setText(currentUser.getCurrentAddress()+", "+currentUser.getCurrentCity()+", "+currentUser.getCurrentCountryDescription());
        txtPhoneNum.setText(currentUser.getCellPhoneNumber());
        if (currentUser.getProfileImage() == null || currentUser.getProfileImage().isEmpty()) {
            if (currentUser.getGender().equals("F")) {

                circleImageView.setImageResource(R.drawable.female_icon);
            } else {
                circleImageView.setImageResource(R.drawable.male_icon);
            }

        } else {
            ImageLoaderHelper.loadImageWithConstantHeadersWithoutAnimation(getContext(), circleImageView, currentUser.getProfileImage());
        }
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("My profile");
        titleBar.showBackButton(getBaseActivity());
        titleBar.showHome(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
    }


    @Override
    public void setListeners() {

        contParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 0;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnCamera, R.id.fab, R.id.circleImageView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCamera:
                cropImagePicker();
                break;
            case R.id.fab:
                getBaseActivity().addDockableFragment(EditProfileFragment.newInstance(), false);
                break;
            case R.id.circleImageView:
                getBaseActivity().openImagePreviewActivity(getCurrentUser().getProfileImage(), getCurrentUser().getName());
                break;
        }
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


        ImageLoader.getInstance().displayImage(uploadFilePath, circleImageView);
//        txtCNICImageName.setText(namePassportUploadedFile);

    }

    private void uploadImageFile(final String uploadFilePath, final String uploadFileUriPath) {
        RequestModel requestModel = new RequestModel();


        requestModel.setCardNumber(sharedPreferenceManager.getCardMemberDetail().getCardNumber());
        requestModel.setMRNumber(sharedPreferenceManager.getCurrentUser().getMRNumber());

        new WebServices(getBaseActivity(),
                getToken(), BaseURLTypes.AHFA_BASE_URL)
                .webServiceUploadFileAPI(WebServiceConstants.METHOD_USER_UPLOAD_PROFILE_PICTURE,
                        uploadFilePath,
                        FileType.IMAGE,
                        requestModel.toString(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                AddUpdateModel addUpdateModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, AddUpdateModel.class);
                                if (addUpdateModel.getStatus()) {
                                    setImageAfterResult(uploadFileUriPath);
                                    getCardDetailService();
                                }

                                UIHelper.showToast(getContext(), addUpdateModel.getMessage());
                            }

                            @Override
                            public void onError() {

                            }
                        });


    }


    private void getCardDetailService() {
        CardMemberDetail cardMemberDetail = new CardMemberDetail(sharedPreferenceManager.getString(AppConstants.KEY_CARD_NUMBER));

        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL).webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_CARD_MEMBER,
                cardMemberDetail.toString(),
                new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        CardMemberDetail cardMemberDetail = GsonFactory.getSimpleGson().fromJson(webResponse.result, CardMemberDetail.class);
                        notifyToAll(Events.ON_CARD_MODEL_UPDATE, cardMemberDetail);
                    }

                    @Override
                    public void onError() {
                        UIHelper.showShortToastInCenter(getContext(), "Something went wrong!");
                    }
                });

    }

    @Override
    public void onNewPacket(int event, Object data) {
        switch (event) {
            case Events.ON_EDIT_PROFILE_INFO:
                setData((UserDetailModel) data);
                break;
        }
    }


}
