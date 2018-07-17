package edu.aku.ehs.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.ehs.R;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.constatnts.AppConstants;
import edu.aku.ehs.models.ImmunizationModel;
import edu.aku.ehs.widget.AnyTextView;

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

        holder.txtStatusType.setVisibility(View.VISIBLE);
        holder.imgStatus.setVisibility(View.VISIBLE);
        holder.txtStatusType.setText(model.getVaccinationStatus());

        holder.txtDateTime.setText(model.getVaccinePlanDate());
        if (model.getHospitalLocation() == null || model.getHospitalLocation().isEmpty()) {
            holder.txtLocation.setText("Not Available");
        } else {
            holder.txtLocation.setText(model.getHospitalLocation());

        }
        holder.txtName.setText(model.getDescription());
        if (model.getRouteDescription() == null || model.getRouteDescription().isEmpty()) {
            holder.txtRoute.setText(model.getRouteID());
        } else {
            holder.txtRoute.setText(model.getRouteDescription());
        }

        switch (model.getVaccinationStatus()) {
            case AppConstants.schedule:

                setViews(holder, activity.getResources().getColor(R.color.base_blue), R.drawable.rounded_box_filled_primary_color, View.VISIBLE);
                break;

            case AppConstants.vaccinated:

                setViews(holder, activity.getResources().getColor(R.color.base_green), R.drawable.rounded_box_filled_base_green, View.GONE);
                break;

            case AppConstants.due:

                setViews(holder, activity.getResources().getColor(R.color.base_amber), R.drawable.rounded_box_filled_base_amber, View.VISIBLE);
                break;

            case AppConstants.over_due:

                setViews(holder, activity.getResources().getColor(R.color.base_reddish), R.drawable.rounded_box_filled_base_red, View.VISIBLE);
                break;
        }

        setListener(holder, model);
    }

    private void setViews(ViewHolder holder, int color, int rounded_box_filled_primary_color, int visible) {
        holder.cardView2.setCardBackgroundColor(color);
        holder.txtStatusType.setTextColor(color);
        holder.btnupdateColorCode.setBackgroundResource(rounded_box_filled_primary_color);
        holder.imgIcon.setColorFilter(color);
        holder.imgStatus.setColorFilter(color);
        holder.RLUpdate.setVisibility(visible);
        holder.txtBtnColor.setTextColor(color);

    }

    private void setListener(final ViewHolder holder, final ImmunizationModel model) {
        holder.RLUpdate.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.txtDateTime)
        AnyTextView txtDateTime;
        @BindView(R.id.imgStatus)
        ImageView imgStatus;
        @BindView(R.id.txtStatusType)
        AnyTextView txtStatusType;
        @BindView(R.id.imgIcon)
        ImageView imgIcon;
        @BindView(R.id.imgTransparent)
        ImageView imgTransparent;
        @BindView(R.id.txtName)
        AnyTextView txtName;
        @BindView(R.id.txtRoute)
        AnyTextView txtRoute;
        @BindView(R.id.txtLocation)
        AnyTextView txtLocation;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.btnupdateColorCode)
        AnyTextView btnupdateColorCode;
        @BindView(R.id.txtBtnColor)
        AnyTextView txtBtnColor;
        @BindView(R.id.RLUpdate)
        RelativeLayout RLUpdate;
        @BindView(R.id.frameColorCode)
        FrameLayout frameColorCode;
        @BindView(R.id.cardView2)
        CardView cardView2;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
