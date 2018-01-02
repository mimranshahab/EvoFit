package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import edu.aku.managers.retrofit.WebServiceFactory;
import edu.aku.models.wrappers.WebResponse;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackFragment extends BaseFragment {

    public EditText edtTextFeedbackMsg;
    AnyTextView btnDoneFeedback;
    Unbinder unbinder;
    private Call<WebResponse<Object>> callfeedBack;

    public static FeedbackFragment newInstance(View.OnClickListener onClickListener) {
        FeedbackFragment frag = new FeedbackFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        setListeners();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_feedback;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.feedback_and_suggestions));
        titleBar.showBackButton(getMainActivity());
    }

    @Override
    public void setListeners() {

    }

    private void bindViews(View view) {
        btnDoneFeedback = (AnyTextView) view.findViewById(R.id.btnDone);
        edtTextFeedbackMsg = (EditText) view.findViewById(R.id.edtFeedback);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnDone)
    public void onViewClicked() {
        btnDoneFeedback.setEnabled(false);
        if (edtTextFeedbackMsg.getText().toString().trim().isEmpty()) {
            UIHelper.showToast(getContext(), "Please Write Something in Feedback");
        } else {
        }
    }


    @Override
    public void onDestroy() {
        if (callfeedBack != null) {
            callfeedBack.cancel();
        }
        super.onDestroy();
    }
}