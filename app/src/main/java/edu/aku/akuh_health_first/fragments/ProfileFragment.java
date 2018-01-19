package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/19/2018.
 */

public class ProfileFragment extends BaseFragment {
    @BindView(R.id.circleImageView)
    CircleImageView circleImageView;
    @BindView(R.id.txtUserName)
    AnyTextView txtUserName;
    @BindView(R.id.txtMRN)
    AnyTextView txtMRN;
    @BindView(R.id.txtEmailAddress)
    AnyTextView txtEmailAddress;
    @BindView(R.id.txtAge)
    AnyTextView txtAge;
    @BindView(R.id.txtCardNum)
    AnyTextView txtCardNum;
    @BindView(R.id.txtCardType)
    AnyTextView txtCardType;
    @BindView(R.id.txtIssueDate)
    AnyTextView txtIssueDate;
    @BindView(R.id.txtExpDate)
    AnyTextView txtExpDate;
    Unbinder unbinder;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_profile;
    }

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    private void setData() {
        txtUserName.setText(sharedPreferenceManager.getCurrentUser().getName());
        txtAge.setText("Age "+sharedPreferenceManager.getCurrentUser().getAge()+"  Gender "+sharedPreferenceManager.getCurrentUser().getGender());
        txtEmailAddress.setText(sharedPreferenceManager.getCurrentUser().getEmailAddress());
//        txtExpDate.setText(sharedPreferenceManager.getCurrentUser().);
        txtMRN.setText(sharedPreferenceManager.getCurrentUser().getMRNumber());
//        txtIssueDate.setText(sharedPreferenceManager.getCurrentUser());
        txtCardType.setText("CardType: " + sharedPreferenceManager.getCurrentUser().getCardTypeDescription());
        txtCardNum.setText("CardNumber: " + sharedPreferenceManager.getCurrentUser().getCardNumber());
        txtExpDate.setText("Expiry date: 12-12-2022");
        txtIssueDate.setText("Issue date: 01-01-2018");
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("My profile");
        titleBar.showBackButton(getBaseActivity());

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
