package edu.aku.ehs.models;

/**
 * Created by hamza.ahmed on 7/23/2018.
 */

public class SessionDetailModel {

    private String employeeName;
    private String date;
    private String status;

    public SessionDetailModel(String sessionName, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
