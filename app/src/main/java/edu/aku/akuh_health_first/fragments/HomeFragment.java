package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.FamilyMembersAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.enums.WebServiceTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.receiving_model.CardMemberDetail;
import edu.aku.akuh_health_first.models.receiving_model.FamilyMembersList;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/16/2018.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {


    @BindView(R.id.listFamilyMembers)
    RecyclerView listFamilyMembers;
    Unbinder unbinder;
    @BindView(R.id.imgUser)
    ImageView imgUser;
    @BindView(R.id.txtName)
    AnyTextView txtName;
    @BindView(R.id.txtViewProfile)
    AnyTextView txtViewProfile;
    @BindView(R.id.txtMRN)
    AnyTextView txtMRN;
    @BindView(R.id.txtGender)
    AnyTextView txtGender;
    @BindView(R.id.txtAge)
    AnyTextView txtAge;
    @BindView(R.id.txtEmailAddress)
    AnyTextView txtEmailAddress;
    private FamilyMembersAdapter adapterFamilyMembers;
    private ArrayList<FamilyMembersList> arrFamList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        serviceCall();
        adapterFamilyMembers = new FamilyMembersAdapter(getBaseActivity(), arrFamList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        listFamilyMembers.setLayoutManager(mLayoutManager);
        listFamilyMembers.setItemAnimator(new DefaultItemAnimator());
        listFamilyMembers.setAdapter(adapterFamilyMembers);
    }

    private void serviceCall() {
        CardMemberDetail cardMemberDetail = new CardMemberDetail(WebServiceConstants.tempCardNumber);

        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                WebServiceTypes.ONLY_TOKEN,
                BaseURLTypes.AHFA).webServiceRequestAPI(WebServiceConstants.METHOD_CARD_MEMBER,
                cardMemberDetail.toString(),
                new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        CardMemberDetail userModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, CardMemberDetail.class);
                        arrFamList.clear();
                        arrFamList.addAll(userModel.getFamilyMembersList());
                        adapterFamilyMembers.notifyDataSetChanged();
                        setData(userModel);
                    }

                    @Override
                    public void onError() {
                        UIHelper.showShortToastInCenter(getContext(), "failure");
                    }
                });

    }

    private void setData(CardMemberDetail userModel) {

        txtName.setText(userModel.getSubscriber().getName());
        txtAge.setText("Age " + userModel.getSubscriber().getAge());
        txtEmailAddress.setText("EmailAddress " + userModel.getSubscriber().getEmailAddress());
        txtMRN.setText("MR# " + userModel.getSubscriber().getMRNumber());
        txtGender.setText("Gender " + userModel.getSubscriber().getMRNumber());

    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
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
        if (object instanceof FamilyMembersList) {
            FamilyMembersList familyMembersList = (FamilyMembersList) object;
            getBaseActivity().addDockableFragment(HealthHistoryFragment.newInstance());

        }
    }
}
