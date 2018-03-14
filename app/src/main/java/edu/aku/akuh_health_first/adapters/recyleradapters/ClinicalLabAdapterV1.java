package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.models.LaboratoryModel;
import edu.aku.akuh_health_first.models.TimelineModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class ClinicalLabAdapterV1 extends RecyclerView.Adapter<ClinicalLabAdapterV1.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<LaboratoryModel> arrData;
    private ArrayList<LaboratoryModel> filteredData = new ArrayList<>();
    private Filter mFilter = new ItemFilter();

    public ClinicalLabAdapterV1(Activity activity, ArrayList<LaboratoryModel> arrayList, OnItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
        this.filteredData = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_clinical_labv1, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final LaboratoryModel model = arrData.get(holder.getAdapterPosition());

        holder.txtSpecimenNumber.setText(model.getSpecimenID());
        holder.txtStatusID.setText(model.getOrdered() + " (" + model.getSpecimenSectionID() + ")");
        holder.txtDateTime.setText(model.getEnteredDTTM());
        holder.txtStatus.setText(model.getStatusID());
        holder.btnShowDetail.setText("SHOW " + model.getSpecimenType() + " DETAILS");
        setListener(holder, model);

        /*
        6 color codes
        Red         HAEM
        Green       BIO
        Blue        MICRO
        Orange      HISTO
        Sky Blue    MMP
        OrangeRed   BBK
         */


        if (model.getSpecimenSectionID().equals("HAEM")) {
            colorCodes(holder, R.color.c_brick_red, R.drawable.rounded_box_filled_base_red);

        } else if (model.getSpecimenSectionID().equals("MICRO")) {
            colorCodes(holder, R.color.base_blue, R.drawable.rounded_box_filled_primary_color);


        } else if (model.getSpecimenSectionID().equals("BIO")) {
            colorCodes(holder, R.color.c_dark_green, R.drawable.rounded_box_filled_base_green);

        } else if (model.getSpecimenSectionID().equals("HISTO")) {
            colorCodes(holder, R.color.base_amber, R.drawable.rounded_box_filled_base_amber);

        } else if (model.getSpecimenSectionID().equals("MMP")) {
            colorCodes(holder, R.color.c_dark_turquoise, R.drawable.rounded_box_filled_primary_color);

        } else {
            colorCodes(holder, R.color.c_orange_red, R.drawable.rounded_box_filled_base_amber);
        }

    }

    private void colorCodes(ViewHolder holder, int framecolor, int btnbackground) {
        holder.frameColorCode.setBackgroundResource(framecolor);
        holder.llColorCode.setBackgroundResource(framecolor);
        holder.btnShowDetail.setBackgroundResource(btnbackground);

        holder.txtStatus.setTextColor(activity.getResources().getColor(framecolor));
    }

    private void setListener(final ViewHolder holder, final LaboratoryModel neurophysiology) {
        holder.btnShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), neurophysiology);
            }
        });
    }


    public void addItem(ArrayList<LaboratoryModel> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtSpecimenNumber)
        AnyTextView txtSpecimenNumber;
        @BindView(R.id.txtStatusID)
        AnyTextView txtStatusID;
        @BindView(R.id.txtDateTime)
        AnyTextView txtDateTime;
        @BindView(R.id.btnShowDetail)
        AnyTextView btnShowDetail;
        @BindView(R.id.txtStatus)
        AnyTextView txtStatus;
        @BindView(R.id.cardView2)
        CardView cardView2;
        @BindView(R.id.llColorCode)
        LinearLayout llColorCode;
        @BindView(R.id.frameColorCode)
        FrameLayout frameColorCode;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public Filter getFilter() {

        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final ArrayList<LaboratoryModel> list = arrData;

            int count = list.size();

//            final ArrayList<String> nlist = new ArrayList<String>(count);
            final ArrayList<LaboratoryModel> filterData = new ArrayList<LaboratoryModel>();

            String filterableString1;
            String filterableString2;
            String filterableString3;
            String filterableString4;

            for (int i = 0; i < count; i++) {
                filterableString1 = list.get(i).getSpecimenNumber();
//                filterableString2 = list.get(i).getPrevResult2();
//                filterableString3 = list.get(i).getPatientVisitHospitalLocation();
//                filterableString4 = list.get(i).getPatientVisitLocation();
                if (filterableString1.toLowerCase().contains(filterString))
//                        || filterableString2.toLowerCase().contains(filterString)
//                        || filterableString3.toLowerCase().contains(filterString)
//                        || filterableString4.toLowerCase().contains(filterString))
                {
//                    nlist.add(filterableString);
                    filterData.add(list.get(i));
                }
            }

            results.values = filterData;
            results.count = filterData.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<LaboratoryModel>) results.values;
            notifyDataSetChanged();
        }

    }


    public int getCount() {
        if (filteredData == null) {
            return 0;
        }
        return filteredData.size();
    }

    public LaboratoryModel getItem(int position) {
        return filteredData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }






}
