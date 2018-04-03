package edu.aku.akuh_health_first.fragments.dialogs;


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
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class HistoryDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.btnClose)
    ImageView btnClose;
    @BindView(R.id.txtResultPrevious1)
    AnyTextView txtResultPrevious1;
    @BindView(R.id.txtResultPrevious1Date)
    AnyTextView txtResultPrevious1Date;
    @BindView(R.id.txtResultPrevious2)
    AnyTextView txtResultPrevious2;
    @BindView(R.id.txtResultPrevious2Date)
    AnyTextView txtResultPrevious2Date;
    @BindView(R.id.txtResultPrevious3)
    AnyTextView txtResultPrevious3;
    @BindView(R.id.txtResultPrevious3Date)
    AnyTextView txtResultPrevious3Date;
    @BindView(R.id.contHistoryResults)
    LinearLayout contHistoryResults;

    String Title,
            ResultPrevious1,
            ResultPrevious1Date,
            ResultPrevious2,
            ResultPrevious2Date,
            ResultPrevious3,
            ResultPrevious3Date;

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

        txtResultPrevious1.setText(getResultPrevious1());
        txtResultPrevious2.setText(getResultPrevious2());
        txtResultPrevious3.setText(getResultPrevious3());
        txtResultPrevious1Date.setText(getResultPrevious1Date());
        txtResultPrevious2Date.setText(getResultPrevious2Date());
        txtResultPrevious3Date.setText(getResultPrevious3Date());


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

    public String getResultPrevious3() {
        return ResultPrevious3;
    }

    public void setResultPrevious3(String resultPrevious3) {
        ResultPrevious3 = resultPrevious3;
    }

    public String getResultPrevious3Date() {
        return ResultPrevious3Date;
    }

    public void setResultPrevious3Date(String resultPrevious3Date) {
        ResultPrevious3Date = resultPrevious3Date;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

