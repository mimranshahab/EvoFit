package edu.aku.family_hifazat.fragments;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;

import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by hamza.ahmed on 4/26/2018.
 */

public class Slider extends BaseFragment {
    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return 0;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
