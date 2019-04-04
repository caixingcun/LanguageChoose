package com.caixingcun.languagechoose.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SpManager {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private static SpManager manager = null;

    private SpManager(Context context) {
        sp = context.getSharedPreferences("language_setting", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static SpManager getInstance(Context context) {
        if (manager == null) {
            manager = new SpManager(context);
        }
        return manager;
    }

    public static SpManager getInstance() {
        return manager;
    }

    public void put(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void put(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public void put(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String get(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public int get(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public long get(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    public boolean get(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }
}
