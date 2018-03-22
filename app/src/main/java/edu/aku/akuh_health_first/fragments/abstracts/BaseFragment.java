package edu.aku.akuh_health_first.fragments.abstracts;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.BaseActivity;
import edu.aku.akuh_health_first.adapters.MySpinnerAdapter;
import edu.aku.akuh_health_first.callbacks.OnNewPacketReceivedListener;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.helperclasses.ui.helper.KeyboardHide;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.BaseApplication;

import java.io.File;
import java.text.NumberFormat;

import edu.aku.akuh_health_first.managers.DateManager;
import edu.aku.akuh_health_first.managers.FileManager;
import edu.aku.akuh_health_first.managers.SharedPreferenceManager;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static edu.aku.akuh_health_first.constatnts.Events.ON_SELECTED_USER_UPDATE;


/**
 * Created by khanhamza on 10-Feb-17.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, OnNewPacketReceivedListener {

    protected View view;
    public SharedPreferenceManager sharedPreferenceManager;
    public String TAG = "Logging Tag";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceManager = SharedPreferenceManager.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayout(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBaseActivity().getTitleBar().resetViews();
        getBaseActivity().getDrawerLayout().setDrawerLockMode(getDrawerLockMode());
        getBaseActivity().getDrawerLayout().closeDrawer(Gravity.LEFT);

        subscribeToNewPacket(this);

    }

    public UserDetailModel getCurrentUser() {
        return sharedPreferenceManager.getCurrentUser();
    }

    public abstract int getDrawerLockMode();

    public void setSpinner(ArrayAdapter adaptSpinner, final TextView textView, final Spinner spinner) {
        if (adaptSpinner == null || spinner == null)
            return;
        //selected item will look like a spinner set from XML
//        simple_list_item_single_choice
        adaptSpinner.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adaptSpinner);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = spinner.getItemAtPosition(position).toString();
                if (textView != null)
                    textView.setText(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    protected abstract int getFragmentLayout();

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }


    public void emptyBackStack() {
        FragmentManager fm = getFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
            fm.popBackStack();
        }
    }

    public void popBackStack() {
        getFragmentManager().popBackStack();
    }

    public void popStackTill(int stackNumber) {
        FragmentManager fm = getFragmentManager();
        for (int i = stackNumber; i < fm.getBackStackEntryCount(); i++) {
            fm.popBackStack();
        }
    }

    public abstract void setTitlebar(TitleBar titleBar);


    public abstract void setListeners();

    @Override
    public void onResume() {
        super.onResume();
        setListeners();

        if (getBaseActivity() != null) {
            setTitlebar(getBaseActivity().getTitleBar());
        }

        if (getBaseActivity() != null && getBaseActivity().getWindow().getDecorView() != null) {
            KeyboardHide.hideSoftKeyboard(getBaseActivity(), getBaseActivity().getWindow().getDecorView());
        }
    }

    @Override
    public void onPause() {

        if (getBaseActivity() != null && getBaseActivity().getWindow().getDecorView() != null) {
            KeyboardHide.hideSoftKeyboard(getBaseActivity(), getBaseActivity().getWindow().getDecorView());
        }

        super.onPause();

    }

    public void genericPopUp(GenericDialogFragment genericDialogFragment, String title, String message, String btn1Text, String btn2Text, GenericClickableInterface btn1Interface, GenericClickableInterface btnbtn2Interface) {
        genericDialogFragment.setTitle(title);
        genericDialogFragment.setMessage(message);
        genericDialogFragment.setButton1(btn1Text, btn1Interface);
        genericDialogFragment.setButton2(btn2Text, btnbtn2Interface);
        genericDialogFragment.show(getFragmentManager(), "abcd");
    }


    public String getFormattedNumber(double value, int factor) {

//       DecimalFormat df2 = new DecimalFormat("#,###,###,##0.00");
        NumberFormat format1 = NumberFormat.getInstance();
        format1.setMaximumFractionDigits(factor);
        format1.setMinimumFractionDigits(factor);

        return format1.format(value);

    }

    Disposable subscription;

    protected void notifyToAll(int event, Object data) {
        BaseApplication.getPublishSubject().onNext(new Pair<>(event, data));
    }

    protected void subscribeToNewPacket(final OnNewPacketReceivedListener newPacketReceivedListener) {
        subscription = BaseApplication.getPublishSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pair>() {
                    @Override
                    public void accept(@NonNull Pair pair) throws Exception {
                        Log.e("abc", "on accept");
                        newPacketReceivedListener.onNewPacket((int) pair.first, pair.second);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("abc", "onDestroyView");
        if (subscription != null)
            subscription.dispose();
    }


    public void showNextBuildToast() {
        UIHelper.showToast(getContext(), "This feature will be implemented in next build");
    }


    public void saveAndOpenFile(WebResponse<String> webResponse) {
        String fileName = AppConstants.FILE_NAME + DateManager.getTime(DateManager.getCurrentMillis()) + ".pdf";

        String path = FileManager.writeResponseBodyToDisk(getContext(), webResponse.result, fileName, AppConstants.getUserFolderPath(getContext()), true, true);

//                                final File file = new File(AppConstants.getUserFolderPath(getContext())
//                                        + "/" + fileName + ".pdf");
        final File file = new File(path);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FileManager.openFile(getContext(), file);
            }
        }, 100);
    }

    @Override
    public void onNewPacket(int event, Object data) {
        switch (event) {
            case ON_SELECTED_USER_UPDATE:
                if (data instanceof UserDetailModel) {
                    UserDetailModel userDetailModel = (UserDetailModel) data;
                    getBaseActivity().getTitleBar().setUserDisplay(userDetailModel, getContext());
                }
                break;
        }
    }

//    public ResideMenu getResideMenu() {
//        return getBaseActivity().getResideMenu();
//    }

//
//    public void setResideMenu() {
////        getResideMenu().openMenu(ResideMenu.DIRECTION_LEFT);
////        getResideMenu().closeMenu();
//    }


    // FOR RESIDE MENU
//    public void closeMenu() {
//        getBaseActivity().getResideMenu().closeMenu();
//    }

}
