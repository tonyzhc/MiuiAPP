package com.example.wangdaopeng.myapplication;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class Test extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);
         String res ="a";

        TextView textView = (TextView) findViewById(R.id.testtext);
        ContentResolver contentResolver =getContentResolver();
        Cursor cursor = contentResolver.query(
                Uri.parse("content://browser/bookmarks"), new String[] {
                        "title", "url", "date" }, "date!=?",
                new String[] { "null" }, "date desc");
        while (cursor != null && cursor.moveToNext()) {
            String url = null;
            String title = null;
            String time = null;
            String date = null;

            title = cursor.getString(cursor.getColumnIndex("title"));
            url = cursor.getString(cursor.getColumnIndex("url"));

            date = cursor.getString(cursor.getColumnIndex("date"));

            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd hh:mm;ss");
            Date d = new Date(Long.parseLong(date));
            time = dateFormat.format(d);

            res += title + url + time+"\n";
        }
        textView.setText(res);
    }








}
