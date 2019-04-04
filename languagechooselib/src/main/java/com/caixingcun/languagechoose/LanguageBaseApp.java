package com.caixingcun.languagechoose;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import com.caixingcun.languagechoose.manager.SpManager;
import com.caixingcun.languagechoose.util.LanguageUtil;

import java.util.List;
import java.util.Locale;

public class LanguageBaseApp extends Application {

    private static List<String> sLanguages;

    private static List<Locale> mLocates;

    public static void setmLocates(List<Locale> mLocates) {
        LanguageBaseApp.mLocates = mLocates;
    }

    public static List<Locale> getmLocates() {
        return mLocates;
    }

    public static void setLanguage(List<String> language) {
        LanguageBaseApp.sLanguages = language;
    }

    public static List<String> getLanguage() {
        return sLanguages;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        SpManager.getInstance(this);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageUtil.upDataLocate(context));
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LanguageUtil.upDataLocate(this);
    }

    /**
     * 设置语言类型
     */
    public static void setApplicationLanguage(Context context) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = LanguageUtil.getCurrentLanguageLocale(context);
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.getApplicationContext().createConfigurationContext(config);
            Locale.setDefault(locale);
        }
        resources.updateConfiguration(config, dm);
    }
}
