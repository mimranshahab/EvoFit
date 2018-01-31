package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.views.AnyTextView;

public class HealthHistoryFragment extends BaseFragment {


    @BindView(R.id.btnClinicalLab)
    AnyTextView btnClinicalLab;
    @BindView(R.id.btnRadiology)
    AnyTextView btnRadiology;
    @BindView(R.id.btnMedProf)
    AnyTextView btnMedProf;
    @BindView(R.id.btnImmunizationProfile)
    AnyTextView btnImmunizationProfile;
    @BindView(R.id.btnCardio)
    AnyTextView btnCardio;
    @BindView(R.id.btnNeurophysiology)
    AnyTextView btnNeurophysiology;
    @BindView(R.id.btnEndoscopy)
    AnyTextView btnEndoscopy;
    @BindView(R.id.btnDischargeSummary)
    AnyTextView btnDischargeSummary;
    Unbinder unbinder;
    @BindView(R.id.contLab)
    LinearLayout contLab;
    @BindView(R.id.contRadiology)
    LinearLayout contRadiology;
    @BindView(R.id.contMedicalProfile)
    LinearLayout contMedicalProfile;
    @BindView(R.id.contImmunization)
    LinearLayout contImmunization;
    @BindView(R.id.contCardio)
    LinearLayout contCardio;
    @BindView(R.id.contNeuroPhysiology)
    LinearLayout contNeuroPhysiology;
    @BindView(R.id.contEndo)
    LinearLayout contEndo;
    @BindView(R.id.contSummary)
    LinearLayout contSummary;

    public static HealthHistoryFragment newInstance() {

        Bundle args = new Bundle();

        HealthHistoryFragment fragment = new HealthHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_health_history;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Health History");
        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
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

    @OnClick({R.id.contLab, R.id.contRadiology, R.id.contMedicalProfile, R.id.contImmunization, R.id.contCardio, R.id.contNeuroPhysiology, R.id.contEndo, R.id.contSummary})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contLab:
                getBaseActivity().addDockableFragment(ClinicalLaboratoryFragment.newInstance());
                break;
            case R.id.contRadiology:
                showNextBuildToast();
                break;
            case R.id.contMedicalProfile:
                showNextBuildToast();
                break;
            case R.id.contImmunization:
                showNextBuildToast();
                break;
            case R.id.contCardio:
                getBaseActivity().addDockableFragment(CardiolopulmonaryFragment.newInstance());

                break;
            case R.id.contNeuroPhysiology:
                getBaseActivity().addDockableFragment(NeurophysiologyFragment.newInstance());
                break;
            case R.id.contEndo:
                showNextBuildToast();
                break;
            case R.id.contSummary:
                getBaseActivity().addDockableFragment(DischargeSummaryFragment.newInstance());
                break;
        }
    }
}