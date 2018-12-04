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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.evofit.R;
import edu.aku.evofit.adapters.recyleradapters.DashboardAdapter;
import edu.aku.evofit.adapters.recyleradapters.NewsFeedAdapter;
import edu.aku.evofit.callbacks.OnItemClickListener;
import edu.aku.evofit.constatnts.AppConstants;
import edu.aku.evofit.fragments.abstracts.BaseFragment;
import edu.aku.evofit.fragments.abstracts.GenericContentFragment;
import edu.aku.evofit.models.DepartmentModel;
import edu.aku.evofit.models.NewsFeedModel;
import edu.aku.evofit.widget.TitleBar;

/**
 * Created by hamza.ahmed on 7/19/2018.
 */

public class NewsFeedFragment extends BaseFragment implements OnItemClickListener {


    Unbinder unbinder;
    @BindView(R.id.recylerView)
    RecyclerView recylerView;

    private ArrayList<NewsFeedModel> arrData;
    private NewsFeedAdapter adapter;


    public static NewsFeedFragment newInstance() {

        Bundle args = new Bundle();

        NewsFeedFragment fragment = new NewsFeedFragment();
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
        arrData = new ArrayList<NewsFeedModel>();
        adapter = new NewsFeedAdapter(getBaseActivity(), arrData, this);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        arrData.clear();


        arrData.add(new NewsFeedModel("Haris Maaz", "Do you drink beer?" , "Haris maaz to charsi hai", "https://www.brandsynario.com/wp-content/uploads/2017/12/abid-shaka.jpg"));
        arrData.add(new NewsFeedModel("Haris Maaz", "Do you drink beer?" , "Haris maaz to charsi hai", "https://www.brandsynario.com/wp-content/uploads/2017/12/abid-shaka.jpg"));
        arrData.add(new NewsFeedModel("Haris Maaz", "Do you drink beer?" , "Haris maaz to charsi hai", "https://www.brandsynario.com/wp-content/uploads/2017/12/abid-shaka.jpg"));
        arrData.add(new NewsFeedModel("Haris Maaz", "Do you drink beer?" , "Haris maaz to charsi hai", "https://www.brandsynario.com/wp-content/uploads/2017/12/abid-shaka.jpg"));
        arrData.add(new NewsFeedModel("Haris Maaz", "Do you drink beer?" , "Haris maaz to charsi hai", "https://www.brandsynario.com/wp-content/uploads/2017/12/abid-shaka.jpg"));

        bindView();
    }


    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recylerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recylerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
//        recyclerView.setLayoutAnimation(animation);
        recylerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_newsfeed;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("News Feed");
        titleBar.setRightButton2(getBaseActivity(), R.drawable.logout_icon, "", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutClick(NewsFeedFragment.this);
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

    @Override
    public void onItemClick(int position, Object object, View view) {



    }
}
