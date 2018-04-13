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
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;

/**
 * Created by hamza.ahmed on 1/19/2018.
 */

public class CardSubscriptionFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.contBasic)
    LinearLayout contBasic;
    @BindView(R.id.contSilver)
    LinearLayout contSilver;
    @BindView(R.id.contGold)
    LinearLayout contGold;
    @BindView(R.id.contPlatinum)
    LinearLayout contPlatinum;

    public static CardSubscriptionFragment newInstance() {

        Bundle args = new Bundle();

        CardSubscriptionFragment fragment = new CardSubscriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_card_subscription_v1;
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
//        contParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int a = 0;
//            }
//        });
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
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.contBasic, R.id.contSilver, R.id.contGold, R.id.contPlatinum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contBasic:

                break;
            case R.id.contSilver:
                break;
            case R.id.contGold:
                break;
            case R.id.contPlatinum:
                break;
        }
    }
}
