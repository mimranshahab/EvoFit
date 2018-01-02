package edu.aku.akuh_health_first.libraries.searchdialog.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import edu.aku.akuh_health_first.libraries.searchdialog.SimpleSearchFilter;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;

import java.util.ArrayList;


/**
 * Created by khanhamza on 8/15/2017.
 */


public abstract class BaseSearchFragment<T extends Searchable> extends BaseFragment implements Filterable {
    private Filter mFilter;
    private ArrayList<T> mItems;
    private FilterResultListener<T> mFilterResultListener;
    private OnPerformFilterListener mOnPerformFilterListener;
    protected boolean mFilterAutomatically = true;
    //    private Context context;
    private SearchResultListener<T> mSearchResultListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getView(view);
        return LayoutInflater.from(getContext()).inflate(getLayoutResId(), null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void getView(View view);

    @LayoutRes
    protected abstract int getLayoutResId();

    public void setSearchListAndAdapter(RecyclerView.Adapter adapter, String highlightColor, boolean setHighlightPartsInCommon, SearchResultListener setSearchResultListener, BaseSearchFragment setSearchFragment, RecyclerView rvItems) {

//        if (adapter instanceof ChatsSearchAdapter) {
//            ChatsSearchAdapter chatSearchAdapter = (ChatsSearchAdapter) adapter;
//            chatSearchAdapter.setHighlightColor(highlightColor);
//            chatSearchAdapter.setHighlightPartsInCommon(setHighlightPartsInCommon);
//            chatSearchAdapter.setSearchResultListener(setSearchResultListener);
//            chatSearchAdapter.setSearchFragment(setSearchFragment);
//            rvItems.setLayoutManager(new CustomLayoutManager(getContext(),
//                    LinearLayoutManager.VERTICAL, false) {
//            });
//            rvItems.setAdapter(chatSearchAdapter);
//            chatSearchAdapter.notifyDataSetChanged();
//        }else if (adapter instanceof ForwardMessageAdapter) {
//            ForwardMessageAdapter forwardMessageAdapter = (ForwardMessageAdapter) adapter;
//            forwardMessageAdapter.setHighlightColor(highlightColor);
//            forwardMessageAdapter.setHighlightPartsInCommon(setHighlightPartsInCommon);
//            forwardMessageAdapter.setSearchResultListener(setSearchResultListener);
//            forwardMessageAdapter.setSearchFragment(setSearchFragment);
//            rvItems.setLayoutManager(new CustomLayoutManager(getContext(),
//                    LinearLayoutManager.VERTICAL, false));
//            rvItems.setAdapter(forwardMessageAdapter);
//            forwardMessageAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null)
            mFilter = new SimpleSearchFilter<>(mItems, mFilterResultListener, true, 1f);
        return mFilter;
    }


    public BaseSearchFragment setFilter(Filter filter) {
        mFilter = filter;
        return this;
    }

    public ArrayList<T> getItems() {
        return mItems;
    }

    public BaseSearchFragment setItems(ArrayList<T> items) {
        mItems = items;
        return this;
    }

    public FilterResultListener<T> getFilterResultListener() {
        return mFilterResultListener;
    }

    public BaseSearchFragment setFilterResultListener(FilterResultListener<T> filterResultListener) {
        mFilterResultListener = filterResultListener;
        return this;
    }

    public BaseSearchFragment setOnPerformFilterListener(OnPerformFilterListener onPerformFilterListener) {
        mOnPerformFilterListener = onPerformFilterListener;
        return this;
    }

    public OnPerformFilterListener getOnPerformFilterListener() {
        return mOnPerformFilterListener;
    }

    public boolean isFilterAutomatically() {
        return mFilterAutomatically;
    }

    public BaseSearchFragment setFilterAutomatically(boolean filterAutomatically) {
        mFilterAutomatically = filterAutomatically;
        return this;
    }

    public BaseSearchFragment setSearchResultListener(
            SearchResultListener<T> searchResultListener) {
        mSearchResultListener = searchResultListener;
        return this;
    }

}
