package com.handom.webviewdebug;


import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.caixingcun.languagechoose.LanguageDelegate;

import java.util.Arrays;
import java.util.Locale;


public class App extends Application {
    public static String[] languages = {"CN", "HK", "TW", "EN"};
    public static Locale[] locates = {Locale.CHINA, Locale.TRADITIONAL_CHINESE, Locale.TAIWAN, Locale.ENGLISH};

    static {
        LanguageDelegate.getInstance().init(Arrays.asList(languages), Arrays.asList(locates));
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        LanguageDelegate.getInstance().upDataLocate(base);
        super.attachBaseContext(base);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LanguageDelegate.getInstance().upDataLocate(this);
    }
}
