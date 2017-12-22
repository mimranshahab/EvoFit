package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import edu.aku.R;
import edu.aku.fragments.abstracts.BaseFragment;
import edu.aku.helperclasses.ui.helper.KeyboardHide;
import edu.aku.helperclasses.ui.helper.TitleBar;
import edu.aku.helperclasses.ui.helper.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by shehrozmirza on 5/15/2017.
 */

public class SortByFragment extends BaseFragment {

    @BindView(R.id.chkMostOrdered)
    CheckBox chkMostOrdered;
    @BindView(R.id.contMostOrdered)
    LinearLayout contMostOrdered;
    @BindView(R.id.chkMostPopular)
    CheckBox chkMostPopular;
    @BindView(R.id.contMostPopular)
    LinearLayout contMostRecent;
    @BindView(R.id.chkMyRecent)
    CheckBox chkMyRecent;
    @BindView(R.id.contMyRecent)
    LinearLayout contMyRecent;
    Unbinder unbinder;
    CheckBox[] uncheckedIds = new CheckBox[2];
    public static final int MOST_ORDERED = 1;
    public static final int MOST_POPULAR = 2;
    public static final int MOST_RECENT = 3;
    public int value;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sortby;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(getString(R.string.sort_by));
        titleBar.showBackButton(getMainActivity());
        titleBar.setRightButton(R.drawable.imgtick, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkMostOrdered.isChecked() || chkMostPopular.isChecked() || chkMyRecent.isChecked()) {
                    getMainActivity().sortBy = value;
                    popBackStack();
                } else {
                    UIHelper.showToast(getMainActivity(), "please select any option ");
                }
            }
        });
    }

    public static SortByFragment newInstance() {
        Bundle args = new Bundle();
        SortByFragment fragment = new SortByFragment();
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
        clearAllChecks();
        setInitialChecked();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.contMostOrdered, R.id.contMostPopular, R.id.contMyRecent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contMostOrdered:
                uncheckedIds[0] = chkMostPopular;
                uncheckedIds[1] = chkMyRecent;
                value = MOST_ORDERED;
                singleStateChecked(chkMostOrdered, uncheckedIds);
                break;

            case R.id.contMostPopular:
                uncheckedIds[0] = chkMostOrdered;
                uncheckedIds[1] = chkMyRecent;
                value = MOST_POPULAR;
                singleStateChecked(chkMostPopular, uncheckedIds);
                break;

            case R.id.contMyRecent:
                uncheckedIds[0] = chkMostOrdered;
                uncheckedIds[1] = chkMostPopular;
                value = MOST_RECENT;
                singleStateChecked(chkMyRecent, uncheckedIds);
                break;
        }
    }

    private void singleStateChecked(CheckBox checkedId, CheckBox ids[]) {
        ids[0].setChecked(false);
        ids[1].setChecked(false);
        if (checkedId.isChecked()) {
            checkedId.setChecked(false);
        } else {
            checkedId.setChecked(true);
        }
    }

    private void setInitialChecked() {
        if (getMainActivity().sortBy == 1) {
            chkMostOrdered.setChecked(true);
            value = MOST_ORDERED;
        } else if (getMainActivity().sortBy == 2) {
            chkMostPopular.setChecked(true);
            value = MOST_POPULAR;
        } else if (getMainActivity().sortBy == 3) {
            chkMyRecent.setChecked(true);
            value = MOST_RECENT;
        }
    }

    private void clearAllChecks() {
        chkMostPopular.setChecked(false);
        chkMostPopular.setChecked(false);
        chkMyRecent.setChecked(false);
    }
}

