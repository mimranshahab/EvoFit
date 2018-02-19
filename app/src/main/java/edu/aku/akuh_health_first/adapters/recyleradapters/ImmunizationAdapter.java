package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
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
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.models.CardioModel;
import edu.aku.akuh_health_first.models.ImmunizationModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class ImmunizationAdapter extends RecyclerView.Adapter<ImmunizationAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<ImmunizationModel> arrImmunization;

    public ImmunizationAdapter(Activity activity, ArrayList<ImmunizationModel> userList, OnItemClickListener onItemClickListener) {
        this.arrImmunization = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_immunization, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final ImmunizationModel model = arrImmunization.get(holder.getAdapterPosition());

        holder.txtType.setText(model.getVaccinationStatus());


        holder.txtDateTime.setText(model.getVaccinePlanDate());
        holder.txtLocation.setText(model.getHospitalLocation());
        holder.txtName.setText(model.getDescription());

        if (model.getVaccinationStatus().equals(AppConstants.schedule)) {
            holder.contBorder.setBackgroundColor(activity.getResources().getColor(R.color.base_grey));
            holder.btnUpdate.setVisibility(View.VISIBLE);
        } else if (model.getVaccinationStatus().equals(AppConstants.vaccinated)) {
            holder.contBorder.setBackgroundColor(activity.getResources().getColor(R.color.c_green));
            holder.btnUpdate.setVisibility(View.GONE);
            holder.txtDateTime.setText(model.getVaccinationDate());
        } else if (model.getVaccinationStatus().equals(AppConstants.due)) {
            holder.contBorder.setBackgroundColor(activity.getResources().getColor(R.color.base_reddish));
            holder.btnUpdate.setVisibility(View.VISIBLE);
        }

        setListener(holder, model);
    }

    private void setListener(final ViewHolder holder, final ImmunizationModel model) {
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(holder.getAdapterPosition(), model);
            }
        });
    }

    public ImmunizationModel getItem(int position) {
        return arrImmunization.get(position);
    }

    public void addItem(ArrayList<ImmunizationModel> homeCategories) {
        this.arrImmunization = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrImmunization.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtType)
        AnyTextView txtType;
        @BindView(R.id.txtName)
        AnyTextView txtName;
        @BindView(R.id.txtDateTime)
        AnyTextView txtDateTime;
        @BindView(R.id.txtLocation)
        AnyTextView txtLocation;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.cardView2)
        CardView cardView2;
        @BindView(R.id.contBorder)
        LinearLayout contBorder;
        @BindView(R.id.btnUpdate)
        Button btnUpdate;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}