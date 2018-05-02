package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by aqsa.sarwar on 4/26/2018.
 */

public class MeasurementFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.txtBSA)
    AnyTextView txtBSA;
    @BindView(R.id.txtHeight)
    AnyTextView txtHeight;
    @BindView(R.id.txtWeight)
    AnyTextView txtWeight;
    @BindView(R.id.txtMeasureDDTM)
    AnyTextView txtMeasureDDTM;
    @BindView(R.id.cardMeasurement)
    CardView cardMeasurement;
    @BindView(R.id.btnRecordMenually)
    AnyTextView btnRecordMenually;
    @BindView(R.id.txtGLUF)
    AnyTextView txtGLUF;
    @BindView(R.id.contWeight)
    LinearLayout contWeight;
    @BindView(R.id.txtGLUR)
    AnyTextView txtGLUR;
    @BindView(R.id.contHeight)
    LinearLayout contHeight;
    @BindView(R.id.btnSave)
    AnyTextView btnSave;

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    public static MeasurementFragment newInstance() {

        Bundle args = new Bundle();

        MeasurementFragment fragment = new MeasurementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_measurements;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Health Summary");
        titleBar.showHome(getBaseActivity());
        titleBar.showBackButton(getBaseActivity()); titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
