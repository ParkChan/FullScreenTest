package com.example.fullscreentest;

import androidx.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class FullScreenApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //디버그 모드에서만 동작하도록 설정
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
