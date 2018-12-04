package edu.aku.evofit.libraries.table.model;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;

import edu.aku.evofit.libraries.table.model.action.Action;
import edu.aku.evofit.libraries.table.model.object.CellObject;
import edu.aku.evofit.libraries.table.model.style.CellStyle;
import edu.aku.evofit.libraries.table.util.UnitsConverter;

public interface ICellData extends ISelection {
    void setCellValue(IRichText value);

    CharSequence getTextValue();
    IRichText getRichTextValue();

    boolean isMerged();

    Range getMergedRange();

    void setMergedRange(Range range);

    void draw(Canvas canvas, Paint paint, Rect rect, int drawType, UnitsConverter uc);

//    public boolean getWrapText();

    void update();

    void setStyleIndex(int index);

    int getStyleIndex();

    CellStyle getCellStyle();

    Layout getLayout();

    void clearLayout();

    int getPositionXInCell(int charOffset);

    ISheetData getSheet();

    void addObject(CellObject d);

    void removeObject(CellObject d);
    int getObjectCount();
    CellObject getObject(int index);
    void setAction(Action action);
    Action getAction();
}
