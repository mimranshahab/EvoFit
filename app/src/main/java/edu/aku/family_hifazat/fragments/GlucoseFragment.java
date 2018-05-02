package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by aqsa.sarwar on 4/26/2018.
 */

public class GlucoseFragment extends BaseFragment {
    @BindView(R.id.txtRandomGlucose)
    AnyTextView txtRandomGlucose;
    @BindView(R.id.txtFastingGlucose)
    AnyTextView txtFastingGlucose;
    @BindView(R.id.txtSugarDDTM)
    AnyTextView txtSugarDDTM;
    @BindView(R.id.cardBloodGlucose)
    CardView cardBloodGlucose;
    @BindView(R.id.btnRecordMenually)
    AnyTextView btnRecordMenually;
    @BindView(R.id.txtGLUR)
    AnyTextView txtGLUR;
    @BindView(R.id.btnGLUFasting)
    ImageView btnGLUFasting;
    @BindView(R.id.btnGLURandom)
    ToggleButton btnGLURandom;
    @BindView(R.id.btnUpdate)
    AnyTextView btnUpdate;
    Unbinder unbinder;

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    public static GlucoseFragment newInstance() {

        Bundle args = new Bundle();

        GlucoseFragment fragment = new GlucoseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_blood_glucose;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Health Summary");
        titleBar.showHome(getBaseActivity());
        titleBar.showBackButton(getBaseActivity());
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

    @OnClick({R.id.btnGLUFasting, R.id.btnGLURandom, R.id.btnUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGLUFasting:
                break;
            case R.id.btnGLURandom:
                btnGLURandom.setBackgroundDrawable(getResources().getDrawable(R.drawable.gluco_random_selected));
                break;
            case R.id.btnUpdate:
                break;
        }
    }
}
