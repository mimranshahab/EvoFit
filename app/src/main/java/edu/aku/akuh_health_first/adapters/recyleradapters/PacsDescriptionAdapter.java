package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.models.PacsDescriptionModel;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class PacsDescriptionAdapter extends RecyclerView.Adapter<PacsDescriptionAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;

    private Activity activity;
    private ArrayList<PacsDescriptionModel> arrayList;

    public PacsDescriptionAdapter(Activity activity, ArrayList<PacsDescriptionModel> userList, OnItemClickListener onItemClickListener) {
        this.arrayList = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_pacs, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final PacsDescriptionModel model = arrayList.get(holder.getAdapterPosition());
        holder.txtpatientName.setText("Name : "+model.getPatient_Name());
        holder.txtpatientDOB.setText("DOB : "+model.getPatientDOB());
        holder.txtPatientGender.setText("Gender : "+model.getPatientGender());
        holder.txtpatientMRN.setText("MRNumber : "+model.getPatientMRN());
        holder.txtstudyTitle.setText("Title : "+model.getStudyTitle());
        holder.txtstudyDataCount.setText(model.getStudyDataCount());
        holder.txtstudyDataDateTime.setText("Date & Time : "+model.getStudyDataDateTime());
        setListener(holder, model);
    }

    private void setListener(final PacsDescriptionAdapter.ViewHolder holder, final PacsDescriptionModel pacsDescriptionModel) {
        holder.contListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), pacsDescriptionModel);
            }
        });}
    public PacsDescriptionModel getItem(int position) {
        return arrayList.get(position);
    }

    public void addItem(ArrayList<PacsDescriptionModel> homeCategories) {
        this.arrayList = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtstudyDataCount)
        AnyTextView txtstudyDataCount;
        @BindView(R.id.txtstudyDataDateTime)
        AnyTextView txtstudyDataDateTime;
        @BindView(R.id.txtstudyTitle)
        AnyTextView txtstudyTitle;
        @BindView(R.id.txtpatient_Name)
        AnyTextView txtpatientName;
        @BindView(R.id.txtpatientMRN)
        AnyTextView txtpatientMRN;
        @BindView(R.id.txtPatientGender)
        AnyTextView txtPatientGender;
        @BindView(R.id.txtpatientDOB)
        AnyTextView txtpatientDOB;
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
