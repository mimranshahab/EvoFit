package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hamza.ahmed on 3/7/2018.
 */

public class MedicationProfileModel {

    @Expose
    @SerializedName("RecordMessage")
    private String recordmessage;
    @Expose
    @SerializedName("RecordFound")
    private String recordfound;
    @Expose
    @SerializedName("Active")
    private String active;
    @Expose
    @SerializedName("LastFileTerminal")
    private String lastfileterminal;
    @Expose
    @SerializedName("LastFileUser")
    private String lastfileuser;
    @Expose
    @SerializedName("LastFileDateTime")
    private String lastfiledatetime;
    @Expose
    @SerializedName("MedicineList")
    private String medicinelist;
    @Expose
    @SerializedName("RouteIDList")
    private String routeidlist;
    @Expose
    @SerializedName("isPatientOnBoard")
    private boolean ispatientonboard;
    @Expose
    @SerializedName("RXMedRouteDescription")
    private String rxmedroutedescription;
    @Expose
    @SerializedName("MedicineExceedLimit")
    private String medicineexceedlimit;
    @Expose
    @SerializedName("MRNumber")
    private String mrnumber;
    @Expose
    @SerializedName("VisitType")
    private String visittype;
    @Expose
    @SerializedName("RXMedexists")
    private String rxmedexists;
    @Expose
    @SerializedName("RXMedDoctor")
    private String rxmeddoctor;
    @Expose
    @SerializedName("RXMedStatus")
    private String rxmedstatus;
    @Expose
    @SerializedName("RXMedRoute")
    private String rxmedroute;
    @Expose
    @SerializedName("RXMedVisitID")
    private String rxmedvisitid;
    @Expose
    @SerializedName("RXMedStopDateTime")
    private String rxmedstopdatetime;
    @Expose
    @SerializedName("RXMedStartDateTime")
    private String rxmedstartdatetime;
    @Expose
    @SerializedName("RXMedFrequency")
    private String rxmedfrequency;
    @Expose
    @SerializedName("RXMedDose")
    private String rxmeddose;
    @Expose
    @SerializedName("RXMedMedication")
    private String rxmedmedication;
    @Expose
    @SerializedName("RXMedRXnumber")
    private String rxmedrxnumber;
    @Expose
    @SerializedName("RXMedcount")
    private int rxmedcount;

    public String getRecordmessage() {
        return recordmessage;
    }

    public void setRecordmessage(String recordmessage) {
        this.recordmessage = recordmessage;
    }

    public String getRecordfound() {
        return recordfound;
    }

    public void setRecordfound(String recordfound) {
        this.recordfound = recordfound;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getLastfileterminal() {
        return lastfileterminal;
    }

    public void setLastfileterminal(String lastfileterminal) {
        this.lastfileterminal = lastfileterminal;
    }

    public String getLastfileuser() {
        return lastfileuser;
    }

    public void setLastfileuser(String lastfileuser) {
        this.lastfileuser = lastfileuser;
    }

    public String getLastfiledatetime() {
        return lastfiledatetime;
    }

    public void setLastfiledatetime(String lastfiledatetime) {
        this.lastfiledatetime = lastfiledatetime;
    }

    public String getMedicinelist() {
        return medicinelist;
    }

    public void setMedicinelist(String medicinelist) {
        this.medicinelist = medicinelist;
    }

    public String getRouteidlist() {
        return routeidlist;
    }

    public void setRouteidlist(String routeidlist) {
        this.routeidlist = routeidlist;
    }

    public boolean getIspatientonboard() {
        return ispatientonboard;
    }

    public void setIspatientonboard(boolean ispatientonboard) {
        this.ispatientonboard = ispatientonboard;
    }

    public String getRxmedroutedescription() {
        return rxmedroutedescription;
    }

    public void setRxmedroutedescription(String rxmedroutedescription) {
        this.rxmedroutedescription = rxmedroutedescription;
    }

    public String getMedicineexceedlimit() {
        return medicineexceedlimit;
    }

    public void setMedicineexceedlimit(String medicineexceedlimit) {
        this.medicineexceedlimit = medicineexceedlimit;
    }

    public String getMrnumber() {
        return mrnumber;
    }

    public void setMrnumber(String mrnumber) {
        this.mrnumber = mrnumber;
    }

    public String getVisittype() {
        return visittype;
    }

    public void setVisittype(String visittype) {
        this.visittype = visittype;
    }

    public String getRxmedexists() {
        return rxmedexists;
    }

    public void setRxmedexists(String rxmedexists) {
        this.rxmedexists = rxmedexists;
    }

    public String getRxmeddoctor() {
        return rxmeddoctor;
    }

    public void setRxmeddoctor(String rxmeddoctor) {
        this.rxmeddoctor = rxmeddoctor;
    }

    public String getRxmedstatus() {
        return rxmedstatus;
    }

    public void setRxmedstatus(String rxmedstatus) {
        this.rxmedstatus = rxmedstatus;
    }

    public String getRxmedroute() {
        return rxmedroute;
    }

    public void setRxmedroute(String rxmedroute) {
        this.rxmedroute = rxmedroute;
    }

    public String getRxmedvisitid() {
        return rxmedvisitid;
    }

    public void setRxmedvisitid(String rxmedvisitid) {
        this.rxmedvisitid = rxmedvisitid;
    }

    public String getRxmedstopdatetime() {
        return rxmedstopdatetime;
    }

    public void setRxmedstopdatetime(String rxmedstopdatetime) {
        this.rxmedstopdatetime = rxmedstopdatetime;
    }

    public String getRxmedstartdatetime() {
        return rxmedstartdatetime;
    }

    public void setRxmedstartdatetime(String rxmedstartdatetime) {
        this.rxmedstartdatetime = rxmedstartdatetime;
    }

    public String getRxmedfrequency() {
        return rxmedfrequency;
    }

    public void setRxmedfrequency(String rxmedfrequency) {
        this.rxmedfrequency = rxmedfrequency;
    }

    public String getRxmeddose() {
        return rxmeddose;
    }

    public void setRxmeddose(String rxmeddose) {
        this.rxmeddose = rxmeddose;
    }

    public String getRxmedmedication() {
        return rxmedmedication;
    }

    public void setRxmedmedication(String rxmedmedication) {
        this.rxmedmedication = rxmedmedication;
    }

    public String getRxmedrxnumber() {
        return rxmedrxnumber;
    }

    public void setRxmedrxnumber(String rxmedrxnumber) {
        this.rxmedrxnumber = rxmedrxnumber;
    }

    public int getRxmedcount() {
        return rxmedcount;
    }

    public void setRxmedcount(int rxmedcount) {
        this.rxmedcount = rxmedcount;
    }
}
