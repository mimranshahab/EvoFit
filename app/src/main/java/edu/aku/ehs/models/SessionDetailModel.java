package edu.aku.ehs.models;

import edu.aku.ehs.enums.EmployeeSessionState;

/**
 * Created by hamza.ahmed on 7/23/2018.
 */

public class SessionDetailModel {

    private String employeeName;
    private String date;
    private EmployeeSessionState status;

    public SessionDetailModel(String sessionName, EmployeeSessionState status) {
        this.employeeName = sessionName;
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EmployeeSessionState getStatus() {
        return status;
    }

    public void setStatus(EmployeeSessionState status) {
        this.status = status;
    }
}
