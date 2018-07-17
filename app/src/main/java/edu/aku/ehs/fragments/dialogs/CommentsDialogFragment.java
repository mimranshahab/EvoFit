package edu.aku.ehs.fragments.dialogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.ehs.R;
import edu.aku.ehs.models.LstLaboratorySpecimenResults;
import edu.aku.ehs.widget.AnyTextView;

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

    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.txtOK)
    edu.aku.ehs.widget.AnyTextView txtOK;

    private LstLaboratorySpecimenResults model;

    public CommentsDialogFragment() {
    }

    public static CommentsDialogFragment newInstance(LstLaboratorySpecimenResults model) {
        CommentsDialogFragment frag = new CommentsDialogFragment();

        Bundle args = new Bundle();
        frag.model = model;
//        args.putString(KEY_TITLE, title);
//        args.putString(KEY_MESSAGE,message);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme);
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


        bindData();

//        getDialog().getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_box_white));


//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


    }


    private void bindData() {

        txtTitle.setText(model.getReportName());
        txtResultComments.setVisibility(View.VISIBLE);
        txtTestComments.setVisibility(View.VISIBLE);
        separator.setVisibility(View.VISIBLE);
        if ((model.getResultComments() == null || model.getResultComments().trim().isEmpty())) {
            txtResultComments.setVisibility(View.GONE);
            separator.setVisibility(View.GONE);
        } else {
            txtResultComments.setText("Result Comments: \n" + model.getResultComments().trim());
        }

        if ((model.getComments() == null || model.getComments().trim().isEmpty())) {
            txtTestComments.setVisibility(View.GONE);
            separator.setVisibility(View.GONE);
        } else {
            txtTestComments.setText("Test Comments: \n" + model.getComments().trim());

        }


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

