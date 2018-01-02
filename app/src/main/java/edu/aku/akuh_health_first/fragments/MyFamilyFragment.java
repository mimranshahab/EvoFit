package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;

/**
 * Created by hamza.ahmed on 1/1/2018.
 */

public class MyFamilyFragment extends BaseFragment {
    @BindView(R.id.btnRegister)
    Button btnRegister;
    Unbinder unbinder;

    public static MyFamilyFragment newInstance() {

        Bundle args = new Bundle();

        MyFamilyFragment fragment = new MyFamilyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_myfamily;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.showBackButton(getMainActivity());
        titleBar.setRightButton(R.drawable.plus, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainActivity().addDockableFragment(AddFamilyMemberFragment.newInstance());
            }
        });
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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

    @OnClick(R.id.btnRegister)
    public void onViewClicked() {
        showNextBuildToast();
    }
}
