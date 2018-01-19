package edu.aku.akuh_health_first.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;

import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.CountDownTimerHelper;
import edu.aku.akuh_health_first.helperclasses.ui.helper.KeyboardHide;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.models.CardModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;
import edu.aku.akuh_health_first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;

/**
 * Created by shehrozmirza on 5/10/2017.
 */

public class VerifyNumberCodeFragment extends BaseFragment {


    @BindView(R.id.txtVerificationCode)
    AnyTextView txtVerificationCode;
    @BindView(R.id.edtVerificationCode)
    FormEditText edtVerificationCode;
    @BindView(R.id.txtCountDownTimer)
    AnyTextView txtCountDownTimer;
    @BindView(R.id.btnCancel)
    AnyTextView btnCancel;
    @BindView(R.id.btnConfirm)
    AnyTextView btnConfirm;
    @BindView(R.id.tvResendCode)
    AnyTextView tvResendCode;
    Unbinder unbinder;

    private CountDownTimer countDownTimer;
    private Call<WebResponse<CardModel>> callCodeVerification;
    private Call<WebResponse<Object>> callSendVerifyCode;
    private String phoneNumber;
    private String countryCode;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_verification_code;
    }

    public static VerifyNumberCodeFragment newInstance(String countryCode, String phoneNumber) {
        Bundle args = new Bundle();
        VerifyNumberCodeFragment fragment = new VerifyNumberCodeFragment();
        fragment.countryCode = countryCode;
        fragment.phoneNumber = phoneNumber;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.verificationCode));
        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public void onPause() {
        countDownTimer.cancel();
        super.onPause();
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvResendCode.setPaintFlags(tvResendCode.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        startTimer();
    }

    private void startTimer() {
        countDownTimer = CountDownTimerHelper.runCountDownTime(299, 1, new CountDownTimerHelper.OnCountDownTimer() {
            @Override
            public void onTick(int time) {
                txtCountDownTimer.setText(String.format("%d:%02d", time / 60, time % 60));
            }

            @Override
            public void onFinish() {
                txtCountDownTimer.setText("0:00");
                btnConfirm.setEnabled(false);
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @OnClick({R.id.btnCancel, R.id.btnConfirm, R.id.tvResendCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                popBackStack();
                break;
            case R.id.btnConfirm:
                if (edtVerificationCode.testValidity() && edtVerificationCode.length() > 4) {

                }
                break;
            case R.id.tvResendCode:
//                setCallSendVerifyCode();

                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getBaseActivity(), getView());

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


//    private void setCallSendVerifyCode() {
//        callSendVerifyCode = WebServiceFactory.getInstance(prefHelper.getUser().token).sendVerificationCode(countryCode,
//                phoneNumber, prefHelper.getUserID());
//        callSendVerifyCode
//                .enqueue(new Callback<WebResponse<Object>>() {
//                    @Override
//                    public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
//
//                        if (response == null || response.body() == null) {
//                            return;
//                        }
//                        if (response.body().isSuccess()) {
//                            UIHelper.showToast(getContext(), response.body().message);
//                            countDownTimer.cancel();
//                            btnConfirm.setEnabled(true);
//                            startTimer();
//                        } else {
//                            UIHelper.showToast(getContext(), response.body().message);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
//                        if (!callSendVerifyCode.isCanceled()) {
//                            t.printStackTrace();
//                        }
//                    }
//                });
//    }

}
