package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.validator.MobileNumberValidation;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.wrappers.WebResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shehrozmirza on 5/10/2017.
 */

public class VerifyYourNumberFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.txtCountry)
    AnyTextView txtCountry;
    @BindView(R.id.txtSpinner)
    AnyTextView txtSpinner;
    @BindView(R.id.spCountryList)
    Spinner spCountryList;
    @BindView(R.id.txtMobileNumber)
    AnyTextView txtMobileNumber;
    @BindView(R.id.edMobileNumber)
    FormEditText edMobileNumber;
    @BindView(R.id.btnSubmit)
    AnyTextView btnSubmit;
    @BindView(R.id.txtCountryCode)
    AnyTextView txtCountryCode;
    @BindView(R.id.edtCountryCode)
    FormEditText edtCountryCode;
    private ArrayList<String> arrCountries;
    private ArrayAdapter adaptCountry;
    private Call<WebResponse<Object>> callSendVerifyCode;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_verifynumber;
    }

    public static VerifyYourNumberFragment newInstance() {
        Bundle args = new Bundle();
        VerifyYourNumberFragment fragment = new VerifyYourNumberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.verifyYourNumber));
        titleBar.showBackButton(getMainActivity());
    }

    @Override
    public void setListeners() {
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrCountries = new ArrayList();
        arrCountries.clear();

        setSpinnerArray();
        adaptCountry = new ArrayAdapter<>(getMainActivity(),
                android.R.layout.simple_list_item_1, arrCountries);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edMobileNumber.addValidator(new MobileNumberValidation());
        setSpinner(adaptCountry, txtSpinner, spCountryList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtSpinner, R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtSpinner:
                spCountryList.performClick();
                break;
            case R.id.btnSubmit:
                if (edtCountryCode.testValidity() && edMobileNumber.testValidity()) {
                    setCallSendVerifyCode();
                }
                break;
        }
    }

    private void setSpinnerArray() {
        arrCountries.add("United Arab Emirates (971)");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());

    }


    private void setCallSendVerifyCode() {
        callSendVerifyCode = WebServiceFactory.getInstance(prefHelper.getUserToken()).sendVerificationCode(edtCountryCode.getText().toString().trim(),
                edMobileNumber.getText().toString().trim(), prefHelper.getUserID());
        callSendVerifyCode
                .enqueue(new Callback<WebResponse<Object>>() {
                    @Override
                    public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {

                        if (response == null || response.body() == null) {
                            return;
                        }
                        if (response.body().isSuccess()) {
                            UIHelper.showToast(getContext(), response.body().message);
                            if (getMainActivity() != null && edtCountryCode != null && edMobileNumber != null) {
                                getMainActivity().addDockableFragment(VerifyNumberCodeFragment.newInstance(edtCountryCode.getText().toString().trim(), edMobileNumber.getText().toString().trim()));
                            }
                        } else {
                            UIHelper.showToast(getContext(), response.body().message);
                        }
                    }

                    @Override
                    public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                        if (!callSendVerifyCode.isCanceled()) {
                            t.printStackTrace();
                        }
                    }
                });
    }


    @Override
    public void onDestroy() {
        if (callSendVerifyCode != null) {
            callSendVerifyCode.cancel();
        }
        super.onDestroy();
    }
}
