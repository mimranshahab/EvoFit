package edu.aku.akuh_health_first.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.Date;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.helperclasses.DateHelper;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.managers.DateManager;
import edu.aku.akuh_health_first.models.LaboratoryModel;
import edu.aku.akuh_health_first.views.AnyTextView;


public class GraphActivity extends BaseActivity {

    GraphView graph;
    AnyTextView txtResult;
    AnyTextView txtPrevReuslt1;
    AnyTextView txtPrevReuslt2;
    TitleBar titleBar;


    private LaboratoryModel laboratoryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        initViews();
        initData();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void initViews() {
        titleBar = findViewById(R.id.titlebar);
        graph = findViewById(R.id.graph);
    }


    @Override
    protected int getViewId() {
        return R.layout.activity_graph;
    }

    @Override
    protected int getTitlebarLayoutId() {
        return R.id.titlebar;
    }

    @Override
    protected int getDrawerLayoutId() {
        return R.id.drawer_layout;
    }

    @Override
    protected int getDockableFragmentId() {
        return R.id.contMain;
    }

    @Override
    protected int getDrawerFragmentId() {
        return R.id.contDrawer;
    }

    @Override
    protected int getPermanentViewId() {
        return R.id.contPermanent;
    }

    private void initData() {
        laboratoryModel = (LaboratoryModel) getIntent().getSerializableExtra(AppConstants.LABORATORY_MODEL);

        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle("Graph View");
        titleBar.showBackButton(this);

        updateView();
    }


    private void updateView() {
        Date resultDate;
        Date prev1Date;
        Date prev2Date;

        resultDate = DateManager.getDate(laboratoryModel.getResultEntryDttm());
        prev1Date = DateManager.getDate(laboratoryModel.getPrevResult1Dttm());
        prev2Date = DateManager.getDate(laboratoryModel.getPrevResult2Dttm());

        LineGraphSeries<DataPoint> series = getDataPointLineGraphSeries(resultDate, prev1Date, prev2Date);


        setGraph(resultDate, prev2Date, series);


    }

    @NonNull
    private LineGraphSeries<DataPoint> getDataPointLineGraphSeries(Date resultDate, Date prev1Date, Date prev2Date) {
        // you can directly pass Date objects to DataPoint-Constructor
        // this will convert the Date to double via Date#getTime()
        LineGraphSeries<DataPoint> series;
        series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(prev2Date, Double.valueOf(laboratoryModel.getPrevResult2())),
                new DataPoint(prev1Date, Double.valueOf(laboratoryModel.getPrevResult1())),
                new DataPoint(resultDate, Double.valueOf(laboratoryModel.getResult()))
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            graph.getGridLabelRenderer().setNumHorizontalLabels(3);
            AnyTextView txtPrevReuslt1 = findViewById(R.id.txtPrevReuslt1);
            AnyTextView txtPrevReuslt2 = findViewById(R.id.txtPrevReuslt2);
            AnyTextView txtResult = findViewById(R.id.txtResult);

            AnyTextView txtPrevReuslt1date = findViewById(R.id.txtPrevReuslt1Date);
            AnyTextView txtPrevReuslt2date = findViewById(R.id.txtPrevReuslt2Date);
            AnyTextView txtResultdate = findViewById(R.id.txtResultDate);
            txtPrevReuslt1.setText("Previous Result 1: " + laboratoryModel.getPrevResult1());
            txtPrevReuslt2.setText("Previous Result 2: " + laboratoryModel.getPrevResult2());
            txtResult.setText("Result: " + laboratoryModel.getResult());

            txtPrevReuslt1date.setText("Previous Result 1 Date: " + prev1Date.toString());
            txtPrevReuslt2date.setText("Previous Result 2 Date: "  + prev2Date.toString());
            txtResultdate.setText("Result Date: " +   resultDate.toString());
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            graph.getGridLabelRenderer().setNumHorizontalLabels(3);
        }
        return series;
    }

    private void setGraph(Date resultDate, Date prev2Date, LineGraphSeries<DataPoint> series) {
        //        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(new DataPoint[]{
//                new DataPoint(date1
//                        , 1),
//                new DataPoint(date2, 5),
//                new DataPoint(date3, 2),
//                new DataPoint(date4, 8)
//        });

        series.setDrawBackground(true);
        series.setAnimated(true);
        series.setDrawDataPoints(true);
        series.setTitle("Medical Data");
//
//
//        series2.setDataWidth(1d);
//        series2.setSpacing(50);
//        series2.setAnimated(true);
//        series2.setDrawValuesOnTop(true);
//        series2.setTitle("Children");
//        series2.setColor(Color.argb(255, 60, 200, 128));

        graph.addSeries(series);
//        graph.addSeries(series2);

        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(graph.getContext()));

//        graph.getGridLabelRenderer().setLabelsSpace(10);


        // set manual x bounds to have nice steps
        graph.getViewport().setMinX(prev2Date.getTime());
        graph.getViewport().setMaxX(resultDate.getTime());
        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setScalable(true);
//        graph.getGridLabelRenderer().setGridColor(getResources().getColor(R.color.c_white));
        graph.getViewport().setScrollable(true);
//        graph.getViewport().setDrawBorder(true);
//        graph.getViewport().setBackgroundColor(getResources().getColor(R.color.c_black));
//        graph.getViewport().setBorderColor(getResources().getColor(R.color.colorPrimaryDark));

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not nessecary
        graph.getGridLabelRenderer().setHumanRounding(false);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                UIHelper.showShortToastInCenter(getApplicationContext(), "Data X: " + DateManager.getDate((long) dataPoint.getX()).toString() + "\n Data Y: " + dataPoint.getY());
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(AppConstants.LABORATORY_MODEL, laboratoryModel);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        if (savedInstanceState != null) {
            laboratoryModel = (LaboratoryModel) savedInstanceState.getSerializable(AppConstants.LABORATORY_MODEL);
            updateView();
        }

    }


    @Override
    public void onBackPressed() {
        finish();
    }

}