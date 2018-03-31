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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class CommentsDialogFragment extends DialogFragment {


    Unbinder unbinder;
    //    @BindView(R.id.txtTitleResultComments)
//    AnyTextView txtTitleResultComments;
    @BindView(R.id.txtResultComments)
    AnyTextView txtResultComments;
    @BindView(R.id.separator)
    ImageView separator;
    //    @BindView(R.id.txtTitleTestComments)
//    AnyTextView txtTitleTestComments;
    @BindView(R.id.txtTestComments)
    AnyTextView txtTestComments;

    String  /*TitleResultComments*/ ResultComments, TestComments/*TitleTestComments*/;

    public CommentsDialogFragment() {
    }

    public static CommentsDialogFragment newInstance() {
        CommentsDialogFragment frag = new CommentsDialogFragment();

        Bundle args = new Bundle();
//        args.putString(KEY_TITLE, title);
//        args.putString(KEY_MESSAGE,message);
        frag.setArguments(args);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        title = getArguments().getString(KEY_TITLE);
//        message = getArguments().getString(KEY_MESSAGE);

        View view = inflater.inflate(R.layout.fragment_comments_popup, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bindData(/*TitleResultComments, */ResultComments, TestComments /*TitleTestComments*/);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        getDialog().getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_box_white));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

    }


    private void bindData(String resultComments, String testComments/*,String titleTest, String testComments*/) {
        txtResultComments.setVisibility(View.VISIBLE);
        txtTestComments.setVisibility(View.VISIBLE);
        separator.setVisibility(View.VISIBLE);
        if ((resultComments == null || resultComments.trim().isEmpty())) {
            txtResultComments.setVisibility(View.GONE);
            separator.setVisibility(View.GONE);
        } else {
            txtResultComments.setText("Result Comments: \n" + resultComments.trim());
        }

        if ((testComments == null || testComments.trim().isEmpty())) {
            txtTestComments.setVisibility(View.GONE);
            separator.setVisibility(View.GONE);
        } else {
            txtTestComments.setText("Test Comments: \n" + testComments.trim());

        }


    }


    public String getResultComments() {
        return ResultComments;
    }

    public void setResultComments(String resultComments) {
        ResultComments = resultComments;
    }

    public String getTestComments() {
        return TestComments;
    }

    public void setTestComments(String testComments) {
        TestComments = testComments;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

