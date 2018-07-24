package edu.aku.ehs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.ehs.R;
import edu.aku.ehs.fragments.abstracts.BaseFragment;
import edu.aku.ehs.helperclasses.DateHelper;
import edu.aku.ehs.helperclasses.Helper;
import edu.aku.ehs.helperclasses.ui.helper.UIHelper;
import edu.aku.ehs.managers.DateManager;
import edu.aku.ehs.widget.AnyEditTextView;
import edu.aku.ehs.widget.AnyTextView;
import edu.aku.ehs.widget.TitleBar;

/**
 * Created by hamza.ahmed on 7/19/2018.
 */

public class AddSessionFragment extends BaseFragment {


    @BindView(R.id.edtSessionName)
    AnyEditTextView edtSessionName;
    @BindView(R.id.txtStartDate)
    AnyTextView txtStartDate;
    @BindView(R.id.txtEndDate)
    AnyTextView txtEndDate;
    @BindView(R.id.btnDone)
    Button btnDone;
    Unbinder unbinder;

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

    @OnClick({R.id.txtStartDate, R.id.txtEndDate, R.id.btnDone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtStartDate:
                 DateManager.showDatePicker(getContext(), txtStartDate, null, false );
                break;
            case R.id.txtEndDate:
                DateManager.showDatePicker(getContext(), txtEndDate, null, false );
                break;
            case R.id.btnDone:
                UIHelper.showToast(getContext(), "Done button pressed");
                break;
        }
    }
}
