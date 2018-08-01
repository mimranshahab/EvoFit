package edu.aku.ehs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.ehs.R;
import edu.aku.ehs.adapters.recyleradapters.SessionDetailAdapter;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.enums.EmployeeSessionState;
import edu.aku.ehs.fragments.abstracts.BaseFragment;
import edu.aku.ehs.helperclasses.ui.helper.UIHelper;
import edu.aku.ehs.managers.DateManager;
import edu.aku.ehs.models.SessionDetailModel;
import edu.aku.ehs.widget.AnyEditTextView;
import edu.aku.ehs.widget.AnyTextView;
import edu.aku.ehs.widget.TitleBar;

/**
 * Created by hamza.ahmed on 7/23/2018.
 */

public class SessionDetailFragment extends BaseFragment implements OnItemClickListener {

    Unbinder unbinder;
    @BindView(R.id.imgBanner)
    ImageView imgBanner;
    @BindView(R.id.empty_view)
    AnyTextView emptyView;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.edtSearchBar)
    AnyEditTextView edtSearchBar;
    @BindView(R.id.contSearch)
    RoundKornerLinearLayout contSearch;
    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.contParent)
    RelativeLayout contParent;
    @BindView(R.id.contOptionButtons)
    LinearLayout contOptionButtons;

    private ArrayList<SessionDetailModel> arrData;
    private SessionDetailAdapter adapter;


    public static SessionDetailFragment newInstance() {

        Bundle args = new Bundle();

        SessionDetailFragment fragment = new SessionDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getDrawerLockMode() {
        return 0;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_general_recyler_view;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Sessions");
        titleBar.showBackButton(getBaseActivity());
        titleBar.showHome(getBaseActivity());
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrData = new ArrayList<SessionDetailModel>();
        adapter = new SessionDetailAdapter(getBaseActivity(), arrData, this);
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
        contSearch.setVisibility(View.VISIBLE);
        imgBanner.setVisibility(View.VISIBLE);
        contOptionButtons.setVisibility(View.VISIBLE);
        bindView();

        bindData();
    }

    private void bindData() {
        arrData.clear();
        SessionDetailModel sessionDetailModel;

        for (int i = 0; i < 8; i++) {
            if (i < 2) {
                sessionDetailModel = new SessionDetailModel("Hamza Ahmed Khan", EmployeeSessionState.ENROLLED);
            } else if (i >= 2 && i < 4) {
                sessionDetailModel = new SessionDetailModel("Haris Maaz ", EmployeeSessionState.SCHEDULED);
            } else if (i >= 4 && i < 6) {
                sessionDetailModel = new SessionDetailModel("Aqsa Sarwar ", EmployeeSessionState.INPROGRESS);
            } else {
                sessionDetailModel = new SessionDetailModel("Mahrukh Mehmood ", EmployeeSessionState.CLOSED);

            }
            arrData.add(sessionDetailModel);
        }
        adapter.notifyDataSetChanged();
    }


    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recylerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recylerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recylerView.setLayoutAnimation(animation);
        recylerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onItemClick(int position, Object object) {
        SessionDetailModel sessionDetailModel = (SessionDetailModel) object;
        switch (sessionDetailModel.getStatus()) {
            case ENROLLED:
                DateManager.showDatePicker(getContext(), date -> UIHelper.showToast(getContext(), date), false, true);
                break;
            case SCHEDULED:
                DateManager.showDatePicker(getContext(), date -> UIHelper.showToast(getContext(), date), false, true);
                break;
            case INPROGRESS:
                UIHelper.showIOSPopup(getContext(), "Cancel Schedule", "Do you really want to cancel this schedule",
                        "Yes", "No", dialog -> {
                        }, dialog -> {
                        });
                break;
        }
    }

}
