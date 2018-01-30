package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.BaseActivity;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.fragments.ClinicalLaboratoryDetailFragment;
import edu.aku.akuh_health_first.models.LaboratoryModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 * Created by aqsa.sarwar on 1/25/2018.
 */

public class ClinicalLabDetailAdapter extends RecyclerView.Adapter<ClinicalLabDetailAdapter.ViewHolder> {

    private final ArrayList<LaboratoryModel> arrClinicalLabLists;
    private BaseActivity activity;
    private OnItemClickListener onItemClickListener;

    public ClinicalLabDetailAdapter(BaseActivity activity, ArrayList<LaboratoryModel> arrClinicalLabLists, OnItemClickListener onItemClickListener) {
        this.activity = activity;
        this.arrClinicalLabLists = arrClinicalLabLists;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_clinical_lab_detail, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final LaboratoryModel model = arrClinicalLabLists.get(holder.getAdapterPosition());

        holder.txtName.setText(model.getReportName());
        holder.txtRange.setText("Range " + model.getNormalRangeFormatted() + " Normal");
        holder.txtComments.setText(model.getComments());
        setListener(holder, model);
    }

    private void setListener(final ClinicalLabDetailAdapter.ViewHolder holder, final LaboratoryModel neurophysiology) {
        holder.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.getAdapterPosition(), neurophysiology);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrClinicalLabLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName)
        AnyTextView txtName;
        @BindView(R.id.txtRange)
        AnyTextView txtRange;
        @BindView(R.id.txtComments)
        AnyTextView txtComments;
        @BindView(R.id.cardView2)
        CardView cardView2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
