package edu.aku.akuh_health_first.libraries.table.model;


import edu.aku.akuh_health_first.libraries.table.model.action.Action;

public interface ITextRun {
    int getStartPos();
    int getLength();
    int getFontIndex();
    int getBackgroundColor();
    Action getAction();
}
