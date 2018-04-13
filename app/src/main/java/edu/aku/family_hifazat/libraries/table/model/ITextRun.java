package edu.aku.family_hifazat.libraries.table.model;


import edu.aku.family_hifazat.libraries.table.model.action.Action;

public interface ITextRun {
    int getStartPos();
    int getLength();
    int getFontIndex();
    int getBackgroundColor();
    Action getAction();
}
