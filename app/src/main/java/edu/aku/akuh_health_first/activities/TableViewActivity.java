package edu.aku.akuh_health_first.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.libraries.table.model.BaseCellData;
import edu.aku.akuh_health_first.libraries.table.model.DefaultSheetData;
import edu.aku.akuh_health_first.libraries.table.model.ISheetData;
import edu.aku.akuh_health_first.libraries.table.util.TableViewConfigure;
import edu.aku.akuh_health_first.libraries.table.view.TableView;
import edu.aku.akuh_health_first.managers.SharedPreferenceManager;
import edu.aku.akuh_health_first.models.LstMicSpecAntibiotic;
import edu.aku.akuh_health_first.models.LstMicSpecParaResult;
import edu.aku.akuh_health_first.models.SheetTemplate1;


public class TableViewActivity extends AppCompatActivity {
    TableView mTableView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);

        LstMicSpecParaResult lstMicSpecParaResult = SharedPreferenceManager.getInstance(this).getObject(AppConstants.KEY_CROSS_TAB_DATA, LstMicSpecParaResult.class);


        mTableView = (TableView) findViewById(R.id.table_view);
        mTableView.setLayoutChagneListener(new TableView.LayoutChagneListener() {
            @Override
            public void onLayoutChange(View v, boolean changed, int left, int top, int right, int bottom) {
                if (changed) {
                    int tableViewWidth = mTableView.getWidth();
                    int colWidth = (int) (tableViewWidth / 3.3);
//                    colWidth = colWidth * 2;
                    DefaultSheetData sheetData = (DefaultSheetData) mTableView.getSheet();
                    for (int i = 0; i < sheetData.getMaxColumnCount(); i++) {
                        sheetData.setColumnWidth(i, colWidth);
                    }
                    calcRowHeight(sheetData);
                    mTableView.clearCacheData();
                }
            }
        });


        ArrayList<LstMicSpecAntibiotic> anti = new ArrayList<>();
        for (LstMicSpecAntibiotic lstMicSpecAntibiotic : lstMicSpecParaResult.getLstMicSpecAntibiotics()) {
            if (anti.isEmpty()) {
                anti.add(lstMicSpecAntibiotic);
            } else {
                boolean toAddItem = true;
                for (LstMicSpecAntibiotic micSpecAntibiotic : anti) {
                    if (micSpecAntibiotic.getABBREVIATION().equals(lstMicSpecAntibiotic.getABBREVIATION())) {
                        toAddItem = false;
                    }
                }
                if (toAddItem) {
                    anti.add(lstMicSpecAntibiotic);
                }
            }
        }

        String[][] table = new String[5][10];


        ISheetData sheet = SheetTemplate1.get(this, table);
        mTableView.setSheetData(sheet);
        TableViewConfigure configure = new TableViewConfigure();
        configure.setShowHeaders(true);
        configure.setEnableResizeRow(false);
        configure.setEnableResizeColumn(false);
        configure.setEnableSelection(false);
        mTableView.setConfigure(configure);

        mTableView.setData(lstMicSpecParaResult.getLstMicSpecOrganism(), anti);
    }

    private void calcRowHeight(DefaultSheetData sheet) {
        int rowCount = sheet.getMaxRowCount();
        int colCount = sheet.getMaxColumnCount();
        for (int i = 0; i < rowCount; i++) {
            int rowHeight = 0;
            for (int j = 0; j < colCount; j++) {
                BaseCellData cell = (BaseCellData) sheet.getCellData(i, j);
                int cellHeight = cell.calcTextHeightByWidth(sheet.getColumnWidth(j));
                rowHeight = Math.max(rowHeight, cellHeight);
            }
            sheet.setRowHeight(i, rowHeight);
        }

    }

}

