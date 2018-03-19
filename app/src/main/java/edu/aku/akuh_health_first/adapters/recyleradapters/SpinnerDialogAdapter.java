package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnSpinnerItemClickListener;
import edu.aku.akuh_health_first.models.SpinnerModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class SpinnerDialogAdapter extends RecyclerView.Adapter<SpinnerDialogAdapter.ViewHolder> {


    private final OnSpinnerItemClickListener onItemClick;



    private Activity activity;
    private ArrayList<SpinnerModel> arrData;

    public SpinnerDialogAdapter(Activity activity, ArrayList<SpinnerModel> arrayList, OnSpinnerItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_spinner_dialog, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final SpinnerModel model = arrData.get(holder.getAdapterPosition());

        holder.txtChoice.setText(model.getText());
        if (model.isSelected()) {
            holder.checkbox.setChecked(true);
        } else {
            holder.checkbox.setChecked(false);
        }
        holder.contParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(holder.getAdapterPosition(), model, SpinnerDialogAdapter.this);
            }
        });


    }

    public void addItem(ArrayList<SpinnerModel> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.checkbox)
        CheckBox checkbox;
        @BindView(R.id.txtChoice)
        AnyTextView txtChoice;
        @BindView(R.id.contParentLayout)
        LinearLayout contParentLayout;

        ViewHolder(View view) {

            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
