package com.example.wangdaopeng.myapplication;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Browser;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class Test extends AppCompatActivity {
    private  final String  DATABASE_PATH  = Environment.getDataDirectory().getPath()+"/data/com.android.chrome/app_chrome/Default/History";
    //     private  final String  DATABASE_PATH  =  "com/example/wangdaopeng/History.db";
    SQLiteDatabase db;

    private String openDatabase() {

        String res = "";
        try {
            String databaseFilename = DATABASE_PATH ;
            db = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
        } catch (Exception e) {
            System.out.print("abccccdddd");
            e.printStackTrace();
        }
        Cursor cursor = db.rawQuery("select term from keyword_search_terms",null);
        while (cursor.moveToNext()){
            String record = cursor.getString(0);
            res+=record;

        }
        db.close();
        cursor.close();
        return res;
    }



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        TextView editText = (TextView) findViewById(R.id.testtext);


//        editText.setText(openDatabase());

        Resources res = getResources();
        Button buttontmp = (Button) findViewById(res.getIdentifier("testbutton","id",getPackageName()));
        buttontmp.setText("successful");

        }



    }

