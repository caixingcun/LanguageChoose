package com.caixingcun.languagechoose;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.caixingcun.languagechoose.manager.SpManager;
import com.caixingcun.languagechoose.util.SpConstants;

import java.util.List;
import java.util.Locale;

/**
 * @author caixingcun
 * @date 2021/10/21
 * Description :
 */
public class LanguageDelegate {

    private static LanguageDelegate instance = null;

    private List<String> languages = null;

    private List<Locale> locates = null;

    public static LanguageDelegate getInstance() {
        if (instance == null) {
            instance = new LanguageDelegate();
        }
        return instance;
    }

    public void init(List<String> languages, List<Locale> locates) {
        this.languages = languages;
        this.locates = locates;
        SpManager.getInstance();
    }

    /**
     * 获取当前语言 Location
     *
     * @param context
     * @return
     */
    private  Locale getCurrentLanguageLocale(Context context) {
        String country = SpConstants.getCountry(context);

        if (TextUtils.isEmpty(country)) {
            return getSystemLocale(context);
        }
        int position = LanguageDelegate.getInstance().getLanguages().indexOf(country);
        return LanguageDelegate.getInstance().getLocates().get(position);
    }

    /**
     * 获取系统的Location
     *
     * @return Locale对象
     */
    private  Locale getSystemLocale(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * 更新 Locate 相关信息
     *
     * @param context 上下文
     * @return 更新资源后的上下文
     */
    public  Context upDataLocate(Context context) {
        Locale locale = getCurrentLanguageLocale(context);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }


    /**
     * 切换语言  当前支持语言 详见 com.caixingcun.languagechoose.bean.LanguageEnum
     *
     * @param context 上下文
     * @return
     */
    public  boolean switchLanguage(Context context, String language) {
        String country = SpConstants.getCountry(context);
        if (language.equals(country)) {
            return false;
        }
        SpConstants.setCountry(language);
        setApplicationLanguage(context.getApplicationContext());
        return true;
    }

    /**
     * 设置语言类型
     */
    private  void setApplicationLanguage(Context context) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = getCurrentLanguageLocale(context);
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

    public List<String> getLanguages() {
        return languages;
    }

    public List<Locale> getLocates() {
        return locates;
    }
}

