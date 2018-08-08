package com.example.wangdaopeng.myapplication;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class Test extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
//        startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        DecimalFormat df = new DecimalFormat("00");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);


//        TextView textView = (TextView) findViewById(R.id.testText);

        Calendar beginCal = Calendar.getInstance();
        beginCal.add(Calendar.HOUR_OF_DAY, -1);
        Calendar endCal = Calendar.getInstance();
        UsageStatsManager manager = (UsageStatsManager) getApplicationContext().getSystemService(USAGE_STATS_SERVICE);
        List<UsageStats> stats = manager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginCal.getTimeInMillis(), endCal.getTimeInMillis());
        String res = "";

        AppUseStatics appUseStatics = new AppUseStatics(manager);

        TreeMap<String, Long> appuse = appUseStatics.get_StaticsByDay();

        Iterator key =  appuse.keySet().iterator();
        PackageManager pm = getApplicationContext().getPackageManager();
        Object obj = key.next();
        String appname = obj.toString();
        setTitle(appname);
        long usetime = appuse.get(obj);




        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
             applicationInfo  = pm.getApplicationInfo(appname,0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Drawable d =  pm.getApplicationIcon(applicationInfo);

        ImageView imageView =(ImageView)findViewById(R.id.appicontest);
        imageView.setImageDrawable(d);

//
//        while (key.hasNext()){
//            Object Appname_obj = key.next();
//            Long UseTime = appuse.get(Appname_obj);
//            String AppName  =  Appname_obj.toString();
//            res += AppName+":" + String.valueOf(UseTime) + "\n";
//        }
//


//        PackageManager pm = getApplicationContext().getPackageManager();
//        for (UsageStats us : stats) {
////            System.out.println("++++++++++++++++++++");
////            System.out.print(us.getPackageName());
////            System.out.print(us.getTotalTimeInForeground());
////            System.out.println("++++++++++++++++++++");
//
//            res += us.getPackageName() + ":" + String.valueOf((us.getTotalTimeInForeground() + 55000) / 60000);
//            try {
//                ApplicationInfo appinfo = pm.getApplicationInfo(us.getPackageName(), PackageManager.GET_META_DATA);
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//
//            }

//            textView.setText(res);


        }


    }
