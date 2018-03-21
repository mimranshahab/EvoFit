package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.libraries.imageloader.ImageLoaderHelper;
import edu.aku.akuh_health_first.models.receiving_model.CardMemberDetail;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/19/2018.
 */

public class ProfileFragment extends BaseFragment {

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
        setData();
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    private void setData() {
        UserDetailModel currentUser = sharedPreferenceManager.getCurrentUser();
        CardMemberDetail cardMemberDetail = sharedPreferenceManager.getCardMemberDetail();
        txtUserName.setText(currentUser.getName());
        txtAge.setText(currentUser.getAge() + " Y Old | " + currentUser.getGenderDescription());
        txtEmailAddress.setText(currentUser.getEmailAddress());
        txtMRN.setText(currentUser.getMRNumber());
        txtCincNumber.setText(currentUser.getCNICNumber());
        txtAddress.setText(currentUser.getCurrentAddress());
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
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
        titleBar.showHome(getBaseActivity());

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

    @OnClick({R.id.btnCamera, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCamera:

                break;
            case R.id.fab:

                break;
        }
    }
}
