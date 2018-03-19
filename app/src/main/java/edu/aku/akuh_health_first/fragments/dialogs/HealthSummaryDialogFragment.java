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

import com.ctrlplusz.anytextview.AnyTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.HealthSummaryDialogAdapter;
import edu.aku.akuh_health_first.models.Shortmessagemobile;
import edu.aku.akuh_health_first.views.recyclerview_layout.CustomLayoutManager;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class HealthSummaryDialogFragment extends DialogFragment {


    String title;

    Unbinder unbinder;

    HealthSummaryDialogAdapter adapter;
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.btnClose)
    ImageView btnClose;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<Shortmessagemobile> arrData;

    public HealthSummaryDialogFragment() {
    }

    public static HealthSummaryDialogFragment newInstance(String title) {
        HealthSummaryDialogFragment frag = new HealthSummaryDialogFragment();

        Bundle args = new Bundle();
//        args.putString(KEY_TITLE, title);
//        args.putString(KEY_MESSAGE,message);
        frag.title = title;
        frag.setArguments(args);

        return frag;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_healthsummary_popup, container);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListeners();
        adapter = new HealthSummaryDialogAdapter(getActivity(), arrData, null);

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnClose)
    public void onViewClicked() {
        this.dismiss();
    }


    public ArrayList<Shortmessagemobile> getArrData() {
        return arrData;
    }

    public void setArrData(ArrayList<Shortmessagemobile> arrData) {
        this.arrData = arrData;
    }

}
