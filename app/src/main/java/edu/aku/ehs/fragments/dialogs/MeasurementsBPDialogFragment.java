package edu.aku.ehs.fragments.dialogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.ehs.R;
import edu.aku.ehs.models.LstLaboratorySpecimenResults;
import edu.aku.ehs.widget.AnyEditTextView;
import edu.aku.ehs.widget.AnyTextView;

/**
 */

public class MeasurementsBPDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.edWeight)
    AnyEditTextView edWeight;
    @BindView(R.id.edHeight)
    AnyEditTextView edHeight;
    @BindView(R.id.edtBPlow)
    AnyEditTextView edtBPlow;
    @BindView(R.id.edBPHigh)
    AnyEditTextView edBPHigh;
    @BindView(R.id.txtOK)
    AnyTextView txtOK;


    private LstLaboratorySpecimenResults model;

    public MeasurementsBPDialogFragment() {
    }

    public static MeasurementsBPDialogFragment newInstance(/*LstLaboratorySpecimenResults model*/) {
        MeasurementsBPDialogFragment frag = new MeasurementsBPDialogFragment();

        Bundle args = new Bundle();
//        frag.model = model;
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

        View view = inflater.inflate(R.layout.fragment_bp_measuements_popup, container);
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

//        txtTitle.setText(model.getReportName());
//        txtResultComments.setVisibility(View.VISIBLE);
//        txtTestComments.setVisibility(View.VISIBLE);
//        separator.setVisibility(View.VISIBLE);
//        if ((model.getResultComments() == null || model.getResultComments().trim().isEmpty())) {
//            txtResultComments.setVisibility(View.GONE);
//            separator.setVisibility(View.GONE);
//        } else {
//            txtResultComments.setText("Result Comments: \n" + model.getResultComments().trim());
//        }
//
//        if ((model.getComments() == null || model.getComments().trim().isEmpty())) {
//            txtTestComments.setVisibility(View.GONE);
//            separator.setVisibility(View.GONE);
//        } else {
//            txtTestComments.setText("Test Comments: \n" + model.getComments().trim());
//
//        }


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

