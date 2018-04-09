package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.TableViewActivity;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.models.LstMicSpecParaResult;
import edu.aku.akuh_health_first.widget.AnyTextView;

import static edu.aku.akuh_health_first.constatnts.AppConstants.KEY_CROSS_TAB_DATA;

/**
 * Created by hamza.ahmed on 3/29/2018.
 */

public class ClinicalParaResultFragment extends BaseFragment {


    @BindView(R.id.txtProcedureDesc)
    AnyTextView txtProcedureDesc;
    @BindView(R.id.txtViewResult)
    AnyTextView txtViewResult;
    @BindView(R.id.txtParaResult)
    AnyTextView txtParaResult;
    Unbinder unbinder;
    private LstMicSpecParaResult micSpecParaResult;
    private String procedureName;
    private String procedureDescription;

    public static ClinicalParaResultFragment newInstance(LstMicSpecParaResult micSpecParaResult, String procedureName, String procedureDescription) {

        Bundle args = new Bundle();

        ClinicalParaResultFragment fragment = new ClinicalParaResultFragment();
        fragment.micSpecParaResult = micSpecParaResult;
        fragment.procedureName = procedureName;
        fragment.procedureDescription = procedureDescription;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtParaResult.setText(Html.fromHtml(micSpecParaResult.getPARARESULT()), TextView.BufferType.SPANNABLE);

        if (micSpecParaResult != null) {
            procedureDescription = procedureDescription.concat(" (" + micSpecParaResult.getPARATYPE() + ")");
        }
        txtProcedureDesc.setText(procedureDescription);

        if (micSpecParaResult.getLstMicSpecOrganism() == null || micSpecParaResult.getLstMicSpecOrganism().isEmpty()) {
            txtViewResult.setVisibility(View.GONE);
        } else {
            txtViewResult.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_clinical_para_result;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(procedureName);
        titleBar.showBackButton(getBaseActivity());
        titleBar.showHome(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
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

    @OnClick(R.id.txtViewResult)
    public void onViewClicked() {
        sharedPreferenceManager.putObject(KEY_CROSS_TAB_DATA, micSpecParaResult);
        getBaseActivity().openActivity(TableViewActivity.class, procedureName);
    }
}
