package edu.aku.akuh_health_first.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.TableViewActivity;
import edu.aku.akuh_health_first.constatnts.AppConstants;
import edu.aku.akuh_health_first.fragments.abstracts.BaseFragment;
import edu.aku.akuh_health_first.helperclasses.ui.helper.TitleBar;
import edu.aku.akuh_health_first.helperclasses.ui.helper.UIHelper;
import edu.aku.akuh_health_first.libraries.table.model.BaseCellData;
import edu.aku.akuh_health_first.libraries.table.model.DefaultSheetData;
import edu.aku.akuh_health_first.libraries.table.model.ISheetData;
import edu.aku.akuh_health_first.libraries.table.util.TableViewConfigure;
import edu.aku.akuh_health_first.libraries.table.view.TableView;
import edu.aku.akuh_health_first.managers.SharedPreferenceManager;
import edu.aku.akuh_health_first.models.LstMicSpecAntibiotic;
import edu.aku.akuh_health_first.models.LstMicSpecParaResult;
import edu.aku.akuh_health_first.models.SheetTemplate1;
import edu.aku.akuh_health_first.widget.AnyTextView;

import static edu.aku.akuh_health_first.constatnts.AppConstants.KEY_CROSS_TAB_DATA;

/**
 * Created by hamza.ahmed on 3/29/2018.
 */

public class ClinicalParaResultFragment extends BaseFragment {

    TableView mTableView;

    @BindView(R.id.txtProcedureDesc)
    AnyTextView txtProcedureDesc;
    @BindView(R.id.txtViewResult)
    AnyTextView txtViewResult;
    @BindView(R.id.txtParaResult)
    AnyTextView txtParaResult;
    Unbinder unbinder;
    private LstMicSpecParaResult micSpecParaResult;
    private String procedureName;
    private String procedureDescription;


    // Converting List to Set
    ArrayList<LstMicSpecAntibiotic> antibioticSet = new ArrayList<>();
    // Declare 2D Array (Organism, Antibiotic Set)
    String[][] tableData;



    public static ClinicalParaResultFragment newInstance(LstMicSpecParaResult micSpecParaResult, String procedureName, String procedureDescription) {

        Bundle args = new Bundle();

        ClinicalParaResultFragment fragment = new ClinicalParaResultFragment();
        fragment.micSpecParaResult = micSpecParaResult;
        fragment.procedureName = procedureName;
        fragment.procedureDescription = procedureDescription;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTableView = (TableView) view.findViewById(R.id.table_view);


        txtParaResult.setText(Html.fromHtml(micSpecParaResult.getPARARESULT()), TextView.BufferType.SPANNABLE);

        if (micSpecParaResult != null) {
            procedureDescription = procedureDescription.concat(" (" + micSpecParaResult.getPARATYPE() + ")");
        }
        txtProcedureDesc.setText(procedureDescription);

        if (micSpecParaResult.getLstMicSpecOrganism() == null || micSpecParaResult.getLstMicSpecOrganism().isEmpty()) {
            txtViewResult.setVisibility(View.GONE);
        } else {
            txtViewResult.setVisibility(View.VISIBLE);
        }


         convertToSetAntibiotics();
        antibioticSet.addAll(antibioticSet);
        antibioticSet.addAll(antibioticSet);
        antibioticSet.addAll(antibioticSet);
        tableData = new String[micSpecParaResult.getLstMicSpecOrganism().size()][antibioticSet.size()];
        setTableViewCellWidth();
        setData();
        setTableView();


    }


    private void convertToSetAntibiotics() {
        for (LstMicSpecAntibiotic lstMicSpecAntibiotic : micSpecParaResult.getLstMicSpecAntibiotics()) {
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
        ISheetData sheet = SheetTemplate1.get(getContext(), tableData);
        mTableView.setSheetData(sheet);
        TableViewConfigure configure = new TableViewConfigure();
        configure.setShowHeaders(true);
        configure.setEnableResizeRow(false);
        configure.setEnableResizeColumn(false);
        configure.setEnableSelection(false);
        mTableView.setConfigure(configure);
        mTableView.setVerticalScrollBarEnabled(false);

        mTableView.setData(micSpecParaResult.getLstMicSpecOrganism(), antibioticSet);
    }


    private void setData() {

        // Set constant data "-"
        for (int x = 0; x < tableData.length; x++) {
            for (int y = 0; y < tableData[x].length; y++) {
                tableData[x][y] = "-";
            }
        }


        // Iterate for Organism
        for (int i = 0; i < micSpecParaResult.getLstMicSpecOrganism().size(); i++) {

            // Iterate for Original Antibiotics
            for (int j = 0; j < micSpecParaResult.getLstMicSpecAntibiotics().size(); j++) {
                if (micSpecParaResult.getLstMicSpecAntibiotics().get(j).getORGANISMID().equals(micSpecParaResult.getLstMicSpecOrganism().get(i).getORGANISMID())) {

                    // Checking the index of this antibiotic from Original antibiotic list
                    for (LstMicSpecAntibiotic lstMicSpecAntibiotic : antibioticSet) {
                        if (lstMicSpecAntibiotic.getABBREVIATION().equals(micSpecParaResult.getLstMicSpecAntibiotics().get(j).getABBREVIATION())) {

                            // Adding data to 2D Array (x- index of Organism List, y- index of Antibiotics Set)
                            tableData[i][antibioticSet.indexOf(lstMicSpecAntibiotic)] = micSpecParaResult.getLstMicSpecAntibiotics().get(j).getREACTION();
                            break;
                        }
                    }

                }
            }

        }
    }


    private void setTableViewCellWidth() {
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


    @Override
    public int getDrawerLockMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_clinical_para_result;
    }

    @Override
    public void setTitlebar(TitleBar titleBar) {
        titleBar.resetViews();
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setTitle(procedureName);
        titleBar.showBackButton(getBaseActivity());
        titleBar.showHome(getBaseActivity());
        titleBar.setUserDisplay(sharedPreferenceManager.getCurrentUser(), getContext());
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
//
//    @OnClick(R.id.txtViewResult)
//    public void onViewClicked() {
//        sharedPreferenceManager.putObject(KEY_CROSS_TAB_DATA, micSpecParaResult);
//        getBaseActivity().openActivity(TableViewActivity.class, procedureName);
//    }
}
