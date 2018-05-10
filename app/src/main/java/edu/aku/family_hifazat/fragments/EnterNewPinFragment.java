package edu.aku.family_hifazat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.fragments.abstracts.BaseFragment;
import edu.aku.family_hifazat.helperclasses.ui.helper.UIHelper;
import edu.aku.family_hifazat.widget.AnyTextView;
import edu.aku.family_hifazat.widget.PinEntryEditText;
import edu.aku.family_hifazat.widget.TitleBar;

/**
 * Created by hamza.ahmed on 5/9/2018.
 */

public class EnterNewPinFragment extends BaseFragment {
    Unbinder unbinder;

    @BindView(R.id.txtWrongPinNumber)
    AnyTextView txtWrongPinNumber;
    @BindView(R.id.txtPinCode)
    PinEntryEditText txtPinCode;
    @BindView(R.id.txtSave)
    AnyTextView txtSave;
    @BindView(R.id.txtCancel)
    AnyTextView txtCancel;

    private View.OnClickListener onSaveClick;


    public static EnterNewPinFragment newInstance(View.OnClickListener onSaveClick) {

        Bundle args = new Bundle();

        EnterNewPinFragment fragment = new EnterNewPinFragment();
        fragment.onSaveClick = onSaveClick;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_enter_new_pin;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Preferences");
        titleBar.showBackButton(getBaseActivity());
        titleBar.showHome(getBaseActivity());
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    @OnClick({R.id.txtSave, R.id.txtCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtSave:
                if (txtPinCode.getText().toString().length() == 4) {
                    sharedPreferenceManager.putValue(AppConstants.KEY_IS_PIN_ENABLE, true);
                    sharedPreferenceManager.putValue(AppConstants.KEY_PIN_CODE, txtPinCode.getText().toString().trim());
                    onSaveClick.onClick(view);
                    getBaseActivity().onBackPressed();
                } else {
                    UIHelper.showToast(getContext(), "Incorrect Pin");
                }

                break;
            case R.id.txtCancel:
                getBaseActivity().onBackPressed();
                break;
        }
    }
}
