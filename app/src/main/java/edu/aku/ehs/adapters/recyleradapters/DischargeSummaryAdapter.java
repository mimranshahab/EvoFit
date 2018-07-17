package edu.aku.ehs.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.ehs.R;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.models.DischargeSummaryModel;
import edu.aku.ehs.widget.AnyTextView;

/**
 */
public class DischargeSummaryAdapter extends RecyclerView.Adapter<DischargeSummaryAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<DischargeSummaryModel> arrdischargeSummaryModels;

    public DischargeSummaryAdapter(Activity activity, ArrayList<DischargeSummaryModel> userList, OnItemClickListener onItemClickListener) {
        this.arrdischargeSummaryModels = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_discharge_summary, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final DischargeSummaryModel DischargeSummaryModel = arrdischargeSummaryModels.get(holder.getAdapterPosition());

        holder.txtReportName.setText(DischargeSummaryModel.getSummaryServiceID());
        holder.txtAdmissionDate.setText(DischargeSummaryModel.getAdmissionDateTime());
        holder.txtDrName.setText("Dr." + DischargeSummaryModel.getPhyName());

        holder.txtDischargeDate.setText(DischargeSummaryModel.getDischargeDateTime());
        holder.txtDischargeDisposition.setText(DischargeSummaryModel.getDischargeDisposition());
        setListener(holder, DischargeSummaryModel);

        if (DischargeSummaryModel.getDischargeDateTime() == null || DischargeSummaryModel.getDischargeDateTime().isEmpty()) {
            holder.contDischargeDate.setVisibility(View.GONE);
        } else {
            holder.contDischargeDate.setVisibility(View.VISIBLE);
        }

//        setViews(holder, activity.getResources().getColor(R.color.base_dark_gray), R.drawable.rounded_box_filled_base_grey, R.drawable.b_dischargesummary_transparent);


    }

    private void setViews(ViewHolder holder, int color, int backgroundResource, int img_transparent) {
//        holder.cardView2.setCardBackgroundColor(color);
//        holder.txtStatusType.setBackgroundResource(backgroundResource);
//        holder.btnReportColorCode1.setBackgroundResource(backgroundResource);
//        holder.imgIcon.setColorFilter(color);
//        holder.imgTransparent.setImageResource(img_transparent);
    }

    private void setListener(final ViewHolder holder, final DischargeSummaryModel DischargeSummaryModel) {
        holder.btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), DischargeSummaryModel);
            }
        });
    }


    public void addItem(ArrayList<DischargeSummaryModel> homeCategories) {
        this.arrdischargeSummaryModels = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrdischargeSummaryModels.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtAdmissionDate)
        AnyTextView txtAdmissionDate;
        @BindView(R.id.txtDischargeDate)
        AnyTextView txtDischargeDate;
        @BindView(R.id.contDischargeDate)
        LinearLayout contDischargeDate;
        @BindView(R.id.imgIcon)
        ImageView imgIcon;
        @BindView(R.id.txtReportName)
        AnyTextView txtReportName;
        @BindView(R.id.txtDrName)
        AnyTextView txtDrName;
        @BindView(R.id.txtDischargeDisposition)
        AnyTextView txtDischargeDisposition;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.contProfile)
        LinearLayout contProfile;
        @BindView(R.id.frameColorCode)
        LinearLayout frameColorCode;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.btnSummary)
        AnyTextView btnSummary;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
