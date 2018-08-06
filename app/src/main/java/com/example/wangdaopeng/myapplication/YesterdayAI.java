package com.example.wangdaopeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class YesterdayAI extends AppCompatActivity {



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent();
            myIntent = new Intent(YesterdayAI.this, TraceActivity_tomorrow.class);
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
                Intent i =  new Intent(YesterdayAI.this,YesterdayAI.class);
                startActivity(i);
            }
        });

        Button tomorrow = (Button)findViewById(R.id.AItomorrow);
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(YesterdayAI.this,TodayAI.class);
                startActivity(i);
            }
        });

        //设置属性内容
        EditText today_AI_date =(EditText)findViewById(R.id.today_AI_date);
        today_AI_date.setText("2018年7月23日 周二");

        ImageView AI_bg =(ImageView)findViewById(R.id.AI_bg);
        AI_bg.setImageDrawable(getResources().getDrawable(R.mipmap.work2));

        EditText weather =(EditText)findViewById(R.id.weather);
        weather.setText("今天下小雨，空气很清新");

        EditText sleep =(EditText)findViewById(R.id.sleep);
        sleep.setText("昨晚睡了5小时20分钟，睡眠不足哦");

        EditText visit =(EditText)findViewById(R.id.visit);
        visit.setText("今天去了一个地方：科利源大厦");

        EditText spend =(EditText)findViewById(R.id.spend);
        spend.setText("今天在聊天社交上花了30分钟，在游戏上花了3小时15分钟");







    }
}






