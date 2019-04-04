package com.caixingcun.languagechoose.util;

import android.content.Context;

import com.caixingcun.languagechoose.manager.SpManager;

/**
 * save some static field about shared preference
 */
public class SpConstants {
    private static final String SP_COUNTRY = "country";

    public static final String getCountry(Context context) {
        return SpManager.getInstance(context).get(SP_COUNTRY, "");
    }
    public static final void setCountry(String country) {
        SpManager.getInstance().put(SP_COUNTRY, country);
    }

}
