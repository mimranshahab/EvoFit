package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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


        holder.txtFacility.setText(timelineModel.getPatientVisitHospitalLocation());
        holder.txtVisitDateTime.setText(timelineModel.getPatientVisitDateTime());
        holder.txtDoctorName.setText(timelineModel.getPatientVisitDoctorName());
        holder.txtService.setText(timelineModel.getPatientVisitService());
        holder.txtVisitType.setText(timelineModel.getPatientVisitType());
        /*
        set banner color
        In-Patient ---->Blue
        Out-Patient ---->Orange
        ER ---->Red
        Clinical ---->Green

         */
        if (timelineModel.getPatientVisitType().equals("ER")) {
            holder.llcolorCode.setBackgroundResource(R.color.c_brick_red);
        } else if (timelineModel.getPatientVisitType().equals("IN")) {
            holder.llcolorCode.setBackgroundResource(R.color.base_dark_blue);

        } else if (timelineModel.getPatientVisitType().equals("CLI")) {
            holder.llcolorCode.setBackgroundResource(R.color.c_dark_green);

        } else {
            holder.llcolorCode.setBackgroundResource(R.color.base_amber);

        }


        setListener(holder, timelineModel);
    }

    private void setListener(final ViewHolder holder, final TimelineModel timelineModel) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), timelineModel);
            }
        });
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
        @BindView(R.id.llcolorCode)
        LinearLayout llcolorCode;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
