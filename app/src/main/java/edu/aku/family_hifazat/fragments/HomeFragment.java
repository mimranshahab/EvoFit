package edu.aku.family_hifazat.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.activities.MainActivity;
import edu.aku.family_hifazat.adapters.recyleradapters.HomeAdapter;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.callbacks.OnNewPacketReceivedListener;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.constatnts.Events;
import edu.aku.family_hifazat.constatnts.WebServiceConstants;
import edu.aku.family_hifazat.enums.BaseURLTypes;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.Helper;
import edu.aku.family_hifazat.managers.SharedPreferenceManager;
import edu.aku.family_hifazat.models.receiving_model.AddUpdateModel;
import edu.aku.family_hifazat.models.sending_model.InsertRegisteredDeviceModel;
import edu.aku.family_hifazat.models.sending_model.RegisteredDeviceModel;
import edu.aku.family_hifazat.widget.TitleBar;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.libraries.imageloader.ImageLoaderHelper;
import edu.aku.family_hifazat.managers.retrofit.GsonFactory;
import edu.aku.family_hifazat.managers.retrofit.WebServices;
import edu.aku.family_hifazat.models.receiving_model.CardMemberDetail;
import edu.aku.family_hifazat.models.receiving_model.UserDetailModel;
import edu.aku.family_hifazat.models.wrappers.WebResponse;
import edu.aku.family_hifazat.widget.AnyTextView;

import static edu.aku.family_hifazat.constatnts.AppConstants.KEY_CODE;
import static edu.aku.family_hifazat.constatnts.AppConstants.KEY_FIREBASE_TOKEN_UPDATED;

/**
 * Created by aqsa.sarwar on 1/16/2018.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener, OnNewPacketReceivedListener {


    @BindView(R.id.listFamilyMembers)
    RecyclerView recyclerHome;
    Unbinder unbinder;
    @BindView(R.id.txtName)
    AnyTextView txtName;
    @BindView(R.id.txtRelation)
    AnyTextView txtRelation;
    @BindView(R.id.txtMRN)
    AnyTextView txtMRN;
    @BindView(R.id.txtGender_age)
    AnyTextView txtGenderAge;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.imgIcon)
    CircleImageView imgUser;
    @BindView(R.id.contListItem)
    RelativeLayout contListItem;
    @BindView(R.id.contParentLayout)
    LinearLayout contParentLayout;
    private HomeAdapter adaptHome;
    private ArrayList<UserDetailModel> arrUserLists;
    UserDetailModel subscriber;
    private String intentData;
    private ProgressDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrUserLists = new ArrayList<>();

        adaptHome = new HomeAdapter(getBaseActivity(), arrUserLists, this);
        subscriber = new UserDetailModel();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    public static HomeFragment newInstance(String intentData) {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.intentData = intentData;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDialog = Helper.getLoader(getContext());
        mDialog.setMessage("Processing your request ...");
        mDialog.setTitle("Please Wait");


        subscribeToNewPacket(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerHome.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerHome.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerHome.setLayoutAnimation(animation);
        recyclerHome.setAdapter(adaptHome);

        RegisteredDeviceModel registeredDevice = AppConstants.getRegisteredDevice(getContext(), getBaseActivity());
        registeredDevice.setLoginCode(sharedPreferenceManager.getString(KEY_CODE));

        if (intentData != null && intentData.equals(AppConstants.ACCESS_LOGIN_DONE)) {
            getCardDetailServiceCall();
        } else {
            if (!(getBaseActivity()).isFinishing()) {
                mDialog.show();
            }
            saveAccessLogCall(registeredDevice);
        }

        if (sharedPreferenceManager.getBoolean(KEY_FIREBASE_TOKEN_UPDATED)) {
            InsertRegisteredDeviceModel insertRegisteredDeviceModel = sharedPreferenceManager.getObject(AppConstants.KEY_INSERT_REGISTERED_DEVICE, InsertRegisteredDeviceModel.class);
            insertRegisteredDevice(insertRegisteredDeviceModel);
        }


    }

    private void getCardDetailServiceCall() {
        final CardMemberDetail cardMemberDetail = new CardMemberDetail(sharedPreferenceManager.getString(AppConstants.KEY_CARD_NUMBER));

        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL).webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_CARD_MEMBER,
                cardMemberDetail.toString(),
                new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        CardMemberDetail cardMemberDetail = GsonFactory.getSimpleGson().fromJson(webResponse.result, CardMemberDetail.class);
                        onGetCardSuccessfully(cardMemberDetail);
                        mDialog.dismiss();
                    }

                    @Override
                    public void onError() {
                        mDialog.dismiss();
                        if (sharedPreferenceManager.getCardMemberDetail() == null) {
                            UIHelper.showShortToastInCenter(getContext(), "Something went wrong!");
                        } else {
                            onGetCardSuccessfully(sharedPreferenceManager.getCardMemberDetail());
                        }
                    }
                });

    }


    private void saveAccessLogCall(RegisteredDeviceModel registeredDeviceModel) {
        new WebServices(getBaseActivity(),
                getToken(),
                BaseURLTypes.AHFA_BASE_URL, false)
                .webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_USER_SAVE_ACCESS_LOG,
                        registeredDeviceModel.toString(),
                        new WebServices.IRequestJsonDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                                AddUpdateModel addUpdateModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, AddUpdateModel.class);
                                if (addUpdateModel.getStatus()) {
                                    getCardDetailServiceCall();
                                } else {
                                    UIHelper.showToast(getContext(), "Session Logout");
                                    sharedPreferenceManager.clearDB();
                                    getBaseActivity().clearAllActivitiesExceptThis(MainActivity.class);
                                }
                            }

                            @Override
                            public void onError() {
                                getCardDetailServiceCall();
                                mDialog.dismiss();
                            }
                        });
    }


    private void onGetCardSuccessfully(CardMemberDetail cardMemberDetail) {

        sharedPreferenceManager.putObject(AppConstants.KEY_CARD_MEMBER_DETAIL, cardMemberDetail);

        arrUserLists.clear();
        arrUserLists.addAll(cardMemberDetail.getFamilyMembersList());

        UserDetailModel selectedUser = sharedPreferenceManager.getCurrentUser();
        if (selectedUser == null && arrUserLists.size() > 0) {
            sharedPreferenceManager.putObject(AppConstants.KEY_CURRENT_USER_MODEL, arrUserLists.get(0));
            arrUserLists.get(0).setSelected(true);

        } else if (selectedUser != null && arrUserLists.size() > 0) {
            for (int i = 0; i < arrUserLists.size(); i++) {
                if (arrUserLists.get(i).getMRNumber().equals(selectedUser.getMRNumber())) {
                    arrUserLists.get(i).setSelected(true);
                    selectedUser = arrUserLists.get(i);
                    sharedPreferenceManager.putObject(AppConstants.KEY_CURRENT_USER_MODEL, selectedUser);
                    break;
                }
            }
        } else {
            arrUserLists.get(0).setSelected(true);
        }


        if (arrUserLists.size() > 0) {
            subscriber = arrUserLists.get(0);
            sharedPreferenceManager.putObject(AppConstants.KEY_CARD_MEMBER_DETAIL, cardMemberDetail);

        }

        notifyToAll(Events.ON_SELECTED_USER_UPDATE, selectedUser);
        adaptHome.notifyDataSetChanged();
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.showSidebar(getBaseActivity());
        titleBar.setTitle("Family Hifazat");
        titleBar.setRightButton(R.drawable.logout_icon, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeftSideMenuFragment.logoutClick(HomeFragment.this);
            }
        });
//        titleBar.setRightButton(R.drawable.notification_icon, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getBaseActivity().addDockableFragment(NotificationFragment.newInstance());
//            }
//        });
    }


    @Override
    public void setListeners() {
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
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

    @Override
    public void onItemClick(int position, Object object) {
        if (object instanceof UserDetailModel) {
            if (getBaseActivity().getSupportFragmentManager().getBackStackEntryCount() == 1) {

                if (getCurrentUser().getMRNumber().equals(((UserDetailModel) object).getMRNumber())) {
                    getBaseActivity().addDockableFragment(HomeDetailFragment.newInstance(), true);
                    return;
                }

                for (UserDetailModel userDetailModel : arrUserLists) {
                    userDetailModel.setSelected(false);
                }
                arrUserLists.get(position).setSelected(true);
                sharedPreferenceManager.putObject(AppConstants.KEY_CURRENT_USER_MODEL, object);
                adaptHome.notifyDataSetChanged();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getBaseActivity().addDockableFragment(HomeDetailFragment.newInstance(), true);
                    }
                }, 500);

            }
        }
    }

    @OnClick(R.id.contListItem)
    public void onViewClicked() {


    }

    private void insertRegisteredDevice(InsertRegisteredDeviceModel insertRegisteredDeviceModel) {
        new WebServices(getContext(),
                SharedPreferenceManager.getInstance(getContext()).getString(AppConstants.KEY_TOKEN),
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIAnyObject(WebServiceConstants.METHOD_USER_INSERT_REGISTERED_DEVICE,
                        insertRegisteredDeviceModel.toString(),
                        new WebServices.IRequestWebResponseAnyObjectCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<Object> webResponse) {
                                boolean isDataInserted = false;
                                if (webResponse.result instanceof Boolean) {
                                    isDataInserted = (boolean) webResponse.result;
                                    sharedPreferenceManager.putValue(KEY_FIREBASE_TOKEN_UPDATED, false);
                                }
                            }

                            @Override
                            public void onError(Object object) {

                            }
                        });
    }


    @Override
    public void onNewPacket(int event, Object data) {
        switch (event) {
            case Events.ON_CARD_MODEL_UPDATE:
                onGetCardSuccessfully((CardMemberDetail) data);
                break;
            case Events.ON_HOME_PRESSED:
                setTitlebar((TitleBar) data);
                break;
        }
    }
}
