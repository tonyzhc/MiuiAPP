package com.example.wangdaopeng.myapplication;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CurrentUser {
    private  static  CurrentUser currentUser =  new CurrentUser();

    private CurrentUser(){

    }
    public  static CurrentUser getInstance(){
        return currentUser;
    }
    private TreeMap<Long,String> traceTodayActivity  = new TreeMap<>(Collections.reverseOrder());

    String usename="user1";
    String date="date1";
    Global_Data global_data = Global_Data.getInstance();
    int current_date = 0;

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public void setCurrent_date(int current_date){
        this.current_date =current_date;
    }

    public int getCurrent_date(){
        return current_date;
    }

    public   String login(String username,String password) throws IOException {
        String url = global_data.getLogin_url();

        System.out.print("urlis ====="+url);

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params=new FormBody.Builder();
        params.add("username",username);
        params.add("password",password);
        Request request = new Request.Builder()
                .url(url)
                .post(params.build())
                .build();
        System.out.print("________________++++++++++++++");
        String login = client.newCall(request).execute().body().string();
        System.out.println(login);
        if(login.equals("1")){

            //app client should set usename == username
            return "success";
        }
        else
            return "failed";
    }

    public String get_dairy(String date) throws IOException {
        String url = global_data.getActstat_url();
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();

        params.add("username", currentUser.getUsename());
        params.add("date", date);

        Request request = new Request.Builder()
                .url(url)
                .post(params.build())
                .build();
        String actstat = client.newCall(request).execute().body().string();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(actstat);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String dairy = "";
        String weather = null;
        String[] visit = null;
        int sleeptime = 0;
        int readtime = 0;
        int watchtime = 0;
        int gametime = 0;
        int sportime = 0;
        try {
            weather = jsonObject.getString("weather");
            visit = jsonObject.getString("visit").split(",");
            sleeptime = jsonObject.getInt("sleep_time");
            readtime = jsonObject.getInt("read_time");
            gametime = jsonObject.getInt("game_time");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        dairy += "Today the weather is "+weather +"you had sleep "+sleeptime+"last time";
        if(sleeptime <100) dairy += "you didn't get enough sleep last night";
        else  dairy+="\n";
        dairy+="You totally spend"+readtime + "on reading,"
                + gametime +"on games"+"and "+watchtime+" on videos";
        dairy +="You had gone "+visit.length+" places today:";
        for(String place: visit){
            dairy+="place ";
        }
        dairy+="\n";

        if(gametime>100) dairy+="Today you spend too much time on games,that isn't good for you\n";
        if(sportime<100) dairy+="You are lack of exercise today, exercise is good for health\n";

        if(visit.length<3 || gametime+watchtime>500) dairy+="This is a busy day\n";
        else  dairy+="Today is relaxing\n";
        return dairy;

    }


    public String logout() throws  IOException{
        String url = global_data.getLogout_url();

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();

        Request request = new Request.Builder()
                .url(url)
                .build();

        String  login =  client.newCall(request).execute().body().string();

        if (login.equals("1")){
            return  "success";
        }
        else {
            return "failed";
        }
    }

    public  String  get_label() throws IOException {
        String url = global_data.getLabel_url();
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();

        params.add("username",currentUser.getUsename());

        Request request = new Request.Builder()
                .url(url)
                .post(params.build())
                .build();
        String label = client.newCall(request).execute().body().string();
        return label;
    }

    public String get_trace(String date) throws IOException {
        String url = global_data.getTrace_url();

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();
        params.add("username",currentUser.getUsename());
        params.add("date",date);

        Request request = new Request.Builder()
                .url(url)
                .post(params.build())
                .build();

        String trace =client.newCall(request).execute().body().string();

        return  trace;
    }

    public String get_welcome() throws IOException {
        String url =  global_data.getWelcome_url();

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params  = new FormBody.Builder();

        Request request = new Request.Builder()
                .url(url)
                .build();

        String welcome  = client.newCall(request).execute().body().string();

        return welcome;
    }

    public  String  submit_label(String label_string) throws IOException {
        String url = global_data.getSubmitlabel_url();
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder params = new FormBody.Builder();

        params.add("username",currentUser.getUsename());
        params.add("label",label_string);

        Request request = new Request.Builder()
                .url(url)
                .post(params.build())
                .build();
        String status = client.newCall(request).execute().body().string();
        return status;
    }

    public  void initTodayTrace(UsageStatsManager usageStatsManager){
        Calendar beginCal  = Calendar.getInstance();
        beginCal.set(Calendar.HOUR_OF_DAY,0);
        beginCal.set(Calendar.SECOND,0);
        beginCal.set(Calendar.MINUTE,0);
        beginCal.set(Calendar.MILLISECOND,0);
        Calendar endCal  =Calendar.getInstance();
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginCal.getTimeInMillis(), endCal.getTimeInMillis());
        for(UsageStats us:stats){
            String appname = us.getPackageName();
            long lastuseTime =us.getLastTimeUsed();
            traceTodayActivity.put(lastuseTime,appname);
        }
    }

    class UpdateActivity extends  Thread{
        UsageStatsManager thread_usageStatsManager;
        public  UpdateActivity(UsageStatsManager usageStatsManager){
            this.thread_usageStatsManager =usageStatsManager;
        }
        public  void  run(){
            while (true){
                Calendar beginCal  = Calendar.getInstance();
                beginCal.set(Calendar.HOUR_OF_DAY,-1);
                Calendar endCal  =Calendar.getInstance();
                String lastAppName  = "apptest";
                long   lastAppTime = 0;

                List<UsageStats> stats = thread_usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginCal.getTimeInMillis(), endCal.getTimeInMillis());
                for(UsageStats us:stats){
                    String appname = us.getPackageName();
                    long lastuseTime =us.getLastTimeUsed();
                    if(lastuseTime>lastAppTime){
                        lastAppTime = lastuseTime;
                        lastAppName = appname;
                    }
                }

                Object iterator = traceTodayActivity.keySet().iterator().next();
                String Pop_Act_Name =  traceTodayActivity.get(iterator).toString();
                Log.v("NBA",lastAppName);
                if (!Pop_Act_Name.equals(lastAppName)){

                    traceTodayActivity.put(lastAppTime,lastAppName);
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  void updateAct(UsageStatsManager usageStatsManager){
        UpdateActivity updateActivity =  new UpdateActivity(usageStatsManager);
        updateActivity.start();
    }

    public TreeMap<Long,String> getTodayTraceActivity(){
        return traceTodayActivity;
    }

}