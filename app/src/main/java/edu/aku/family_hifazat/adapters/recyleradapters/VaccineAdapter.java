package edu.aku.family_hifazat.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.models.CardioModel;
import edu.aku.family_hifazat.models.ImmunizationModel;
import edu.aku.family_hifazat.widget.AnyTextView;

/**
 */
public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.ViewHolder> {

     private final AdapterView.OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<ImmunizationModel> arrImmunization;

    public VaccineAdapter(Activity activity, ArrayList<ImmunizationModel> userList, AdapterView.OnItemClickListener onItemClickListener) {
        this.arrImmunization = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_spinner, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final ImmunizationModel model = arrImmunization.get(holder.getAdapterPosition());

        holder.txtType.setText(model.getVaccinationStatus());

        holder.txtDateTime.setText(model.getVaccinationDate());
        holder.txtLocation.setText(model.getHospitalLocation());
        holder.txtName.setText(model.getDescription());


//        setListener(holder, model);
    }

    private void setListener(final ViewHolder holder, final CardioModel cardioModel) {
//        holder.btnShowGraph.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
//            }
//        });
//
//        holder.btnShowReport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
//            }
//        });

//        holder.btnShowReport.setOnClickListener(clickListener);
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


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
