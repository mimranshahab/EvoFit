package edu.aku.ehs.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.ehs.R;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.models.BannerModel;
import edu.aku.ehs.widget.AnyTextView;

/**
 */
public class ClinicalLabQeuryResultAdapter extends RecyclerView.Adapter<ClinicalLabQeuryResultAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;



    private Activity activity;
    private ArrayList<BannerModel> arrData;

    public ClinicalLabQeuryResultAdapter(Activity activity, ArrayList<BannerModel> arrayList, OnItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_query_result, parent, false));

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int  position) {

            BannerModel bannerModel =  arrData.get(holder.getAdapterPosition());
            holder.txtQueryString.setText(bannerModel.getHeader());
            holder.txtQueryResult.setText(bannerModel.getSubheader());
            if (position % 2 == 0) {
               holder.contParentLayout.setBackgroundColor(activity.getResources().getColor(R.color.c_white));
            } else {
               holder.contParentLayout.setBackgroundColor(activity.getResources().getColor(R.color.query_grey));
            }

    }

    private void setListener(final ViewHolder holder, final Object object) {
//        holder.contLastLab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClick.onItemClick(holder.getAdapterPosition(), object);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtQueryString)
        AnyTextView txtQueryString;
        @BindView(R.id.txtQueryResult)
        AnyTextView txtQueryResult;
        @BindView(R.id.contParentLayout)
        LinearLayout contParentLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
