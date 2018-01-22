package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.models.TimelineModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<TimelineModel> neurophysiologyArrayList;

    public TimelineAdapter(Activity activity, ArrayList<TimelineModel> userList, OnItemClickListener onItemClickListener) {
        this.neurophysiologyArrayList = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_timeline, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final TimelineModel timelineModel = neurophysiologyArrayList.get(holder.getAdapterPosition());
        holder.txtVisit.setText(timelineModel.getText());
        holder.txtPurpose.setText(timelineModel.getText());
        holder.txtDoctor.setText(timelineModel.getText());

        setListener(holder, timelineModel);
    }

    private void setListener(final ViewHolder holder, final TimelineModel neurophysiology) {

    }


    public void addItems(ArrayList<TimelineModel> homeCategories) {
        this.neurophysiologyArrayList = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return neurophysiologyArrayList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtVisit)
        AnyTextView txtVisit;

        @BindView(R.id.txtPurpose)
        AnyTextView txtPurpose;

        @BindView(R.id.txtDoctor)
        AnyTextView txtDoctor;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
