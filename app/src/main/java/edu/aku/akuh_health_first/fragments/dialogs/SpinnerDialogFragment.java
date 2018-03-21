package edu.aku.akuh_health_first.fragments.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import edu.aku.akuh_health_first.widget.AnyTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.SpinnerDialogAdapter;
import edu.aku.akuh_health_first.callbacks.OnSpinnerItemClickListener;
import edu.aku.akuh_health_first.models.SpinnerModel;
import edu.aku.akuh_health_first.widget.recyclerview_layout.CustomLayoutManager;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class SpinnerDialogFragment extends DialogFragment {

    String title;
    Unbinder unbinder;

    SpinnerDialogAdapter adapter;
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.btnClose)
    ImageView btnClose;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtOK)
    edu.aku.akuh_health_first.widget.AnyTextView txtOK;

    private ArrayList<SpinnerModel> arrData;
    private OnSpinnerItemClickListener onItemClickListener;
    private int scrollToPosition;

    public SpinnerDialogFragment() {
    }

    public static SpinnerDialogFragment newInstance(String title, ArrayList<SpinnerModel> arrData,
                                                    OnSpinnerItemClickListener onItemClickListener, int scrollToPosition) {
        SpinnerDialogFragment frag = new SpinnerDialogFragment();

        Bundle args = new Bundle();
        frag.title = title;
        frag.arrData = arrData;
        frag.onItemClickListener = onItemClickListener;
        frag.scrollToPosition = scrollToPosition;
        frag.setArguments(args);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_spinner_popup, container);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListeners();
        adapter = new SpinnerDialogAdapter(getActivity(), arrData, onItemClickListener);
        adapter.notifyDataSetChanged();
        txtTitle.setText(title);

        bindView();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        getDialog().getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_box_white));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    private void setListeners() {

    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new CustomLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapter);
        scrollToPosition(scrollToPosition);

    }

    public void scrollToPosition(int scrollToPosition) {
        if (scrollToPosition > -1) {
            recyclerView.scrollToPosition(scrollToPosition);
        } else {
            recyclerView.scrollToPosition(0);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.btnClose, R.id.txtOK})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClose:
                this.dismiss();
                break;
            case R.id.txtOK:
                this.dismiss();
                break;
        }
    }
}

