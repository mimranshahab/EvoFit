package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericDialogFragment;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.model.wrappers.WebResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khanhamza on 09-May-17.
 */

public class ForgotPasswordFragment extends BaseFragment implements GenericClickableInterface {

    @BindView(R.id.txtEmailAddress)
    TextView tvEmailAddress;
    @BindView(R.id.edEmailAddress)
    FormEditText edEmailAddress;
    @BindView(R.id.btnSubmit)
    AnyTextView imgBtnSubmit;
    Unbinder unbinder;
    Call<WebResponse<Object>> forgetPasswordCall;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_forgot_password;
    }


    public static ForgotPasswordFragment newInstance() {
        Bundle args = new Bundle();
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {

        titleBar.resetViews();
        titleBar.setTitle(getString(R.string.forgot_password));
        titleBar.showBackButton(getMainActivity());
        titleBar.setVisibility(View.VISIBLE);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {
        if (edEmailAddress.testValidity()) {
            forgotClick();
        }
    }

    private void showDialog(String message) {

        final GenericDialogFragment genericDialogFragment = GenericDialogFragment.newInstance();
        genericDialogFragment.setTitle(getString(R.string.reset));
        genericDialogFragment.setMessage(message);
        genericDialogFragment.setButton1(getString(R.string.Ok), new GenericClickableInterface() {
            @Override
            public void click() {
                genericDialogFragment.getDialog().dismiss();
                popBackStack();
            }
        });
        genericDialogFragment.show(getFragmentManager(), null);
    }

    @Override
    public void click() {

    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());

    }

    private void forgotClick() {
        imgBtnSubmit.setEnabled(false);
        forgetPasswordCall = WebServiceFactory.getInstance("").postForgotPassword(edEmailAddress.getText().toString().trim());
        forgetPasswordCall.enqueue(new Callback<WebResponse<Object>>() {
            @Override
            public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                imgBtnSubmit.setEnabled(true);
                if (response.body().isSuccess()) {
                    showDialog(response.body().message);
                } else {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                if (!forgetPasswordCall.isCanceled()) {
                    imgBtnSubmit.setEnabled(true);
                    t.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (forgetPasswordCall != null) {
            forgetPasswordCall.cancel();
        }
        super.onDestroy();
    }
}
