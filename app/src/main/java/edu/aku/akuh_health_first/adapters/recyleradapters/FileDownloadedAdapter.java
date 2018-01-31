/**
 * Created by aqsa.sarwar on 1/31/2018.
 */

//public class FileDownloadedAdapter extends RecyclerView.Adapter(CardioAdapter.ViewHolder) {
//}
package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.FileManager;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class FileDownloadedAdapter extends RecyclerView.Adapter<FileDownloadedAdapter.ViewHolder> {


    private Activity activity;
    private ArrayList<File> arrData;

    public FileDownloadedAdapter(Activity activity, ArrayList<File> arrayList) {
        this.arrData = arrayList;
        this.activity = activity;

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
//        holder.txtFileName.setText(arrData.get(i).getName());

        holder.txtFileName.setOnClickListener(new View.OnClickListener() {
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
                    }, 300);


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

        @BindView(R.id.txtFileName)
        AnyTextView txtFileName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
