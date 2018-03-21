package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
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
    @BindView(R.id.txtUserName)
    AnyTextView txtUserName;
    @BindView(R.id.txtMRN)
    AnyTextView txtMRN;
    @BindView(R.id.txtEmailAddress)
    AnyTextView txtEmailAddress;
    @BindView(R.id.txtAge)
    AnyTextView txtAge;
    @BindView(R.id.txtCardNum)
    AnyTextView txtCardNum;
    @BindView(R.id.txtCreationDate)
    AnyTextView txtCreationDate;
    @BindView(R.id.txtExpirayDate)
    AnyTextView txtExpirayDate;
    @BindView(R.id.contListItem)
    LinearLayout contListItem;
    @BindView(R.id.cardView2)
    CardView cardView2;
    @BindView(R.id.txtCardType)
    AnyTextView txtCardType;
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
        txtAge.setText("Age " + currentUser.getAge() + "  Gender " + currentUser.getGender());
        txtEmailAddress.setText(currentUser.getEmailAddress());
//        txtExpDate.setText(sharedPreferenceManager.getCurrentUser().);
        txtMRN.setText(currentUser.getMRNumber());
//        txtIssueDate.setText(sharedPreferenceManager.getCurrentUser());
        txtCardType.setText("CardType: " + cardMemberDetail.getCardTypeDescription());
        txtCardNum.setText("CardNumber: " + cardMemberDetail.getCardNumber());
        txtExpirayDate.setText("Expiry date: " + cardMemberDetail.getCardExpiryDateTime());
        txtCreationDate.setText("Issue date: " + cardMemberDetail.getCardCreationDateTime());
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
}
