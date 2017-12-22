package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import com.google.gson.JsonObject;

import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.fragments.abstracts.GenericClickableInterface;
import edu.aku.fragments.abstracts.GenericClickableSpan;
import edu.aku.helperclasses.PasswordValidation;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.managers.retrofit.WebServices;
import edu.aku.model.UserModel;
import edu.aku.model.wrappers.WebResponse;
import edu.aku.residemenu.ResideMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static edu.aku.constatnts.WebServiceConstants.temporaryToken;

/**
 * Created by khanhamza on 08-May-17.
 */

public class LoginFragment extends BaseFragment {

    @BindView(R.id.edtEmail)
    FormEditText edtEmail;
    @BindView(R.id.edtPassword)
    FormEditText edtPassword;
    @BindView(R.id.txtForgotPassword)
    AnyTextView txtForgotPassword;
    @BindView(R.id.btnLogin)
    AnyTextView btnLogin;
    @BindView(R.id.txtSignUp)
    AnyTextView txtSignUp;
    Call<WebResponse<UserModel>> loginCall;
    Unbinder unbinder;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.GONE);
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
        edtPassword.addValidator(new PasswordValidation());
        setClickableSpan(txtSignUp);

        getResideMenu().openMenu(ResideMenu.DIRECTION_LEFT);
        closeMenu();


        if (prefHelper.isLanguageArabic()) {
            edtPassword.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        } else {
            edtPassword.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        }

        edtPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
// position the text type in the left top corner
                    edtPassword.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                } else {
// no text entered. Center the hint text.
                    checkLocaleGravity(edtPassword);
                }
            }
        });
    }


    private void setClickableSpan(TextView textView) {
        GenericClickableSpan text1 = new GenericClickableSpan(getMainActivity(), new GenericClickableInterface() {
            @Override
            public void click() {
                getMainActivity().addDockableFragment(SignUpFragment.newInstance());
            }
        });
        text1.setSpannableStringValue(textView, getString(R.string.create_an_account), new SpannableString(textView.getText().toString().trim()));
        text1.setSpan(1.2f);
        text1.setUnderline(false);
        text1.setTextViewWithColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtForgotPassword, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtForgotPassword:
//                getMainActivity().addDockableFragment(ForgotPasswordFragment.newInstance());

                new WebServices(getMainActivity(), temporaryToken).webServiceRequestAPI("UserManager.GetRegisterVM", "", new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        UIHelper.showShortToastInCenter(getContext(), "Successsssssssss");
                    }

                    @Override
                    public void onError() {
                        UIHelper.showShortToastInCenter(getContext(), "failure");
                    }
                });
                break;
            case R.id.btnLogin:
                if (edtEmail.testValidity() && edtPassword.testValidity()) {
                    loginClick();
                }
                break;
        }
    }


    private void loginClick() {
        btnLogin.setEnabled(false);
        loginCall = WebServiceFactory.getInstance("").login(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());

        loginCall.enqueue(new Callback<WebResponse<UserModel>>() {
            @Override
            public void onResponse(Call<WebResponse<UserModel>> call,
                                   Response<WebResponse<UserModel>> response) {
                btnLogin.setEnabled(true);

                if (response == null || response.body() == null) return;

                if (response.body().isSuccess()) {
                    Log.d(TAG, "onResponse Login: " + response.body().result.userID);
                    prefHelper.putUser(response.body().result);

                    if (response.body().result.getIsVerified()) {
                        prefHelper.setGuest(false);
                        prefHelper.putUser(response.body().result);
                        emptyBackStack();
                        getMainActivity().addDockableFragment(HomeFragment.newInstance());
                    } else {
                        UIHelper.showToast(getContext(), "Please verify your account");
                        getMainActivity().addDockableFragment(VerifyYourNumberFragment.newInstance());
                    }

                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<UserModel>> call, Throwable t) {
                if (!loginCall.isCanceled()) {
                    btnLogin.setEnabled(true);
                    t.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (loginCall != null)
            loginCall.cancel();

        super.onDestroy();
    }
}
