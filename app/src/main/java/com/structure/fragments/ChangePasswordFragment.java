package com.structure.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import com.structure.R;
import com.structure.fragments.abstracts.BaseFragment;
import com.structure.helperclasses.PasswordValidation;
import com.structure.helperclasses.ui.helper.KeyboardHide;
import com.structure.helperclasses.ui.helper.TitleBar;
import com.structure.helperclasses.ui.helper.UIHelper;

import com.structure.managers.retrofit.WebServiceFactory;
import com.structure.model.wrappers.WebResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.structure.R.string.changePassword;

/**
 * Created by shehrozmirza on 5/15/2017.
 */

public class ChangePasswordFragment extends BaseFragment {

    @BindView(R.id.txtCurrentPassword)
    AnyTextView txtCurrentPassword;
    @BindView(R.id.edCurrentPassword)
    FormEditText edCurrentPassword;
    @BindView(R.id.txtNewPassword)
    AnyTextView txtNewPassword;
    @BindView(R.id.edNewPassword)
    FormEditText edNewPassword;
    @BindView(R.id.txtPassword)
    AnyTextView txtPassword;
    @BindView(R.id.edConfirmPassword)
    FormEditText edConfirmPassword;
    @BindView(R.id.btnDone)
    AnyTextView btnDone;
    private Unbinder unbinder;
    private Call<WebResponse<Object>> callChangePassword;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_change_password;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(changePassword));
        titleBar.showBackButton(getMainActivity());
    }

    public static ChangePasswordFragment newInstance() {
        Bundle args = new Bundle();
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        fragment.setArguments(args);
        return fragment;
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
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());

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

        edConfirmPassword.addValidator(new PasswordValidation());
        edNewPassword.addValidator(new PasswordValidation());
        edCurrentPassword.addValidator(new PasswordValidation());
        edConfirmPassword.addValidator(new PasswordValidation(edNewPassword));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnDone)
    public void onViewClicked() {
        if (edCurrentPassword.testValidity() && edNewPassword.testValidity() && edConfirmPassword.testValidity()) {
            changePassword();
        }
    }

    private void changePassword() {
        callChangePassword = WebServiceFactory.getInstance(prefHelper.getUser().token)
                .postChangePassword(
                        edCurrentPassword.getText().toString(),
                        edNewPassword.getText().toString(),
                        edConfirmPassword.getText().toString(),
                        prefHelper.getUserID());

        callChangePassword.enqueue(new Callback<WebResponse<Object>>() {
            @Override
            public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                if (response.body().isSuccess()) {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                    emptyBackStack();
                    getMainActivity().addDockableFragment(HomeFragment.newInstance());
                } else {
                    UIHelper.showToast(getMainActivity(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                if (!callChangePassword.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (callChangePassword != null) {
            callChangePassword.cancel();
        }
        super.onDestroy();
    }
}
