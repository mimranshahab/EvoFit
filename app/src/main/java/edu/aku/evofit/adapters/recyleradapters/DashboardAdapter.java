package edu.aku.evofit.adapters.recyleradapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.evofit.R;
import edu.aku.evofit.activities.BaseActivity;
import edu.aku.evofit.callbacks.OnItemClickListener;
import edu.aku.evofit.models.DepartmentModel;
import edu.aku.evofit.widget.AnyTextView;

/**
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private BaseActivity activity;
    private ArrayList<DepartmentModel> arrData;

    public DashboardAdapter(BaseActivity activity, ArrayList<DepartmentModel> arrData, OnItemClickListener onItemClickListener) {
        this.arrData = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_dashboard, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {
        DepartmentModel model = arrData.get(i);
        holder.txtDesc.setText(model.getDeptName());
        holder.imgBackground.setBackgroundColor(activity.getResources().getColor(model.getColor()));
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDesc)
        AnyTextView txtDesc;
        @BindView(R.id.contParent)
        RelativeLayout contParent;
        @BindView(R.id.imgBackground)
        ImageView imgBackground;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
