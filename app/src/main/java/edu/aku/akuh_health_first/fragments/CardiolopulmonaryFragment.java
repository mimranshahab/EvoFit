package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.adapters.recyleradapters.CardioAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.enums.BaseURLTypes;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.FileManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.managers.retrofit.WebServices;
import edu.aku.akuh_health_first.models.CardioModel;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.models.wrappers.WebResponse;

import static edu.aku.akuh_health_first.constatnts.AppConstants.DOC_PATH;

/**
 * Created by aqsa.sarwar on 1/17/2018.
 */

public class CardiolopulmonaryFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.recylerView)
    RecyclerView recyclerCardio;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private ArrayList<CardioModel> arrCardioModelLists;
    private CardioAdapter adapterCardio;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrCardioModelLists = new ArrayList<CardioModel>();
        adapterCardio = new CardioAdapter(getBaseActivity(), arrCardioModelLists, this);
    }

    public static CardiolopulmonaryFragment newInstance() {

        Bundle args = new Bundle();

        CardiolopulmonaryFragment fragment = new CardiolopulmonaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_general_recyler_view;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("Cardiolopulmonary");
        titleBar.showBackButton(getBaseActivity());
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        serviceCall();
    }

    private void bindView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerCardio.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recyclerCardio.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerCardio.setLayoutAnimation(animation);
        recyclerCardio.setAdapter(adapterCardio);
    }

    @Override
    public void setListeners() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                serviceCall();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        switch (view.getId()) {
            case R.id.btnShowGraph:
                showGraphAPI(adapterCardio.getItem(position));
                break;
            case R.id.btnShowReport:
                showReportAPI(adapterCardio.getItem(position));
                break;
        }

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

    private void showGraphAPI(final CardioModel cardiolopulmonary) {
        String fileName = cardiolopulmonary.getDetailGraphID();
        final File file = new File(DOC_PATH
                + "/" + fileName);
        if (FileManager.isFileExits(file.getPath())) {

//            UIHelper.showToast(getContext(), "File already exist");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    FileManager.openFile(getContext(), file);
                }
            }, 300);
        } else {

            new WebServices(getBaseActivity(), WebServiceConstants.temporaryToken, BaseURLTypes.AHFA_BASE_URL)
                    .webServiceRequestAPIForWebResponseWithString(WebServiceConstants.METHOD_CARDIO_SHOW_GRAPH,
                            cardiolopulmonary.toString(), new WebServices.IRequestWebResponseWithStringDataCallBack() {
                                @Override
                                public void requestDataResponse(WebResponse<String> webResponse) {
                                    String fileName = cardiolopulmonary.getDetailGraphID();

                                    FileManager.writeResponseBodyToDisk(webResponse.result, fileName, AppConstants.getUserFolderPath(getContext()));

                                    final File file = new File( AppConstants.getUserFolderPath(getContext())
                                            + "/" + fileName);

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            FileManager.openFile(getContext(), file);
                                        }
                                    }, 300);


                                }

                                @Override
                                public void onError() {


                                }
                            }
                    );
        }
    }

    private void showReportAPI(final CardioModel cardiolopulmonary) {
        String fileName = cardiolopulmonary.getDetailReportID();
        final File file = new File(DOC_PATH
                + "/" + fileName);
        if (FileManager.isFileExits(file.getPath())) {

             new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    FileManager.openFile(getContext(), file);
                }
            }, 300);
        } else {

            new WebServices(getBaseActivity(), WebServiceConstants.temporaryToken, BaseURLTypes.AHFA_BASE_URL)
                    .webServiceRequestAPIForWebResponseWithString(WebServiceConstants.METHOD_CARDIO_SHOW_REPORT,
                            cardiolopulmonary.tempCardioOb(), new WebServices.IRequestWebResponseWithStringDataCallBack() {
                                @Override
                                public void requestDataResponse(WebResponse<String> webResponse) {
                                    String fileName = cardiolopulmonary.getDetailReportID();

                                    FileManager.writeResponseBodyToDisk(webResponse.result, fileName, AppConstants.getUserFolderPath(getContext()));

                                    final File file = new File( AppConstants.getUserFolderPath(getContext())
                                            + "/" + fileName);

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            FileManager.openFile(getContext(), file);
                                        }
                                    }, 300);


                                }

                                @Override
                                public void onError() {


                                }
                            }
                    );
        }

    }

    private void serviceCall() {
        // FIXME: 1/18/2018 Use live data in future
        UserDetailModel currentUser = sharedPreferenceManager.getCurrentUser();
        currentUser.setMRNumber(WebServiceConstants.tempMRN);

        new WebServices(getBaseActivity(),
                WebServiceConstants.temporaryToken,
                BaseURLTypes.AHFA_BASE_URL)
                .webServiceRequestAPIForArray(WebServiceConstants.METHOD_CARDIO,
                        currentUser.getMRNumberwithComma(),
                        new WebServices.IRequestArrayDataCallBack() {
                            @Override
                            public void requestDataResponse(WebResponse<ArrayList<JsonObject>> webResponse) {

                                Type type = new TypeToken<ArrayList<CardioModel>>() {
                                }.getType();
                                ArrayList<CardioModel> arrayList = GsonFactory.getSimpleGson()
                                        .fromJson(GsonFactory.getSimpleGson().toJson(webResponse.result)
                                                , type);

                                arrCardioModelLists.clear();
                                arrCardioModelLists.addAll(arrayList);
                                adapterCardio.notifyDataSetChanged();
                            }

                            @Override
                            public void onError() {
                                UIHelper.showShortToastInCenter(getContext(), "failure");
                            }
                        });

    }
}
