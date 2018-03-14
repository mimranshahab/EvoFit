package edu.aku.akuh_health_first.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hamza.ahmed on 3/13/2018.
 */

public class DetailHealthSummaryModel {
    @Expose
    @SerializedName("SummaryTitle")
    private String summarytitle;

    @Expose
    @SerializedName("ShortMessage")
    private String shortmessage;

    @Expose
    @SerializedName("DetailedMessage")
    private String detailedmessage;

    @Expose
    @SerializedName("LinkToHistory")
    private String linktohistory;

    @Expose
    @SerializedName("ShortMessageMobile")
    private Shortmessagemobile shortmessagemobile;
  @Expose
    @SerializedName("DetailedMessageMobile")
    private ArrayList<Shortmessagemobile> detailMessageMobileArray;

    @Expose
    @SerializedName("WebandMobileModel")
    private Webandmobilemodel webandmobilemodel;


    public ArrayList<Shortmessagemobile> getDetailMessageMobileArray() {
        return detailMessageMobileArray;
    }

    public void setDetailMessageMobileArray(ArrayList<Shortmessagemobile> detailMessageMobileArray) {
        this.detailMessageMobileArray = detailMessageMobileArray;
    }

    public String getSummarytitle() {
        return summarytitle;
    }

    public void setSummarytitle(String summarytitle) {
        this.summarytitle = summarytitle;
    }

    public String getShortmessage() {
        return shortmessage;
    }

    public void setShortmessage(String shortmessage) {
        this.shortmessage = shortmessage;
    }

    public String getDetailedmessage() {
        return detailedmessage;
    }

    public void setDetailedmessage(String detailedmessage) {
        this.detailedmessage = detailedmessage;
    }

    public String getLinktohistory() {
        return linktohistory;
    }

    public void setLinktohistory(String linktohistory) {
        this.linktohistory = linktohistory;
    }

    public Shortmessagemobile getShortmessagemobile() {
        return shortmessagemobile;
    }

    public void setShortmessagemobile(Shortmessagemobile shortmessagemobile) {
        this.shortmessagemobile = shortmessagemobile;
    }

    public Webandmobilemodel getWebandmobilemodel() {
        return webandmobilemodel;
    }

    public void setWebandmobilemodel(Webandmobilemodel webandmobilemodel) {
        this.webandmobilemodel = webandmobilemodel;
    }
}
