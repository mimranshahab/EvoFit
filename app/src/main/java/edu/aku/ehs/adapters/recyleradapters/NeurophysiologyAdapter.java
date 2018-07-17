package edu.aku.ehs.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import edu.aku.ehs.R;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.models.NeurophysiologyModel;
import edu.aku.ehs.widget.AnyTextView;

/**
 */
public class NeurophysiologyAdapter extends RecyclerView.Adapter<NeurophysiologyAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;

    private Activity activity;
    private ArrayList<NeurophysiologyModel> neurophysiologyModelArrayList;

    public NeurophysiologyAdapter(Activity activity, ArrayList<NeurophysiologyModel> userList, OnItemClickListener onItemClickListener) {
        this.neurophysiologyModelArrayList = userList;
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

        final NeurophysiologyModel model = neurophysiologyModelArrayList.get(holder.getAdapterPosition());
        holder.txtDateTime.setText(model.getRequestServiceDateTime());

        holder.txtName.setText(model.getService());

        holder.RlGraph.setVisibility(View.GONE);
        holder.RlReport.setVisibility(View.VISIBLE);
        holder.txtStatusType.setVisibility(View.VISIBLE);
        holder.imgStatus.setVisibility(View.VISIBLE);
        holder.txtDrName.setText(model.getLastFileUser());


        holder.txtStatusType.setText(model.getStatus());
        if (model.getStatus().equalsIgnoreCase("Finalized")) {
            holder.RlReport.setEnabled(true);
            holder.RlReport.setAlpha(1f);
            setViews(holder, activity.getResources().getColor(R.color.base_green), R.drawable.rounded_box_filled_base_green, R.drawable.b_neurophysiology_transparent);

        } else {
            setViews(holder, activity.getResources().getColor(R.color.base_amber), R.drawable.rounded_box_filled_base_amber, R.drawable.b_neurophysiology_transparent);
            holder.RlReport.setEnabled(false);
            holder.RlReport.setAlpha(0.15f);

        }

        setListener(holder, model);

    }

    private void setViews(ViewHolder holder, int color, int backgroundResource, int circular_background) {
        holder.cardView2.setCardBackgroundColor(color);
        holder.txtStatusType.setTextColor(color);
        holder.imgTransparent.setImageResource(circular_background);
        holder.btnReportColorCode1.setBackgroundResource(backgroundResource);
        holder.imgIcon.setColorFilter(color);
        holder.imgStatus.setColorFilter(color);
    }

    private void setListener(final ViewHolder holder, final NeurophysiologyModel neurophysiologyModel) {
        holder.RlReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), neurophysiologyModel);
            }
        });
    }


    public void addItem(ArrayList<NeurophysiologyModel> homeCategories) {
        this.neurophysiologyModelArrayList = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return neurophysiologyModelArrayList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDateTime)
        AnyTextView txtDateTime;
        @BindView(R.id.txtStatusType)
        AnyTextView txtStatusType;
        @BindView(R.id.imgIcon)
        ImageView imgIcon;
        @BindView(R.id.imgStatus)
        ImageView imgStatus;
        @BindView(R.id.imgTransparent)
        ImageView imgTransparent;
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
        @BindView(R.id.RlGraph)
        RelativeLayout RlGraph;
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
