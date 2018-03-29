package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.libraries.fileloader.FileLoader;
import edu.aku.akuh_health_first.libraries.fileloader.listener.FileRequestListener;
import edu.aku.akuh_health_first.libraries.fileloader.pojo.FileResponse;
import edu.aku.akuh_health_first.libraries.fileloader.request.FileLoadRequest;
import edu.aku.akuh_health_first.models.PacsDescriptionModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

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
        holder.txtpatientMRN.setText(model.getPatientMRN());
        holder.txtstudyDataCount.setText(model.getStudyDataCount());
        holder.txtstudyDataDateTime.setText(model.getStudyDataDateTime());

        List<String> imageUri = model.getStudyDataString();
        holder.progressBar.setVisibility(View.VISIBLE);
        loadImage(holder.imgPacs, imageUri.get(0), holder);

        setListener(holder, model);
    }

    private void setListener(final ViewHolder holder, final PacsDescriptionModel pacsDescriptionModel) {
        holder.contListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), pacsDescriptionModel);
            }
        });
    }

    public PacsDescriptionModel getItem(int position) {
        return arrayList.get(position);
    }

    public void addItem(ArrayList<PacsDescriptionModel> homeCategories) {
        this.arrayList = homeCategories;
        notifyDataSetChanged();
    }

    private void loadImage(final ImageView iv, String imageUrl, final ViewHolder viewHolder) {
        FileLoader.with(activity)
                .load(imageUrl)
                .asBitmap(new FileRequestListener<Bitmap>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<Bitmap> response) {
//                        Bitmap bitmap = BitmapFactory.decodeFile(response.getDownloadedFile().getPath());
                        iv.setImageBitmap(response.getBody());
                        viewHolder.progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        iv.setImageResource(R.drawable.radiology);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgPacs)
        ImageView imgPacs;
        @BindView(R.id.txtpatientMRN)
        AnyTextView txtpatientMRN;
        @BindView(R.id.txtstudyDataCount)
        AnyTextView txtstudyDataCount;
        @BindView(R.id.txtstudyDataDateTime)
        AnyTextView txtstudyDataDateTime;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.cardView2)
        CardView cardView2;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
