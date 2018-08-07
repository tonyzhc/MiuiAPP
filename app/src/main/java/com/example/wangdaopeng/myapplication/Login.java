package com.example.wangdaopeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;

public class Login extends AppCompatActivity {
    long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK || event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        setTitle("登录界面");


//        Field field = null;
//        try {
//            field = R.mipmap.class.getField("baidu");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        int id = 0;
//        try {
//            id = (Integer)field.get(new R.mipmap());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        if(id==R.mipmap.baidu){
//            Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();
//        }

        Button submit =(Button)findViewById(R.id.login_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText account_view =(EditText) findViewById(R.id.login_account);
                String account = account_view.getText().toString();

                EditText password_view =(EditText)findViewById(R.id.login_password);
                String password = password_view.getText().toString();

                CurrentUser currentUser = CurrentUser.getInstance();

                System.out.print(account+":"+password);


//      测试是否能进行正常的网络通信
//                String welcome  = null;
//                try {
//                    welcome = currentUser.get_welcome();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Toast.makeText(getApplicationContext(),welcome,Toast.LENGTH_SHORT).show();

                try {
                    String login_res = currentUser.login(account,password);
                    if (login_res.equals("success")){
                        currentUser.setUsename(account);
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        Intent i =  new Intent(Login.this,MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "exception", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
