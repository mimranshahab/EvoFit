package edu.aku.akuh_health_first.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jsibbold.zoomage.ZoomageView;
import com.warkiz.widget.IndicatorSeekBar;

import java.io.File;
import java.util.ArrayList;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.helperclasses.Helper;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.libraries.fileloader.FileLoader;
import edu.aku.akuh_health_first.libraries.fileloader.listener.FileRequestListener;
import edu.aku.akuh_health_first.libraries.fileloader.listener.MultiFileDownloadListener;
import edu.aku.akuh_health_first.libraries.fileloader.pojo.FileResponse;
import edu.aku.akuh_health_first.libraries.fileloader.request.FileLoadRequest;
import edu.aku.akuh_health_first.managers.SharedPreferenceManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.models.PacsDescriptionModel;
import edu.aku.akuh_health_first.models.TupleModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

public class PacsActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TitleBar titlebar;
    ZoomageView image;
    AnyTextView btnPrevious;
    AnyTextView tvProgress;
    AnyTextView btnNext;
    ProgressBar progressbar;
    AnyTextView btnPreviousBatch;
    IndicatorSeekBar indicatorSeekBar;
    AnyTextView txttotalCount;
    AnyTextView btnNextBatch;
    AnyTextView txtUserName;

    AnyTextView txtMRnumber;
    AnyTextView txtStudyTitle;
    ProgressBar progressBar;

    private int pointer;
    private ArrayList<String> pacsList;
    private int max = 0;

    ProgressDialog loader;
    private ArrayList<TupleModel> arrTupleModel;
    TupleModel selectedTupleModel;
    int selectedTupleIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacsv1);
        arrTupleModel = new ArrayList<>();
        loader = Helper.getLoader(this);
        bindViews();


        settitlebar();


        SharedPreferenceManager sharedPreferenceManager = SharedPreferenceManager.getInstance(this);
        String fromJson = sharedPreferenceManager.getString("JSON_STRING_KEY");

        PacsDescriptionModel pacsModel = GsonFactory.getSimpleGson().fromJson(fromJson, PacsDescriptionModel.class);
        pacsList = (ArrayList<String>) pacsModel.getStudyDataString();


        txttotalCount.setText("Total count " + pacsList.size() + "");
        txtUserName.setText(pacsModel.getPatient_Name());
        txtMRnumber.setText(pacsModel.getPatientMRN());
        txtStudyTitle.setText(pacsModel.getStudyTitle());

        uriArrToTuple(pacsList.size());
        selectedTupleModel = arrTupleModel.get(0);
        selectedTupleIndex = 0;


        if (Helper.isNetworkConnected(this, true)) {
            updateData(arrTupleModel.get(0));
        }
        setListeners();


        indicatorSeekBar.setProgress(0);

        if (arrTupleModel.size() <= 1) {
            btnNextBatch.setEnabled(false);
            btnPreviousBatch.setEnabled(false);
            btnNextBatch.setAlpha(0.4f);
            btnPreviousBatch.setAlpha(0.4f);
        } else {
            btnNextBatch.setEnabled(true);
            btnPreviousBatch.setEnabled(false);
            btnNextBatch.setAlpha(1f);
            btnPreviousBatch.setAlpha(1f);
        }

        if (pacsList.size() <= 1) {
            indicatorSeekBar.setVisibility(View.GONE);
        } else {
            indicatorSeekBar.setVisibility(View.VISIBLE);
        }


    }

    private void bindViews() {

        titlebar = findViewById(R.id.titlebar);
        image = findViewById(R.id.image);
        btnPrevious = findViewById(R.id.btnPrevious);
        tvProgress = findViewById(R.id.tv_progress);
        btnNext = findViewById(R.id.btnNext);
        progressbar = findViewById(R.id.progressBar);
        btnPreviousBatch = findViewById(R.id.btnPreviousBatch);
        indicatorSeekBar = findViewById(R.id.indSeekbar);
        txttotalCount = findViewById(R.id.txttotalCount);
        btnNextBatch = findViewById(R.id.btnNextBatch);
        progressbar = findViewById(R.id.progressBar);
        txtUserName = findViewById(R.id.txtNamePacs);
        txtMRnumber = findViewById(R.id.txtMRNPacs);
        txtStudyTitle = findViewById(R.id.txtStudyTitle);

    }


    private void updateData(TupleModel tupleModel) {
        loader.show();

//        loadMultipleFiles(pacsList.subList(tupleModel.getMin(), tupleModel.getMax()).toArray(new String[0]));
        for (int i = tupleModel.getMin(); i <= tupleModel.getMax(); i++) {
            if (pacsList.get(i) != null) {
                loadImage(image, pacsList.get(i), false);
            }
        }
        loader.dismiss();
        loadImage(image, pacsList.get(tupleModel.getMin()), true);
        tvProgress.setText((tupleModel.getMin() + 1) + " of " + pacsList.size());
        pointer = tupleModel.getMin();
        indicatorSeekBar.setMax(tupleModel.getMax() + 1);
        indicatorSeekBar.setMin(tupleModel.getMin() + 1);
        indicatorSeekBar.setProgress(tupleModel.getMin() + 1);
    }


    private void settitlebar() {
        titlebar.resetViews();
        titlebar.setVisibility(View.VISIBLE);
        titlebar.showBackButton(this);
        titlebar.setTitle("Radiology Images");
        titlebar.setRightButton2(this, R.drawable.b_home_icon, null, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllActivitiesExceptThis(HomeActivity.class);
            }
        });

    }

    public void clearAllActivitiesExceptThis(Class<?> cls) {
        Intent intents = new Intent(this, cls);
        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intents);
        finish();
    }

    private void uriArrToTuple(int size) {

        for (int j = size; j > 0; j = j - 30) {
            if (j > 30) {
                int min = max;
                max = max + 30;
                TupleModel tupleModel = new TupleModel();
                tupleModel.setMin(min);
                tupleModel.setMax(max);
                arrTupleModel.add(tupleModel);
            } else {
                TupleModel tupleModel = new TupleModel();
                tupleModel.setMin(max);
                tupleModel.setMax(size - 1);
                arrTupleModel.add(tupleModel);
            }
        }

    }

    private void setListeners() {
        pointer = selectedTupleModel.getMin();
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pointer <= selectedTupleModel.getMin()) {
                    btnPrevious.setEnabled(false);
                    btnPrevious.setAlpha(0.4f);
                } else {
                    if (!btnNext.isEnabled()) {
                        btnNext.setAlpha(1f);
                        btnNext.setEnabled(true);
                    }

                    pointer--;
                    if (pacsList.get(pointer) != null) {
                        loadImage(image, pacsList.get(pointer), true);
                        tvProgress.setText(pointer + 1 + " of " + pacsList.size());
                        indicatorSeekBar.setProgress(pointer + 1);
                    }
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pointer > (selectedTupleModel.getMax() - 1)) {
                    btnNext.setEnabled(false);
                    btnNext.setAlpha(0.4f);

                } else {
                    if (!btnPrevious.isEnabled()) {
                        btnPrevious.setAlpha(1f);
                        btnPrevious.setEnabled(true);
                    }
                    pointer++;
                    if (pacsList.get(pointer) != null) {
                        loadImage(image, pacsList.get(pointer).toString(), true);
                        tvProgress.setText(pointer + 1 + " of " + pacsList.size());
                        indicatorSeekBar.setProgress(pointer + 1);

                    }
                }
            }
        });


        btnNextBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Helper.isNetworkConnected(PacsActivity.this, true)) {
                    return;
                }

                if (selectedTupleIndex < arrTupleModel.size()) {
                    selectedTupleIndex++;
                    if (arrTupleModel.size() <= selectedTupleIndex) return;
                    if (arrTupleModel.get(selectedTupleIndex) != null) {
                        selectedTupleModel = arrTupleModel.get(selectedTupleIndex);
                        updateData(selectedTupleModel);
                    }

                    if (!btnPreviousBatch.isEnabled()) {
                        btnPreviousBatch.setAlpha(1f);
                        btnPreviousBatch.setEnabled(true);
                    }

                } else {
                    btnNextBatch.setEnabled(false);
                    btnNextBatch.setAlpha(0.4f);
                }
            }
        });


        btnPreviousBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Helper.isNetworkConnected(PacsActivity.this, true)) {
                    return;
                }

                if (selectedTupleIndex > 0) {
                    selectedTupleIndex--;
                    if (arrTupleModel.get(selectedTupleIndex) != null) {
                        selectedTupleModel = arrTupleModel.get(selectedTupleIndex);
                        updateData(selectedTupleModel);
                    }
                    if (!btnNextBatch.isEnabled()) {
                        btnNextBatch.setAlpha(1f);
                        btnNextBatch.setEnabled(true);
                    }
                } else {
                    btnPreviousBatch.setEnabled(false);
                    btnPreviousBatch.setAlpha(0.4f);
                }
            }
        });


        indicatorSeekBar.setMin(selectedTupleModel.getMin() + 1);
        indicatorSeekBar.setMax(selectedTupleModel.getMax() + 1);

        indicatorSeekBar.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {

                if (indicatorSeekBar.getMin() == progress) {
                    btnPrevious.setEnabled(false);
                    btnPrevious.setAlpha(0.4f);
                } else {
                    if (!btnPrevious.isEnabled()) {
                        btnPrevious.setEnabled(true);
                        btnPrevious.setAlpha(1f);
                    }
                }


                if (indicatorSeekBar.getMax() == progress) {
                    btnNext.setEnabled(false);
                    btnNext.setAlpha(0.4f);
                } else {
                    if (!btnNext.isEnabled()) {
                        btnNext.setEnabled(true);
                        btnNext.setAlpha(1f);
                    }
                }

                pointer = progress - 1;
                loadImage(image, pacsList.get(progress - 1), true);
                tvProgress.setText(progress + " of " + pacsList.size());
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

    }


    private void loadImage(final ImageView iv, String imageUrl, final boolean isShowImage) {
        FileLoader.with(this)
                .load(imageUrl)
                .asBitmap(new FileRequestListener<Bitmap>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<Bitmap> response) {
//                        Bitmap bitmap = BitmapFactory.decodeFile(response.getDownloadedFile().getPath());
                        if (isShowImage) {
                            iv.setImageBitmap(response.getBody());
                        }
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.d(TAG, "onError: " + t.getMessage());
                    }
                });
    }

    private void loadMultipleFiles(String... uris) {

        loader.show();
        FileLoader.multiFileDownload(this).progressListener(new MultiFileDownloadListener() {
            @Override
            public void onProgress(File downloadedFile, int progress, int totalFiles) {
                Log.d(TAG, "onProgress: progress: " + progress);
                loader.dismiss();
            }
        }).loadMultiple(uris);
    }

//
}
