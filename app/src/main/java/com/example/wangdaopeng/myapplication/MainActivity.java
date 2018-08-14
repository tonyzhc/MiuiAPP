package com.example.wangdaopeng.myapplication;

import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public boolean onKeyDown(int keyCode, KeyEvent event) {
         long exitTime = 0;
        if(keyCode == KeyEvent.KEYCODE_BACK || event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 100){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private TextView topBar;
    private TextView tabDeal;
    private TextView tabPoi;
    private TextView tabMore;
    private TextView tabUser;

    private FrameLayout ly_content;

    private FirstFragment f1,f2,f3,f4;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTitle("APP主界面");
        setContentView(R.layout.activity_main);
        bindView();
        Toast.makeText(getApplicationContext(),CurrentUser.getInstance().getUsename(),Toast.LENGTH_SHORT).show();


         Button totest =  (Button)findViewById(R.id.totest);
         totest.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i =  new Intent(MainActivity.this,Test.class);
                 startActivity(i);
             }
         });



    }

    //UI组件初始化与事件绑定
    private void bindView() {
//        topBar = (TextView)this.findViewById(R.id.txt_top);
        tabDeal = (TextView)this.findViewById(R.id.txt_deal);
        tabPoi = (TextView)this.findViewById(R.id.txt_poi);
        tabUser = (TextView)this.findViewById(R.id.txt_user);
        tabMore = (TextView)this.findViewById(R.id.txt_more);
        ly_content = (FrameLayout) findViewById(R.id.fragment_container);
//不设置就没法点击
        tabDeal.setOnClickListener(this);
        tabMore.setOnClickListener(this);
        tabUser.setOnClickListener(this);
        tabPoi.setOnClickListener(this);

    }

    //重置所有文本的选中状态
    public void selected(){
        tabDeal.setSelected(false);
        tabMore.setSelected(false);
        tabPoi.setSelected(false);
        tabUser.setSelected(false);
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }
        if(f4!=null){
            transaction.hide(f4);
        }
    }


    @Override
    public void onClick(View v) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            hideAllFragment(transaction);
            switch(v.getId()){
            case R.id.txt_deal:
                Calendar now =  Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = sdf.format(now.getTime());
                System.out.print(dateString);
                int y = Integer.parseInt(dateString.split("-")[0]);
                int m = Integer.parseInt(dateString.split("-")[1]);
                int d = Integer.parseInt(dateString.split("-")[2]);

                CurrentUser.getInstance().setCurrent_date(y*1000+m*100+d);

                System.out.print("******************");
                System.out.print(y);
                System.out.print(m);
                System.out.print(d);
                System.out.print("******************");

                Intent i = new Intent(this,TraceActivity.class);
                i.putExtra("y",y);
                i.putExtra("m",m);
                i.putExtra("d",d);
                i.putExtra("flag",0);
                startActivity(i);
                break;

            case R.id.txt_more:

                Intent l = new Intent(this,My_info.class);
                startActivity(l);

                break;

            case R.id.txt_poi:

                Intent k = new Intent(this,profile.class);
                startActivity(k);
                break;

            case R.id.txt_user:

                Intent j = new Intent(this,App_count_application.class);
                startActivity(j);
                break;
        }

        transaction.commit();
    }
}