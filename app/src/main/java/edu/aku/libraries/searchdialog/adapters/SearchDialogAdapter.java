package edu.aku.libraries.searchdialog.adapters;//package com.structure.libraries.searchdialog.adapters;
//
//import android.content.Context;
//import android.os.Build;
//import android.support.annotation.ColorRes;
//import android.support.annotation.IdRes;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.structure.R;
//import com.structure.libraries.searchdialog.StringsHelper;
//import com.structure.libraries.searchdialog.core.BaseSearchDialogCompat;
//import com.structure.libraries.searchdialog.core.SearchResultListener;
//import com.structure.libraries.searchdialog.core.Searchable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SearchDialogAdapter<T extends Searchable>
//        extends RecyclerView.Adapter<SearchDialogAdapter.ViewHolderItem> {
//    protected Context mContext;
//    private List<T> mItems = new ArrayList<>();
//    private LayoutInflater mLayoutInflater;
//    private int mLayout;
//    private SearchResultListener mSearchResultListener;
//    private AdapterViewBinder<T> mViewBinder;
//    private String mSearchTag;
//    private boolean mHighlightPartsInCommon = true;
//
//    private BaseSearchDialogCompat mSearchDialog;
//
//    public SearchDialogAdapter(Context context, @LayoutRes int layout, List<T> items) {
//        this(context,layout,null, items);
//    }
//
//    public SearchDialogAdapter(Context context, AdapterViewBinder<T> viewBinder,
//                               @LayoutRes int layout, List<T> items) {
//        this(context,layout,viewBinder, items);
//    }
//
//    public SearchDialogAdapter(Context context, @LayoutRes int layout,
//                               @Nullable AdapterViewBinder<T> viewBinder,
//                               List<T> items) {
//        this.mContext = context;
//        this.mLayoutInflater = LayoutInflater.from(context);
//        this.mItems = items;
//        this.mLayout = layout;
//        this.mViewBinder = viewBinder;
//    }
//
//    public List<T> getItems() {
//        return mItems;
//    }
//
//    public void setItems(List<T> objects) {
//        this.mItems = objects;
//        notifyDataSetChanged();
//    }
//
//    public T getItem(int position) {
//        return mItems.get(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mItems != null)
//            return mItems.size();
//        else return 0;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public SearchDialogAdapter<T> setViewBinder(AdapterViewBinder<T> viewBinder) {
//        this.mViewBinder = viewBinder;
//        return this;
//    }
//
//    @Override
//    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
//        View convertView = mLayoutInflater.inflate(mLayout, parent, false);
//        convertView.setTag(new ViewHolderItem(convertView));
//        ViewHolderItem viewHolder = (ViewHolderItem) convertView.getTag();
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolderItem holder, int position) {
//        initializeViews(getItem(position), holder, position);
//    }
//    private void initializeViews(final T object, final ViewHolderItem holder,
//                                 final int position) {
//        if(mViewBinder != null)
//            mViewBinder.bind(holder, object, position);
//        TextView text = holder.getViewById(android.R.id.text1);
//        text.setTextColor(getColor(R.color.searchDialogResultColor));
//        if(mSearchTag != null && mHighlightPartsInCommon)
//            text.setText(StringsHelper.highlightLCS(object.getTitle(), getSearchTag(),
//                    getColor(R.color.searchDialogResultHighlightColor)));
//        else text.setText(object.getTitle());
//
//        if (mSearchResultListener != null)
//            holder.getBaseView().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mSearchResultListener.onSelected(mSearchDialog, object, position);
//                }
//            });
//    }
//
//    public SearchResultListener getSearchResultListener(){
//        return mSearchResultListener;
//    }
//    public void setSearchResultListener(SearchResultListener searchResultListener){
//        this.mSearchResultListener = searchResultListener;
//    }
//
//    public SearchDialogAdapter setSearchTag(String searchTag) {
//        mSearchTag = searchTag;
//        return this;
//    }
//
//    public String getSearchTag() {
//        return mSearchTag;
//    }
//
//    public SearchDialogAdapter setHighlightPartsInCommon(boolean highlightPartsInCommon) {
//        mHighlightPartsInCommon = highlightPartsInCommon;
//        return this;
//    }
//
//    public boolean isHighlightPartsInCommon() {
//        return mHighlightPartsInCommon;
//    }
//
//    public SearchDialogAdapter setSearchDialog(BaseSearchDialogCompat searchDialog) {
//        mSearchDialog = searchDialog;
//        return this;
//    }
//
//    private int getColor(@ColorRes int colorResId){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            return mContext.getResources().getColor(colorResId, null);
//        } else return mContext.getResources().getColor(colorResId);
//    }
//
//    public static class ViewHolderItem extends RecyclerView.ViewHolderItem {
//        private View mBaseView;
//
//        public ViewHolderItem(View view) {
//            super(view);
//            mBaseView = view;
//        }
//
//        public View getBaseView() {
//            return mBaseView;
//        }
//        public <T> T getViewById(@IdRes int id){
//            return (T)mBaseView.findViewById(id);
//        }
//        public void clearAnimation(@IdRes int id)
//        {
//            mBaseView.findViewById(id).clearAnimation();
//        }
//    }
//
//    public interface AdapterViewBinder<T> {
//        void bind(ViewHolderItem holder, T item, int position);
//    }
//}