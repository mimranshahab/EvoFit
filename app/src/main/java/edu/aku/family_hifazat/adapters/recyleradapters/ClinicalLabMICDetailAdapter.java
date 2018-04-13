package edu.aku.family_hifazat.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.callbacks.OnItemClickListener;
import edu.aku.family_hifazat.models.BannerModel;
import edu.aku.family_hifazat.models.LstLaboratoryMicspecimenResults;
import edu.aku.family_hifazat.widget.AnyTextView;

/**
 */
public class ClinicalLabMICDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final OnItemClickListener onItemClick;

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 0;

    private Activity activity;
    private ArrayList arrData;

    public ClinicalLabMICDetailAdapter(Activity activity, ArrayList arrayList, OnItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }


    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isHeaderPosition(int position) {
        return arrData.get(position) instanceof BannerModel;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            //inflate your layout and pass it to view holder
            return new ViewHolderHeader(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_mic_banner, parent, false));
        } else {
            //inflate your layout and pass it to view holder
            return new ViewHolderItem(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_mic_detail, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolderHeader) {
            ViewHolderHeader holderHeader = (ViewHolderHeader) holder;

            BannerModel bannerModel = (BannerModel) arrData.get(holder.getAdapterPosition());
            holderHeader.txtProcedureDesc.setText(bannerModel.getHeader());
            holderHeader.txtSource.setText("Source: " + bannerModel.getSubheader());
        } else if (holder instanceof ViewHolderItem) {
            ViewHolderItem holderItem = (ViewHolderItem) holder;
            holderItem.contHeader.setVisibility(View.GONE);
            LstLaboratoryMicspecimenResults lstLaboratoryMicspecimenResults = (LstLaboratoryMicspecimenResults) arrData.get(holder.getAdapterPosition());

            holderItem.txtShortMessage.setText(lstLaboratoryMicspecimenResults.getProcedureDescription());

            if (lstLaboratoryMicspecimenResults.getProcedureTypeId().equals("P") && lstLaboratoryMicspecimenResults.getLstMicSpecParaResult() != null && !lstLaboratoryMicspecimenResults.getLstMicSpecParaResult().isEmpty()) {
                holderItem.txtDate.setVisibility(View.VISIBLE);
                holderItem.txtDate.setText(lstLaboratoryMicspecimenResults.getLstMicSpecParaResult().get(0).getPARATYPE());
            } else if (lstLaboratoryMicspecimenResults.getProcedureTypeId().equals("Q") && lstLaboratoryMicspecimenResults.getLstMicSpecQueryResult() != null && !lstLaboratoryMicspecimenResults.getLstMicSpecQueryResult().isEmpty()) {
                holderItem.txtDate.setVisibility(View.VISIBLE);
                holderItem.txtDate.setText(lstLaboratoryMicspecimenResults.getLstMicSpecQueryResult().get(0).getParatype());
            } else {
                holderItem.txtDate.setVisibility(View.GONE);
            }
            setListener(holderItem, lstLaboratoryMicspecimenResults);

        }


    }

    private void setListener(final ViewHolderItem holder, final Object object) {
        holder.contLastLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), object);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }

    static class ViewHolderHeader extends RecyclerView.ViewHolder {

        @BindView(R.id.txtProcedureDesc)
        AnyTextView txtProcedureDesc;
        @BindView(R.id.txtSource)
        AnyTextView txtSource;

        ViewHolderHeader(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderItem extends RecyclerView.ViewHolder {

        @BindView(R.id.imgTitle)
        ImageView imgTitle;
        @BindView(R.id.txtTitle)
        AnyTextView txtTitle;
        @BindView(R.id.contHeader)
        LinearLayout contHeader;
        @BindView(R.id.txtShortMessage)
        AnyTextView txtShortMessage;
        @BindView(R.id.txtDate)
        AnyTextView txtDate;
        @BindView(R.id.imgClick)
        ImageView imgClick;
        @BindView(R.id.contLastLab)
        LinearLayout contLastLab;
        @BindView(R.id.frameColorCode)
        FrameLayout frameColorCode;

        ViewHolderItem(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
