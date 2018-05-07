package edu.aku.family_hifazat.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import edu.aku.family_hifazat.adapters.recyleradapters.GlucoseHistoryAdapter;
import edu.aku.family_hifazat.fragments.GlucoseFragment;
import edu.aku.family_hifazat.fragments.GlucoseHistoryFragment;
import edu.aku.family_hifazat.models.PatientHealthSummaryModel;

public class GlucoseTabAdapter extends FragmentStatePagerAdapter {


    private PatientHealthSummaryModel modelGLUF;
    private PatientHealthSummaryModel modelGLUR;

    public GlucoseTabAdapter(FragmentManager fm, PatientHealthSummaryModel modelGLUF, PatientHealthSummaryModel modelGLUR) {
        super(fm);
        this.modelGLUF = modelGLUF;
        this.modelGLUR = modelGLUR;
    }

    // CURRENT FRAGMENT

    private SparseArray<Fragment> registeredFragments = new SparseArray<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        registeredFragments.remove(position);
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }


    @Override
    public Fragment getItem(int position) {
//        return ForgotPasswordFragment.newInstance();
        switch (position) {
            case 0:
                return GlucoseFragment.newInstance(modelGLUF, modelGLUR);
            case 1:
                return GlucoseHistoryFragment.newInstance(modelGLUF, modelGLUR);
            default:
                return GlucoseFragment.newInstance(modelGLUF, modelGLUR);
        }

    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        if (position == 0) {
            return "Latest";
        } else {
            return "History";
        }

    }
}