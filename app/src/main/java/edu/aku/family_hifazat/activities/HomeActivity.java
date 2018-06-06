package edu.aku.family_hifazat.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;

import java.util.List;

import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.callbacks.GenericClickableInterface;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.firebase.GcmDataObject;
import edu.aku.family_hifazat.fragments.HomeFragment;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.fragments.abstracts.GenericDialogFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.models.RadiologyModel;

import static edu.aku.family_hifazat.constatnts.AppConstants.ACCESS_LOGIN_DONE;
import static edu.aku.family_hifazat.constatnts.AppConstants.JSON_STRING_KEY;


public class HomeActivity extends BaseActivity {


    NavigationView navigationView;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getExtras() != null && intent.getExtras().get(AppConstants.GCM_DATA_OBJECT) != null) {
            GcmDataObject gcmDataObject = (GcmDataObject) intent.getExtras().get(AppConstants.GCM_DATA_OBJECT);
            UIHelper.showOnlyTextPopup(this, gcmDataObject.getMessage());

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        String intentData = getIntent().getStringExtra(JSON_STRING_KEY);
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().get(AppConstants.GCM_DATA_OBJECT) != null) {
            GcmDataObject gcmDataObject = (GcmDataObject) getIntent().getExtras().get(AppConstants.GCM_DATA_OBJECT);
            UIHelper.showOnlyTextPopup(this, gcmDataObject.getMessage());
        }

        initFragments(intentData);
        navigationView = findViewById(R.id.nav_view);
        navigationView.getBackground().setColorFilter(0x80000000, PorterDuff.Mode.MULTIPLY);
    }


    @Override
    protected int getViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getTitlebarLayoutId() {
        return R.id.titlebar;
    }


    @Override
    protected int getDrawerLayoutId() {
        return R.id.drawer_layout;
    }

    @Override
    protected int getDockableFragmentId() {
        return R.id.contMain;
    }

    @Override
    protected int getDrawerFragmentId() {
        return R.id.contDrawer;
    }

    @Override
    protected int getPermanentViewId() {
        return R.id.contPermanent;
    }

    private void initFragments(String intentData) {
        replacePermanentFramgment(HomeFragment.newInstance(intentData), false);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {

            if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawer(Gravity.START);
            } else {
                super.onBackPressed();
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                BaseFragment fragment = (BaseFragment) fragments.get(fragments.size() - 1);
                fragment.setTitlebar(titleBar);
            }
        } else {
            closeApp();
        }
    }


}