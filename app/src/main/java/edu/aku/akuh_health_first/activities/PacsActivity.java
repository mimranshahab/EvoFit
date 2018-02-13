
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
    private ImageButton btnLeft;
    private ImageButton btnRight;
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
        uri.size();

        final List<MultiFileLoadRequest> multiFileLoadRequests = new ArrayList<>();
//        MultiFileLoadRequest[] loadRequestArr = new MultiFileLoadRequest[uri.size()];
        for (int i = 0; i < uri.size(); i++) {
            MultiFileLoadRequest loadRequest = new MultiFileLoadRequest(uri.get(i).toString());
            multiFileLoadRequests.add(i, loadRequest);

//        final String[] uris = {"https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996566936592174&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996569933571814&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996572917041775&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996575994352617&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996578921212408&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996581918961194&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996584993451574&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996587929365361&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996590916692176&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996593916775950&contentType=image/jpeg",
//                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996596915381886&contentType=image/jpeg"};

//            final List<MultiFileLoadRequest> multiFileLoadRequests = new ArrayList<>();
//
//            multiFileLoadRequests.addAll(loadRequestArr)
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[0]));
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[1]));
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[2]));
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[3]));
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[4]));
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[5]));
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[6]));
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[7]));
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[8]));
//            multiFileLoadRequests.add(new MultiFileLoadRequest(uris[9]));

            loadImage(iv, uri.get(0).toString());
            tvProgress.setText(1 + " of " + multiFileLoadRequests.size());

            for (int j = 1; j <= multiFileLoadRequests.size(); j++) {
                progress = j;
                btnLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (progress <= 1) {
                            return;
                        } else {
                            loadImage(iv, uri.get(--progress).toString());
                            tvProgress.setText(progress + " of " + multiFileLoadRequests.size());
                        }

                    }
                });

                btnRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (progress >= multiFileLoadRequests.size()) {
                            return;
                        } else {
                            loadImage(iv, uri.get(progress++).toString());
                            tvProgress.setText(progress + " of " + multiFileLoadRequests.size());
                        }
                    }
                });


            }

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

    }


    private void bindViews() {
        iv = (ImageView) findViewById(R.id.image);
        btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        btnRight = (ImageButton) findViewById(R.id.btnRight);
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
