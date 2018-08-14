package com.example.wangdaopeng.myapplication;

import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class App_count_application extends AppCompatActivity {

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent();
            myIntent = new Intent(App_count_application.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private ListView lv1;

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.app_count);

            setTitle("App统计-应用显示");

//            Button bt = (Button)findViewById(R.id.show_category) ;
////            bt.setText("显示类别");

            ImageView imageView =(ImageView)findViewById(R.id.zhuzhuangtu);
            imageView.setImageResource(R.mipmap.yytj);


        UsageStatsManager manager = (UsageStatsManager) getApplicationContext().getSystemService(USAGE_STATS_SERVICE);
        AppUseStatics appUseStatics = new AppUseStatics(manager);


        /** 接受参数flag已决定当前显示的 当天、当周、当月、或者当年的数据**/

        int flag =getIntent().getIntExtra("flag",1);


        Button whatShowing = (Button)findViewById(R.id.what_showing);
        if (flag ==1) whatShowing.setText("今天");
        if (flag ==2) whatShowing.setText("最近一周");
        if (flag ==3) whatShowing.setText("最近一月");
        if (flag ==4) whatShowing.setText("最近一年");

//        switch (flag){
//            case 1: whatShowing.setText("今天");
//            case 2: whatShowing.setText("最近一周");
//            case 3: whatShowing.setText("最近一月");
//            case 4: whatShowing.setText("最近一年");
//            default: whatShowing.setText("error");
//        }


        /*跳转到今天*/
        Button today = (Button)findViewById(R.id.appcount_today);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(App_count_application.this,App_count_application.class);
                i.putExtra("flag",1);
                startActivity(i);
            }
        });


        /*跳转到最近一周*/
        Button lastweek = (Button)findViewById(R.id.appcount_lastweek);
        lastweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(App_count_application.this,App_count_application.class);
                i.putExtra("flag",2);
                startActivity(i);
            }
        });

        /*跳转到最近一月*/
        Button lastmonth = (Button)findViewById(R.id.appcount_lastmonth);
        lastmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(App_count_application.this,App_count_application.class);
                i.putExtra("flag",3);
                startActivity(i);
            }
        });


        /*跳转到最近一年*/
        Button lastyear = (Button)findViewById(R.id.appcount_lastyear);
        lastyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(App_count_application.this,App_count_application.class);
                i.putExtra("flag",4);
                startActivity(i);
            }
        });



        /**
         put("getByDay",1);
         put("getByWeek",2);
         put("getByMonth",3);
         put("getByyear",4);
         **/

        TreeMap<String,Long> appuse=appUseStatics.get_StaticsByDay() ;
        if(flag==1) appuse = appUseStatics.get_StaticsByDay();
        if(flag==2) appuse = appUseStatics.get_StaticsByWeek();
        if(flag==3) appuse = appUseStatics.get_StaticsByMonth();
        if(flag==4) appuse = appUseStatics.get_StaticsByYear();




        //获取app的名称 使用时长和图标
        PackageManager packageManager = getApplicationContext().getPackageManager();
        ApplicationInfo applicationInfo = null;
        ArrayList<String> name_list = new ArrayList<String>();
        ArrayList<String> time_list = new ArrayList<String>();
        ArrayList<Drawable> ic_list   = new ArrayList<Drawable>();


        /**TreeMap排序函数**/

        List<Map.Entry<String, Long>> entryArrayList = new ArrayList<>(appuse.entrySet());

        Collections.sort(entryArrayList, (Comparator<? super Map.Entry<String, Long>>) new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return (o1.getValue().compareTo(o2.getValue()))*-1;
            }
        });


        for(Map.Entry<String,Long>entry:entryArrayList){
            String Appname = entry.getKey();
            Long  UseTime = entry.getValue();
            name_list.add(Appname);
            time_list.add(String.valueOf(UseTime)+"min");
            try {
                applicationInfo  = packageManager.getApplicationInfo(Appname,0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Drawable d = packageManager.getApplicationIcon(applicationInfo);
            ic_list.add(d);
        }

        final String[] name  =  new String[name_list.size()];
        name_list.toArray(name);
        final  String[] time  = new String[time_list.size()];
        time_list.toArray(time);
        final  Drawable[] ic  = new Drawable[ic_list.size()];
        ic_list.toArray(ic);

//
//            Button show_category =(Button)findViewById(R.id.show_category);
//            show_category.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i =  new Intent(App_count_application.this,App_count_category.class);
//                    startActivity(i);
//                }
//            });
//


//        Button today =(Button)findViewById(R.id.app_today);
//        today.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =  new Intent(App_count_application.this,App_count_application.class);
//                startActivity(i);
//            }
//        });

//        Button yesterday =(Button)findViewById(R.id.app_yesterday);
//        yesterday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =  new Intent(App_count_application.this,App_count_application.class);
//                startActivity(i);
//            }
//        });

//        Button week =(Button)findViewById(R.id.app_last_week);
//        week.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =  new Intent(App_count_application.this,App_count_application.class);
//                startActivity(i);
//            }
//        });


//        Button month =(Button)findViewById(R.id.app_last_month);
//        month.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =  new Intent(App_count_application.this,App_count_application.class);
//                startActivity(i);
//            }
//        });
        lv1 = (ListView) findViewById(R.id.app_list);

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO 自动生成的方法存根
                View layout=View.inflate(App_count_application.this, R.layout.list, null);
                ImageView app_icon = (ImageView)layout.findViewById(R.id.app_icon);
                ImageView process =(ImageView) layout.findViewById(R.id.process);
                TextView app_name = (TextView)layout.findViewById(R.id.app_name);
                TextView app_time =(TextView)layout.findViewById(R.id.app_time) ;


                app_icon.setImageDrawable(ic[position]);
//                process.setImageResource(pre[position]);
                app_name.setText(name[position]);
                app_time.setText(time[position]);

                return layout;
            }

            @Override
            public long getItemId(int position) {
                // TODO 自动生成的方法存根
                return position;
            }

            @Override
            public Object getItem(int position) {
                // TODO 自动生成的方法存根
                return name[position];
            }

            @Override
            public int getCount() {
                // TODO 自动生成的方法存根
                return name.length;
            }
        };///new BaseAdapter()

        lv1.setAdapter(adapter);

    }
    }

