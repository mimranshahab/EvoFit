package edu.aku.evofit.libraries.table.model;


import edu.aku.evofit.libraries.table.model.action.Action;

public interface ITextRun {
    int getStartPos();
    int getLength();
    int getFontIndex();
    int getBackgroundColor();
    Action getAction();
}
