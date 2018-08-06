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
import android.widget.DatePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace);
        setTitle("我的足迹");

        DatePicker datePicker =(DatePicker)findViewById(R.id.datapicker);
        datePicker.updateDate(2018,6,24);

        Button AI = (Button)findViewById(R.id.today_myAI);
        AI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TraceActivity.this,TodayAI.class);
                startActivity(i);
            }
        });


        Button yesterday = (Button)findViewById(R.id.trace_yesterday);
        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TraceActivity.this,TraceActivity_yesterday.class);
                startActivity(i);
            }
        });

        Button tomorrow = (Button)findViewById(R.id.trace_tomorrow);
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TraceActivity.this,TraceActivity_tomorrow.class);
                startActivity(i);
            }
        });

        findView();
        String date = "date";
        try {
            try {
                // notice!!!!
                initData(date);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
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

    private void initData(String date) throws JSONException, IOException, NoSuchFieldException, IllegalAccessException {
        CurrentUser currentUser =CurrentUser.getInstance();
        JSONObject  jsonObject = new JSONObject(currentUser.get_trace(date));
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = jsonObject.getString(key);
            Field field =  R.mipmap.class.getField(value);
            int id =(Integer)field.get(new R.mipmap());
            traceList.add(new Trace(key,"使用"+value,id));
        }



        traceList.add(new Trace("2018-07-24 22:00:00", "通话15分钟",R.mipmap.ddh));


        adapter = new TraceListAdapter(this, traceList);
        rvTrace.setLayoutManager(new LinearLayoutManager(this));
        rvTrace.setAdapter(adapter);
    }
}

