package com.example.wangdaopeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LabelFeedback extends AppCompatActivity {
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    //@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent(LabelFeedback.this, profile.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.label_feedback_listview);
        setTitle("标签问题反馈");
        List<String> data;

        // 此dummy_variable仅为示范，实际将接受一个由服务器传来的字符串，且不一定以#分割
//        String dummy_variable = "#标签#标签#标签#标签#标签#标签#标签#标签#标签#标签#标签#标签#标签#标签#标签#标签";
        String dummy_variable = null;
        try {
             dummy_variable = CurrentUser.getInstance().get_label();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = new ArrayList<>(Arrays.asList(dummy_variable.split("#")));
        data.remove(0);


        rv = findViewById(R.id.label_rv);
        rv.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);
        adapter = new LabelListAdapter(data);
        rv.setAdapter(adapter);

        Button fd_button = (Button) findViewById(R.id.save_label_change);
        fd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(LabelFeedback.this,profile.class);
                String flag ="2";
                String result = ((LabelListAdapter) adapter).getLabels();
                Log.v("liuqi",result);
                try {
                     flag =CurrentUser.getInstance().submit_label(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(flag.equals("1"))
                    Toast.makeText(getApplicationContext(), "标签更新成功", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "标签更新失败", Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ((LabelListAdapter) adapter).addLabel("请修改新标签");
        return super.onOptionsItemSelected(item);
    }
}