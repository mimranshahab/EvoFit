package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.models.TimelineModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<TimelineModel> arrData;
    private ArrayList<TimelineModel> filteredData = new ArrayList<>();
    private ItemFilter mFilter = new ItemFilter();


    public TimelineAdapter(Activity activity, ArrayList<TimelineModel> arrData, OnItemClickListener onItemClickListener) {
        this.arrData = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
        filteredData = arrData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_timeline, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final TimelineModel timelineModel = filteredData.get(holder.getAdapterPosition());

        /*
        Dr name PatientVisitDoctorName
        Service PatientVisitService
        loc, facility  PatientVisitLocation,PatientVisitHospitalLocation
        date PatientVisitDateTime
         */

        holder.txtFacility.setText(timelineModel.getPatientVisitLocation() + "," + timelineModel.getPatientVisitHospitalLocation());
        holder.txtVisitDateTime.setText(timelineModel.getPatientVisitDateTime());
        holder.txtDoctorName.setText(timelineModel.getPatientVisitDoctorName());
        holder.txtService.setText(timelineModel.getPatientVisitService());
//        holder.txtVisitType.setText(timelineModel.getPatientVisitType());
//        holder.txtVisitType.setVisibility(View.GONE);
        /*
        set banner color
        In-Patient ---->Blue
        Out-Patient ---->Orange
        ER ---->Red
        Clinical ---->Green

         */
        if (timelineModel.getPatientVisitType().equals("ER")) {
            holder.frameColorCode.setBackgroundResource(R.color.c_brick_red);
            holder.llColorCode.setBackgroundResource(R.color.c_brick_red);
        } else if (timelineModel.getPatientVisitType().equals("IN")) {
            holder.frameColorCode.setBackgroundResource(R.color.base_dark_blue);
            holder.llColorCode.setBackgroundResource(R.color.base_dark_blue);

        } else if (timelineModel.getPatientVisitType().equals("CLI")) {
            holder.frameColorCode.setBackgroundResource(R.color.c_dark_green);
            holder.llColorCode.setBackgroundResource(R.color.c_dark_green);

        } else {
            holder.frameColorCode.setBackgroundResource(R.color.base_amber);
            holder.llColorCode.setBackgroundResource(R.color.base_amber);

        }


        setListener(holder, timelineModel);
    }

    private void setListener(final ViewHolder holder, final TimelineModel timelineModel) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), timelineModel);
            }
        });
    }


    public void addItems(ArrayList<TimelineModel> arrData) {
        this.arrData = arrData;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
//        return arrData.size();
        return getCount();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtFacility)
        AnyTextView txtFacility;
        @BindView(R.id.txtVisitDateTime)
        AnyTextView txtVisitDateTime;
        @BindView(R.id.txtDoctorName)
        AnyTextView txtDoctorName;
        @BindView(R.id.txtService)
        AnyTextView txtService;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.frameColorCode)
        FrameLayout frameColorCode;
        @BindView(R.id.llColorCode)
        LinearLayout llColorCode;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    // FILTER DATA START

    public int getCount() {
        if (filteredData == null) {
            return 0;
        }
        return filteredData.size();
    }

    public TimelineModel getItem(int position) {
        return filteredData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public Filter getFilter() {
        return mFilter;
    }


    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final ArrayList<TimelineModel> list = arrData;

            int count = list.size();

//            final ArrayList<String> nlist = new ArrayList<String>(count);
            final ArrayList<TimelineModel> filterData = new ArrayList<TimelineModel>();

            String filterableString1;
            String filterableString2;
            String filterableString3;
            String filterableString4;

            for (int i = 0; i < count; i++) {
                filterableString1 = list.get(i).getPatientVisitDoctorName();
                filterableString2 = list.get(i).getPatientVisitService();
                filterableString3 = list.get(i).getPatientVisitHospitalLocation();
                filterableString4 = list.get(i).getPatientVisitLocation();
                if (filterableString1.toLowerCase().contains(filterString)
                        || filterableString2.toLowerCase().contains(filterString)
                        || filterableString3.toLowerCase().contains(filterString)
                        || filterableString4.toLowerCase().contains(filterString)) {
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
            filteredData = (ArrayList<TimelineModel>) results.values;
            notifyDataSetChanged();
        }

    }


// FILTER DATA CLOSE


}
