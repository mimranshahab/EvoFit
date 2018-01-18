package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
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

    @OnClick({R.id.btnClinicalLab, R.id.btnRadiology, R.id.btnMedProf, R.id.btnImmunizationProfile, R.id.btnCardio, R.id.btnNeurophysiology, R.id.btnEndoscopy, R.id.btnDischargeSummary})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClinicalLab:
                UIHelper.showToast(getBaseActivity(), getString(R.string.will_be_implemented_soon));
                break;
            case R.id.btnRadiology:
                UIHelper.showToast(getBaseActivity(), getString(R.string.will_be_implemented_soon));
                break;
            case R.id.btnMedProf:
                UIHelper.showToast(getBaseActivity(), getString(R.string.will_be_implemented_soon));
                break;
            case R.id.btnImmunizationProfile:
                UIHelper.showToast(getBaseActivity(), getString(R.string.will_be_implemented_soon));
                break;
            case R.id.btnCardio:
                UIHelper.showToast(getBaseActivity(), getString(R.string.will_be_implemented_soon));
                break;
            case R.id.btnNeurophysiology:
                getBaseActivity().addDockableFragment(NeurophysiologyFragment.newInstance());

                break;
            case R.id.btnEndoscopy:
                UIHelper.showToast(getBaseActivity(), getString(R.string.will_be_implemented_soon));
                break;
            case R.id.btnDischargeSummary:
                UIHelper.showToast(getBaseActivity(), getString(R.string.will_be_implemented_soon));
                break;
        }
    }
}