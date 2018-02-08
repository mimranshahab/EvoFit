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
import edu.aku.akuh_health_first.models.RadiologyModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class RadiologyAdapter extends RecyclerView.Adapter<RadiologyAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;

    private Activity activity;
    private ArrayList<RadiologyModel> arrayList;

    public RadiologyAdapter(Activity activity, ArrayList<RadiologyModel> userList, OnItemClickListener onItemClickListener) {
        this.arrayList = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_radiology, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final RadiologyModel model = arrayList.get(holder.getAdapterPosition());
        holder.txtExamDatetime.setText("Exam Date" + " - " + model.getExamdate());
        holder.txtExamOrderNumber.setText("Exam Order Number" + " - " + model.getExamordernumber());
        holder.txtExamTypeId.setText("Exam Type ID" + " - " + model.getExamtypeid());
        holder.txtExam.setText("Exam" + " - " + model.getExam());
        holder.txtStatus.setText("Status" + " - " + model.getStatus());
//        holder.btnShowReport.setVisibility(View.VISIBLE);
        setListener(holder, model);
    }

    private void setListener(final ViewHolder holder, final RadiologyModel radiologyModel) {
        holder.contListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), radiologyModel);
            }
        });
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
        @BindView(R.id.txtReqDatetime)
        AnyTextView txtExamDatetime;
        @BindView(R.id.txtExamOrderNumber)
        AnyTextView txtExamOrderNumber;
        @BindView(R.id.txtExamTypeId)
        AnyTextView txtExamTypeId;
        @BindView(R.id.txtExam)
        AnyTextView txtExam;
        @BindView(R.id.txtStatus)
        AnyTextView txtStatus;
        @BindView(R.id.btnShowReport)
        Button btnShowReport;
        @BindView(R.id.btnShowGraph)
        Button btnShowGraph;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.cardView2)
        CardView cardView2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
