//package com.structure.adapters.recycler;
//
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.aaloo.android.R;
//import com.aaloo.android.enums.CallStatus;
//import com.aaloo.android.managers.DateManager;
//import com.aaloo.android.models.CallLogModel;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//import static com.aaloo.android.enums.CallType.VIDEO_CALL;
//
//public class CallLogInfoAdapter extends RecyclerView.Adapter<CallLogInfoAdapter.ViewHolderCallLogList> {
//
//    ArrayList<CallLogModel> arrCallLogs;
//    private Context mContext;
//    private LayoutInflater mInflater;
//
//    public CallLogInfoAdapter(Context mContext, ArrayList<CallLogModel> logs) {
//        this.mContext = mContext;
//        this.arrCallLogs = logs;
//        mInflater = LayoutInflater.from(mContext);
//    }
//
//    @Override
//    public ViewHolderCallLogList onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = null;
//        itemView = mInflater
//                .inflate(R.layout.list_item_call_info, parent, false);
//        return new ViewHolderCallLogList(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolderCallLogList holder, int position) {
//
//        CallLogModel log = arrCallLogs.get(position);
//
//        if (log.getCallStatus_() == CallStatus.INCOMING) {
//            holder.tv_call_status.setVisibility(View.VISIBLE);
//            holder.iv_call_type.setImageResource(R.drawable.icon_recive_call);
//            holder.tv_call_type.setText(log.getCallType_() == VIDEO_CALL ? mContext.getString(R.string.incoming_video_call) : mContext.getString(R.string.incoming_voice_call));
//        } else if (log.getCallStatus_() == CallStatus.OUTGOING) {
//            holder.tv_call_status.setVisibility(View.VISIBLE);
//            holder.iv_call_type.setImageResource(R.drawable.icon_dial_call);
//            holder.tv_call_type.setText(log.getCallType_() == VIDEO_CALL ? mContext.getString(R.string.outgoing_video_call) : mContext.getString(R.string.outgoing_voice_call));
//        } else {
//            holder.tv_call_status.setVisibility(View.GONE);
//            holder.iv_call_type.setImageResource(R.drawable.icon_miss_call);
//            holder.tv_call_type.setText(log.getCallType_() == VIDEO_CALL ? mContext.getString(R.string.missed_video_call) : mContext.getString(R.string.missed_voice_call));
//        }
//
//        if (log.getDuration() == 0)
//            holder.tv_call_status.setText(mContext.getString(R.string.cancelled));
//        else
//            holder.tv_call_status.setText(DateManager.getTimeFromSeconds(log.getDuration()));
//
//        holder.tv_call_time.setText(DateManager.getFormattedTime(log.getTime()));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrCallLogs.size();
//    }
//
//    public CallLogModel getItemAt(int position) {
//        return arrCallLogs.get(position);
//    }
//
//
//    static class ViewHolderCallLogList extends RecyclerView.ViewHolder {
//        @BindView(R.id.iv_call_type)
//        ImageView iv_call_type;
//        @BindView(R.id.tv_call_type)
//        TextView tv_call_type;
//        @BindView(R.id.tv_call_time)
//        TextView tv_call_time;
//        @BindView(R.id.tv_call_status)
//        TextView tv_call_status;
//
//        ViewHolderCallLogList(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//    }
//}
