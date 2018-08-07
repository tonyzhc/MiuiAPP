package com.example.wangdaopeng.myapplication;

import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;




public class Test  extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todayai);

        setTitle("从本机获取数据");
        mUsageStatsManager = (UsageStatsManager) getActivity()
                .getSystemService(Context.USAGE_STATS_SERVICE);



    }

    public static long getUseDuration(String pkgName) {

        // 注意适配性问题，无法找到该类
        try{
            com.android.internal.app.IUsageStats mUsageStatsService = com.android.internal.app.IUsageStats.Stub
                    .asInterface(ServiceManager.getService("usagestats"));
            PkgUsageStats[] stats;
            try {
                stats = mUsageStatsService.getAllPkgUsageStats();
            } catch (Exception e) {
                LogUtil.d("no permission get use duration");
                e.printStackTrace();
                return 0;
            }
            if (stats == null) {
                return 0;
            }

            for (PkgUsageStats ps : stats) {
                if (ps.packageName.equals(pkgName)) {
                    return ps.usageTime;
                }
            }
        }catch(Exception e){

        }
        return 0;
    }




    // 获取使用次数
    public static long getUseTime(String pkgName) {
        // 注意适配性问题，无法找到该类
        try{
            com.android.internal.app.IUsageStats mUsageStatsService = com.android.internal.app.IUsageStats.Stub
                    .asInterface(ServiceManager.getService("usagestats"));
            PkgUsageStats[] stats = null;
            try {
                stats = mUsageStatsService.getAllPkgUsageStats();
            } catch (Exception e) {
                LogUtil.d("no permission get use duration");
                e.printStackTrace();
                return 0;
            }
            if (stats == null) {
                return 0;
            }
            for (PkgUsageStats ps : stats) {
                if (ps.packageName.equals(pkgName)) {
                    return ps.launchCount;
                }
            }
        }catch(Exception e){

        }
        return 0;
    }


}
