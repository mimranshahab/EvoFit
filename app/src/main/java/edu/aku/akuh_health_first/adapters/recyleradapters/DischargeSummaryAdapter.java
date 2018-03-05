package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.models.DischargeSummaryModel;
import edu.aku.akuh_health_first.views.AnyTextView;

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
                .inflate(R.layout.item_neurophysiology, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final DischargeSummaryModel DischargeSummaryModel = arrdischargeSummaryModels.get(holder.getAdapterPosition());
//        holder.txtHospLoc.setText("AdmissionDateTime:  "+ DischargeSummaryModel.getAdmissionDateTime());


        holder.txtAdmNo.setVisibility(View.GONE);
        holder.txtHospLoc.setVisibility(View.GONE);
        holder.txtReqNum.setVisibility(View.GONE);

        holder.txtReqDatetime.setText("MRNumber:  "+ DischargeSummaryModel.getMRNumber());
//        holder.txtReqNum.setText("Physician Name:  "+DischargeSummaryModel.getPhyName());
//        holder.txtStatus.setText("Visit Type:  "+DischargeSummaryModel.getVisitType());
        holder.txtService.setText("Last File DTTM:  "+DischargeSummaryModel.getLastFileDttm());
        holder.btnShowReport.setVisibility(View.VISIBLE);
        setListener(holder, DischargeSummaryModel);
    }

    private void setListener(final ViewHolder holder, final DischargeSummaryModel DischargeSummaryModel) {
        holder.btnShowReport.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.txtHospLoc)
        AnyTextView txtHospLoc;
        @BindView(R.id.txtReqDatetime)
        AnyTextView txtReqDatetime;
        @BindView(R.id.txtReqNum)
        AnyTextView txtReqNum;
        @BindView(R.id.txtAdmNo)
        AnyTextView txtAdmNo;
        @BindView(R.id.txtService)
        AnyTextView txtService;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.btnShowReport)
        Button btnShowReport;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
