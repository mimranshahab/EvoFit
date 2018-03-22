package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;

import edu.aku.akuh_health_first.callbacks.OnNewPacketReceivedListener;
import edu.aku.akuh_health_first.constatnts.Events;
import edu.aku.akuh_health_first.managers.SharedPreferenceManager;
import edu.aku.akuh_health_first.models.receiving_model.CardMemberDetail;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.HomeActivity;
import edu.aku.akuh_health_first.activities.MainActivity;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.fragments.abstracts.GenericClickableInterface;
import edu.aku.akuh_health_first.fragments.abstracts.GenericContentFragment;
import edu.aku.akuh_health_first.fragments.abstracts.GenericDialogFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;

/**
 * Created by khanhamza on 09-May-17.
 */

public class LeftSideMenuFragment extends BaseFragment implements OnNewPacketReceivedListener {

    Unbinder unbinder;
    @BindView(R.id.txtHome)
    AnyTextView txtHome;
    @BindView(R.id.txtCardSubscription)
    AnyTextView txtCardSubscription;
    @BindView(R.id.txtAbout)
    AnyTextView txtAbout;
    @BindView(R.id.txtLogout)
    AnyTextView txtLogout;
    @BindView(R.id.txtSubscriberName)
    AnyTextView txtSubscriberName;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.imgBackground)
    ImageView imgBackground;


    public static LeftSideMenuFragment newInstance() {

        Bundle args = new Bundle();

        LeftSideMenuFragment fragment = new LeftSideMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sidebar;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subscribeToNewPacket(this);
////        scrollToTop();
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {

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
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNDEFINED;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void logoutClick() {
        final GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();

        genericDialogFragment.setTitle(getString(R.string.logout));
        genericDialogFragment.setMessage(getString(R.string.areYouSureToLogout));

        genericDialogFragment.setButton1(getString(R.string.yes), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
                getBaseActivity().clearAllActivitiesExceptThis(MainActivity.class);
//                emptyBackStack();
//                getBaseActivity().addDockableFragment(LoginFragment.newInstance());
            }
        });

        genericDialogFragment.setButton2(getString(R.string.no), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
            }
        });
        genericDialogFragment.show(getFragmentManager(), null);
    }

    public void scrollToTop() {
        scrollView.post(new Runnable() {
            public void run() {
                scrollView.scrollTo(0, 0);
//                scrollView.fullScroll(View.FOCUS_UP);
            }
        });
    }


    @OnClick({R.id.txtHome, R.id.txtCardSubscription, R.id.txtAbout, R.id.txtLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.txtHome:
                if (getActivity() instanceof HomeActivity) {
                    getBaseActivity().reload();
                } else {
                    getBaseActivity().clearAllActivitiesExceptThis(HomeActivity.class);
                }
                break;

            case R.id.txtCardSubscription:
                getBaseActivity().addDockableFragment(CardSubscriptionFragment.newInstance());
                break;

            case R.id.txtAbout:
                getBaseActivity().addDockableFragment(GenericContentFragment.newInstance(getString(R.string.generic), "About"));
                break;
            case R.id.txtLogout:
                logoutClick();
                break;
        }
    }

    @Override
    public void onNewPacket(int event, Object data) {
        switch (event) {
            case Events.ON_CARD_MODEL_GET:
            case Events.ON_CARD_MODEL_UPDATE:
                if (txtSubscriberName != null) {
                    CardMemberDetail cardMemberDetail = (CardMemberDetail) data;
                    txtSubscriberName.setText(cardMemberDetail.getCardHolderName());
                }
                break;
        }

    }

}
