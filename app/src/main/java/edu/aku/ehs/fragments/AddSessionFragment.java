package edu.aku.ehs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.ehs.R;
import edu.aku.ehs.fragments.abstracts.BaseFragment;
import edu.aku.ehs.helperclasses.ui.helper.UIHelper;
import edu.aku.ehs.managers.DateManager;
import edu.aku.ehs.widget.TitleBar;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by hamza.ahmed on 7/19/2018.
 */

public class AddSessionFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.edtSessionName)
    ExtendedEditText edtSessionName;
    @BindView(R.id.txtStartDate)
    ExtendedEditText txtStartDate;
    @BindView(R.id.tfStartDate)
    TextFieldBoxes tfStartDate;
    @BindView(R.id.txtEndDate)
    ExtendedEditText txtEndDate;
    @BindView(R.id.tfEndDate)
    TextFieldBoxes tfEndDate;

    public static AddSessionFragment newInstance() {

        Bundle args = new Bundle();
        AddSessionFragment fragment = new AddSessionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getDrawerLockMode() {
        return 0;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_add_session;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Add Session");
        titleBar.showHome(getBaseActivity());
        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public void setListeners() {

        txtStartDate.setOnFocusChangeListener((view, b) ->
        { if (b) tfStartDate.performClick(); });

        txtEndDate.setOnFocusChangeListener((view, b) -> {
            if (b) tfEndDate.performClick(); });
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

    @OnClick({R.id.tfStartDate, R.id.txtStartDate, R.id.txtEndDate, R.id.tfEndDate, R.id.btnDone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tfStartDate:
            case R.id.txtStartDate:
                DateManager.showDatePicker(getContext(), txtStartDate, null, false);
                break;

            case R.id.tfEndDate:
            case R.id.txtEndDate:
                DateManager.showDatePicker(getContext(), txtEndDate, null, false);
                break;

            case R.id.btnDone:
                UIHelper.showToast(getContext(), "Done button pressed");
                break;
        }
    }
}
