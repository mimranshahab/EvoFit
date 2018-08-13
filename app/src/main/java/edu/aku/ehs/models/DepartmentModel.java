package edu.aku.ehs.models;

/**
 * Created by hamza.ahmed on 7/23/2018.
 */

public class DepartmentModel {

    private String deptName;
    private boolean isSelected = false;

    public DepartmentModel(String deptName) {
        this.deptName = deptName;
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

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
