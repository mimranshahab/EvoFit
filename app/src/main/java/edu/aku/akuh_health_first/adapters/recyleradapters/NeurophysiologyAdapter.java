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
import edu.aku.akuh_health_first.models.Neurophysiology;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.views.AnyTextView;

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
                .inflate(R.layout.item_neurophysiology, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final Neurophysiology neurophysiology = neurophysiologyArrayList.get(holder.getAdapterPosition());
        holder.txtHospLoc.setText("HospitalLocation"+neurophysiology.getHospitalLocation());
//        holder.txtViewProfile.setText(neurophysiology.getName());
        holder.txtReqDatetime.setText("RequestServiceDateTime " + neurophysiology.getRequestServiceDateTime());
        holder.txtReqNum.setText("RequestNumber " + neurophysiology.getRequestNumber());
        holder.txtAdmNo.setText("AdmissionNumber " + neurophysiology.getAdmissionNumber());
        holder.txtService.setText("Service " + neurophysiology.getService());


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
        @BindView(R.id.txtHospLoc)
        AnyTextView txtHospLoc;
        @BindView(R.id.txtReqDatetime)
        AnyTextView txtReqDatetime;
        @BindView(R.id.txtReqNum)
        AnyTextView txtReqNum;
        @BindView(R.id.txtAdmNo)
        AnyTextView txtAdmNo;
        @BindView(R.id.txtService)
        AnyTextView txtService;



        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
