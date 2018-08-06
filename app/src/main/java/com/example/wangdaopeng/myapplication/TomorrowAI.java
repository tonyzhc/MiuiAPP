package com.example.wangdaopeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class TomorrowAI  extends AppCompatActivity{


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent();
            myIntent = new Intent(TomorrowAI.this, TraceActivity_tomorrow.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todayai);

        setTitle("我的AI日记");
        Button yesterday = (Button)findViewById(R.id.AIyesterday);
        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TomorrowAI.this,TodayAI.class);
                startActivity(i);
            }
        });

        Button tomorrow = (Button)findViewById(R.id.AItomorrow);
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(TomorrowAI.this,TomorrowAI.class);
                startActivity(i);
            }
        });

        //设置属性内容
        EditText today_AI_date =(EditText)findViewById(R.id.today_AI_date);
        today_AI_date.setText("2018年7月25日 周四");

        ImageView AI_bg =(ImageView)findViewById(R.id.AI_bg);
        AI_bg.setImageDrawable(getResources().getDrawable(R.mipmap.work3));

        EditText weather =(EditText)findViewById(R.id.weather);
        weather.setText("今天电闪雷鸣，下了大暴雨");

        EditText sleep =(EditText)findViewById(R.id.sleep);
        sleep.setText("昨晚睡了9小时40分钟，睡眠很充足");

        EditText visit =(EditText)findViewById(R.id.visit);
        visit.setText("今天哪都没去，宅在家里一整天");

        EditText spend =(EditText)findViewById(R.id.spend);
        spend.setText("今天在聊天社交上花了2小时50分钟，在影视娱乐上花了4小时30分钟");

        EditText busy =(EditText)findViewById(R.id.busy);
        busy.setText("悠闲的一天马上过去了");








    }
}