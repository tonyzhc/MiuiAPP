package com.example.wangdaopeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

public class AIDiary extends AppCompatActivity {

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent();
            myIntent = new Intent(AIDiary.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aidiary);

        DecimalFormat df  = new DecimalFormat("00");
        final int y = getIntent().getIntExtra("y",0);
        final int m = getIntent().getIntExtra("m",0);
        final int d = getIntent().getIntExtra("d",0);

        String date =  String.valueOf(y) + df.format(m) + df.format(d);
        setTitle(date);
        //生成一个calender以便于后面跳转


        Calendar calendar = Calendar.getInstance();
        calendar.set(y,m-1,d);
        //获得前一天和后一天的时间，标准格式,用于请求数据
        calendar.add(calendar.DATE,-1);
        final String yesterday_date_string = String.valueOf(calendar.get(calendar.YEAR))+df.format(calendar.get(calendar.MONTH))+df.format(calendar.get(calendar.DAY_OF_MONTH));
        calendar.add(calendar.DATE,+2);
        final String tomorrow_date_string  = String.valueOf(calendar.get(calendar.YEAR))+df.format(calendar.get(calendar.MONTH))+df.format(calendar.get(calendar.DAY_OF_MONTH));


        //设置datepicker 在界面上显示当前的时间

        DatePicker datePicker = (DatePicker)findViewById(R.id.Aidatapicker);
        datePicker.updateDate(y,m-1,d);

        /** 显示日记**/
        TextView textView =(TextView)findViewById(R.id.Aidairy);
        try {
            textView.setText(CurrentUser.getInstance().get_dairy(date));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Button yesterday = (Button)findViewById(R.id.AIyesterday);
        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(AIDiary.this,AIDiary.class);
                i.putExtra("y",Integer.valueOf(yesterday_date_string.substring(0,4)));
                i.putExtra("m",Integer.valueOf(yesterday_date_string.substring(4,6))+1);
                i.putExtra("d",Integer.valueOf(yesterday_date_string.substring(6,8)));
                i.putExtra("flag",1);
                startActivity(i);
            }
        });

        Button tomorrow = (Button)findViewById(R.id.AItomorrow);
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(AIDiary.this,AIDiary.class);
                i.putExtra("y",Integer.valueOf(tomorrow_date_string.substring(0,4)));
                i.putExtra("m",Integer.valueOf(tomorrow_date_string.substring(4,6))+1);
                i.putExtra("d",Integer.valueOf(tomorrow_date_string.substring(6,8)));
                startActivity(i);
            }
        });





    }
}
