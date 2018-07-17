package edu.aku.ehs.adapters.recyleradapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.ehs.R;
import edu.aku.ehs.activities.BaseActivity;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.models.Subhealthindicator;
import edu.aku.ehs.widget.AnyTextView;

/**
 */
public class GlucoseHistoryAdapter extends RecyclerView.Adapter<GlucoseHistoryAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;
    String status = "Fasting";


    private BaseActivity activity;
    private ArrayList<Subhealthindicator> arrData;

    public GlucoseHistoryAdapter(BaseActivity activity, ArrayList<Subhealthindicator> arrData, OnItemClickListener onItemClickListener) {
        this.arrData = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_blood_glucose, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final Subhealthindicator model = arrData.get(holder.getAdapterPosition());


        holder.txtGlucoType.setText("Glucose (" + getStatus() + ")");
        holder.txtFastingGlucoseStatus.setText(model.getSource());
        holder.txtGlucoseDate.setText(model.getDatetimestr());
        holder.txtGlucose.setText(model.getHealthindicatorvalue());


//        setListener(holder, model);
    }

    private void setListener(final ViewHolder holder, final Subhealthindicator user) {
        holder.cardBloodGlucose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), user);
            }
        });
    }

    public void addItem(ArrayList<Subhealthindicator> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtGlucoseDate)
        AnyTextView txtGlucoseDate;
        @BindView(R.id.txtGlucoType)
        AnyTextView txtGlucoType;
        @BindView(R.id.txtFastingGlucoseStatus)
        AnyTextView txtFastingGlucoseStatus;
        @BindView(R.id.txtGlucose)
        AnyTextView txtGlucose;
        @BindView(R.id.txtUnit)
        AnyTextView txtUnit;
        @BindView(R.id.txtSugarDDTM)
        AnyTextView txtSugarDDTM;
        @BindView(R.id.cardBloodGlucose)
        CardView cardBloodGlucose;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
