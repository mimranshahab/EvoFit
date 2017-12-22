package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.andreabaccega.widget.FormEditText;
import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
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

import static android.content.ContentValues.TAG;

/**
 * Created by shehrozmirza on 5/11/2017.
 */

public class ReportAnIssueFragment extends BaseFragment {


    Unbinder unbinder;
    String selectedValue = "";
    @BindView(R.id.edEmail)
    FormEditText edEmail;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.edtIssueDescription)
    FormEditText edtIssueDescription;
    @BindView(R.id.btnReport)
    AnyTextView btnReport;
    Call<WebResponse<Object>> callReportAnIssue;

    @Override
    protected int getFragmentLayout() {

        return R.layout.fragment_report_an_issue;
    }

    public static ReportAnIssueFragment newInstance() {
        Bundle args = new Bundle();
        ReportAnIssueFragment fragment = new ReportAnIssueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {

        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.report_an_issue));
        titleBar.showBackButton(getMainActivity());
    }

    @Override
    public void setListeners() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                selectedValue = rb.getText().toString();
            }
        });

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

    @OnClick(R.id.btnReport)
    public void onViewClicked() {
        if (!selectedValue.isEmpty() && edEmail.testValidity() && edtIssueDescription.testValidity()) {
            CallReportAnIssue();
        } else {
            UIHelper.showToast(getMainActivity(), "Please fill all fields");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardHide.hideSoftKeyboard(getMainActivity(), getView());
    }

    public void CallReportAnIssue() {
        btnReport.setEnabled(false);

        callReportAnIssue = WebServiceFactory.getInstance(
                prefHelper.getUserToken()).reportAnIssue(prefHelper.getUserID(),
                edEmail.getText().toString(), selectedValue,
                edtIssueDescription.getText().toString());

        Log.d(TAG, "CallReportAnIssue: " + prefHelper.getUserID());

        callReportAnIssue.enqueue(new Callback<WebResponse<Object>>() {
            @Override
            public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> response) {
                btnReport.setEnabled(true);
                if (response.body() == null || response.body().result == null) return;
                if (response.body().isSuccess()) {
                    UIHelper.showToast(getContext(), response.body().message);
                    popBackStack();
                } else {
                    UIHelper.showToast(getContext(), response.body().message);
                }
            }

            @Override
            public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
                if (!callReportAnIssue.isCanceled())
                    t.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroy() {
        if (callReportAnIssue != null) {
            callReportAnIssue.cancel();
        }
        super.onDestroy();
    }
}
