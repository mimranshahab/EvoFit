package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.models.TimelineModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<TimelineModel> arrData;

    public TimelineAdapter(Activity activity, ArrayList<TimelineModel> userList, OnItemClickListener onItemClickListener) {
        this.arrData = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_timeline, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final TimelineModel timelineModel = arrData.get(holder.getAdapterPosition());


        holder.txtFacility.setText(timelineModel.getPatientvisithospitallocation());
        holder.txtVisitDateTime.setText(timelineModel.getPatientvisitdatetime());
        holder.txtDoctorName.setText(timelineModel.getPatientvisitdoctorname());
        holder.txtService.setText(timelineModel.getPatientvisitservice());
        holder.txtVisitType.setText(timelineModel.getPatientvisittype());
        setListener(holder, timelineModel);
    }

    private void setListener(final ViewHolder holder, final TimelineModel neurophysiology) {

    }


    public void addItems(ArrayList<TimelineModel> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtFacility)
        AnyTextView txtFacility;
        @BindView(R.id.txtVisitDateTime)
        AnyTextView txtVisitDateTime;
        @BindView(R.id.txtDoctorName)
        AnyTextView txtDoctorName;
        @BindView(R.id.txtService)
        AnyTextView txtService;
        @BindView(R.id.txtVisitType)
        AnyTextView txtVisitType;
        @BindView(R.id.cardView)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
