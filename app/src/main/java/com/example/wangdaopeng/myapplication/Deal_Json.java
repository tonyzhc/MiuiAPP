package com.example.wangdaopeng.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Deal_Json extends AppCompatActivity {
    private String file_name;
    private String field_name;

    public  Deal_Json(){

    }


    public Deal_Json(String date, String name) {
        this.file_name = date + ".json";
        this.field_name = name;

    }

    public HashMap<String, String> get_json() {
        HashMap<String, String> res = new HashMap<>();
        res.put("a","b");
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager   = getApplicationContext().getAssets();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(file_name),"utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());

            Log.i("TESTJSON", "cat=" + jsonObject.getString("label"));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return res;
    }

}
