package edu.aku.ehs.fragments.dialogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.ehs.R;
import edu.aku.ehs.adapters.recyleradapters.ClinicalLabHistoryAdapter;
import edu.aku.ehs.models.LabHistoryModel;
import edu.aku.ehs.widget.AnyTextView;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class HistoryDialogFragment extends DialogFragment {


    Unbinder unbinder;
    String Title;
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtOK)
    AnyTextView txtOK;
    private ArrayList<LabHistoryModel> arrData;
    private ClinicalLabHistoryAdapter clinicalLabHistoryAdapter;


    public HistoryDialogFragment() {
    }

    public static HistoryDialogFragment newInstance() {
        HistoryDialogFragment frag = new HistoryDialogFragment();

        Bundle args = new Bundle();
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


        View view = inflater.inflate(R.layout.fragment_history_popup_list, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clinicalLabHistoryAdapter = new ClinicalLabHistoryAdapter(getActivity(), arrData, null);

        bindData();

//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

    }


    private void bindData() {
        txtTitle.setText(getTitle());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(clinicalLabHistoryAdapter);
        clinicalLabHistoryAdapter.notifyDataSetChanged();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setData(ArrayList<LabHistoryModel> arrData) {
        this.arrData = arrData;
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

