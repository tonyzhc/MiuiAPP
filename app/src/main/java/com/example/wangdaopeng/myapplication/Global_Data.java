package com.example.wangdaopeng.myapplication;


import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Global_Data  {



    private  static  Global_Data global_data = new Global_Data();

    private Global_Data(){
    }

    public  static Global_Data getInstance(){
        return global_data;
    }

    private  String netAddress ="http://10.231.27.241:5002/";
    private  String Login_url  =netAddress + "login/";
    private  String Label_url  =netAddress + "label/";
    private  String Trace_url  =netAddress + "trace/";
    private  String Logout_url =netAddress + "logout/";

    HashMap<String,String> pic_name = new HashMap<String,String>(){
        {
            put("aqysp","爱奇艺视频");
            put("baidu","百度");
            put("bddt","百度地图");
            put("cs","cs枪战");
            put("dd","滴滴出行");
            put("ddh","打电话");
            put("dushu","读书");
            put("dx","短信");
            put("game","游戏");
            put("jinrong","金融");
            put("jrtt","今日头条");
            put("mg","饮食消费");
            put("nba","NBA");
            put("nz","闹钟");
            put("pb","小米运动");
            put("qq","腾讯QQ");
            put("std","手电筒");
            put("sll","360卫士");
            put("tongxun","通讯");
            put("wechat","微信");
            put("weibo","新浪微博");
            put("wx","微信");
            put("wyyy","网易音乐");
            put("xlyy","迅雷影视");
            put("xmsc","小米商城");
            put("xmyy","小米音乐");
            put("yingshi","影视视听");
            put("yysd","应用商店");
            put("yytj","爱奇艺视频");
            put("zhifubao","支付宝");
        }
    };




    public HashMap get_picname(){return  pic_name;}

    public String getWelcome_url() {
        return netAddress;
    }

    public String getLogin_url() {
        return Login_url;
    }

    public String getLabel_url() {
        return Label_url;
    }

    public String getTrace_url() {
        return Trace_url;
    }

    public String getLogout_url() {
        return Logout_url;
    }

}

