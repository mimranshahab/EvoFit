
package edu.aku.akuh_health_first.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.libraries.fileloader.FileLoader;
import edu.aku.akuh_health_first.libraries.fileloader.listener.FileRequestListener;
import edu.aku.akuh_health_first.libraries.fileloader.pojo.FileResponse;
import edu.aku.akuh_health_first.libraries.fileloader.request.FileLoadRequest;
import edu.aku.akuh_health_first.libraries.fileloader.request.MultiFileLoadRequest;

public class PacsActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<Bitmap> imgList;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacs);
        final ImageView iv = (ImageView)findViewById(R.id.image);
        final Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnRight = (Button) findViewById(R.id.btnRight);
        final TextView tvProgress = (TextView)findViewById(R.id.tv_progress);


        final String[] uris = {"https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996566936592174&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996569933571814&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996572917041775&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996575994352617&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996578921212408&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996581918961194&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996584993451574&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996587929365361&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996590916692176&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996593916775950&contentType=image/jpeg",
                "https://pacsviewer.aku.edu/wado?requestType=WADO&studyUID=1.2.528.1.1008.91758213637.1507007296.94.2&seriesUID=1.2.392.200036.9116.7.8.6.30623300.2.0.4467365923412484&objectUID=1.2.392.200036.9116.7.8.6.30623300.5.0.2996596915381886&contentType=image/jpeg"};

        final List<MultiFileLoadRequest> multiFileLoadRequests = new ArrayList<>();
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[0], "test4", FileLoader.DIR_CACHE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[1], "test4", FileLoader.DIR_CACHE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[2], "test4", FileLoader.DIR_CACHE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[3], "test4", FileLoader.DIR_CACHE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[4], "test4", FileLoader.DIR_CACHE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[5], "test4", FileLoader.DIR_CACHE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[6], "test4", FileLoader.DIR_CACHE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[7], "test4", FileLoader.DIR_CACHE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[8], "test4", FileLoader.DIR_CACHE, true));
        multiFileLoadRequests.add(new MultiFileLoadRequest(uris[9], "test4", FileLoader.DIR_CACHE, true));

        loadImage(iv, uris[0]);
        tvProgress.setText(1 + " of " + multiFileLoadRequests.size());

        for (int i = 1; i <= multiFileLoadRequests.size(); i++) {
            progress = i;
            btnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (progress <= 1) {
                        return;
                    } else {
                        loadImage(iv, uris[--progress]);
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
                        loadImage(iv, uris[++progress]);
                        tvProgress.setText(progress + " of " + multiFileLoadRequests.size());
                    }
//                iv.setImageBitmap(imgList.get(progress));
                }
            });


//                    imgList.add(BitmapFactory.decodeFile(downloadedFile.getAbsolutePath()));


//
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

    private void loadImage(final ImageView iv, String imageUrl) {
        iv.setImageBitmap(null);
        FileLoader.with(this)
                .load(imageUrl)
//                .fromDirectory("test4", FileLoader.DIR_INTERNAL)
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
