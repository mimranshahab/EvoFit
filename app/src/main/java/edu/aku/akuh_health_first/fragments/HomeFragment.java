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

import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.BaseApplication;
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
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.receiving_model.CardMemberDetail;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

/**
 * Created by aqsa.sarwar on 1/16/2018.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {


    @BindView(R.id.listFamilyMembers)
    RecyclerView recyclerHome;
    Unbinder unbinder;
    private HomeAdapter adaptHome;
    private ArrayList<UserDetailModel> arrUserLists = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrUserLists.clear();
        adaptHome = new HomeAdapter(getBaseActivity(), arrUserLists, this);

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

    private void serviceCall() {
        CardMemberDetail cardMemberDetail = new CardMemberDetail(WebServiceConstants.tempCardNumber);

        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL).webServiceRequestAPI(WebServiceConstants.METHOD_CARD_MEMBER,
                cardMemberDetail.toString(),
                new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        CardMemberDetail cardMemberDetail = GsonFactory.getSimpleGson().fromJson(webResponse.result, CardMemberDetail.class);
                        arrUserLists.clear();
                        arrUserLists.add(cardMemberDetail.getSubscriber());
                        arrUserLists.addAll(cardMemberDetail.getFamilyMembersList());

                        UserDetailModel currentUser = sharedPreferenceManager.getCurrentUser();
                        if (currentUser == null && arrUserLists.size() > 0) {
                            sharedPreferenceManager.putObject(AppConstants.KEY_CURRENT_USER_MODEL, arrUserLists.get(0));
                            arrUserLists.get(0).setSelected(true);
                        } else if (currentUser != null && arrUserLists.size() > 0) {
                            for (int i = 0; i < arrUserLists.size(); i++) {
                                if (arrUserLists.get(i).getMRNumber().equals(currentUser.getMRNumber())) {
                                    arrUserLists.get(i).setSelected(true);
                                }
                            }
                        }

                        sharedPreferenceManager.putObject(AppConstants.KEY_CARD_MEMBER_DETAIL, cardMemberDetail);
                        adaptHome.notifyDataSetChanged();

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
        }
    }
}
