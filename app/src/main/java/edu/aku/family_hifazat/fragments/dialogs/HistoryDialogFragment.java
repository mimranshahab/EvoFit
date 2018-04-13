package edu.aku.family_hifazat.fragments.dialogs;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.widget.AnyTextView;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class HistoryDialogFragment extends DialogFragment {


    Unbinder unbinder;
    String Title,
            ResultPrevious1,
            ResultPrevious1Date,
            ResultPrevious2,
            ResultPrevious2Date,
            CurrentResult,
            CurrentDate;

    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.separator)
    ImageView separator;
    @BindView(R.id.txtCurrentDate)
    AnyTextView txtCurrentDate;
    @BindView(R.id.txtCurrentResult)
    AnyTextView txtCurrentResult;
    @BindView(R.id.txtPrevDate1)
    AnyTextView txtPrevDate1;
    @BindView(R.id.txtPrevResult1)
    AnyTextView txtPrevResult1;
    @BindView(R.id.txtPrevDate2)
    AnyTextView txtPrevDate2;
    @BindView(R.id.txtPrevResult2)
    AnyTextView txtPrevResult2;
    @BindView(R.id.txtOK)
    AnyTextView txtOK;

    public HistoryDialogFragment() {
    }

    public static HistoryDialogFragment newInstance() {
        HistoryDialogFragment frag = new HistoryDialogFragment();

        Bundle args = new Bundle();
        frag.setArguments(args);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_history_popup, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bindData();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        getDialog().getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_box_white));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

    }


    private void bindData() {


        txtTitle.setText(getTitle());

        txtPrevResult1.setText(getResultPrevious1());
        txtPrevResult2.setText(getResultPrevious2());
        txtPrevDate1.setText(getResultPrevious1Date());
        txtPrevDate2.setText(getResultPrevious2Date());
        txtCurrentResult.setText(getCurrentResult());
        txtCurrentDate.setText(getCurrentDate());
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


    public String getResultPrevious1() {
        return ResultPrevious1;
    }

    public void setResultPrevious1(String resultPrevious1) {
        ResultPrevious1 = resultPrevious1;
    }

    public String getResultPrevious1Date() {
        return ResultPrevious1Date;
    }

    public void setResultPrevious1Date(String resultPrevious1Date) {
        ResultPrevious1Date = resultPrevious1Date;
    }

    public String getResultPrevious2() {
        return ResultPrevious2;
    }

    public void setResultPrevious2(String resultPrevious2) {
        ResultPrevious2 = resultPrevious2;
    }

    public String getResultPrevious2Date() {
        return ResultPrevious2Date;
    }

    public void setResultPrevious2Date(String resultPrevious2Date) {
        ResultPrevious2Date = resultPrevious2Date;
    }

    public String getCurrentResult() {
        return CurrentResult;
    }

    public void setCurrentResult(String currentResult) {
        CurrentResult = currentResult;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDAte) {
        CurrentDate = currentDAte;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtOK})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtOK:
                this.dismiss();
                break;
        }
    }
}

