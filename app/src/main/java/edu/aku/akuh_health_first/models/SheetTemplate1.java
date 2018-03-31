package edu.aku.akuh_health_first.models;


import android.content.Context;

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
        DefaultSheetData sheet = new DefaultSheetData(context);

        Set<LstMicSpecAntibiotic> treesetList = new HashSet<>(lstMicSpecParaResult.getLstMicSpecAntibiotics());
        int rowCount = 10;
        int colCount = 5;

//        int rowCount = treesetList.size();
//        int colCount = lstMicSpecParaResult.getLstMicSpecOrganism().size();

        sheet.setMaxColumnCount(lstMicSpecParaResult.getLstMicSpecOrganism().size());
//        sheet.setFreezedRowCount(1);


        Font whiteFontColor = Font.createDefault(context);
        whiteFontColor.setColor(context.getResources().getColor(R.color.c_white));
        int whiteFontIndex = sheet.getFontManager().addFont(whiteFontColor);


        Font blackFontColor = Font.createDefault(context);
        blackFontColor.setColor(context.getResources().getColor(R.color.c_black));
        int blackFontIndex = sheet.getFontManager().addFont(blackFontColor);


//        CellStyle firstRowStyle = new CellStyle();
//        firstRowStyle.setBgColor(0xffff8c5d);// cell color selected
//        firstRowStyle.setAlignment(TableConst.ALIGNMENT_CENTER);
//        firstRowStyle.setVerticalAlignment(TableConst.VERTICAL_ALIGNMENT_CENTRE);
//
//        Font firstRowFont = Font.createDefault(context);
//        firstRowFont.setColor(0xfffbe8d4); // text color of row 2
//        int frIndex = sheet.getFontManager().addFont(firstRowFont);
//        firstRowStyle.setFontIndex(frIndex);
//        int frStyleIndex = sheet.getCellStyleManager().addCellStyle(firstRowStyle);
//
        CellStyle cellStyle = new CellStyle();
        cellStyle.setBgColor(0xffffffff); // odd cells color
        cellStyle.setAlignment(TableConst.ALIGNMENT_CENTER);
        cellStyle.setVerticalAlignment(TableConst.VERTICAL_ALIGNMENT_CENTRE);
        cellStyle.setFontIndex(blackFontIndex);
        int whiteStyle = sheet.getCellStyleManager().addCellStyle(cellStyle);

        CellStyle cellStyle1 = new CellStyle();
        cellStyle1.setBgColor(0xffff8c5d); // odd cells color
        cellStyle1.setAlignment(TableConst.ALIGNMENT_CENTER);
        cellStyle1.setVerticalAlignment(TableConst.VERTICAL_ALIGNMENT_CENTRE);
        cellStyle1.setFontIndex(whiteFontIndex);

        int amberStyle = sheet.getCellStyleManager().addCellStyle(cellStyle1);

        CellStyle cellStyle2 = new CellStyle();
        cellStyle2.setBgColor(0xff0f8c4d); // odd cells color
        cellStyle2.setAlignment(TableConst.ALIGNMENT_CENTER);
        cellStyle2.setVerticalAlignment(TableConst.VERTICAL_ALIGNMENT_CENTRE);
        cellStyle2.setFontIndex(whiteFontIndex);
        int greenStyle = sheet.getCellStyleManager().addCellStyle(cellStyle2);


        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                DefaultCellData cell = new DefaultCellData(sheet);
                RichText richText = new RichText();
//                if (i == 1 && j == 3 || i == 2 && j == 2 || i == 8 && j == 1
//                        || i == 1 && j == 0
//                        || i == 3 && j == 0
//                        || i == 5 && j == 0
//                        || i == 0 && j == 1 || i == 9 && j == 3) {
                    cell.setStyleIndex(greenStyle);
                    richText.setText("hey-" + i + "-" + j);

//
//                } else if (i == 4 && j == 4 || i == 2 && j == 6 || i == 2 && j == 1
//                        || i == 4 && j == 1 || i == 6 && j == 1
//                        || i == 5 && j == 8 || i == 1 && j == 0 || i == 0 && j == 1) {
//                    cell.setStyleIndex(amberStyle);
//                    richText.setText("hell000000o-" + i + "-" + j);
//                } else {
//                    cell.setStyleIndex(whiteStyle);
//                    richText.setText("-");
//                }
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
