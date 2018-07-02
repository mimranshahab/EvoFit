package edu.aku.family_hifazat.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.enums.VisitTypes;
import edu.aku.family_hifazat.models.TimelineModel;
import edu.aku.family_hifazat.widget.AnyTextView;

/**
 */
public class TimelineAdapter_V2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<TimelineModel> arrData;
    private ArrayList<TimelineModel> filteredData = new ArrayList<>();
    private ItemFilter mFilter = new ItemFilter();

    private static final int TYPE_EVENT = 1;
    private static final int TYPE_VISIT = 0;


    public TimelineAdapter_V2(Activity activity, ArrayList<TimelineModel> arrData, OnItemClickListener onItemClickListener) {
        this.arrData = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
        filteredData = arrData;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == arrData.size() - 1)
            return TYPE_EVENT;
        else
            return TYPE_VISIT;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;

        if (viewType == TYPE_EVENT) {
            return new ViewHolderEvent(LayoutInflater.from(activity)
                    .inflate(R.layout.item_timeline_event, parent, false));
        } else {
            return new ViewHolderVisit(LayoutInflater.from(activity)
                    .inflate(R.layout.item_timeline_v2, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int i) {

        if (holder instanceof ViewHolderEvent) {
            ViewHolderEvent holderEvent = (ViewHolderEvent) holder;
            if (holder.getAdapterPosition() == 0) {
                holderEvent.txtEvent.setText("Expired");
                holderEvent.txtGenderMessage.setText("");
                holderEvent.txtEvent.setBackgroundColor(activity.getResources().getColor(R.color.timeline_expire_dark));
                holderEvent.contParent.setBackgroundResource(R.drawable.rounded_box_timeline_expire);
            } else {
                holderEvent.txtEvent.setText("Born at AKUH");
                holderEvent.txtEvent.setBackgroundColor(activity.getResources().getColor(R.color.timeline_born_dark));
                holderEvent.txtGenderMessage.setText("Baby boy of (Mother Name)");
                holderEvent.contParent.setBackgroundResource(R.drawable.rounded_box_timeline_born);
            }
        } else {

            ViewHolderVisit holderVisit = (ViewHolderVisit) holder;
            final TimelineModel timelineModel = filteredData.get(holder.getAdapterPosition());
            VisitTypes visitTypes;

        /*
        Dr name PatientVisitDoctorName
        Service PatientVisitService
        loc, facility  PatientVisitLocation,PatientVisitHospitalLocation
        date PatientVisitDateTime
         */

            if (timelineModel.getPatientVisitType().contains("Emergency")) {
                visitTypes = VisitTypes.ER;
                holderVisit.txtVisitType.setText("ER");
            } else if (timelineModel.getPatientVisitType().contains("In-Patient")) {
                holderVisit.txtVisitType.setText("INP");
                visitTypes = VisitTypes.INP;
            } else if (timelineModel.getPatientVisitType().contains("Clinic")) {
                holderVisit.txtVisitType.setText("SDC");
                visitTypes = VisitTypes.SDC;
            } else {
                holderVisit.txtVisitType.setText("OUT");
                visitTypes = VisitTypes.OUT;
            }
// FIXME: 6/28/2018 Visit type will come from service
//            holderVisit.txtVisitType.setText(timelineModel.getPatientVisitType());
//            holderVisit.txtVisitDetail.setText(timelineModel.getPatientVisitType());
            holderVisit.txtDoctorName.setText("Dr." + timelineModel.getPatientVisitDoctorName());
            setDate(holderVisit, timelineModel, visitTypes);
            holderVisit.txtServiceDetails.setText(timelineModel.getPatientVisitLocation() + " | " + timelineModel.getPatientVisitHospitalLocation());


            setServiceDetail(holderVisit, timelineModel);


         /*
        set banner color
        In-Patient ---->Blue
        Out-Patient ---->Orange
        ER ---->Red
        Clinical ---->Green

         */

            switch (visitTypes) {
                case ER:
                    setViews(holderVisit, R.drawable.rounded_box_timeline_er, R.drawable.rounded_box_timeline_er_status, activity.getResources().getColor(R.color.timeline_er_dark));
                    break;
                case SDC:
                    setViews(holderVisit, R.drawable.rounded_box_timeline_sdc, R.drawable.rounded_box_timeline_sdc_status, activity.getResources().getColor(R.color.timeline_sdc_dark));
                    break;
                case OUT:
                    setViews(holderVisit, R.drawable.rounded_box_timeline_out, R.drawable.rounded_box_timeline_out_status, activity.getResources().getColor(R.color.timeline_out_dark));
                    break;
                case INP:
                    setViews(holderVisit, R.drawable.rounded_box_timeline_inp, R.drawable.rounded_box_timeline_inp_status, activity.getResources().getColor(R.color.timeline_inp_dark));
                    break;
            }

            setListener(holderVisit, timelineModel);
        }

    }

    private void setServiceDetail(ViewHolderVisit holderVisit, TimelineModel timelineModel) {

        String serviceDetails = "";

        if (timelineModel.getPatientVisitServiceDescription() == null || timelineModel.getPatientVisitServiceDescription().isEmpty()) {
            if (timelineModel.getPatientVisitService() != null && !timelineModel.getPatientVisitService().isEmpty()) {
                serviceDetails = serviceDetails + timelineModel.getPatientVisitService() + " | ";
            }
        } else {
            serviceDetails = serviceDetails + timelineModel.getPatientVisitServiceDescription() + " | ";
        }

        serviceDetails = serviceDetails + timelineModel.getPatientVisitLocation() + " | ";
        serviceDetails = serviceDetails + timelineModel.getPatientVisitHospitalLocation();

        holderVisit.txtServiceDetails.setText(serviceDetails);

    }

    private void setDate(ViewHolderVisit holderVisit, TimelineModel timelineModel, VisitTypes visitTypes) {

        if (timelineModel.getPatientVisitDischargeDate() == null || timelineModel.getPatientVisitDischargeDate().isEmpty()) {
            holderVisit.txtDate.setText(timelineModel.getPatientVisitDateTime());
        } else {
            switch (visitTypes) {
                case ER:
                    holderVisit.txtDate.setText(timelineModel.getPatientVisitDateTime() + " to " + timelineModel.getPatientVisitDischargeDate());
                    break;
                case SDC:
                    holderVisit.txtDate.setText(timelineModel.getPatientVisitDateTime() + " till " + timelineModel.getPatientVisitDischargeDate());
                    break;
                case OUT:
                    holderVisit.txtDate.setText(timelineModel.getPatientVisitDateTime());
                    break;
                case INP:
                    holderVisit.txtDate.setText(timelineModel.getPatientVisitDateTime() + " to " + timelineModel.getPatientVisitDischargeDate());
                    break;
            }
        }


    }

    private void setViews(ViewHolderVisit holderVisit, int resourceIDContainer, int resourceIDStatus, int doctorNameColor) {
        holderVisit.contParent.setBackgroundResource(resourceIDContainer);
        holderVisit.txtVisitType.setBackgroundResource(resourceIDStatus);
        holderVisit.txtDoctorName.setTextColor(doctorNameColor);
    }

    private void setListener(final ViewHolderVisit holderVisit, final TimelineModel timelineModel) {
        holderVisit.contParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holderVisit.getAdapterPosition(), timelineModel);
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

    static class ViewHolderVisit extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDate)
        AnyTextView txtDate;
        @BindView(R.id.txtVisitType)
        AnyTextView txtVisitType;
        @BindView(R.id.txtVisitDetail)
        AnyTextView txtVisitDetail;
        @BindView(R.id.txtDoctorName)
        AnyTextView txtDoctorName;
        @BindView(R.id.txtServiceDetails)
        AnyTextView txtServiceDetails;
        @BindView(R.id.contParent)
        LinearLayout contParent;

        ViewHolderVisit(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderEvent extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDate)
        AnyTextView txtDate;
        @BindView(R.id.txtEvent)
        AnyTextView txtEvent;
        @BindView(R.id.txtGenderMessage)
        AnyTextView txtGenderMessage;
        @BindView(R.id.contParent)
        LinearLayout contParent;

        ViewHolderEvent(View view) {
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
