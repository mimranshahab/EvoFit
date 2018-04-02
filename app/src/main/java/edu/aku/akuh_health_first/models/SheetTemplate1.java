package edu.aku.akuh_health_first.models;


import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.libraries.table.model.DefaultCellData;
import edu.aku.akuh_health_first.libraries.table.model.DefaultSheetData;
import edu.aku.akuh_health_first.libraries.table.model.ISheetData;
import edu.aku.akuh_health_first.libraries.table.model.Range;
import edu.aku.akuh_health_first.libraries.table.model.RichText;
import edu.aku.akuh_health_first.libraries.table.model.style.CellStyle;
import edu.aku.akuh_health_first.libraries.table.model.style.Font;
import edu.aku.akuh_health_first.libraries.table.model.style.TableConst;

public class SheetTemplate1 {

    public static ISheetData get(final Context context, LstMicSpecParaResult lstMicSpecParaResult) {

//    public static ISheetData get(final Context context, int rowCount, int colCount) {
        DefaultSheetData sheet = new DefaultSheetData(context);
//        int rowCount = treesetList.size();


        Set<LstMicSpecAntibiotic> treesetList = new HashSet<>(lstMicSpecParaResult.getLstMicSpecAntibiotics());
        ArrayList<LstMicSpecAntibiotic> anti = new ArrayList<LstMicSpecAntibiotic>(treesetList);


        int rowCount = anti.size();
        int colCount = lstMicSpecParaResult.getLstMicSpecOrganism().size();
        sheet.setMaxRowCount(rowCount);
        sheet.setMaxColumnCount(colCount);



        Font whiteFontColor = Font.createDefault(context);
        whiteFontColor.setColor(context.getResources().getColor(R.color.c_white));
        int whiteFontIndex = sheet.getFontManager().addFont(whiteFontColor);


        Font blackFontColor = Font.createDefault(context);
        blackFontColor.setColor(context.getResources().getColor(R.color.c_black));
        int blackFontIndex = sheet.getFontManager().addFont(blackFontColor);

        CellStyle cellStyle = new CellStyle();
        cellStyle.setBgColor(context.getResources().getColor(R.color.c_white)); // odd cells color
        cellStyle.setAlignment(TableConst.ALIGNMENT_CENTER);
        cellStyle.setVerticalAlignment(TableConst.VERTICAL_ALIGNMENT_CENTRE);
        cellStyle.setFontIndex(blackFontIndex);
        int whiteStyle = sheet.getCellStyleManager().addCellStyle(cellStyle);

        CellStyle cellStyle1 = new CellStyle();
        cellStyle1.setBgColor(context.getResources().getColor(R.color.blue_sensitive)); // odd cells color
        cellStyle1.setAlignment(TableConst.ALIGNMENT_CENTER);
        cellStyle1.setVerticalAlignment(TableConst.VERTICAL_ALIGNMENT_CENTRE);
        cellStyle1.setFontIndex(whiteFontIndex);
        int blueStyle = sheet.getCellStyleManager().addCellStyle(cellStyle1);

        CellStyle cellStyle2 = new CellStyle();
        cellStyle2.setBgColor(context.getResources().getColor(R.color.green_intermediate)); // odd cells color
        cellStyle2.setAlignment(TableConst.ALIGNMENT_CENTER);
        cellStyle2.setVerticalAlignment(TableConst.VERTICAL_ALIGNMENT_CENTRE);
        cellStyle2.setFontIndex(whiteFontIndex);
        int greenStyle = sheet.getCellStyleManager().addCellStyle(cellStyle2);

        CellStyle cellStyle3 = new CellStyle();
        cellStyle3.setBgColor(context.getResources().getColor(R.color.red_resistant)); // odd cells color
        cellStyle3.setAlignment(TableConst.ALIGNMENT_CENTER);
        cellStyle3.setVerticalAlignment(TableConst.VERTICAL_ALIGNMENT_CENTRE);
        cellStyle3.setFontIndex(whiteFontIndex);
        int redStyle = sheet.getCellStyleManager().addCellStyle(cellStyle3);


        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                DefaultCellData cell = new DefaultCellData(sheet);
                RichText richText = new RichText();

                richText.setText(lstMicSpecParaResult.getLstMicSpecAntibiotics().get(j).getREACTION() + i + "-" + j);

                if (lstMicSpecParaResult.getLstMicSpecAntibiotics().get(j).getREACTION().contains("Resistant")) {
                    cell.setStyleIndex(redStyle);

                } else if (lstMicSpecParaResult.getLstMicSpecAntibiotics().get(j).getREACTION().contains("Intermediate")) {
                    cell.setStyleIndex(greenStyle);
                } else if (lstMicSpecParaResult.getLstMicSpecAntibiotics().get(j).getREACTION().contains("Sensitive")) {
                    cell.setStyleIndex(blueStyle);
                } else {
                    cell.setStyleIndex(whiteStyle);
                }
                cell.setCellValue(richText);
                sheet.setCellData(cell, i, j);
            }
        }


        return sheet;
    }

    private static void addMergeRange(DefaultSheetData sheet) {
        Range range = new Range(0, 2, 1, 3);
        sheet.addMergedRange(range);
    }
}
