package com.example.wangdaopeng.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
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
        List<String> data = new ArrayList<>();

        try {
            //Read labels from local json file

            //Convert InputStream to a string; cannot use toString() because it uses the one from java.lang.Object
            InputStream test = getAssets().open("test.json");
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(test, "UTF-8"));
            StringBuilder builder = new StringBuilder();
            String input;
            while((input = streamReader.readLine()) != null) {
                builder.append(input);
            }

            //get JSONObject and read into the data List
            JSONObject jdata = new JSONObject(builder.toString());
            JSONArray json_labels = (JSONArray) jdata.get("label");
            for(int i = 0; i < json_labels.length(); i++)
                data.add(json_labels.getString(i));
        } catch(IOException iexc) {
            System.out.println(iexc.getStackTrace()[0].getLineNumber());
            System.out.println(iexc.getMessage());
        } catch(JSONException jexc) {
            jexc.printStackTrace();
        }

        //set up recyclerView and feed data
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
                JSONArray currentLabels = ((LabelListAdapter) adapter).getLabels();
                JSONObject writeData = new JSONObject();
                try {
                    writeData.put("label", currentLabels);
                    System.out.println(writeData.toString());
                    File root = new File(Environment.getExternalStorageDirectory(), "Notes");
                    if (!root.exists()) {
                        root.mkdirs();
                    }
                    File gpxfile = new File(root, "test_out.json");
                    FileWriter writer = new FileWriter(gpxfile);
                    writer.append(writeData.toString());
                    writer.flush();
                    writer.close();

                    Toast.makeText(getApplicationContext(), "标签更新成功", Toast.LENGTH_SHORT).show();
                } catch (JSONException jexc) {
                    jexc.printStackTrace();
                } catch (FileNotFoundException fexc) {
                    fexc.printStackTrace();
                } catch (UnsupportedEncodingException uexc) {
                    uexc.printStackTrace();
                } catch (IOException iexc) {
                    iexc.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        ((LabelListAdapter) adapter).addLabel("请修改新标签");
        //LabelListAdapter.ViewHolder vh = (LabelListAdapter.ViewHolder) rv.getChildViewHolder(layoutManager.findViewByPosition(((GridLayoutManager) layoutManager).findLastVisibleItemPosition()));
        //vh.selectItem(((GridLayoutManager) layoutManager).findLastVisibleItemPosition());

        return super.onOptionsItemSelected(item);
    }
}