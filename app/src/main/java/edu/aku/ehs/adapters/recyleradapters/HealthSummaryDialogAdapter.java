package edu.aku.ehs.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.ehs.R;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.models.ShortMessageMobile;
import edu.aku.ehs.widget.AnyTextView;

/**
 */
public class HealthSummaryDialogAdapter extends RecyclerView.Adapter<HealthSummaryDialogAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;



    private Activity activity;
    private ArrayList<ShortMessageMobile> arrData;

    public HealthSummaryDialogAdapter(Activity activity, ArrayList<ShortMessageMobile> arrayList, OnItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_healthsummary_dialog, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final ShortMessageMobile model = arrData.get(holder.getAdapterPosition());

        holder.txtShortMessage.setText(model.getTitle());
        holder.txtDate.setText(model.getMessage());

    }

    public void addItem(ArrayList<ShortMessageMobile> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtShortMessage)
        AnyTextView txtShortMessage;
        @BindView(R.id.txtDate)
        AnyTextView txtDate;
        @BindView(R.id.contLastLab)
        LinearLayout contLastLab;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
