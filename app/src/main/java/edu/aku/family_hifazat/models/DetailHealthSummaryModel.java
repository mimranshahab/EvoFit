package edu.aku.family_hifazat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hamza.ahmed on 3/13/2018.
 */

public class DetailHealthSummaryModel {
//    @Expose
//    @SerializedName("SummaryTitle")
//    private String summarytitle;
//
//    @Expose
//    @SerializedName("ShortMessage")
//    private String shortmessage;
//
//    @Expose
//    @SerializedName("DetailedMessage")
//    private String detailedmessage;
//
//    @Expose
//    @SerializedName("LinkToHistory")
//    private String linktohistory;
//
//    @Expose
//    @SerializedName("ShortMessageMobile")
//    private ShortMessageMobile shortmessagemobile;
//  @Expose
//    @SerializedName("DetailedMessageMobile")
//    private ArrayList<ShortMessageMobile> detailMessageMobileArray;
//
//    @Expose
//    @SerializedName("WebandMobileModel")
//    private WebandMobileModel webandmobilemodel;
//
//
//    public ArrayList<ShortMessageMobile> getDetailMessageMobileArray() {
//        return detailMessageMobileArray;
//    }
//
//    public void setDetailMessageMobileArray(ArrayList<ShortMessageMobile> detailMessageMobileArray) {
//        this.detailMessageMobileArray = detailMessageMobileArray;
//    }
//
//    public String getSummarytitle() {
//        return summarytitle;
//    }
//
//    public void setSummarytitle(String summarytitle) {
//        this.summarytitle = summarytitle;
//    }
//
//    public String getShortmessage() {
//        return shortmessage;
//    }
//
//    public void setShortmessage(String shortmessage) {
//        this.shortmessage = shortmessage;
//    }
//
//    public String getDetailedmessage() {
//        return detailedmessage;
//    }
//
//    public void setDetailedmessage(String detailedmessage) {
//        this.detailedmessage = detailedmessage;
//    }
//
//    public String getLinktohistory() {
//        return linktohistory;
//    }
//
//    public void setLinktohistory(String linktohistory) {
//        this.linktohistory = linktohistory;
//    }
//
//    public ShortMessageMobile getShortmessagemobile() {
//        return shortmessagemobile;
//    }
//
//    public void setShortmessagemobile(ShortMessageMobile shortmessagemobile) {
//        this.shortmessagemobile = shortmessagemobile;
//    }
//
//    public WebandMobileModel getWebandmobilemodel() {
//        return webandmobilemodel;
//    }
//
//    public void setWebandmobilemodel(WebandMobileModel webandmobilemodel) {
//        this.webandmobilemodel = webandmobilemodel;
//    }





        @SerializedName("SummaryTitle")
        @Expose
        private String summaryTitle;
        @SerializedName("ShortMessage")
        @Expose
        private String shortMessage;
        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("DetailedMessage")
        @Expose
        private String detailedMessage;
        @SerializedName("LinkToHistory")
        @Expose
        private String linkToHistory;
        @SerializedName("ShortMessageMobile")
        @Expose
        private ShortMessageMobile shortMessageMobile;
        @SerializedName("DetailedMessageMobile")
        @Expose
        private ArrayList<ShortMessageMobile> detailedMessageMobile = null;
        @SerializedName("WebandMobileModel")
        @Expose
        private WebandMobileModel webandMobileModel;
        @SerializedName("RecordFound")
        @Expose
        private String recordFound;
        @SerializedName("RecordMessage")
        @Expose
        private String recordMessage;
        private final static long serialVersionUID = -4555247005046073764L;

    public String getSummaryTitle() {
        return summaryTitle;
    }

    public void setSummaryTitle(String summaryTitle) {
        this.summaryTitle = summaryTitle;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }

    public String getLinkToHistory() {
        return linkToHistory;
    }

    public void setLinkToHistory(String linkToHistory) {
        this.linkToHistory = linkToHistory;
    }

    public ShortMessageMobile getShortMessageMobile() {
        return shortMessageMobile;
    }

    public void setShortMessageMobile(ShortMessageMobile shortMessageMobile) {
        this.shortMessageMobile = shortMessageMobile;
    }

    public ArrayList<ShortMessageMobile> getDetailedMessageMobile() {
        return detailedMessageMobile;
    }

    public void setDetailedMessageMobile(ArrayList<ShortMessageMobile> detailedMessageMobile) {
        this.detailedMessageMobile = detailedMessageMobile;
    }

    public WebandMobileModel getWebandMobileModel() {
        return webandMobileModel;
    }

    public void setWebandMobileModel(WebandMobileModel webandMobileModel) {
        this.webandMobileModel = webandMobileModel;
    }

    public String getRecordFound() {
        return recordFound;
    }

    public void setRecordFound(String recordFound) {
        this.recordFound = recordFound;
    }

    public String getRecordMessage() {
        return recordMessage;
    }

    public void setRecordMessage(String recordMessage) {
        this.recordMessage = recordMessage;
    }


}
