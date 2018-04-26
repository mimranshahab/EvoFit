package edu.aku.family_hifazat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;

import java.util.List;

import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.fragments.LoginFragment;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.managers.SharedPreferenceManager;


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
            replacePermanentFramgment(LoginFragment.newInstance(), false);
        } else {
            openActivity(HomeActivity.class);
            this.finish();
        }
    }


    @Override
    public void onBackPressed() {
        /**
         * Show Close app popup if no or single fragment is in stack. otherwise check if drawer is open. Close it..
         */

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