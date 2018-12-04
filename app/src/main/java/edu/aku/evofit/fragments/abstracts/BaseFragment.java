package edu.aku.evofit.fragments.abstracts;

import android.content.Context;
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

import com.gdacciaro.iOSDialog.iOSDialogBuilder;

import edu.aku.evofit.R;
import edu.aku.evofit.activities.BaseActivity;
import edu.aku.evofit.activities.MainActivity;
import edu.aku.evofit.callbacks.OnNewPacketReceivedListener;
import edu.aku.evofit.constatnts.AppConstants;
import edu.aku.evofit.helperclasses.ui.helper.KeyboardHelper;
import edu.aku.evofit.widget.TitleBar;
import edu.aku.evofit.helperclasses.ui.helper.UIHelper;
import edu.aku.evofit.BaseApplication;

import java.io.File;

import edu.aku.evofit.managers.DateManager;
import edu.aku.evofit.managers.FileManager;
import edu.aku.evofit.managers.SharedPreferenceManager;
import edu.aku.evofit.models.receiving_model.UserDetailModel;
import edu.aku.evofit.models.wrappers.WebResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static edu.aku.evofit.constatnts.Events.ON_SELECTED_USER_UPDATE;


/**
 * Created by khanhamza on 10-Feb-17.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, OnNewPacketReceivedListener {

    protected View view;
    public SharedPreferenceManager sharedPreferenceManager;
    public String TAG = "Logging Tag";
    public boolean onCreated = false;
    Disposable subscription;


    /**
     * This is an abstract class, we should inherit our fragment from this class
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceManager = SharedPreferenceManager.getInstance(getContext());
        onCreated = false;
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

    public String getToken() {
        return sharedPreferenceManager.getString(AppConstants.KEY_TOKEN);
    }

    public String getOneTimeToken() {
        return sharedPreferenceManager.getString(AppConstants.KEY_ONE_TIME_TOKEN);
    }

    public void putOneTimeToken(String token) {
        sharedPreferenceManager.putValue(AppConstants.KEY_ONE_TIME_TOKEN, token);
    }

    public abstract int getDrawerLockMode();


    // Use  UIHelper.showSpinnerDialog
    @Deprecated
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
        if (fm == null) return;
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
            fm.popBackStack();
        }
    }

    public void popBackStack() {
        if (getFragmentManager() == null) {
            return;
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void popStackTill(int stackNumber) {
        FragmentManager fm = getFragmentManager();
        if (fm == null) return;
        for (int i = stackNumber; i < fm.getBackStackEntryCount(); i++) {
            fm.popBackStack();
        }
    }

    public abstract void setTitlebar(TitleBar titleBar);


    public abstract void setListeners();

    @Override
    public void onResume() {
        super.onResume();
        onCreated = true;
        setListeners();

        if (getBaseActivity() != null) {
            setTitlebar(getBaseActivity().getTitleBar());
        }

        if (getBaseActivity() != null && getBaseActivity().getWindow().getDecorView() != null) {
            KeyboardHelper.hideSoftKeyboard(getBaseActivity(), getBaseActivity().getWindow().getDecorView());
        }

    }

    @Override
    public void onPause() {

        if (getBaseActivity() != null && getBaseActivity().getWindow().getDecorView() != null) {
            KeyboardHelper.hideSoftKeyboard(getBaseActivity(), getBaseActivity().getWindow().getDecorView());
        }

        super.onPause();

    }


    public void notifyToAll(int event, Object data) {
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
        UIHelper.showToast(getContext(), "This feature is in progress");
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

        }
    }



    public static void logoutClick(final BaseFragment baseFragment) {
        Context context = baseFragment.getContext();

        new iOSDialogBuilder(context)
                .setTitle(context.getString(R.string.logout))
                .setSubtitle(context.getString(R.string.areYouSureToLogout))
                .setBoldPositiveLabel(false)
                .setCancelable(false)
                .setPositiveListener(context.getString(R.string.yes), dialog -> {
                    dialog.dismiss();
                    baseFragment.sharedPreferenceManager.clearDB();
                    baseFragment.getBaseActivity().clearAllActivitiesExceptThis(MainActivity.class);
                })
                .setNegativeListener(context.getString(R.string.no), dialog -> dialog.dismiss())
                .build().show();


    }
}
