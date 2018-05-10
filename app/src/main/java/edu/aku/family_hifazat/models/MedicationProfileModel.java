package edu.aku.family_hifazat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    private List<String> medicinelist;
    @Expose
    @SerializedName("FrequencyIDList")
    private List<String> frequencyidlist;
    @Expose
    @SerializedName("RouteIDList")
    private List<String> routeidlist;
    @Expose
    @SerializedName("RXMedTimingList")
    private List<String> rxmedtiminglist;
    @Expose
    @SerializedName("RXMedUrduDirection")
    private String rxmedurdudirection;
    @Expose
    @SerializedName("RXMedTimings")
    private String rxmedtimings;
    @Expose
    @SerializedName("RXMedFrequencyDescription")
    private String rxmedfrequencydescription;
    @Expose
    @SerializedName("isPatientOnBoard")
    private boolean ispatientonboard;
    @Expose
    @SerializedName("RXMedRouteDescription")
    private String rxmedroutedescription;
    @Expose
    @SerializedName("MedicineExceedLimit")
    private boolean medicineexceedlimit;
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
    @SerializedName("RXMedFrequencyID")
    private String rxmedfrequencyid;
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
    @SerializedName("LifetimeMedicine")
    private boolean lifeTimeMedicin;
    @Expose
    @SerializedName("RXMedcount")
    private int rxmedcount;
    @Expose
    @SerializedName("NextDoseDttm")
    private String nextDoseDttm;
    @Expose
    @SerializedName("IdentifyMedicationLocation")
    private String identifyMedicationLocation;

    public String getIdentifyMedicationLocation() {
        return identifyMedicationLocation;
    }

    public void setIdentifyMedicationLocation(String identifyMedicationLocation) {
        this.identifyMedicationLocation = identifyMedicationLocation;
    }

    public boolean isLifeTimeMedicin() {
        return lifeTimeMedicin;
    }

    public String getNextDoseDttm() {
        return nextDoseDttm;
    }

    public void setNextDoseDttm(String nextDoseDttm) {
        this.nextDoseDttm = nextDoseDttm;
    }

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

    public List<String> getMedicinelist() {
        return medicinelist;
    }

    public void setMedicinelist(List<String> medicinelist) {
        this.medicinelist = medicinelist;
    }

    public List<String> getFrequencyidlist() {
        return frequencyidlist;
    }

    public void setFrequencyidlist(List<String> frequencyidlist) {
        this.frequencyidlist = frequencyidlist;
    }

    public List<String> getRouteidlist() {
        return routeidlist;
    }

    public void setRouteidlist(List<String> routeidlist) {
        this.routeidlist = routeidlist;
    }

    public List<String> getRxmedtiminglist() {
        return rxmedtiminglist;
    }

    public void setRxmedtiminglist(List<String> rxmedtiminglist) {
        this.rxmedtiminglist = rxmedtiminglist;
    }

    public String getRxmedurdudirection() {
        return rxmedurdudirection;
    }

    public void setRxmedurdudirection(String rxmedurdudirection) {
        this.rxmedurdudirection = rxmedurdudirection;
    }

    public String getRxmedtimings() {
        return rxmedtimings;
    }

    public void setRxmedtimings(String rxmedtimings) {
        this.rxmedtimings = rxmedtimings;
    }

    public String getRxmedfrequencydescription() {
        return rxmedfrequencydescription;
    }

    public void setRxmedfrequencydescription(String rxmedfrequencydescription) {
        this.rxmedfrequencydescription = rxmedfrequencydescription;
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

    public boolean getMedicineexceedlimit() {
        return medicineexceedlimit;
    }

    public void setMedicineexceedlimit(boolean medicineexceedlimit) {
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

    public String getRxmedfrequencyid() {
        return rxmedfrequencyid;
    }

    public void setRxmedfrequencyid(String rxmedfrequencyid) {
        this.rxmedfrequencyid = rxmedfrequencyid;
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

    public boolean isIspatientonboard() {
        return ispatientonboard;
    }

    public boolean isMedicineexceedlimit() {
        return medicineexceedlimit;
    }

    public boolean getLifeTimeMedicin() {
        return lifeTimeMedicin;
    }

    public void setLifeTimeMedicin(boolean lifeTimeMedicin) {
        this.lifeTimeMedicin = lifeTimeMedicin;
    }
}
