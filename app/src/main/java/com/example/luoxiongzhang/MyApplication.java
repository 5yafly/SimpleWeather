package com.example.luoxiongzhang;

import android.app.Application;

import com.qweather.sdk.view.HeConfig;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HeConfig.init("HE2305300905391451", "49f67a7e606a4a3092fda65fc7e247ff");
        HeConfig.switchToBizService();
    }
}
