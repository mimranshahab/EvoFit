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
public class MeasurmentHistoryAdapter extends RecyclerView.Adapter<MeasurmentHistoryAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private BaseActivity activity;
    private ArrayList<HealthSummaryHistoryModel> arrData;

    public MeasurmentHistoryAdapter(BaseActivity activity, ArrayList<HealthSummaryHistoryModel> arrData, OnItemClickListener onItemClickListener) {
        this.arrData = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_measurment_health_history, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final HealthSummaryHistoryModel model = arrData.get(holder.getAdapterPosition());


        // Height
        holder.txtHeight.setText(model.getModelHeightOrSystolic().getHealthindicatorvalue());
        holder.txtDttm.setText(model.getModelHeightOrSystolic().getDatetimestr());


        // Weight
        holder.txtWeight.setText(model.getModelWeightOrDiastolic().getHealthindicatorvalue());

        // BSA
        double round = Math.round(Math.sqrt(Double.valueOf(model.getModelHeightOrSystolic().getHealthindicatorvalue()) * Double.valueOf(model.getModelWeightOrDiastolic().getHealthindicatorvalue()) / 3600));
        holder.txtBSA.setText(String.valueOf(round));


//        setListener(holder, model);
    }

    private void setListener(final ViewHolder holder, final HealthSummaryHistoryModel user) {
        holder.cardMeasurement.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.txtHeight)
        AnyTextView txtHeight;
        @BindView(R.id.txtHeightUnit)
        AnyTextView txtHeightUnit;
        @BindView(R.id.txtWeight)
        AnyTextView txtWeight;
        @BindView(R.id.txtWeightUnit)
        AnyTextView txtWeightUnit;
        @BindView(R.id.txtBSA)
        AnyTextView txtBSA;
        @BindView(R.id.txtBSAUnit)
        LinearLayout txtBSAUnit;
        @BindView(R.id.txtDttm)
        AnyTextView txtDttm;
        @BindView(R.id.cardMeasurement)
        CardView cardMeasurement;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
