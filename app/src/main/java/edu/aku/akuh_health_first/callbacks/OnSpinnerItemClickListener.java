package edu.aku.akuh_health_first.callbacks;

import android.widget.SpinnerAdapter;

import edu.aku.akuh_health_first.adapters.recyleradapters.SpinnerDialogAdapter;

public interface OnSpinnerItemClickListener {
    void onItemClick(int position, Object object, SpinnerDialogAdapter adapter);
}
