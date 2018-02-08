package edu.aku.akuh_health_first.libraries.swipetodelete;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gaurav on 27/8/16.
 */
public abstract class CdsRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    private ArrayList<T> mList = null;
    private Context mContext;

    public CdsRecyclerViewAdapter(Context context, ArrayList<T> list) {
        this.mContext = context;
        mList = list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindHolder((VH) holder, position);
    }

    public void bindHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public T getItem(int position) {
        return mList.get(position);
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(ArrayList<T> list) {
        mList = list;
    }

    public void addItem(T item, int position) {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void moveItem(int fromPos, int toPos) {
        if (fromPos < toPos) {
            for (int i = fromPos; i < toPos; i++) {
                Collections.swap(mList, i, i + 1);
            }
        } else {
            for (int i = fromPos; i > toPos; i--) {
                Collections.swap(mList, i, i - 1);
            }
        }
        notifyItemMoved(fromPos, toPos);
    }
}
