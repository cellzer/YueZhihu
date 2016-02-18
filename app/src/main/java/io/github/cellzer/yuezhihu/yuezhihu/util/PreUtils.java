package io.github.cellzer.yuezhihu.yuezhihu.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by wwjun.wang on 2015/8/19.
 */
public class PreUtils {
    public static void putStringToDefault(Context context, String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(key, value).commit();
    }

    public static String getStringFromDefault(Context context, String key, String defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key, defValue);
    }
    public static void putBooleanToDefault(Context context, String key, boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBooleanFromDefault(Context context, String key, boolean defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(key,defValue);
    }

  /*  public static void putStringTo(String name, Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getStringFrom(String name, Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }*/
}
