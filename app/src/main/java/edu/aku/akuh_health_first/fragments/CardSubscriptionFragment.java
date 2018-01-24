package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.models.receiving_model.CardMemberDetail;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by hamza.ahmed on 1/19/2018.
 */

public class CardSubscriptionFragment extends BaseFragment {
    @BindView(R.id.txtUserName)
    AnyTextView txtUserName;
    @BindView(R.id.txtCardType)
    AnyTextView txtCardType;
    @BindView(R.id.txtCardNum)
    AnyTextView txtCardNum;
    @BindView(R.id.txtCreationDate)
    AnyTextView txtCreationDate;
    @BindView(R.id.txtExpirayDate)
    AnyTextView txtExpirayDate;
    @BindView(R.id.txtEmailAddress)
    AnyTextView txtEmailAddress;
    @BindView(R.id.contListItem)
    LinearLayout contListItem;
    @BindView(R.id.contParent)
    LinearLayout contParent;
    Unbinder unbinder;

    public static CardSubscriptionFragment newInstance() {

        Bundle args = new Bundle();

        CardSubscriptionFragment fragment = new CardSubscriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_card_subscription;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Card Subscription");
        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public void setListeners() {
        contParent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setData();
    }

    private void setData() {
        CardMemberDetail cardMemberDetail = sharedPreferenceManager.getObject(AppConstants.KEY_CARD_MEMBER_DETAIL, CardMemberDetail.class);

        txtUserName.setText(cardMemberDetail.getCardHolderName().toUpperCase());
        txtCardType.setText(cardMemberDetail.getCardTypeDescription());
        txtCardNum.setText(cardMemberDetail.getCardNumber());
        txtCreationDate.setText(cardMemberDetail.getCardCreationDateTime());
        txtExpirayDate.setText(cardMemberDetail.getCardExpiryDateTime());
        txtEmailAddress.setText(cardMemberDetail.getCardEmailAddress());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
