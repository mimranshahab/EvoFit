package edu.aku.family_hifazat.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import edu.aku.family_hifazat.fragments.BPFragment;
import edu.aku.family_hifazat.fragments.BPHistoryFragment;
import edu.aku.family_hifazat.models.PatientHealthSummaryModel;

public class BPTabAdapter extends FragmentStatePagerAdapter {


    private PatientHealthSummaryModel modelBPDIASTOLIC;
    private PatientHealthSummaryModel modelBPSYSTOLIC;

    public BPTabAdapter(FragmentManager fm, PatientHealthSummaryModel modelBPDIASTOLIC, PatientHealthSummaryModel modelBPSYSTOLIC) {
        super(fm);
        this.modelBPDIASTOLIC = modelBPDIASTOLIC;
        this.modelBPSYSTOLIC = modelBPSYSTOLIC;
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
                return BPFragment.newInstance(modelBPDIASTOLIC, modelBPSYSTOLIC);
            case 1:
                return BPHistoryFragment.newInstance(modelBPSYSTOLIC, modelBPDIASTOLIC);
            default:
                return BPFragment.newInstance(modelBPDIASTOLIC, modelBPSYSTOLIC);
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