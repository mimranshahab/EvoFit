package edu.aku.family_hifazat.adapters.recyleradapters;

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
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.models.TimelineModel;
import edu.aku.family_hifazat.widget.AnyTextView;

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
        holder.txtDoctorName.setText("Dr." + timelineModel.getPatientVisitDoctorName());
        if (timelineModel.getPatientVisitServiceDescription() == null && timelineModel.getPatientVisitServiceDescription().isEmpty()) {
            holder.txtService.setText(timelineModel.getPatientVisitService());
        } else {
            holder.txtService.setText(timelineModel.getPatientVisitServiceDescription());
        }

        if (timelineModel.getPatientVisitDischargeDate() == null || timelineModel.getPatientVisitDischargeDate().isEmpty()) {
            holder.contDischargeDate.setVisibility(View.GONE);
        } else {
            holder.contDischargeDate.setVisibility(View.VISIBLE);
            holder.txtDischargeDateTime.setText(timelineModel.getPatientVisitDischargeDate());
        }

        holder.txtVisitType.setText(timelineModel.getPatientVisitType());
        /*
        set banner color
        In-Patient ---->Blue
        Out-Patient ---->Orange
        ER ---->Red
        Clinical ---->Green

         */
        if (timelineModel.getPatientVisitType().contains("Emergency")) {
            setViews(holder, R.color.base_reddish, activity.getResources().getColor(R.color.base_reddish));
        } else if (timelineModel.getPatientVisitType().contains("In-Patient")) {
            setViews(holder, R.color.base_blue, activity.getResources().getColor(R.color.base_blue));

        } else if (timelineModel.getPatientVisitType().contains("Clinic")) {
            setViews(holder, R.color.base_green, activity.getResources().getColor(R.color.base_green));

        } else {
            setViews(holder, R.color.base_amber, activity.getResources().getColor(R.color.base_amber));
        }


        setListener(holder, timelineModel);
    }

    private void setViews(ViewHolder holder, int base_color, int color) {
        holder.cardView.setCardBackgroundColor(color);
        holder.llColorCode.setBackgroundResource(base_color);
        holder.txtDoctorName.setTextColor(color);
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
        @BindView(R.id.txtVisitDateTime)
        AnyTextView txtVisitDateTime;
        @BindView(R.id.txtDischargeDateTime)
        AnyTextView txtDischargeDateTime;
        @BindView(R.id.contDischargeDate)
        LinearLayout contDischargeDate;
        @BindView(R.id.txtDoctorName)
        AnyTextView txtDoctorName;
        @BindView(R.id.txtService)
        AnyTextView txtService;
        @BindView(R.id.txtFacility)
        AnyTextView txtFacility;
        @BindView(R.id.txtVisitType)
        AnyTextView txtVisitType;
        @BindView(R.id.contProfile)
        LinearLayout contProfile;
        @BindView(R.id.frameColorCode)
        FrameLayout frameColorCode;
        @BindView(R.id.cardView)
        CardView cardView;
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
