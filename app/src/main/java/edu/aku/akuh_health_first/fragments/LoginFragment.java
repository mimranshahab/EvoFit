package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import com.google.gson.JsonObject;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.fragments.abstracts.GenericClickableInterface;
import edu.aku.akuh_health_first.fragments.abstracts.GenericClickableSpan;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.helperclasses.validator.PasswordValidation;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.UserModel;
import edu.aku.akuh_health_first.models.sending_model.LoginApiModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

/**
 * Created by khanhamza on 08-May-17.
 */

public class LoginFragment extends BaseFragment {

    @BindView(R.id.edtUserName)
    FormEditText edtEmail;
    @BindView(R.id.edtPassword)
    FormEditText edtPassword;
    @BindView(R.id.txtForgotPassword)
    AnyTextView txtForgotPassword;
    @BindView(R.id.btnLogin)
    AnyTextView btnLogin;
    @BindView(R.id.txtSignUp)
    AnyTextView txtSignUp;
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
        titleBar.setVisibility(View.VISIBLE);
        titleBar.showSidebar(getBaseActivity());
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

    }


    private void setClickableSpan(TextView textView) {
        GenericClickableSpan text1 = new GenericClickableSpan(getBaseActivity(), new GenericClickableInterface() {
            @Override
            public void click() {
                getBaseActivity().addDockableFragment(RegisterFragment.newInstance());
            }
        });
        text1.setSpannableStringValue(textView, getString(R.string.register_an_account), new SpannableString(textView.getText().toString().trim()));
        text1.setSpan(1.2f);
        text1.setUnderline(true);
        text1.setTextViewWithColor(getResources().getColor(R.color.base_color));
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
                getBaseActivity().addDockableFragment(SlideShowFragment.newInstance());

                break;
            case R.id.btnLogin:

                if (edtEmail.testValidity() && edtPassword.testValidity()) {
                    showNextBuildToast();
                    // FIXME: 1/2/2018 enter live data
                    LoginApiModel loginApiModel = new LoginApiModel(WebServiceConstants.tempUserName,WebServiceConstants.tempPassword);

                    new WebServices(getBaseActivity(), WebServiceConstants.temporaryToken).webServiceRequestAPI(WebServiceConstants.METHOD_USER_GET_USER, loginApiModel.toString(), new WebServices.IRequestJsonDataCallBack() {
                        @Override
                        public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                            UserModel userModel = GsonFactory.getSimpleGson().fromJson(webResponse.result, UserModel.class);
                            UIHelper.showShortToastInCenter(getContext(), webResponse.message);
//                            SharedPreferenceManager.getInstance(getContext()).putObject(AppConstants.KEY_REGISTER_VM, webResponse.result);

                        }

                        @Override
                        public void onError() {
                            UIHelper.showShortToastInCenter(getContext(), "failure");
                        }
                    });


                }
                break;
        }
    }
}
