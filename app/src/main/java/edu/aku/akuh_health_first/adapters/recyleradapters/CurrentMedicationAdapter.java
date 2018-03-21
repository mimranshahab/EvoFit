package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.models.MedicationProfileModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 */
public class CurrentMedicationAdapter extends RecyclerView.Adapter<CurrentMedicationAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<MedicationProfileModel> arrData;


    public CurrentMedicationAdapter(Activity activity, ArrayList<MedicationProfileModel> arrData, OnItemClickListener onItemClickListener) {
        this.arrData = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_medication, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final MedicationProfileModel model = arrData.get(holder.getAdapterPosition());


        holder.txtMedicineName.setText(model.getRxmedmedication());

        holder.txtFrequency.setText(model.getRxmedfrequencydescription());
        holder.txtRoute.setText(model.getRxmedroutedescription()); // Null in History
        holder.txtStartDate.setText(model.getRxmedstartdatetime());
        holder.txtStopDate.setText(model.getRxmedstopdatetime());


//        setListener(holder, timelineModel);
    }

    private void setListener(final ViewHolder holder, final MedicationProfileModel timelineModel) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), timelineModel);
            }
        });
    }


    public void addItems(ArrayList<MedicationProfileModel> arrData) {
        this.arrData = arrData;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
//        return getCount();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtMedicineName)
        AnyTextView txtMedicineName;
        @BindView(R.id.txtFrequency)
        AnyTextView txtFrequency;
        @BindView(R.id.txtRoute)
        AnyTextView txtRoute;
        @BindView(R.id.txtStartDate)
        AnyTextView txtStartDate;
        @BindView(R.id.txtStopDate)
        AnyTextView txtStopDate;
        @BindView(R.id.contProfile)
        LinearLayout contProfile;
        @BindView(R.id.frameColorCode)
        FrameLayout frameColorCode;
        @BindView(R.id.cardView)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
