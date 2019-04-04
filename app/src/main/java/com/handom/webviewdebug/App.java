package com.handom.webviewdebug;

import android.app.Application;
import android.content.Context;

import com.caixingcun.languagechoose.LanguageBaseApp;

import java.util.Arrays;
import java.util.Locale;


public class App extends LanguageBaseApp {
    public static String[] languages = {"CN", "HK", "TW", "EN"};
    public static Locale[] locates = {Locale.CHINA, Locale.TRADITIONAL_CHINESE, Locale.TAIWAN, Locale.ENGLISH};

    static {
        setLanguage(Arrays.asList(languages));
        setmLocates(Arrays.asList(locates));
    }


}
