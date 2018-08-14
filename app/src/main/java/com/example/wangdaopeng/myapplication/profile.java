package com.example.wangdaopeng.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class profile extends AppCompatActivity {

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent();
            myIntent = new Intent(profile.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        setTitle("我的个人画像");

        String  labelString = "";
        //批量获取标签
//        try {
//             labelString = CurrentUser.getInstance().get_label();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            labelString = CurrentUser.getInstance().get_label().substring(1,CurrentUser.getInstance().get_label().length()-1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] userLabel = labelString.split("#");
        String[]  Idname =  {"userlabel1","userlabel2","userlabel3","userlabel4","userlabel5","userlabel6","userlabel7","userlabel8","userlabel9"};
        Resources res =  getResources();
        ArrayList<Button> buttonslist = new ArrayList<>();
        for(int i =0;i<Idname.length;i++){
            Button buttontmp = (Button) findViewById(res.getIdentifier(Idname[i],"id",getPackageName()));
            buttontmp.setText(userLabel[i]);
        }


        Button label_feedback =(Button)findViewById(R.id.to_feedback);
        label_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(profile.this,LabelFeedback.class);
                startActivity(i);
            }
        });
    }
}
