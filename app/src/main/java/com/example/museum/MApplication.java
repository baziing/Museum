package com.example.museum;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this,"e348d3c336d7b8a4fe71b195016b06cc");
    }
}
