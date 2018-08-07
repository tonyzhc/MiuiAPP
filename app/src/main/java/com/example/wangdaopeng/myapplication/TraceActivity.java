package com.example.wangdaopeng.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TraceActivity extends AppCompatActivity {
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent();
            myIntent = new Intent(TraceActivity.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private RecyclerView rvTrace;
    private List<Trace> traceList = new ArrayList<>(10);
    private TraceListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DecimalFormat df  = new DecimalFormat("00");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trace);

        final int y = getIntent().getIntExtra("y",0);
        final int m = getIntent().getIntExtra("m",0);
        final int d = getIntent().getIntExtra("d",0);

        /**if flag ==1 then the data should get from local phone
         *
         *if flag ==0  then the data should get from remote
         */


        final int flag = getIntent().getIntExtra("flag",0);

        //生成一个calender以便于后面跳转
        Calendar calendar = Calendar.getInstance();
        calendar.set(y,m,d);
        //获得前一天和后一天的时间，标准格式,用于请求数据
        calendar.add(calendar.DATE,-1);
        final String yesterday_date_string = String.valueOf(calendar.get(calendar.YEAR))+df.format(calendar.get(calendar.MONTH))+df.format(calendar.get(calendar.DAY_OF_MONTH));
        calendar.add(calendar.DATE,+2);
        final String tomorrow_date_string  = String.valueOf(calendar.get(calendar.YEAR))+df.format(calendar.get(calendar.MONTH))+df.format(calendar.get(calendar.DAY_OF_MONTH));


        //设置datepicker 在界面上显示当前的时间

        String date =  String.valueOf(y) + df.format(m) + df.format(d);
        setTitle(date);
        DatePicker datePicker = (DatePicker)findViewById(R.id.datapicker);
        datePicker.updateDate(y,m,d);




        Button AI = (Button)findViewById(R.id.today_myAI);
        AI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TraceActivity.this,TodayAI.class);
                i.putExtra("y",y);
                i.putExtra("m",d);
                i.putExtra("d",m);
                startActivity(i);
            }
        });

        //跳转到前一天
        Button yesterday = (Button)findViewById(R.id.trace_yesterday);
        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =  new Intent(TraceActivity.this,TraceActivity.class);
                i.putExtra("y",Integer.valueOf(yesterday_date_string.substring(0,4)));
                i.putExtra("m",Integer.valueOf(yesterday_date_string.substring(4,6)));
                i.putExtra("d",Integer.valueOf(yesterday_date_string.substring(6,8)));
                Toast.makeText(getApplicationContext(),"to yesterday"+String.valueOf(Integer.valueOf(yesterday_date_string.substring(6,8))),Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });


        //跳转到后一天
        Button tomorrow = (Button)findViewById(R.id.trace_tomorrow);
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TraceActivity.this,TraceActivity.class);
                i.putExtra("y",Integer.valueOf(tomorrow_date_string.substring(0,4)));
                i.putExtra("m",Integer.valueOf(tomorrow_date_string.substring(4,6)));
                i.putExtra("d",Integer.valueOf(tomorrow_date_string.substring(6,8)));
                startActivity(i);
            }
        });

      //刷新
      // 获取并设置日历的时间，点击刷新按钮
        Button refresh = (Button)findViewById(R.id.trace_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker datePicker =(DatePicker)findViewById(R.id.datapicker);
                int y =  datePicker.getYear();
                int m =  datePicker.getMonth();
                int d =  datePicker.getDayOfMonth();

                Intent i =  new Intent(TraceActivity.this,TraceActivity.class);
                i.putExtra("y",y);
                i.putExtra("m",m);
                i.putExtra("d",d);
                startActivity(i);
            }
        });

        findView();

        try {
            try {
                if (flag==0)
                // notice!!!!
                      initData_fromRemmote(date);
                else
                      initData_fromLocal();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e){
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void findView() {
        rvTrace = (RecyclerView) findViewById(R.id.rvTrace);
    }

    //system get josn data

    private void initData_fromRemmote(String date) throws JSONException, IOException, NoSuchFieldException, IllegalAccessException {

                CurrentUser currentUser =CurrentUser.getInstance();
        JSONObject  jsonObject = new JSONObject(currentUser.get_trace(date));
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = jsonObject.getString(key);
            Field field =  R.mipmap.class.getField(value);
            int id =(Integer)field.get(new R.mipmap());
            traceList.add(new Trace(key,"使用"+Global_Data.getInstance().get_picname().get(value),id));
        }

        traceList.add(new Trace("2018-07-24 22:00:00", "通话15分钟",R.mipmap.ddh));
        adapter = new TraceListAdapter(this, traceList);
        rvTrace.setLayoutManager(new LinearLayoutManager(this));
        rvTrace.setAdapter(adapter);
    }

    private void initData_fromLocal() throws JSONException, IOException, NoSuchFieldException, IllegalAccessException {

        traceList.add(new Trace("2018-07-24 22:00:00", "通话15分钟",R.mipmap.ddh));
        adapter = new TraceListAdapter(this, traceList);
        rvTrace.setLayoutManager(new LinearLayoutManager(this));
        rvTrace.setAdapter(adapter);
    }
}
