package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.enums.HealthSummaryTypes;
import edu.aku.akuh_health_first.models.DetailHealthSummaryModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 */
public class HealthSummaryAdapter extends RecyclerView.Adapter<HealthSummaryAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<DetailHealthSummaryModel> arrData;

    public HealthSummaryAdapter(Activity activity, ArrayList<DetailHealthSummaryModel> arrayList, OnItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_healthsummary_v2, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final DetailHealthSummaryModel model = arrData.get(holder.getAdapterPosition());

        if (model.getShortMessageMobile().getTitle() == null || model.getShortMessageMobile().getTitle().isEmpty()) {
            holder.txtShortMessage.setVisibility(View.GONE);
        } else {
            holder.txtShortMessage.setVisibility(View.VISIBLE);
            holder.txtShortMessage.setText(model.getShortMessageMobile().getTitle());
        }

        if (model.getShortMessageMobile().getMessage() == null || model.getShortMessageMobile().getMessage().isEmpty()) {
            holder.txtDate.setVisibility(View.GONE);
            holder.txtDate.setText(model.getShortMessageMobile().getMessage());
        } else {
            // setting date in title if title is empty and date exist
            if (model.getShortMessageMobile().getTitle() == null || model.getShortMessageMobile().getTitle().isEmpty()) {
                holder.txtShortMessage.setVisibility(View.VISIBLE);
                holder.txtDate.setVisibility(View.INVISIBLE);
                holder.txtShortMessage.setText(model.getShortMessageMobile().getMessage());
            } else {
                holder.txtDate.setVisibility(View.VISIBLE);
                holder.txtDate.setText(model.getShortMessageMobile().getMessage());
            }

        }

        if (model.getStatus() == null || model.getStatus().isEmpty()) {

            holder.txtStatusType.setVisibility(View.GONE);
            holder.imgStatus.setVisibility(View.GONE);

        } else {
            holder.txtStatusType.setVisibility(View.VISIBLE);
            holder.imgStatus.setVisibility(View.VISIBLE);
            holder.txtStatusType.setText(model.getStatus());

            if (model.getStatus().equalsIgnoreCase("Completed")) {
                colorCodes(holder, activity.getResources().getColor(R.color.summary_green));
            } else if (model.getStatus().equalsIgnoreCase("Pending")) {
                colorCodes(holder, activity.getResources().getColor(R.color.summary_orange));
            } else {
                colorCodes(holder, activity.getResources().getColor(R.color.summary_blue));
            }
        }
        holder.txtTitle.setText(model.getSummaryTitle());

        HealthSummaryTypes state = HealthSummaryTypes.fromStringForm(model.getWebandMobileModel().getLink());

        if (!(state == null)) {
            switch (state) {
                case Allergies:
                    holder.imgTitle.setImageResource(R.drawable.a_allergies);
                    break;
                case ClinicalLaboratory:
                    holder.imgTitle.setImageResource(R.drawable.a_lab_report);
                    break;
                case Radiology:
                    holder.imgTitle.setImageResource(R.drawable.a_radiology);
                    break;
                case MedicationProfile:
                    holder.imgTitle.setImageResource(R.drawable.a_activemedications);
                    break;
                case ImmunizationProfile:
                    holder.imgTitle.setImageResource(R.drawable.a_immunization);
                    break;
                case LastVisit:
                    holder.imgTitle.setImageResource(R.drawable.a_futureappointment);
                    break;
                case FutureAppointment:
                    holder.imgTitle.setImageResource(R.drawable.a_futureappointment);
                    break;
            }
        }


        boolean isLinkToHistory = Boolean.parseBoolean(model.getLinkToHistory());

        if (isLinkToHistory) {
            holder.imgClick.setVisibility(View.VISIBLE);
        } else {
            if (model.getDetailedMessageMobile().isEmpty()) {
                holder.imgClick.setVisibility(View.GONE);
            } else {
                holder.imgClick.setVisibility(View.VISIBLE);
            }
        }


//        if (holder.txtShortMessage.getVisibility() == View.INVISIBLE && holder.txtStatusType.getVisibility() == View.GONE) {
//            holder.contShortMessageAndStatus.setVisibility(View.GONE);
//        } else {
//            holder.contShortMessageAndStatus.setVisibility(View.VISIBLE);
//        }
        setListener(holder, model);

    }

    private void colorCodes(ViewHolder holder, int color) {
        holder.txtStatusType.setTextColor(color);
        holder.imgStatus.setColorFilter(color);
    }

    private void setListener(final ViewHolder holder, final DetailHealthSummaryModel model) {
        holder.contLastLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), model);
            }
        });
    }


    public void addItem(ArrayList<DetailHealthSummaryModel> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgTitle)
        ImageView imgTitle;
        @BindView(R.id.txtTitle)
        AnyTextView txtTitle;
        @BindView(R.id.txtShortMessage)
        AnyTextView txtShortMessage;
        @BindView(R.id.txtDate)
        AnyTextView txtDate;
        @BindView(R.id.txtStatusType)
        AnyTextView txtStatusType;
        @BindView(R.id.contLastLab)
        LinearLayout contLastLab;
        @BindView(R.id.frameColorCode)
        FrameLayout frameColorCode;
        @BindView(R.id.imgClick)
        ImageView imgClick;
        @BindView(R.id.imgStatus)
        ImageView imgStatus;
        @BindView(R.id.contShortMessageAndStatus)
        LinearLayout contShortMessageAndStatus;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
