package com.example.wangdaopeng.myapplication;


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


    public String getNetAddress() {
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

