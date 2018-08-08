package com.example.wangdaopeng.myapplication;

import android.graphics.drawable.Drawable;

public class Trace {
    /** 时间 */
    private String acceptTime;
    /** 描述 */
    private String acceptStation;

//    private int imageID;
    private Drawable image;
    public Trace() {
    }
/** changed here*/
//    public Trace(String acceptTime, String acceptStation,int imageID) {
//        this.acceptTime = acceptTime;
//        this.acceptStation = acceptStation;
//        this.imageID =imageID;
//    }
    public Trace(String acceptTime, String acceptStation,Drawable image) {
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
        this.image =image;
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
/**changed here*/
//    public int getImageID(){return imageID;}
  public  Drawable getImage(){return image;}


    public void setAcceptStation(String acceptStation) {
        this.acceptStation = acceptStation;
    }
}

