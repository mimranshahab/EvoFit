package edu.aku.evofit.fragments.dialogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.evofit.R;
import edu.aku.evofit.helperclasses.ui.helper.KeyboardHelper;
import edu.aku.evofit.managers.SharedPreferenceManager;
import edu.aku.evofit.widget.AnyTextView;
import edu.aku.evofit.widget.PinEntryEditText;

import static edu.aku.evofit.constatnts.AppConstants.KEY_PIN_CODE;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class PinEntryDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.txtPinCode)
    PinEntryEditText txtPinCode;
    @BindView(R.id.txtWrongPinNumber)
    AnyTextView txtWrongPinNumber;
    @BindView(R.id.txtLogout)
    AnyTextView txtLogout;
    @BindView(R.id.contLogout)
    LinearLayout contLogout;
    @BindView(R.id.txtSave)
    AnyTextView txtSave;
    @BindView(R.id.txtCancel)
    AnyTextView txtCancel;
    @BindView(R.id.contButton)
    LinearLayout contButton;
    private String Title;
    private View.OnClickListener onNextButtonClick;
    private View.OnClickListener onLogoutButtonClick;


    public PinEntryDialogFragment() {
    }

    public static PinEntryDialogFragment newInstance(View.OnClickListener onNextButtonClick, View.OnClickListener onLogoutButtonClick) {
        PinEntryDialogFragment frag = new PinEntryDialogFragment();

        frag.onNextButtonClick = onNextButtonClick;
        frag.onLogoutButtonClick = onLogoutButtonClick;

        Bundle args = new Bundle();
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pincode_popup, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindData();

        txtPinCode.setOnPinEnteredListener(str -> {
            String pinCode = SharedPreferenceManager.getInstance(getContext()).getString(KEY_PIN_CODE);
            if (txtPinCode.getText().toString().equals(pinCode)) {
                onNextButtonClick.onClick(view);
                KeyboardHelper.hideSoftKeyboard(getContext(), txtPinCode);
            } else {
                txtWrongPinNumber.setVisibility(View.VISIBLE);
                txtPinCode.setText("");
            }
        });

        KeyboardHelper.showSoftKeyboard(getContext(), txtPinCode);
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

    }

    @Override
    public void onResume() {
        super.onResume();
     }

    private void bindData() {
        txtTitle.setText(getTitle());
        contButton.setVisibility(View.GONE);
        txtWrongPinNumber.setVisibility(View.GONE);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.txtLogout:
                this.dismiss();
                onLogoutButtonClick.onClick(view);
                break;
        }
    }
}

