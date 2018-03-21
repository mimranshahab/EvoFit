package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.models.DischargeSummaryModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

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
                .inflate(R.layout.item_cps_nps_endo_summary_, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final DischargeSummaryModel DischargeSummaryModel = arrdischargeSummaryModels.get(holder.getAdapterPosition());

        holder.txtName.setText(DischargeSummaryModel.getSummaryServiceID());
        holder.txtDateTime.setText(DischargeSummaryModel.getLastFileDttm());
        holder.txtDrName.setText(DischargeSummaryModel.getPhyName());
        holder.txtStatusType.setVisibility(View.INVISIBLE);
        setListener(holder, DischargeSummaryModel);
        holder.RlReport.setVisibility(View.VISIBLE);
    }

    private void setListener(final ViewHolder holder, final DischargeSummaryModel DischargeSummaryModel) {
        holder.RlReport.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.txtDateTime)
        AnyTextView txtDateTime;
        @BindView(R.id.txtStatusType)
        AnyTextView txtStatusType;
        @BindView(R.id.imgIcon)
        CircleImageView imgUser;
        @BindView(R.id.txtName)
        AnyTextView txtName;
        @BindView(R.id.txtDrName)
        AnyTextView txtDrName;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.btnReportColorCode1)
        AnyTextView btnReportColorCode1;
        @BindView(R.id.RlReport)
        RelativeLayout RlReport;

        @BindView(R.id.frameColorCode)
        FrameLayout frameColorCode;
        @BindView(R.id.cardView2)
        CardView cardView2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
