package com.example.wangdaopeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class My_info extends AppCompatActivity {
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent();
            myIntent = new Intent(My_info.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);
        setTitle("我的信息");

        Button my_ai = (Button)findViewById(R.id.myinfo_myai);
        my_ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(My_info.this,AIDiary.class);
                startActivity(i);
            }
        });


        Button my_app = (Button)findViewById(R.id.myinfo_myapp);
        my_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(My_info.this,App_count_application.class);
                startActivity(i);
            }
        });


        Button my_label = (Button)findViewById(R.id.myinfo_mylabel);
        my_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(My_info.this,profile.class);
                startActivity(i);
            }
        });


        Button my_trace = (Button)findViewById(R.id.myinfo_trace);
        my_trace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(My_info.this,AIDiary.class);
                startActivity(i);
            }
        });

        Button my_fb = (Button)findViewById(R.id.myinfo_myfb);
        my_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(My_info.this,My_info.class);
                startActivity(i);
            }
        });

        Button my_set = (Button)findViewById(R.id.myinfo_myset);
        my_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(My_info.this,My_info.class);
                startActivity(i);
            }
        });



    }
}