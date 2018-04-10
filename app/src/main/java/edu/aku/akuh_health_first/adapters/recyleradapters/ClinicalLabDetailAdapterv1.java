package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.helperclasses.Spanny;
import edu.aku.akuh_health_first.models.LstLaboratorySpecimenResults;
import edu.aku.akuh_health_first.widget.AnyTextView;
import edu.aku.akuh_health_first.widget.CustomTypefaceSpan;

/**
 */
public class ClinicalLabDetailAdapterv1 extends RecyclerView.Adapter<ClinicalLabDetailAdapterv1.ViewHolder> {


    private final AdapterView.OnItemClickListener onItemClick;

    Typeface bold;
    Typeface regular;


    private Activity activity;
    private ArrayList<LstLaboratorySpecimenResults> arrData;

    public ClinicalLabDetailAdapterv1(Activity activity, ArrayList<LstLaboratorySpecimenResults> arrayList, AdapterView.OnItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
        bold = Typeface.createFromAsset(activity.getAssets(), "fonts/HelveticaNeue Medium.ttf");
        regular = Typeface.createFromAsset(activity.getAssets(), "fonts/HelveticaNeue Light.ttf");

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_clinical_lab_detail_v1, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final LstLaboratorySpecimenResults model = arrData.get(holder.getAdapterPosition());
        holder.txtReportName.setText(model.getReportName());
        CustomTypefaceSpan customTypefaceSpan;
        /**
         *
         * Colors of Result accordingly
         * Panic High ---- > Red color with bold font
         * High       ---- > Red
         * Panic low  ---- > Blue color with bold font
         * Low        ---- > Blue
         **/
        if (model.getAbnormalFlag() == null || model.getAbnormalFlag().isEmpty()) {
            holder.txtResult.setTextColor(activity.getResources().getColor(R.color.text_color_grey));
            holder.txtState.setTextColor(activity.getResources().getColor(R.color.text_color_grey));
            holder.txtState.setBackground(activity.getResources().getDrawable(R.drawable.rounded_stroke_white));
            customTypefaceSpan = new CustomTypefaceSpan(bold);
        } else if (model.getAbnormalFlag().equalsIgnoreCase("Low")) {
            customTypefaceSpan = new CustomTypefaceSpan(bold);
            holder.txtResult.setTextColor(activity.getResources().getColor(R.color.panic_blue));
            holder.txtState.setTextColor(activity.getResources().getColor(R.color.panic_blue));
            holder.txtState.setBackground(activity.getResources().getDrawable(R.drawable.rounded_stroke_blue));

        } else if (model.getAbnormalFlag().equalsIgnoreCase("High")) {
            customTypefaceSpan = new CustomTypefaceSpan(bold);
            holder.txtState.setTextColor(activity.getResources().getColor(R.color.base_reddish));
            holder.txtResult.setTextColor(activity.getResources().getColor(R.color.base_reddish));
            holder.txtState.setBackground(activity.getResources().getDrawable(R.drawable.rounded_stroke_red));

        } else if (model.getAbnormalFlag().equalsIgnoreCase("Panic High") || model.getAbnormalFlag().equalsIgnoreCase("ph")) {
            holder.txtState.setTextColor(activity.getResources().getColor(R.color.base_reddish));
            holder.txtResult.setTextColor(activity.getResources().getColor(R.color.base_reddish));
            holder.txtState.setBackground(activity.getResources().getDrawable(R.drawable.rounded_stroke_red));

            model.setAbnormalFlag("High");
            customTypefaceSpan = new CustomTypefaceSpan(bold);
        } else {
            holder.txtState.setTextColor(activity.getResources().getColor(R.color.panic_blue));
            holder.txtResult.setTextColor(activity.getResources().getColor(R.color.panic_blue));
            holder.txtState.setBackground(activity.getResources().getDrawable(R.drawable.rounded_stroke_blue));
            customTypefaceSpan = new CustomTypefaceSpan(bold);
            model.setAbnormalFlag("Low");
        }

//        Spanny resultSpanny = new Spanny(model.getResult(), customTypefaceSpan).append(" " + model.getAbnormalFlag(),
//                new AbsoluteSizeSpan(activity.getResources().getDimensionPixelSize(R.dimen.s10)));
        Spanny resultSpanny = new Spanny(model.getResult(), customTypefaceSpan);
        Spanny stateSpanny = new Spanny(model.getAbnormalFlag(), customTypefaceSpan);
        holder.txtResult.setText(resultSpanny);
        holder.txtState.setText(stateSpanny);


        if ((model.getComments() == null || model.getComments().isEmpty()) && (model.getResultComments() == null || model.getResultComments().isEmpty())) {
//            holder.btnComment.setVisibility(View.GONE);
            holder.btnComment.setEnabled(false);
            holder.btnComment.setAlpha(.15f);

        } else {
            holder.btnComment.setVisibility(View.VISIBLE);
            holder.btnComment.setEnabled(true);

        }

        if ((model.getPrevResult1() == null || model.getPrevResult1().isEmpty()) && (model.getPrevResult2() == null || model.getPrevResult2().isEmpty())) {
//            holder.btnHistory.setVisibility(View.GONE);
            holder.btnHistory.setEnabled(false);
            holder.btnHistory.setAlpha(.15f);
        } else {
            holder.btnHistory.setVisibility(View.VISIBLE);
            holder.btnHistory.setEnabled(true);
        }

        if ((!holder.btnHistory.isEnabled()) && (!holder.btnComment.isEnabled())) {
//            holder.historySeperator.setVisibility(View.GONE);
            holder.contButtonLayout.setVisibility(View.INVISIBLE);
        } else {
            holder.contButtonLayout.setVisibility(View.VISIBLE);
//            holder.historySeperator.setVisibility(View.VISIBLE);
        }
        setListener(holder, model);


        if ((model.getNormalRangeFormatted() == null || model.getNormalRangeFormatted().isEmpty()) && (model.getUnit() == null || model.getUnit().isEmpty())) {
            holder.txtNormalRangeFormatted.setVisibility(View.INVISIBLE);

        } else {
            Spanny spanny = new Spanny("Range " + model.getNormalRangeFormatted());
            holder.txtNormalRangeFormatted.setText(spanny);
            holder.txtNormalRangeFormatted.setVisibility(View.VISIBLE);
        }

        holder.txtunit.setText(model.getUnit());

    }

    //    private void setListener(final ViewHolder holder, final LstLaboratorySpecimenResults lstLaboratorySpecimenResults) {
//        holder.btnComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClick.onItemClick(holder.getAdapterPosition(), lstLaboratorySpecimenResults);
//            }
//        });
//        holder.btnHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClick.onItemClick(holder.getAdapterPosition(), lstLaboratorySpecimenResults);
//            }
//        });
//    }
    private void setListener(final ViewHolder holder, final LstLaboratorySpecimenResults lstLaboratorySpecimenResults) {
        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
            }
        });

        holder.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
            }
        });

    }

    public LstLaboratorySpecimenResults getItem(int position) {
        return arrData.get(position);
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtReportName)
        AnyTextView txtReportName;
        @BindView(R.id.txtResult)
        AnyTextView txtResult;
        @BindView(R.id.txtNormalRangeFormatted)
        AnyTextView txtNormalRangeFormatted;
        //        @BindView(R.id.historySeperator)
//        ImageView historySeperator;
        @BindView(R.id.txtComments)
        AnyTextView btnComment;
        @BindView(R.id.btnHistory)
        AnyTextView btnHistory;
        @BindView(R.id.txtunit)
        AnyTextView txtunit;
        @BindView(R.id.txtState)
        AnyTextView txtState;
        @BindView(R.id.cardView2)
        CardView cardView2;
        @BindView(R.id.contButtonLayout)
        LinearLayout contButtonLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
