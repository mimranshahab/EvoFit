package edu.aku.family_hifazat.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.Gravity;

import java.util.List;

import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.fragments.HomeFragment;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;


public class HomeActivity extends BaseActivity {


    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initFragments();
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

    private void initFragments() {
        replacePermanentFramgment(HomeFragment.newInstance(), true);
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

//                FragmentManager.BackStackEntry backStackEntryAt = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1);
//                String fragmentName  = backStackEntryAt.getName();
//                getSupportFragmentManager().getFragment()
                fragment.setTitlebar(titleBar);
            }
        } else {
            closeApp();
        }
    }


}