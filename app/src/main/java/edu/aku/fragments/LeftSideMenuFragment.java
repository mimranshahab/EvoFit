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
import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.libraries.imageloader.LazyLoading;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by khanhamza on 09-May-17.
 */

public class LeftSideMenuFragment extends BaseFragment {


    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;
    @BindView(R.id.txtNotifications)
    public AnyTextView txtNotifications;
    @BindView(R.id.contNotifications)
    LinearLayout contNotifications;
    @BindView(R.id.txtOrderHistory)
    AnyTextView txtOrderHistory;
    @BindView(R.id.txtFavorite)
    AnyTextView txtFavorite;
    @BindView(R.id.txtSetting)
    AnyTextView txtSetting;
    @BindView(R.id.txtContactUs)
    AnyTextView txtContactUs;
    @BindView(R.id.txtAboutApp)
    AnyTextView txtAboutApp;
    @BindView(R.id.txtLogout)
    AnyTextView txtLogout;
    Unbinder unbinder;
    @BindView(R.id.txtAddresses)
    AnyTextView txtAddresses;
    @BindView(R.id.txtUserName)
    AnyTextView txtUserName;
    @BindView(R.id.contUserName)
    LinearLayout contUserName;
    @BindView(R.id.txtHome)
    AnyTextView txtHome;
    @BindView(R.id.txtLanguage)
    AnyTextView txtLanguage;
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


        if (prefHelper.getUser() != null) {
            txtUserName.setText(prefHelper.getUser().userName);

            if (prefHelper.getUser().userProfilePictureURL != null) {
                ImageLoader.getInstance().displayImage(prefHelper.getUser().userProfilePictureURL, imgProfile, LazyLoading.options);
            }
//        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.user_image, imgProfile, LazyLoading.options);
        }

//        scrollToTop();
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


    @OnClick({R.id.txtHome, R.id.contUserName, R.id.contNotifications, R.id.txtAddresses, R.id.txtOrderHistory, R.id.txtFavorite, R.id.txtSetting, R.id.txtContactUs, R.id.txtAboutApp, R.id.txtLogout, R.id.txtLanguage})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.txtHome:
                closeMenu();
                emptyBackStack();
                getMainActivity().addDockableFragment(HomeFragment.newInstance());
                break;

            case R.id.contUserName:
                closeMenu();

                break;
            case R.id.contNotifications:
                closeMenu();
                getMainActivity().addDockableFragment(NotificationFragment.newInstance());
                break;
            case R.id.txtOrderHistory:
                closeMenu();
//                getMainActivity().addDockableFragment(OrderHistoryTabFragment.newInstance());
                break;
            case R.id.txtFavorite:
                closeMenu();
//                getMainActivity().addDockableFragment(FavoriteFragment.newInstance());
                break;
            case R.id.txtSetting:
                closeMenu();
                getMainActivity().addDockableFragment(SettingFragment.newInstance());
                break;
            case R.id.txtAddresses:
                closeMenu();
                getMainActivity().addDockableFragment(MyAddressesFragment.newInstance());
                break;
            case R.id.txtContactUs:
                closeMenu();
                break;
            case R.id.txtAboutApp:
                closeMenu();
                getMainActivity().addDockableFragment(AboutAppFragment.newInstance());
                break;

            case R.id.txtLanguage:
                closeMenu();
                final GenericDialogFragment genericDialogFragment = new GenericDialogFragment();
                genericPopUp(genericDialogFragment, getString(R.string.language), getString(R.string.select_language), "English", "عربى", new GenericClickableInterface() {
                    @Override
                    public void click() {
                        genericDialogFragment.dismiss();
                        prefHelper.putLang(getMainActivity(), "en", false);

                    }
                }, new GenericClickableInterface() {
                    @Override
                    public void click() {
                        genericDialogFragment.dismiss();
                        prefHelper.putLang(getMainActivity(), "ar", false);
                    }
                });
                break;

            case R.id.txtLogout:
                closeMenu();
                logoutClick();
                break;
        }
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


}
