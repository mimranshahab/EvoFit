package edu.aku.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.model.ContactDetail;
import edu.aku.model.wrappers.WebResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by khanhamza on 20-Feb-17.
 */

public class ContactUsFragment extends BaseFragment {

    LinearLayout layoutFeedback;
    LinearLayout layoutCall;
    LinearLayout layoutReportAnIssue;
    @BindView(R.id.txtPhoneNumber)
    AnyTextView txtPhoneNumber;
    Unbinder unbinder;

    FeedbackFragment feedbackFragment;
    private Call<WebResponse<ContactDetail>> callContact;
    private boolean isWebCallDone;
    public String phoneNumber = "";

    public static ContactUsFragment newInstance() {
        Bundle args = new Bundle();
        ContactUsFragment fragment = new ContactUsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_contact_us;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedbackFragment = feedbackFragment.newInstance(this);
        isWebCallDone = false;
    }

    private void callContact() {

        callContact = WebServiceFactory.getInstance(prefHelper.getUserToken()).getContactDetails(prefHelper.getUserID());
        callContact.enqueue(new Callback<WebResponse<ContactDetail>>() {
            @Override
            public void onResponse(Call<WebResponse<ContactDetail>> call,
                                   Response<WebResponse<ContactDetail>> response) {

                if (response == null || response.body() == null)
                    return;

                if (response.body().isSuccess()) {
                    if (txtPhoneNumber != null && response.body().result != null) {
                        updatePhoneNumber(response.body().result.phoneNumber);
                        phoneNumber = response.body().result.phoneNumber;
                        Log.d(TAG, "onResponse: " + response.body().result.phoneNumber);
                        isWebCallDone = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<WebResponse<ContactDetail>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void updatePhoneNumber(String phoneNumber) {
        txtPhoneNumber.setText(phoneNumber);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews(view);
        setListeners();
        if (isWebCallDone) {
            updatePhoneNumber(phoneNumber);
        } else {
            callContact();
        }

    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.contactUs));
        titleBar.showBackButton(getMainActivity());
    }

    @Override
    public void setListeners() {
        layoutFeedback.setOnClickListener(this);
        layoutCall.setOnClickListener(this);
        layoutReportAnIssue.setOnClickListener(this);
    }

    private void bindViews(View view) {
        layoutFeedback = (LinearLayout) view.findViewById(R.id.layoutFeedback);
        layoutCall = (LinearLayout) view.findViewById(R.id.layoutCall);
        layoutReportAnIssue = (LinearLayout) view.findViewById(R.id.contReportAnIssue);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())

        {
            case R.id.layoutFeedback: {
                getMainActivity().addDockableFragment(feedbackFragment);
                break;
            }
            case R.id.layoutCall: {
                dialPhoneNumber(txtPhoneNumber.getText().toString());
                break;
            }
            case R.id.contReportAnIssue: {
                getMainActivity().addDockableFragment(ReportAnIssueFragment.newInstance());
                break;
            }
        }
    }


    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void sendEmail(String emailAddress) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + emailAddress));
        startActivity(Intent.createChooser(emailIntent, "Send Email From"));
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
    public void onDestroyView() {
        if (callContact != null) {
            callContact.cancel();
        }

        super.onDestroyView();
        unbinder.unbind();
    }


//    private void getContactDetails(boolean isGuest) {
//
//        showProgress();
//        if (isGuest) {
//            WebServiceFactory.getInstance("0").getContactDetails(0)
//                    .enqueue(new Callback<WebResponse<ContactDetailWrapper>>() {
//                        @Override
//                        public void onResponse(Call<WebResponse<ContactDetailWrapper>> call, Response<WebResponse<ContactDetailWrapper>> responseCode) {
//                            if (responseCode.content().isSuccess()) {
//                                contactDetail = responseCode.content().result.contactDetail;
//                                txtEmail.setText(contactDetail.emailAddress);
//                                txtPhoneNumber.setText(contactDetail.phoneNumber);
//                                dismissProgress();
//                            } else {
//                                UIHelper.showToast(getContext(), responseCode.content().message);
//                                dismissProgress();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<WebResponse<ContactDetailWrapper>> call, Throwable t) {
//                            t.printStackTrace();
//                            dismissProgress();
//                        }
//                    });
//
//        } else {
//            WebServiceFactory.getInstance(prefHelper.getUser().token).getContactDetails(prefHelper.getUserID())
//                    .enqueue(new Callback<WebResponse<ContactDetailWrapper>>() {
//                        @Override
//                        public void onResponse(Call<WebResponse<ContactDetailWrapper>> call, Response<WebResponse<ContactDetailWrapper>> responseCode) {
//                            if (responseCode.content().isSuccess()) {
//                                contactDetail = responseCode.content().result.contactDetail;
//                                txtEmail.setText(contactDetail.emailAddress);
//                                txtPhoneNumber.setText(contactDetail.phoneNumber);
//                                dismissProgress();
//                            } else {
//                                UIHelper.showToast(getContext(), responseCode.content().message);
//                                dismissProgress();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<WebResponse<ContactDetailWrapper>> call, Throwable t) {
//                            t.printStackTrace();
//                            dismissProgress();
//                        }
//                    });
//        }
//
//    }


//    private void postFeedback(String message) {
//
//        showProgress();
//        WebServiceFactory.getInstance(prefHelper.getUser().token).postFeedback(prefHelper.getUserID(), message)
//                .enqueue(new Callback<WebResponse<Object>>() {
//                    @Override
//                    public void onResponse(Call<WebResponse<Object>> call, Response<WebResponse<Object>> responseCode) {
//                        if (responseCode.content().isSuccess()) {
//                            UIHelper.showToast(getContext(), responseCode.content().message);
//                            dismissProgress();
//                        } else {
//                            UIHelper.showToast(getContext(), responseCode.content().message);
//                            dismissProgress();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
//                        t.printStackTrace();
//                        dismissProgress();
//                    }
//                });
//    }
}
