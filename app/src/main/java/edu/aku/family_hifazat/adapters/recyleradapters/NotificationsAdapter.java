package edu.aku.family_hifazat.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.models.NotificationModel;
import edu.aku.family_hifazat.widget.AnyTextView;

/**
 */
public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;

    private Activity activity;
    private ArrayList<NotificationModel> arrNotifications;

    public NotificationsAdapter(Activity activity, ArrayList<NotificationModel> arrData, OnItemClickListener onItemClickListener) {
        this.arrNotifications = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final NotificationModel timelineModel = arrNotifications.get(holder.getAdapterPosition());
        setListener(holder, timelineModel);
    }

    private void setListener(final ViewHolder holder, final NotificationModel neurophysiology) {

    }


    public void addItems(ArrayList<NotificationModel> homeCategories) {
        this.arrNotifications = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrNotifications.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTitle)
        AnyTextView txtTitle;
        @BindView(R.id.txtNotificaitonText)
        AnyTextView txtNotificaitonText;
        @BindView(R.id.cardView)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
