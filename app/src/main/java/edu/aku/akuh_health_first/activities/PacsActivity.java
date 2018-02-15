
package edu.aku.akuh_health_first.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.constatnts.WebServiceConstants;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.libraries.fileloader.FileLoader;
import edu.aku.akuh_health_first.libraries.fileloader.listener.FileRequestListener;
import edu.aku.akuh_health_first.libraries.fileloader.pojo.FileResponse;
import edu.aku.akuh_health_first.libraries.fileloader.request.FileLoadRequest;
import edu.aku.akuh_health_first.libraries.fileloader.request.MultiFileLoadRequest;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.models.PacsDescriptionModel;
import edu.aku.akuh_health_first.models.PacsModel;

public class PacsActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private int progress;
    private String fromJson;
    private PacsDescriptionModel pacsModel;
    private ImageView iv;
    private ImageButton btnPrevious;
    private ImageButton btnNext;
    private TextView tvProgress;
    private ArrayList<String> pacsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacs);
        bindViews();

        fromJson = getIntent().getExtras().getString(AppConstants.JSON_STRING_KEY);
        pacsModel = GsonFactory.getSimpleGson().fromJson(fromJson, PacsDescriptionModel.class);
        pacsList = (ArrayList<String>) pacsModel.getStudyDataString();
        final ArrayList uri = new ArrayList<String>();
        for (String stringList : pacsList) {
            uri.add(stringList);
        }


        loadImage(iv, uri.get(0).toString());
        tvProgress.setText(1 + " of " + uri.size());

//        final List<MultiFileLoadRequest> multiFileLoadRequests = new ArrayList<>();
//        for (int i = 0; i < uri.size(); i++) {
//            MultiFileLoadRequest loadRequest = new MultiFileLoadRequest(uri.get(i).toString());
//            multiFileLoadRequests.add(i, loadRequest);
           setListeners(uri);

        }
//        final MultiFileDownloader multiFileDownloader = FileLoader.multiFileDownload(this);
//        multiFileDownloader.progressListener(new MultiFileDownloadListener() {
//            @Override
//            public void onProgress(final File downloadedFile, final int progress, final int totalFiles) {
////                multiFileDownloader.cancelLoad();
//
////                imgList = new ArrayList<Bitmap>();
////
////                for (int i = 1; i <= totalFiles; i++) {
////                    imgList.add(BitmapFactory.decodeFile(downloadedFile.getAbsolutePath()));
////
////                }
//                MainActivity.this.progress = progress;
//                tvProgress.setText(progress + " of " + totalFiles);
//                Glide.with(MainActivity.this).load(downloadedFile).into(iv);
//            }
//        }).loadMultiple(multiFileLoadRequests);

//    }

    private void setListeners(final ArrayList uri/*, final List<MultiFileLoadRequest> multiFileLoadRequests*/) {
//        for (int j = 1; j <= multiFileLoadRequests.size(); j++) {
            progress = uri.size();
            btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( progress <= 1) {
                        return;
                    } else {
                        loadImage(iv, uri.get(--progress).toString());
                        tvProgress.setText(progress + " of " + uri.size());
                    }

                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (progress >= uri.size()) {
                        return;
                    } else {
                        loadImage(iv, uri.get(progress++).toString());
                        tvProgress.setText(progress + " of " + uri.size());
                    }
                }
            });


        }



    private void bindViews() {
        iv = (ImageView) findViewById(R.id.image);
        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        tvProgress = (TextView) findViewById(R.id.tv_progress);
        pacsList = new ArrayList<String>();
    }

    private void loadImage(final ImageView iv, String imageUrl) {
        iv.setImageBitmap(null);
        FileLoader.with(this)
                .load(imageUrl)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        Bitmap bitmap = BitmapFactory.decodeFile(response.getDownloadedFile().getPath());
                        iv.setImageBitmap(bitmap);


                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.d(TAG, "onError: " + t.getMessage());
                    }
                });
    }
}
