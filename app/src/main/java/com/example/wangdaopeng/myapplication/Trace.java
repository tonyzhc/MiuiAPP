package com.example.wangdaopeng.myapplication;

public class Trace {
    /** 时间 */
    private String acceptTime;
    /** 描述 */
    private String acceptStation;

    private int imageID;

    public Trace() {
    }

    public Trace(String acceptTime, String acceptStation,int imageID) {
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
        this.imageID =imageID;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getAcceptStation() {
        return acceptStation;
    }

    public int getImageID(){return imageID;}


    public void setAcceptStation(String acceptStation) {
        this.acceptStation = acceptStation;
    }
}

