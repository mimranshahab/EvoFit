package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.models.CardioModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class CardioAdapter extends RecyclerView.Adapter<CardioAdapter.ViewHolder> {


    private final AdapterView.OnItemClickListener onItemClick;

    private Activity activity;
    private ArrayList<CardioModel> arrListCardioModel;

    public CardioAdapter(Activity activity, ArrayList<CardioModel> userList, AdapterView.OnItemClickListener onItemClickListener) {
        this.arrListCardioModel = userList;
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

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final CardioModel cardioModel = arrListCardioModel.get(holder.getAdapterPosition());
//        holder.txtHospLoc.setText(activity.getString(R.string.hospitalLocation) + " " + cardioModel.getHospitalLocation());
//        holder.txtViewProfile.setText(cardioModel.getName());
        holder.txtReqDatetime.setText(activity.getString(R.string.date_time) + " " + cardioModel.getRequestServiceDateTime());
//        holder.txtReqNum.setText(activity.getString(R.string.status) + " " + cardioModel.getStatus());
        holder.txtStatus.setText(activity.getString(R.string.status) + " " + cardioModel.getStatus());
        holder.txtService.setText(activity.getString(R.string.service) + " " + cardioModel.getService());

        holder.txtHospLoc.setVisibility(View.GONE);
        holder.txtReqNum.setVisibility(View.GONE);

        holder.btnShowGraph.setVisibility(View.VISIBLE);
        holder.btnShowReport.setVisibility(View.VISIBLE);
        if (!cardioModel.isGraphAvailable(cardioModel.getGraphAvailable())) {
            holder.btnShowGraph.setEnabled(false);
            holder.btnShowGraph.setAlpha(.15f);
        }

        if (!cardioModel.isReportAvailable(cardioModel.getReportable())) {
            holder.btnShowReport.setEnabled(false);
            holder.btnShowReport.setAlpha(.15f);

        }

        setListener(holder, cardioModel);
    }

    private void setListener(final ViewHolder holder, final CardioModel cardioModel) {
        holder.btnShowGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
            }
        });

        holder.btnShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
            }
        });

//        holder.btnShowReport.setOnClickListener(clickListener);
    }

    public CardioModel getItem(int position) {
        return arrListCardioModel.get(position);
    }

    public void addItem(ArrayList<CardioModel> homeCategories) {
        this.arrListCardioModel = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrListCardioModel.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtHospLoc)
        AnyTextView txtHospLoc;
        @BindView(R.id.txtReqDatetime)
        AnyTextView txtReqDatetime;
        @BindView(R.id.txtReqNum)
        AnyTextView txtReqNum;
        @BindView(R.id.txtAdmNo)
        AnyTextView txtStatus;
        @BindView(R.id.txtService)
        AnyTextView txtService;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.btnShowGraph)
        Button btnShowGraph;
        @BindView(R.id.btnShowReport)
        Button btnShowReport;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
