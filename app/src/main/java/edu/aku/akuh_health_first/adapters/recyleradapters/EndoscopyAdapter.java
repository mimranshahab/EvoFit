package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
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
import edu.aku.akuh_health_first.models.EndoscopyModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class EndoscopyAdapter extends RecyclerView.Adapter<EndoscopyAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;

    private Activity activity;
    private ArrayList<EndoscopyModel> arrData;

    public EndoscopyAdapter(Activity activity, ArrayList<EndoscopyModel> arrayList, OnItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_endoscopy, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final EndoscopyModel model = arrData.get(holder.getAdapterPosition());

        holder.txtProcedures.setText(model.getProcedures());
        holder.txtDateTime.setText(model.getProceduredttm());
        holder.txtMRNumber.setText(model.getMrnumber());
        holder.txtReportTypeId.setText(model.getReporttypeid());
        setListener(holder, model);
    }

    private void setListener(final ViewHolder holder, final EndoscopyModel neurophysiology) {
        holder.btnShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), neurophysiology);
            }
        });
    }


    public void addItem(ArrayList<EndoscopyModel> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtProcedures)
        AnyTextView txtProcedures;
        @BindView(R.id.txtReportTypeId)
        AnyTextView txtReportTypeId;
        @BindView(R.id.txtMRNumber)
        AnyTextView txtMRNumber;
        @BindView(R.id.txtDateTime)
        AnyTextView txtDateTime;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.btnShowReport)
        Button btnShowReport;
        @BindView(R.id.cardView2)
        CardView cardView2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
