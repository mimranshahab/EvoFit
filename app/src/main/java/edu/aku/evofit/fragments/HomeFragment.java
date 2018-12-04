package edu.aku.evofit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.evofit.R;
import edu.aku.evofit.adapters.recyleradapters.DashboardAdapter;
import edu.aku.evofit.fragments.abstracts.BaseFragment;
import edu.aku.evofit.models.DepartmentModel;
import edu.aku.evofit.widget.AnyEditTextView;
import edu.aku.evofit.widget.TitleBar;

/**
 * Created by hamza.ahmed on 7/19/2018.
 */

public class HomeFragment extends BaseFragment {


    Unbinder unbinder;
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
    private ArrayList<DepartmentModel> arrData;
    private DashboardAdapter adapterEndoscopy;


    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getDrawerLockMode() {
        return 0;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrData = new ArrayList<DepartmentModel>();
        adapterEndoscopy = new DashboardAdapter(getBaseActivity(), arrData, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        arrData.add(new DepartmentModel("What Is", R.color.material_blue900));
        arrData.add(new DepartmentModel("Prediabetes", R.color.material_blue800));
        arrData.add(new DepartmentModel("Guide", R.color.material_blue700));
        arrData.add(new DepartmentModel("Blood Glucose", R.color.material_blue600));
        arrData.add(new DepartmentModel("Treatment", R.color.material_blue500));
        arrData.add(new DepartmentModel("Exercise", R.color.material_blue400));
        arrData.add(new DepartmentModel("Diabetic Diet Plan", R.color.material_blue300));
        arrData.add(new DepartmentModel("Forum", R.color.material_blue200));
        arrData.add(new DepartmentModel("All Records", R.color.material_blue100));

        bindView();
    }


    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getBaseActivity(),2);
        recylerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recylerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerView.setLayoutAnimation(animation);
        recylerView.setAdapter(adapterEndoscopy);
        adapterEndoscopy.notifyDataSetChanged();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_general_recyler_view;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Home");
        titleBar.setRightButton2(getBaseActivity(), R.drawable.logout_icon, "", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutClick(HomeFragment.this);
            }
        });
        titleBar.showBackButtonInvisible();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
