package edu.aku.ehs.libraries.table.model;


import edu.aku.ehs.libraries.table.model.action.Action;

public interface ITextRun {
    int getStartPos();
    int getLength();
    int getFontIndex();
    int getBackgroundColor();
    Action getAction();
}
