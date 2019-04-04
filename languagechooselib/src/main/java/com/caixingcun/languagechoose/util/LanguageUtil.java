package com.caixingcun.languagechoose.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;

import com.caixingcun.languagechoose.LanguageBaseApp;

import java.util.Locale;

public class LanguageUtil {
    /**
     * 获取当前语言 Location
     *
     * @param context
     * @return
     */
    public static Locale getCurrentLanguageLocale(Context context) {
        String country = SpConstants.getCountry(context);

        if (TextUtils.isEmpty(country)) {
            return getSystemLocale(context);
        }
        int position = LanguageBaseApp.getLanguage().indexOf(country);
        return LanguageBaseApp.getmLocates().get(position);

    }

    /**
     * 获取系统的Location
     *
     * @return Locale对象
     */
    private static Locale getSystemLocale(Context context) {
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
    public static Context upDataLocate(Context context) {
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
     * @param context  上下文
     * @return
     */
    public static boolean switchLanguage(Context context, String language) {
        String country = SpConstants.getCountry(context);
        if (language.equals(country)) {
            return false;
        }
        SpConstants.setCountry(language);
        LanguageBaseApp.setApplicationLanguage(context.getApplicationContext());
        return true;
    }


}


