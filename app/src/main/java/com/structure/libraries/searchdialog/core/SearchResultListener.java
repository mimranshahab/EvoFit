package com.structure.libraries.searchdialog.core;

/**
 * Created by MADNESS on 5/14/2017.
 */

public interface SearchResultListener<T> {
    // FIXME: 15-Aug-17 Changing dialog to fragment
//    void onSelected(BaseSearchDialogCompat dialog, T item, int position);
    void onSelected(BaseSearchFragment fragment, T item, int position);
}
