package edu.aku.akuh_health_first.models;

/**
 * Created by hamza.ahmed on 3/27/2018.
 */

public class BannerModel {
    String header;
    String subheader;


    public BannerModel(String header, String subheader) {
        this.header = header;
        this.subheader = subheader;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubheader() {
        return subheader;
    }

    public void setSubheader(String subheader) {
        this.subheader = subheader;
    }
}
