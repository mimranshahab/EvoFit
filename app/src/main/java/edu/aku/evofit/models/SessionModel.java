package edu.aku.evofit.models;

/**
 * Created by hamza.ahmed on 7/23/2018.
 */

public class SessionModel {

    private String sessionName;
    private String startDate;
    private String stopDate;

    public SessionModel(String sessionName, String startDate, String stopDate) {
        this.sessionName = sessionName;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }



    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }
}
