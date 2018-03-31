package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.models.RadiologyModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 */
public class RadiologyAdapter extends RecyclerView.Adapter<RadiologyAdapter.ViewHolder> {


    private final AdapterView.OnItemClickListener onItemClick;

    private Activity activity;
    private ArrayList<RadiologyModel> arrayList;

    public RadiologyAdapter(Activity activity, ArrayList<RadiologyModel> userList, AdapterView.OnItemClickListener onItemClickListener) {
        this.arrayList = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
//                .inflate(R.layout.item_radiology, parent, false);
                .inflate(R.layout.item_cps_nps_endo_summary_, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final RadiologyModel model = arrayList.get(holder.getAdapterPosition());
        holder.txtDateTime.setText(model.getExamdate());
        holder.txtDrName.setText(model.getVisitlocation());
        holder.txtName.setText(model.getExam());

        holder.RlReport.setVisibility(View.VISIBLE);
        holder.RlGraph.setVisibility(View.VISIBLE);
        holder.txtGraph.setText("Images");

        if (model.getStatus().equalsIgnoreCase("F")) {
            holder.txtStatusType.setText("Finalized");
            setViews(holder, activity.getResources().getColor(R.color.base_green), R.drawable.rounded_box_filled_base_green, R.drawable.radiology_transparent);

        } else {
            holder.txtStatusType.setText("Pending");
            setViews(holder, activity.getResources().getColor(R.color.base_amber), R.drawable.rounded_box_filled_base_amber, R.drawable.radiology_transparent);
            holder.RlReport.setVisibility(View.GONE);
            holder.RlGraph.setVisibility(View.GONE);
        }

        setListener(holder, model);


    }

    private void setViews(ViewHolder holder, int color, int backgroundResource, int img_transparent) {
        holder.cardView2.setCardBackgroundColor(color);
        holder.txtStatusType.setBackgroundResource(backgroundResource);
//        holder.btnReportColorCode1.setBackgroundResource(backgroundResource);
        holder.imgIcon.setColorFilter(color);
        holder.imgTransparent.setImageResource(img_transparent);
        holder.imgTransparent.setPadding(5, 5, 5, 5);
    }

    private void setListener(final ViewHolder holder, final RadiologyModel cardioModel) {
        holder.RlGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
            }
        });

        holder.RlReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
            }
        });

    }

    public RadiologyModel getItem(int position) {
        return arrayList.get(position);
    }

    public void addItem(ArrayList<RadiologyModel> homeCategories) {
        this.arrayList = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.txtReqDatetime)
//        AnyTextView txtExamDatetime;
//        @BindView(R.id.txtLocation)
//        AnyTextView txtLocation;
//
//        @BindView(R.id.txtExam)
//        AnyTextView txtExam;
//        @BindView(R.id.txtStatus)
//        AnyTextView txtStatus;
//        @BindView(R.id.btnShowReport)
//        AnyTextView btnShowReport;
//        @BindView(R.id.btnShowGraph)
//        AnyTextView btnShowGraph;
//        @BindView(R.id.contListItem)
//        LinearLayout contListItem;
//        @BindView(R.id.cardView2)
//        CardView cardView2;
        @BindView(R.id.txtDateTime)
        AnyTextView txtDateTime;
        @BindView(R.id.txtStatusType)
        AnyTextView txtStatusType;
        @BindView(R.id.imgIcon)
        ImageView imgIcon;
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
        @BindView(R.id.txtGraph)
        AnyTextView txtGraph;
        @BindView(R.id.btnGraphColorCode)
        AnyTextView btnGraphColorCode;
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
