package edu.aku.ehs.adapters.recyleradapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.ehs.R;
import edu.aku.ehs.activities.BaseActivity;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.models.HealthSummaryHistoryModel;
import edu.aku.ehs.widget.AnyTextView;

/**
 */
public class BPHistoryAdapter extends RecyclerView.Adapter<BPHistoryAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;
    @BindView(R.id.txtBPsystolic)
    AnyTextView txtBPsystolic;
    @BindView(R.id.txtBPdiastolic)
    AnyTextView txtBPdiastolic;
    @BindView(R.id.contBPResult)
    LinearLayout contBPResult;
    @BindView(R.id.txtBPUnit)
    AnyTextView txtBPUnit;
    @BindView(R.id.txtBPDDTM)
    AnyTextView txtBPDDTM;
    @BindView(R.id.cardBP)
    CardView cardBP;


    private BaseActivity activity;
    private ArrayList<HealthSummaryHistoryModel> arrData;

    public BPHistoryAdapter(BaseActivity activity, ArrayList<HealthSummaryHistoryModel> arrData, OnItemClickListener onItemClickListener) {
        this.arrData = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_bp_health_history, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final HealthSummaryHistoryModel model = arrData.get(holder.getAdapterPosition());


        // Height
        holder.txtBPsystolic.setText(model.getModelHeightOrSystolic().getHealthindicatorvalue());
        holder.txtBPDDTM.setText(model.getModelHeightOrSystolic().getDatetimestr());


        // Weight
        holder.txtBPdiastolic.setText(model.getModelWeightOrDiastolic().getHealthindicatorvalue());


//        setListener(holder, model);
    }

    private void setListener(final ViewHolder holder, final HealthSummaryHistoryModel user) {
        holder.cardBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), user);
            }
        });
    }

    public void addItem(ArrayList<HealthSummaryHistoryModel> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtBPsystolic)
        AnyTextView txtBPsystolic;
        @BindView(R.id.txtBPdiastolic)
        AnyTextView txtBPdiastolic;
        @BindView(R.id.contBPResult)
        LinearLayout contBPResult;
        @BindView(R.id.txtBPUnit)
        AnyTextView txtBPUnit;
        @BindView(R.id.txtBPDDTM)
        AnyTextView txtBPDDTM;
        @BindView(R.id.cardBP)
        CardView cardBP;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
