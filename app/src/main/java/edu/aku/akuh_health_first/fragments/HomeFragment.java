package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
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
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.HomeAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
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
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/16/2018.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {


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
    private ArrayList<UserDetailModel> arrUserLists = new ArrayList<>();
    UserDetailModel subscriber;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrUserLists.clear();
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
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerHome.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerHome.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerHome.setLayoutAnimation(animation);
//        recyclerHome.setItemAnimator(new DefaultItemAnimator());
        recyclerHome.setAdapter(adaptHome);
        serviceCall();

    }

    private void setData() {

        ImageLoaderHelper.loadImageWithConstantHeaders(getContext(), imgUser, subscriber.getProfileImage());
        txtName.setText(subscriber.getName());
        txtGenderAge.setText(subscriber.getGenderDescription() + "/" + subscriber.getAge());
        txtMRN.setText(subscriber.getMRNumber());
    }

    private void serviceCall() {
        CardMemberDetail cardMemberDetail = new CardMemberDetail(WebServiceConstants.tempCardNumber);

        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL).webServiceRequestAPIForJsonObject(WebServiceConstants.METHOD_CARD_MEMBER,
                cardMemberDetail.toString(),
                new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        CardMemberDetail cardMemberDetail = GsonFactory.getSimpleGson().fromJson(webResponse.result, CardMemberDetail.class);
                        arrUserLists.clear();
//                        arrUserLists.add(cardMemberDetail.getSubscriber());
                        arrUserLists.addAll(cardMemberDetail.getFamilyMembersList());

                        UserDetailModel currentUser = sharedPreferenceManager.getCurrentUser();
                        if (currentUser == null && arrUserLists.size() > 0) {
                            sharedPreferenceManager.putObject(AppConstants.KEY_CURRENT_USER_MODEL, arrUserLists.get(0));
                            arrUserLists.get(0).setSelected(true);
                        } else if (currentUser != null && arrUserLists.size() > 0) {
                            for (int i = 0; i < arrUserLists.size(); i++) {
                                if (arrUserLists.get(i).getMRNumber().equals(currentUser.getMRNumber())) {
                                    arrUserLists.get(i).setSelected(true);
                                    break;
                                }
                            }
                        } else {
                            arrUserLists.get(0).setSelected(true);
                        }
                        subscriber = arrUserLists.get(0);
                        arrUserLists.remove(0);

                        sharedPreferenceManager.putObject(AppConstants.KEY_CARD_MEMBER_DETAIL, cardMemberDetail);
                        adaptHome.notifyDataSetChanged();

                        if (subscriber != null) {
                            setData();
                        }


                    }

                    @Override
                    public void onError() {
                        UIHelper.showShortToastInCenter(getContext(), "Something went wrong!");
                    }
                });

    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.showSidebar(getBaseActivity());
        titleBar.setTitle("Home");
        titleBar.setRightButton(R.drawable.notification_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().addDockableFragment(NotificationFragment.newInstance());
            }
        });

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
            for (UserDetailModel userDetailModel : arrUserLists) {
                userDetailModel.setSelected(false);
            }
            arrUserLists.get(position).setSelected(true);
            sharedPreferenceManager.putObject(AppConstants.KEY_CURRENT_USER_MODEL, object);
            adaptHome.notifyDataSetChanged();
            notifyToAll(Events.ON_CURRENT_USER_CHANGED, object);
            getBaseActivity().addDockableFragment(HomeDetailFragment.newInstance());
        }
    }

    @OnClick(R.id.contListItem)
    public void onViewClicked() {
        getBaseActivity().addDockableFragment(HomeDetailFragment.newInstance());
//                getBaseActivity().addDockableFragment(HomeDetailFragment.newInstance());

//        i++;
//        if (i > 6) {
//            i = 1;
//        }
//
//        switch (i) {
//            case 1:
//                contParentLayout.setBackgroundResource(R.drawable.test1);
//                break;
//
//            case 2:
//                contParentLayout.setBackgroundResource(R.drawable.test2);
//                break;
//
//            case 3:
//                contParentLayout.setBackgroundResource(R.drawable.test3);
//                break;
//
//            case 4:
//                contParentLayout.setBackgroundResource(R.drawable.test4);
//                break;
//
//            case 5:
//                contParentLayout.setBackgroundResource(R.drawable.test5);
//                break;
//
//            case 6:
//                contParentLayout.setBackgroundResource(R.drawable.test6);
//                break;
//        }

    }

//    int i = 1;
}
