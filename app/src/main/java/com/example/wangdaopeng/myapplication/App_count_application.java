package com.example.wangdaopeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

    private int[] ic={R.mipmap.wechat,R.mipmap.qq,R.mipmap.weibo,R.mipmap.xlyy,R.mipmap.sll,R.mipmap.cs,R.mipmap.nba,R.mipmap.zhifubao};
    private int[] pre={R.mipmap.process_4,R.mipmap.process_3,R.mipmap.process_2,R.mipmap.process_1,R.mipmap.process_1,R.mipmap.process_1,R.mipmap.process_1,R.mipmap.process_1};
    private	String[] name={"微信","QQ","微博","迅雷视频","360","cs枪战","NBA直播","支付宝"};
    private  String[] time={"40min","30min","20min","10min","10min","10min","10min","10min"};

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.app_count);

            setTitle("App统计-应用显示");

            Button bt = (Button)findViewById(R.id.show_category) ;
            bt.setText("显示类别");

            ImageView imageView =(ImageView)findViewById(R.id.zhuzhuangtu);
            imageView.setImageResource(R.mipmap.yytj);

            Button show_category =(Button)findViewById(R.id.show_category);
            show_category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =  new Intent(App_count_application.this,App_count_category.class);
                    startActivity(i);
                }
            });



        Button today =(Button)findViewById(R.id.app_today);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(App_count_application.this,App_count_application.class);
                startActivity(i);
            }
        });

        Button yesterday =(Button)findViewById(R.id.app_yesterday);
        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(App_count_application.this,App_count_application.class);
                startActivity(i);
            }
        });

        Button week =(Button)findViewById(R.id.app_last_week);
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(App_count_application.this,App_count_application.class);
                startActivity(i);
            }
        });


        Button month =(Button)findViewById(R.id.app_last_month);
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(App_count_application.this,App_count_application.class);
                startActivity(i);
            }
        });







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


                app_icon.setImageResource(ic[position]);
                process.setImageResource(pre[position]);
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

