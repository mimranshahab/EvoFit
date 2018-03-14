package edu.aku.akuh_health_first.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.libraries.fileloader.FileLoader;
import edu.aku.akuh_health_first.libraries.fileloader.listener.FileRequestListener;
import edu.aku.akuh_health_first.libraries.fileloader.pojo.FileResponse;
import edu.aku.akuh_health_first.libraries.fileloader.request.FileLoadRequest;
import edu.aku.akuh_health_first.managers.SharedPreferenceManager;
import edu.aku.akuh_health_first.managers.retrofit.GsonFactory;
import edu.aku.akuh_health_first.models.PacsDescriptionModel;
import edu.aku.akuh_health_first.models.TupleModel;
import edu.aku.akuh_health_first.views.AnyTextView;

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

    private int pointer;
    private PacsDescriptionModel pacsModel;

    private ArrayList<String> pacsList;
    private int min = 0, max = 0;
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


        String fromJson = SharedPreferenceManager.getInstance(this).getString("JSON_STRING_KEY");
        pacsModel = GsonFactory.getSimpleGson().fromJson(fromJson, PacsDescriptionModel.class);
        pacsList = (ArrayList<String>) pacsModel.getStudyDataString();

        uriArrToTuple(pacsList.size());
        selectedTupleModel = arrTupleModel.get(0);
        selectedTupleIndex = 0;

        if (Helper.isNetworkConnected(this, true)) {
            updateData(arrTupleModel.get(0));
        }
        setListeners();
        txttotalCount.setText("Total count "+pacsList.size()+"");

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


    }


    private void updateData(TupleModel tupleModel) {
        loader.show();
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
        titlebar.setTitle("PACS Viewer");

    }

    private void uriArrToTuple(int size) {

        for (int j = size; j > 0; j = j - 30) {
            if (j > 30) {
                min = max + 0;
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
                    return;
                } else {
                    pointer--;
                    if (pacsList.get(pointer) != null) {
                        loadImage(image, pacsList.get(pointer).toString(), true);
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
                    return;
                } else {
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

                } else {
                    UIHelper.showToast(PacsActivity.this, "No further batch");
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
                }
            }
        });


        indicatorSeekBar.setMin(selectedTupleModel.getMin() + 1);
        indicatorSeekBar.setMax(selectedTupleModel.getMax() + 1);

        indicatorSeekBar.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
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
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        Bitmap bitmap = BitmapFactory.decodeFile(response.getDownloadedFile().getPath());
                        if (isShowImage) {
                            iv.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.d(TAG, "onError: " + t.getMessage());
                    }
                });
    }

//
}
