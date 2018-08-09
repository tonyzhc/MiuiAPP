package com.example.wangdaopeng.myapplication;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AppUseStatics {

    UsageStatsManager manager;
    public AppUseStatics() {
    }
    public AppUseStatics(UsageStatsManager manager){
        this.manager = manager;
    }

    TreeMap<String, Long> AppStatics = new TreeMap<String, Long>(new Comparator<String>() {

        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }

    });


    //按日获取app数据
    public TreeMap get_StaticsByDay() {
//        PackageManager pm = getApplicationContext().getPackageManager();
        Calendar beginCal = Calendar.getInstance();
        Log.v("Byday",beginCal.toString());

        beginCal.set(Calendar.HOUR_OF_DAY,0);
        beginCal.set(Calendar.SECOND,0);
        beginCal.set(Calendar.MINUTE,0);
        beginCal.set(Calendar.MILLISECOND,0);

//        System.out.print();
        Calendar endCal = Calendar.getInstance();
        List<UsageStats> stats = manager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginCal.getTimeInMillis(), endCal.getTimeInMillis());

//        Log.v("time222" ,beginCal.toString());
        for (UsageStats us : stats) {
            String AppName = us.getPackageName();
            long UseTime = (us.getTotalTimeInForeground()+55000)/60000;

            Log.v("firsttimestamp",String.valueOf(us.getFirstTimeStamp()));
            Log.v("lasttimestamp",String.valueOf(us.getLastTimeStamp()));
            Log.v("last use time",String.valueOf(us.getLastTimeUsed()));


            if (UseTime <=0) continue;
            else AppStatics.put(AppName,UseTime);
        }
        return AppStatics;
    }


    public TreeMap get_StaticsByWeek() {
        Calendar beginCal = Calendar.getInstance();

        Log.v("weektime1111",beginCal.toString());
        beginCal.add(Calendar.WEEK_OF_MONTH, -1);

        Log.v("weektime2222",beginCal.toString());
        Calendar endCal = Calendar.getInstance();

        List<UsageStats> stats = manager.queryUsageStats(UsageStatsManager.INTERVAL_WEEKLY, beginCal.getTimeInMillis(), endCal.getTimeInMillis());

        for (UsageStats us : stats) {
            String AppName = us.getPackageName();
            long UseTime = (us.getTotalTimeInForeground()+55000)/60000;
            if (UseTime <=0) continue;
            else AppStatics.put(AppName,UseTime);
        }
        return AppStatics;
    }

    public TreeMap get_StaticsByMonth() {
        Calendar beginCal = Calendar.getInstance();
        beginCal.add(Calendar.MONTH, -1);
        Calendar endCal = Calendar.getInstance();

        List<UsageStats> stats = manager.queryUsageStats(UsageStatsManager.INTERVAL_MONTHLY, beginCal.getTimeInMillis(), endCal.getTimeInMillis());

        for (UsageStats us : stats) {
            String AppName = us.getPackageName();
            long UseTime = (us.getTotalTimeInForeground()+55000)/60000;
            if (UseTime <=0) continue;
            else AppStatics.put(AppName,UseTime);
        }
        return AppStatics;
    }


    public TreeMap get_StaticsByYear() {
        Calendar beginCal = Calendar.getInstance();
        beginCal.add(Calendar.YEAR, -1);
        Calendar endCal = Calendar.getInstance();

        List<UsageStats> stats = manager.queryUsageStats(UsageStatsManager.INTERVAL_YEARLY, beginCal.getTimeInMillis(), endCal.getTimeInMillis());

        for (UsageStats us : stats) {
            String AppName = us.getPackageName();
            long UseTime = (us.getTotalTimeInForeground()+55000)/60000;
            if (UseTime <=0) continue;
            else AppStatics.put(AppName,UseTime);
        }

        return AppStatics;
    }









}
