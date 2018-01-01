package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ctrlplusz.anytextview.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.libraries.imageloader.LazyLoading;

/**
 * Created by khanhamza on 09-May-17.
 */

public class LeftSideMenuFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;
    @BindView(R.id.txtUserName)
    AnyTextView txtUserName;
    @BindView(R.id.contUserName)
    LinearLayout contUserName;
    @BindView(R.id.txtRegistration)
    AnyTextView txtRegistration;
    @BindView(R.id.txtHealthHistory)
    AnyTextView txtHealthHistory;
    @BindView(R.id.txtMyProfile)
    AnyTextView txtMyProfile;
    @BindView(R.id.txtCardSubscription)
    AnyTextView txtCardSubscription;
    @BindView(R.id.txtCardRenewal)
    AnyTextView txtCardRenewal;
    @BindView(R.id.txtCardUpgrade)
    AnyTextView txtCardUpgrade;
    @BindView(R.id.txtPrintCard)
    AnyTextView txtPrintCard;
    @BindView(R.id.txtAbout)
    AnyTextView txtAbout;
    @BindView(R.id.txtLogout)
    AnyTextView txtLogout;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

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


//        if (prefHelper.getUser() != null) {
//            txtUserName.setText(prefHelper.getUser().userName);
//
//            if (prefHelper.getUser().userProfilePictureURL != null) {
//                ImageLoader.getInstance().displayImage(prefHelper.getUser().userProfilePictureURL, imgProfile, LazyLoading.options);
//            }
////        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.user_image, imgProfile, LazyLoading.options);
//        }
//
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
                prefHelper.removeLocalData();
                emptyBackStack();
                getMainActivity().addDockableFragment(LoginFragment.newInstance());
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


    @OnClick({R.id.contUserName, R.id.txtRegistration, R.id.txtHealthHistory, R.id.txtMyProfile, R.id.txtCardSubscription, R.id.txtCardRenewal, R.id.txtCardUpgrade, R.id.txtPrintCard, R.id.txtAbout, R.id.txtLogout})
    public void onViewClicked(View view) {
        if (getResideMenu().isOpened()) {
            closeMenu();
        }
        switch (view.getId()) {
            case R.id.contUserName:
                break;
            case R.id.txtRegistration:
                break;
            case R.id.txtHealthHistory:
                break;
            case R.id.txtMyProfile:
                break;
            case R.id.txtCardSubscription:
                break;
            case R.id.txtCardRenewal:
                break;
            case R.id.txtCardUpgrade:
                break;
            case R.id.txtPrintCard:
                break;
            case R.id.txtAbout:
                break;
            case R.id.txtLogout:
                logoutClick();
                break;
        }
    }
}
