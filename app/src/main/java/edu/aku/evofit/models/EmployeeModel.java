package edu.aku.evofit.models;

/**
 * Created by hamza.ahmed on 7/23/2018.
 */

public class EmployeeModel {

    private String employeeName;
    private boolean isSelected = false;

    public EmployeeModel(String employeeName) {
        this.employeeName = employeeName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

}
