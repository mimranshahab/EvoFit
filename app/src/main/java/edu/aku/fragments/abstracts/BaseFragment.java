package edu.aku.fragments.abstracts;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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

import edu.aku.BaseApplication;
import edu.aku.activities.MainActivity;
import edu.aku.callbacks.OnNewPacketReceivedListener;
import edu.aku.managers.BaseSharedPreferenceManager;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;
import edu.aku.residemenu.ResideMenu;

import java.text.NumberFormat;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by khanhamza on 10-Feb-17.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    protected View view;
    public BaseSharedPreferenceManager prefHelper;
    ProgressDialog progressDialog;

    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //persmission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void showProgress() {
        progressDialog.setCancelable(false);
//        progressDialog.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_states));
//        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.loader ));
        progressDialog.setMessage("Loading");
        progressDialog.show();
    }

    public void dismissProgress() {
        progressDialog.dismiss();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefHelper = new BaseSharedPreferenceManager(getMainActivity());
        progressDialog = new ProgressDialog(getMainActivity());
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
        getMainActivity().getTitleBar().resetViews();
    }

    public void setSpinner(ArrayAdapter adaptSpinner, final TextView textView, final Spinner spinner) {
        if (adaptSpinner == null || spinner == null)
            return;
        //selected item will look like a spinner set from XML
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

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
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

        if (getMainActivity() != null) {
            setTitlebar(getMainActivity().getTitleBar());
        }

        if (getMainActivity() != null && getMainActivity().getWindow().getDecorView() != null) {
            KeyboardHide.hideSoftKeyboard(getMainActivity(), getMainActivity().getWindow().getDecorView());
        }
    }

    @Override
    public void onPause() {

        if (getMainActivity() != null && getMainActivity().getWindow().getDecorView() != null) {
            KeyboardHide.hideSoftKeyboard(getMainActivity(), getMainActivity().getWindow().getDecorView());
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


    public ResideMenu getResideMenu() {
        return getMainActivity().getResideMenu();
    }


    public void setResideMenu() {
        getResideMenu().openMenu(ResideMenu.DIRECTION_LEFT);
        getResideMenu().closeMenu();
    }


    // FOR RESIDE MENU
    public void closeMenu() {
        getMainActivity().getResideMenu().closeMenu();
    }


    protected void checkLocaleGravity(TextView view) {
        if (prefHelper.isLanguageArabic()) {
//arabic (rtl)
            view.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        } else {
//english (ltr)
            view.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        }
    }

}
