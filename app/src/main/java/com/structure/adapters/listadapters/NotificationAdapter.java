package com.structure.adapters.listadapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.structure.R;
import com.structure.activities.MainActivity;
import com.structure.constatnts.WebServiceConstants;
import com.structure.fragments.abstracts.GenericClickableInterface;
import com.structure.fragments.abstracts.GenericClickableSpan;
import com.structure.model.NotificationModel;

import java.util.ArrayList;

/**
 * Created by khanhamza on 21-Feb-17.
 */

public class NotificationAdapter extends ArrayAdapter<NotificationModel> {

    private static final String TAG = "FEEDBACK";
    public ArrayList<NotificationModel> arrayListNotifications = new ArrayList<>();
    Context context;
    private View.OnClickListener onClickListener;

    public NotificationAdapter(Activity context, ArrayList<NotificationModel> notificationModels,
                               View.OnClickListener onClickListener) {

        super(context, 0, notificationModels);
        this.onClickListener = onClickListener;
        this.context = context;
        arrayListNotifications = notificationModels;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;


        final ViewHolder viewHolder;

        NotificationModel currentItem = getItem(position);


        if (listItemView == null) {
            viewHolder = new ViewHolder();


            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_notification, parent, false);

            bindViews(listItemView, viewHolder);

            listItemView.setTag(viewHolder);

        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) listItemView.getTag();
        }

        setViews(viewHolder, currentItem);
        return listItemView;
    }

    private void setViews(ViewHolder viewHolder, NotificationModel currentItem) {
        viewHolder.txtViewOrderNo.setText(String.valueOf(currentItem.getOrderID()));
        viewHolder.txtViewOrderStatus.setText(currentItem.getOrderStatus());

        if (currentItem.getOrderStatus().toLowerCase().contains(WebServiceConstants.WS_KEY_STATUS_COMPLETED)) {
            Log.d(TAG, "setViews: " + "It does *******");
            viewHolder.btnGiveFeedback.setVisibility(View.VISIBLE);
        } else {
            viewHolder.btnGiveFeedback.setVisibility(View.GONE);
        }
    }

    private void bindViews(View listItemView, ViewHolder viewHolder) {
        viewHolder.txtViewOrderNo = (TextView) listItemView.findViewById(R.id.txtOrderNo);
        viewHolder.txtViewOrderStatus = (TextView) listItemView.findViewById(R.id.txtOrderStatus);
        viewHolder.btnGiveFeedback = (Button) listItemView.findViewById(R.id.btnGiveFeedback);
        viewHolder.btnGiveFeedback.setOnClickListener(onClickListener);
    }


    static class ViewHolder {
        TextView txtViewOrderNo;
        TextView txtViewOrderStatus;
        Button btnGiveFeedback;
    }
}
