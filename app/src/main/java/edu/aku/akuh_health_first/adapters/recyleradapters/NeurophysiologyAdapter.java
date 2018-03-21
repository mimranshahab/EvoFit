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
import edu.aku.akuh_health_first.models.Neurophysiology;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 */
public class NeurophysiologyAdapter extends RecyclerView.Adapter<NeurophysiologyAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;

    private Activity activity;
    private ArrayList<Neurophysiology> neurophysiologyArrayList;

    public NeurophysiologyAdapter(Activity activity, ArrayList<Neurophysiology> userList, OnItemClickListener onItemClickListener) {
        this.neurophysiologyArrayList = userList;
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

        final Neurophysiology model = neurophysiologyArrayList.get(holder.getAdapterPosition());
        holder.txtDateTime.setText(model.getRequestServiceDateTime());

        holder.txtName.setText(model.getService());

        holder.RlReport.setVisibility(View.VISIBLE);
        holder.RlGraph.setVisibility(View.GONE);

        setListener(holder, model);
//
        if (model.getStatus().equalsIgnoreCase("S")) {
            holder.txtStatusType.setText("Signed");

        setViews(holder, activity.getResources().getColor(R.color.base_green), R.drawable.rounded_box_filled_base_green, R.drawable.a_neurophysiology_green);

        } else {
            setViews(holder, activity.getResources().getColor(R.color.base_reddish), R.drawable.rounded_box_filled_base_red, R.drawable.a_neurophysiology_red);
            holder.txtStatusType.setText(model.getStatus());
        }

    }
    private void setViews(ViewHolder holder, int color, int backgroundResource, int circular_background) {
        holder.frameColorCode.setBackgroundColor(color);
        holder.txtStatusType.setBackgroundResource(backgroundResource);
        holder.imgIcon.setImageResource(circular_background);
    }

    private void setListener(final ViewHolder holder, final Neurophysiology neurophysiology) {
        holder.RlReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), neurophysiology);
            }
        });
    }


    public void addItem(ArrayList<Neurophysiology> homeCategories) {
        this.neurophysiologyArrayList = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return neurophysiologyArrayList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDateTime)
        AnyTextView txtDateTime;
        @BindView(R.id.txtStatusType)
        AnyTextView txtStatusType;
        @BindView(R.id.imgIcon)
        CircleImageView imgIcon;
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
