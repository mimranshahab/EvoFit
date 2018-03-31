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
                .inflate(R.layout.item_healthsummary, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final DetailHealthSummaryModel model = arrData.get(holder.getAdapterPosition());

        if (model.getShortmessagemobile().getTitle() == null || model.getShortmessagemobile().getTitle().isEmpty()) {
            holder.txtShortMessage.setVisibility(View.GONE);
        } else {
            holder.txtShortMessage.setVisibility(View.VISIBLE);
            holder.txtShortMessage.setText(model.getShortmessagemobile().getTitle());
        }

        if (model.getShortmessagemobile().getMessage() == null || model.getShortmessagemobile().getMessage().isEmpty()) {

            holder.txtDate.setVisibility(View.GONE);
            holder.txtDate.setText(model.getShortmessagemobile().getMessage());
        } else {
            holder.txtDate.setVisibility(View.VISIBLE);
            holder.txtDate.setText(model.getShortmessagemobile().getMessage());
        }

        holder.txtTitle.setText(model.getSummarytitle());

        HealthSummaryTypes state = HealthSummaryTypes.fromStringForm(model.getWebandmobilemodel().getLink());

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
                    holder.imgTitle.setImageResource(R.drawable.a_last_visits);
                    break;
                case FutureAppointment:
                    holder.imgTitle.setImageResource(R.drawable.a_futureappointment);
                    break;
            }
        }


        boolean isLinkToHistory = Boolean.parseBoolean(model.getLinktohistory());

        if (isLinkToHistory) {
            holder.imgClick.setVisibility(View.VISIBLE);
        } else {
            if (model.getDetailMessageMobileArray().isEmpty()) {
                holder.imgClick.setVisibility(View.GONE);
            } else {
                holder.imgClick.setVisibility(View.VISIBLE);
            }
        }
        setListener(holder, model);

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
        @BindView(R.id.contLastLab)
        LinearLayout contLastLab;
        @BindView(R.id.frameColorCode)
        FrameLayout frameColorCode;
        @BindView(R.id.imgClick)
        ImageView imgClick;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
