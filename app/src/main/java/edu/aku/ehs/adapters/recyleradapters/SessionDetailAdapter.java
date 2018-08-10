package edu.aku.ehs.adapters.recyleradapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.badoualy.stepperindicator.StepperIndicator;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.ehs.R;
import edu.aku.ehs.activities.BaseActivity;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.models.SessionDetailModel;
import edu.aku.ehs.widget.AnyTextView;

/**
 */
public class SessionDetailAdapter extends RecyclerView.Adapter<SessionDetailAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private BaseActivity activity;
    private ArrayList<SessionDetailModel> arrData;

    public SessionDetailAdapter(BaseActivity activity, ArrayList<SessionDetailModel> arrData, OnItemClickListener onItemClickListener) {
        this.arrData = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_session_detail, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {
        SessionDetailModel model = arrData.get(i);
        holder.txtEmployeeName.setText(model.getEmployeeName());
        holder.txtStatus.setText(model.getStatus().canonicalForm());

        holder.btnSchedule.setVisibility(View.VISIBLE);
        holder.btnDelete.setVisibility(View.VISIBLE);
        switch (model.getStatus()) {
            case ENROLLED:
//                holder.btnSchedule.setText("Set Schedule");
                break;
            case SCHEDULED:
//                holder.btnSchedule.setText("Update Schedule");
                break;
            case INPROGRESS:
//                holder.btnSchedule.setText("Cancel Schedule");
                holder.btnSchedule.setVisibility(View.GONE);
                holder.btnDelete.setVisibility(View.GONE);
                break;
            case CLOSED:
                holder.btnSchedule.setVisibility(View.GONE);
                holder.btnDelete.setVisibility(View.GONE);
                break;
        }

        holder.stepView.setCurrentStep(2);


        setListener(holder, model);
    }

    private void setListener(final ViewHolder holder, final SessionDetailModel model) {
        holder.btnSchedule.setOnClickListener(view -> onItemClick.onItemClick(holder.getAdapterPosition(), model, view));
        holder.btnDelete.setOnClickListener(view -> onItemClick.onItemClick(holder.getAdapterPosition(), model, view));
        holder.contListItem.setOnClickListener(view -> onItemClick.onItemClick(holder.getAdapterPosition(), model, view));
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtEmployeeName)
        AnyTextView txtEmployeeName;
        @BindView(R.id.txtEmployeeGender)
        AnyTextView txtEmployeeGender;
        @BindView(R.id.txDate)
        AnyTextView txDate;
        @BindView(R.id.txtStatus)
        AnyTextView txtStatus;
        @BindView(R.id.stepView)
        StepperIndicator stepView;
        @BindView(R.id.btnSchedule)
        ImageView btnSchedule;
        @BindView(R.id.btnDelete)
        ImageView btnDelete;
        @BindView(R.id.contListItem)
        RoundKornerLinearLayout contListItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}