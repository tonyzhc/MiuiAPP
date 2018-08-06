package com.example.wangdaopeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TraceActivity_tomorrow extends AppCompatActivity {

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent();
            myIntent = new Intent(TraceActivity_tomorrow.this, MainActivity.class);
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
        datePicker.updateDate(2018,6,25);

        Button AI = (Button)findViewById(R.id.today_myAI);
        AI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TraceActivity_tomorrow.this,TomorrowAI.class);
                startActivity(i);
            }
        });

        Button yesterday = (Button)findViewById(R.id.trace_yesterday);
        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TraceActivity_tomorrow.this,TraceActivity.class);
                startActivity(i);
            }
        });

        Button tomorrow = (Button)findViewById(R.id.trace_tomorrow);
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TraceActivity_tomorrow.this,TraceActivity_tomorrow.class);
                startActivity(i);
            }
        });




        findView();
        try {
            initData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void findView() {
        rvTrace = (RecyclerView) findViewById(R.id.rvTrace);
    }

    //system get josn data

    private void initData() throws JSONException {

//        System.out.print("+++++++++++aaaaaaaaaaaa++++++++");
//        String field_name = "personal_trace" ;
//        Deal_Json deal_json = new Deal_Json(date,field_name);
//        Map data =  deal_json.get_json();
//        Iterator  iterator = data.entrySet().iterator();




        traceList.add(new Trace("2018-07-25 21:00:00", "打开手机手电筒",R.mipmap.sdt));
        traceList.add(new Trace("2018-07-25 20:00:00", "通话15分钟",R.mipmap.ddh));
        traceList.add(new Trace("2018-07-25 19:00:00", "在xx面馆支付10元",R.mipmap.mg));
        traceList.add(new Trace("2018-07-25 18:00:00", "使用嘀嘀打车",R.mipmap.dd));
        traceList.add(new Trace("2018-07-25 17:00:00", "收到一条短信",R.mipmap.dx));
        traceList.add(new Trace("2018-07-25 16:00:00", "使用小米音箱15分钟",R.mipmap.xmyy));
        traceList.add(new Trace("2018-07-25 15:00:00", "爱奇艺视频30分钟",R.mipmap.aqysp));
        traceList.add(new Trace("2018-07-25 14:00:00", "使用百度地图10分钟",R.mipmap.bddt));
        traceList.add(new Trace("2018-07-25 13:00:00", "跑步40分钟",R.mipmap.pb));
        traceList.add(new Trace("2018-07-25 12:00:00", "小米商城购买xx",R.mipmap.xmsc));
        traceList.add(new Trace("2018-07-25 11:00:00", "小米应用商店下载游戏",R.mipmap.yysd));
        traceList.add(new Trace("2018-07-25 10:00:00", "使用今日头条10分钟",R.mipmap.jrtt));
        traceList.add(new Trace("2018-07-25 09:00:00", "爱奇艺视频30分钟",R.mipmap.aqysp));
        traceList.add(new Trace("2018-07-25 08:00:00", "使用美团外卖订餐",R.mipmap.mtwm));
        traceList.add(new Trace("2018-07-25 07:00:00", "使用网易音乐30分钟",R.mipmap.wyyy));
        traceList.add(new Trace("2018-07-25 06:00:00", "使用微信20分钟",R.mipmap.wechat));

        traceList.add(new Trace("2018-07-25 06:00:00", "闹钟响",R.mipmap.nz));

        adapter = new TraceListAdapter(this, traceList);
        rvTrace.setLayoutManager(new LinearLayoutManager(this));
        rvTrace.setAdapter(adapter);
    }
}