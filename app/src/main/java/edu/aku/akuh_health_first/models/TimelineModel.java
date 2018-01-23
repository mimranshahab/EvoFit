package edu.aku.akuh_health_first.models;

/**
 * Created by User on 1/19/2018.
 */

public class TimelineModel {
    private String Visit;
    private String Purpose;

//    public TimelineModel(String visit, String purpose, String doctor) {
//        Visit = visit;
//        Purpose = purpose;
//        Doctor = doctor;
//    }

    public String getVisit() {
        return Visit;
    }


    public void setVisit(String visit) {
        Visit = visit;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    private String Doctor;


    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
