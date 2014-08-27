package com.peony.iview.global;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by wdynetposa on 14-8-9.
 */
public class DataHelper {

    private static final String datafile = "config_data";

    public static <T> boolean SetData(Context context, String key, T value) {
        SharedPreferences userInfo = context.getSharedPreferences(datafile, 0);
        if (value instanceof Boolean)
            userInfo.edit().putBoolean(key, (Boolean) value).commit();
        else if (value instanceof Float)
            userInfo.edit().putFloat(key, (Float) value).commit();
        else if (value instanceof Integer)
            userInfo.edit().putInt(key, (Integer) value).commit();
        else if (value instanceof Long)
            userInfo.edit().putLong(key, (Long) value).commit();
        else if (value instanceof String)
            userInfo.edit().putString(key, (String) value).commit();
        else
            return false;
        return true;
    }

    public static <T> T GetData(Context context, String key, T defaultValue) {
        T result;
        SharedPreferences userInfo = context.getSharedPreferences(datafile, 0);
        if (defaultValue instanceof Boolean) {
            Boolean data = userInfo.getBoolean(key, (Boolean) defaultValue);
            result = (T) data;
        } else if (defaultValue instanceof Float) {
            Float data = userInfo.getFloat(key, (Float) defaultValue);
            result = (T) data;
        } else if (defaultValue instanceof Integer) {
            Integer data = userInfo.getInt(key, (Integer) defaultValue);
            result = (T) data;
        } else if (defaultValue instanceof Long) {
            Long data = userInfo.getLong(key, (Long) defaultValue);
            result = (T) data;
        } else if (defaultValue instanceof String) {
            String data = userInfo.getString(key, (String) defaultValue);
            result = (T) data;
        } else
            return null;
        return result;
    }

    public static Boolean IsFirstShow(Context context) {
        PackageInfo info = null;
        try {
            PackageManager manager = context.getPackageManager();
            String packageName = context.getPackageName();
            info = manager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int currentVersion = info.versionCode;
        int version = GetData(context, ConstDefine.RUNTIME_VERSION, 0);

        if (version != currentVersion) {
            SetData(context, ConstDefine.RUNTIME_VERSION, currentVersion);
            SetData(context, ConstDefine.IS_FIRST_SHOW, true);
            return true;
        }

        Boolean isFirst = GetData(context, ConstDefine.IS_FIRST_SHOW, true);
        return isFirst;
    }
}
