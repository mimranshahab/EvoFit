package edu.aku.evofit.models;

/**
 * Created by hamza.ahmed on 7/23/2018.
 */

public class DepartmentModel {

    private String deptName;
    private int color;
    private boolean isSelected = false;

    public DepartmentModel(String deptName, int color) {
        this.deptName = deptName;
        this.color = color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getDeptName() {
        return deptName;
    }

    public int getColor() {return  color;}

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
