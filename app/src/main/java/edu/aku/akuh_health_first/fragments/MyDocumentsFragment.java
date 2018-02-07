package edu.aku.akuh_health_first.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.File;
import java.util.AbstractQueue;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.MainActivity;
import edu.aku.akuh_health_first.adapters.recyleradapters.MyDocumentsAdapter;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.libraries.swipetodelete.CdsItemTouchCallback;
import edu.aku.akuh_health_first.libraries.swipetodelete.CdsRecyclerView;
import edu.aku.akuh_health_first.managers.FileManager;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/31/2018.
 */

public class MyDocumentsFragment extends BaseFragment implements OnItemClickListener {


    CdsRecyclerView recylerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.empty_view)
    AnyTextView emptyView;
    private MyDocumentsAdapter adapterFileDownloded;
    private ArrayList<File> arrFiles;

//    private CdsItemTouchCallback.ItemDragCompleteListener mItemDragCompleteListener;
//    private CdsItemTouchCallback.ItemSwipeCompleteListener mItemSwipeCompleteListener;

    public static MyDocumentsFragment newInstance() {

        Bundle args = new Bundle();

        MyDocumentsFragment fragment = new MyDocumentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrFiles = new ArrayList<File>();
        adapterFileDownloded = new MyDocumentsAdapter(getBaseActivity(), arrFiles, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListeners();
        bindView();
        arrFiles.addAll(FileManager.getFiles(AppConstants.getUserFolderPath(getContext())));
        adapterFileDownloded.notifyDataSetChanged();
        Log.d("FILE", "FILE COUNT: " + arrFiles.size());
//        }
        if (arrFiles.size() > 0) {
            bindView();
            emptyView.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);

        } else {
            refreshLayout.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);


        }
    }

    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    private void bindView() {
        recylerView = new CdsRecyclerView(getContext());
        recylerView = view.findViewById(R.id.recylerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        recylerView.setLayoutManager(mLayoutManager);
        ((DefaultItemAnimator) recylerView.getItemAnimator()).setSupportsChangeAnimations(false);
        int resId = R.anim.layout_animation_fall_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recylerView.setLayoutAnimation(animation);
        recylerView.setAdapter(adapterFileDownloded);
    }


    private void initListeners() {

//        mItemDragCompleteListener = new CdsItemTouchCallback.ItemDragCompleteListener() {
//            @Override
//            public void onItemDragComplete(int fromPosition, int toPosition) {
//                Toast.makeText(getContext(), "Item dragged from " + fromPosition +
//                        " to " + toPosition, Toast.LENGTH_SHORT).show();
//            }
//        };
//        mItemSwipeCompleteListener = new CdsItemTouchCallback.ItemSwipeCompleteListener() {
//            @Override
//            public void onItemSwipeComplete(int position) {
//                Toast.makeText(getContext(), "Item was swiped:" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        };

    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_mydocument;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setTitle("My Documents");
        titleBar.showBackButton(getBaseActivity());
        titleBar.showHome(getBaseActivity());
        titleBar.setCircleImageView();

    }

    @Override
    public void setListeners() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

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
    public void onItemClick(final int position, Object object) {

        if (object instanceof File) {
            final File file = (File) object;


            UIHelper.showAlertDialog("Press 'OK' to delete this File.", "Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    file.delete();
                    arrFiles.remove(position);
//                    adapterFileDownloded.removeItem(position);
                    adapterFileDownloded.notifyDataSetChanged();
                    UIHelper.showToast(getContext(), "File deleted successfully.");
                }
            }, getContext());

        }


    }
}
