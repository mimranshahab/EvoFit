//package com.structure.adapters.recycler;
//
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.aaloo.android.R;
//import com.aaloo.android.enums.CallStatus;
//import com.aaloo.android.enums.CallType;
//import com.aaloo.android.managers.DateManager;
//import com.aaloo.android.models.CallLogModel;
//import com.aaloo.android.util.ViewsHelper;
//
//import java.util.ArrayList;
//import java.util.Locale;
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import de.hdodenhof.circleimageview.CircleImageView;
//import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;
//
//public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.ViewHolderCallLogList> {
//
//    Map<Integer, ArrayList<CallLogModel>> arrCallLogs;
//    private Context mContext;
//    private LayoutInflater mInflater;
//    private AdapterView.OnItemClickListener onItemClickListener;
//
//    public CallLogAdapter(Context mContext, Map<Integer, ArrayList<CallLogModel>> logs) {
//        this.mContext = mContext;
//        this.arrCallLogs = logs;
//        mInflater = LayoutInflater.from(mContext);
//    }
//
//    public void setDataSet(Map<Integer, ArrayList<CallLogModel>> arrCallLogs) {
//        this.arrCallLogs = arrCallLogs;
//    }
//
//    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//
//    @Override
//    public ViewHolderCallLogList onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = null;
//
//        itemView = mInflater
//                .inflate(R.layout.list_item_call_log, parent, false);
//        return new CallLogAdapter.ViewHolderCallLogList(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolderCallLogList holder, int position) {
//
//        if (arrCallLogs == null) return;
//// FIXME: 10-Aug-17 NULL POINTER EXCEPTION ON KEY. TO be done by Humza Khan
//        if (arrCallLogs.get(position) != null) {
//            CallLogModel log = arrCallLogs.get(position).get(0); /* 0 b/c we will show only first one , and later will be in next screen*/
//            if (log.getTo() != null)
//                holder.txtFullName.setText(log.getTo().getName());
//
//            ViewsHelper.setImage(log.getTo(), holder.imageUserProfile, true);
//
//            if (log.getCallStatus_() == CallStatus.INCOMING)
//                holder.imageCallStatus.setImageResource(R.drawable.icon_recive_call);
//            else if (log.getCallStatus_() == CallStatus.OUTGOING)
//                holder.imageCallStatus.setImageResource(R.drawable.icon_dial_call);
//            else
//                holder.imageCallStatus.setImageResource(R.drawable.icon_miss_call);
//
//            if (log.getCallType_() == CallType.VIDEO_CALL)
//                holder.txtCallCount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dlg_video_call, 0, 0, 0);
//            else
//                holder.txtCallCount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dlg_call, 0, 0, 0);
//            ;
//
//            if (arrCallLogs.get(position).size() > 1) {
//                holder.txtCallCount.setText(String.format(Locale.US, "(%d)", arrCallLogs.get(position).size()));
//            } else {
//                holder.txtCallCount.setText("");
//            }
//
//            holder.txtTime.setText(DateManager.getProperTimeDifference(DateManager.getDate(log.getTime()), false));
//            holder.rowFG.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onItemClick(null, null, holder.getAdapterPosition(), 0);
//                }
//            });
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        if (arrCallLogs == null || arrCallLogs.isEmpty()) {
//            return 0;
//        }
//        return arrCallLogs.size();
//    }
//
//    public ArrayList<CallLogModel> getItemAt(int position) {
//        return arrCallLogs.get(position);
//    }
//
//
//    static class ViewHolderCallLogList extends RecyclerView.ViewHolder {
//        @BindView(R.id.imageUserProfile)
//        CircleImageView imageUserProfile;
//        @BindView(R.id.imageOnlineStatus)
//        ImageView imageOnlineStatus;
//        @BindView(R.id.relativeUserImage)
//        RelativeLayout relativeUserImage;
//        @BindView(R.id.txtFullName)
//        EmojiconTextView txtFullName;
//        @BindView(R.id.txtTime)
//        TextView txtTime;
//        @BindView(R.id.txtCallCount)
//        TextView txtCallCount;
//        @BindView(R.id.imageCallStatus)
//        ImageView imageCallStatus;
//        @BindView(R.id.lytRoot)
//        View lytRoot;
//        @BindView(R.id.rowFG)
//        View rowFG;
//
//        ViewHolderCallLogList(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//    }
//}
