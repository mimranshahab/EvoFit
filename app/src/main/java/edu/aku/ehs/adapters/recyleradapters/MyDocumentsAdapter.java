/**
 * Created by aqsa.sarwar on 1/31/2018.
 */

//public class MyDocumentsAdapter extends RecyclerView.Adapter(CardioAdapter.ViewHolder) {
//}
package edu.aku.ehs.adapters.recyleradapters;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.ehs.R;
import edu.aku.ehs.callbacks.OnItemClickListener;
import edu.aku.ehs.constatnts.AppConstants;
import edu.aku.ehs.managers.FileManager;
import edu.aku.ehs.widget.AnyTextView;

/**
 */
public class MyDocumentsAdapter extends RecyclerView.Adapter<MyDocumentsAdapter.ViewHolder> {


    private Activity activity;
    private ArrayList<File> arrData;
    private OnItemClickListener onItemClickListener;

    public MyDocumentsAdapter(Activity activity, ArrayList<File> arrayList, OnItemClickListener onItemClickListener) {
        this.arrData = arrayList;
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_downloaded_files, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final File model = arrData.get(holder.getAdapterPosition());

        holder.txtFileName.setText(model.getName());

        setListener(holder, model);

    }

    private void setListener(final ViewHolder holder, final File model) {

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(holder.getAdapterPosition(), model);
            }
        });

        holder.contItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final File file = new File(AppConstants.getUserFolderPath(activity)
                        + "/" + model.getName());
                if (FileManager.isFileExits(file.getPath())) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            FileManager.openFile(activity, file);
                        }
                    }, 100);


                }
            }

        });
    }

    public void addItem(ArrayList<File> homeCategories) {
        this.arrData = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contItem)
        LinearLayout contItem;
        @BindView(R.id.txtFileName)
        AnyTextView txtFileName;
        @BindView(R.id.btnDelete)
        ImageView btnDelete;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
