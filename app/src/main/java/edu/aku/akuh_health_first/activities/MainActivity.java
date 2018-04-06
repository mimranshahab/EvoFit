package edu.aku.akuh_health_first.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.fragments.LoginFragment;
import edu.aku.akuh_health_first.managers.SharedPreferenceManager;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initFragments();
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
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


    private void initFragments() {

        if (SharedPreferenceManager.getInstance(getApplicationContext()).getString(AppConstants.KEY_CARD_NUMBER) == null
                || SharedPreferenceManager.getInstance(getApplicationContext()).getString(AppConstants.KEY_CARD_NUMBER).isEmpty()) {
            replacePermanentFramgment(LoginFragment.newInstance(),false);
         } else {
            openActivity(HomeActivity.class);
            this.finish();
        }

    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {

            if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawer(Gravity.START);
            } else {
                super.onBackPressed();
            }

        } else {
            closeApp();
        }
    }


}