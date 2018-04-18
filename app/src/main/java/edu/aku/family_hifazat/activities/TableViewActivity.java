package edu.aku.family_hifazat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.family_hifazat.R;
import edu.aku.family_hifazat.constatnts.AppConstants;
import edu.aku.family_hifazat.widget.TitleBar;
import edu.aku.family_hifazat.libraries.table.model.BaseCellData;
import edu.aku.family_hifazat.libraries.table.model.DefaultSheetData;
import edu.aku.family_hifazat.libraries.table.model.ISheetData;
import edu.aku.family_hifazat.libraries.table.util.TableViewConfigure;
import edu.aku.family_hifazat.libraries.table.view.TableView;
import edu.aku.family_hifazat.managers.SharedPreferenceManager;
import edu.aku.family_hifazat.models.LstMicSpecAntibiotic;
import edu.aku.family_hifazat.models.LstMicSpecParaResult;
import edu.aku.family_hifazat.models.SheetTemplate1;

import static edu.aku.family_hifazat.constatnts.AppConstants.JSON_STRING_KEY;


public class TableViewActivity extends AppCompatActivity {
    TableView mTableView;
    @BindView(R.id.titlebar_tableview)
    TitleBar titlebarTableview;
    // Converting List to Set
    ArrayList<LstMicSpecAntibiotic> antibioticSet = new ArrayList<>();
    // Declare 2D Array (Organism, Antibiotic Set)
    String[][] tableData;

    LstMicSpecParaResult lstMicSpecParaResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra(JSON_STRING_KEY);
        lstMicSpecParaResult = SharedPreferenceManager.getInstance(this).getObject(AppConstants.KEY_CROSS_TAB_DATA, LstMicSpecParaResult.class);
        convertToSetAntibiotics();
        antibioticSet.addAll(antibioticSet);
        antibioticSet.addAll(antibioticSet);
        antibioticSet.addAll(antibioticSet);
        tableData = new String[lstMicSpecParaResult.getLstMicSpecOrganism().size()][antibioticSet.size()];
        setTitleBar(title);
        setTableViewCellWidth();
        setData();
        setTableView();
    }

    private void convertToSetAntibiotics() {
        for (LstMicSpecAntibiotic lstMicSpecAntibiotic : lstMicSpecParaResult.getLstMicSpecAntibiotics()) {
            if (antibioticSet.isEmpty()) {
                antibioticSet.add(lstMicSpecAntibiotic);
            } else {
                boolean toAddItem = true;
                for (LstMicSpecAntibiotic micSpecAntibiotic : antibioticSet) {
                    if (micSpecAntibiotic.getABBREVIATION().equals(lstMicSpecAntibiotic.getABBREVIATION())) {
                        toAddItem = false;
                    }
                }
                if (toAddItem) {
                    antibioticSet.add(lstMicSpecAntibiotic);
                }
            }
        }
    }

    private void setTableView() {
        ISheetData sheet = SheetTemplate1.get(this, tableData);
        mTableView.setSheetData(sheet);
        TableViewConfigure configure = new TableViewConfigure();
        configure.setShowHeaders(true);
        configure.setEnableResizeRow(false);
        configure.setEnableResizeColumn(false);
        configure.setEnableSelection(false);
        mTableView.setConfigure(configure);
        mTableView.setVerticalScrollBarEnabled(false);

        mTableView.setData(lstMicSpecParaResult.getLstMicSpecOrganism(), antibioticSet);
    }

    private void setData() {

        // Set constant data "-"
        for (int x = 0; x < tableData.length; x++) {
            for (int y = 0; y < tableData[x].length; y++) {
                tableData[x][y] = "-";
            }
        }


        // Iterate for Organism
        for (int i = 0; i < lstMicSpecParaResult.getLstMicSpecOrganism().size(); i++) {

            // Iterate for Original Antibiotics
            for (int j = 0; j < lstMicSpecParaResult.getLstMicSpecAntibiotics().size(); j++) {
                if (lstMicSpecParaResult.getLstMicSpecAntibiotics().get(j).getORGANISMID().equals(lstMicSpecParaResult.getLstMicSpecOrganism().get(i).getORGANISMID())) {

                    // Checking the index of this antibiotic from Original antibiotic list
                    for (LstMicSpecAntibiotic lstMicSpecAntibiotic : antibioticSet) {
                        if (lstMicSpecAntibiotic.getABBREVIATION().equals(lstMicSpecParaResult.getLstMicSpecAntibiotics().get(j).getABBREVIATION())) {

                            // Adding data to 2D Array (x- index of Organism List, y- index of Antibiotics Set)
                            tableData[i][antibioticSet.indexOf(lstMicSpecAntibiotic)] = lstMicSpecParaResult.getLstMicSpecAntibiotics().get(j).getREACTION();
                            break;
                        }
                    }

                }
            }

        }
    }

    private void setTableViewCellWidth() {
        mTableView = (TableView) findViewById(R.id.table_view);
        mTableView.setLayoutChagneListener(new TableView.LayoutChagneListener() {
            @Override
            public void onLayoutChange(View v, boolean changed, int left, int top, int right, int bottom) {
                if (changed) {
                    int tableViewWidth = mTableView.getWidth();
                    int colWidth = (int) (tableViewWidth / 3.6);
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
    }

    private void setTitleBar(String title) {
        titlebarTableview.resetViews();
        titlebarTableview.setVisibility(View.VISIBLE);
        titlebarTableview.showBackButton(this);
        titlebarTableview.setTitle(title);
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
            // FIXME: 4/4/2018 increase height manually
            sheet.setRowHeight(i, rowHeight + 30);
        }

    }

}

