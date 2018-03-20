package edu.aku.akuh_health_first.models;

/**
 * Created by hamza.ahmed on 3/16/2018.
 */

public class SpinnerModel {


    private String text;
    private boolean isSelected = false;

    public SpinnerModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SpinnerModel) {
            return ((SpinnerModel) obj).text.equals(text);
        }
        return super.equals(obj);
    }
}
