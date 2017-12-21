package com.structure.libraries.searchdialog;

import android.support.annotation.NonNull;
import android.widget.Filter;

import com.structure.libraries.searchdialog.core.BaseFilter;
import com.structure.libraries.searchdialog.core.FilterResultListener;
import com.structure.libraries.searchdialog.core.Searchable;

import java.util.ArrayList;
import java.util.List;


public class SimpleSearchFilter<T extends Searchable> extends BaseFilter {
    private ArrayList<T> mItems;
    private FilterResultListener mFilterResultListener;
    private boolean mCheckLCS;
    private final float mAccuracyPercentage;

    public SimpleSearchFilter(List<T> objects, @NonNull FilterResultListener filterResultListener,
                              boolean checkLCS, float accuracyPercentage) {
        mFilterResultListener = filterResultListener;
        mCheckLCS = checkLCS;
        mAccuracyPercentage = accuracyPercentage;
        mItems = new ArrayList<>();
        synchronized (this) {
            mItems.addAll(objects);
        }
    }

    public SimpleSearchFilter(List<T> objects, @NonNull FilterResultListener filterResultListener) {
        this(objects, filterResultListener, false, 0);
    }

    @Override
    protected Filter.FilterResults performFiltering(CharSequence chars) {
        doBeforeFiltering();
        String filterSeq = chars.toString().toLowerCase();
        Filter.FilterResults result = new Filter.FilterResults();
        if (filterSeq != null && filterSeq.length() > 0) {
            ArrayList<T> filter = new ArrayList<>();
            for (T object : mItems)
                if (object.getTitle().toLowerCase().contains(filterSeq))
                    filter.add(object);
                else if (mCheckLCS)
                    if (StringsHelper.lcs(object.getTitle(), filterSeq).length()
                            > object.getTitle().length() * mAccuracyPercentage)
                        filter.add(object);

            result.values = filter;
            result.count = filter.size();
        } else {
            synchronized (this) {
                result.values = mItems;
                result.count = mItems.size();
            }
        }
        return result;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        ArrayList<T> filtered = (ArrayList<T>) results.values;
        mFilterResultListener.onFilter(filtered);
        doAfterFiltering();
    }
}