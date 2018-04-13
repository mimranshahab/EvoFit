package edu.aku.akuh_health_first.fragments;

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
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.HomeAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.callbacks.OnNewPacketReceivedListener;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.constatnts.Events;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.libraries.imageloader.ImageLoaderHelper;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.receiving_model.CardMemberDetail;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.widget.AnyTextView;

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

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeToNewPacket(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerHome.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerHome.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerHome.setLayoutAnimation(animation);
        recyclerHome.setAdapter(adaptHome);
        serviceCall();
    }

    private void setData() {
        if (imgUser == null || txtName == null || txtGenderAge == null || txtMRN == null) {
            return;
        }

        if (subscriber.getProfileImage() == null || subscriber.getProfileImage().isEmpty()) {
            if (subscriber.getGender().equals("F")) {
                imgUser.setImageResource(R.drawable.female_icon_filled);
            } else {
                imgUser.setImageResource(R.drawable.male_icon_filled);
            }
        } else {
            ImageLoaderHelper.loadImageWithConstantHeaders(getContext(), imgUser, subscriber.getProfileImage());
        }

        txtName.setText(subscriber.getName());
        txtGenderAge.setText(subscriber.getGenderDescription() + "/" + subscriber.getAge());
        txtMRN.setText(subscriber.getMRNumber());
    }

    private void serviceCall() {
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
                    }

                    @Override
                    public void onError() {
                        if (sharedPreferenceManager.getCardMemberDetail() == null) {
                            UIHelper.showShortToastInCenter(getContext(), "Something went wrong!");
                        } else {
                            onGetCardSuccessfully(sharedPreferenceManager.getCardMemberDetail());
                        }
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
