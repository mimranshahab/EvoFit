package edu.aku.family_hifazat.libraries.table.model;


public interface IRichText {
    CharSequence getText();
    int getRunCount();
    ITextRun getRun(int index);
}
