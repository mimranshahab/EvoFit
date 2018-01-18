package edu.aku.akuh_health_first.fragments;

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

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.NeurophysiologyAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.Neurophysiology;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class NeurophysiologyFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.listNeurophysiology)
    RecyclerView recyclerNeurophysiology;
    Unbinder unbinder;
    private ArrayList<Neurophysiology> arrNeuropysiologyLists;
    private NeurophysiologyAdapter adaptNeuropysiology;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adaptNeuropysiology = new NeurophysiologyAdapter(getBaseActivity(), arrNeuropysiologyLists, this);
    }

    public static NeurophysiologyFragment newInstance() {

        Bundle args = new Bundle();

        NeurophysiologyFragment fragment = new NeurophysiologyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_neurophysiology;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Neurophysiology");
        titleBar.showBackButton(getBaseActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerNeurophysiology.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerNeurophysiology.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerNeurophysiology.setLayoutAnimation(animation);
        recyclerNeurophysiology.setAdapter(adaptNeuropysiology);
        serviceCall();
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
    public void onItemClick(int position, Object object) {

    }

    private void serviceCall() {
        // FIXME: 1/18/2018 Use live data in future
        sharedPreferenceManager.getCurrentUser().setMRNumber(WebServiceConstants.tempMRN);

        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL).webServiceRequestAPI(WebServiceConstants.METHOD_NEUROPHIOLOGY,
                sharedPreferenceManager.getCurrentUser().getMRNumberwithComma(),
                new WebServices.IRequestJsonDataCallBack() {
                    @Override
                    public void requestDataResponse(WebResponse<JsonObject> webResponse) {
                        Type type = new TypeToken<ArrayList<Neurophysiology>>() {
                        }.getType();
                        ArrayList<Neurophysiology> arrayList = GsonFactory.getSimpleGson().fromJson(webResponse.result, type);
                        arrNeuropysiologyLists.clear();
                        arrNeuropysiologyLists.addAll(arrayList);
                        adaptNeuropysiology.notifyDataSetChanged();
                    }

                    @Override
                    public void onError() {
                        UIHelper.showShortToastInCenter(getContext(), "failure");
                    }
                });

    }
}
