package edu.aku.ehs.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.ehs.R;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.models.LabHistoryModel;
import edu.aku.ehs.widget.AnyTextView;

/**
 */
public class ClinicalLabHistoryAdapter extends RecyclerView.Adapter<ClinicalLabHistoryAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<LabHistoryModel> arrData;

    public ClinicalLabHistoryAdapter(Activity activity, ArrayList<LabHistoryModel> arrayList, OnItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lab_history, parent, false));

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        LabHistoryModel labHistoryModel = arrData.get(holder.getAdapterPosition());
        if (holder.getAdapterPosition() == 0) {
            holder.txtDate.setText("Current");
        } else {
            holder.txtDate.setText(labHistoryModel.getSortdttm());
        }
        holder.txtResult.setText(labHistoryModel.getResult());


    }

    private void setListener(final ViewHolder holder, final Object object) {
//        holder.contLastLab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClick.onItemClick(holder.getAdapterPosition(), object);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDate)
        AnyTextView txtDate;
        @BindView(R.id.txtResult)
        AnyTextView txtResult;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
