package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.models.CardioModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 */
public class CardioAdapter extends RecyclerView.Adapter<CardioAdapter.ViewHolder> {


    private final AdapterView.OnItemClickListener onItemClick;


    private Activity activity;
    private ArrayList<CardioModel> arrListCardioModel;

    public CardioAdapter(Activity activity, ArrayList<CardioModel> userList, AdapterView.OnItemClickListener onItemClickListener) {
        this.arrListCardioModel = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_cps_nps_endo_summary_, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final CardioModel model = arrListCardioModel.get(holder.getAdapterPosition());
        holder.txtDateTime.setText(model.getRequestServiceDateTime());

        holder.txtName.setText(model.getService());

        holder.RlReport.setVisibility(View.VISIBLE);
        holder.RlGraph.setVisibility(View.VISIBLE);

        setEnability(holder, model);
        setListener(holder, model);

        if (model.getStatus().equalsIgnoreCase("Signed")) {
            holder.txtStatusType.setText("Finalised");
            setViews(holder, activity.getResources().getColor(R.color.base_green),
                    R.drawable.rounded_box_filled_base_green, R.drawable.cardiopulmonary_green);

        } else {
            holder.txtStatusType.setText("Pending");

            setViews(holder, activity.getResources().getColor(R.color.base_reddish),
                    R.drawable.rounded_box_filled_base_red, R.drawable.cardiopulmonary_red);
        }


    }

    private void setViews(ViewHolder holder, int color, int backgroundResource, int circular_background) {
        holder.cardView2.setCardBackgroundColor(color);
        holder.txtStatusType.setBackgroundResource(backgroundResource);
        holder.imgIcon.setImageResource(circular_background);
    }

    private void setEnability(ViewHolder holder, CardioModel cardioModel) {

        if (cardioModel.getGraphAvailable().equalsIgnoreCase("false")) {
            holder.RlGraph.setEnabled(false);
            holder.RlGraph.setAlpha(.15f);
        }

        if (cardioModel.getReportable().equalsIgnoreCase("false")) {
            holder.RlReport.setEnabled(false);
            holder.RlReport.setAlpha(.15f);

        }
    }

    private void setListener(final ViewHolder holder, final CardioModel cardioModel) {
        holder.RlGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
            }
        });

        holder.RlReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(null, v, holder.getAdapterPosition(), 0);
            }
        });

//        holder.btnShowReport.setOnClickListener(clickListener);
    }

    public CardioModel getItem(int position) {
        return arrListCardioModel.get(position);
    }

    public void addItem(ArrayList<CardioModel> homeCategories) {
        this.arrListCardioModel = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrListCardioModel.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDateTime)
        AnyTextView txtDateTime;
        @BindView(R.id.txtStatusType)
        AnyTextView txtStatusType;
        @BindView(R.id.imgIcon)
        CircleImageView imgIcon;
        @BindView(R.id.txtName)
        AnyTextView txtName;
        @BindView(R.id.txtDrName)
        AnyTextView txtDrName;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.btnReportColorCode1)
        AnyTextView btnReportColorCode1;

        @BindView(R.id.btnGraphColorCode)
        AnyTextView btnGraphColorCode;
        @BindView(R.id.RlReport)
        RelativeLayout RlReport;
        @BindView(R.id.RlGraph)
        RelativeLayout RlGraph;
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
